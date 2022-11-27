# go-cloud
go over SpringCloud 

> https://github.com/alibaba/spring-cloud-alibaba


## Nacos
> 注册中心（服务发现/注册）and 配置中心（动态配置管理）

https://github.com/alibaba/nacos/releases/tag/1.1.4

```
namespace 与 group 最佳实践
每个微服务创建自己的 namespace 进行隔离，group 来区分 dev，test，prod 等环境
如group还可以取双11或者双12的环境，灵活运用
nacos 的DataId = ${spring.application.name}.properties
```

## Feign：声明式 HTTP 客户端（调用远程服务）
> 自带Ribbon：负载均衡

## Gateway：API 网关
> 取代zuul网关的新规范

https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.1.3.RELEASE/single/spring-cloud-gateway.html

```
API 网关是介于客户端和服务器端之间的中间层， 所有的外部请求都会先经过 API 网关这一层

API 网关提供 API 全托管服务，丰富的 API 管理功能，辅助企业管理大规模的 API，以降低管理 
成本和安全风险，包括协议适配、协议转发、安全策略、防刷、流量、监控日志等功能
```


### 与Zuul网关区别用法
```yaml
zuul:
  routes:
    tensquare-base: #基础
      path: /base/**
      serviceId: tensquare-base
    tensquare-user: #用户
      path: /user/**
      serviceId: tensquare-user
```

GateWay
```yaml
gateway:
   routes:
      - id: go_producer
        uri: lb://go-producer
        predicates:
          - Path=/api/producer/**
        filters:
          - RewritePath=/api/(?<segment>/?.*), /$\{segment}
      - id: go_consumer
        uri: lb://go-consumer
        predicates:
          - Path=/api/consumer/**
        filters:
          - RewritePath=/api/(?<segment>/?.*), /$\{segment}
```

### Gateway核心概念
1. 路由：路由信息由一个自定义Id，一个目的URL，一组断言和过滤器组成
2. 断言: 给开发者用于定义和匹配http request的任何信息，如请求头和参数
3. 过滤器：分为两种，Gateway Filter and Global Filter。这里的过滤器会对请求和响应进行修改

通过loadbalancer Global Filter 解析到真实的URL上：
lb://go-consumer -> localhost:8001

上述sample用了RewritePath Gateway Filter将请求的路径进行了重写：
localhost:8000/api/producer/health -> localhost:8001/producer/health

> 具体和其他请refer official wiki

https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.1.3.RELEASE/single/spring-cloud-gateway.html#_rewritepath_gatewayfilter_factory

https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.1.3.RELEASE/single/spring-cloud-gateway.html#_loadbalancerclient_filter


## Sleuth+Zipkin 调用链监控 

> https://cloud.spring.io/spring-cloud-static/spring-cloud-sleuth/2.1.3.RELEASE/single/spring-cloud-sleuth.html

docker run -d -p 9411:9411 openzipkin/zipkin

一个trace由很多span组成一个树状结构，每调用一个新的服务都会产生一个新span,
所有由这个请求产生的span组成了这个trace,每个span里面由annotation进行标注信息
c:client s: server s: sent r: received（cs,sr,ss,cr）


## Seata 分布式事务解决方案

> https://seata.io/zh-cn/docs/user/quickstart.html

> https://github.com/seata/seata-samples

> https://github.com/seata/seata-samples/tree/master/springcloud-jpa-seata

Seata AT模式步骤如下：
> 2PC演变

```
1. 每个微服务下创建undo_log表
2. 安装事务协调者Seata(TC)
    2.1 https://github.com/seata/seata/releases (0.7.1)
    2.2 解压进入conf目录，修改registry.conf,将此注册到nacos上,修改成功后可在nacos上看到serverAddr的服务名
3. 整合到每个微服务上
    3.1 引入spring-cloud-starter-alibaba-seata
    3.2 使用seata DataSourceProxy代理数据源
    3.3 导入Seata种的file.conf and registry.conf在resource目录下
        3.3.1 修改 file.conf的service.vgroup_mapping.${你自己的微服务名称即application.name}-fescar-service-group = "default"
4. 使用
    4.1 大事务入口标注@GlobalTransactional
    4.2 小事务入口标注@Transactional
```




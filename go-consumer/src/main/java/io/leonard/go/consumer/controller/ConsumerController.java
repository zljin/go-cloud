package io.leonard.go.consumer.controller;

import cn.hutool.core.bean.BeanUtil;
import io.leonard.go.common.pojo.CommonReturnType;
import io.leonard.go.common.pojo.DevMachine;
import io.leonard.go.consumer.feign.ProducerFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private ProducerFeignService producerFeignService;

    @GetMapping("/health")
    public CommonReturnType health() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" go-consumer is health.");
        sb.append(" IP: " + InetAddress.getLocalHost().getHostAddress());
        return CommonReturnType.create(sb.toString());
    }

    @GetMapping("/sellMachine")
    public CommonReturnType sellMachine() throws Exception {
        String todayDatePrefix = new SimpleDateFormat("yyyyMMdd").format(new Date());
        CommonReturnType returnType = producerFeignService.createMachine(todayDatePrefix);
        Map data = (Map) returnType.getData();
        DevMachine machine = BeanUtil.fillBeanWithMap(data, new DevMachine(), false);
        return CommonReturnType.create(machine.getMachineId() + "销售成功");
    }

}

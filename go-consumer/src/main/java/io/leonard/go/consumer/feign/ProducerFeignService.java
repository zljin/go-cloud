package io.leonard.go.consumer.feign;

import io.leonard.go.common.pojo.CommonReturnType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("go-producer")
public interface ProducerFeignService {

    @RequestMapping("producer/createMachine/{prefix}")
    CommonReturnType createMachine(@PathVariable("prefix") String prefix) throws Exception;

}
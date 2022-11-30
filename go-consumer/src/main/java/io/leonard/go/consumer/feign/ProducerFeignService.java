package io.leonard.go.consumer.feign;

import io.leonard.go.common.pojo.CommonReturnType;
import io.leonard.go.consumer.feign.fallback.ProducerFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "go-producer",fallback = ProducerFallBackService.class)
public interface ProducerFeignService {

    @RequestMapping("producer/createMachine/{prefix}")
    CommonReturnType createMachine(@PathVariable("prefix") String prefix) throws Exception;

    @RequestMapping("producer/updateMachine/{machineId}")
    CommonReturnType updateMachine(@PathVariable("machineId") String machineId) throws Exception;

}

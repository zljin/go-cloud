package io.leonard.go.consumer.feign.fallback;

import io.leonard.go.common.pojo.CommonReturnType;
import io.leonard.go.consumer.feign.ProducerFeignService;
import org.springframework.stereotype.Service;

@Service
public class ProducerFallBackService implements ProducerFeignService {
    @Override
    public CommonReturnType createMachine(String prefix) throws Exception {
        return CommonReturnType.create("error create machine");
    }

    @Override
    public CommonReturnType updateMachine(String machineId) throws Exception {
        return CommonReturnType.create("error update machine");
    }
}

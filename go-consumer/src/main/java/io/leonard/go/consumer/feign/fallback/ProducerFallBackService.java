package io.leonard.go.consumer.feign.fallback;

import io.leonard.go.common.pojo.CommonReturnType;
import io.leonard.go.consumer.feign.ProducerFeignService;
import org.springframework.stereotype.Service;

@Service
public class ProducerFallBackService implements ProducerFeignService {
    @Override
    public CommonReturnType createMachine(String prefix) throws Exception {
        System.out.println("createMachine quick fail");
        return null;
    }

    @Override
    public CommonReturnType updateMachine(String machineId) throws Exception {
        throw new RuntimeException("updateMachine quick fail");
    }
}

package io.leonard.go.producer.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import io.leonard.go.common.pojo.CommonReturnType;
import io.leonard.go.common.pojo.DevMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.Random;

@RestController
@RequestMapping("producer")
public class ProducerController {

    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;


    @GetMapping("/health")
    public CommonReturnType health() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" go-producer is health.");
        sb.append(" IP: " + InetAddress.getLocalHost().getHostAddress());
        sb.append(" Zone: " + nacosDiscoveryProperties.getMetadata().get("zone"));
        return CommonReturnType.create(sb.toString());
    }

    @GetMapping("/createMachine/{prefix}")
    public CommonReturnType createMachine(@PathVariable("prefix") String prefix) throws Exception {
        DevMachine devMachine = new DevMachine();
        devMachine.setMachineId(prefix + "" + new Random().nextInt(10000));
        devMachine.setMachineName("大挖"+prefix+new Random().nextInt(100));
        devMachine.setMachineType("挖掘机");
        devMachine.setDescription("挖掘机666");
        return CommonReturnType.create(devMachine);
    }


}

package io.leonard.go.producer.controller;

import io.leonard.go.common.pojo.CommonReturnType;
import io.leonard.go.producer.service.DevMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RefreshScope
@RestController
@RequestMapping("producer")
public class ProducerController {


    @Value("${producer.env:null}")
    private String env;

    @Autowired
    private DevMachineService devMachineService;


    @GetMapping("/health")
    public CommonReturnType health() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" go-producer is health.");
        sb.append(" IP: " + InetAddress.getLocalHost().getHostAddress());
        sb.append(" env: " + env);
        return CommonReturnType.create(sb.toString());
    }

    @GetMapping("/createMachine/{prefix}")
    public CommonReturnType createMachine(@PathVariable("prefix") String prefix) throws Exception {
        return devMachineService.createMachine(prefix);
    }

    @GetMapping("/updateMachine/{machineId}")
    public CommonReturnType updateMachine(@PathVariable("machineId") String machineId) throws Exception {
        return devMachineService.updateMachine(machineId);
    }



}

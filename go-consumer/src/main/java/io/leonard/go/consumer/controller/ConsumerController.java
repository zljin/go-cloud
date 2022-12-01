package io.leonard.go.consumer.controller;

import io.leonard.go.common.pojo.CommonReturnType;
import io.leonard.go.consumer.service.SaleRecordService;
import io.leonard.go.consumer.service.TestSentienlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private SaleRecordService saleRecordService;

    @Autowired
    private TestSentienlService testSentienlService;

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
        return saleRecordService.sellMachine(todayDatePrefix);
    }

    @GetMapping("/sayHi")
    public CommonReturnType sayHi() throws Exception {
        return testSentienlService.sayHi();
    }

}

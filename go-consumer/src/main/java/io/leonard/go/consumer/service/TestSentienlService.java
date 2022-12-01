package io.leonard.go.consumer.service;

import io.leonard.go.common.pojo.CommonReturnType;
import org.springframework.stereotype.Service;

@Service
public class TestSentienlService {


    public CommonReturnType sayHi(){
        System.out.println("sayHi 正在执行！！！");
        return CommonReturnType.create("sayHi");
    }


}

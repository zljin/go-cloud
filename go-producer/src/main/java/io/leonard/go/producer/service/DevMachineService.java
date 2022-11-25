package io.leonard.go.producer.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.leonard.go.common.pojo.CommonReturnType;
import io.leonard.go.producer.entity.DevMachineEntity;


public interface DevMachineService extends IService<DevMachineEntity> {

    CommonReturnType createMachine(String prefix);

    CommonReturnType updateMachine(String machineId);
}


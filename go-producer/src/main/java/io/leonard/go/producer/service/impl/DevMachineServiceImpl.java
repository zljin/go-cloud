package io.leonard.go.producer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.leonard.go.common.pojo.CommonReturnType;
import io.leonard.go.common.pojo.DevMachine;
import io.leonard.go.producer.dao.DevMachineDao;
import io.leonard.go.producer.entity.DevMachineEntity;
import io.leonard.go.producer.service.DevMachineService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;


@Service("devMachineService")
public class DevMachineServiceImpl extends ServiceImpl<DevMachineDao, DevMachineEntity> implements DevMachineService {

    @Resource
    private DevMachineDao devMachineDao;

    @Transactional
    @Override
    public CommonReturnType createMachine(String prefix) {
        DevMachine devMachine = new DevMachine();
        devMachine.setMachineId(prefix + "" + new Random().nextInt(10000));
        devMachine.setMachineName("大挖"+prefix+new Random().nextInt(100));
        devMachine.setMachineType("挖掘机");
        devMachine.setDescription("挖掘机666");

        DevMachineEntity devMachineEntity = new DevMachineEntity();
        BeanUtils.copyProperties(devMachine,devMachineEntity);
        devMachineEntity.setSaleStatus(0);

        devMachineDao.insert(devMachineEntity);
        return CommonReturnType.create(devMachine);
    }

    @Transactional
    @Override
    public CommonReturnType updateMachine(String machineId) {
        devMachineDao.updateDevMachineStatus(machineId);
        return CommonReturnType.create("更新成功");
    }
}
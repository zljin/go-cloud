package io.leonard.go.consumer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.leonard.go.common.pojo.CommonReturnType;
import io.leonard.go.common.pojo.DevMachine;
import io.leonard.go.consumer.dao.SaleRecordDao;
import io.leonard.go.consumer.entity.SaleRecordEntity;
import io.leonard.go.consumer.feign.ProducerFeignService;
import io.leonard.go.consumer.service.SaleRecordService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


@Service("saleRecordService")
public class SaleRecordServiceImpl extends ServiceImpl<SaleRecordDao, SaleRecordEntity> implements SaleRecordService {


    @Autowired
    private ProducerFeignService producerFeignService;

    @Resource
    private SaleRecordDao saleRecordDao;

    @GlobalTransactional
    @Override
    public CommonReturnType sellMachine(String preFix) throws Exception {
        CommonReturnType returnType = producerFeignService.createMachine(preFix);
        if(null == returnType){
            throw new RuntimeException("createMachine quick fail");
        }
        Map data = (Map) returnType.getData();
        DevMachine machine = BeanUtil.fillBeanWithMap(data, new DevMachine(), false);
        SaleRecordEntity saleRecordEntity = new SaleRecordEntity();
        BeanUtils.copyProperties(machine,saleRecordEntity);
        saleRecordDao.insert(saleRecordEntity);

        producerFeignService.updateMachine(machine.getMachineId());

        //int i = 10/0;//测试分布式事务
        return CommonReturnType.create(machine.getMachineId() + "销售成功");
    }
}
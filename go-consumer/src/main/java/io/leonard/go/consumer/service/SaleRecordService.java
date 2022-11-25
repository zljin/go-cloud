package io.leonard.go.consumer.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.leonard.go.common.pojo.CommonReturnType;
import io.leonard.go.consumer.entity.SaleRecordEntity;


public interface SaleRecordService extends IService<SaleRecordEntity> {

    CommonReturnType sellMachine(String preFix) throws Exception;

}


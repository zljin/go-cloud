package io.leonard.go.consumer.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.leonard.go.consumer.entity.SaleRecordEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SaleRecordDao extends BaseMapper<SaleRecordEntity> {
	
}

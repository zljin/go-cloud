package io.leonard.go.producer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.leonard.go.producer.entity.DevMachineEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DevMachineDao extends BaseMapper<DevMachineEntity> {

    int updateDevMachineStatus(@Param("machineId") String machineId);
	
}

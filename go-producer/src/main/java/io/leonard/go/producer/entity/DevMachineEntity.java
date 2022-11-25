package io.leonard.go.producer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
@TableName("dev_machine")
public class DevMachineEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String machineId;
	/**
	 * 
	 */
	private String machineType;
	/**
	 * 
	 */
	private String machineName;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private Integer saleStatus;

}

package io.leonard.go.consumer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("sale_record")
public class SaleRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
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

}

package com.bootdo.oa.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 打卡时间表
 * 
 * @author
 * @email
 * @date
 */
public class OvertimeDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//加班
	public static final String OVERTIME_STATUS = "0";
	//外勤
	public static final String OUT_WORK_STATUS = "1";
	//正常
	public static final String NORMAL_STATUS = "2";
	
	//
	private String id;
	//
	private String name;
	//
	private String deptName;
	//
	private String startTime;
	//
	private String endTime;
	//
	private BigDecimal overtime;
	//打卡日期
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date date;
	//（2：正常 1:外勤，0:加班）
	private String status;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取：
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置：
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 设置：
	 */
	public void setOvertime(BigDecimal overtime) {
		this.overtime = overtime;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getOvertime() {
		return overtime;
	}
	/**
	 * 设置：打卡日期
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * 获取：打卡日期
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * 设置：（1:外勤，0:正常）
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：（1:外勤，0:正常）
	 */
	public String getStatus() {
		return status;
	}

}

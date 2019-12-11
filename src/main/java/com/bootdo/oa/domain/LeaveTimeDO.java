package com.bootdo.oa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 请假小时记录表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-10 10:35:12
 */
public class LeaveTimeDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//姓名
	private String name;
	//部门
	private String deptName;
	//请假类型
	private String leaveType;
	//请假日期
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date leaveDate;
	//开始时间
	private String start;
	//结束时间
	private String end;
	//时长
	private BigDecimal duration;
	//请假事由
	private String reason;


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
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：部门
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取：部门
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置：请假类型
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	/**
	 * 获取：请假类型
	 */
	public String getLeaveType() {
		return leaveType;
	}
	/**
	 * 设置：请假日期
	 */
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	/**
	 * 获取：请假日期
	 */
	public Date getLeaveDate() {
		return leaveDate;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * 获取：开始时间
	 */
	public String getStart() {
		return start;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEnd(String end) {
		this.end = end;
	}
	/**
	 * 获取：结束时间
	 */
	public String getEnd() {
		return end;
	}
	/**
	 * 设置：时长
	 */
	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}
	/**
	 * 获取：时长
	 */
	public BigDecimal getDuration() {
		return duration;
	}
	/**
	 * 设置：请假事由
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：请假事由
	 */
	public String getReason() {
		return reason;
	}

}

package com.bootdo.oa.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 请假小时记录表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-05 10:30:30
 */
public class LeaveTimeDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String name;
	//
	private String deptName;
	//丧假 小时
	private BigDecimal funeralLeave;
	//事假
	private BigDecimal casualLeave;
	//
	private BigDecimal maritalLeave;
	//年假
	private BigDecimal annualLeave;
	//病假
	private BigDecimal sickLeave;
	//调休
	private BigDecimal restCan;
	//陪产假
	private BigDecimal paternityLeave;
	//年月
	private String yearMonth;

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
	 * 设置：丧假 小时
	 */
	public void setFuneralLeave(BigDecimal funeralLeave) {
		this.funeralLeave = funeralLeave;
	}
	/**
	 * 获取：丧假 小时
	 */
	public BigDecimal getFuneralLeave() {
		return funeralLeave;
	}
	/**
	 * 设置：事假
	 */
	public void setCasualLeave(BigDecimal casualLeave) {
		this.casualLeave = casualLeave;
	}
	/**
	 * 获取：事假
	 */
	public BigDecimal getCasualLeave() {
		return casualLeave;
	}
	/**
	 * 设置：
	 */
	public void setMaritalLeave(BigDecimal maritalLeave) {
		this.maritalLeave = maritalLeave;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getMaritalLeave() {
		return maritalLeave;
	}
	/**
	 * 设置：年假
	 */
	public void setAnnualLeave(BigDecimal annualLeave) {
		this.annualLeave = annualLeave;
	}
	/**
	 * 获取：年假
	 */
	public BigDecimal getAnnualLeave() {
		return annualLeave;
	}
	/**
	 * 设置：病假
	 */
	public void setSickLeave(BigDecimal sickLeave) {
		this.sickLeave = sickLeave;
	}
	/**
	 * 获取：病假
	 */
	public BigDecimal getSickLeave() {
		return sickLeave;
	}
	/**
	 * 设置：调休
	 */
	public void setRestCan(BigDecimal restCan) {
		this.restCan = restCan;
	}
	/**
	 * 获取：调休
	 */
	public BigDecimal getRestCan() {
		return restCan;
	}
	/**
	 * 设置：陪产假
	 */
	public void setPaternityLeave(BigDecimal paternityLeave) {
		this.paternityLeave = paternityLeave;
	}
	/**
	 * 获取：陪产假
	 */
	public BigDecimal getPaternityLeave() {
		return paternityLeave;
	}
	/**
	 * 设置：年月
	 */
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	/**
	 * 获取：年月
	 */
	public String getYearMonth() {
		return yearMonth;
	}

}

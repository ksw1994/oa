package com.bootdo.oa.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 简历工作经历表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:56
 */
public class GzjlDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//基础信息编号
	private String jcxxId;
	//公司名称
	private String companyName;
	//开始日期
	private String sdate;
	//结束日期
	private String edate;
	//职务描述
	private String desc;
	//工作职责
	private String duty;

	/**
	 * 设置：编号
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：基础信息编号
	 */
	public void setJcxxId(String jcxxId) {
		this.jcxxId = jcxxId;
	}
	/**
	 * 获取：基础信息编号
	 */
	public String getJcxxId() {
		return jcxxId;
	}
	/**
	 * 设置：公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：开始日期
	 */
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	/**
	 * 获取：开始日期
	 */
	public String getSdate() {
		return sdate;
	}
	/**
	 * 设置：结束日期
	 */
	public void setEdate(String edate) {
		this.edate = edate;
	}
	/**
	 * 获取：结束日期
	 */
	public String getEdate() {
		return edate;
	}
	/**
	 * 设置：职务描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：职务描述
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：工作职责
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 * 获取：工作职责
	 */
	public String getDuty() {
		return duty;
	}
}

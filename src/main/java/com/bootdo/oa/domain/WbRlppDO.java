package com.bootdo.oa.domain;

import java.io.Serializable;




/**
 * 项目人力匹配表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2020-01-07 09:20:31
 */
public class WbRlppDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//项目Id
	private Integer projectId;
	//合同人月
	private String compact;
	//截止年月 格式为yyyymm
	private String endDate;
	//实际月份 格式为yyyymm
	private String date;
	//投入人员总数
	private Integer count;
	//投入人员ID集合 1,2,3,4..
	private String userId;
	//实际月打卡天数
	private Integer monthSum;
	//项目名称
	private String itemName;

	/**
	 * 设置：编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：项目Id
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：项目Id
	 */
	public Integer getProjectId() {
		return projectId;
	}
	/**
	 * 设置：合同人月
	 */
	public void setCompact(String compact) {
		this.compact = compact;
	}
	/**
	 * 获取：合同人月
	 */
	public String getCompact() {
		return compact;
	}
	/**
	 * 设置：截止年月 格式为yyyymm
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：截止年月 格式为yyyymm
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * 设置：实际月份 格式为yyyymm
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 获取：实际月份 格式为yyyymm
	 */
	public String getDate() {
		return date;
	}
	/**
	 * 设置：投入人员总数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：投入人员总数
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：投入人员ID集合 1,2,3,4..
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：投入人员ID集合 1,2,3,4..
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：实际月打卡天数
	 */
	public void setMonthSum(Integer monthSum) {
		this.monthSum = monthSum;
	}
	/**
	 * 获取：实际月打卡天数
	 */
	public Integer getMonthSum() {
		return monthSum;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}

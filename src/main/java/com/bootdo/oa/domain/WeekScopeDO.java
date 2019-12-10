package com.bootdo.oa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 周末、节假日范围表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-03 16:38:38
 */
public class WeekScopeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String yearMonth;
	//周末范围
	private String scope;
	//前台传来的周末日期
	private List<Date> dateList;

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
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	/**
	 * 获取：
	 */
	public String getYearMonth() {
		return yearMonth;
	}
	/**
	 * 设置：周末范围
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/**
	 * 获取：周末范围
	 */
	public String getScope() {
		return scope;
	}

	public List<Date> getDateList() {
		return dateList;
	}

	public void setDateList(List<Date> dateList) {
		this.dateList = dateList;
	}
}

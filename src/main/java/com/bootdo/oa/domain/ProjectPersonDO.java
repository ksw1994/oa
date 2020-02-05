package com.bootdo.oa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * 项目人员信息表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-11 10:02:11
 */
public class ProjectPersonDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	//管理基础信息表
	private Integer id;
	//基础信息表Id
	private String userId;
	//项目Id
	private Integer projectId;
	//实际入场时间
	private String einlass;
	//铁律考试通过实践
	private String examTime;
	//退场时间
	private String exitTime;
	//备注
	private String remarks;
	//
	//新增
	//统计项目人员总数
	private Integer countUserId;
	//项目名称
	private String itemName;
	//进场时间sdate
	private String sdate;
	//退场时间edate
	private String edate;
	//基础信息表Ids
	private List<String> userIds;
	//基础信息人员名称
	private String userName;
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "ProjectPersonDO [id=" + id + ", userId=" + userId + ", projectId=" + projectId + ", einlass=" + einlass
				+ ", examTime=" + examTime + ", exitTime=" + exitTime + ", remarks=" + remarks + ", countUserId="
				+ countUserId + ", itemName=" + itemName + ", sdate=" + sdate + ", edate=" + edate + ", userIds="
				+ userIds + ", userName=" + userName + "]";
	}
	public List<String> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	
	public Integer getCountUserId() {
		return countUserId;
	}
	public void setCountUserId(Integer countUserId) {
		this.countUserId = countUserId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	/**
	 * 设置：管理基础信息表
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：管理基础信息表
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：基础信息表Id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：基础信息表Id
	 */
	public String getUserId() {
		return userId;
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
	 * 设置：实际入场时间
	 */
	public void setEinlass(String einlass) {
		this.einlass = einlass;
	}
	/**
	 * 获取：实际入场时间
	 */
	public String getEinlass() {
		return einlass;
	}
	/**
	 * 设置：铁律考试通过实践
	 */
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	/**
	 * 获取：铁律考试通过实践
	 */
	public String getExamTime() {
		return examTime;
	}
	/**
	 * 设置：退场时间
	 */
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
	/**
	 * 获取：退场时间
	 */
	public String getExitTime() {
		return exitTime;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
}

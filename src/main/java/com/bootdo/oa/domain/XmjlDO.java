package com.bootdo.oa.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 简历项目经历表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:56
 */
public class XmjlDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//基础信息编号
	private String jcxxId;
	//项目序号
	private String itemSeq;
	//项目名称
	private String itemName;
	//是否建行项目
	private String isJhItem;
	//使用建行新一代框架
	private String frame;
	//是否中农工交银行项目
	private String isZngjItem;
	//是否中农工交以外其银行
	private String isNozngjItem;
	//项目业务类型
	private String itemType;
	//开始日期
	private String sdate;
	//结束日期
	private String edate;
	//项目角色
	private String itemRole;
	//证明人
	private String witness;
	//证明人电话
	private String witnessPhone;
	//项目简述
	private String desc;
	//项目职责
	private String duty;
	//使用工具/技能
	private String skill;
	//姓名
	private String name;

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
	 * 设置：项目序号
	 */
	public void setItemSeq(String itemSeq) {
		this.itemSeq = itemSeq;
	}
	/**
	 * 获取：项目序号
	 */
	public String getItemSeq() {
		return itemSeq;
	}
	/**
	 * 设置：项目名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * 获取：项目名称
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * 设置：是否建行项目
	 */
	public void setIsJhItem(String isJhItem) {
		this.isJhItem = isJhItem;
	}
	/**
	 * 获取：是否建行项目
	 */
	public String getIsJhItem() {
		return isJhItem;
	}
	/**
	 * 设置：使用建行新一代框架
	 */
	public void setFrame(String frame) {
		this.frame = frame;
	}
	/**
	 * 获取：使用建行新一代框架
	 */
	public String getFrame() {
		return frame;
	}
	/**
	 * 设置：是否中农工交银行项目
	 */
	public void setIsZngjItem(String isZngjItem) {
		this.isZngjItem = isZngjItem;
	}
	/**
	 * 获取：是否中农工交银行项目
	 */
	public String getIsZngjItem() {
		return isZngjItem;
	}
	/**
	 * 设置：是否中农工交以外其银行
	 */
	public void setIsNozngjItem(String isNozngjItem) {
		this.isNozngjItem = isNozngjItem;
	}
	/**
	 * 获取：是否中农工交以外其银行
	 */
	public String getIsNozngjItem() {
		return isNozngjItem;
	}
	/**
	 * 设置：项目业务类型
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	/**
	 * 获取：项目业务类型
	 */
	public String getItemType() {
		return itemType;
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
	 * 设置：项目角色
	 */
	public void setItemRole(String itemRole) {
		this.itemRole = itemRole;
	}
	/**
	 * 获取：项目角色
	 */
	public String getItemRole() {
		return itemRole;
	}
	/**
	 * 设置：证明人
	 */
	public void setWitness(String witness) {
		this.witness = witness;
	}
	/**
	 * 获取：证明人
	 */
	public String getWitness() {
		return witness;
	}
	/**
	 * 设置：证明人电话
	 */
	public void setWitnessPhone(String witnessPhone) {
		this.witnessPhone = witnessPhone;
	}
	/**
	 * 获取：证明人电话
	 */
	public String getWitnessPhone() {
		return witnessPhone;
	}
	/**
	 * 设置：项目简述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：项目简述
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：项目职责
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 * 获取：项目职责
	 */
	public String getDuty() {
		return duty;
	}
	/**
	 * 设置：使用工具/技能
	 */
	public void setSkill(String skill) {
		this.skill = skill;
	}
	/**
	 * 获取：使用工具/技能
	 */
	public String getSkill() {
		return skill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

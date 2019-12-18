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
	//项目序号 必填
	private String itemSeq;
	//项目名称 必填
	private String itemName;
	//是否建行项目  必填
	private String isJhItem;
	//使用建行新一代框架 非必填，填写P1-P12，多项之间用英文逗号分隔，没有则不填写；
	private String frame;
	//是否中农工交银行项目 必填
	private String isZngjItem;
	//是否中农工交以外其银行 必填
	private String isNozngjItem;
	//项目业务类型 必填
	private String itemType;
	//开始日期 填写前设置单元格格式为文本(必填)格式为yyyy/mm/dd；开始时间小于结束时间
	private String sdate;
	//结束日期 填写前设置单元格格式为文本(必填)格式为yyyy/mm/dd或至今
	private String edate;
	//项目角色 (必填)最多允许30汉字，不能为空
	private String itemRole;
	//证明人 (必填)，不能为空；
	private String witness;
	//证明人电话 必填数字，无则填写为“1”；
	private String witnessPhone;
	//项目简述 (必填)最多允许300汉字，不能为空；
	private String desc;
	//项目职责 (必填)最多允许300汉字，不能为空；
	private String duty;
	//使用工具/技能 必填，不超过300汉字，无则填写无，不能为空；
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

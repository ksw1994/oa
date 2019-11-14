package com.bootdo.oa.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 简历基础信息表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-11 10:29:06
 */
public class JcxxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String name;
	//
	private String sex;
	//
	private String cardType;
	//
	private String cardId;
	//
	private String address;
	//
	private String phone;
	//
	private String email;
	//
	private String companyName;
	//
	private String site;
	//
	private String desc;
	//
	private String pactSdate;
	//
	private String pactEdate;
	//
	private String birthday;
	//0：作废，1:新增，2：更新，3：导出
	private String status;
	//
	private String updateTime;
	//是否上传了附件 0：否 1：是
	private String isFj;

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
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	/**
	 * 获取：
	 */
	public String getCardType() {
		return cardType;
	}
	/**
	 * 设置：
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	/**
	 * 获取：
	 */
	public String getCardId() {
		return cardId;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * 获取：
	 */
	public String getSite() {
		return site;
	}
	/**
	 * 设置：
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：
	 */
	public void setPactSdate(String pactSdate) {
		this.pactSdate = pactSdate;
	}
	/**
	 * 获取：
	 */
	public String getPactSdate() {
		return pactSdate;
	}
	/**
	 * 设置：
	 */
	public void setPactEdate(String pactEdate) {
		this.pactEdate = pactEdate;
	}
	/**
	 * 获取：
	 */
	public String getPactEdate() {
		return pactEdate;
	}
	/**
	 * 设置：
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	public String getIsFj() {
		return isFj;
	}

	public void setIsFj(String isFj) {
		this.isFj = isFj;
	}
}

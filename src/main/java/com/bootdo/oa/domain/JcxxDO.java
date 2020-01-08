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
public class JcxxDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//姓名 必填
	private String name;
	//必填；（男、女）
	private String sex;
	//必填；
	private String cardType;
	//必填；
	private String cardId;
	//必填，不超过33个汉字；
	private String address;
	//必填数字，如无则填写为“1”；
	private String phone;
	//必填，必须是邮箱格式，如无则统一填写公司邮箱；
	private String email;
	//必填；
	private String companyName;
	//必填
	private String site;
	//必填；
	private String desc;
	//填写前设置单元格格式为文本(必填)格式为yyyy/mm/dd；开始时间小于到期时间；
	private String pactSdate;
	//填写前设置单元格格式为文本(必填)格式为yyyy/mm/dd，无限期的填写“无限期”；
	private String pactEdate;
	//
	private String birthday;
	//0：作废，1:新增，2：更新，3：导出
	private String status;
	//是否上传了附件 0：否 1：是
	private String isFj;
	//最新项目id
	private String itemId;
	//最新项目名称
	private String itemName;
	//入场时间
	private String entranceTime;
	//退场时间
	private String exitTime;
	//预计退场时间
	private Date predictExitTime;
	//0:入场 1：退场
	private String isEntrance;

	//预计退场时间(开始)
	private Date start;
	//预计退场时间（结束）
	private Date end;
	//毕业年限
	private Integer graduateYear;

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

	public String getIsFj() {
		return isFj;
	}

	public void setIsFj(String isFj) {
		this.isFj = isFj;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getEntranceTime() {
		return entranceTime;
	}

	public void setEntranceTime(String entranceTime) {
		this.entranceTime = entranceTime;
	}

	public String getExitTime() {
		return exitTime;
	}

	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}

	public Date getPredictExitTime() {
		return predictExitTime;
	}

	public void setPredictExitTime(Date predictExitTime) {
		this.predictExitTime = predictExitTime;
	}

	public String getIsEntrance() {
		return isEntrance;
	}

	public void setIsEntrance(String isEntrance) {
		this.isEntrance = isEntrance;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Integer getGraduateYear() {
		return graduateYear;
	}

	public void setGraduateYear(Integer graduateYear) {
		this.graduateYear = graduateYear;
	}
}

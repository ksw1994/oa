package com.bootdo.oa.domain;

import java.io.Serializable;




/**
 * 外包人员信息表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2020-01-07 09:20:31
 */
public class RyxxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//所属项目组ID
	private Integer teamId;
	//姓名
	private String name;
	//性别 0：男，1：女，-1：未知
	private String sex;
	//证件类型 
	private String cardType;
	//证件号码  
	private String cardId;
	//银行卡号
	private String bankId;
	//工资发放地
	private String wagesAddress;
	//现住址
	private String address;
	//电话 必填数字，如无则填写为“1”
	private String phone;
	//必须是邮箱格式，如无则统一填写公司邮箱
	private String email;
	//公司名称
	private String companyName;
	//人员所在地名 
	private String site;
	//劳动合同开始日期  格式为yyyy/mm/dd
	private String pactSdate;
	//劳动合同结束日期 格式为yyyy/mm/dd，无限期的填写“无限期”
	private String pactEdate;
	//入职日期 格式为yyyy/mm/dd
	private String inSdate;
	//离职日期 格式为yyyy/mm/dd
	private String outEdate;
	//出生日期 格式为yyyy/mm/dd,导入时根据身份证号截取
	private String birthday;
	//状态 0：作废，1:入职，2：离职
	private String status;
	//最后更新时间 格式为yyyy/mm/dd hh:mm:ss
	private String updateTime;

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
	 * 设置：所属项目组ID
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	/**
	 * 获取：所属项目组ID
	 */
	public Integer getTeamId() {
		return teamId;
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
	 * 设置：性别 0：男，1：女，-1：未知
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别 0：男，1：女，-1：未知
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：证件类型 
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	/**
	 * 获取：证件类型 
	 */
	public String getCardType() {
		return cardType;
	}
	/**
	 * 设置：证件号码  
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	/**
	 * 获取：证件号码  
	 */
	public String getCardId() {
		return cardId;
	}
	/**
	 * 设置：银行卡号
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	/**
	 * 获取：银行卡号
	 */
	public String getBankId() {
		return bankId;
	}
	/**
	 * 设置：工资发放地
	 */
	public void setWagesAddress(String wagesAddress) {
		this.wagesAddress = wagesAddress;
	}
	/**
	 * 获取：工资发放地
	 */
	public String getWagesAddress() {
		return wagesAddress;
	}
	/**
	 * 设置：现住址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：现住址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：电话 必填数字，如无则填写为“1”
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话 必填数字，如无则填写为“1”
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：必须是邮箱格式，如无则统一填写公司邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：必须是邮箱格式，如无则统一填写公司邮箱
	 */
	public String getEmail() {
		return email;
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
	 * 设置：人员所在地名 
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * 获取：人员所在地名 
	 */
	public String getSite() {
		return site;
	}
	/**
	 * 设置：劳动合同开始日期  格式为yyyy/mm/dd
	 */
	public void setPactSdate(String pactSdate) {
		this.pactSdate = pactSdate;
	}
	/**
	 * 获取：劳动合同开始日期  格式为yyyy/mm/dd
	 */
	public String getPactSdate() {
		return pactSdate;
	}
	/**
	 * 设置：劳动合同结束日期 格式为yyyy/mm/dd，无限期的填写“无限期”
	 */
	public void setPactEdate(String pactEdate) {
		this.pactEdate = pactEdate;
	}
	/**
	 * 获取：劳动合同结束日期 格式为yyyy/mm/dd，无限期的填写“无限期”
	 */
	public String getPactEdate() {
		return pactEdate;
	}
	/**
	 * 设置：入职日期 格式为yyyy/mm/dd
	 */
	public void setInSdate(String inSdate) {
		this.inSdate = inSdate;
	}
	/**
	 * 获取：入职日期 格式为yyyy/mm/dd
	 */
	public String getInSdate() {
		return inSdate;
	}
	/**
	 * 设置：离职日期 格式为yyyy/mm/dd
	 */
	public void setOutEdate(String outEdate) {
		this.outEdate = outEdate;
	}
	/**
	 * 获取：离职日期 格式为yyyy/mm/dd
	 */
	public String getOutEdate() {
		return outEdate;
	}
	/**
	 * 设置：出生日期 格式为yyyy/mm/dd,导入时根据身份证号截取
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：出生日期 格式为yyyy/mm/dd,导入时根据身份证号截取
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * 设置：状态 0：作废，1:入职，2：离职
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0：作废，1:入职，2：离职
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：最后更新时间 格式为yyyy/mm/dd hh:mm:ss
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：最后更新时间 格式为yyyy/mm/dd hh:mm:ss
	 */
	public String getUpdateTime() {
		return updateTime;
	}
}

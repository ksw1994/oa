package com.bootdo.oa.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 项目信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-15 15:50:56
 */
public class ProjectDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//项目名称
	private String itemName;
	//是否建行项目
	private String isJhItem;
	//使用建行新一代框架
	private String frame;
	//是否中农工交银行项目
	private String isZngjItem;
	//是否中农工交以外其他银行
	private String isNozngjItem;
	//项目业务类别
	private String itemType;
	//开始日期
	private String sdate;
	//结束日期
	private String edate;
	//证明人
	private String witness;
	//证明人电话
	private String telephone;
	//项目简述
	private String desc;
	//公司名称
	private String companyName;
	//进场日期
	private String inSdate;
	//退场日期
	private String outSdate;
	//需求总工作量
	private String workNum;
	//初级需求工作量
	private String cWorkNum;
	//中级需求工作量
	private String zWorkNum;
	//高级需求工作量
	private String gWorkNum;
	//维护要求
	private String demand;
	//事项编号
	private String proceedId;
	//合同编号
	private String pactId;
	//协管员
	private String helpName;
	//合同人数
	private String pactNum;
	//在场人数
	private String spotNum;
	//按合同入场人数
	private String inNum;
	//变更人员
	private String changeStaff;
	//不符合资质人员
	private String failStaff;
	//项目状态
	private String status;

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
	 * 设置：是否中农工交以外其他银行
	 */
	public void setIsNozngjItem(String isNozngjItem) {
		this.isNozngjItem = isNozngjItem;
	}
	/**
	 * 获取：是否中农工交以外其他银行
	 */
	public String getIsNozngjItem() {
		return isNozngjItem;
	}
	/**
	 * 设置：项目业务类别
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	/**
	 * 获取：项目业务类别
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
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：证明人电话
	 */
	public String getTelephone() {
		return telephone;
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
	 * 设置：进场日期
	 */
	public void setInSdate(String inSdate) {
		this.inSdate = inSdate;
	}
	/**
	 * 获取：进场日期
	 */
	public String getInSdate() {
		return inSdate;
	}
	/**
	 * 设置：退场日期
	 */
	public void setOutSdate(String outSdate) {
		this.outSdate = outSdate;
	}
	/**
	 * 获取：退场日期
	 */
	public String getOutSdate() {
		return outSdate;
	}
	/**
	 * 设置：需求总工作量
	 */
	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}
	/**
	 * 获取：需求总工作量
	 */
	public String getWorkNum() {
		return workNum;
	}
	/**
	 * 设置：初级需求工作量
	 */
	public void setCWorkNum(String cWorkNum) {
		this.cWorkNum = cWorkNum;
	}
	/**
	 * 获取：初级需求工作量
	 */
	public String getCWorkNum() {
		return cWorkNum;
	}
	/**
	 * 设置：中级需求工作量
	 */
	public void setZWorkNum(String zWorkNum) {
		this.zWorkNum = zWorkNum;
	}
	/**
	 * 获取：中级需求工作量
	 */
	public String getZWorkNum() {
		return zWorkNum;
	}
	/**
	 * 设置：高级需求工作量
	 */
	public void setGWorkNum(String gWorkNum) {
		this.gWorkNum = gWorkNum;
	}
	/**
	 * 获取：高级需求工作量
	 */
	public String getGWorkNum() {
		return gWorkNum;
	}
	/**
	 * 设置：维护要求
	 */
	public void setDemand(String demand) {
		this.demand = demand;
	}
	/**
	 * 获取：维护要求
	 */
	public String getDemand() {
		return demand;
	}
	/**
	 * 设置：事项编号
	 */
	public void setProceedId(String proceedId) {
		this.proceedId = proceedId;
	}
	/**
	 * 获取：事项编号
	 */
	public String getProceedId() {
		return proceedId;
	}
	/**
	 * 设置：合同编号
	 */
	public void setPactId(String pactId) {
		this.pactId = pactId;
	}
	/**
	 * 获取：合同编号
	 */
	public String getPactId() {
		return pactId;
	}
	/**
	 * 设置：协管员
	 */
	public void setHelpName(String helpName) {
		this.helpName = helpName;
	}
	/**
	 * 获取：协管员
	 */
	public String getHelpName() {
		return helpName;
	}
	/**
	 * 设置：合同人数
	 */
	public void setPactNum(String pactNum) {
		this.pactNum = pactNum;
	}
	/**
	 * 获取：合同人数
	 */
	public String getPactNum() {
		return pactNum;
	}
	/**
	 * 设置：在场人数
	 */
	public void setSpotNum(String spotNum) {
		this.spotNum = spotNum;
	}
	/**
	 * 获取：在场人数
	 */
	public String getSpotNum() {
		return spotNum;
	}
	/**
	 * 设置：按合同入场人数
	 */
	public void setInNum(String inNum) {
		this.inNum = inNum;
	}
	/**
	 * 获取：按合同入场人数
	 */
	public String getInNum() {
		return inNum;
	}
	/**
	 * 设置：变更人员
	 */
	public void setChangeStaff(String changeStaff) {
		this.changeStaff = changeStaff;
	}
	/**
	 * 获取：变更人员
	 */
	public String getChangeStaff() {
		return changeStaff;
	}
	/**
	 * 设置：不符合资质人员
	 */
	public void setFailStaff(String failStaff) {
		this.failStaff = failStaff;
	}
	/**
	 * 获取：不符合资质人员
	 */
	public String getFailStaff() {
		return failStaff;
	}
	/**
	 * 设置：项目状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：项目状态
	 */
	public String getStatus() {
		return status;
	}
}

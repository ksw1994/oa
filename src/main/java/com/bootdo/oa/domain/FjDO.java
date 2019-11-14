package com.bootdo.oa.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 简历附件表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:55
 */
public class FjDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//基础信息编号
	private String jcxxId;
	//毕业证书
	private String studyImg;
	//学位证书
	private String degreeImg;
	//身份证正面
	private String cardImgF;
	//身份证反面
	private String cardImgR;

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
	 * 设置：毕业证书
	 */
	public void setStudyImg(String studyImg) {
		this.studyImg = studyImg;
	}
	/**
	 * 获取：毕业证书
	 */
	public String getStudyImg() {
		return studyImg;
	}
	/**
	 * 设置：学位证书
	 */
	public void setDegreeImg(String degreeImg) {
		this.degreeImg = degreeImg;
	}
	/**
	 * 获取：学位证书
	 */
	public String getDegreeImg() {
		return degreeImg;
	}
	/**
	 * 设置：身份证正面
	 */
	public void setCardImgF(String cardImgF) {
		this.cardImgF = cardImgF;
	}
	/**
	 * 获取：身份证正面
	 */
	public String getCardImgF() {
		return cardImgF;
	}
	/**
	 * 设置：身份证反面
	 */
	public void setCardImgR(String cardImgR) {
		this.cardImgR = cardImgR;
	}
	/**
	 * 获取：身份证反面
	 */
	public String getCardImgR() {
		return cardImgR;
	}
}

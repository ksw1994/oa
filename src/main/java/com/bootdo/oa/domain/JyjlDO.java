package com.bootdo.oa.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 简历教育经历表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:56
 */
public class JyjlDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//基础信息编号
	private String jcxxId;
	//学历
	private String studyBg;
	//学位
	private String degree;
	//毕业学校名称
	private String schoolName;
	//毕业学校类别
	private String schoolType;
	//专业名称
	private String majorName;
	//专业类别
	private String majorType;
	//毕业证号
	private String diplomaId;
	//学位证号
	private String degreeId;
	//入学日期
	private String sdate;
	//毕业日期
	private String edate;
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
	 * 设置：学历
	 */
	public void setStudyBg(String studyBg) {
		this.studyBg = studyBg;
	}
	/**
	 * 获取：学历
	 */
	public String getStudyBg() {
		return studyBg;
	}
	/**
	 * 设置：学位
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}
	/**
	 * 获取：学位
	 */
	public String getDegree() {
		return degree;
	}
	/**
	 * 设置：毕业学校名称
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	/**
	 * 获取：毕业学校名称
	 */
	public String getSchoolName() {
		return schoolName;
	}
	/**
	 * 设置：毕业学校类别
	 */
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	/**
	 * 获取：毕业学校类别
	 */
	public String getSchoolType() {
		return schoolType;
	}
	/**
	 * 设置：专业名称
	 */
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	/**
	 * 获取：专业名称
	 */
	public String getMajorName() {
		return majorName;
	}
	/**
	 * 设置：毕业证号
	 */
	public void setDiplomaId(String diplomaId) {
		this.diplomaId = diplomaId;
	}
	/**
	 * 获取：毕业证号
	 */
	public String getDiplomaId() {
		return diplomaId;
	}
	/**
	 * 设置：学位证号
	 */
	public void setDegreeId(String degreeId) {
		this.degreeId = degreeId;
	}
	/**
	 * 获取：学位证号
	 */
	public String getDegreeId() {
		return degreeId;
	}
	/**
	 * 设置：入学日期
	 */
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	/**
	 * 获取：入学日期
	 */
	public String getSdate() {
		return sdate;
	}
	/**
	 * 设置：毕业日期
	 */
	public void setEdate(String edate) {
		this.edate = edate;
	}
	/**
	 * 获取：毕业日期
	 */
	public String getEdate() {
		return edate;
	}

	public String getMajorType() {
		return majorType;
	}

	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

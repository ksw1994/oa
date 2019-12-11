package com.bootdo.oa.excel;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;



/**
 * 考勤明细表Excel
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-19 11:08:46
 */
@ExcelTarget("attendance")
public class AttendanceExcel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//项目编号
	@Excel(name="姓名",width=15)
	private String userName;
	
	@Excel(name="身份证号",width=22)
	private String idCard;
	
	private Integer projectId;
	//公司名称
	@Excel(name="供应商公司名称",width=25)
	private String companyName;
	//人员资质
	@Excel(name="公司人员资质",width=15)
	private String itemRole;
	//参与项目周期
	@Excel(name="参与项目周期",width=15)
	private String period;
	//有效天数
	@Excel(name="有效天数",width=15)
	private String effectiveDays;
	//人月
	@Excel(name="人月",width=15,numFormat="0.00")
	private String manMouth;
	//月份
	@Excel(name="月份",width=15)
	private String mouth;
	//在岗天数
	@Excel(name="在岗天数",width=15)
	private String onday;
	//周末加班天数
	@Excel(name="周末加班天数",width=15)
	private String weekDay;
	//当月工作日
	@Excel(name="当月工作日",width=15)
	private String mouthDay;

	
	public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
	/**
	 * 设置：项目编号
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：项目编号
	 */
	public Integer getProjectId() {
		return projectId;
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
	 * 设置：人员资质
	 */
	public void setItemRole(String itemRole) {
		this.itemRole = itemRole;
	}
	/**
	 * 获取：人员资质
	 */
	public String getItemRole() {
		return itemRole;
	}
	/**
	 * 设置：参与项目周期
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	/**
	 * 获取：参与项目周期
	 */
	public String getPeriod() {
		return period;
	}
	/**
	 * 设置：有效天数
	 */
	public void setEffectiveDays(String effectiveDays) {
		this.effectiveDays = effectiveDays;
	}
	/**
	 * 获取：有效天数
	 */
	public String getEffectiveDays() {
		return effectiveDays;
	}
	/**
	 * 设置：人月
	 */
	public void setManMouth(String manMouth) {
		this.manMouth = manMouth;
	}
	/**
	 * 获取：人月
	 */
	public String getManMouth() {
		return manMouth;
	}
	/**
	 * 设置：月份
	 */
	public void setMouth(String mouth) {
		this.mouth = mouth;
	}
	/**
	 * 获取：月份
	 */
	public String getMouth() {
		return mouth;
	}
	/**
	 * 设置：在岗天数
	 */
	public void setOnday(String onday) {
		this.onday = onday;
	}
	/**
	 * 获取：在岗天数
	 */
	public String getOnday() {
		return onday;
	}
	/**
	 * 设置：周末加班天数
	 */
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	/**
	 * 获取：周末加班天数
	 */
	public String getWeekDay() {
		return weekDay;
	}
	/**
	 * 设置：当月工作日
	 */
	public void setMouthDay(String mouthDay) {
		this.mouthDay = mouthDay;
	}
	/**
	 * 获取：当月工作日
	 */
	public String getMouthDay() {
		return mouthDay;
	}
}

package com.bootdo.oa.domain;

import java.io.Serializable;




/**
 * 外包人员所属项目组表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2020-01-07 09:20:31
 */
public class XmzDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//父类ID
	private Integer parentId;
	//所属层级
	private Integer level;
	//是否外包公司 0:否，1-是
	private Integer isThird;
	//项目组 如：广开，深圳金科...
	private String team;

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
	 * 设置：父类ID
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父类ID
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置：所属层级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：所属层级
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：是否外包公司 0:否，1-是
	 */
	public void setIsThird(Integer isThird) {
		this.isThird = isThird;
	}
	/**
	 * 获取：是否外包公司 0:否，1-是
	 */
	public Integer getIsThird() {
		return isThird;
	}
	/**
	 * 设置：项目组 如：广开，深圳金科...
	 */
	public void setTeam(String team) {
		this.team = team;
	}
	/**
	 * 获取：项目组 如：广开，深圳金科...
	 */
	public String getTeam() {
		return team;
	}
}

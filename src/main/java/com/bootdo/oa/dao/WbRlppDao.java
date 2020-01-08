package com.bootdo.oa.dao;

import com.bootdo.oa.domain.WbRlppDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 项目人力匹配表
 * @author ksw
 * @email 18819123386@163.com
 * @date 2020-01-07 09:20:31
 */
@Mapper
public interface WbRlppDao {

	WbRlppDO get(Integer id);
	
	List<WbRlppDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WbRlppDO wbRlpp);
	
	int update(WbRlppDO wbRlpp);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 获取该项目中最大的一个投入人数总数
	 * @param projectId
	 * @return
	 */
	WbRlppDO getMaxCountByProjectId(Integer projectId);
}

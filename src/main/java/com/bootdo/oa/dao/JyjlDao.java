package com.bootdo.oa.dao;

import com.bootdo.oa.domain.JyjlDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 简历教育经历表 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:56
 */
@Mapper
public interface JyjlDao {

	JyjlDO get(String id);
	
	List<JyjlDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JyjlDO jyjl);
	
	int update(JyjlDO jyjl);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	/**
	 * 根据基础信息id删除
	 * @param jcxxId
	 * @return
	 */
    int deleteByJcxxId(String jcxxId);
}

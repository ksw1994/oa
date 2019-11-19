package com.bootdo.oa.dao;

import com.bootdo.oa.domain.GzjlDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 简历工作经历表 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:56
 */
@Mapper
public interface GzjlDao {

	GzjlDO get(String id);
	
	List<GzjlDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GzjlDO gzjl);
	
	int update(GzjlDO gzjl);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	/**
	 * 根据基础信息id删除
	 * @param jcxxId
	 * @return
	 */
	int deleteByJcxxId(String jcxxId);

    List<GzjlDO> getByJcxxId(String jcxxId);
}

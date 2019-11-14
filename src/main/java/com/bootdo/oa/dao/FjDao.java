package com.bootdo.oa.dao;

import com.bootdo.oa.domain.FjDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 简历附件表 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:55
 */
@Mapper
public interface FjDao {

	FjDO get(String id);
	
	List<FjDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FjDO fj);
	
	int update(FjDO fj);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	/**
	 * 根据基础信息id删除
	 * @param jcxxId
	 * @return
	 */
	int deleteByJcxxId(String jcxxId);

	/**
	 * 根据基础信息id获取
	 * @param jcxxId
	 * @return
	 */
	FjDO getByJcxxId(String jcxxId);
}

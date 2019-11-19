package com.bootdo.oa.dao;

import com.bootdo.oa.domain.XmjlDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 简历项目经历表 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:56
 */
@Mapper
public interface XmjlDao {

	XmjlDO get(String id);
	
	List<XmjlDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XmjlDO xmjl);
	
	int update(XmjlDO xmjl);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	/**
	 * 根据基础信息id删除
	 * @param jcxxId
	 * @return
	 */
	int deleteByJcxxId(String jcxxId);

    List<XmjlDO> getByJcxxId(String jcxxId);
}

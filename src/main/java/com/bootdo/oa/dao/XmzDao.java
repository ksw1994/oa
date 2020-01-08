package com.bootdo.oa.dao;

import com.bootdo.oa.domain.XmzDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 外包人员所属项目组表
 * @author ksw
 * @email 18819123386@163.com
 * @date 2020-01-07 09:20:31
 */
@Mapper
public interface XmzDao {

	XmzDO get(Integer id);
	
	List<XmzDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XmzDO xmz);
	
	int update(XmzDO xmz);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<XmzDO> getAll();
}

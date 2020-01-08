package com.bootdo.oa.dao;

import com.bootdo.oa.domain.RyxxDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 外包人员信息表
 * @author ksw
 * @email 18819123386@163.com
 * @date 2020-01-07 09:20:31
 */
@Mapper
public interface RyxxDao {

	RyxxDO get(Integer id);
	
	List<RyxxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RyxxDO ryxx);
	
	int update(RyxxDO ryxx);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 获取所有用户id
	 * @return
	 */
	List<Integer> getUserIdList();
}

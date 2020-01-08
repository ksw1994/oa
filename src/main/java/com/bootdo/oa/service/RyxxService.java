package com.bootdo.oa.service;

import com.bootdo.oa.domain.RyxxDO;

import java.util.List;
import java.util.Map;

/**
 * 外包人员信息表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2020-01-07 09:20:31
 */
public interface RyxxService {
	
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

	/**
	 * 随机获取一定数量的用户信息id
	 * @param count
	 * @return
	 */
	String getRandomUserIds(int count);
}

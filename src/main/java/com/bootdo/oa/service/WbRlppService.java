package com.bootdo.oa.service;

import com.bootdo.oa.domain.WbRlppDO;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 项目人力匹配表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2020-01-07 09:20:31
 */
public interface WbRlppService {
	
	WbRlppDO get(Integer id);
	
	List<WbRlppDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WbRlppDO wbRlpp);
	
	int update(WbRlppDO wbRlpp);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 根据项目id获取投入人员总数
	 * @param projectId
	 * @return
	 */
	WbRlppDO getMaxCount(Integer projectId);

	int saveList(List<WbRlppDO> wbRlppList);

	/**
	 * 获取该项目中第三方公司的人员数
	 * @param id
	 * @return
	 */
	int getThirdCount(Integer id);

	/**
	 * 根据截止年月导出excle
	 * @param endDate
	 * @return
	 */
    File exportExcle(String endDate);
}

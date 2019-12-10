package com.bootdo.oa.service;

import com.bootdo.oa.domain.WeekScopeDO;

import java.util.List;
import java.util.Map;

/**
 * 周末范围表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-03 16:38:38
 */
public interface WeekScopeService {
	
	WeekScopeDO get(String id);
	
	List<WeekScopeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(String dateList);
	
	int update(WeekScopeDO weekScope);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	WeekScopeDO getByYearMonth(String yearMonth);
}

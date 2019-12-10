package com.bootdo.oa.dao;

import java.util.List;
import java.util.Map;

import com.bootdo.oa.domain.WeekScopeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 周末范围表
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-03 16:38:38
 */
@Mapper
public interface WeekScopeDao {

	WeekScopeDO get(String id);
	
	List<WeekScopeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WeekScopeDO weekScope);
	
	int update(WeekScopeDO weekScope);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	WeekScopeDO getByYearMonth(String yearMonth);

	int deleteByYearMonth(String yearMonth);
}

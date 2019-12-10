package com.bootdo.oa.service.impl;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.WeekScopeDao;
import com.bootdo.oa.domain.WeekScopeDO;
import com.bootdo.oa.service.WeekScopeService;



@Service
public class WeekScopeServiceImpl implements WeekScopeService {
	@Autowired
	private WeekScopeDao weekScopeDao;
	
	@Override
	public WeekScopeDO get(String id){
		return weekScopeDao.get(id);
	}
	
	@Override
	public List<WeekScopeDO> list(Map<String, Object> map){
		return weekScopeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return weekScopeDao.count(map);
	}
	
	@Override
	public int save(String dateList){
		String[] dates = dateList.split(",");
		String yearMonth = dates[0].substring(0, 7);
		weekScopeDao.deleteByYearMonth(yearMonth);
		WeekScopeDO weekScope = new WeekScopeDO();
		weekScope.setYearMonth(yearMonth);
		weekScope.setScope(dateList);
		weekScope.setId(UUIDUtils.randomUUID());
		return weekScopeDao.save(weekScope);
	}
	
	@Override
	public int update(WeekScopeDO weekScope){
		return weekScopeDao.update(weekScope);
	}
	
	@Override
	public int remove(String id){
		return weekScopeDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return weekScopeDao.batchRemove(ids);
	}

	@Override
	public WeekScopeDO getByYearMonth(String yearMonth) {
		return weekScopeDao.getByYearMonth(yearMonth);
	}

}

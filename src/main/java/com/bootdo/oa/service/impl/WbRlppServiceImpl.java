package com.bootdo.oa.service.impl;

import com.bootdo.oa.service.RyxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.WbRlppDao;
import com.bootdo.oa.domain.WbRlppDO;
import com.bootdo.oa.service.WbRlppService;



@Service
public class WbRlppServiceImpl implements WbRlppService {
	@Autowired
	private WbRlppDao wbRlppDao;

	@Autowired
	private RyxxService ryxxService;
	
	@Override
	public WbRlppDO get(Integer id){
		return wbRlppDao.get(id);
	}
	
	@Override
	public List<WbRlppDO> list(Map<String, Object> map){
		return wbRlppDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return wbRlppDao.count(map);
	}
	
	@Override
	public int save(WbRlppDO wbRlpp){
		if (ryxxService.getUserIdList().size() < wbRlpp.getCount()){
			return 0;
		}
		WbRlppDO maxCount = getMaxCount(wbRlpp.getProjectId());
		if (maxCount != null){//如果不等于空 有3种情况 1：跟最大的相等   2：比最大的小  3：比最大的大
			if (maxCount.getCount() == wbRlpp.getCount()){
				wbRlpp.setUserId(maxCount.getUserId());
			}else if (maxCount.getCount() > wbRlpp.getCount()){
				wbRlpp.setUserId(getMinUserIds(maxCount.getUserId(),wbRlpp.getCount()));
			}else{
				wbRlpp.setUserId(getMaxUserIds(maxCount.getUserId(),wbRlpp.getCount()));
			}
		}else{
			wbRlpp.setUserId(ryxxService.getRandomUserIds(wbRlpp.getCount()));
		}
		return wbRlppDao.save(wbRlpp);
	}
	
	@Override
	public int update(WbRlppDO wbRlpp){
		return wbRlppDao.update(wbRlpp);
	}
	
	@Override
	public int remove(Integer id){
		return wbRlppDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return wbRlppDao.batchRemove(ids);
	}

	@Override
	public WbRlppDO getMaxCount(Integer projectId) {
		WbRlppDO maxCount = wbRlppDao.getMaxCountByProjectId(projectId);
		return maxCount;
	}

	//从大的数组中获取更小的
	public String getMinUserIds(String userIds,Integer count){
		StringBuffer s = new StringBuffer();
		String[] u = userIds.split(",");
		List<String> list = Arrays.asList(u);
		for (int i = 0; i < count ; i++) {
			s.append(list.get(i));
			s.append(",");
		}
		s.deleteCharAt(s.length()-1);
		return s.toString();
	}

	//从小的数组中获取更大的
	public String getMaxUserIds(String userIds,Integer count){
		StringBuffer s = new StringBuffer();
		s.append(userIds);
		s.append(",");
		String[] u = userIds.split(",");
		List<String> list = Arrays.asList(u);
		int temp = count - list.size();
		int n = 0;
		List<Integer> userIdList = ryxxService.getUserIdList();
		for (Integer userId : userIdList) {
			if (!list.contains(userId)){
				s.append(userId);
				s.append(",");
				n++;
				if (temp == n){
					break;
				}
			}
		}
		s.deleteCharAt(s.length()-1);
		return userIds;
	}

}

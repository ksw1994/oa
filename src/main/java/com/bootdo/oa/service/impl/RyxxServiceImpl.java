package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.bootdo.oa.dao.RyxxDao;
import com.bootdo.oa.domain.RyxxDO;
import com.bootdo.oa.service.RyxxService;



@Service
public class RyxxServiceImpl implements RyxxService {
	@Autowired
	private RyxxDao ryxxDao;
	
	@Override
	public RyxxDO get(Integer id){
		return ryxxDao.get(id);
	}
	
	@Override
	public List<RyxxDO> list(Map<String, Object> map){
		return ryxxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return ryxxDao.count(map);
	}
	
	@Override
	public int save(RyxxDO ryxx){
		return ryxxDao.save(ryxx);
	}
	
	@Override
	public int update(RyxxDO ryxx){
		return ryxxDao.update(ryxx);
	}
	
	@Override
	public int remove(Integer id){
		return ryxxDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return ryxxDao.batchRemove(ids);
	}

	@Override
	public List<Integer> getUserIdList() {
		return ryxxDao.getUserIdList();
	}

	@Override
	public String getRandomUserIds(int count) {
		List<Integer> userIdList = ryxxDao.getUserIdList();
		List<Integer> temp = new ArrayList<>();
		StringBuffer userIds = new StringBuffer();
		for (int i = 0; i < count; i++) {
			Random random = new Random();
			int n = random.nextInt(userIdList.size());
			if (!temp.contains(n)){
				temp.add(userIdList.get(n));
			}
		}
		for (Integer userId : temp) {
			userIds.append(userId);
			userIds.append(",");
		}
		//去掉最后一个","
		userIds.deleteCharAt(userIds.length()-1);
		return userIds.toString();
	}

}

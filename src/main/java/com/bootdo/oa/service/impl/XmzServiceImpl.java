package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.XmzDao;
import com.bootdo.oa.domain.XmzDO;
import com.bootdo.oa.service.XmzService;



@Service
public class XmzServiceImpl implements XmzService {
	@Autowired
	private XmzDao xmzDao;
	
	@Override
	public XmzDO get(Integer id){
		return xmzDao.get(id);
	}
	
	@Override
	public List<XmzDO> list(Map<String, Object> map){
		return xmzDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xmzDao.count(map);
	}
	
	@Override
	public int save(XmzDO xmz){
		return xmzDao.save(xmz);
	}
	
	@Override
	public int update(XmzDO xmz){
		return xmzDao.update(xmz);
	}
	
	@Override
	public int remove(Integer id){
		return xmzDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return xmzDao.batchRemove(ids);
	}

	@Override
	public List<XmzDO> getAll() {
		return xmzDao.getAll();
	}

}

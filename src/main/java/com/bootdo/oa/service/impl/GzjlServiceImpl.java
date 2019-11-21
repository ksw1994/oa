package com.bootdo.oa.service.impl;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.UUIDUtils;
import com.bootdo.oa.domain.JcxxDO;
import com.bootdo.oa.service.JcxxService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.GzjlDao;
import com.bootdo.oa.domain.GzjlDO;
import com.bootdo.oa.service.GzjlService;



@Service
public class GzjlServiceImpl implements GzjlService {
	@Autowired
	private GzjlDao gzjlDao;

	@Autowired
	private JcxxService jcxxService;
	
	@Override
	public GzjlDO get(String id){
		return gzjlDao.get(id);
	}
	
	@Override
	public List<GzjlDO> list(Map<String, Object> map){
		return gzjlDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return gzjlDao.count(map);
	}
	
	@Override
	public int save(GzjlDO gzjl){
		gzjl.setId(UUIDUtils.randomUUID());
		gzjl.setCreateBy(ShiroUtils.getUserId());
		gzjl.setCreateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
		return gzjlDao.save(gzjl);
	}
	
	@Override
	public int update(GzjlDO gzjl){
		gzjl.setUpdateBy(ShiroUtils.getUserId());
		gzjl.setUpdateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
		return gzjlDao.update(gzjl);
	}
	
	@Override
	public int remove(String id){
		return gzjlDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return gzjlDao.batchRemove(ids);
	}

	@Override
	public int deleteByJcxxId(String jcxxId) {
		return gzjlDao.deleteByJcxxId(jcxxId);
	}

	@Override
	public List<GzjlDO> getExcel(List<List<String>> list) {
		List<GzjlDO> gzjlList = new ArrayList<>();
		for (List<String> strings : list) {
			if (StringUtil.isNullOrEmpty(strings.get(1))){
				continue;
			}
			GzjlDO gzjlDO = new GzjlDO();
			String cardId = strings.get(1);
			JcxxDO jcxx = jcxxService.getByCardId(cardId);
			gzjlDO.setJcxxId(jcxx.getId());//基础信息id
			gzjlDO.setCompanyName(strings.get(3));//公司名称
			gzjlDO.setSdate(strings.get(4));//开始时间
			gzjlDO.setEdate(strings.get(5));//结束时间
			gzjlDO.setDesc(strings.get(6));//职务描述
			gzjlDO.setDuty(strings.get(7));//工作职责
			gzjlList.add(gzjlDO);
		}
		return gzjlList;
	}

	@Override
	public List<GzjlDO> getByJcxxId(String jcxxId) {
		return gzjlDao.getByJcxxId(jcxxId);
	}

}

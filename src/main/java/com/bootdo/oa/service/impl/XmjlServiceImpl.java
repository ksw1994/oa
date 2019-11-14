package com.bootdo.oa.service.impl;

import com.bootdo.common.utils.UUIDUtils;
import com.bootdo.oa.domain.JcxxDO;
import com.bootdo.oa.service.JcxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.XmjlDao;
import com.bootdo.oa.domain.XmjlDO;
import com.bootdo.oa.service.XmjlService;



@Service
public class XmjlServiceImpl implements XmjlService {
	@Autowired
	private XmjlDao xmjlDao;

	@Autowired
	private JcxxService jcxxService;
	
	@Override
	public XmjlDO get(String id){
		return xmjlDao.get(id);
	}
	
	@Override
	public List<XmjlDO> list(Map<String, Object> map){
		return xmjlDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xmjlDao.count(map);
	}
	
	@Override
	public int save(XmjlDO xmjl){
		xmjl.setId(UUIDUtils.randomUUID());
		return xmjlDao.save(xmjl);
	}
	
	@Override
	public int update(XmjlDO xmjl){
		return xmjlDao.update(xmjl);
	}
	
	@Override
	public int remove(String id){
		return xmjlDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return xmjlDao.batchRemove(ids);
	}

	@Override
	public int deleteByJcxxId(String jcxxId) {
		return xmjlDao.deleteByJcxxId(jcxxId);
	}

	@Override
	public List<XmjlDO> getExcel(List<List<String>> list) {
		List<XmjlDO> xmjlList = new ArrayList<>();
		for (List<String> strings : list) {
			XmjlDO xmjlDO = new XmjlDO();
			String cardId = strings.get(1);//身份证号
			JcxxDO jcxx = jcxxService.getByCardId(cardId);
			xmjlDO.setJcxxId(jcxx.getId());//基础信息id
			xmjlDO.setItemSeq(strings.get(3));//项目序号
			xmjlDO.setItemName(strings.get(4));//项目名称
			xmjlDO.setIsJhItem(strings.get(5));//是否是建行项目
			xmjlDO.setFrame(strings.get(6));//使用建行新一代框架
			xmjlDO.setIsZngjItem(strings.get(7));//是否中农工交银行项目
			xmjlDO.setIsNozngjItem(strings.get(8));//是否中农工交以外其银行
			xmjlDO.setItemType(strings.get(9));//项目业务类型
			xmjlDO.setSdate(strings.get(10));//开始时间
			xmjlDO.setEdate(strings.get(11));//结束时间
			xmjlDO.setItemRole(strings.get(12));//项目角色
			xmjlDO.setWitness(strings.get(13));//证明人
			xmjlDO.setWitnessPhone(strings.get(14));//证明人电话
			xmjlDO.setDesc(strings.get(15));//项目简述
			xmjlDO.setDuty(strings.get(16));//项目职责
			xmjlDO.setSkill(strings.get(17));//使用工具/技能
			xmjlList.add(xmjlDO);
		}
		return xmjlList;
	}

}

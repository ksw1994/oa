package com.bootdo.oa.service.impl;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.UUIDUtils;
import com.bootdo.oa.domain.JcxxDO;
import com.bootdo.oa.service.JcxxService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.JyjlDao;
import com.bootdo.oa.domain.JyjlDO;
import com.bootdo.oa.service.JyjlService;



@Service
public class JyjlServiceImpl implements JyjlService {
	@Autowired
	private JyjlDao jyjlDao;

	@Autowired
	private JcxxService jcxxService;
	
	@Override
	public JyjlDO get(String id){
		return jyjlDao.get(id);
	}
	
	@Override
	public List<JyjlDO> list(Map<String, Object> map){
		return jyjlDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jyjlDao.count(map);
	}
	
	@Override
	public int save(JyjlDO jyjl){
		jyjl.setId(UUIDUtils.randomUUID());
		jyjl.setCreateBy(ShiroUtils.getUserId());
		jyjl.setCreateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
		jyjlDao.save(jyjl);
		//修改基础表的毕业年限
		JcxxDO jcxxDO = jcxxService.get(jyjl.getJcxxId());
		try {
			jcxxDO.setGraduateYear(DateUtils.getYears(jyjl.getEdate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jcxxService.update(jcxxDO);
		return 1;
	}
	
	@Override
	public int update(JyjlDO jyjl){
		jyjl.setUpdateBy(ShiroUtils.getUserId());
		jyjl.setUpdateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
		jyjlDao.update(jyjl);
		//修改基础表的毕业年限
		JcxxDO jcxxDO = jcxxService.get(jyjl.getJcxxId());
		try {
			jcxxDO.setGraduateYear(DateUtils.getYears(jyjl.getEdate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jcxxService.update(jcxxDO);
		return 1;
	}
	
	@Override
	public int remove(String id){
		return jyjlDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return jyjlDao.batchRemove(ids);
	}

	@Override
	public int deleteByJcxxId(String jcxxId) {
		return jyjlDao.deleteByJcxxId(jcxxId);
	}

	@Override
	public List<JyjlDO> getExcel(List<List<String>> list) {
		List<JyjlDO> jyjlList = new ArrayList<>();
		for (List<String> strings : list) {
			if (StringUtil.isNullOrEmpty(strings.get(1))){
				continue;
			}
			JyjlDO jyjlDO = new JyjlDO();
			String cardId = strings.get(1);//身份证id
			JcxxDO jcxx = jcxxService.getByCardId(cardId);
			jyjlDO.setJcxxId(jcxx.getId());//基础信息id
			jyjlDO.setStudyBg(strings.get(3));//学习
			jyjlDO.setDegree(strings.get(4));//学位
			jyjlDO.setSchoolName(strings.get(5));//毕业学习
			jyjlDO.setSchoolType(strings.get(6));//学习类型
			jyjlDO.setMajorName(strings.get(7));//专业名称
			jyjlDO.setMajorType(strings.get(8));//专业类别
			jyjlDO.setDiplomaId(strings.get(9));//毕业证号
			jyjlDO.setDegreeId(strings.get(10));//学位证号
			jyjlDO.setSdate(strings.get(11));//入学日期
			jyjlDO.setEdate(strings.get(12));//毕业日期
			jyjlList.add(jyjlDO);
		}
		return jyjlList;
	}

	@Override
	public JyjlDO getByJcxxId(String jcxxId) {
		return jyjlDao.getByJcxxId(jcxxId);
	}

}

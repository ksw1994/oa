package com.bootdo.oa.service.impl;

import com.bootdo.common.excel.ExcelUtil;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.IDCardUtils;
import com.bootdo.common.utils.UUIDUtils;
import com.bootdo.oa.dao.JcxxDao;
import com.bootdo.oa.domain.*;
import com.bootdo.oa.service.*;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Service
public class JcxxServiceImpl implements JcxxService {
	@Autowired
	private JcxxDao jcxxDao;

	@Autowired
	private FjService fjService;

	@Autowired
	private JyjlService jyjlService;

	@Autowired
	private GzjlService gzjlService;

	@Autowired
	private XmjlService xmjlService;


	
	@Override
	public JcxxDO get(String id){
		return jcxxDao.get(id);
	}
	
	@Override
	public List<JcxxDO> list(Map<String, Object> map){
		return jcxxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jcxxDao.count(map);
	}
	
	@Override
	public int save(JcxxDO jcxx){
		jcxx.setId(UUIDUtils.randomUUID());
		jcxx.setUpdateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
		if (StringUtil.isNullOrEmpty(jcxx.getBirthday())){
			jcxx.setBirthday(IDCardUtils.getBirth(jcxx.getCardId()));
		}
		jcxx.setStatus("1");//新增
		//新增一个基础信息时新增一个对应附件表
		jcxxDao.save(jcxx);
		FjDO fj = new FjDO();
		fj.setId(UUIDUtils.randomUUID());
		fj.setJcxxId(jcxx.getId());
		return fjService.save(fj) ;
	}
	
	@Override
	public int update(JcxxDO jcxx){
		jcxx.setUpdateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
		jcxx.setStatus("2");//修改
		return jcxxDao.update(jcxx);
	}
	
	@Override
	public int remove(String id){
		//移除基础信息时同时删除教育经历，工作经历，项目经历，附件
		jyjlService.deleteByJcxxId(id);//删除教育经历
		gzjlService.deleteByJcxxId(id);//删除工作经历
		xmjlService.deleteByJcxxId(id);//删除项目经历
		fjService.deleteByJcxxId(id);//删除附件
		return jcxxDao.remove(id);//删除基础信息;
	}
	
	@Override
	public int batchRemove(String[] ids){
		return jcxxDao.batchRemove(ids);
	}

	@Override
	public void importExcelExcelFile(MultipartFile file) {
		List<List<String>> list0 = ExcelUtil.readExcel(file,0);//基础信息
		list0.remove(list0.get(0));
		List<JcxxDO> jcxxList = getExcel(list0);
		for (JcxxDO jcxxDO : jcxxList) {
			JcxxDO temp = jcxxDao.getByCardId(jcxxDO.getCardId());
			if (Objects.nonNull(temp)){//已存在，更新
				jcxxDO.setId(temp.getId());
				jcxxDao.update(jcxxDO);
			}else{//新增
				save(jcxxDO);
			}
		}

		List<List<String>> list1 = ExcelUtil.readExcel(file,1);//教育经历
		list1.remove(list1.get(0));
		List<JyjlDO> jyjlList = jyjlService.getExcel(list1);
		for (JyjlDO jyjlDO : jyjlList) {
			jyjlService.save(jyjlDO);
		}

		List<List<String>> list2 = ExcelUtil.readExcel(file,2);//工作经历
		list2.remove(list2.get(0));
		List<GzjlDO> gzjlList = gzjlService.getExcel(list2);
		for (GzjlDO gzjlDO : gzjlList) {
			gzjlService.save(gzjlDO);
		}

		List<List<String>> list3 = ExcelUtil.readExcel(file,3);//工作经验
		list3.remove(list3.get(0));
		List<XmjlDO> xmjlList = xmjlService.getExcel(list3);
		for (XmjlDO xmjlDO : xmjlList) {
			xmjlService.save(xmjlDO);
		}

	}

	@Override
	public JcxxDO getByCardId(String cardId) {
		return jcxxDao.getByCardId(cardId);
	}

	public List<JcxxDO> getExcel(List<List<String>> list){
		List<JcxxDO> jcxxList = new ArrayList<>();
		for (List<String> l : list) {
			JcxxDO jcxx = new JcxxDO();
			jcxx.setName(l.get(0));
			jcxx.setSex(l.get(1));
			jcxx.setCardType(l.get(2));
			jcxx.setCardId(l.get(3));
			jcxx.setAddress(l.get(4));
			jcxx.setPhone(l.get(5));
			jcxx.setEmail(l.get(6));
			jcxx.setCompanyName(l.get(7));
			jcxx.setSite(l.get(8));
			jcxx.setDesc(l.get(9));
			jcxx.setPactSdate(l.get(10));
			jcxx.setPactEdate(l.get(11));
			jcxx.setBirthday(IDCardUtils.getBirth(jcxx.getCardId()));
			jcxxList.add(jcxx);
		}
		return jcxxList;
	}

}

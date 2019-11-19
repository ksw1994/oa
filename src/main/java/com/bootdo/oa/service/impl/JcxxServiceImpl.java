package com.bootdo.oa.service.impl;

import com.bootdo.common.excel.ExcelUtil;
import com.bootdo.common.utils.*;
import com.bootdo.common.word.WordExportUtil;
import com.bootdo.oa.dao.JcxxDao;
import com.bootdo.oa.domain.*;
import com.bootdo.oa.service.*;
import freemarker.template.utility.DateUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
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

	@Value("${bootdo.templatePath}")
	private String templatePath;

	@Value("${bootdo.exportPath}")
	private String exportPath;
	
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
			if (Objects.nonNull(temp)){//已存在，删除
				remove(temp.getId());//级联删除
			}else{//新增
				save(jcxxDO);
			}
		}

		List<List<String>> list1 = ExcelUtil.readExcel(file,1);//教育经历 只有一个,要不新增，要不更新
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

	@Override
	public File exportJcxx(String jcxxId) {
		String image1 = null;
		String image2 = null;
		String image3 = null;
		String image4 = null;

		JcxxDO jcxx = get(jcxxId);//基础信息
		JyjlDO jyjl = jyjlService.getByJcxxId(jcxxId);//教育经历
		List<GzjlDO> gzjlList = gzjlService.getByJcxxId(jcxxId);//工作经历
		List<XmjlDO> xmjlList = xmjlService.getByJcxxId(jcxxId);//项目经历
		if (jcxx.getIsFj().equals("1")){
			FjDO fj = fjService.getByJcxxId(jcxxId);
			if (!StringUtil.isNullOrEmpty(fj.getStudyImg())){//毕业证
				image1 = fj.getStudyImg();
			}
			if (!StringUtil.isNullOrEmpty(fj.getDegreeImg())){//学位证
				image2 = fj.getDegreeImg();
			}
			if (!StringUtil.isNullOrEmpty(fj.getCardImgF())){//身份证正面
				image3 = fj.getCardImgF();
			}
			if (!StringUtil.isNullOrEmpty(fj.getCardImgR())){//身份证反面
				image4 = fj.getCardImgR();
			}
		}


		String templateFilePath = templatePath+"简历.ftl";//ftl模板路径
		Map<String,Object> dataMap = new HashMap<>();
		/*StringBuffer certificate = new StringBuffer();
		certificate.append("1.精通Java开发语言 \r\n");
		certificate.append("2.精通node.JS、Webpack结合前端开发 \r\n");
		certificate.append("3.熟悉Struts、hibernate、SpringMVC、Spring、Hibernate、myBatis、 iBatis、Junit等MVC框架 \r\n");
		certificate.append("4.熟悉WEB开发相关技术（JSP、HTML、H5、css、javascript、jquery、vue） \r\n");
		certificate.append("5.熟悉系统、网络、存储及相关软件等 \r\n");
*/
		List<Map<String,String>> projectList = new ArrayList<>();
		for (XmjlDO xmjlDO : xmjlList) {
			Map<String,String> m = new HashMap<>();
			StringBuffer projectTime = new StringBuffer();
			projectTime.append(xmjlDO.getSdate());
			projectTime.append("-");
			projectTime.append(xmjlDO.getEdate());
			m.put("projectTime",projectTime.toString());
			m.put("projectRoleName",xmjlDO.getItemRole());
			m.put("projectName",xmjlDO.getItemName());
			m.put("workContent",xmjlDO.getDuty());
			projectList.add(m);
		}

		dataMap.put("project_role",xmjlList.get(xmjlList.size()-1).getItemRole());//取项目经历最晚的
		dataMap.put("name",jcxx.getName());
		dataMap.put("age",IDCardUtils.getAgeByIdCard(jcxx.getCardId()));
		dataMap.put("sex",jcxx.getSex());
		dataMap.put("school",jyjl.getSchoolName());
		dataMap.put("profession",jyjl.getMajorName());
		String gzStart = gzjlList.get(0).getSdate();//开始工作时间
		String bankStart = xmjlList.get(0).getSdate();//银行开始工作时间
		try {
			dataMap.put("seniority",DateUtils.getYears(gzStart)+"年");//工作时间
			dataMap.put("bank_seniority",DateUtils.getYears(bankStart)+"年");//银行工作时间
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//dataMap.put("certificate",certificate.toString().replaceAll("\r\n", "<w:p></w:p>"));
		dataMap.put("certificate",jcxx.getDesc());
		dataMap.put("projectList",projectList);
		dataMap.put("image1", image1  == null ? "" : new Base64Utils().getImageStr(image1));
		dataMap.put("image2", image2  == null ? "" : new Base64Utils().getImageStr(image2));
		dataMap.put("image3", image3  == null ? "" : new Base64Utils().getImageStr(image3));
		dataMap.put("image4", image4  == null ? "" : new Base64Utils().getImageStr(image4));
		String exportFilePath = exportPath+ DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN)+jcxx.getName()+"简历.doc";//导出路径
		int loadType = 1;
		File file = null;
		try {
			file = new WordExportUtil().createDocFile(templateFilePath,dataMap,exportFilePath,loadType);
		} catch (Exception e) {

		}
		return file;

	}

	public List<JcxxDO> getExcel(List<List<String>> list){
		List<JcxxDO> jcxxList = new ArrayList<>();
		for (List<String> l : list) {
			if (StringUtil.isNullOrEmpty(l.get(0))){
				continue;
			}
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
			jcxx.setIsFj("0");
			jcxxList.add(jcxx);
		}
		return jcxxList;
	}

}

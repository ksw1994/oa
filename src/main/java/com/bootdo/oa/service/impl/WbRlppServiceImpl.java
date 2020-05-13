package com.bootdo.oa.service.impl;

import com.bootdo.common.excel.WriteExcle;
import com.bootdo.oa.service.RyxxService;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.bootdo.oa.dao.WbRlppDao;
import com.bootdo.oa.domain.WbRlppDO;
import com.bootdo.oa.service.WbRlppService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class WbRlppServiceImpl implements WbRlppService {
	@Autowired
	private WbRlppDao wbRlppDao;

	@Autowired
	private RyxxService ryxxService;

	@Value("${bootdo.templatePath}")
	private String templatePath;

	@Value("${bootdo.exportPath}")
	private String exportPath;
	
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
		//如果不等于空 有3种情况 1：跟最大的相等   2：比最大的小  3：比最大的大
		if (maxCount != null){
			if (maxCount.getCount().equals(wbRlpp.getCount()) ){
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

	@Transactional
	@Override
	public int saveList(List<WbRlppDO> wbRlppList) {
		for (WbRlppDO wbRlppDO : wbRlppList) {
			int r = save(wbRlppDO);
			if (r == 0){
				return 0;
			}
		}
		return 1;
	}

	@Override
	public int getThirdCount(Integer id) {
		AtomicInteger count = new AtomicInteger(0);
		WbRlppDO wbRlppDO = get(id);
		String userId = wbRlppDO.getUserId();
		List<String> userIdList = Arrays.asList(userId.split(","));
		for (String s : userIdList) {
			if (ryxxService.isThird(Integer.valueOf(s)) == 1){
				count.incrementAndGet();
			}
		}
		return count.intValue();
	}

	@Override
	public File exportExcle(String endDate) {
		List<WbRlppDO> wbRlppDOList = wbRlppDao.getByEndDate(endDate);
		if (wbRlppDOList.isEmpty()){
			return null;
		}
		//模板地址
		StringBuffer tempPath = new StringBuffer();
		tempPath.append(templatePath);
		String newPath = exportPath+"计划&实际执行表.xlsx";
		tempPath.append("计划&实际执行表.xlsx");
		//新建一份excle
		File newFile = WriteExcle.createNewFile(tempPath.toString(), newPath);

		// 新文件写入数据，并下载
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		XSSFCellStyle style0 = null;
		XSSFCellStyle style1 = null;
		try {
			// 创建个workbook
			workbook = new XSSFWorkbook(new FileInputStream(newFile));
			int month = new Date().getMonth();
			workbook.setSheetName(0, month+1+"月实际");
			workbook.setSheetName(1,getMonth(endDate)+"月实际更新");
			// 写数据
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.flush();
			workbook.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile;
	}

	/**
	 * 从大的数组中获取更小的
	 * @param userIds
	 * @param count
	 * @return
	 */
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

	/**
	 * 从小的数组中获取更大的
	 * @param userIds
	 * @param count
	 * @return
	 */
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

	public String getMonth(String date){
		String zero = "0";
		String s = date.substring(4, 6);
		if (zero.equals(s.subSequence(0,1))){
			return s.substring(1,2);
		}
		return s;
	}

}

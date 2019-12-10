package com.bootdo.oa.service;

import com.bootdo.oa.domain.OvertimeDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 打卡时间表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-12-03 10:34:37
 */
public interface OvertimeService {
	
	OvertimeDO get(String id);
	
	List<OvertimeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OvertimeDO overtime);
	
	int update(OvertimeDO overtime);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    void importExcelFile(MultipartFile file);

	/**
	 * 导出加班
	 * @param date
	 * @return
	 */
	File exportOvertime(String date);
}

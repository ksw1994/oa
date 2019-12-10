package com.bootdo.oa.service;

import com.bootdo.oa.domain.LeaveTimeDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 请假小时记录表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-05 10:30:30
 */
public interface LeaveTimeService {
	
	LeaveTimeDO get(String id);
	
	List<LeaveTimeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LeaveTimeDO leaveTime);
	
	int update(LeaveTimeDO leaveTime);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	void importExcelFile(MultipartFile file);
}

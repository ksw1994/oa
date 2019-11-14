package com.bootdo.oa.service;

import com.bootdo.oa.domain.FjDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 简历附件表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:55
 */
public interface FjService {
	
	FjDO get(String id);
	
	List<FjDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FjDO fj);
	
	int update(FjDO fj);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	/**
	 * 根据基础信息id删除
	 * @param jcxxId
	 * @return
	 */
	int deleteByJcxxId(String jcxxId);

	/**
	 * 保存附件
	 * @param files
	 * @param jcxxId
	 */
    void filesUpload(MultipartFile[] files, String jcxxId);
}

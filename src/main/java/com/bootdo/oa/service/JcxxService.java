package com.bootdo.oa.service;

import com.bootdo.oa.domain.JcxxDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 简历基础信息表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-11 10:29:06
 */
public interface JcxxService {
	
	JcxxDO get(String id);
	
	List<JcxxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JcxxDO jcxx);
	
	int update(JcxxDO jcxx);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	/**
	 * 导入excle
	 * @param file
	 */
    void importExcelExcelFile(MultipartFile file);

	/**
	 * 根据身份证号查询
	 * @param cardId
	 * @return
	 */
	JcxxDO getByCardId(String cardId);

	/**
	 * 导出单个文件简历
	 * @param jcxxId
	 * @return
	 */
    File exportJcxx(String jcxxId);
}

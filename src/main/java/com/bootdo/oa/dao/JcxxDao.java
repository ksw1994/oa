package com.bootdo.oa.dao;


import java.util.List;
import java.util.Map;

import com.bootdo.oa.domain.JcxxDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 简历基础信息表 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-11 10:29:06
 */
@Mapper
public interface JcxxDao {

	JcxxDO get(String id);
	
	List<JcxxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JcxxDO jcxx);
	
	int update(JcxxDO jcxx);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	/**
	 * 根据身份证号查询
	 * @param cardId
	 * @return
	 */
	JcxxDO getByCardId(String cardId);
}

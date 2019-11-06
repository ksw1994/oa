package com.bootdo.system.service;

import com.bootdo.system.domain.ProductDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-14 15:49:56
 */
public interface ProductService {
	
	ProductDO get(Integer pid);
	
	List<ProductDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductDO product);
	
	int update(ProductDO product);
	
	int remove(Integer pid);
	
	int batchRemove(Integer[] pids);
}

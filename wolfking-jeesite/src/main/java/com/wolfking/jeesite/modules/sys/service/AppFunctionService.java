/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wolfking.jeesite.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wolfking.jeesite.common.service.CrudService;
import com.wolfking.jeesite.modules.sys.dao.AppFunctionDao;
import com.wolfking.jeesite.modules.sys.entity.AppFunction;

/**
 *app Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AppFunctionService extends CrudService<AppFunctionDao, AppFunction> {
	
	@Override
	@Transactional(readOnly = false)
	public void save(AppFunction dict) {
		super.save(dict);
		//CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(AppFunction dict) {
		super.delete(dict);
		//CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}
}

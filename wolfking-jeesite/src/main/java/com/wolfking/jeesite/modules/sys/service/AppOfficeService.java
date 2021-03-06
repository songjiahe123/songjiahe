/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wolfking.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wolfking.jeesite.common.persistence.Page;
import com.wolfking.jeesite.common.service.CrudService;
import com.wolfking.jeesite.common.utils.CacheUtils;
import com.wolfking.jeesite.modules.sys.dao.AppOfficeDao;
import com.wolfking.jeesite.modules.sys.dao.AppsDao;
import com.wolfking.jeesite.modules.sys.entity.AppOffice;

/**
 * Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AppOfficeService extends CrudService<AppOfficeDao, AppOffice> {
	
	

	@Override
	@Transactional(readOnly = false)
	public void save(AppOffice appOffice) {
		super.save(appOffice);
		//CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(AppOffice apps) {
		super.delete(apps);
		//CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}
	@Transactional(readOnly = false)
	public void deletebyofficeid(String officeId) {
		AppOffice ao=new AppOffice();
		ao.setOfficeId(officeId);
		dao.deletebyofficeid(ao);
	}

}

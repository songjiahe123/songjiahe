/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wolfking.jeesite.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wolfking.jeesite.common.service.CrudService;
import com.wolfking.jeesite.modules.sys.dao.AssetsDao;
import com.wolfking.jeesite.modules.sys.entity.Assets;

/**
 * Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AssetsService extends CrudService<AssetsDao, Assets> {
	
	

	@Override
	@Transactional(readOnly = false)
	public void save(Assets assets) {
		super.save(assets);
		//CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Assets apps) {
		super.delete(apps);
		//CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	public Assets getbyAppidOfficeid(Assets assets) {
		// TODO Auto-generated method stub
		return dao.getbyAppidOfficeid(assets);
	}
	@Transactional(readOnly = false)
	public void update(Assets assets) {
		
		dao.update(assets);
		
	}

}

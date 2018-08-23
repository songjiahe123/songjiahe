/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wolfking.jeesite.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wolfking.jeesite.common.service.TreeService;
import com.wolfking.jeesite.modules.sys.dao.OfficeDao;
import com.wolfking.jeesite.modules.sys.entity.Office;
import com.wolfking.jeesite.modules.sys.entity.User;
import com.wolfking.jeesite.modules.sys.utils.UserUtils;

/**
 * 机构Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {

	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		User u =UserUtils.getUser();
		if(u.getCompany().getId().equals("1")) {
			if(office != null){
				office.setParentIds(office.getParentIds()+"%");
				return dao.findByParentIdsLike(office);
			}
		}else {
			if(office != null){
				//
				if(office.getParentIds().equals("")) {
					office.setParentIds("0,");
				}
				if(office.getParentIds().equals("0,")) {
					office.setParentIds("0,"+u.getCompany().getId()+",");
				
					office.setParentIds(office.getParentIds()+"%");
					List<Office>  offi= dao.findByParentIdsLike(office);
					List<Office> offi2=new ArrayList<Office>();
					offi2.add(u.getCompany());
					offi2.addAll(offi);
					return offi2;
				}else {
					office.setParentIds(office.getParentIds()+"%");
					return dao.findByParentIdsLike(office);
				}
				//return dao.findByParentIdsLike(office);
			}
		}
		/*if(office != null){
			office.setParentIds(office.getParentIds()+"%");
			return dao.findByParentIdsLike(office);
		}*/
		return  new ArrayList<Office>();
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
}

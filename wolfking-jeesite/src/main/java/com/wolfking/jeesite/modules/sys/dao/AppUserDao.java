/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wolfking.jeesite.modules.sys.dao;

import com.wolfking.jeesite.common.persistence.CrudDao;
import com.wolfking.jeesite.modules.sys.entity.AppOffice;
import com.wolfking.jeesite.modules.sys.entity.AppUser;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * app user DAO接口
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@Mapper
public interface AppUserDao extends CrudDao<AppUser> {

    List<String> findTypeList(AppUser apps);

	void deletebyuserid(AppUser ao);

}

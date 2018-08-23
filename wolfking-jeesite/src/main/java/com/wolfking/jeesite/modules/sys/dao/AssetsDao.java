/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wolfking.jeesite.modules.sys.dao;

import com.wolfking.jeesite.common.persistence.CrudDao;
import com.wolfking.jeesite.modules.sys.entity.Assets;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * assetsDAO接口
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@Mapper
public interface AssetsDao extends CrudDao<Assets> {

    List<String> findTypeList(Assets assets);

	Assets getbyAppidOfficeid(Assets assets);

}

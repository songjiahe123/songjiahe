/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wolfking.jeesite.modules.sys.entity;

import javax.xml.bind.annotation.XmlAttribute;

import org.hibernate.validator.constraints.Length;

import com.wolfking.jeesite.common.persistence.DataEntity;

/**
 * App Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class Apps extends DataEntity<Apps> {

	private static final long serialVersionUID = 1L;
	private String appname;	// app名称
	private String type;	// 类型
	private String description;// 描述
	private String officeId;//企业id;

	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public Apps() {
		super();
	}
	
	public Apps(String id){
		super(id);
	}

	@Length(min=1, max=100)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAttribute
	@Length(min=0, max=100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
package com.wolfking.jeesite.modules.sys.entity;

import com.wolfking.jeesite.common.persistence.DataEntity;

public class AppOffice extends DataEntity<AppOffice> {
	private String appId;
	private String officeId;
	private Apps app;
	private Office office;
	private String userid;
	private String roleid;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public Apps getApp() {
		return app;
	}
	public void setApp(Apps app) {
		this.app = app;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	
}

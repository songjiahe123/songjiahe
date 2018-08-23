package com.wolfking.jeesite.modules.sys.entity;

import com.wolfking.jeesite.common.persistence.DataEntity;

public class AppUser extends DataEntity<AppUser> {
	private String userid;
	private String roleid;
	private String appid;
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
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
}

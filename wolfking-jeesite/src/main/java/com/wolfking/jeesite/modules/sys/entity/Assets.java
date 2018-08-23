package com.wolfking.jeesite.modules.sys.entity;

import com.wolfking.jeesite.common.persistence.DataEntity;

public class Assets extends DataEntity<Assets> {
	private static final long serialVersionUID = 1L;
	private String eid;//企业id
	private String appid;//App ID
	private String storage;//存储限制
	private String bandwidth;//带宽限制
	private String naccount;//账户数限制
	private String ndevices;//终端数量限制
	private String workMode;//按功能群划分的套件模式
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}
	public String getNaccount() {
		return naccount;
	}
	public void setNaccount(String naccount) {
		this.naccount = naccount;
	}
	public String getNdevices() {
		return ndevices;
	}
	public void setNdevices(String ndevices) {
		this.ndevices = ndevices;
	}
	public String getWorkMode() {
		return workMode;
	}
	public void setWorkMode(String workMode) {
		this.workMode = workMode;
	}
	
	
}

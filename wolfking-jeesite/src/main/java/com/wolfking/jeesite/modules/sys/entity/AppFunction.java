package com.wolfking.jeesite.modules.sys.entity;

import com.wolfking.jeesite.common.persistence.DataEntity;

public class AppFunction extends DataEntity<AppFunction> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appId;
	private String functionName;
	private Apps app;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public Apps getApp() {
		return app;
	}
	public void setApp(Apps app) {
		this.app = app;
	}
	
}

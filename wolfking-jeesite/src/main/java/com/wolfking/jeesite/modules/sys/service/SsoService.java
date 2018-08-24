package com.wolfking.jeesite.modules.sys.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wolfking.jeesite.common.utils.HttpClientUtil;
import com.wolfking.jeesite.modules.sys.entity.User;
@Service
public class SsoService {
	@Value("${SSO.BASE.URL}")
	public static final  String SSO_BASE_URL="http://127.0.0.1:8081";
	
	public  String insertUser(User user) {
		Map<String,String> umap=new HashMap<String,String>();
		umap.put("username", user.getName());
		umap.put("password", user.getPassword());
		Object pjson = JSONObject.toJSON(umap);//将java对象转换为json对象
		String str = pjson.toString();//将json对象转换为字符串
		System.out.println(str);
		String json = HttpClientUtil.doPostJson(SSO_BASE_URL+"/user/insertUser", str);
		System.out.println("json : " + json);
		return json;
	}
	public  String updateUser(User user) {
		Map<String,String> umap=new HashMap<String,String>();
		umap.put("username", user.getName());
		umap.put("password", user.getPassword());
		Object pjson = JSONObject.toJSON(umap);//将java对象转换为json对象
		String str = pjson.toString();//将json对象转换为字符串
		System.out.println(str);
		String json = HttpClientUtil.doPostJson(SSO_BASE_URL+"/user/updateUser", str);
		System.out.println("json : " + json);
		return json;
	}
}

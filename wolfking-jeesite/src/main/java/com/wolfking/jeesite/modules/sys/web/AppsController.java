/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wolfking.jeesite.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wolfking.jeesite.common.config.Global;
import com.wolfking.jeesite.common.persistence.Page;
import com.wolfking.jeesite.common.utils.StringUtils;
import com.wolfking.jeesite.common.web.BaseController;
import com.wolfking.jeesite.modules.sys.entity.AppFunction;
import com.wolfking.jeesite.modules.sys.entity.AppOffice;
import com.wolfking.jeesite.modules.sys.entity.AppUser;
import com.wolfking.jeesite.modules.sys.entity.Apps;
import com.wolfking.jeesite.modules.sys.entity.Assets;
import com.wolfking.jeesite.modules.sys.entity.Office;
import com.wolfking.jeesite.modules.sys.service.AppFunctionService;
import com.wolfking.jeesite.modules.sys.service.AppOfficeService;
import com.wolfking.jeesite.modules.sys.service.AppUserService;
import com.wolfking.jeesite.modules.sys.service.AppsService;
import com.wolfking.jeesite.modules.sys.service.AssetsService;
import com.wolfking.jeesite.modules.sys.service.OfficeService;

/**
 * appController
 * @author ThinkGem
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/apps")
public class AppsController extends BaseController {

	@Autowired
	private AppsService appsService;
	@Autowired
	private AppOfficeService appOfficeService;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private AssetsService assetsService;
	@Autowired
	private AppFunctionService appFunctionService;
	@ModelAttribute
	public Apps get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return appsService.get(id);
		}else{
			return new Apps();
		}
	}
	
	@RequiresPermissions("sys:apps:view")
	@RequestMapping(value = {"list", ""})
	public String list(Apps apps, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> typeList = appsService.findTypeList();
		model.addAttribute("typeList", typeList);
        Page<Apps> page = appsService.findPage(new Page<Apps>(request, response), apps); 
        model.addAttribute("page", page);
		return "modules/sys/appsList";
	}

	@RequiresPermissions("sys:apps:view")
	@RequestMapping(value = "form")
	public String form(Apps apps, Model model) {
		model.addAttribute("apps", apps);
		return "modules/sys/appsForm";
	}
	
	@RequiresPermissions("sys:apps:view")
	@RequestMapping(value = "assets")
	public String assets(HttpServletRequest request, HttpServletResponse response, Model model) {
		String appid=request.getParameter("appid");
		String officeid=request.getParameter("officeid");
		Assets assets=new Assets();
		assets.setAppid(appid);
		assets.setEid(officeid);
	    assets=assetsService.getbyAppidOfficeid(assets);
	    if(assets==null) {
	    	assets=new Assets();
	    }
	    assets.setAppid(appid);
		assets.setEid(officeid);
		model.addAttribute("assets", assets);
		//查找app下的功能套餐
		AppFunction af=new AppFunction();
		af.setAppId(appid);
		List<AppFunction> aflist=appFunctionService.findList(af);
		model.addAttribute("aflist",aflist);
		return "modules/sys/assetsForm";
	}

	@RequiresPermissions("sys:apps:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(Apps apps, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/apps/?repage&type="+apps.getType();
		}
		if (!beanValidator(model, apps)){
			return form(apps, model);
		}
		appsService.save(apps);
		addMessage(redirectAttributes, "保存App'" + apps.getAppname() + "'成功");
		return "redirect:" + adminPath + "/sys/apps/?repage&type="+apps.getType();
	}
	@RequiresPermissions("sys:apps:edit")
	@RequestMapping(value = "saveassets")//@Valid 
	public String saveassets(Assets assets, Model model, RedirectAttributes redirectAttributes) {
		/*if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/apps/?repage&type="+apps.getType();
		}*/
		/*if (!beanValidator(model, assets)){
			return assets(assets, model);
		}*/
		Assets as=assetsService.getbyAppidOfficeid(assets);
		if(as==null) {
		assetsService.save(assets);
		}else {
			assetsService.update(assets);
		}
		addMessage(redirectAttributes, "保存资产权利成功");
		return "redirect:" + adminPath + "/sys/office/fenpeiapp?id="+assets.getEid();
	}
	@RequiresPermissions("sys:apps:edit")
	@RequestMapping(value = "savefenpei")//@Valid 
	public String savefenpei( HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		/*if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/apps/?repage&type="+apps.getType();
		}*/
		String officeId=request.getParameter("officeid");
		String appids=request.getParameter("appids");
		//删除office下的app
		appOfficeService.deletebyofficeid(officeId);
		if(appids!=null) {
			String[] apparray=appids.split(",");
			for(int i=0;i<apparray.length;i++) {
				String appid=apparray[i];
				AppOffice ao=new AppOffice();
				ao.setAppId(appid);
				ao.setOfficeId(officeId);
				appOfficeService.save(ao);
			}
		}
		addMessage(redirectAttributes, "保存分配App成功");
		Office office=officeService.get(officeId);
		String id = "0".equals(office.getParentId()) ? "" : office.getParentId();
		return "redirect:" + adminPath + "/sys/office/list?id="+id+"&parentIds="+office.getParentIds();
	}
	@RequiresPermissions("sys:apps:edit")
	@RequestMapping(value = "saveapptouser")//@Valid 
	public String saveapptouser( HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		/*if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/apps/?repage&type="+apps.getType();
		}*/
		String userid=request.getParameter("userid");
		String appids=request.getParameter("appids");
		//删除user下的app
		appUserService.deletebyuserid(userid);
		if(appids!=null&&!appids.equals("")) {
			String[] apparray=appids.split(",");
			for(int i=0;i<apparray.length;i++) {
				String appid=apparray[i];
				String[] appidarray=appid.split("##");
				
				AppUser au=new AppUser();
				au.setAppid(appidarray[0]);
				au.setUserid(userid);
				au.setRoleid(appidarray[1]);
				appUserService.save(au);
			}
		}
		addMessage(redirectAttributes, "保存分配App成功");
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	@RequiresPermissions("sys:apps:edit")
	@RequestMapping(value = "delete")
	public String delete(Apps apps, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/apps/?repage";
		}
		appsService.delete(apps);
		addMessage(redirectAttributes, "删除App成功");
		return "redirect:" + adminPath + "/sys/apps/?repage&type="+apps.getType();
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String type, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Apps apps = new Apps();
		apps.setType(type);
		List<Apps> list = appsService.findList(apps);
		for (int i=0; i<list.size(); i++){
			Apps e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("name", StringUtils.replace(e.getAppname(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}
	
	@ResponseBody
	@RequestMapping(value = "listData")
	public List<Apps> listData(@RequestParam(required=false) String type) {
		Apps apps = new Apps();
		apps.setType(type);
		return appsService.findList(apps);
	}

}

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wolfking.jeesite.modules.sys.web;

import java.util.List;
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

import com.wolfking.jeesite.common.config.Global;
import com.wolfking.jeesite.common.persistence.Page;
import com.wolfking.jeesite.common.utils.StringUtils;
import com.wolfking.jeesite.common.web.BaseController;
import com.wolfking.jeesite.modules.sys.entity.AppFunction;
import com.wolfking.jeesite.modules.sys.entity.Apps;
import com.wolfking.jeesite.modules.sys.service.AppFunctionService;
import com.wolfking.jeesite.modules.sys.service.AppsService;

/**
 * appController
 * @author ThinkGem
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/appfunction")
public class AppFunctionController extends BaseController {

	@Autowired
	private AppsService appsService;
	@Autowired
	private AppFunctionService appFunctionService;
	@ModelAttribute
	public AppFunction get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return appFunctionService.get(id);
		}else{
			return new AppFunction();
		}
	}
	
	@RequiresPermissions("sys:appfunction:view")
	@RequestMapping(value = {"list", ""})
	public String list(AppFunction appfunction, HttpServletRequest request, HttpServletResponse response, Model model) {
		String appId=request.getParameter("appId");
		appfunction.setAppId(appId);
        Page<AppFunction> page = appFunctionService.findPage(new Page<AppFunction>(request, response), appfunction); 
        model.addAttribute("page", page);
        model.addAttribute("appId",appfunction.getAppId());
        Apps app=appsService.get(appfunction.getAppId());
        model.addAttribute("app",app);
		return "modules/sys/appFunctionList";
	}

	@RequiresPermissions("sys:appfunction:view")
	@RequestMapping(value = "form")
	public String form(AppFunction appfunction, HttpServletRequest request, HttpServletResponse response, Model model) {
		String appId=request.getParameter("appid");
		if(appId!=null&&!appId.equals("")) {
		   appfunction.setAppId(appId);
		}
		model.addAttribute("appFunction", appfunction);
		return "modules/sys/appFunctionForm";
	}
	
	@RequiresPermissions("sys:appfunction:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(AppFunction appfunction, Model model, RedirectAttributes redirectAttributes) {
		/*if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/apps/?repage&type="+apps.getType();
		}*/
		/*if (!beanValidator(model, appfunction)){
			return form(appfunction, model);
		}*/
		appFunctionService.save(appfunction);
		addMessage(redirectAttributes, "保存'" + appfunction.getFunctionName() + "'成功");
		return "redirect:" + adminPath + "/sys/appfunction/list/?repage&appId="+appfunction.getAppId();
	}
	
	@RequiresPermissions("sys:appfunction:edit")
	@RequestMapping(value = "delete")
	public String delete(AppFunction appfunction, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/appfunction/?repage";
		}
		appFunctionService.delete(appfunction);
		addMessage(redirectAttributes, "删除功能套餐成功");
		return "redirect:" + adminPath + "/sys/appfunction/?repage&appId="+appfunction.getAppId();
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "listData")
	public List<AppFunction> listData(@RequestParam(required=false) String appId) {
		AppFunction appfunction = new AppFunction();
		appfunction.setAppId(appId);
		return appFunctionService.findList(appfunction);
	}

}

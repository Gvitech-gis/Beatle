package org.gt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.gt.bean.sys_modelmenu;
import org.gt.bean.sys_models;
import org.gt.common.messageHelper;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;
import org.gt.service.IjstreeService;
import org.gt.service.Isys_modelsService;
import org.gt.vo.jstree;
import org.gt.vo.sys_modelform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.glass.ui.View;

@Controller
@RequestMapping("sys_models")
public class sys_modelsController {

	@Autowired
	private Isys_modelsService sys_modelsService;
	@Autowired
	private IjstreeService jstreeService;
	@RequestMapping("queryTreeMenu.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object queryMember() {
		
		List<sys_models> adminmenus = null;
		adminmenus = sys_modelsService.queryAll();
		return adminmenus;
		
	}

	@RequestMapping("view.do")//用於查詢所有的下拉菜單分級顯示
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/model/sys_models_view");
		return mv;
	}


	@RequestMapping("add_view.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public ModelAndView add_view() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/model/sys_models_add");
		//View view=mv.getClass();
		return mv;
	}

	@RequestMapping("edit_view.do")//用於查詢所有的下拉菜單分級顯示
	//@ResponseBody//用於AJAX
	public ModelAndView edit_view(String id) {
		sys_models sys_models=sys_modelsService.serchByID(id);
		ModelAndView mv = new ModelAndView("sys/model/sys_models_edit");
		String names="nihao";
		mv.addObject(sys_models);
		mv.addObject("ret","nihao  ninasinfi");
		//mv.setViewName("sys_models_edit");
		//View view=mv.getClass();
		return mv;
	}
	//分页查询
	@RequestMapping("serch.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object serch(pagerHelperRQ pager) {
		pagerHelperRS pagerrs=sys_modelsService.serchbypager(pager);
		return pagerrs;
	}

	
	//分页查询
	@RequestMapping("serchCount.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public int  serchCount(String name) {
		int count = sys_modelsService.countAll(name);
		return count;
	}
	//分页查询
	@RequestMapping("add.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object add(sys_models menus) {
		messageHelper message= new messageHelper();
		try {
			 int result= sys_modelsService.addone(menus);
			 if(result>0){
				message.setMst(0);
				message.setMsg("新增菜单功能成功！");
			 }else{
				message.setMst(1);
				message.setMsg("新增菜单功能失败！");
			 }
		} catch (Exception e) {
			message.setMst(1);
			message.setMsg("新增菜单功能失败！"+e.getMessage());
		}
		return message;
	}
	
	//修改 
		@RequestMapping("edit.do")//用於查詢所有的下拉菜單分級顯示
		@ResponseBody//用於AJAX
		public Object edit(sys_models menus) {
			messageHelper message= new messageHelper();
			try {
				 int result= sys_modelsService.modify(menus);
				 if(result>0){
					message.setMst(0);
					message.setMsg("修改菜单功能成功！");
				 }else{
					message.setMst(1);
					message.setMsg("修改菜单功能失败！");
				 }
			} catch (Exception e) {
				message.setMst(1);
				message.setMsg("修改菜单功能失败！"+e.getMessage());
			}
			return message;
		}
	//分页查询

	@RequestMapping("delmenu.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object delmenu(String id) {
		messageHelper message= new messageHelper();
		try {
			 int result= sys_modelsService.delById(id);
			 if(result>0){
				message.setMst(0);
				message.setMsg("删除菜单功能成功！");
			 }else{
				message.setMst(1);
				message.setMsg("删除菜单功能失败！");
			 }
			
		} catch (Exception e) {
			message.setMst(1);
			message.setMsg("删除菜单功能失败！"+e.getMessage());
		}
		return message;
	}
	@RequestMapping("models_Tree.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object serchAll() {
 		List<jstree> jstrees = jstreeService.model_tree();
		return jstrees;
	}
	
	@RequestMapping(value= "updateMenus.do",method={RequestMethod.POST})//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object updateMenus(@RequestBody sys_modelform modelform) {
		messageHelper message= new messageHelper();
		try {
			boolean result= sys_modelsService.addmodelmenus(modelform.getModelid(), modelform.getSys_modelmenuss());
			 if(result){
				message.setMst(0);
				message.setMsg("调整菜单功能成功！");
			 }else{
				message.setMst(1);
				message.setMsg("调整菜单功能失败！");
			 }
		} catch (Exception e) {
			message.setMst(1);
			message.setMsg("调整菜单功能失败！"+e.getMessage());
		}
		return message;
	}
	@RequestMapping(value= "modelmenuswithmenus.do",method={RequestMethod.POST})//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object modelmenuswithmenus() {
		List<sys_modelmenu> sys_modelmenus = sys_modelsService.modelmenuswithmenus();
		return sys_modelmenus;
	}
	
	/*
	 * 通过roleid查询所有模组权限
	 * */
	@RequestMapping(value= "selectMenus.do",method={RequestMethod.GET})//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object selectMenus(String modelid) {
		messageHelper message= new messageHelper();
		try {
			List<sys_modelmenu> lstsys_rolemodel= sys_modelsService.getMenus(modelid);
				message.setMst(0);
				message.setMsg("");
				message.setData(lstsys_rolemodel);
		} catch (Exception e) {
			message.setMst(1);
			message.setMsg("查询失败！"+e.getMessage());
		}
		return message;
	}
}

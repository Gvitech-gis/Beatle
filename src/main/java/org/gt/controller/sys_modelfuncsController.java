package org.gt.controller;

import java.util.List;
import java.util.Map;

import org.gt.bean.sys_modelfuncs;
import org.gt.common.messageHelper;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;
import org.gt.service.IjstreeService;
import org.gt.service.Isys_modelfuncsService;
import org.gt.vo.jstree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.glass.ui.View;
@Controller
@RequestMapping("sys_modelfuncs")
public class sys_modelfuncsController {

	@Autowired
	private Isys_modelfuncsService sys_modelfuncsService;
	@Autowired
	private IjstreeService jstreeService;
	@RequestMapping("queryTreeMenu.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object queryMember() {
		
		List<sys_modelfuncs> adminmenus = null;
		adminmenus = sys_modelfuncsService.queryAll();
		return adminmenus;
		
	}
	
	@RequestMapping("modelFuncTree.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object modelFuncTree() {
		List<jstree> jstrees = null;
		jstrees = jstreeService.modelfunc_tree();
		return jstrees;
	}
	

	@RequestMapping("view.do")//用於查詢所有的下拉菜單分級顯示
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/function/sys_modelfuncs_view");
		return mv;
	}


	@RequestMapping("add_view.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public ModelAndView add_view() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/function/sys_modelfuncs_add");
		//View view=mv.getClass();
		return mv;
	}

	@RequestMapping("edit_view.do")//用於查詢所有的下拉菜單分級顯示
	//@ResponseBody//用於AJAX
	public ModelAndView edit_view(String id) {
		sys_modelfuncs sys_modelfuncs=sys_modelfuncsService.serchByID(id);
		ModelAndView mv = new ModelAndView("sys/function/sys_modelfuncs_edit");
		String names="nihao";
		mv.addObject(sys_modelfuncs);
		mv.addObject("ret","nihao  ninasinfi");
		//mv.setViewName("sys_modelfuncs_edit");
		//View view=mv.getClass();
		return mv;
	}
	//分页查询
	@RequestMapping("serch.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object serch(pagerHelperRQ pager) {
		pagerHelperRS pagerrs=sys_modelfuncsService.serchbypager(pager);
		return pagerrs;
	}

	
	//分页查询
	@RequestMapping("serchCount.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public int  serchCount(String name) {
		int count = sys_modelfuncsService.countAll(name);
		return count;
	}
	//分页查询
	@RequestMapping("add.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object add(sys_modelfuncs menus) {
		messageHelper message= new messageHelper();
		try {
			 int result= sys_modelfuncsService.addone(menus);
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
		public Object edit(sys_modelfuncs menus) {
			messageHelper message= new messageHelper();
			try {
				 int result= sys_modelfuncsService.modify(menus);
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
			 int result= sys_modelfuncsService.delById(id);
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
	
}

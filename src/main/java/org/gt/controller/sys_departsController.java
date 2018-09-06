package org.gt.controller;

import java.util.List;
import java.util.Map;

import org.gt.bean.sys_departs;
import org.gt.common.messageHelper;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;
import org.gt.service.Isys_departsService;
import org.gt.vo.sys_departrole_vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.glass.ui.View;

@Controller
@RequestMapping("sys_departs")
public class sys_departsController {

	@Autowired
	private Isys_departsService sys_departsService;
	@RequestMapping("queryTreeMenu.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object queryMember() {
		
		List<sys_departs> adminmenus = null;
		adminmenus = sys_departsService.queryAll();
		return adminmenus;
		
	}
	
	@RequestMapping("view.do")//用於查詢所有的下拉菜單分級顯示
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/depart/sys_departs_view");
		return mv;
	}

	
	@RequestMapping("add_view.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public ModelAndView add_view() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/depart/sys_departs_add");
		//View view=mv.getClass();
		return mv;
	}
	

	@RequestMapping("edit_view.do")//用於查詢所有的下拉菜單分級顯示
	//@ResponseBody//用於AJAX
	public ModelAndView edit_view(String id) {
		sys_departs sys_departs=sys_departsService.serchByID(id);
		ModelAndView mv = new ModelAndView("sys/depart/sys_departs_edit");
		String names="nihao";
		mv.addObject(sys_departs);
		mv.addObject("ret","nihao  ninasinfi");
		//mv.setViewName("sys_departs_edit");
		//View view=mv.getClass();
		return mv;
	}
	
	//分页查询
	@RequestMapping("serch.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object serch(pagerHelperRQ pager) {
		pagerHelperRS pagerrs=sys_departsService.serchbypager(pager);
		return pagerrs;
	}

	
	//分页查询
	@RequestMapping("serchCount.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public int  serchCount(String name) {
		int count = sys_departsService.countAll(name);
		return count;
	}
	
	//分页查询
	@RequestMapping("add.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object add(sys_departs menus) {
		messageHelper message= new messageHelper();
		try {
			 int result= sys_departsService.addone(menus);
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
		public Object edit(sys_departs menus) {
			messageHelper message= new messageHelper();
			try {
				 int result= sys_departsService.modify(menus);
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
			 int result= sys_departsService.delById(id);
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
	
	@RequestMapping(value= "updateroles.do",method={RequestMethod.POST})//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object updateroles(@RequestBody sys_departrole_vo modelform) {
		messageHelper message= new messageHelper();
		try {
			boolean result= sys_departsService.adddepartroles(modelform.getDepartid(), modelform.getLstsys_departrole());
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
	
}

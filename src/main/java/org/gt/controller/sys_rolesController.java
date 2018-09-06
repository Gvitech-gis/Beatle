package org.gt.controller;

import java.util.List;
import java.util.Map;

import org.gt.bean.sys_rolemodel;
import org.gt.bean.sys_rolemodelfunc;
import org.gt.bean.sys_rolemodelmenu;
import org.gt.bean.sys_roles;
import org.gt.common.messageHelper;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;
import org.gt.service.IjstreeService;
import org.gt.service.Isys_rolesService;
import org.gt.vo.jstree;
import org.gt.vo.sys_rolemodelfuncvo;
import org.gt.vo.sys_rolemodelmenuvo;
import org.gt.vo.sys_rolemodelvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.glass.ui.View;
@Controller
@RequestMapping("sys_roles")
public class sys_rolesController {

	@Autowired
	private Isys_rolesService sys_rolesService;
	@Autowired
	private IjstreeService jstreeService;
	@RequestMapping("queryTreeMenu.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object queryMember() {
		
		List<sys_roles> adminmenus = null;
		adminmenus = sys_rolesService.queryAll();
		return adminmenus;
		
	}

	@RequestMapping("view.do")//用於查詢所有的下拉菜單分級顯示
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/role/sys_roles_view");
		return mv;
	}


	@RequestMapping("add_view.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public ModelAndView add_view() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/role/sys_roles_add");
		//View view=mv.getClass();
		return mv;
	}

	@RequestMapping("edit_view.do")//用於查詢所有的下拉菜單分級顯示
	//@ResponseBody//用於AJAX
	public ModelAndView edit_view(String id) {
		sys_roles sys_roles=sys_rolesService.serchByID(id);
		ModelAndView mv = new ModelAndView("sys/role/sys_roles_edit");
		String names="nihao";
		mv.addObject(sys_roles);
		mv.addObject("ret","nihao  ninasinfi");
		//mv.setViewName("sys_roles_edit");
		//View view=mv.getClass();
		return mv;
	}
	//分页查询
	@RequestMapping("serch.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object serch(pagerHelperRQ pager) {
		pagerHelperRS pagerrs=sys_rolesService.serchbypager(pager);
		return pagerrs;
	}

	
	//分页查询
	@RequestMapping("serchCount.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public int  serchCount(String name) {
		int count = sys_rolesService.countAll(name);
		return count;
	}
	//分页查询
	@RequestMapping("add.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object add(sys_roles menus) {
		messageHelper message= new messageHelper();
		try {
			 int result= sys_rolesService.addone(menus);
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
		public Object edit(sys_roles menus) {
			messageHelper message= new messageHelper();
			try {
				 int result= sys_rolesService.modify(menus);
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
			 int result= sys_rolesService.delById(id);
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
	@RequestMapping("roles_Tree.do")//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object roles_Tree() {
 		List<jstree> jstrees = jstreeService.role_tree();
		return jstrees;
	}
	/*
	 * 角色赋模组权限
	 * */
	@RequestMapping(value= "updateRoleModels.do",method={RequestMethod.POST})//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object updateRoleModels(@RequestBody sys_rolemodelvo modelform) {
		messageHelper message= new messageHelper();
		try {
			boolean result= sys_rolesService.updateRoleModels(modelform.getRoleid(), modelform.getSys_rolemodels());
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
	/*
	 * 通过roleid查询所有模组权限
	 * */
	@RequestMapping(value= "selectRoleModels.do",method={RequestMethod.POST})//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object selectRoleModels(String id) {
		messageHelper message= new messageHelper();
		try {
			List<sys_rolemodel> lstsys_rolemodel= sys_rolesService.selectRoleModels(id);
				message.setMst(0);
				message.setMsg("");
				message.setData(lstsys_rolemodel);
		} catch (Exception e) {
			message.setMst(1);
			message.setMsg("查询失败！"+e.getMessage());
		}
		return message;
	}
	/*
	 * 角色赋模组按钮权限
	 * */
	@RequestMapping(value= "updateRoleModelMenus.do",method={RequestMethod.POST})//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object updateRoleModelMenus(@RequestBody sys_rolemodelmenuvo modelform) {
		messageHelper message= new messageHelper();
		try {
			boolean result= sys_rolesService.updateRoleModelMenus(modelform.getRoleid(), modelform.getSys_rolemodelmenus());
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
	/*
	 * 通过roleid查询所有模组菜单权限
	 * */
	@RequestMapping(value= "selectRoleModelMenus.do",method={RequestMethod.POST})//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object selectRoleModelMenus(String id) {
		messageHelper message= new messageHelper();
		try {
			List<sys_rolemodelmenu> lstsys_rolemodelmenu= sys_rolesService.selectRoleModelMenus(id);
				message.setMst(0);
				message.setMsg("");
				message.setData(lstsys_rolemodelmenu);
		} catch (Exception e) {
			message.setMst(1);
			message.setMsg("查询失败！"+e.getMessage());
		}
		return message;
	}
	
	/*
	 * 角色赋模组按钮权限
	 * */
	@RequestMapping(value= "updateRoleModelfuncs.do",method={RequestMethod.POST})//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object updateRoleModelfuncs(@RequestBody sys_rolemodelfuncvo modelform) {
		messageHelper message= new messageHelper();
		try {
			boolean result= sys_rolesService.updateRoleModelfuncs(modelform.getRoleid(), modelform.getSys_rolemodelfuncs());
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
	/*
	 * 通过roleid查询所有模组菜单权限
	 * */
	@RequestMapping(value= "selectRoleModelfuncs.do",method={RequestMethod.POST})//用於查詢所有的下拉菜單分級顯示
	@ResponseBody//用於AJAX
	public Object selectRoleModelfuncs(String id) {
		messageHelper message= new messageHelper();
		try {
			List<sys_rolemodelfunc> lstsys_rolemodelmenu= sys_rolesService.selectRoleModelfuncs(id);
				message.setMst(0);
				message.setMsg("");
				message.setData(lstsys_rolemodelmenu);
		} catch (Exception e) {
			message.setMst(1);
			message.setMsg("查询失败！"+e.getMessage());
		}
		return message;
	}
	
}

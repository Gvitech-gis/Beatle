package org.gt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SysViewController {

    /****************部门*****************/
    /**
     *部门列表页面
     */
    @RequestMapping("/sys_departs_view")
    public String sys_departs_view() {
        return "sys/depart/sys_departs_view";
    }
    /**
     *部门新增页面
     */
    @RequestMapping("/sys_departs_add")
    public String sys_departs_add() {
        return "sys/depart/sys_departs_add";
    }
    /**
     *部门修改页面
     */
    @RequestMapping("/sys_departs_edit")
    public String sys_departs_edit() {
        return "sys/depart/sys_departs_edit";
    }
    /******************按钮*********************/

    /**
     *按钮列表页面
     */
    @RequestMapping("/sys_menus_view")
    public String sys_menus_view() {
        return "sys/menu/sys_menus_view";
    }

    /**
     *按钮新增页面
     */
    @RequestMapping("/sys_menus_add")
    public String sys_menus_add() {
        return "sys/menu/sys_menus_add";
    }

    /**
     *按钮修改页面
     */
    @RequestMapping("/sys_menus_edit")
    public String sys_menus_edit() {
        return "sys/menu/sys_menus_edit";
    }
    /******************模块*********************/

    /**
     *模块列表页面
     */
    @RequestMapping("/sys_models_view")
    public String sys_models_view() {
        return "sys/model/sys_models_view";
    }

    /**
     *模块新增页面
     */
    @RequestMapping("/sys_models_add")
    public String sys_models_add() {
        return "sys/model/sys_models_add";
    }

    /**
     *模块修改页面
     */
    @RequestMapping("/sys_models_edit")
    public String sys_models_edit() {
        return "sys/model/sys_models_edit";
    }
    /******************模块方法*********************/

    /**
     *模块方法列表页面
     */
    @RequestMapping("/sys_modelfuncs_view")
    public String sys_modelfuncs_view() {
        return "sys/function/sys_modelfuncs_view";
    }

    /**
     *模块方法新增页面
     */
    @RequestMapping("/sys_modelfuncs_add")
    public String sys_modelfuncs_add() {
        return "sys/function/sys_modelfuncs_add";
    }

    /**
     *模块方法修改页面
     */
    @RequestMapping("/sys_modelfuncs_edit")
    public String sys_modelfuncs_edit() {
        return "sys/function/sys_modelfuncs_edit";
    }
    /******************角色*********************/

    /**
     *角色列表页面
     */
    @RequestMapping("/sys_roles_view")
    public String sys_roles_view() {
        return "sys/role/sys_roles_view";
    }

    /**
     *角色新增页面
     */
    @RequestMapping("/sys_roles_add")
    public String sys_roles_add() {
        return "sys/role/sys_roles_add";
    }

    /**
     *角色修改页面
     */
    @RequestMapping("/sys_roles_edit")
    public String sys_roles_edit() {
        return "sys/role/sys_roles_edit";
    }

    /******************人员*********************/

    /**
     *人员列表页面
     */
    @RequestMapping("/sys_users_view")
    public String sys_users_view() {
        return "sys/user/sys_users_view";
    }

    /**
     *人员新增页面
     */
    @RequestMapping("/sys_users_add")
    public String sys_users_add() {
        return "sys/user/sys_users_add";
    }

    /**
     *人员修改页面
     */
    @RequestMapping("/sys_users_edit")
    public String sys_users_edit() {
        return "sys/user/sys_users_edit";
    }

}

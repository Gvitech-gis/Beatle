package org.gt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.gt.bean.sys_modelmenu;
import org.gt.bean.sys_models;
import org.gt.common.pagerHelperRQ;

@Mapper
public interface sys_modelsMapper {

    List<sys_models> selectAll();
    List<sys_models> serchByPage(@Param("pagerHelperRQ") pagerHelperRQ pagerHelperRQ);
    int countAll(@Param("name") String name);
    int delByID(@Param("modelid") String menu_id);
    //List<sys_models_tree>  selectTree();
    int addone(sys_models sys_models );
    int modify(sys_models sys_models );
    sys_models serchById(@Param("id") String id);
    /*
     * 添加按钮
     * */
    int addmodelmenu(sys_modelmenu sys_modelmenu);//
    /*
     * 通过modelid删除所有对应的按钮
     * */
    int delBymodelID(String modelid);//
    /*
     * 通过modelid删除所有对应的按钮
     * */
    List<sys_modelmenu> modelmenuswithmenus();//
    /*
     * 通过modelid删除所有对应的按钮
     * */
    List<sys_modelmenu> getMenus(@Param("modelid") String modelid);//
}

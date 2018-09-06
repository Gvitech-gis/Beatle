package org.gt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.gt.bean.sys_rolemodelmenu;

@Mapper
public interface sys_rolemodelmenuMapper {

    List<sys_rolemodelmenu> selectAll();
    List<sys_rolemodelmenu> selectByRoleid(@Param("roleid") String roleid);
    int delByID(@Param("roleid") String menu_id);
    int addone(sys_rolemodelmenu sys_rolemodelmenu );
}

package org.gt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.gt.bean.sys_rolemodel;

import java.util.List;

@Mapper
public interface sys_rolemodelMapper {

    List<sys_rolemodel> selectAll();
    List<sys_rolemodel> selectByRoleid(@Param("roleid") String roleid);
    int delByID(@Param("roleid") String menu_id);
    int addone(sys_rolemodel sys_rolemodel );
}

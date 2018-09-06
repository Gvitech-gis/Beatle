package org.gt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.gt.bean.sys_rolemodelfunc;

import java.util.List;

@Mapper
public interface sys_rolemodelfuncMapper {

    List<sys_rolemodelfunc> selectAll();
    List<sys_rolemodelfunc> selectByRoleid(@Param("roleid") String roleid);
    int delByID(@Param("roleid") String menu_id);
    int addone(sys_rolemodelfunc sys_rolemodelfunc );
}

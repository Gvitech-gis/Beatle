package org.gt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.gt.bean.sys_departuser;


import java.util.List;

@Mapper
public interface sys_departuserMapper {

    List<sys_departuser> selectAll();
    List<sys_departuser> selectByRoleid(@Param("userid") String roleid);
    int delByID(@Param("userid") String menu_id);
    int addone(sys_departuser sys_departuser );
}

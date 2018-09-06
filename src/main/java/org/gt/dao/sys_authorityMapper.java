package org.gt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.gt.bean.sys_authority;

import java.util.List;

@Mapper
public interface sys_authorityMapper {
    int delByroleid(@Param("userid") String menu_id);
    int addone(sys_authority sys_authority );
    List<sys_authority> serchByroleid(@Param("roleid") String id);
}

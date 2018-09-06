package org.gt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.gt.bean.sys_roles;
import org.gt.common.pagerHelperRQ;

import java.util.List;

@Mapper
public interface sys_rolesMapper {

    List<sys_roles> selectAll();
    List<sys_roles> serchByPage(@Param("pagerHelperRQ") pagerHelperRQ pagerHelperRQ);
    int countAll(@Param("name") String name);
    int delByID(@Param("roleid") String menu_id);
    //List<sys_roles_tree>  selectTree();
    int addone(sys_roles sys_roles );
    int modify(sys_roles sys_roles );
    sys_roles serchById(@Param("id") String id);
}

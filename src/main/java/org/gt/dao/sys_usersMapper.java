package org.gt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.gt.bean.sys_users;
import org.gt.common.pagerHelperRQ;

import java.util.List;

@Mapper
public interface sys_usersMapper {

    List<sys_users> selectAll();
    List<sys_users> serchByPage(@Param("pagerHelperRQ") pagerHelperRQ pagerHelperRQ);
    int countAll(@Param("name") String name);
    int delByID(@Param("userid") String menu_id);
    //List<sys_users_tree>  selectTree();
    int addone(sys_users sys_users );
    int modify(sys_users sys_users );
    sys_users serchById(@Param("id") String id);
    sys_users serchByName(@Param("name") String name);//登录使用
    int hasAU(@Param("url") String url,@Param("userid") String roleid);
}

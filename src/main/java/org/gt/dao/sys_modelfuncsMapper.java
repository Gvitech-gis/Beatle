package org.gt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.gt.bean.sys_modelfuncs;
import org.gt.common.pagerHelperRQ;

import java.util.List;

@Mapper
public interface sys_modelfuncsMapper {

    List<sys_modelfuncs> selectAll();
    List<sys_modelfuncs> serchByPage(@Param("pagerHelperRQ") pagerHelperRQ pagerHelperRQ);
    int countAll(@Param("name") String name);
    int delByID(@Param("funcid") String menu_id);
    //List<sys_modelfuncs_tree>  selectTree();
    int addone(sys_modelfuncs sys_modelfuncs );
    int modify(sys_modelfuncs sys_modelfuncs );
    sys_modelfuncs serchById(@Param("id") String id);
}

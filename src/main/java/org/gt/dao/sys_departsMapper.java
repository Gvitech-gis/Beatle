package org.gt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.gt.bean.sys_departs;
import org.gt.common.pagerHelperRQ;

import java.util.List;

@Mapper
public interface sys_departsMapper {

    List<sys_departs> selectAll();
    List<sys_departs> serchByPage(@Param("pagerHelperRQ") pagerHelperRQ pagerHelperRQ);
    int countAll(@Param("name") String name);
    int delByID(@Param("departid") String menu_id);
    //List<sys_departs_tree>  selectTree();
    int addone(sys_departs sys_departs );
    int modify(sys_departs sys_departs );
    sys_departs serchById(@Param("id") String id);
}

package org.gt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.gt.bean.sys_departrole;

import java.util.List;


@Mapper
public interface sys_departroleMapper {
    /*
     * 閫氳繃userid鎼滅储
     * */
    List<sys_departrole> serchByDeapartId(String departid);//
    /*
     * 娣诲姞涓�涓敤鎴疯鑹插搴�
     * */
    int adddepartrole(sys_departrole sys_departrole);//
    /*
     * 閫氳繃userid鍒犻櫎
     * */
    int delByDepartid(String userid);//
}

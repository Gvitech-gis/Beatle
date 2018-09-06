package org.gt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.gt.bean.sys_userrole;

import java.util.List;

@Mapper
public interface sys_userroleMapper {
    /*
     * 閫氳繃userid鎼滅储
     * */
    List<sys_userrole> serchByUserId(sys_userrole sys_userrole);//
    /*
     * 娣诲姞涓�涓敤鎴疯鑹插搴�
     * */
    int adduserorle(sys_userrole sys_userrole);//
    /*
     * 閫氳繃userid鍒犻櫎
     * */
    int delByUserid(String userid);//
}

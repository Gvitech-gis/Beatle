package org.gt.service;


import org.gt.bean.sys_authority;

import java.util.List;

public interface Isys_authorityService {
	boolean addAuth(List<sys_authority> lst, String roleid);
    List<sys_authority> serchByroleid(String id);
}

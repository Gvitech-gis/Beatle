package org.gt.service;
import org.gt.bean.sys_departuser;
import org.gt.bean.sys_userrole;
import org.gt.bean.sys_users;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;

import java.util.List;


public interface Isys_usersService {
	List<sys_users>  queryAll();
	pagerHelperRS serchbypager(pagerHelperRQ pager);
	List<sys_users>  serchAll();
	//List<sys_users_tree>  serchTree();
	int countAll(String name);
	int delById(String id);
	int addone(sys_users menu);
	int modify(sys_users menu);
	sys_users serchByID(String id);
	sys_users serchByName(String name);

	boolean adduserroles(String userid, List<sys_userrole> sys_userroles);
	boolean updateuserdeparts(String userid, List<sys_departuser> sys_departusers);
	int hasAU(String url, String userid);
}

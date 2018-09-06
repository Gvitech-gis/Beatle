package org.gt.service;
import org.gt.bean.sys_menus;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;

import java.util.List;

public interface Isys_menusService {
	List<sys_menus>  queryAll();
	pagerHelperRS serchbypager(pagerHelperRQ pager);
	List<sys_menus>  serchAll();
	//List<sys_menus_tree>  serchTree();
	int countAll(String name);
	int delById(String id);
	int addone(sys_menus menu);
	int modify(sys_menus menu);
	sys_menus serchByID(String id);
}

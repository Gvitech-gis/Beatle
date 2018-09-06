package org.gt.service;
import org.gt.bean.sys_modelfuncs;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;

import java.util.List;

public interface Isys_modelfuncsService {
	List<sys_modelfuncs>  queryAll();
	pagerHelperRS serchbypager(pagerHelperRQ pager);
	List<sys_modelfuncs>  serchAll();
	//List<sys_modelfuncs_tree>  serchTree();
	int countAll(String name);
	int delById(String id);
	int addone(sys_modelfuncs menu);
	int modify(sys_modelfuncs menu);
	sys_modelfuncs serchByID(String id);
}

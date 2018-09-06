package org.gt.service;
import org.gt.bean.sys_departrole;
import org.gt.bean.sys_departs;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;

import java.util.List;

public interface Isys_departsService {
	List<sys_departs>  queryAll();
	pagerHelperRS serchbypager(pagerHelperRQ pager);
	List<sys_departs>  serchAll();
	//List<sys_departs_tree>  serchTree();
	int countAll(String name);
	int delById(String id);
	int addone(sys_departs menu);
	int modify(sys_departs menu);
	sys_departs serchByID(String id);
	boolean adddepartroles(String departid, List<sys_departrole> lstdepartrole);
}

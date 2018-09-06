package org.gt.service;
import org.gt.bean.sys_modelmenu;
import org.gt.bean.sys_models;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;

import java.util.List;


public interface Isys_modelsService {
	List<sys_models>  queryAll();
	pagerHelperRS serchbypager(pagerHelperRQ pager);
	List<sys_models>  serchAll();
	//List<sys_models_tree>  serchTree();
	int countAll(String name);
	int delById(String id);
	int addone(sys_models menu);
	int modify(sys_models menu);
	sys_models serchByID(String id);
	boolean addmodelmenus(String modelid, List<sys_modelmenu> lstmodelmenu);
//	List<sys_models_gridtree> serchModelGridTree();
    List<sys_modelmenu> modelmenuswithmenus();//
    List<sys_modelmenu> getMenus(String modelid);
	
}

package org.gt.service.impl;

import org.gt.bean.sys_menus;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;
import org.gt.dao.sys_menusMapper;
import org.gt.service.Isys_menusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class sys_menusServiceImpl implements Isys_menusService {
	@Autowired
	private sys_menusMapper sys_menusDaos;
	//@Autowired
	private pagerHelperRS pagerRs;
	
	@Override
	public List<sys_menus> queryAll() {
		List<sys_menus> sys_menuss = null;
		sys_menuss = sys_menusDaos.selectAll();
		return sys_menuss;
	}
	@Override
	public pagerHelperRS serchbypager(pagerHelperRQ pager) {
		pager.setStart();
		pagerRs=new pagerHelperRS();
		List<sys_menus> sys_menuss = null;
		int count = sys_menusDaos.countAll(pager.getSearchString());
		//return count;
		sys_menuss = sys_menusDaos.serchByPage(pager);
		pagerRs.setRows(sys_menuss);
		pagerRs.setPage(pager.getPage());
		pagerRs.setRecords(count);
		pagerRs.setTotal((int)Math.ceil((double)count/(double)pager.getRows()));
		return pagerRs;
	}
	@Override
	public List<sys_menus> serchAll() {
		List<sys_menus> sys_menuss = null;
		sys_menuss = sys_menusDaos.selectAll();
		return sys_menuss;
	}
	@Override
	public int countAll(String name) {
		int count = sys_menusDaos.countAll(name);
		return count;
	}	
	@Override
	public int delById(String id) {
		return sys_menusDaos.delByID(id);
	}
	@Override
	public int addone(sys_menus menu) {
		return sys_menusDaos.addone(menu);
	}
	@Override
	public int modify(sys_menus menu) {
		return sys_menusDaos.modify(menu);
	}
	@Override
	public sys_menus serchByID(String id){
		return sys_menusDaos.serchById(id);
	}

}

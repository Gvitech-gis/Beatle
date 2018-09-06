package org.gt.service.impl;

import org.gt.bean.sys_rolemodel;
import org.gt.bean.sys_rolemodelfunc;
import org.gt.bean.sys_rolemodelmenu;
import org.gt.bean.sys_roles;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;
import org.gt.dao.sys_rolemodelMapper;
import org.gt.dao.sys_rolemodelfuncMapper;
import org.gt.dao.sys_rolemodelmenuMapper;
import org.gt.dao.sys_rolesMapper;
import org.gt.service.Isys_rolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class sys_rolesServiceImpl implements Isys_rolesService {
	@Autowired
	private sys_rolesMapper sys_rolesDao;
	@Autowired
	private sys_rolemodelMapper sys_rolemodelDao;
	@Autowired
	private sys_rolemodelmenuMapper sys_rolemodelmenuDao;
	@Autowired
	private sys_rolemodelfuncMapper sys_rolemodelfuncDao;
	//@Autowired
	private pagerHelperRS pagerRs;
	
	@Override
	public List<sys_roles> queryAll() {
		List<sys_roles> sys_roless = null;
		sys_roless = sys_rolesDao.selectAll();
		return sys_roless;
	}
	@Override
	public pagerHelperRS serchbypager(pagerHelperRQ pager) {
		pager.setStart();
		pagerRs=new pagerHelperRS();
		List<sys_roles> sys_roless = null;
		int count = sys_rolesDao.countAll(pager.getSearchString());
		//return count;
		sys_roless = sys_rolesDao.serchByPage(pager);
		pagerRs.setRows(sys_roless);
		pagerRs.setPage(pager.getPage());
		pagerRs.setRecords(count);
		pagerRs.setTotal((int)Math.ceil((double)count/(double)pager.getRows()));
		return pagerRs;
	}
	@Override
	public List<sys_roles> serchAll() {
		List<sys_roles> sys_roless = null;
		sys_roless = sys_rolesDao.selectAll();
		return sys_roless;
	}
	@Override
	public int countAll(String name) {
		int count = sys_rolesDao.countAll(name);
		return count;
	}	
	@Override
	public int delById(String id) {
		return sys_rolesDao.delByID(id);
	}
	@Override
	public int addone(sys_roles menu) {
		return sys_rolesDao.addone(menu);
	}
	@Override
	public int modify(sys_roles menu) {
		return sys_rolesDao.modify(menu);
	}
	@Override
	public sys_roles serchByID(String id){
		return sys_rolesDao.serchById(id);
	}
	@Override
	public boolean updateRoleModels(String roleid, List<sys_rolemodel> lstrolemodel) {
		sys_rolemodelDao.delByID(roleid);
		for (sys_rolemodel one : lstrolemodel) {
			sys_rolemodelDao.addone(one);
		}
		return true;
	}
	@Override
	public boolean updateRoleModelMenus(String roleid, List<sys_rolemodelmenu> lstrolemodelmenu) {
		sys_rolemodelmenuDao.delByID(roleid);
		for (sys_rolemodelmenu one : lstrolemodelmenu) {
			sys_rolemodelmenuDao.addone(one);
		}
		return true;
	}
	@Override
	public List<sys_rolemodel> selectRoleModels(String roleid) {
		return	sys_rolemodelDao.selectByRoleid(roleid);
		 
	}
	@Override
	public List<sys_rolemodelmenu> selectRoleModelMenus(String roleid) {
		return sys_rolemodelmenuDao.selectByRoleid(roleid);
	}
	@Override
	public boolean updateRoleModelfuncs(String roleid, List<sys_rolemodelfunc> lstrolemodelfunc) {
		sys_rolemodelfuncDao.delByID(roleid);
		for (sys_rolemodelfunc one : lstrolemodelfunc) {
			sys_rolemodelfuncDao.addone(one);
		}
		return true;
	}
	@Override
	public List<sys_rolemodelfunc> selectRoleModelfuncs(String roleid) {
		return sys_rolemodelfuncDao.selectByRoleid(roleid);
	}
}

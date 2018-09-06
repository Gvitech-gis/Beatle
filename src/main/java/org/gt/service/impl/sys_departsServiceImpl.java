package org.gt.service.impl;

import org.gt.bean.sys_departrole;
import org.gt.bean.sys_departs;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;
import org.gt.dao.sys_departroleMapper;
import org.gt.dao.sys_departsMapper;
import org.gt.service.Isys_departsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class sys_departsServiceImpl implements Isys_departsService {
	@Autowired
	private sys_departsMapper sys_departsDao;
	@Autowired
	private sys_departroleMapper sys_departroleDao;
	//@Autowired
	private pagerHelperRS pagerRs;
	
	@Override
	public List<sys_departs> queryAll() {
		List<sys_departs> sys_departss = null;
		sys_departss = sys_departsDao.selectAll();
		return sys_departss;
	}
	@Override
	public pagerHelperRS serchbypager(pagerHelperRQ pager) {
		pager.setStart();
		pagerRs=new pagerHelperRS();
		List<sys_departs> sys_departss = null;
		int count = sys_departsDao.countAll(pager.getSearchString());
		//return count;
		sys_departss = sys_departsDao.serchByPage(pager);
		pagerRs.setRows(sys_departss);
		pagerRs.setPage(pager.getPage());
		pagerRs.setRecords(count);
		pagerRs.setTotal((int)Math.ceil((double)count/(double)pager.getRows()));
		return pagerRs;
	}
	@Override
	public List<sys_departs> serchAll() {
		List<sys_departs> sys_departss = null;
		sys_departss = sys_departsDao.selectAll();
		return sys_departss;
	}
	@Override
	public int countAll(String name) {
		int count = sys_departsDao.countAll(name);
		return count;
	}	
	@Override
	public int delById(String id) {
		return sys_departsDao.delByID(id);
	}
	@Override
	public int addone(sys_departs menu) {
		return sys_departsDao.addone(menu);
	}
	@Override
	public int modify(sys_departs menu) {
		return sys_departsDao.modify(menu);
	}
	@Override
	public sys_departs serchByID(String id){
		return sys_departsDao.serchById(id);
	}
	@Override
	public boolean adddepartroles(String departid, List<sys_departrole> lstdepartrole) {
		sys_departroleDao.delByDepartid(departid);
		for (sys_departrole one : lstdepartrole) {
			sys_departroleDao.adddepartrole(one);
		}
		return true;
	}
}

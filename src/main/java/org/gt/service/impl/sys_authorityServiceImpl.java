package org.gt.service.impl;

import org.gt.bean.sys_authority;
import org.gt.dao.sys_authorityMapper;
import org.gt.service.Isys_authorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class sys_authorityServiceImpl implements Isys_authorityService {

	@Autowired
	private sys_authorityMapper sys_authorityDao;
	@Override
	public boolean addAuth(List<sys_authority> lst, String roleid) {
		sys_authorityDao.delByroleid(roleid);
		for(sys_authority one : lst){
			sys_authorityDao.addone(one);
		}
		return true;
	}

	@Override
	public List<sys_authority> serchByroleid(String id) {
		return sys_authorityDao.serchByroleid(id);
	}
}

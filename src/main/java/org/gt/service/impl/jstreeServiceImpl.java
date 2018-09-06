package org.gt.service.impl;

import org.gt.dao.jstreeMapper;
import org.gt.service.IjstreeService;
import org.gt.vo.jstree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class jstreeServiceImpl implements IjstreeService {
	@Autowired
	private jstreeMapper jstreeDao;
	
	@Override
	public List<jstree> model_tree() {
		List<jstree> jstrees = null;
		jstrees = jstreeDao.model_tree();
		return jstrees;
	}
	@Override
	public List<jstree> role_tree() {
		List<jstree> jstrees = null;
		jstrees = jstreeDao.role_tree();
		return jstrees;
	}
	@Override
	public List<jstree> modelfunc_tree() {
		List<jstree> jstrees = null;
		jstrees = jstreeDao.modelfunc_tree();
		return jstrees;
	}
}

package org.gt.service.impl;

import org.gt.bean.sys_modelmenu;
import org.gt.bean.sys_models;
import org.gt.common.pagerHelperRQ;
import org.gt.common.pagerHelperRS;
import org.gt.dao.sys_modelsMapper;
import org.gt.service.Isys_modelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class sys_modelsServiceImpl implements Isys_modelsService {
	@Autowired
	private sys_modelsMapper sys_modelsDao;
	//@Autowired
	private pagerHelperRS pagerRs;
	
	@Override
	public List<sys_models> queryAll() {
		List<sys_models> sys_modelss = null;
		sys_modelss = sys_modelsDao.selectAll();
		return sys_modelss;
	}
	@Override
	public pagerHelperRS serchbypager(pagerHelperRQ pager) {
		pager.setStart();
		pagerRs=new pagerHelperRS();
		List<sys_models> sys_modelss = null;
		int count = sys_modelsDao.countAll(pager.getSearchString());
		//return count;
		sys_modelss = sys_modelsDao.serchByPage(pager);
		pagerRs.setRows(sys_modelss);
		pagerRs.setPage(pager.getPage());
		pagerRs.setRecords(count);
		pagerRs.setTotal((int)Math.ceil((double)count/(double)pager.getRows()));
		return pagerRs;
	}
	@Override
	public List<sys_models> serchAll() {
		List<sys_models> sys_modelss = null;
		sys_modelss = sys_modelsDao.selectAll();
		return sys_modelss;
	}
	@Override
	public int countAll(String name) {
		int count = sys_modelsDao.countAll(name);
		return count;
	}	
	@Override
	public int delById(String id) {
		return sys_modelsDao.delByID(id);
	}
	@Override
	public int addone(sys_models menu) {
		return sys_modelsDao.addone(menu);
	}
	@Override
	public int modify(sys_models menu) {
		return sys_modelsDao.modify(menu);
	}
	@Override
	public sys_models serchByID(String id){
		return sys_modelsDao.serchById(id);
	}
	@Override
	public  List<sys_modelmenu> modelmenuswithmenus(){
		return sys_modelsDao.modelmenuswithmenus();
	}
	@Override
	public boolean addmodelmenus(String id,List<sys_modelmenu> lstmodelmenu){
		sys_modelsDao.delBymodelID(id);
		for(sys_modelmenu one : lstmodelmenu){
			sys_modelsDao.addmodelmenu(one);
		}
		return true;
	}
//	@Override
//	public List<sys_models_gridtree> serchModelGridTree() {
//		List<sys_models> lstModel=sys_modelsDao.selectAll();
//		 Stack<sys_models> stChildren = new Stack<sys_models>();
//		for (sys_models sys_model : lstModel) {
//			stChildren.push(sys_model);
//		}
//		return null;
//	}
//	private List<sys_models_gridtree> getGridTree(Stack<sys_models> stChildren,List<sys_models> lstModel) {
//		List<sys_models_gridtree>lstmodels_gridtree = new ArrayList<sys_models_gridtree>();
//		for (sys_models sys_model : lstModel) {
//			sys_models_gridtree models_gridtree =new sys_models_gridtree();
//			Integer i=0;
//			while (stChildren.iterator().hasNext()) {
//				sys_models sys_modelpush=stChildren.get(i);
//				i++;
//				if(sys_modelpush.getParentid().equals(sys_model.getModelid())){
//					
//				}
//			}
//		}
//		while (stChildren.pop()!=null) {
//			
//		}
//		//getGridTree();
//	}
//	public static void it(Stack<sys_models> s){
//        Iterator<sys_models> it = s.iterator();  
//        while(it.hasNext()){  
//            System.out.print(it.next()+";");  
//        }   
//    }  
	@Override
	public List<sys_modelmenu> getMenus(String modelid) {
		// TODO Auto-generated method stub
		return sys_modelsDao.getMenus(modelid);
	}
}

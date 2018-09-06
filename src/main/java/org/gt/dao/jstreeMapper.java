package org.gt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.gt.vo.jstree;
@Mapper
public interface jstreeMapper {
	/*
	 * ģ������б�ҳ�������
	 * */
    List<jstree>  model_tree();
	/*
	 * ��ɫ�����б�ҳ�������
	 * */
    List<jstree>  role_tree();
	/*
	 * ��ɫ�б�ҳ�淽��Ȩ�޹���
	 * */
    List<jstree>  modelfunc_tree();
    
}

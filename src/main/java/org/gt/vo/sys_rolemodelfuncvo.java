package org.gt.vo;

import java.util.List;

import org.gt.bean.sys_rolemodelfunc;

public class sys_rolemodelfuncvo {
		//��ɫ��ҳ��--����ɫ��ģ�鰴ťȨ��
		private List<sys_rolemodelfunc> sys_rolemodelfuncs;
		private String roleid;
		public List<sys_rolemodelfunc> getSys_rolemodelfuncs() {
			return sys_rolemodelfuncs;
		}
		public void setSys_rolemodelfuncs(List<sys_rolemodelfunc> sys_rolemodelfuncs) {
			this.sys_rolemodelfuncs = sys_rolemodelfuncs;
		}
		public String getRoleid() {
			return roleid;
		}
		public void setRoleid(String roleid) {
			this.roleid = roleid;
		}
		
}

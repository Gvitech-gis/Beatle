package org.gt.vo;

import org.gt.bean.sys_rolemodelmenu;

import java.util.List;


public class sys_rolemodelmenuvo {
		//��ɫ��ҳ��--����ɫ��ģ�鰴ťȨ��
		private List<sys_rolemodelmenu> sys_rolemodelmenus;
		private String roleid;
		public List<sys_rolemodelmenu> getSys_rolemodelmenus() {
			return sys_rolemodelmenus;
		}
		public void setSys_rolemodelmenus(List<sys_rolemodelmenu> sys_rolemodelmenus) {
			this.sys_rolemodelmenus = sys_rolemodelmenus;
		}
		public String getRoleid() {
			return roleid;
		}
		public void setRoleid(String roleid) {
			this.roleid = roleid;
		}
}

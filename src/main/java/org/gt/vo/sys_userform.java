package org.gt.vo;

import org.gt.bean.sys_userrole;

import java.util.List;


public class sys_userform {
		//�������͵�����
		private List<sys_userrole> lstsys_userrole;
		private String userid;
		public List<sys_userrole> getLstsys_userrole() {
			return lstsys_userrole;
		}
		public void setLstsys_userrole(List<sys_userrole> lstsys_userrole) {
			this.lstsys_userrole = lstsys_userrole;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		

}

package org.gt.vo;

import org.gt.bean.sys_departuser;

import java.util.List;


public class sys_userdepart_vo {
		//��Ա��ҳ��--����Ա���䲿��
		private List<sys_departuser> lstsys_userdepart;
		private String userid;
		public List<sys_departuser> getLstsys_userdepart() {
			return lstsys_userdepart;
		}
		public void setLstsys_userdepart(List<sys_departuser> lstsys_userdepart) {
			this.lstsys_userdepart = lstsys_userdepart;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
}

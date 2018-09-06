package org.gt.vo;

import org.gt.bean.sys_modelmenu;

import java.util.List;


public class sys_modelform {
		//�������͵�����
		private List<sys_modelmenu> sys_modelmenuss;
		private String modelid;

		public List<sys_modelmenu> getSys_modelmenuss() {
			return sys_modelmenuss;
		}

		public void setSys_modelmenuss(List<sys_modelmenu> sys_modelmenuss) {
			this.sys_modelmenuss = sys_modelmenuss;
		}

		public String getModelid() {
			return modelid;
		}

		public void setModelid(String modelid) {
			this.modelid = modelid;
		}
		
}

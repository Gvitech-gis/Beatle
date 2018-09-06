package org.gt.vo;

import org.gt.bean.sys_departs;
import org.gt.bean.sys_roles;

import java.util.List;


public class sys_users_session {
	private  String userid;
	private  Integer orderby;
	private  String username;
	private  String description;
	private  String password;
	private  String rolename;
	private  String departname;
	private  String setting;
	private  String isused;
	private  String logincount;
	private  String lastlogintime;
	private  String creatuser;
	private  String creattime;
	private  String modifyuser;
	private  String modifytime;
	private List<sys_roles> lstRoleid;
	private List<sys_departs> lstDepartid;
	//private List<model> lstDepartid;
	private String randCode;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getOrderby() {
		return orderby;
	}
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	public String getSetting() {
		return setting;
	}
	public void setSetting(String setting) {
		this.setting = setting;
	}
	public String getIsused() {
		return isused;
	}
	public void setIsused(String isused) {
		this.isused = isused;
	}
	public String getLogincount() {
		return logincount;
	}
	public void setLogincount(String logincount) {
		this.logincount = logincount;
	}
	public String getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public String getCreatuser() {
		return creatuser;
	}
	public void setCreatuser(String creatuser) {
		this.creatuser = creatuser;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	public String getModifytime() {
		return modifytime;
	}
	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}
	public List<sys_roles> getLstRoleid() {
		return lstRoleid;
	}
	public void setLstRoleid(List<sys_roles> lstRoleid) {
		this.lstRoleid = lstRoleid;
	}
	public List<sys_departs> getLstDepartid() {
		return lstDepartid;
	}
	public void setLstDepartid(List<sys_departs> lstDepartid) {
		this.lstDepartid = lstDepartid;
	}
	public String getRandCode() {
		return randCode;
	}
	public void setRandCode(String randCode) {
		this.randCode = randCode;
	}
	
	
}

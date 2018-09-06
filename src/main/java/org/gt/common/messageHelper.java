package org.gt.common;


public class messageHelper {
	private String msg;
	private int mst;//0表示成功、1表示失败
	private Object data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getMst() {
		return mst;
	}
	public void setMst(int mst) {
		this.mst = mst;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}

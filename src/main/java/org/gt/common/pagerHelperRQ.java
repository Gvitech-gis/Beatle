package org.gt.common;

public class pagerHelperRQ {
private boolean _search;//�Ƿ��в�ѯ����
private String nd; //��ѯ���к�
private int rows;//����
private int page;//ҳ��
private int start;//��ʼ����
private String sidx;//�������������
private String sord;//����ʽ
private String searchField;//��ѯ�ֶ�
private String searchString;//��ѯ����
private String searchOper;//��ѯ��ʽ
private String filters;
public boolean is_search() {
	return _search;
}
public void set_search(boolean _search) {
	this._search = _search;
}
public String getNd() {
	return nd;
}
public void setNd(String nd) {
	this.nd = nd;
}
public int getRows() {
	return rows;
}
public void setRows(int rows) {
	this.rows = rows;
}
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
public String getSidx() {
	return sidx==""?null:sidx;
}
public void setSidx(String sidx) {
	this.sidx = sidx==""?null:sidx;
}
public String getSord() {
	return sord;
}
public void setSord(String sord) {
	this.sord = sord;
}
public String getSearchField() {
	return searchField;
}
public void setSearchField(String searchField) {
	this.searchField = searchField;
}
public String getSearchString() {
	return searchString;
}
public void setSearchString(String searchString) {
	this.searchString = searchString;
}
public String getSearchOper() {
	return searchOper;
}
public void setSearchOper(String searchOper) {
	this.searchOper = searchOper;
}
public String getFilters() {
	return filters;
}
public void setFilters(String filters) {
	this.filters = filters;
}
public int getStart() {
	return start;
}
public void setStart() {
	if(this.page-1>0){
		this.start = (this.page-1) * this.rows;
	}else{
		this.start = 0;
	}
}

}

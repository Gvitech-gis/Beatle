package org.gt.been;

public class Fields {
	private String fieldmame;
	private String fieldtype;
	public Fields(String name,String type)
	{
		fieldmame=name;
		fieldtype=type;
	}
	public String getFieldtype() {
		return fieldtype;
	}
	public String getFieldname() {
		return fieldmame;
	}
}

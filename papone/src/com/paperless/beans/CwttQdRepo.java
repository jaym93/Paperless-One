package com.paperless.beans;

public class CwttQdRepo {
	private String statusClass;
	private String cwttNum;
	private String cwttClass;
	private int qdNum;
	private String qdClass;
	
	
	public String getStatusClass() {
		return statusClass;
	}
	public void setStatusClass(String statusClass) {
		this.statusClass = statusClass;
	}
	public String getCwttNum() {
		return cwttNum;
	}
	public void setCwttNum(String string) {
		this.cwttNum = string;
	}
	public String getCwttClass() {
		return cwttClass;
	}
	public void setCwttClass(String cwttClass) {
		this.cwttClass = cwttClass;
	}
	public int getQdNum() {
		return qdNum;
	}
	public void setQdNum(int qd) {
		this.qdNum = qd;
	}
	public String getQdClass() {
		return qdClass;
	}
	public void setQdClass(String qdClass) {
		this.qdClass = qdClass;
	}
	

}

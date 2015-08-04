package com.paperless.beans;

import java.util.List;

public class IndexPage {
	
	private String imToday;
	private String frToday;
	private String imMonth;
	private String frMonth;
	private String crMonth;
	private String prMonth;
	private List<topIssues> topIssueList;
	private List<CalenderData> calArray;
	private List<CalenderData> calPresentMonthArray;
	
	
	public List<CalenderData> getCalPresentMonthArray() {
		return calPresentMonthArray;
	}
	public void setCalPresentMonthArray(List<CalenderData> calPresentMonthArray) {
		this.calPresentMonthArray = calPresentMonthArray;
	}
	public List<CalenderData> getCalArray() {
		return calArray;
	}
	public void setCalArray(List<CalenderData> calArray) {
		this.calArray = calArray;
	}
	public String getImToday() {
		return imToday;
	}
	public void setImToday(String imToday) {
		this.imToday = imToday;
	}
	public String getFrToday() {
		return frToday;
	}
	public void setFrToday(String frToday) {
		this.frToday = frToday;
	}
	public String getImMonth() {
		return imMonth;
	}
	public void setImMonth(String imMonth) {
		this.imMonth = imMonth;
	}
	public String getFrMonth() {
		return frMonth;
	}
	public void setFrMonth(String frMonth) {
		this.frMonth = frMonth;
	}
	public String getCrMonth() {
		return crMonth;
	}
	public void setCrMonth(String crMonth) {
		this.crMonth = crMonth;
	}
	public String getPrMonth() {
		return prMonth;
	}
	public void setPrMonth(String prMonth) {
		this.prMonth = prMonth;
	}
	public List<topIssues> getTopIssueList() {
		return topIssueList;
	}
	public void setTopIssueList(List<topIssues> topIssueList) {
		this.topIssueList = topIssueList;
	}
	

}

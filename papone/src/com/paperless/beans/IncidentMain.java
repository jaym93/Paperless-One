package com.paperless.beans;

import java.util.List;

public class IncidentMain {
	
	private List<Incident> incidentList;
	private List<String> month;
	private List<Integer> incCount;
	public List<Incident> getIncidentList() {
		return incidentList;
	}
	public void setIncidentList(List<Incident> incidentList) {
		this.incidentList = incidentList;
	}
	public List<String> getMonth() {
		return month;
	}
	public void setMonth(List<String> month) {
		this.month = month;
	}
	public List<Integer> getIncCount() {
		return incCount;
	}
	public void setIncCount(List<Integer> incCount) {
		this.incCount = incCount;
	}

	
	

}

package com.paperless.beans;

import java.util.List;

public class Problems {
	
	private int proactiveCount;
	private int reactiveCount;
	private int rcaKnown;
	private int rcaUnknown;
	private List<Incident> proactiveList;
	private List<Incident> reactiveList;
	private List<Incident> rcaKnownList;
	private List<Incident> rcaUnknownList;
	private List<Incident> probList;
	private List<Integer> probCount;
	private List<String> month;
	private List<Integer> probCountClosed;
	private List<String> monthClosed;
	
	
	
	public List<Integer> getProbCountClosed() {
		return probCountClosed;
	}
	public void setProbCountClosed(List<Integer> probCountClosed) {
		this.probCountClosed = probCountClosed;
	}
	public List<String> getMonthClosed() {
		return monthClosed;
	}
	public void setMonthClosed(List<String> monthClosed) {
		this.monthClosed = monthClosed;
	}
	public int getProactiveCount() {
		return proactiveCount;
	}
	public void setProactiveCount(int proactiveCount) {
		this.proactiveCount = proactiveCount;
	}
	public int getReactiveCount() {
		return reactiveCount;
	}
	public void setReactiveCount(int reactiveCount) {
		this.reactiveCount = reactiveCount;
	}
	public int getRcaKnown() {
		return rcaKnown;
	}
	public void setRcaKnown(int rcaKnown) {
		this.rcaKnown = rcaKnown;
	}
	public int getRcaUnknown() {
		return rcaUnknown;
	}
	public void setRcaUnknown(int rcaUnknown) {
		this.rcaUnknown = rcaUnknown;
	}
	public List<Incident> getProactiveList() {
		return proactiveList;
	}
	public void setProactiveList(List<Incident> proactiveList) {
		this.proactiveList = proactiveList;
	}
	public List<Incident> getReactiveList() {
		return reactiveList;
	}
	public void setReactiveList(List<Incident> reactiveList) {
		this.reactiveList = reactiveList;
	}
	public List<Incident> getRcaKnownList() {
		return rcaKnownList;
	}
	public void setRcaKnownList(List<Incident> rcaKnownList) {
		this.rcaKnownList = rcaKnownList;
	}
	public List<Incident> getRcaUnknownList() {
		return rcaUnknownList;
	}
	public void setRcaUnknownList(List<Incident> rcaUnknownList) {
		this.rcaUnknownList = rcaUnknownList;
	}
	public List<Incident> getProbList() {
		return probList;
	}
	public void setProbList(List<Incident> probList) {
		this.probList = probList;
	}
	public List<Integer> getProbCount() {
		return probCount;
	}
	public void setProbCount(List<Integer> probCount) {
		this.probCount = probCount;
	}
	public List<String> getMonth() {
		return month;
	}
	public void setMonth(List<String> month) {
		this.month = month;
	}
	
	

}

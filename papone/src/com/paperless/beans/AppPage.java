package com.paperless.beans;

import java.util.List;

public class AppPage {
	
	private int criticalIncident;
	private int majorIncident;
	private int minorIncident;
	private int criticalProblem;
	private int majorProblem;
	private int minorProblem;
	private int frCount;
	private List<topIssues> topIssueList;
	private List<CalenderData> calArray;
	private List<ticketDetails> ticketDetailCritical;
	private List<ticketDetails> ticketDetailMajor;
	private List<ticketDetails> ticketDetailMinor;
	private List<ticketDetails> problemDetailCritical;
	private List<ticketDetails> problemDetailMajor;
	private List<ticketDetails> problemDetailMinor;
	private List<Fulfillments>  frDetails;
	
	
	
	public int getFrCount() {
		return frCount;
	}
	public void setFrCount(int frCount) {
		this.frCount = frCount;
	}
	public List<Fulfillments> getFrDetails() {
		return frDetails;
	}
	public void setFrDetails(List<Fulfillments> frDetails) {
		this.frDetails = frDetails;
	}
	public int getCriticalIncident() {
		return criticalIncident;
	}
	public void setCriticalIncident(int criticalIncident) {
		this.criticalIncident = criticalIncident;
	}
	public int getMajorIncident() {
		return majorIncident;
	}
	public void setMajorIncident(int majorIncident) {
		this.majorIncident = majorIncident;
	}
	public int getMinorIncident() {
		return minorIncident;
	}
	public void setMinorIncident(int minorIncident) {
		this.minorIncident = minorIncident;
	}
	public int getCriticalProblem() {
		return criticalProblem;
	}
	public void setCriticalProblem(int criticalProblem) {
		this.criticalProblem = criticalProblem;
	}
	public int getMajorProblem() {
		return majorProblem;
	}
	public void setMajorProblem(int majorProblem) {
		this.majorProblem = majorProblem;
	}
	public int getMinorProblem() {
		return minorProblem;
	}
	public void setMinorProblem(int minorProblem) {
		this.minorProblem = minorProblem;
	}
	public List<topIssues> getTopIssueList() {
		return topIssueList;
	}
	public void setTopIssueList(List<topIssues> topIssueList) {
		this.topIssueList = topIssueList;
	}
	public List<CalenderData> getCalArray() {
		return calArray;
	}
	public void setCalArray(List<CalenderData> calArray) {
		this.calArray = calArray;
	}
	public List<ticketDetails> getTicketDetailCritical() {
		return ticketDetailCritical;
	}
	public void setTicketDetailCritical(List<ticketDetails> ticketDetailCritical) {
		this.ticketDetailCritical = ticketDetailCritical;
	}
	public List<ticketDetails> getTicketDetailMajor() {
		return ticketDetailMajor;
	}
	public void setTicketDetailMajor(List<ticketDetails> ticketDetailMajor) {
		this.ticketDetailMajor = ticketDetailMajor;
	}
	public List<ticketDetails> getTicketDetailMinor() {
		return ticketDetailMinor;
	}
	public void setTicketDetailMinor(List<ticketDetails> ticketDetailMinor) {
		this.ticketDetailMinor = ticketDetailMinor;
	}
	public List<ticketDetails> getProblemDetailCritical() {
		return problemDetailCritical;
	}
	public void setProblemDetailCritical(List<ticketDetails> problemDetailCritical) {
		this.problemDetailCritical = problemDetailCritical;
	}
	public List<ticketDetails> getProblemDetailMajor() {
		return problemDetailMajor;
	}
	public void setProblemDetailMajor(List<ticketDetails> problemDetailMajor) {
		this.problemDetailMajor = problemDetailMajor;
	}
	public List<ticketDetails> getProblemDetailMinor() {
		return problemDetailMinor;
	}
	public void setProblemDetailMinor(List<ticketDetails> problemDetailMinor) {
		this.problemDetailMinor = problemDetailMinor;
	}
	
}

package com.paperless.beans;

import java.util.List;

public class ProblemDetails {
	
	private List<PhaseBean> graphData;
	private List<Integer> ageingProblemCount;
	private List<Incident> detail30;
	private List<Incident> detail90;
	private List<Incident> detail180;
	private List<Incident> identifyProblem;
	private List<Incident> investigateProblem;
	private List<Incident> nKnownErrorInvestigation;
	private List<Incident> detailOver360;
	private List<Incident> nKnowErrorSolApproval;
	private List<Incident> nRCApproval;
	private List<String> phaseName;
	
	
	public List<PhaseBean> getGraphData() {
		return graphData;
	}
	public void setGraphData(List<PhaseBean> graphData) {
		this.graphData = graphData;
	}
	public List<Integer> getAgeingProblemCount() {
		return ageingProblemCount;
	}
	public void setAgeingProblemCount(List<Integer> ageingProblemCount) {
		this.ageingProblemCount = ageingProblemCount;
	}
	public List<Incident> getDetail30() {
		return detail30;
	}
	public void setDetail30(List<Incident> detail30) {
		this.detail30 = detail30;
	}
	public List<Incident> getDetail90() {
		return detail90;
	}
	public void setDetail90(List<Incident> detail90) {
		this.detail90 = detail90;
	}
	public List<Incident> getDetail180() {
		return detail180;
	}
	public void setDetail180(List<Incident> detail180) {
		this.detail180 = detail180;
	}
	public List<Incident> getIdentifyProblem() {
		return identifyProblem;
	}
	public void setIdentifyProblem(List<Incident> identifyProblem) {
		this.identifyProblem = identifyProblem;
	}
	public List<Incident> getInvestigateProblem() {
		return investigateProblem;
	}
	public void setInvestigateProblem(List<Incident> investigateProblem) {
		this.investigateProblem = investigateProblem;
	}
	public List<Incident> getnKnownErrorInvestigation() {
		return nKnownErrorInvestigation;
	}
	public void setnKnownErrorInvestigation(List<Incident> nKnownErrorInvestigation) {
		this.nKnownErrorInvestigation = nKnownErrorInvestigation;
	}
	public List<Incident> getDetailOver360() {
		return detailOver360;
	}
	public void setDetailOver360(List<Incident> detailOver360) {
		this.detailOver360 = detailOver360;
	}
	public List<Incident> getnKnowErrorSolApproval() {
		return nKnowErrorSolApproval;
	}
	public void setnKnowErrorSolApproval(List<Incident> nKnowErrorSolApproval) {
		this.nKnowErrorSolApproval = nKnowErrorSolApproval;
	}
	public List<Incident> getnRCApproval() {
		return nRCApproval;
	}
	public void setnRCApproval(List<Incident> nRCApproval) {
		this.nRCApproval = nRCApproval;
	}
	public List<String> getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(List<String> phaseName) {
		this.phaseName = phaseName;
	}
	
	
	
	

}

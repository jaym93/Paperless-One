package com.paperless.process.executors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paperless.beans.Incident;
import com.paperless.beans.PhaseBean;
import com.paperless.beans.ProblemDetails;
import com.paperless.beans.Problems;
import com.paperless.connections.ConnectionHelper;
import com.paperless.connections.SQL;
import com.paperless.connections.Select;

public class PrDetailsPageExecuter {
	
	public Problems getProblemDetailsFirst(String ci) throws Exception{
		
		Problems localBean = new Problems();
		Incident incBean;
		 List<Incident> proactiveList = new ArrayList<Incident>();
		 List<Incident> reactiveList = new ArrayList<Incident>();
		 List<Incident> rcaKnownList = new ArrayList<Incident>();
		 List<Incident> rcaUnknownList = new ArrayList<Incident>();
		 List<Integer> probCount = new ArrayList<Integer>();
		 List<String> month = new ArrayList<String>();
		ConnectionHelper con = new ConnectionHelper();
		Connection connection;
		Select select = new Select();
		connection = con.open();
		ResultSet rs = null;
		
		rs = select.selectWithParams(SQL.PRS_SNAPSHOT,ci, connection);
		while(rs.next()){
			probCount.add(rs.getInt(1));
			month.add(rs.getString(3)+"-"+rs.getString(2));
		}
		
		localBean.setMonth(month);
		localBean.setProbCount(probCount);
		
		rs = select.selectWithParams(SQL.PR_PROACTIVE_COUNT, ci, connection);
		while(rs.next()){
			localBean.setProactiveCount(rs.getInt(1));
		}
		rs = select.selectWithParams(SQL.PR_REACTIVE_COUNT, ci, connection);
		while(rs.next()){
			localBean.setReactiveCount(rs.getInt(1));
		}
		rs = select.selectWithParams(SQL.PR_RC_KNOWN_COUNT, ci, connection);
		while(rs.next()){
			localBean.setRcaKnown(rs.getInt(1));
		}
		rs = select.selectWithParams(SQL.PR_RC_UNKNOWN_COUNT, ci, connection);
		while(rs.next()){
			localBean.setRcaUnknown(rs.getInt(1));
		}
		
		rs = select.selectWithParams(SQL.PR_PROACTIVE_DETAIL, ci, connection);
		while(rs.next()){
			incBean = new Incident();
			incBean.setId(rs.getString(1));
			incBean.setTitle(rs.getString(2));
			incBean.setStatus(rs.getString(3));
			incBean.setCi(rs.getString(4));
			incBean.setReportedDate(rs.getString(5));
			incBean.setPhase(rs.getString(6));
			incBean.setSmGroup(rs.getString(7));
			proactiveList.add(incBean);
		}
		
		localBean.setProactiveList(proactiveList);
		
		rs = select.selectWithParams(SQL.PR_REACTIVE_DETAIL, ci, connection);
		while(rs.next()){
			incBean = new Incident();
			incBean.setId(rs.getString(1));
			incBean.setTitle(rs.getString(2));
			incBean.setStatus(rs.getString(3));
			incBean.setCi(rs.getString(4));
			incBean.setReportedDate(rs.getString(5));
			incBean.setPhase(rs.getString(6));
			incBean.setSmGroup(rs.getString(7));
			reactiveList.add(incBean);
		}
		
		localBean.setReactiveList(reactiveList);
		
		rs = select.selectWithParams(SQL.PR_RC_KNOWN_DETAIL, ci, connection);
		while(rs.next()){
			incBean = new Incident();
			incBean.setId(rs.getString(1));
			incBean.setTitle(rs.getString(2));
			incBean.setStatus(rs.getString(3));
			incBean.setCi(rs.getString(4));
			incBean.setReportedDate(rs.getString(5));
			incBean.setPhase(rs.getString(6));
			incBean.setSmGroup(rs.getString(7));
			rcaKnownList.add(incBean);
		}
		
		localBean.setRcaKnownList(rcaKnownList);
		
		rs = select.selectWithParams(SQL.PR_RC_UNKNOWN_DETAIL, ci, connection);
		while(rs.next()){
			incBean = new Incident();
			incBean.setId(rs.getString(1));
			incBean.setTitle(rs.getString(2));
			incBean.setStatus(rs.getString(3));
			incBean.setCi(rs.getString(4));
			incBean.setReportedDate(rs.getString(5));
			incBean.setPhase(rs.getString(6));
			incBean.setSmGroup(rs.getString(7));
			rcaUnknownList.add(incBean);
		}
		
		localBean.setRcaUnknownList(rcaUnknownList);
		
		
		
		
		return localBean;
	}
	
	public ProblemDetails getProblemDetailsSecond(String ci) throws Exception{
		
		ConnectionHelper con = new ConnectionHelper();
		Connection connection;
		Select select = new Select();
		connection = con.open();
		ResultSet rs = null;
		PhaseBean phaseSetter;
		Incident incSetter;
		ProblemDetails probSetter= new ProblemDetails();
		 List<PhaseBean> graphData = new ArrayList<PhaseBean>();
		 List<Integer> ageingProblemCount = new ArrayList<Integer>();
		 List<Incident> detail30 = new ArrayList<Incident>();
		 List<Incident> detail90 = new ArrayList<Incident>();
		 List<Incident> detail180 = new ArrayList<Incident>();
		 List<Incident> identifyProblem = new ArrayList<Incident>();
		 List<Incident> investigateProblem = new ArrayList<Incident>();
		 List<Incident> nKnownErrorInvestigation = new ArrayList<Incident>();
		 List<Incident> detailOver360 = new ArrayList<Incident>();
		 List<Incident> nKnowErrorSolApproval = new ArrayList<Incident>();
		 List<Incident> nRCApproval = new ArrayList<Incident>();
		 List<String> phaseName = new ArrayList<String>();
		 
		 rs = select.selectWithParams(SQL.PR_PHASE_COUNT, ci, connection);
		 while(rs.next()){
			 phaseSetter = new PhaseBean();
			 phaseSetter.setPhase(rs.getString(2));
			 phaseName.add(rs.getString(2));
			 phaseSetter.setPhaseCount(rs.getInt(1));
			 graphData.add(phaseSetter);
		 }
		 
		 probSetter.setGraphData(graphData);
		 
		 rs = select.selectWithParams(SQL.PR_30DAYS_COUNT, ci, connection);
		 while(rs.next()){
			ageingProblemCount.add(rs.getInt(1)); 
		 }
		 rs = select.selectWithParams(SQL.PR_90DAYS_COUNT, ci, connection);
		 while(rs.next()){
			ageingProblemCount.add(rs.getInt(1)); 
		 }
		 rs = select.selectWithParams(SQL.PR_1800DAYS_COUNT, ci, connection);
		 while(rs.next()){
			ageingProblemCount.add(rs.getInt(1)); 
		 }
		 rs = select.selectWithParams(SQL.PR_OVER180_COUNT, ci, connection);
		 while(rs.next()){
			ageingProblemCount.add(rs.getInt(1)); 
		 }
		 
		 probSetter.setAgeingProblemCount(ageingProblemCount);
		 
		 rs = select.selectWithParams(SQL.PR_30DAYS_DETAIL, ci, connection);
		 while(rs.next()){
			 incSetter = new Incident();
			 incSetter.setId(rs.getString(1));
			 incSetter.setTitle(rs.getString(2));
			 incSetter.setStatus(rs.getString(3));
			 incSetter.setCi(rs.getString(4));
			 incSetter.setReportedDate(rs.getString(5));
			 incSetter.setPhase(rs.getString(6));
			 incSetter.setSmGroup(rs.getString(7));
			 detail30.add(incSetter);
			 
		 }		 
		 probSetter.setDetail30(detail30);
		 
		 rs = select.selectWithParams(SQL.PR_90DAYS_DETAIl, ci, connection);
		 while(rs.next()){
			 incSetter = new Incident();
			 incSetter.setId(rs.getString(1));
			 incSetter.setTitle(rs.getString(2));
			 incSetter.setStatus(rs.getString(3));
			 incSetter.setCi(rs.getString(4));
			 incSetter.setReportedDate(rs.getString(5));
			 incSetter.setPhase(rs.getString(6));
			 incSetter.setSmGroup(rs.getString(7));
			 detail90.add(incSetter);
			 
		 }		 
		 probSetter.setDetail90(detail90);
		 
		 rs = select.selectWithParams(SQL.PR_1800DAYS_DETAIL, ci, connection);
		 while(rs.next()){
			 incSetter = new Incident();
			 incSetter.setId(rs.getString(1));
			 incSetter.setTitle(rs.getString(2));
			 incSetter.setStatus(rs.getString(3));
			 incSetter.setCi(rs.getString(4));
			 incSetter.setReportedDate(rs.getString(5));
			 incSetter.setPhase(rs.getString(6));
			 incSetter.setSmGroup(rs.getString(7));
			 detail180.add(incSetter);
			 
		 }		 
		 probSetter.setDetail180(detail180);
		 
		 rs = select.selectWithParams(SQL.PR_OVER180_DETAIL, ci, connection);
		 while(rs.next()){
			 incSetter = new Incident();
			 incSetter.setId(rs.getString(1));
			 incSetter.setTitle(rs.getString(2));
			 incSetter.setStatus(rs.getString(3));
			 incSetter.setCi(rs.getString(4));
			 incSetter.setReportedDate(rs.getString(5));
			 incSetter.setPhase(rs.getString(6));
			 incSetter.setSmGroup(rs.getString(7));
			 detailOver360.add(incSetter);
			 
		 }		 
		 probSetter.setDetailOver360(detailOver360);
		 
		 rs = select.selectWithParams(SQL.PR_PHASE_IDENTIFY, ci, connection);
		 while(rs.next()){
			 incSetter = new Incident();
			 incSetter.setId(rs.getString(1));
			 incSetter.setTitle(rs.getString(2));
			 incSetter.setStatus(rs.getString(3));
			 incSetter.setCi(rs.getString(4));
			 incSetter.setReportedDate(rs.getString(5));
			 incSetter.setPhase(rs.getString(6));
			 incSetter.setSmGroup(rs.getString(7));
			 identifyProblem.add(incSetter);
			 
		 }		 
		 probSetter.setIdentifyProblem(identifyProblem);
		 
		 rs = select.selectWithParams(SQL.PR_PHASE_INVESTIGATE, ci, connection);
		 while(rs.next()){
			 incSetter = new Incident();
			 incSetter.setId(rs.getString(1));
			 incSetter.setTitle(rs.getString(2));
			 incSetter.setStatus(rs.getString(3));
			 incSetter.setCi(rs.getString(4));
			 incSetter.setReportedDate(rs.getString(5));
			 incSetter.setPhase(rs.getString(6));
			 incSetter.setSmGroup(rs.getString(7));
			 investigateProblem.add(incSetter);
			 
		 }		 
		 probSetter.setInvestigateProblem(investigateProblem);
		 
		 rs = select.selectWithParams(SQL.PR_PHASE_KNOWNERR, ci, connection);
		 while(rs.next()){
			 incSetter = new Incident();
			 incSetter.setId(rs.getString(1));
			 incSetter.setTitle(rs.getString(2));
			 incSetter.setStatus(rs.getString(3));
			 incSetter.setCi(rs.getString(4));
			 incSetter.setReportedDate(rs.getString(5));
			 incSetter.setPhase(rs.getString(6));
			 incSetter.setSmGroup(rs.getString(7));
			 nKnownErrorInvestigation.add(incSetter);
			 
		 }		 
		 probSetter.setnKnownErrorInvestigation(nKnownErrorInvestigation);
		 
		 rs = select.selectWithParams(SQL.PR_PHASE_KNOWNERR_APPROVAL, ci, connection);
		 while(rs.next()){
			 incSetter = new Incident();
			 incSetter.setId(rs.getString(1));
			 incSetter.setTitle(rs.getString(2));
			 incSetter.setStatus(rs.getString(3));
			 incSetter.setCi(rs.getString(4));
			 incSetter.setReportedDate(rs.getString(5));
			 incSetter.setPhase(rs.getString(6));
			 incSetter.setSmGroup(rs.getString(7));
			 nKnowErrorSolApproval.add(incSetter);
			 
		 }		 
		 probSetter.setnKnowErrorSolApproval(nKnowErrorSolApproval);
		 
		 rs = select.selectWithParams(SQL.PR_PHASE_RCAPPROVAL, ci, connection);
		 while(rs.next()){
			 incSetter = new Incident();
			 incSetter.setId(rs.getString(1));
			 incSetter.setTitle(rs.getString(2));
			 incSetter.setStatus(rs.getString(3));
			 incSetter.setCi(rs.getString(4));
			 incSetter.setReportedDate(rs.getString(5));
			 incSetter.setPhase(rs.getString(6));
			 incSetter.setSmGroup(rs.getString(7));
			 nRCApproval.add(incSetter);
			 
		 }		 
		 probSetter.setnRCApproval(nRCApproval);
		 
		 
		 
		 return probSetter;
	}

}

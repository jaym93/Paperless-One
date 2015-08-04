package com.paperless.process.executors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paperless.beans.Incident;
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

}

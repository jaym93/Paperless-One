package com.paperless.process.executors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paperless.beans.Incident;import com.paperless.beans.Problems;
import com.paperless.connections.ConnectionHelper;
import com.paperless.connections.SQL;
import com.paperless.connections.Select;

public class ProblemPageExecuter {
	
	public Problems getProblems() throws Exception{
		Problems localBean = new Problems();
		Incident prbSetter;
		List<Incident> prbList = new ArrayList<Incident>();
		List<Integer> prbCount = new ArrayList<Integer>();
		List<String> month = new ArrayList<String>();
		ConnectionHelper con = new ConnectionHelper();
		Connection connection;
		Select select = new Select();
		connection = con.open();
		ResultSet rs = null;
		
		rs = select.runCommandNoParams(SQL.PRS_MONTH_DETAIL, connection);
		while(rs.next()){
			prbSetter = new Incident();
			prbSetter.setId(rs.getString(1));
			prbSetter.setTitle(rs.getString(2));
			prbSetter.setPhase(rs.getString(3));
			prbSetter.setUrgency(rs.getString(4));
			prbSetter.setCi(rs.getString(5));
			prbSetter.setReportedDate(rs.getString(6));
			prbList.add(prbSetter);
			
			
		}
		localBean.setProbList(prbList);
		rs = select.selectWithParams(SQL.PRS_SNAPSHOT	,"PINS%", connection);
		while(rs.next()){
			prbCount.add(rs.getInt(1));
			month.add(rs.getString(3)+"-"+rs.getString(2));
		}
		
		localBean.setMonth(month);
		localBean.setProbCount(prbCount);
		return localBean;
		}

}

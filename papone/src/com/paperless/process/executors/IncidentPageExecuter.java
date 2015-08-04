package com.paperless.process.executors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paperless.beans.Incident;
import com.paperless.beans.IncidentMain;
import com.paperless.connections.ConnectionHelper;
import com.paperless.connections.SQL;
import com.paperless.connections.Select;

public class IncidentPageExecuter {
	
	public List<Incident> getIncidents() throws Exception{
	Incident localBean ;
	List<Incident> incList = new ArrayList<Incident>();
	ConnectionHelper con = new ConnectionHelper();
	Connection connection;
	Select select = new Select();
	connection = con.open();
	ResultSet rs = null;
	
	rs = select.runCommandNoParams(SQL.INCIDENTS_OPEN_DETAIL, connection);
	while(rs.next()){
		localBean = new Incident();
		localBean.setId(rs.getString(1));
		localBean.setTitle(rs.getString(2));
		localBean.setCi(rs.getString(3));
		localBean.setUrgency(rs.getString(4));
		localBean.setStatus(rs.getString(5));
		localBean.setSpecialist(rs.getString(6));
		localBean.setReportedDate(rs.getString(7));
		localBean.setTargetDate(rs.getString(8));
		incList.add(localBean);
	}
	
	
	return incList;
	}
	
	public IncidentMain getSnapSHotDetails() throws Exception{
		IncidentMain mainBean = new IncidentMain();
		List<Integer> snapShot = new ArrayList<Integer>();
		List<String> monthYear = new ArrayList<String>();
		ConnectionHelper con = new ConnectionHelper();
		Connection connection;
		Select select = new Select();
		connection = con.open();
		ResultSet rs = null;
		
		rs = select.runCommandNoParams(SQL.INCIDENTS_SNAPSHOT, connection);
		while(rs.next()){
			snapShot.add(rs.getInt(1));
			monthYear.add(rs.getString(3)+"-"+rs.getString(2));
			
		}
		mainBean.setIncCount(snapShot);
		mainBean.setMonth(monthYear);
		return mainBean;
		
	}

}


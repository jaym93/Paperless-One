package com.paperless.process.executors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paperless.beans.FrMain;
import com.paperless.beans.Fulfillments;
import com.paperless.beans.IncidentMain;
import com.paperless.connections.ConnectionHelper;
import com.paperless.connections.SQL;
import com.paperless.connections.Select;

public class FulfillmentPageExecuter {

	public List<Fulfillments> getFRS() throws Exception{
		Fulfillments localBean ;
		List<Fulfillments> incList = new ArrayList<Fulfillments>();
		ConnectionHelper con = new ConnectionHelper();
		Connection connection;
		Select select = new Select();
		connection = con.open();
		ResultSet rs = null;
		
		rs = select.runCommandNoParams(SQL.FR_OPEN_DETAIL, connection);
		while(rs.next()){
			localBean = new Fulfillments();
			localBean.setId(rs.getString(1));
			localBean.setTitle(rs.getString(2));
			localBean.setCi(rs.getString(3));			
			localBean.setStatus(rs.getString(4));
			localBean.setSpecialist(rs.getString(5));
			localBean.setRequestDate(rs.getString(6));
			localBean.setTargetDate(rs.getString(7));
			incList.add(localBean);
		}
		
		
		return incList;
		}
	
	public FrMain getFrSnapSHotDetails() throws Exception{
		FrMain mainBean = new FrMain();
		List<Integer> snapShot = new ArrayList<Integer>();
		List<String> monthYear = new ArrayList<String>();
		ConnectionHelper con = new ConnectionHelper();
		Connection connection;
		Select select = new Select();
		connection = con.open();
		ResultSet rs = null;
		
		rs = select.runCommandNoParams(SQL.FRS_SNAPSHOT, connection);
		while(rs.next()){
			snapShot.add(rs.getInt(1));
			monthYear.add(rs.getString(3)+"-"+rs.getString(2));
			
		}
		mainBean.setFrCount(snapShot);
		mainBean.setMonth(monthYear);
		return mainBean;
		
	}
}

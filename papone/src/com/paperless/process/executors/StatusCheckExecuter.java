package com.paperless.process.executors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import com.paperless.beans.Const;
import com.paperless.beans.CwttQdRepo;
import com.paperless.connections.ConnectionHelper;
import com.paperless.connections.SQL;
import com.paperless.connections.Select;



public class StatusCheckExecuter  {
	
	public CwttQdRepo checkStatus(String ci, int id) throws Exception{
		CwttQdRepo repoObj = new CwttQdRepo();		
		int critical = 0;
		int high =0;
		int ticketCount =0; 
		int closedCount =0;
		int cwttCount =0;
		int qd =0;
		double percentageCwtt = 0;
		ConnectionHelper con = new ConnectionHelper();
		Connection connection;
		connection = con.open();
		ResultSet rs = null;
		Select select = new Select();
		
		//Audit Checks
		rs = select.selectWithParams(SQL.STATUS_CRITICAL, ci, connection);
		while(rs.next()){
			critical = rs.getInt(1);
		}
		rs = select.selectWithParams(SQL.STATUS_HIGH, ci, connection);
		while(rs.next()){
			high = rs.getInt(1);
		}
		rs = select.selectWithParams(SQL.STATUS_OPEN, ci, connection);
		while(rs.next()){
			ticketCount = rs.getInt(1);
		}
		if(critical > 0|| high >= 3){
			repoObj.setStatusClass(Const.STATUS_INDICATOR_RED);
						
			
		}else if(high >1 || ticketCount >5 ){
			repoObj.setStatusClass(Const.STATUS_INDICATOR_YELLOW);
						
			
		}else{
			repoObj.setStatusClass(Const.STATUS_INDICATOR_GREEN);
			
		}
		rs = select.selectWithParams(SQL.CWTT_CLOSED, ci, connection);
		while(rs.next()){
			closedCount = rs.getInt(1);
			
		}
		rs = select.selectWithParams(SQL.CWTT_SLA, ci, connection);
		while(rs.next()){
			cwttCount = rs.getInt(1);
			
		}
		percentageCwtt = (double)((float)cwttCount/(float)closedCount)*100;
		
			if(percentageCwtt > 90){
				DecimalFormat df = new DecimalFormat("###.##");
				repoObj.setCwttNum(df.format((int)percentageCwtt));
				repoObj.setCwttClass(Const.PIECHART_CLASS_GREEN);
			}else if(percentageCwtt > 80){
				DecimalFormat df = new DecimalFormat("###.##");
				repoObj.setCwttNum(df.format((int)percentageCwtt));				
				repoObj.setCwttClass(Const.PIECHART_CLASS_YELLOW);
			}else{
				DecimalFormat df = new DecimalFormat("###.##");
				repoObj.setCwttNum(df.format((int)percentageCwtt));				
				repoObj.setCwttClass(Const.PIECHART_CLASS_RED);
			}
			rs = select.runCommandNoParams(SQL.QD_STATUS, connection);
			while(rs.next()){
				qd = rs.getInt(id);
				
			}
			if(qd >=95 ){
				repoObj.setQdNum((int)qd);
				repoObj.setQdClass(Const.PIECHART_CLASS_GREEN);
			}else if(qd >85){
				repoObj.setQdNum((int)qd);
				repoObj.setQdClass(Const.PIECHART_CLASS_YELLOW);
			}else{
				repoObj.setQdNum((int)qd);
				repoObj.setQdClass(Const.PIECHART_CLASS_RED);
			}
			
		return repoObj;
	}

}

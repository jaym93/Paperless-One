package com.paperless.process;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paperless.beans.CalenderData;
import com.paperless.beans.Const;
import com.paperless.beans.IndexPage;
import com.paperless.beans.topIssues;
import com.paperless.connections.ConnectionHelper;
import com.paperless.connections.SQL;
import com.paperless.connections.Select;

public class ExhibitPageProcess {
	
	public IndexPage getExhibitPage() throws Exception{
		
			ConnectionHelper con = new ConnectionHelper();
			Connection connection;
			connection = con.open();
			ResultSet rs = null;
			List<topIssues> topIssue = new ArrayList<topIssues>();
			List<CalenderData> cal = new ArrayList<CalenderData>();
			List<CalenderData> calArr = new ArrayList<CalenderData>();
			topIssues t ;
			CalenderData ca;
			IndexPage i = new IndexPage();
			Select select = new Select();
			rs = select.runCommandNoParams(SQL.INCIDENTS_OPEN_TODAY, connection);
			while(rs.next()){
				i.setImToday(rs.getString(1));			
			}
			rs = select.runCommandNoParams(SQL.FRS_OPEN_TODAY, connection);
			while(rs.next()){
				i.setFrToday(rs.getString(1));			
			}
			rs = select.runCommandNoParams(SQL.IMS_MONTH, connection);
			while(rs.next()){
				i.setImMonth(rs.getString(1));			
			}
			rs = select.runCommandNoParams(SQL.FRS_MONTH, connection);
			while(rs.next()){
				i.setFrMonth(rs.getString(1));			
			}
			rs = select.runCommandNoParams(SQL.CRS_MONTH, connection);
			while(rs.next()){
				i.setCrMonth(rs.getString(1));			
			}
			rs = select.runCommandNoParams(SQL.PRS_MONTH, connection);
			while(rs.next()){
				i.setPrMonth(rs.getString(1));			
			}
			
			ResultSet result = select.runCommandNoParams(SQL.TOP_INCIDENTS, connection);
			while(result.next()){
				t = new topIssues();
				t.setIm(result.getString(1).toString());
				t.setPriority(result.getString(4).toString());
				t.setStatus(result.getString(5).toString());
				t.setTitle(result.getString(2).toString());
				t.setAppName(result.getString(3).toString());
				topIssue.add(t);	
							
			}
			i.setTopIssueList(topIssue);
			
			
			
			rs = select.runCommandNoParams(SQL.CR_CALENDAR, connection);
			while(rs.next()){
				ca = new CalenderData();
				if(rs.getString(3) != null){
				String cat = rs.getString(5);
				if(cat.contains("Emergency")){
					ca.setClassName(Const.CAL_CLASS_NAME_ORANGE);
				}else if(cat.contains("Expedited")){
					ca.setClassName(Const.CAL_CLASS_NAME_BLUE);
				}else{
					
					ca.setClassName(Const.CAL_CLASS_NAME_GREEN);
				}	
				String cid = rs.getString(1);
				String cTilte = rs.getString(2);
				ca.setTextBody(cTilte);
				ca.setTitle(cid);
				ca.setChangeType(cat);
				ca.setStart(rs.getString(3));
				ca.setEnd(rs.getString(4));
				ca.setUrl(Const.CAL_LINK+cid);
				cal.add(ca);
				}
				else{
					//System.out.println("NULL FOUND");
				}
			}
			
			i.setCalArray(cal);
			
			rs = select.runCommandNoParams(SQL.CR_PAGE, connection);
			while(rs.next()){
				ca = new CalenderData();
				if(rs.getString(3) != null){
				String cat = rs.getString(5);
				if(cat.contains("Emergency")){
					ca.setClassName(Const.CAL_CLASS_NAME_ORANGE);
				}else if(cat.contains("Expedited")){
					ca.setClassName(Const.CAL_CLASS_NAME_BLUE);
				}else{
					
					ca.setClassName(Const.CAL_CLASS_NAME_GREEN);
				}	
				String cid = rs.getString(1);
				String cTilte = rs.getString(2);
				ca.setTextBody(cTilte);
				ca.setTitle(cid);
				ca.setChangeType(cat);
				ca.setStart(rs.getString(3));
				ca.setEnd(rs.getString(4));
				ca.setUrl(Const.CAL_LINK+cid);
				calArr.add(ca);
				}
				else{
					//System.out.println("NULL FOUND");
				}
			}
			
			i.setCalPresentMonthArray(calArr);
			return i;
		
	}

}

package com.paperless.process.executors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paperless.beans.AppPage;
import com.paperless.beans.CalenderData;
import com.paperless.beans.Const;
import com.paperless.beans.Fulfillments;
import com.paperless.beans.SpocDetails;
import com.paperless.beans.ticketDetails;
import com.paperless.beans.topIssues;
import com.paperless.connections.ConnectionHelper;
import com.paperless.connections.SQL;
import com.paperless.connections.Select;

public class AppPageExecuter {
	
	public AppPage getData(String ci) throws Exception {
		
		AppPage appData = new AppPage();
		ticketDetails ticketInfo;
		CalenderData ca;
		List<CalenderData> cal = new ArrayList<CalenderData>();
		topIssues topList;
		List<ticketDetails> ticketInfoListCriticalIncident = new ArrayList<ticketDetails>();
		List<ticketDetails> ticketInfoListMajorIncident = new ArrayList<ticketDetails>();
		List<ticketDetails> ticketInfoListMinorIncident = new ArrayList<ticketDetails>();
		List<ticketDetails> ticketInfoListCriticalProblem = new ArrayList<ticketDetails>();
		List<ticketDetails> ticketInfoListMinorProblem = new ArrayList<ticketDetails>();
		List<ticketDetails> ticketInfoListMajorProblem = new ArrayList<ticketDetails>();
		List<topIssues> topIssuesList = new ArrayList<topIssues>();
		
		ConnectionHelper con = new ConnectionHelper();
		Connection connection;
		Select select = new Select();
		connection = con.open();
		ResultSet rs = null;
		
		rs = select.selectWithParams(SQL.APP_CR_CALENDAR,ci, connection);
		
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
			ca.setStart(rs.getString(3));
			ca.setEnd(rs.getString(4));
			ca.setUrl(Const.CAL_LINK+cid);
			cal.add(ca);
			}
			else{
				//System.out.println("NULL FOUND");
			}
		}
		
		appData.setCalArray(cal);
		
		rs = select.selectWithParams(SQL.APP_STATUS_CRITICAL_COUNT, ci, connection);
		while(rs.next()){
			
			appData.setCriticalIncident(rs.getInt(1));
		}
		rs = select.selectWithParams(SQL.APP_STATUS_HIGH_COUNT, ci, connection);
		while(rs.next()){
			
			appData.setMajorIncident(rs.getInt(1));
		}
		rs = select.selectWithParams(SQL.APP_STATUS_MINOR_COUNT, ci, connection);
		while(rs.next()){
			
			appData.setMinorIncident(rs.getInt(1));
		}
		rs = select.selectWithParams(SQL.APP_STATUS_CRITICAL_PROBLEM_COUNT, ci, connection);
		while(rs.next()){
			
			appData.setCriticalProblem(rs.getInt(1));
		}
		rs = select.selectWithParams(SQL.APP_STATUS_HIGH_PROBLEM_COUNT, ci, connection);
		while(rs.next()){
			
			appData.setMajorProblem(rs.getInt(1));
		}
		rs = select.selectWithParams(SQL.APP_STATUS_MINOR_PROBLEM_COUNT, ci, connection);
		while(rs.next()){
			
			appData.setMinorProblem(rs.getInt(1));
		}
		
		//Critical Incident Details
		
		rs = select.selectWithParams(SQL.APP_STATUS_CRITICAL_DETAIL, ci, connection);
		while(rs.next()){
			ticketInfo = new ticketDetails();
			ticketInfo.setId(rs.getString(1));
			ticketInfo.setTitle(rs.getString(2));
			ticketInfo.setSpecialist(rs.getString(3));
			ticketInfo.setAppName(rs.getString(4));
			ticketInfo.setStatus(rs.getString(5));
			ticketInfo.setReportedDate(rs.getString(6));
			ticketInfoListCriticalIncident.add(ticketInfo);
		}
		
		appData.setTicketDetailCritical(ticketInfoListCriticalIncident);
		
		//Major Incident Details
		rs = select.selectWithParams(SQL.APP_STATUS_HIGH_DETAIL, ci, connection);
		while(rs.next()){
			ticketInfo = new ticketDetails();
			ticketInfo.setId(rs.getString(1));
			ticketInfo.setTitle(rs.getString(2));
			ticketInfo.setSpecialist(rs.getString(3));
			ticketInfo.setAppName(rs.getString(4));
			ticketInfo.setStatus(rs.getString(5));
			ticketInfo.setReportedDate(rs.getString(6));
			ticketInfoListMajorIncident.add(ticketInfo);
		}
		
		appData.setTicketDetailMajor(ticketInfoListMajorIncident);
		
		//Minor Incident Details
		rs = select.selectWithParams(SQL.APP_STATUS_MINOR_DETAIL, ci, connection);
		while(rs.next()){
			ticketInfo = new ticketDetails();
			ticketInfo.setId(rs.getString(1));
			ticketInfo.setTitle(rs.getString(2));
			ticketInfo.setSpecialist(rs.getString(3));
			ticketInfo.setAppName(rs.getString(4));
			ticketInfo.setStatus(rs.getString(5));
			ticketInfo.setReportedDate(rs.getString(6));
			ticketInfoListMinorIncident.add(ticketInfo);
		}
		
		appData.setTicketDetailMinor(ticketInfoListMinorIncident);
		
		//Critical Problem Details
		rs = select.selectWithParams(SQL.APP_STATUS_CRITICAL_PROBLEM_DETAIL, ci, connection);
		while(rs.next()){
			ticketInfo = new ticketDetails();
			ticketInfo.setId(rs.getString(1));
			ticketInfo.setTitle(rs.getString(2));
			ticketInfo.setSpecialist(rs.getString(3));
			ticketInfo.setAppName(rs.getString(4));
			ticketInfo.setStatus(rs.getString(5));
			ticketInfo.setReportedDate(rs.getString(6));
			ticketInfoListCriticalProblem.add(ticketInfo);
		}
		
		appData.setProblemDetailCritical(ticketInfoListCriticalProblem);
		
		//Major Problem Details
		
		rs = select.selectWithParams(SQL.APP_STATUS_HIGH_PROBLEM_DETAIL, ci, connection);
		while(rs.next()){
			ticketInfo = new ticketDetails();
			ticketInfo.setId(rs.getString(1));
			ticketInfo.setTitle(rs.getString(2));
			ticketInfo.setSpecialist(rs.getString(3));
			ticketInfo.setAppName(rs.getString(4));
			ticketInfo.setStatus(rs.getString(5));
			ticketInfo.setReportedDate(rs.getString(6));
			ticketInfoListMajorProblem.add(ticketInfo);
		}
		
		appData.setProblemDetailMajor(ticketInfoListMajorProblem);
		
		rs = select.selectWithParams(SQL.APP_STATUS_MINOR_PROBLEM_DETAIL, ci, connection);
		while(rs.next()){
			ticketInfo = new ticketDetails();
			ticketInfo.setId(rs.getString(1));
			ticketInfo.setTitle(rs.getString(2));
			ticketInfo.setSpecialist(rs.getString(3));
			ticketInfo.setAppName(rs.getString(4));
			ticketInfo.setStatus(rs.getString(5));
			ticketInfo.setReportedDate(rs.getString(6));
			ticketInfoListMinorProblem.add(ticketInfo);
		}
		
		appData.setProblemDetailMinor(ticketInfoListMinorProblem);
		
		rs = select.selectWithParams(SQL.APP_TOP_INCIDENTS, ci, connection);
		while(rs.next()){
			topList = new topIssues();
			topList.setIm(rs.getString(1));
			topList.setTitle(rs.getString(2));
			topList.setAssigneeName(rs.getString(3));
			topList.setPriority(rs.getString(4));
			topIssuesList.add(topList);		
			
		}
		rs = select.selectWithParams(SQL.APP_SLA_TODAY, ci, connection);
		while(rs.next()){
			topList = new topIssues();
			topList.setIm(rs.getString(1));
			topList.setTitle(rs.getString(2));
			topList.setAssigneeName(rs.getString(3));
			topList.setPriority("SLA Today");
			topIssuesList.add(topList);
		}
		appData.setTopIssueList(topIssuesList);
		
		return appData;
	}
	
	public SpocDetails getSpocs(String ci) throws Exception{
		SpocDetails localBean = new SpocDetails();
		ConnectionHelper con = new ConnectionHelper();
		Connection connection;
		Select select = new Select();
		connection = con.open();
		ResultSet rs = null;
		
		rs = select.selectWithParams(SQL.APP_SPOC_DETAILS, ci, connection);
		while(rs.next()){
			localBean.setAppDesc(rs.getString(1));
			localBean.setSpocName(rs.getString(2));
			localBean.setSpocEmail(rs.getString(3));
			localBean.setAppPdl(rs.getString(4));
			localBean.setSpocLocation(rs.getString(5));
			localBean.setMailTo(rs.getString(6));
			
		}
		
		return localBean;
	}
	
	public List<Fulfillments> getFrDetails(String ci) throws Exception{
		
		Fulfillments setBean;
		List<Fulfillments> arrayFR = new ArrayList<Fulfillments>();
		ConnectionHelper con = new ConnectionHelper();
		Connection connection;
		Select select = new Select();
		connection = con.open();
		ResultSet rs = null;
		
		rs = select.selectWithParams(SQL.APP_FRS_OPEN_TODAY_DETAIL, ci, connection);
		while(rs.next()){
			setBean =  new Fulfillments();
			setBean.setId(rs.getString(1));
			setBean.setTitle(rs.getString(2));
			setBean.setStatus(rs.getString(3));
			setBean.setSpecialist(rs.getString(4));
			setBean.setRequestDate(rs.getString(5));
			setBean.setTargetDate(rs.getString(6));
			arrayFR.add(setBean);
			
		}
		
		return arrayFR;
		
	}
	
	public int getFrCount(String ci) throws Exception {
		ConnectionHelper con = new ConnectionHelper();
		Connection connection;
		Select select = new Select();
		connection = con.open();
		ResultSet rs = null;
		int count = 0;
		rs = select.selectWithParams(SQL.APP_FRS_OPEN_TODAY, ci, connection);
		while(rs.next()){
			count = rs.getInt(1);
		}
		
		return count;
		
	}

}

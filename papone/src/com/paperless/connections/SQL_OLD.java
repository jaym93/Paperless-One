package com.paperless.connections;

public class SQL_OLD {
	
	/*EXHIBIT PAGE QUERIES*/
	public static String INCIDENTS_OPEN_TODAY = "select count(*) from incident_master where status not like '%Closed%' AND status not like '%Resolved%' AND ci like '%PINS%'";
	public static String FRS_OPEN_TODAY = "select count(*) from fulfillment_master where status not like '%Fulfilled%' AND status not like '%Closed%' AND ci like '%PINS%'";
	public static String IMS_MONTH = "SELECT count(*) FROM incident_master WHERE STR_TO_DATE(reported_date,'%Y-%m-%d') BETWEEN DATE_FORMAT(NOW(), '%Y-%m-01 00:00:00') AND NOW() AND ci like '%PINS%'";
	public static String FRS_MONTH = "SELECT count(*) FROM fulfillment_master WHERE STR_TO_DATE(request_date,'%Y-%m-%d') BETWEEN DATE_FORMAT(NOW(), '%Y-%m-01 00:00:00') AND NOW() AND ci like '%PINS%'";
	public static String PRS_MONTH = "SELECT count(*) FROM problem_master WHERE STR_TO_DATE(open_date,'%Y-%m-%d') BETWEEN DATE_FORMAT(NOW(), '%Y-%m-01 00:00:00') AND NOW() AND ci like '%PINS%'";
	public static String CRS_MONTH = "SELECT count(*) FROM change_master WHERE STR_TO_DATE(open_date,'%Y-%m-%d') BETWEEN DATE_FORMAT(NOW(), '%Y-%m-01 00:00:00') AND NOW() AND ci like '%PINS%'";
	public static String TOP_INCIDENTS = "SELECT id, title, ci, urgency, status FROM incident_master WHERE (urgency like '%Critical%' or urgency like '%High%') AND (status not like '%Closed%' and status not like '%Resolved%') and ci like '%PINS%' order by urgency";
	public static String CR_CALENDAR = "SELECT id, title, case when STR_TO_DATE(planned_start, '%Y-%m-%d') = '0000-00-00' then null else STR_TO_DATE(planned_start, '%Y-%m-%d') end as start, case when STR_TO_DATE(planned_finish, '%Y-%m-%d') = '0000-00-00' then null else STR_TO_DATE(planned_finish, '%Y-%m-%d') end as finish, category FROM change_master WHERE status not like '%Draft%' and status not like '%Closed%'";
	
	/*STATUS QUERIES*/
	
	public static final String STATUS_CRITICAL ="select count(*) from incident_master where ci like ? and urgency like '%Critical%'and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String STATUS_HIGH ="select count(*) from incident_master where ci like ? and urgency like '%High%'and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String STATUS_OPEN ="select count(*) from incident_master where ci like ? and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String CWTT_CLOSED ="SELECT count(*) FROM `incident_master` WHERE status like '%Closed%' and STR_TO_DATE(lastupdate_date,'%Y-%m-%d') BETWEEN DATE_FORMAT(NOW(),'%Y-%m-01 00:00:00') AND NOW() AND CI like ?";
	public static String CWTT_SLA="SELECT count(*) FROM `incident_master` WHERE status like '%Closed%' and STR_TO_DATE(lastupdate_date,'%Y-%m-%d') BETWEEN DATE_FORMAT(NOW(),'%Y-%m-01 00:00:00') AND NOW() AND STR_TO_DATE(lastupdate_date,'%Y-%m-%d') < STR_TO_DATE(target_date,'%Y-%m-%d') AND CI like ?";
	public static String QD_STATUS ="select * from qd where slmid =1";
	
	//APPLICATION SERVICE
	
	public static String APP_TOP_INCIDENTS = "SELECT id, title, specialist, urgency FROM incident_master WHERE (urgency like '%Critical%' or urgency like '%High%') AND (status not like '%Closed%' and status not like '%Resolved%') and ci like ? order by urgency";
	public static String APP_SLA_TODAY = "SELECT id, title, specialist FROM incident_master WHERE  (status not like '%Closed%' and status not like '%Resolved%') AND DATE_FORMAT(target_date,'%Y-%m-%d') = CURDATE() and ci like ? ";
	public static String APP_STATUS_CRITICAL_COUNT ="select count(*) from incident_master where ci like ? and urgency like '%Critical%'and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String APP_STATUS_HIGH_COUNT ="select count(*) from incident_master where ci like ? and urgency like '%High%'and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String APP_STATUS_MINOR_COUNT ="select count(*) from incident_master where ci like ? and (urgency not like '%High%' and urgency not like '%Critical%')and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String APP_STATUS_CRITICAL_PROBLEM_COUNT ="select count(*) from problem_master where ci like ? and urgency like '%Critical%'and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String APP_STATUS_HIGH_PROBLEM_COUNT ="select count(*) from problem_master where ci like ? and urgency like '%High%'and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String APP_STATUS_MINOR_PROBLEM_COUNT ="select count(*) from problem_master where ci like ? and (urgency not like '%High%' and urgency not like '%Critical%')and (status not like '%Closed%' and status not like '%Resolved%')";
	
	public static String APP_STATUS_CRITICAL_DETAIL ="select id,title,specialist,ci,status,reported_date,target_date,lastupdate_date from incident_master where ci like ? and urgency like '%Critical%'and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String APP_STATUS_HIGH_DETAIL ="select id,title,specialist,ci,status,reported_date,target_date,lastupdate_date from incident_master where ci like ? and urgency like '%High%'and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String APP_STATUS_MINOR_DETAIL ="select id,title,specialist,ci,status,reported_date,target_date,lastupdate_date from incident_master where ci like ? and (urgency not like '%High%' and urgency not like '%Critical%')and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String APP_STATUS_CRITICAL_PROBLEM_DETAIL ="select id,title,owner,ci,status,open_date from problem_master where ci like ? and urgency like '%Critical%'and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String APP_STATUS_HIGH_PROBLEM_DETAIL ="select id,title,owner,ci,status,open_date from problem_master where ci like ? and urgency like '%High%'and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String APP_STATUS_MINOR_PROBLEM_DETAIL ="select id,title,owner,ci,status,open_date from problem_master where ci like ? and (urgency not like '%High%' and urgency not like '%Critical%')and (status not like '%Closed%' and status not like '%Resolved%')";
	public static String APP_CR_CALENDAR = "SELECT id, title, case when STR_TO_DATE(planned_start, '%Y-%m-%d') = '0000-00-00' then null else STR_TO_DATE(planned_start, '%Y-%m-%d') end as start, case when STR_TO_DATE(planned_finish, '%Y-%m-%d') = '0000-00-00' then null else STR_TO_DATE(planned_finish, '%Y-%m-%d') end as finish, category FROM change_master WHERE status not like '%Draft%' and status not like '%Closed%' and ci like ?";
	public static String APP_SPOC_DETAILS ="SELECT appdesc,spoc_name,spoc_email,pdl_email,spoc_timezone,mailto FROM `app_desc` WHERE ci=?";
	
	public static String INCIDENTS_OPEN_DETAIL = "select id,title,ci,urgency,status,specialist,reported_date,target_date from incident_master where status not like '%Closed%' AND status not like '%Resolved%' AND ci like '%PINS%'";
	public static String INCIDENTS_SNAPSHOT ="SELECT count(*), YEAR(i.reported_date), MONTHNAME(i.reported_date) from incident_master i where i.ci like'PINS%' group by YEAR(i.reported_date) DESC, MONTH(i.reported_date) DESC";
	public static String FR_OPEN_DETAIL = "select id,title,ci,status,specialist,request_date,target_date from fulfillment_master where status not like '%Closed%' AND status not like '%Fulfilled%' AND ci like '%PINS%'";
}


package com.paperless.process;
import com.paperless.beans.Const;
import com.paperless.beans.CwttQdRepo;
import com.paperless.beans.StatusIndicator;
import com.paperless.process.executors.StatusCheckExecuter;

public class StatusCwttProcess {
	
	public StatusIndicator checkStatus() throws Exception{
		StatusIndicator siObj = new StatusIndicator();
		CwttQdRepo statusObj = new CwttQdRepo();
		StatusCheckExecuter check = new StatusCheckExecuter();
		statusObj = check.checkStatus(Const.AUDIT_CI,Const.AUDIT);
		siObj.setAuditStatus(statusObj);
		statusObj = check.checkStatus(Const.BINDER_CI,Const.BINDER);
		siObj.setBinderStatus(statusObj);
		statusObj = check.checkStatus(Const.DASHBOARD_CI,Const.DASHBOARD);
		siObj.setDashboardStatus(statusObj);
		statusObj = check.checkStatus(Const.KNOW_CI,Const.KNOW);
		siObj.setKnowStatus(statusObj);
		statusObj = check.checkStatus(Const.MYC_CI,Const.MYC);
		siObj.setMycoachStatus(statusObj);
		statusObj = check.checkStatus(Const.ORDER_CI,Const.ORDER);
		siObj.setOrderStatus(statusObj);
		statusObj = check.checkStatus(Const.SEA_CI,Const.SEA);
		siObj.setSeaStatus(statusObj);
		statusObj = check.checkStatus(Const.VISIT_CI,Const.VISIT);
		siObj.setVisitStatus(statusObj);
		
		
		return siObj;
		
	}

}

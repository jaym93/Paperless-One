package com.paperless.process;

import java.util.ArrayList;
import java.util.List;

import com.paperless.beans.AppPage;
import com.paperless.beans.Fulfillments;
import com.paperless.beans.SpocDetails;
import com.paperless.process.executors.AppPageExecuter;

public class AppPageProcess {

	public AppPage getAppData(String ci) throws Exception{
		ci=ci+"%";
		List<Fulfillments> frArr = new ArrayList<Fulfillments>();
		
		AppPage appDataBean = new AppPage();
		AppPageExecuter app = new AppPageExecuter();	
		appDataBean=	app.getData(ci);
		frArr = app.getFrDetails(ci);
		appDataBean.setFrDetails(frArr);
		int count = app.getFrCount(ci);
		appDataBean.setFrCount(count);
		return appDataBean;
	}
	
	public SpocDetails getSpocData(String ci) throws Exception{
		
		SpocDetails spocBean = new SpocDetails();
		AppPageExecuter app = new AppPageExecuter();	
		spocBean=app.getSpocs(ci);
		return spocBean;
	}
}

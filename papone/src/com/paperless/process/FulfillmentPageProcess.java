package com.paperless.process;

import java.util.ArrayList;
import java.util.List;

import com.paperless.beans.FrMain;
import com.paperless.beans.Fulfillments;
import com.paperless.process.executors.FulfillmentPageExecuter;


public class FulfillmentPageProcess {
	public FrMain getFRData() throws Exception{
		FrMain localBean = new FrMain();
		List<Fulfillments> data = new ArrayList<Fulfillments>();
		FulfillmentPageExecuter exec = new FulfillmentPageExecuter();
		data = exec.getFRS();
		localBean = exec.getFrSnapSHotDetails();
		localBean.setIncidentList(data);
		
		return localBean;
	}

}

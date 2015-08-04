package com.paperless.process;

import java.util.ArrayList;
import java.util.List;

import com.paperless.beans.Incident;
import com.paperless.beans.IncidentMain;
import com.paperless.process.executors.IncidentPageExecuter;

public class IncidentPageProcess  {
	
	public IncidentMain getIncidentData() throws Exception{
		IncidentMain localBean = new IncidentMain();
		List<Incident> data = new ArrayList<Incident>();
		IncidentPageExecuter exec = new IncidentPageExecuter();
		data = exec.getIncidents();
		localBean= exec.getSnapSHotDetails();
		localBean.setIncidentList(data);
		
		
		return localBean;
	}
	

}

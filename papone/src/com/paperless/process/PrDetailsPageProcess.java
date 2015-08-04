package com.paperless.process;

import com.paperless.beans.ProblemDetails;
import com.paperless.beans.Problems;
import com.paperless.process.executors.PrDetailsPageExecuter;

public class PrDetailsPageProcess {
	PrDetailsPageExecuter exec;
	public Problems runPrPageExecutor(String ci) throws Exception {
		Problems localBean = new Problems();
		 exec = new PrDetailsPageExecuter();
		localBean = exec.getProblemDetailsFirst(ci);
		
		return localBean;
		
	}
	
	public ProblemDetails runPrPageExecutor2(String ci) throws Exception{
		
		ProblemDetails localBean = new ProblemDetails();
		exec = new PrDetailsPageExecuter();
		localBean = exec.getProblemDetailsSecond(ci);
		
		return localBean;
	}

}

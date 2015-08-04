package com.paperless.process;

import com.paperless.beans.Problems;
import com.paperless.process.executors.PrDetailsPageExecuter;

public class PrDetailsPageProcess {
	
	public Problems runPrPageExecutor(String ci) throws Exception {
		Problems localBean = new Problems();
		PrDetailsPageExecuter exec = new PrDetailsPageExecuter();
		localBean = exec.getProblemDetailsFirst(ci);
		
		return localBean;
		
	}

}

package com.paperless.process;

import com.paperless.beans.Problems;
import com.paperless.process.executors.ProblemPageExecuter;

public class ProblemPageProcess {
	
	public Problems getProbs() throws Exception{
		
		Problems probs = new Problems();
		ProblemPageExecuter exec = new ProblemPageExecuter();
		probs = exec.getProblems();
		
		return probs;
		}

}

package com.DS.utils.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.DS.utils.common.PythonByRuntime;
public class PYML implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		PythonByRuntime.runPython3("project.py");
		
	}

}

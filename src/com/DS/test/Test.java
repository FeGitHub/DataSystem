package com.DS.test;
import java.util.HashMap;
import java.util.Map;
import com.DS.utils.quartz.QuartzManager;
import com.DS.utils.quartz.jobs.RemindJob;
public class Test {	 
	 public static void main(String[] args) { 
		// QuartzManager.modifyJobTime("DBBackup", "DBBackupGroupName", "DBBackupTrigger", "DBBackupJobGroup", "0 59 17 * * ?");
		// QuartzManager.addJob("ee", "dfdf", "ff", "ff", RemindJob.class, "0 56 12 * * ?", "ff", map);
		 QuartzManager.modifyJobTime("ee", "dfdf", "ff", "ff", "0 0 9 * * ?","");
		//QuartzManager.removeJob("ee", "dfdf", "ff", "ff");
	 } 
}

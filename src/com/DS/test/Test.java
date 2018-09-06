package com.DS.test;

import com.DS.utils.quartz.QuartzManager;

public class Test {
	 
	 public static void main(String[] args) { 
		 QuartzManager.modifyJobTime("DBBackup", "DBBackupGroupName", "DBBackupTrigger", "DBBackupJobGroup", "0 59 17 * * ?");
	 } 
}

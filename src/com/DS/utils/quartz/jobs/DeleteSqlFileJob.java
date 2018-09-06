package com.DS.utils.quartz.jobs;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.DS.utils.CommonData;
import com.DS.utils.FileUtil;
/***
 * 
 * @author jeff
 * 定时删除不必要的数据库文件
 * 需求与信息提示：
 * 1.超过10天的文件将会被删除
 * 2.数据库备份文件名是备份时间
 */
public class DeleteSqlFileJob implements Job{
	 private static Logger logger = Logger.getLogger(DeleteSqlFileJob.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		delSqlFile();
	}
	public static void delSqlFile(){
		String path=CommonData.DBPath;//数据库备份存放的位置		
		ArrayList<String> pathlist=FileUtil.getFilePaths(path);//备份数据库文件夹下的所有备份sql文件
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		File tempfile;//用于在遍历过程中存放文件对象
		String tempFileName;
		long fileTime;//从sql文件名知道此备份文件产生的时间
		long diff;//备份sql与今天的时间差
		if(pathlist.size()<10){
			logger.info("数据库备份文件少于10份，不进行删除动作"); 
			return;
		}
		for(String tempPath:pathlist){//遍历备份的sql文件
			tempfile = new File(tempPath);
			tempFileName=tempfile.getName();
			tempFileName=tempFileName.substring(0, tempFileName.lastIndexOf("."));//去除后缀
			try {
				fileTime = sdf.parse(tempFileName).getTime();
				diff=now.getTime()-fileTime;
				diff=diff/(1000 * 60 * 60 * 24);//距离今天的天数
				if(diff>=10){
					if(FileUtil.deleteFile(tempPath)){
						logger.info(tempPath+"判定为不需要的数据库备份文件，成功删除");
					}else{
						logger.info(tempPath+"判定为不需要的数据库备份文件，删除失败");
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}			
	}	
	/* public static void main(String[] args){
		  delSqlFile();
	  }*/
}

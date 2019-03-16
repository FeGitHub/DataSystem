package com.DS.utils.quartz.jobs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.DS.utils.common.SystemUtil;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
/***
 * 备份数据库的任务类
 * @author jeff 
 *
 */
public class DBBackupJob implements Job{
	private static Logger logger = Logger.getLogger(DBBackupJob.class);
	//以下是数据库资源备份默认配置
	private static String hostIP="127.0.0.1";//MySQL数据库所在服务器地址IP
	private static String userName="root";//进入数据库所要的用户
	private static String password="root";//进入数据库所要的密码
	private static String savePath="C:/MysqlBackup";//数据库导出文件保存路径
	private static String fileName="";//数据库导出文件文件名
	private static String databaseName="pams";//要备份的数据库名称
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
			//重新加载数据库备份文件资源
		    Prop p =PropKit.use("config.properties");
			hostIP=p.get("hostIP");
		    savePath=p.get("DBPath");
		    userName=p.get("userName");
		    password=p.get("password");
		    databaseName=p.get("databaseName");		    
		    Date d = new Date();   
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        fileName= sdf.format(d)+".sql"; 
	        if(SystemUtil.isLinux()){//linux下的默认路径处理
	        	Prop linuxp =PropKit.use("linux.properties");
	        	savePath=linuxp.get("DBPath");
	        }
		try {
			if (exportDatabaseTool()) {
				logger.info("数据库备份成功！--备份文件地址为"+savePath);
			} else {
				logger.info("数据库备份失败！--备份文件地址为"+savePath);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/***
	 * 备份数据库
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean exportDatabaseTool() throws InterruptedException {
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {// 如果目录不存在
			saveFile.mkdirs();// 创建文件
		}
		if(!savePath.endsWith(File.separator)){
			savePath = savePath + File.separator;
		}		
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
			String str="mysqldump -h "+hostIP+" -u"+userName+" -p"+password+" "+databaseName;
			Process process = Runtime.getRuntime().exec(str);
			InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
			bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
            	printWriter.println(line);
            }
            printWriter.flush();
			if(process.waitFor() == 0){//0 表示线程正常终止
				
				return true;
			}
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static void main(String[] args){
		    Date d = new Date();   
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        fileName= sdf.format(d)+".sql";  
		try {
			if (exportDatabaseTool()) {
				System.out.println("success");
			} else {
				System.out.println("fail");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

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

import com.DS.utils.common.SecretUtil;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

/***
 * 备份数据库的任务类
 * 
 * @author jeff 要配置mysqldump的环境变量，配置成功后最好退出Eclipse
 * https://www.csdn.net/gather_27/MtTaMgwsNDg2Ny1ibG9n.html
 */
public class DBBackupJob implements Job {
	private static Logger logger = Logger.getLogger(DBBackupJob.class);
	// 以下是数据库资源备份默认配置
	private static String hostIP = "";// MySQL数据库所在服务器地址IP
	private static String userName = "";// 进入数据库所要的用户
	private static String password = "";// 进入数据库所要的密码
	private static String savePath = "";// 数据库导出文件保存路径
	private static String fileName = "";// 数据库导出文件文件名
	private static String databaseName = "";// 要备份的数据库名称

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// 重新加载数据库备份文件资源
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		fileName = sdf.format(d) + ".sql";
		try {
			if (exportDatabaseTool()) {
				logger.info("数据库备份成功！--备份文件地址为" + savePath);
			} else {
				logger.info("数据库备份失败！--备份文件地址为" + savePath+",请检查是否配置了mysqldump的环境变量");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 初始化配置
	 */
	public static void initConfig() {
		Prop p = PropKit.use("config.properties");
		hostIP = p.get("hostIP");
		savePath = p.get("MysqlBackupPath");
		userName = p.get("userName");
		password = SecretUtil.decrypt(p.get("password"));
		databaseName = p.get("databaseName");
	}

	/***
	 * 备份数据库
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean exportDatabaseTool() throws InterruptedException {
		initConfig();
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {// 如果目录不存在
			saveFile.mkdirs();// 创建文件
		}
		if (!savePath.endsWith(File.separator)) {
			savePath = savePath + File.separator;
		}
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
			String str = "mysqldump -h " + hostIP + " -u" + userName + " -p" + password + " " + databaseName;
			// logger.info(str);
			Process process = Runtime.getRuntime().exec(str);
			InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				printWriter.println(line);
			}
			printWriter.flush();
			if (process.waitFor() == 0) {// 0 表示线程正常终止
				return true;
			}
		} catch (IOException e) {
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

	/***
	 * 测试是否能正常备份
	 */
	public static void main(String[] args) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		fileName = sdf.format(d) + ".sql";
		try {
			if (exportDatabaseTool()) {
				logger.info("数据库备份成功！--备份文件地址为" + savePath);
			} else {
				logger.info("数据库备份失败！--备份文件地址为" + savePath);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

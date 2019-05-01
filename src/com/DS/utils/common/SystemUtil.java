package com.DS.utils.common;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

/*****
 * 
 * @author jeff
 * 系统工具类
 */
public class SystemUtil {
	 public static void main(String[] args){
		 if(isLinux()){
			 System.out.println("yes");
		 }else{
			 System.out.println("no"); 
		 }
	 }
	 /****
	  * 判断当前的系统是否是linux系统
	  * @return
	  */
	public static boolean isLinux() { 
		return System.getProperty("os.name").toLowerCase().indexOf("linux") >= 0; 
	}
	
	/*****
	 * 获取系统的数据库备份文件路径
	 * @return
	 */
	public static String getMysqlBackupPath(){
		String MysqlBackupPath=null;
		if(isLinux()){
			MysqlBackupPath=PropKit.use("linux.properties").get("MysqlBackupPath");
		}else{
			MysqlBackupPath=PropKit.use("config.properties").get("MysqlBackupPath");//数据库备份存放的位置	
		}
		return MysqlBackupPath;
	}
}

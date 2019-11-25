package com.DS.utils.common;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/****
 * 
 * @author jeff
 * 部署环境监测
 * Environmental Monitoring--EM
 */
public class EM {
	 public static void main(String[] args) {
		 testDb();		 
	   }   
	 
	 
	 /***
	  * 测试数据库连接状况
	  */
	 public static void testDb(){
		 try {
	           // Class.forName("com.mysql.jdbc.Driver");  
			    Class.forName("com.mysql.cj.jdbc.Driver");  		  
	            Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pams?serverTimezone=UTC&useSSL=false","root","jeffqiu"); 
	            System.out.println(conn);
		 } catch (ClassNotFoundException e) {	         
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 
	 
	 /****
	  * 测试文件配置路径
	  */
	 public static void testEm(){
		 //需要检测的配置属性
		 Prop p =PropKit.use("config.properties");
		 String MysqlBackupPath=p.get("MysqlBackupPath");//数据库备份路径
		 String uploadFile=p.get("uploadFile");//文件上传路径
		 String log4j="E:\\logs";//日志信息
		 String pams=p.get("pams");
		 //检测
		 FileUtil.checkedPath(MysqlBackupPath);
		 FileUtil.checkedPath(uploadFile);
		 FileUtil.checkedPath(log4j);
		 FileUtil.checkedPath(pams);
	 }
}

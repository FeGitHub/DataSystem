package com.DS.utils.common;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

/****
 * 
 * @author jeff
 * 部署环境监测
 * Environmental Monitoring--EM
 */
public class EM {
	 public static void main(String[] args) {
		 //需要检测的配置属性
		 Prop p =PropKit.use("config.properties");
		 String DBPath=p.get("DBPath");//数据库备份路径
		 String uploadFile=p.get("uploadFile");//文件上传路径
		 String log4j="E:\\logs";//日志信息
		 //检测
		 FileUtil.checkedPath(DBPath);
		 FileUtil.checkedPath(uploadFile);
		 FileUtil.checkedPath(log4j);
		 
	   }   
}

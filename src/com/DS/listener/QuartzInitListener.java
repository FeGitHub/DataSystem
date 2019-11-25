package com.DS.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DS.utils.quartz.QuartzManager;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
/**
 * 随web启动而启动所有quartz任务
 * @author jeff
 */
public class QuartzInitListener implements ServletContextListener {  
	
	Logger logger = LoggerFactory.getLogger(QuartzInitListener.class);
	
    public QuartzInitListener() { }

    /***
	 * 初始化加载quartz
	 */
    public void contextInitialized(ServletContextEvent sce)  { 
    	 Prop p =PropKit.use("config.properties");
		 String qrtz=p.get("qrtz");
		 if("true".equals(qrtz)){
			 QuartzManager.startJobs();  
		 }else{
			 logger.info("定时任务处于关闭状态");
		 }   	 
    }
    
    
    /***
     * 停止
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	QuartzManager.shutdownJobs();
    }
}

package com.DS.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.DS.utils.quartz.QuartzManager;
/**
 * 随web启动而启动所有quartz任务
 * @author jeff
 */
public class QuartzInitListener implements ServletContextListener {   
    public QuartzInitListener() { }

    /***
	 * 初始化加载quartz
	 */
    public void contextInitialized(ServletContextEvent sce)  { 
    	QuartzManager.startJobs();
    	System.out.println("----------quartz初始化-----------");
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	QuartzManager.shutdownJobs();
    	System.out.println("----------quartz停止-------------");
    }
}

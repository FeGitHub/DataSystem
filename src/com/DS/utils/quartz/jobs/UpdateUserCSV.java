package com.DS.utils.quartz.jobs;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.DS.common.model.User;
import com.DS.pams.service.UserService;
import com.DS.pams.service.impl.AnalyseServiceImpl;
import com.DS.pams.service.impl.UserServiceImpl;

/***
 * 
 * @author jeff
 * 用于测试的基本job类
 */
public class UpdateUserCSV implements Job{
	private static Logger logger = Logger.getLogger(UpdateUserCSV.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("***************更新用户任务信息资源*************************");	
		UserService userService=new UserServiceImpl();
		List<User> users=userService.getAllUser();
		AnalyseServiceImpl analyse=new AnalyseServiceImpl();
		for(User user:users){
			analyse.createAnalyseCSV(user);
			logger.info("**********"+user.getAccount()+"任务信息资源更新完成***");
		}
	}	
}

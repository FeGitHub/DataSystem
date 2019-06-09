package com.DS.utils.quartz.jobs;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.DS.analyse.service.impl.AnalyseServiceImpl;
import com.DS.common.model.User;
import com.DS.user.service.UserService;
import com.DS.user.service.impl.UserServiceImpl;
/***
 * 
 * @author jeff
 * 用于测试的基本job类
 */
public class UpdateProjectAnalyse implements Job{
	private static Logger logger = Logger.getLogger(UpdateProjectAnalyse.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("***************更新用户工程任务分析参数*******************");	
		UserService userService=new UserServiceImpl();
		List<User> users=userService.getAllUser();
		AnalyseServiceImpl analyse=new AnalyseServiceImpl();
		for(User user:users){
			boolean result=analyse.updateProjectAnalyse(user);
			if(result){
				logger.info(user.getAccount()+"工程任务分析参数更新完成");
			}else{
				logger.error(user.getAccount()+"工程任务分析参数更新失败");
			}			
		}
	}
	
	
}

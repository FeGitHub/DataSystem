package com.DS.utils.quartz.jobs;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.DS.bean.MailBean;
import com.DS.common.model.User;
import com.DS.pams.service.UserService;
import com.DS.pams.service.impl.NotificationServiceImpl;
import com.DS.pams.service.impl.UserServiceImpl;


/****
 * 
 * @author jeff
 * function: 将用户的今日信息进行汇总
 */
public class GetTodayNotify implements Job{
 
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		NotificationServiceImpl n=new NotificationServiceImpl();
		UserService userService=new UserServiceImpl();
		List<User> users=userService.getAllUser();
		for(User user:users){
			n.getTodayNotification(user);
		}			
	}
	
	
}

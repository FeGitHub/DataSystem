package com.DS.utils.quartz.jobs;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.DS.analyse.service.impl.AnalyseServiceImpl;
import com.DS.bean.MailBean;
import com.DS.common.model.User;
import com.DS.notification.service.NotificationService;
import com.DS.notification.service.impl.NotificationServiceImpl;
import com.DS.user.service.UserService;
import com.DS.user.service.impl.UserServiceImpl;
/***
 * 
 * @author jeff
 * 用于测试的基本job类
 */
public class DoSomethingToEveryone implements Job{	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		UserService userService=new UserServiceImpl();
		NotificationService notificationService=new NotificationServiceImpl();
		List<User> users=userService.getAllUser();
		String tempMail;
		AnalyseServiceImpl analyse=new AnalyseServiceImpl();
		for(User user:users){
			//analyse.updateTaskAnalyse(user);
			/*tempMail=user.getMail();		
			if(tempMail!=null&&!"".equals(tempMail)){
				MailBean mail=new MailBean(tempMail,"主题");
		        mail.setContent("内容");
				notificationService.sendMail(mail);
			}*/
			analyse.createAnalyseCSV(user);
		}
	}
}

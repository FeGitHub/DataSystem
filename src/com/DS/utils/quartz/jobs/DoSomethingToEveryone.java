package com.DS.utils.quartz.jobs;
import java.util.List;

import org.apache.log4j.Logger;
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
import com.DS.utils.common.RegularUtil;
/***
 * 
 * @author jeff
 * 向系统中的用户发送信息
 */
public class DoSomethingToEveryone implements Job{	
	private static Logger logger = Logger.getLogger(DoSomethingToEveryone.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		UserService userService=new UserServiceImpl();
		NotificationService mailDoServer=new NotificationServiceImpl();
		List<User> users=userService.getAllUser();
		AnalyseServiceImpl analyse=new AnalyseServiceImpl();
		String mailAdress="";
		for(User user:users){
			mailAdress=user.getMail();
			if(RegularUtil.isNameAdressFormat(mailAdress)){
				MailBean mail=new MailBean(mailAdress,"信息通知");
				mailDoServer.sendMail(mail);
			}else{
				logger.info("用户："+user.getAccount()+",邮件地址无效："+mailAdress);
			}
			analyse.createAnalyseCSV(user);
		}
	}
}

package com.DS.utils.quartz.jobs;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.DS.common.model.Notification;
import com.DS.common.model.ProjectTree;
import com.DS.common.model.Remind;
import com.DS.common.model.Task;
import com.DS.common.model.User;
import com.DS.remind.service.RemindService;
import com.DS.remind.service.impl.RemindServiceImpl;
import com.DS.task.service.ProjectService;
import com.DS.task.service.TaskService;
import com.DS.task.service.impl.ProjectServiceImpl;
import com.DS.task.service.impl.TaskServiceImpl;
import com.DS.user.service.UserService;
import com.DS.user.service.impl.UserServiceImpl;
import com.jfinal.plugin.activerecord.Db;
/****
 * 
 * @author jeff
 * function: 将用户的今日信息进行汇总
 */
public class GetTodayNotify implements Job{
	private List<Task> taskList=null;
	private List<Remind> remindList=null;
	List<ProjectTree> projectTreeList=null;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//job的实例由quart创建，无法直接依赖注入（待解决）
		UserService userService=new UserServiceImpl();
		RemindService remindService=new RemindServiceImpl();
		TaskService taskService=new TaskServiceImpl();
		ProjectService projectTreeService=new ProjectServiceImpl();
		List<User> users=userService.getAllUser();
		String userId=null;
		for(User user:users){
			userId=	user.getId()+"";
			taskList=taskService.getTodayTarget(userId);
			remindList=remindService.getTodayRemind(userId);
			projectTreeList=projectTreeService.getTodayTreeTask(userId);
			//开始处理
			targetToNotify(taskList);
			remindToNotify(remindList);
			projectTreeToNotify(projectTreeList);
		}					
	}
	
	/****
	 * 备忘
	 * @param remindList
	 * @return
	 */
	public int  remindToNotify(List<Remind> remindList){
		List<Notification> resultList=new ArrayList<Notification>();
		for(Remind item:remindList){
			Notification n=new Notification();
			n.setSender("备忘系统");
			n.setUserId(item.getUserId()+"");
			n.setSubject(item.getSubject());
			n.setContent(item.getContent());
			n.setOperatetime(new Date());
			n.setCreateType(1);
			resultList.add(n);			
		}		
		Db.batchSave(resultList, 1000);
		return 1;	
	}
	
	public int  targetToNotify(List<Task> taskList){
		List<Notification> resultList=new ArrayList<Notification>();
		for(Task item:taskList){
			Notification n=new Notification();
			n.setSender("目标系统");
			n.setUserId(item.getUserId()+"");
			n.setSubject(item.getTaskName());
			n.setContent(item.getDescription());
			n.setOperatetime(new Date());
			n.setCreateType(2);
			resultList.add(n);			
		}		
		Db.batchSave(resultList, 1000);
		return 1;	
	}
   
	
	public int  projectTreeToNotify(List<ProjectTree> projectTreeList){
		List<Notification> resultList=new ArrayList<Notification>();
		for(ProjectTree item:projectTreeList){
			Notification n=new Notification();
			n.setSender("工程系统");
			n.setUserId(item.getUserId()+"");
			n.setSubject(item.getTaskName());
			n.setContent(item.getDepiction());
			n.setOperatetime(new Date());
			n.setCreateType(3);
			resultList.add(n);			
		}		
		Db.batchSave(resultList, 1000);
		return 1;	
	}
}

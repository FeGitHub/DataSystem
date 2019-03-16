package com.DS.notification.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.DS.bean.MailBean;
import com.DS.common.model.Notification;
import com.DS.common.model.ProjectTree;
import com.DS.common.model.Remind;
import com.DS.common.model.Task;
import com.DS.notification.service.NotificationService;
import com.DS.remind.service.RemindService;
import com.DS.remind.service.impl.RemindServiceImpl;
import com.DS.task.service.ProjectTreeService;
import com.DS.task.service.TaskService;
import com.DS.task.service.impl.ProjectTreeServiceImpl;
import com.DS.task.service.impl.TaskServiceImpl;
import com.DS.utils.common.MailUtil;
import com.DS.utils.common.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
/***
 * 通知信息服务层实现
 * @author jeff
 *
 */
public class NotificationServiceImpl implements NotificationService {
    @Inject(RemindServiceImpl.class)
    RemindService remindService;
    
    @Inject(TaskServiceImpl.class)
    TaskService taskService;
    
    @Inject(ProjectTreeServiceImpl.class)
    ProjectTreeService projectTreeService;
    
	/****
	 * 用户通知信息栏的信息
	 */
	@Override
	public  List<Record> getNotification(String userId) {
		 Map<String,Object> cond=new HashMap<String,Object>();
		 cond.put("userId", userId);
		 //cond.put("limitSize", 5);
		 SqlPara sql = Db.getSqlPara("notification.selectNotifications",cond);		
		 List<Record> notifications= Db.find(sql);		
		 return notifications;
	}
    
	/****
	 * 获取用户的信息列表
	 */
	@Override
	public List<Record> getNotificationList(String userId,String pageNumber) {
		 int pageSize=5;
		 int start=(Integer.parseInt(pageNumber)-1)*pageSize;
		 Map<String,Object> cond=new HashMap<String,Object>();
		 cond.put("start", start);
		 cond.put("pageSize", pageSize);
		 cond.put("userId", userId);
		 SqlPara sql = Db.getSqlPara("notification.selectNotifications",cond);		
		 List<Record> list= Db.find(sql);			
		 return list;
	}
	
	/****
	 * 得到用户的通知信息的总条数
	 */
	@Override
	public long getNotificationSize(String userId) {
		 Map<String,String> cond=new HashMap<String,String>();
		 cond.put("userId", userId);
		 SqlPara sql = Db.getSqlPara("notification.getNotificationSize",cond);		
		 Record record=Db.findFirst(sql);
		 long  size=0;
		 if(record!=null){
			 size=record.get("size");
		 }			 
		 return size;
	}
   
	/****
	 * 新增通知信息
	 */
	@Override
	public int addNotification(Notification n) {
		  Map<String,Object> paramMap=ObjectUtil.convertBeanToMap(n);
		  SqlPara sql = Db.getSqlPara("notification.addNotification",paramMap);
		  return Db.update(sql);
	}
   
	/***
	 * 删除用户的通知信息
	 */
	@Override
	public int delNotification(String id,String userId) {
		if(id==null ||userId==null){
			  return 0;
		}
		 Map<String,Object> paramMap=new HashMap<String,Object>();
		 paramMap.put("id", id);
		 paramMap.put("userId", userId);
		 SqlPara sql = Db.getSqlPara("notification.delNotification",paramMap);
		 return Db.update(sql);
	}
  
	
	/***
	 * 修改通知信息
	 */
	@Override
	public int updateNotification(Notification n) {
		if(n.getId()==null ||n.getUserId()==null){
			  return 0;
		}
		Map<String,Object> paramMap=ObjectUtil.convertBeanToMap(n);
		SqlPara sql = Db.getSqlPara("notification.updateNotification",paramMap);
		return Db.update(sql);
	}
   
	/****
	 * 批量删除通知新信息
	 */
	@Override
	public int batchDel(List<String> list) {
	    Map<String,List<String>> param=new HashMap<String,List<String>>();
	    param.put("ids", list);
	    SqlPara delSql=Db.getSqlPara("notification.batchDel", param);			    
		return Db.update(delSql);
	}
    
	/****
	 * 加载通知信息列表
	 */
	@Override
	public Map<String, Object> loadNotifyList(String userId, String pageNumber) {		 
		  List<Record> list=getNotificationList(userId,pageNumber);	
		  long size=getNotificationSize(userId);		  
		  long endPageNumber=getNotificationSize(userId);
		  endPageNumber=(long) Math.floor(endPageNumber/5);	
		  if(endPageNumber==0){
			  endPageNumber=endPageNumber+1;
		  }
	      Map<String,Object> map=new HashMap<String,Object>();
	      JSONObject hash = new JSONObject();
		  hash.put("mailList", list);	
	      map.put("info", hash);//详细通知信息列表
	      map.put("size", size);//通知信息总条数
	      map.put("pageNumber", pageNumber);//页码
	      map.put("endPageNumber", endPageNumber);
	      map.put("msg", "刷新成功");
		return map;
	}
   
	
	/****
	 * 发送邮件
	 */
	@Override
	public int sendMail(MailBean mail) {
		if(mail.getReceiveMailAccount()==null&&"".equals(mail.getReceiveMailAccount())){
			return 0;
		}
		try {
			MailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
   
	/****
	 * 发送验证码
	 */
	@Override
	public int sendCode(String mailAdress) {
		 Random random = new Random();
		 int randomNum = random.nextInt(1000000);
         String randomCode = String.format("%06d", randomNum);
         MailBean mail=new MailBean(mailAdress,"系统验证码");
         mail.setReceiveName("新用户");
         mail.setSenderName("系统");
         mail.setContent("系统注册验证码："+randomCode);  
         if(sendMail(mail)>0){
        	 return randomNum;
         }
         return 0;
	}
   
	/****
	 * 得到今天应进行的任务
	 */
	@Override
	public void getAllTaskToday(String userId) {
		List<Remind> remindList=remindService.getTodayRemind(userId);

		//List<Task> taskList=taskService.getTodayTarget(userId);
		//List<ProjectTree> projectTreeList=projectTreeService.getTodayTreeTask(userId);
	}
    
	
	
}

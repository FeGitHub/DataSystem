package com.DS.notification.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.notification.service.NotificationService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
/***
 * 通知信息服务层实现
 * @author jeff
 *
 */
public class NotificationServiceImpl implements NotificationService {
  
	/****
	 * 获取用户最新的5条通知信息
	 */
	@Override
	public  List<Record> getNotification(String userId) {
		 Map<String,Object> cond=new HashMap<String,Object>();
		 cond.put("userId", userId);
		 cond.put("limitSize", 5);
		 SqlPara sql = Db.getSqlPara("notification.selectNotifications",cond);		
		 List<Record> notifications= Db.find(sql);		
		 return notifications;
	}
    
	/****
	 * 获取用户的信息列表
	 */
	@Override
	public List<Record> getNotificationList(String userId) {
		 Map<String,String> cond=new HashMap<String,String>();
		 cond.put("userId", userId);
		 SqlPara sql = Db.getSqlPara("notification.selectNotifications",cond);		
		 List<Record> list= Db.find(sql);			
		 return list;
	}
		
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

}

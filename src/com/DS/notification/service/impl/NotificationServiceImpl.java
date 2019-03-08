package com.DS.notification.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.common.model.Notification;
import com.DS.notification.service.NotificationService;
import com.DS.utils.common.ObjectUtil;
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

}

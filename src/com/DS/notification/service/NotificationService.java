package com.DS.notification.service;

import java.util.List;


import com.jfinal.plugin.activerecord.Record;
/****
 * 
 * @author jeff
 * 信息通知服务层接口
 *
 */
public interface NotificationService {     
	 List<Record> getNotification(String userId);
	 
	 /****
	  * 
	  * @param userId  用户id
	  * @return
	  */
	 List<Record> getNotificationList(String userId);
	 
	 /****
	  * 获取用户的通知信息条数
	  * @param userId
	  * @return
	  */
	 long getNotificationSize(String userId);
	 
	 /****
	  * 新增通知信息
	  */	 
	 int addNotification(String userId);	
}

package com.DS.notification.service;

import java.util.List;


import com.jfinal.plugin.activerecord.Record;

public interface NotificationService {     
	 List<Record> getNotification(String userId);
	 
	 /****
	  * 
	  * @param userId  用户id
	  * @return
	  */
	 List<Record> getNotificationList(String userId);
	 
	 long getNotificationSize(String userId);
}

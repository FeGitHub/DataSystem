package com.DS.notification.service.impl;
import java.util.List;
import com.DS.notification.service.NotificationService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
public class NotificationServiceImpl implements NotificationService {

	@Override
	public  List<Record> getNotification() {
		 String sql=Db.getSql("notification.selectNotifications");
		 List<Record> notifications= Db.find(sql);		
		 return notifications;
	}

}

package com.DS.notification.service;

import java.util.List;


import com.jfinal.plugin.activerecord.Record;

public interface NotificationService {     
	 List<Record> getNotification();
}

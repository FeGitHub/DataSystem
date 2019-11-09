package com.DS.pams.service;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.DS.bean.MailBean;
import com.DS.common.model.Notification;
import com.DS.common.model.User;
import com.jfinal.plugin.activerecord.Record;
/****
 * 
 * @author jeff
 * 信息通知服务层接口
 *
 */
public interface NotificationService {   
	/****
	 * 加载用户信息通知栏
	 * @param userId
	 * @param limitFlag
	 * @return
	 */
	 List<Record> getNotification(String userId,String limitFlag);
	 
	 /****
	  * 获取用户邮箱的分页展示消息
	  * @param userId  用户id
	  * @return
	  */
	 List<Record> getNotificationList(String userId,String pageNumber,String limitFlag);
	 
	 /****
	  * 获取用户的通知信息条数
	  * @param userId
	  * @return
	  */
	 long getNotificationSize(String userId,String limitFlag);
	 
	 /****
	  * 新增通知信息
	  */	 
	 int addNotification(Notification n);	
	 
	 /****
	  * 
	  * @param id  通知信息的id
	  * @param userId  用户的id,确保用户只能操作自己的数据
	  * @return
	  */
	 int delNotification(String id,String userId);
	 
	 /***
	  * 修改通知信息
	  */
	 int updateNotification(Notification n);
	 
	 /****
	  *  批量删除通知信息
	  */
	 int batchDel(List<String> list);
	 
	 /****
	  * 加载用户的通知信息列表
	  * @param userId 用户id
	  * @param pageNumber  当前页码
	  * @return
	  */
	 Map<String,Object> loadNotifyList(String userId,String pageNumber);
	 
	 /****
	  * 发送邮件
	  * @param mail
	  * @return true--发送成功  false--发送失败
	  */
	 boolean sendMail(MailBean mail);
	 
	 /****
	  * 发送验证码
	  * @param mailAdress
	  * @return
	  */
	 int sendCode(String mailAdress);
	 
	 /*****
	  * 处理用户今日的信息通知
	  * @param user
	  */
	 void getTodayNotification(User user);
	 
	 
	 void refresh( HttpSession session, User user);
	 
	 
	 
}

package com.DS.interceptor;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.DS.common.model.User;
import com.DS.menu.service.MenuService;
import com.DS.menu.service.impl.MenuServiceImpl;
import com.DS.notification.service.NotificationService;
import com.DS.notification.service.impl.NotificationServiceImpl;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.aop.Inject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
/***
 * 登录验证的拦截器
 * @author jeff
 *
 */
public class LoginInterceptor implements Interceptor{
	@Inject(NotificationServiceImpl.class)
	private NotificationService notificationService;
	
	@Inject(MenuServiceImpl.class)
	private MenuService menuService;
	
	@Override
	public void intercept(Invocation inv) {
		HttpSession session = inv.getController().getSession();
		Controller controller = inv.getController();
		if(session == null){
			inv.getController().redirect("/");
		}
		else{
			User user = (User)session.getAttribute("user");
			if(user!= null) {
				 JSONArray menu=null;
				 if(session.getAttribute("menu")==null){
					 menu=menuService.getTreeMenu(); 
					 session.setAttribute("menu", menu);			
				 }else{
					 menu=(JSONArray) session.getAttribute("menu");					
				 }
				 List<Record> notifications=notificationService.getNotification(user.getStr("id"));
				 long notificationSize=notificationService.getNotificationSize(user.getStr("id"));
				 controller.setAttr("notificationSize", notificationSize);
				 controller.setAttr("menuTree", menu);
				 controller.setAttr("notifications", notifications);
				 inv.invoke();
			}
			else {
				inv.getController().redirect("/");
			}
		}
	}
}

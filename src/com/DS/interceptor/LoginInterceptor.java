package com.DS.interceptor;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.DS.bean.MenuInfo;
import com.DS.common.model.User;
import com.DS.pams.service.MenuService;
import com.DS.pams.service.NotificationService;
import com.DS.pams.service.impl.MenuServiceImpl;
import com.DS.pams.service.impl.NotificationServiceImpl;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void intercept(Invocation inv) {
		HttpServletRequest request = inv.getController().getRequest();
		HttpServletResponse response = inv.getController().getResponse();
		String url = request.getServletPath();
		HttpSession session = inv.getController().getSession();
		Controller controller = inv.getController();
		if(session == null){
			inv.getController().redirect("/");
		}
		else{
			User user = (User)session.getAttribute("user");
			if(user!= null) {				 		
                 List<String> urls=new ArrayList<String>();
                 MenuInfo menuInfo=(MenuInfo) session.getAttribute("menuInfo");
				 if(menuInfo==null){
					 menuInfo=menuService.getTreeMenu(user.getLeve());			
					 session.setAttribute("menuInfo", menuInfo);	
				 }				
				 controller.setAttr("menuTree", menuInfo.getTreeMenu());
				 List<String> allMenuUrl=(List<String>) session.getAttribute("allMenuUrl");
				 urls=menuInfo.getUrls();
				 if((allMenuUrl.contains(url))&&(!urls.contains(url))){
					 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
 					 return;
				 }
				 //处理通知信息，有新信息时才进行更新
				 List<Record> notifications=null;
				 Long notificationSize=null;
				 if(session.getAttribute("notifications")==null){
					 //更新用户信息通知信息栏
					 notifications=notificationService.getNotification(user.getStr("id"),"limit");
					 notificationSize=notificationService.getNotificationSize(user.getStr("id"),"limit");
					 session.setAttribute("notifications", notifications);
					 session.setAttribute("notificationSize", notificationSize);
				 }else{
					 notifications=(List<Record>) session.getAttribute("notifications");
					 notificationSize=(Long) session.getAttribute("notificationSize");
				 }				
				 controller.setAttr("notificationSize", notificationSize);				
				 controller.setAttr("notifications", notifications);
				 controller.setAttr("user", user);
				 if(user.getLeve()==1){
					 controller.setAttr("role", "普通用户");
				 }else{
					 controller.setAttr("role", "管理员");
				 }
				 inv.invoke();
			}
			else {
				inv.getController().redirect("/");
			}
		}
	}
}

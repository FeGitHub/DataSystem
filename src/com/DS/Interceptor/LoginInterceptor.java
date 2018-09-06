package com.DS.Interceptor;
import javax.servlet.http.HttpSession;

import com.DS.Model.UserModel;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
/***
 * 登录验证的拦截器
 * @author jeff
 *
 */
public class LoginInterceptor implements Interceptor{
	@Override
	public void intercept(Invocation inv) {
		HttpSession session = inv.getController().getSession();
		if(session == null){
			inv.getController().redirect("/");
		}
		else{
			UserModel user = (UserModel)session.getAttribute("user");
			if(user!= null) {
				inv.invoke();
			}
			else {
				inv.getController().redirect("/");
			}
		}

	}

}

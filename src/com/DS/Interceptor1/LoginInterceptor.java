package com.DS.Interceptor1;
import javax.servlet.http.HttpSession;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.activerecord.Record;
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
			Record user = (Record)session.getAttribute("user");
			if(user!= null) {
				inv.invoke();
			}
			else {
				inv.getController().redirect("/");
			}
		}

	}

}

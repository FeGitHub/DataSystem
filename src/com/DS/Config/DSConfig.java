package com.DS.Config;
import com.DS.Controller.BlogController;
import com.DS.Controller.Early_warningController;
import com.DS.Controller.LoginController;
import com.DS.Controller.GoController;
import com.DS.Controller.QrtzController;
import com.DS.Controller.RemindController;
import com.DS.Controller.RemoteHTTPAction;
import com.DS.Controller.UserController;
import com.DS.Controller.indexController;
import com.DS.Interceptor.LoginInterceptor;
import com.DS.common.model._MappingKit;
import com.jfinal.config.*;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;
/***
 * @Author jeff
 * @Description 基于Jfinal的项目的配置文件
 */
public class DSConfig extends JFinalConfig {
    /***
     * @Description 配置文件的常量配置
     * @param me
     */
    public void configConstant(Constants me) {
       //me.setViewType(ViewType.FREE_MARKER);//启动FREE_MARKER
       me.setViewType(ViewType.JSP);
       PropKit.use("DB.properties");
	   me.setDevMode(PropKit.getBoolean("devMode", true));
	   me.setInjectDependency(true);//依赖注入

       
    }
    /***
     * @Description 配置访问路由
     * @param me
     */
    public void configRoute(Routes me) {
        me.setBaseViewPath("/WEB-INF/view/"); //默认视图路径
        me.add("/", indexController.class);//默认处理
        me.add("/go", GoController.class);//用于跳转页面
        me.add("/login", LoginController.class);//登录处理
        me.add("/BlogData", BlogController.class);//博客数据
        me.add("/user",UserController.class);//用户
        me.add("/RemoteAction",RemoteHTTPAction.class);//提供给远程调用的action
        me.add("/qrtz",QrtzController.class);//提供给远程调用的action
        me.add("/warn",Early_warningController.class);//预警信息
        me.add("/remind",RemindController.class);//备忘提醒的相关处理
    }
    @Override
    public void configEngine(Engine engine) {
    	
    }
    /***
     * @Description 插件配置
     * @param me
     */
    public void configPlugin(Plugins me) {    	    
    	 DruidPlugin dp = createDruidPlugin();
    	 me.add(dp);
    	 ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
    	 arp.getEngine().setSourceFactory(new ClassPathSourceFactory());
    	 arp.addSqlTemplate("all.sql");
    	 arp.setShowSql(true);//打开sql语句显示 
    	 _MappingKit.mapping(arp);    
    	 me.add(arp);
    }
    
    /***
     * 拦截器配置
     */
    public void configInterceptor(Interceptors me) {
    	 // 添加控制层全局拦截器
        me.addGlobalActionInterceptor(new LoginInterceptor());
    }
    
    public void configHandler(Handlers me) {
    	  me.add(new UrlSkipHandler("/api.*", false));//除去对hessian的影响
    	  me.add(new ContextPathHandler("BASE_PATH"));
    }
    
   
    public static DruidPlugin createDruidPlugin() {
    	 PropKit.use("DB.properties");
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}

}

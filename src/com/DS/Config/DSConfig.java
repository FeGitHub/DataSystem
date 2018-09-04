package com.DS.Config;
import com.DS.Controller.BlogController;
import com.DS.Controller.LoginController;
import com.DS.Controller.PageController;
import com.DS.Controller.QrtzController;
import com.DS.Controller.RemoteHTTPAction;
import com.DS.Controller.TestController;
import com.DS.Controller.UserController;
import com.DS.Controller.indexController;
import com.DS.Model.QrtzJobDetailsModel;
import com.DS.Model.TestModel;
import com.DS.Model.UserModel;
import com.jfinal.config.*;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
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
       me.setDevMode(true);//设置为开发者模式
       me.setViewType(ViewType.FREE_MARKER);//启动FREE_MARKER
       
    }
    /***
     * @Description 配置访问路由
     * @param me
     */
    public void configRoute(Routes me) {
        me.setBaseViewPath("/WEB-INF/view/"); 
        me.add("/", indexController.class);
        me.add("/test", TestController.class);//用于测试
        me.add("/go", PageController.class);//用于跳转页面
        me.add("/login", LoginController.class);
        me.add("/BlogData", BlogController.class);
        me.add("/user",UserController.class);
        me.add("/RemoteAction",RemoteHTTPAction.class);//提供给远程调用的action
        me.add("/qrtz",QrtzController.class);//提供给远程调用的action

    }
    @Override
    public void configEngine(Engine engine) {
    }
    /***
     * @Description 插件配置
     * @param me
     */
    public void configPlugin(Plugins me) {
    	//mysql数据库插件配置
        C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://localhost/jfinal_demo?useSSL=true", "root", "root");
        me.add(cp);
        //记录映射配置
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        me.add(arp);
        this.setDBMapping(arp);
       
    }
    public void configInterceptor(Interceptors me) {}
    public void configHandler(Handlers me) {
    	  me.add(new UrlSkipHandler("/api.*", false));//除去对hessian的影响
    }
    
    /**
     * 添加数据库表的映射
     */
    public void setDBMapping(ActiveRecordPlugin arp){
    	 arp.addMapping("blog", TestModel.class);//用于测试
    	 arp.addMapping("user", UserModel.class);//用户信息
    	 arp.addMapping("qrtz_job_details", QrtzJobDetailsModel.class);//调度器任务信息
    	    
    }
    
}

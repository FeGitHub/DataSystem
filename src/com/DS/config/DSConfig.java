package com.DS.config;
import com.DS.common.model._MappingKit;
import com.DS.interceptor.LoginInterceptor;
import com.DS.utils.SecretUtil;
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
	   me.setBaseUploadPath("F:/temp");
	   me.setBaseDownloadPath("F:/");

       
    }
    /***
     * @Description 配置访问路由
     * @param me
     */
    public void configRoute(Routes me) {
        me.setBaseViewPath("/WEB-INF/view/"); //默认视图路径
        _MappingRoute.mapping(me);
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
    	 String password=PropKit.get("password").trim();
    	 password=SecretUtil.decrypt(password);
		 return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), password);
	}

}

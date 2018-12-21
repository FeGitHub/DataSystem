package com.DS.config;
import com.jfinal.core.JFinal;
/***
 * -------------
 * @Author jeff
 * @Description 
 * 如果使用jetty插件,则这个用于启动服务
 * 如果使用tomcat,请删除jetty插件
 * ----------2018.12.12----------------
 *  jfinal3.5+jetty-server-2018.11.jar运行会出错
 *  目前使用tomcat启动，如果要用jetty,用temp/jfinal3.4+jetty8.18的包
 */
public class StartDSConfig {
    public static void main(String[] args) {
    	JFinal.start("WebRoot", 8081, "/",5);
    }
}
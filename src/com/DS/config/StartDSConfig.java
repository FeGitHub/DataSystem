package com.DS.config;
import com.jfinal.core.JFinal;
/***
 * -------------
 * @Author jeff
 * @Description 
 * 如果使用jetty插件,则这个用于启动服务
 * 如果使用tomcat,请删除jetty插件
 */
public class StartDSConfig {
    public static void main(String[] args) {
    	JFinal.start("WebRoot", 8081, "/",5);
    }
}
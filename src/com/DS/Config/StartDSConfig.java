package com.DS.Config;
import com.jfinal.core.JFinal;
/***
 * @Author jeff
 * @Description 用于启动jetty服务
 */
public class StartDSConfig {
    public static void main(String[] args) {
    	JFinal.start("WebRoot", 8081, "/",5);
    }
}
package com.DS.pams.web.controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
/**
 * 提供给远程调用的action
 *
 */
@Clear
public class RemoteHTTPAction extends Controller{
	 /***
     * 通过apache的jar包
     * 用于远程调用action
     */
    public String OriginalHttp() throws IOException, ClassNotFoundException{ 
    	  //创建request和response对象 
    	  HttpServletResponse response = getResponse(); 
    	  HttpServletRequest request=getRequest(); 
    	  //设置response编码    	  
    	  //用来告诉浏览器页面的编码方式的,如果有响应页面，则响应页面应该采取的字符编码
    	  response.setContentType("text/html;charset=UTF-8");   	  
    	  //告诉服务器请求的编码方式，
    	  response.setCharacterEncoding("UTF-8");    	      	  
    	  //创建writer实例 
    	  PrintWriter out = null; 
    	  out = response.getWriter(); 
    	  //gson 用于把map转为JSON 
    	  Gson gs = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create(); 
    	  //通过request获取传过来的参数，然后解析数据流获取参数 
    	  int length = (int) request.getContentLength();// 获取长度  
    	  InputStream is = request.getInputStream(); 
    	  if (length != -1) {  
    	          byte[] data = new byte[length];  
    	          byte[] temp = new byte[512];  
    	          int readLen = 0;  
    	          int destPos = 0;  
    	          while ((readLen = is.read(temp)) > 0) {  
    	              System.arraycopy(temp, 0, data, destPos, readLen);  
    	              destPos += readLen;  
    	          }  
    	          //获取的参数 
    	          String result = new String(data, "UTF-8"); // utf-8编码  
    	         // System.out.println(result); 
    	      } 
    	  //把要返回的参数写入map，转成JSON 
    	  Map<String, String> map = new HashMap<String, String>(); 
    	  map.put("ID","123"); 
    	  map.put("success", "true"); 
    	  String jsonmap = gs.toJson(map); 
    	  out.print(jsonmap); 
    	  return null; 
    	  } 
}

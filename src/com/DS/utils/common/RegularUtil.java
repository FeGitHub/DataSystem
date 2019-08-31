package com.DS.utils.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

/****
 * 
 * @author jeff
 * 正则校验
 * 
 */
public class RegularUtil {
	 public static void main(String[] args) {
		 String email="XXXX@qq.com";
		 isNameAdressFormat(email);
	   }   
	 
	 
	 /***
	  * 校验邮件地址是否合法
	  * @param email
	  * @return
	  */
	 public static  boolean isNameAdressFormat(String email){  
	        boolean isExist = false;  	       
	        Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");  
	        Matcher m = p.matcher(email);  
	        boolean b = m.matches();  
	        if(b) {  
	           // System.out.println("有效邮件地址");  
	            isExist=true;  
	        } else {  
	           // System.out.println("无效邮件地址");  
	        }  
	        return isExist;  
	    }  
}

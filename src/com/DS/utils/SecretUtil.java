package com.DS.utils;
import com.alibaba.druid.filter.config.ConfigTools;
/***
 * 
 * @author jeff
 * 加解密工具
 */
@SuppressWarnings("all")
public class SecretUtil {
	public static void main(String[] args) {
		try {
			String str="root";//原始数据		
			String EncryptStr="";//加密后
			String DecryptStr="";//解密后
			ConfigTools configTools = new ConfigTools ();
			EncryptStr=configTools.encrypt(str);
			System.out.println(EncryptStr);
			System.out.println(configTools.decrypt(EncryptStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

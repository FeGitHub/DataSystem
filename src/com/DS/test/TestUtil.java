package com.DS.test;
import com.jfinal.kit.PropKit;
import org.junit.Test;
public class TestUtil {	 
	
	@Test
	 public void testProKit(){
		 PropKit.use("config.properties");
		 String path=PropKit.get("DBPath");
		 String filePath=PropKit.get("uploadFile");
		 System.out.println(path);
		 System.out.println(filePath);
	 }
	@Test 
	public void hello(){
		System.out.println("hello");
	}
	
}

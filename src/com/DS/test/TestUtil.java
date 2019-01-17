package com.DS.test;
import com.jfinal.kit.PropKit;
import org.junit.Test;
public class TestUtil {	 
	
	@Test
	 public void testProKit(){
		 PropKit.use("CommonConfig.properties");
		 String path=PropKit.get("DBPath");
		 System.out.println(path);
	 }
	@Test 
	public void hello(){
		System.out.println("hello");
	}
	
}

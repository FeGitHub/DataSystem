package com.DS.Controller;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
/***
 * 
 * @author jeff
 * 为了方便跳转用于测试页面的控制器
 *
 */
public class GoController extends Controller{
		 public void goMenu(){
		 	   render("main.jsp");
		 }
		 public void goData(){
		 	   render("data.html");
		 }
		 public void goVue(){
		 	   render("vueDemo.jsp");
		 }
		 @Clear
		 public void goTree(){
			   render("tree.jsp");
		 }
		 @Clear
		 public void goDynamicTable(){
			   render("DynamicTable.jsp");
		 }
		 @Clear
		 public void goPopup(){
			   render("popup.jsp");
		 }
		 @Clear
		 public void goValidate(){
			   render("validationEngine.jsp");
		 }	 
		 /****
		  * 测试新的主管理页面
		  */
		 @Clear
		 public void goTest(){
			   render("page-main.jsp");
		 }
		 
		 @Clear
		 public void goBc(){
			   render("bootstrap-components.jsp");
		 }
		 @Clear
		 public void goHello(){
		 	   render("hello.jsp");
		 }
		 /****
		  * 测试新的登陆页面
		  */
		 @Clear
		 public void goLogin(){
			   render("page-login.jsp");
		 }
	 
	    /***
	     * 测试dataTables
	     */
	    public void goDataTables(){
	    	   render("dataTable.jsp");
	    }
	    
	    /***
	     * 测试弹出框
	     */
	    public void goComponent(){
	    	   render("component.jsp");
	    }
	    
	    /***
	     * 测试
	     */
	    public void goRemindTest(){
	    	   render("remindTest.jsp");
	    }
	    
}


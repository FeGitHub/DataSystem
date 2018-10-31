package com.DS.Controller;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
/***
 * 
 * @author jeff
 * 为了方便跳转用于测试页面的控制器
 *
 */
public class PageController extends Controller{
	 public void goMenu(){
	 	   render("main.jsp");
	 }
	 @Clear
	 public void goHello(){
	 	   render("hello.jsp");
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
	    	   render("blogTable.jsp");
	    }
}


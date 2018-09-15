package com.DS.Controller;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
public class PageController extends Controller{
	 public void goMenu(){
	 	   render("main.html");
	 }
	 @Clear
	 public void goHello(){
	 	   render("hello.html");
	 }
	 public void goData(){
	 	   render("data.html");
	 }
	 public void goVue(){
	 	   render("vueDemo.html");
	 }
	 public void goEditBlog(){
		   render("editBlog.html");
	 }
	 @Clear
	 public void goTree(){
		   render("tree.html");
	 }
	 @Clear
	 public void goDynamicTable(){
		   render("DynamicTable.html");
	 }
}


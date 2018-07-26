package com.DS.Controller;

import com.jfinal.core.Controller;

public class PageController extends Controller{
	 public void goMenu(){
	 	   render("main.html");
	 }
	 public void goHello(){
	 	   render("hello.html");
	 }
	 public void goData(){
	 	   render("data.html");
	 }
	 public void goVue(){
	 	   render("vueDemo.html");
	 }
}

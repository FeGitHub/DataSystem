<!--页面布局-->
<%@ page contentType="text/html;charset=UTF-8"%>  
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>
			<sitemesh:title/>
    </title> 
     
    <link rel="stylesheet" type="text/css" href="${BASE_PATH}/plug/vali/css/main.css"><!--页面主体样式-->
   	    <%@ include file="/WEB-INF/view/component/headjs.jsp" %>  
   	     <sitemesh:head/> 
  </head>
  <body class="app sidebar-mini rtl">
    <header class="app-header"><a class="app-header__logo" href="index.html"></a>
      <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
      <ul class="app-nav">
        <li class="app-search">
          <input class="app-search__input" type="search" placeholder="搜索">
          <button class="app-search__button"><i class="fa fa-search"></i></button>
        </li>
        <!--通知信息菜单-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Show notifications"><i class="fa fa-bell-o fa-lg"></i></a>
          <ul class="app-notification dropdown-menu dropdown-menu-right">
            <li class="app-notification__title">你有4个新的通知</li>
            <div class="app-notification__content">	                  
            </div>
            <li class="app-notification__footer"><a href="#">查看所有通知</a></li>
          </ul>
        </li>
        <!--用户菜单-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i class="fa fa-user fa-lg"></i></a>
          <ul class="dropdown-menu settings-menu dropdown-menu-right">
            <li><a class="dropdown-item" href="page-user.html"><i class="fa fa-cog fa-lg"></i> 设置</a></li>
            <li><a class="dropdown-item" href="page-user.html"><i class="fa fa-user fa-lg"></i> 账号</a></li>
            <li><a class="dropdown-item" id="loginOut"><i class="fa fa-sign-out fa-lg"></i> 登出</a></li>
          </ul>
        </li>
      </ul>
    </header>
    <!--功能菜单-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <aside class="app-sidebar">
      <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="${BASE_PATH}/images/48.jpg" alt="User Image">
        <div>
          <p class="app-sidebar__user-name">用户名</p>
          <p class="app-sidebar__user-designation">描述</p>
        </div>
      </div>
      <ul class="app-menu" id="menuId">
      </ul>
    </aside>
    <main class="app-content"> 
      <div class="app-title">
        <div>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="#">当前目录</a></li>
        </ul>
      </div>
      
      <div class="row">
        <div class="col-md-6 col-lg-3">
          <div class="widget-small primary coloured-icon"><i class="icon fa fa-users fa-3x"></i>
            <div class="info">
              <h4>学习</h4>
              <p><b></b></p>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-3">
          <div class="widget-small info coloured-icon"><i class="icon fa fa-thumbs-o-up fa-3x"></i>
            <div class="info">
              <h4>工作</h4>
              <p><b></b></p>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-3">
          <div class="widget-small warning coloured-icon"><i class="icon fa fa-files-o fa-3x"></i>
            <div class="info">
              <h4>娱乐</h4>
              <p><b></b></p>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-3">
          <div class="widget-small danger coloured-icon"><i class="icon fa fa-star fa-3x"></i>
            <div class="info">
              <h4>运动</h4>
              <p><b></b></p>
            </div>
          </div>
        </div>
      </div>
      <sitemesh:body/>       
    </main>   
	<%@ include file="/WEB-INF/view/component/footerJs.jsp" %>  
	<%@ include file="/WEB-INF/view/component/handlebarsTemplate.jsp" %>  	 	      
  </body>
</html>
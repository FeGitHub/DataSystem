<!--尝试修改的页面布局-->
<%@ page contentType="text/html;charset=UTF-8"%>  
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<sitemesh:title/>
</title>
<%@ include file="/WEB-INF/view/component/headResource.jsp" %>
  <sitemesh:head/> 
   <link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/plugcss/metisMenu.min.css" />
   <link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/pagecss/MyMetisMenu.css" />
   <link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/pagecss/template-backup-2.css" />
   <link rel="shortcut icon" href="${BASE_PATH}/favicon.ico">
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top"></nav>
    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li class="sidebar-search">
                    <div class="input-group custom-search-form">
                        <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
                </li>
                <li>
                    <a href=""><i class="fa fa-dashboard fa-fw"></i> 首页</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 系统管理<span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="">用户管理</a></li>
								<li><a href=""> 菜单管理</a></li>
								<li><a href=""> 角色管理</a></li>
								<li><a href=""> 修改密码</a></li>
								<li><a href="${BASE_PATH}/go/goHello"> 日志查看</a></li>
                    </ul>
                </li>
                <li>
                    <a href=""><i class="fa fa-table fa-fw"></i> 预警规则</a>
                </li>
                <li>
                    <a href=""><i class="fa fa-edit fa-fw"></i> 待处理事项<span
                                class="fa arrow"></span></a>
                                 <ul class="nav nav-second-level">
			                        <li>
			                            <a href="${BASE_PATH}/remind/goRemindList"> 备忘提醒</a>
			                        </li>                        
                   				 </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-wrench fa-fw"></i> 定时任务<span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="">Panels and Wells</a>
                        </li>
                        <li>
                            <a href="">Buttons</a>
                        </li>
                        <li>
                            <a href="">Notifications</a>
                        </li>
                        <li>
                            <a href="">Typography</a>
                        </li>
                        <li>
                            <a href=""> Icons</a>
                        </li>
                        <li>
                            <a href="">Grid</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-sitemap fa-fw"></i> 表格案例<span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="#">Second Level Item</a>
                        </li>
                        <li>
                            <a href="#">Second Level Item</a>
                        </li>
                        <li>
                            <a href="#">Third Level <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li ><!-- class="active" -->
                    <a href="#"><i class="fa fa-files-o fa-fw"></i> 欢迎界面<span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="active" href="#">Blank Page</a>
                        </li>
                        <li>
                            <a href="#">Login Page</a>
                        </li>
                    </ul>
                </li>
                 <li>
                    <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 预警判定<span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="">用户管理</a></li>
								<li><a href=""> 菜单管理</a></li>
								<li><a href=""> 角色管理</a></li>
								<li><a href=""> 修改密码</a></li>
								<li><a href="${BASE_PATH}/go/goHello"> 日志查看</a></li>
                    </ul>
                </li>
                 <li>
                    <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 页面测试<span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="">用户管理</a></li>
								<li><a href=""> 菜单管理</a></li>
								<li><a href=""> 角色管理</a></li>
								<li><a href=""> 修改密码</a></li>
								<li><a href="${BASE_PATH}/go/goHello"> 日志查看</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
              <!--主体内容-->		
					<div id="content" class="col-md-10 mainBody" >						 
   							<sitemesh:body/>  
  					</div> 	
  			<!--主体内容-->	
				
<script type="text/javascript" src="${BASE_PATH}/js/layer/layer.js"></script>
<script type="text/javascript" src="${BASE_PATH}/js/plugjs/jquery.metisMenu.js"></script>
<script type="text/javascript">
$(function() {
    $('#side-menu').metisMenu(); 
})
</script>
</body>
</html>
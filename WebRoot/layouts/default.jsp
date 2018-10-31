
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
   <link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/main.css" />
</head>
<body>
	<!--头部导航开始开始 -->
	<div class="navbar navbar-duomi navbar-static-top" role="navigation">
    	<div class="container-fluid">
     		<div class="navbar-header">
           		<a class="navbar-brand logo" href="${BASE_PATH}/go/goMenu">数据管理系统</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
      <li><a href="#"  class="logo"><span class="glyphicon glyphicon-user"></span>账号</a></li>
      <li><a href="${BASE_PATH}/login/signOut"  class="logo"><span class="glyphicon glyphicon-log-out"></span>退出</a></li>
    </ul>
		</div>
    </div>
    <!--头部导航结束 -->
    
   <!--中间主体部分开始 --> 
<div class="container-fluid">
			<div class="row">
			<div class="col-md-2" id="menu">
			<!-- 左部菜单开始 -->
			<ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
						<!--第一行 -->
						<li class="active">
							<a href=""><i class="glyphicon glyphicon-th-large"></i>首页 </a>
						</li>
						<!--第二行 -->
						<li>
							<a href="#to" class="nav-header collapsed" data-toggle="collapse"><i class="glyphicon glyphicon-cog"></i>系统管理<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a>
							<!--第二行的子菜单 -->
							<ul id="to" class="nav nav-list collapse secondmenu" style="height: 0px;">
								<li><a href=""><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
								<li><a href=""><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
								<li><a href=""><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
								<li><a href=""><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
								<li><a href="${BASE_PATH}/go/goHello"><i class="glyphicon glyphicon-eye-open"></i>日志查看</a></li>
							</ul>
					     </li>
					     
					    <!--第三行 -->
					<li><a href="${BASE_PATH}/warn/goWarnRule"><i class="glyphicon glyphicon-credit-card"></i>预警规则 </a></li>
						 <!--第四行 -->						
					<li>
							<a href="#goinfo" class="nav-header collapsed" data-toggle="collapse"><i class="glyphicon glyphicon-cog"></i>待处理事项<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a>
							<!--第二行的子菜单 -->
							<ul id="goinfo" class="nav nav-list collapse secondmenu" style="height: 0px;">
								<li> <a href="javascript:void(0);" id="info"><i class="glyphicon glyphicon-globe"></i>信息提示<span class="label label-warning pull-right">5</span></a></li>
								<li><a href="${BASE_PATH}/remind/goRemindList"><i class="glyphicon glyphicon-edit"></i>备忘提醒</a></li>							
							</ul>
					</li>
					   <!-- 第五行 -->
					<li><a href="${BASE_PATH}/qrtz/goQuartzJob"><i class="glyphicon glyphicon-calendar"></i>定时任务</a></li>
					 <!-- 第六行 -->
					<li><a href="${BASE_PATH}/test/goDataTables"><i class="glyphicon glyphicon-fire"></i>测试表格</a></li>				
					<li ><a href="${BASE_PATH}/go/goHello"><i class="glyphicon glyphicon-fire"></i>测试欢迎页面--可用</a></li>
					
					<li >
						<a href="#gowarn" class="nav-header collapsed" data-toggle="collapse"><i class="glyphicon glyphicon-cog"></i>预警判定<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a>						
							<ul id="gowarn" class="nav nav-list collapse secondmenu" style="height: 0px;">
								<li><a href="${BASE_PATH}/warn/goOrderCheck" class="inMyPlace"><i class="glyphicon glyphicon-fire"></i>测试预警ztree</a></li>	
								<li><a href="${BASE_PATH}/warn/goTh_OrderCheck" class="inMyPlace"><i class="glyphicon glyphicon-fire"></i>物料预警</a></li>							
							</ul>
					</li>
					
					<!--页面测试 -->
					<li >
						<a href="#goTestPage" class="nav-header collapsed" data-toggle="collapse"><i class="glyphicon glyphicon-cog"></i>页面测试<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a>
							<ul id="goTestPage" class="nav nav-list collapse secondmenu" style="height: 0px;">
								<li><a href="${BASE_PATH}/go/goValidate" class="inMyPlace"><i class="glyphicon glyphicon-fire"></i>测试表单验证</a></li>															
							</ul>
					</li>					
				</ul>
			</div>		  
					<!--主体内容-->		
					<div id="content" class="col-md-10 mainBody" >
					 <div style="width:100%;height:80px;" class="panel-title"></div>   					 
   							<sitemesh:body/>  
  					</div> 	
  					 <!--主体内容-->				
	     </div>
</div> 
</body>
</html>
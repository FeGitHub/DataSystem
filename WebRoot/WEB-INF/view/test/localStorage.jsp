<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
	管理页面
</title>
 <script src="${BASE_PATH}/js/validation/lang/jquery-1.11.1.min.js"></script>
  <script src="${BASE_PATH}/js/bootstrap.min.js"></script><!-- bootstrap的最小js支持 -->   
  <link type="text/css" rel="styleSheet" href="${BASE_PATH}/css/bootstrap.min.css"><!--引进bootstrap的样式 -->
  <link type="text/css" rel="styleSheet" href="${BASE_PATH}/css/template.css">
   <link type="text/css" rel="styleSheet" href="${BASE_PATH}/css/validation/validationEngine.jquery.css">
</head>
<body>
	<!--头部导航开始开始 -->
	<div class="navbar navbar-duomi navbar-static-top app-header" role="navigation">
    	<div class="container-fluid app-header">
     		<div class="navbar-header">
           		<span class="navbar-brand logo">数据管理系统</span>
			</div>
			<ul class="nav navbar-nav navbar-right">
      <li><a href="http://localhost:8081/go/goMenu#" class="logo"><span class="glyphicon glyphicon-user"></span>账号</a></li>
      <li><a href="http://localhost:8081/login/signOut" class="logo"><span class="glyphicon glyphicon-log-out"></span>退出</a></li>
    </ul>
		</div>
    </div>
    <!--头部导航结束 -->
    
   <!--中间主体部分开始 --> 
<div class="container-fluid">
			<div class="row">
			<div class="col-md-2 app-sidebar app-sidebar" id="menu">
			<!-- 左部菜单开始 -->
			<ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
						<!--第一行 -->
						<li class="active">
							<a href="http://localhost:8081/go/goMenu"><i class="glyphicon glyphicon-th-large"></i>首页 </a>
						</li>
						<!--第二行 -->
						<li>
							<a href="http://localhost:8081/go/goMenu#to" class="nav-header collapsed" data-toggle="collapse"><i class="glyphicon glyphicon-cog"></i>系统管理<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a>
							<!--第二行的子菜单 -->
							<ul id="to" class="nav nav-list collapse secondmenu" style="height: 0px;">
								<li><a href="http://localhost:8081/go/goMenu"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
								<li><a href="http://localhost:8081/go/goMenu"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
								<li><a href="http://localhost:8081/go/goMenu"><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
								<li><a href="http://localhost:8081/go/goMenu"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
								<li><a href="http://localhost:8081/go/goHello"><i class="glyphicon glyphicon-eye-open"></i>日志查看</a></li>
							</ul>
					     </li>
					     
					    <!--第三行 -->
					<li><a href="http://localhost:8081/warn/goWarnRule"><i class="glyphicon glyphicon-credit-card"></i>预警规则 </a></li>
						 <!--第四行 -->						
					<li>
							<a href="http://localhost:8081/go/goMenu#goinfo" class="nav-header collapsed" data-toggle="collapse"><i class="glyphicon glyphicon-cog"></i>待处理事项<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a>
							<!--第二行的子菜单 -->
							<ul id="goinfo" class="nav nav-list collapse secondmenu" style="height: 0px;">
								<li> <a href="javascript:void(0);" id="info"><i class="glyphicon glyphicon-globe"></i>信息提示<span class="label label-warning pull-right">5</span></a></li>
								<li><a href="http://localhost:8081/remind/goRemindList"><i class="glyphicon glyphicon-edit"></i>备忘提醒</a></li>							
							</ul>
					</li>
					   <!-- 第五行 -->
					<li><a href="http://localhost:8081/qrtz/goQuartzJob"><i class="glyphicon glyphicon-calendar"></i>定时任务</a></li>
					 <!-- 第六行 -->
					<li><a href="http://localhost:8081/go/goDataTables"><i class="glyphicon glyphicon-fire"></i>表格案例</a></li>				
					<li><a href="http://localhost:8081/go/goHello"><i class="glyphicon glyphicon-fire"></i>欢迎界面</a></li>
					
					<li>
						<a href="http://localhost:8081/go/goMenu#gowarn" class="nav-header collapsed" data-toggle="collapse"><i class="glyphicon glyphicon-cog"></i>预警判定<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a>						
							<ul id="gowarn" class="nav nav-list collapse secondmenu" style="height: 0px;">
								<li><a href="http://localhost:8081/warn/goOrderCheck" class="inMyPlace"><i class="glyphicon glyphicon-fire"></i>测试预警树</a></li>	
								<li><a href="http://localhost:8081/warn/goTh_OrderCheck" class="inMyPlace"><i class="glyphicon glyphicon-fire"></i>物料预警</a></li>							
							</ul>
					</li>
					
					<!--页面测试 -->
					<li>
						<a href="http://localhost:8081/go/goMenu#goTestPage" class="nav-header collapsed" data-toggle="collapse"><i class="glyphicon glyphicon-cog"></i>页面测试<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a>
							<ul id="goTestPage" class="nav nav-list collapse secondmenu" style="height: 0px;">
								<li><a href="http://localhost:8081/go/goValidate" class="inMyPlace"><i class="glyphicon glyphicon-fire"></i>测试表单验证</a></li>
								<li><a href="http://localhost:8081/test/gotoForm" class="inMyPlace"><i class="glyphicon glyphicon-fire"></i>zujian</a></li>																
							</ul>
					</li>					
				</ul>
			</div>		  
					<!--主体内容-->		
					<div id="content" class="col-md-10 mainBody">
					 <div style="width:100%;height:80px;" class="panel-title"></div>   					 
   							
<div class="panel-body">
    <h2 align="center">欢迎界面</h2>
    <button type="button" class="btn btn-info testMethod" style="float:right;margin-right:20px;">新增</button>
    <table class="table table-hover dataTable" style="margin-top:80px;">
    <tr class="active">
        <th>账号</th><th>密码</th><th>电话号码</th><th>操作</th>
    </tr>   
    </table>
 </div> 				
</div> 
<!--弹出框基本框架开始-->
				<div class="modal" id="seeMethodModal">
						<div class="modal-dialog modal-lg ptn" style="width:700px;"><!--修改这个width可以改变整个模态框的宽度-->
						    <div class="modal-content" >
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						        <h4 class="modal-title">账号生成</h4>
						      </div>
						      <div class="modal-body" style="height:250px;overflow:hidden;"><!--改变这个高度可以改变整个模态框的高度，但是内部元素可能撑破-->
						      </div>	
						      <div class="modal-footer">
                				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
               					 <button type="button" class="btn btn-primary" id="submitBtn">保存</button>
            				</div>     
					    </div>
					  </div>
				</div>
<!--弹出框主体框架结束-->

<!--弹出框内容模板-->
<template id="testTemp">
    <div class="panel mbn" style="height:450px;overflow-y:auto;">
       <div class="panel-body" >
       <form id="testFrom">
		<table class="table table-bordered table-hover">
          <tbody>
            <tr>
				<td class="va-m" width="20%"><span>用户账号</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input id="username" class="form-control validate[required]" placeholder="请输入用户名" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>
			 <tr>
				<td class="va-m" width="30%"><span>用户密码</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="pad" class="form-control validate[funcCall[rangeInputIsRight]]" placeholder="请输入密码" maxlength="20" type="password">      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>
             <tr>
				<td class="va-m" width="30%"><span>确认密码</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="repad" class="form-control validate[funcCall[rangeInputIsRight]]" placeholder="请再次输入密码" maxlength="20" type="password">      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>				
          </tbody>
        </table>
        </form>
      </div>
    </div>
</template>
<!--弹出框内容模板-->

<script src="${BASE_PATH}/js/validation/lang/jquery.validationEngine-zh_CN.js"></script>
<script src="${BASE_PATH}/js/validation/jquery.validationEngine.min.js"></script>	
<script>
var userJson = [];
	$(function(){
		testData();
		$('form').validationEngine({   
  			promptPosition: 'centerReight',
  			scroll:false
  		});
		 $("#submitBtn").click(function(){
			 if($("form").validationEngine('validate')){	
			 $("#seeMethodModal").modal("hide");
			   var username=$("#username").val();
			   var padvalue=$("#pad").val();
			    userJson = JSON.parse(localStorage.getItem("storageUserInfo")) == null ? [] : JSON.parse(localStorage.getItem("storageUserInfo"));  
			    var userInfo = {
					'username': username,
					'password': padvalue,
					'phone': 'fdffs'
		          };
		          userJson.push(userInfo);
		          localStorage.setItem("storageUserInfo", JSON.stringify(userJson));
		          userJson = JSON.parse(localStorage.getItem("storageUserInfo")) == null ? [] : JSON.parse(localStorage.getItem("storageUserInfo"));
		          setDataTable(userJson);
					//alert("通过");
				}
			}); 	
	});
 function rangeInputIsRight(field, rules, i, options){
 	var padvalue=$("#pad").val();
 	var repadvalue=$("#repad").val();
 	if(padvalue==""||repadvalue==""||(padvalue!=repadvalue)){
 		 rules.push('required');
 		 return "* 密码不一致";
 	}

 }

	  function setDataTable(data){
	  	var _html="";
	  	$("table tr").not(':eq(0)').empty();
		for(var i=0;i<data.length;i++){
				_html+="<tr><td>"+data[i].username+"</td><td>"+data[i].password+"</td><td>"+data[i].phone+"</td><td>编辑</td></tr>";
		}
		$(".dataTable tr:eq(0)").after(_html);
	  }


	  function testData(){
		userJson = JSON.parse(localStorage.getItem("storageUserInfo")) == null ? [] : JSON.parse(localStorage.getItem("storageUserInfo"));
		setDataTable(userJson);
	  }
	  $('body').on('click','.testMethod',function(){	
		 var htm = $($('#testTemp').html());
			htm.find('tr:eq(0)').find('.border-none').text("fdf");
			htm.find('tr:eq(1)').find('.border-none').text("fdf");	
			htm.find('tr:eq(2)').find('.border-none').text("fdf");		   
			var _html='<div>'+htm[0].outerHTML+'</div>';
			$("#seeMethodModal").modal("show");
			$("#seeMethodModal .modal-body").html(_html);			
	 })	
</script>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/component/headResource.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>  
    <title>管理模板</title>
    <link rel="stylesheet" type="text/css" href="${BASE_PATH}/css/test/main.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">
  </head>
  <body class="app sidebar-mini rtl"> 
  	 <%@ include file="/WEB-INF/view/component/div/basic.jsp" %>     
    <!--中间主体开始-->
    <main class="app-content"><!--主体灰色部分-->
      <div class="app-title">
        <div>
          <h1><i class="fa fa-dashboard"></i>测试</h1>
          <p>开源用户模板</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="#">某个链接</a></li>
        </ul>
      </div>
      
       <!-- 第一行  -->
      <div class="row">
        <div class="col-md-6 col-lg-3">
          <div class="widget-small primary coloured-icon"><i class="icon fa fa-users fa-3x"></i>
            <div class="info">
              <h4>用户</h4>
              <p><b>5</b></p>
            </div>
          </div>
        </div>
        
        <div class="col-md-6 col-lg-3">
          <div class="widget-small info coloured-icon"><i class="icon fa fa-thumbs-o-up fa-3x"></i>
            <div class="info">
              <h4>赞</h4>
              <p><b>25</b></p>
            </div>
          </div>
        </div>
        
        <div class="col-md-6 col-lg-3">
          <div class="widget-small warning coloured-icon"><i class="icon fa fa-files-o fa-3x"></i>
            <div class="info">
              <h4>上传</h4>
              <p><b>10</b></p>
            </div>
          </div>
        </div>
        
        <div class="col-md-6 col-lg-3">
          <div class="widget-small danger coloured-icon"><i class="icon fa fa-star fa-3x"></i>
            <div class="info">
              <h4>跟随</h4>
              <p><b>500</b></p>
            </div>
          </div>
        </div>
      </div>
      
      
      <!--第二行-->
      <div class="row">
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title">第一个画板</h3>
          <div class="embed-responsive embed-responsive-16by9">
              <canvas class="embed-responsive-item" id="pieChartDemo"></canvas>
            </div>
           
          </div>
        </div>
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title">第二个画板</h3>
             <div class="embed-responsive embed-responsive-16by9">
              <canvas class="embed-responsive-item" id="pieChartDemo"></canvas>
            </div>
           
          </div>
        </div>
      </div>
      
      <!--第三行-->
      <div class="row">
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title">展示行</h3>
            <ul>
              <li>看看</li>
              <li>权利加工辅导费飞士大夫</li>
              <li>发是第三方是都不让干豆腐干豆腐</li>                     
            </ul>           
          
            <p class="mt-4 mb-4"><a class="btn btn-primary mr-2 mb-2" href="http://pratikborsadiya.in/blog/vali-admin" target="_blank"><i class="fa fa-file"></i>Docs</a><a class="btn btn-info mr-2 mb-2" href="https://github.com/pratikborsadiya/vali-admin" target="_blank"><i class="fa fa-github"></i>GitHub</a><a class="btn btn-success mr-2 mb-2" href="https://github.com/pratikborsadiya/vali-admin/archive/master.zip" target="_blank"><i class="fa fa-download"></i>Download</a></p>
          </div>
        </div>
        
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title">其他</h3>
            <p>
            <p>诗，又称诗歌，是一种用高度凝练的语言，形象表达作者丰富情感，集中反映社会生活并具有一定节奏和韵律的文学体裁。诗乃文学之祖，艺术之根。诗是一种阐述心灵的文学体裁 <a href="https://github.com/pratikborsadiya/vali-admin" target="_blank">GitHub</a>.</p>
          </div>
        </div>
      </div>
    </main>
    <!--中间主体结束-->
    
    
     <script src="${BASE_PATH}/js/test/jquery-3.2.1.min.js"></script>
    <script src="${BASE_PATH}/js/test/popper.min.js"></script><!--先定义这个再定义bootstrap-->
    <script src="${BASE_PATH}/js/test/bootstrap.min.js"></script>
    <script src="${BASE_PATH}/js/test/main.js"></script> 
 <%--    <script src="${BASE_PATH}/js/test/plugins/pace.min.js"></script> --%>
  <%--   <script type="text/javascript" src="${BASE_PATH}/js/test/plugins/chart.js"></script> --%>

   
  </body>
</html>
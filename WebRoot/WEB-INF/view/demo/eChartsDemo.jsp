<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
</head>
<body>
        <div class="tile">
	    	<form action="${BASE_PATH}/demo/uploadFile" enctype="multipart/form-data" method="post"> 
	    	  <input type="file" name="file"/> 
	    	  <input type="submit"/> 
	    	</form> 
	    	<a href="${BASE_PATH}/demo/downfile">下载</a> 
	    </div>   
	    
	  <div class="tile">
	    	 <div id="main" style="height:400px;"></div>
	    </div>      
 	<div class="tile">
    	  <div id="container" style="height:400px;"></div>
    </div> 
    <div class="tile">
    	 <div id="pieChart" style="height:400px;"></div>
    </div>  
    
     <div class="tile">
    	 <div id="barnNegative" style="height:400px;"></div>
    </div>  
    <!--http://echarts.baidu.com/-->
    <script src="${BASE_PATH}/js/plugjs/echarts.js"></script>
    <script src="${BASE_PATH}/js/pagejs/demojs/eChartsDemo/walden.js"></script> <!--图标样式-->
    <script src="${BASE_PATH}/js/pagejs/demojs/eChartsDemo/eChartsDemo.js"></script>     
</body>
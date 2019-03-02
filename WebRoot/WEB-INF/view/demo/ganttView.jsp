<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${BASE_PATH}/plug/jQuery-ganttView/lib/jquery-ui-1.8.4.css" />
	<link rel="stylesheet" type="text/css" href="${BASE_PATH}/plug/jQuery-ganttView/ganttView.css" />
	<link rel="stylesheet" type="text/css" href="${BASE_PATH}/css/pagecss/democss/ganttViewEdit.css"><!--页面主体样式-->
	<title>jQuery Gantt</title>
</head>
<body>
    <div class="tile">   
     <!--  -->
           <div class="overlay">
              <div class="m-loader mr-4">
                <svg class="m-circular" viewBox="25 25 50 50">
                	<circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"/>
                </svg>
              </div>
              <h3 class="l-text">Loading</h3>
            </div>
          <!--  -->
    	<div id="ganttChart"></div>
	<br/><br/>
	<div id="eventMessage"></div>
	<div class="task" data-task='{ "name": "任务7", "start": "2018/01/11", "end": "2018/01/18" }'>
		<h2>任务7</h2>
		<p>预计开始时间：2018/1/12</p>
		<p>预计结束时间：2018/1/15</p>
	</div>
	<div class="task" data-task='{ "name": "任务6", "start": "2018/01/06", "end": "2018/01/13" }'>
		<h2>任务6</h2>
		<p>预计开始时间：2018/1/06</p>
		<p>预计结束时间：2018/1/13</p>
	</div>
	<button id="submit">提交</button>
	<div id="submitData"></div>
    </div>	 
	<script type="text/javascript" src="${BASE_PATH}/plug/jQuery-ganttView/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript" src="${BASE_PATH}/plug/jQuery-ganttView/jqueryui/1.9.0/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${BASE_PATH}/plug/jQuery-ganttView/ganttView.js"></script>
	<script type="text/javascript" src="${BASE_PATH}/plug/jQuery-ganttView/example/data.js"></script>
	<script type="text/javascript" src="${BASE_PATH}/js/pagejs/demojs/ganttView.js"></script>
</body>
</html>

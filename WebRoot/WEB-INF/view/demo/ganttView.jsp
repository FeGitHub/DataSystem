<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	 <link rel="stylesheet" type="text/css" href="${BASE_PATH}/plug/jQuery-ganttView/lib/jquery-ui-1.8.4.css" />
	<link rel="stylesheet" type="text/css" href="${BASE_PATH}/plug/jQuery-ganttView/ganttView.css" />
        <link rel="stylesheet" type="text/css" href="${BASE_PATH}/TemplatePlug/vali/css/main.css"><!--页面主体样式-->
	<style type="text/css">
		body {
			font-family: tahoma, verdana, helvetica;
			font-size: 0.8em;
			padding: 10px;
		}
		
		*, *::before, *::after {
    -webkit-box-sizing: content-box;
    box-sizing: content-box;
}
	</style>
	<title>jQuery Gantt</title>
</head>
<body>
  <div class="tile"> 
    	<!--  -->
    		<div id="ganttChart"></div>
	<br/><br/>
	<div id="eventMessage"></div>
	<div class="task" data-task='{ "name": "任务7", "start": "2018/01/11", "end": "2018/01/18" }'
		 style="background: #fff; overflow: hidden;margin: 10px 0; box-shadow:0 0 10px rgba(0,0,0,.3); width:200px;padding:10px;-webkit-user-select: none;user-select: none;">
		<h2>任务7</h2>
		<p>预计开始时间：2018/1/12</p>
		<p>预计结束时间：2018/1/15</p>
	</div>
	<div class="task" data-task='{ "name": "任务6", "start": "2018/01/06", "end": "2018/01/13" }'
		 style="background: #fff; overflow: hidden;margin: 10px 0;box-shadow:0 0 10px rgba(0,0,0,.3); width:200px;padding:10px;-webkit-user-select: none;user-select: none;">
		<h2>任务6</h2>
		<p>预计开始时间：2018/1/06</p>
		<p>预计结束时间：2018/1/13</p>
	</div>
	<button id="submit">提交</button>
	<div id="submitData"></div>
    	<!--  -->
   </div>	 
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/1.8.3/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jqueryui/1.9.0/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${BASE_PATH}/plug/jQuery-ganttView/ganttView.js"></script>
	<script type="text/javascript" src="${BASE_PATH}/plug/jQuery-ganttView/example/data.js"></script>
	<script type="text/javascript">
        $( ".task" ).draggable({
			revert: true,
            opacity: 0.7
		});
	</script>
	<script type="text/javascript">
		$(function () {
			$("#ganttChart").ganttView({
                // showWeekends: true,
				data: ganttData,
				behavior: {
					onClick: function (data) { 
						var msg = "click事件:" + JSON.stringify(data);
						$("#eventMessage").text(msg);
					},
					onResize: function (data) {
					    console.log(data);
						var msg = "resize事件:" + JSON.stringify(data);
						$("#eventMessage").text(msg);
					},
					onDrag: function (data) {
					    // console.log(data);
						var msg = "drag事件:" + JSON.stringify(data);
						$("#eventMessage").text(msg);
					}
				}
			});
			$("#submit").click(function () {
                $("#ganttChart").ganttView("getDatas", function (datas) {
                    console.log(datas);
                    $("#submitData").text(JSON.stringify(datas));
                });
            });
		});
	</script>

</body>
</html>

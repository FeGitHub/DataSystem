<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎界面</title> 
</head>
<body>
   <div class="row">
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title"></h3>
            <div class="embed-responsive embed-responsive-16by9">
              <canvas class="embed-responsive-item" id="lineChartDemo"></canvas>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title"></h3>
            <div class="embed-responsive embed-responsive-16by9">
              <canvas class="embed-responsive-item" id="pieChartDemo"></canvas>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title"></h3>
            <div class="embed-responsive embed-responsive-16by9">
              <canvas class="embed-responsive-item"></canvas>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title"></h3>
           <div class="embed-responsive embed-responsive-16by9">
              <canvas class="embed-responsive-item"></canvas>
            </div>
          </div>
        </div>
      </div>
	<script>
$(function () { 
	$("[data-toggle='popover']").popover();
	$("#test").attr("data-content","测试成功");
});
</script>
</body>
</html>
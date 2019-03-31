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
/* $(function () { 
	$("[data-toggle='popover']").popover();
	$("#test").attr("data-content","测试成功");
}); */
</script>
</body>
</html>
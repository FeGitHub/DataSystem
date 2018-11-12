<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${BASE_PATH}/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${BASE_PATH}/css/bootstrap-datetimepicker.min.css" type="text/css">
    <script src="${BASE_PATH}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="${BASE_PATH}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${BASE_PATH}/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
     <script src="${BASE_PATH}/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
    <title>时间控件</title>
    <script>
        $(function(){
            $('#datetimepicker1').datetimepicker({
                language:"zh-CN",
                bootcssVer:3,
                format: 'yyyy-mm-dd',  
                autoclose:true,
                todayHighlight: true,
                minView:2,
                weekStart:1
            }); 
            $('#datetimepicker2').datetimepicker({
                language:"zh-CN",
                bootcssVer:3,
                format: 'yyyy-mm-dd',  
                autoclose:true,
                todayHighlight: true,
                minView:2,
                weekStart:1
            });          
        });    
    </script>
</head>
<body>
<!--时间控件-->
<div class="form-inline" align="center">
		            <div class="form-group mr20 mt5" >						
		                  <div class="input-daterange input-group">
		                  	<span class="input-group-addon" style="border-left:1px solid #ddd;"><i class="glyphicon glyphicon-calendar"></i> </span>
						    <input type="text" id="datetimepicker1"  class="form-control" name="startDate" />
						    <span class="input-group-addon">to</span>
						    <input type="text" id="datetimepicker2"  class="form-control" name="endDate" />
					  	  </div>
		                </div>
	</div>
 <!--时间控件-->   
	<hr>
	<div class="container">
    <form action="" class="form-horizontal"  role="form">
        <fieldset>
            <legend>Test</legend>
            <div class="form-group">
                <label for="dtp_input1" class="col-md-2 control-label">DateTime Picking</label>
                <div class="input-group date form_datetime col-md-4" data-date="1979-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
				<input type="hidden" id="dtp_input1" value="" /><br/>
            </div>
			<div class="form-group">
                <label for="dtp_input2" class="col-md-2 control-label">Date Picking</label>
                <div class="input-group date form_date col-md-4" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
				<input type="hidden" id="dtp_input2" value="" /><br/>
            </div>
			<div class="form-group">
                <label for="dtp_input3" class="col-md-2 control-label">Time Picking</label>
                <div class="input-group date form_time col-md-4" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                </div>
				<input type="hidden" id="dtp_input3" value="" /><br/>
            </div>
        </fieldset>
    </form>
</div>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
    	language:"zh-CN",
        bootcssVer:3,
        format: 'yyyy-mm-dd',  
        autoclose:true,
        todayHighlight: true,
        minView:2,
        weekStart:1
    });
	$('.form_date').datetimepicker({
		language:"zh-CN",
        bootcssVer:3,
        format: 'yyyy-mm-dd',  
        autoclose:true,
        todayHighlight: true,
        minView:2,
        weekStart:1
    });
	$('.form_time').datetimepicker({
		language:"zh-CN",
        bootcssVer:3,
        format: 'yyyy-mm-dd',  
        autoclose:true,
        todayHighlight: true,
        minView:2,
        weekStart:1
    });
</script>
</body>
</html>

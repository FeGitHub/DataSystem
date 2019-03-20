<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>工程任务</title>
</head>
<body>
<div class="tile">
	<form action="" class="form-inline" id="filterForm">	
						<div class="form-group">
					        <label>任务名称</label>
					        <input type="text" class="form-control keepPlace" name="taskName" id="taskName">
					    </div>						    				       
		               <div class="input-daterange input-group keepPlace">
		               		<label>添加时间 &nbsp;&nbsp;&nbsp;</label>			                	       			                                
		                  	<span class="input-group-addon keepPlace"><i class="fa fa-calendar"></i></span>
						    <input type="text" id="datetimepicker1" class="form-control quaryTime" name="startDates" style="width:180px;">
						    <span class="input-group-addon">to</span>
						    <input type="text" id="datetimepicker2" class="form-control quaryTime" name="endDates" style="width:180px;">
						    	 <button type="button" id="querys" class="btn btn-info mt5 mr3 keepPlace" data-step="3" data-intro="点击查询按钮，按查询条件查出数据列表！">查询</button>	
						    	 <button type="button" id="reset" class="btn btn-default mt5 keepPlace" data-step="4" data-intro="点击重置将查询条件还原成默认查询状态！">重置</button>							       
						         <button type="button"  id="createTask-btn" class="btn btn-success mt5 keepPlace">新增</button>		    	    
					  	  </div>					  
	</form>	
</div>
<div class="tile">
	<table id="taskTable" class="display" style="width:100%">
    <thead>
        <tr>
            <th>任务名称</th>  
            <th>添加时间</th>
            <th>完成指标</th>
            <th>开始时间</th>     
            <th>截至时间</th>           
            <th>操作</th>            
        </tr>
    </thead>
    <tbody>      
    </tbody>
</table>
</div>
<script src="${BASE_PATH}/js/pagejs/task/targetList.js"></script>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>备忘提醒</title>

</head>
<body>
<div class="tile">
	<form action="" class="form-inline" id="filterForm">	
						<div class="form-group">
					        <label>事项简称</label>
					        <input type="text" class="form-control keepPlace" name="taskName" id="taskName">
					    </div>						    				       
		               <div class="input-daterange input-group keepPlace">
		               		<label>时间范围 &nbsp;&nbsp;&nbsp;</label>			                	       			                                
		                  	<span class="input-group-addon keepPlace"><i class="fa fa-calendar"></i></span>
						    <input type="text" id="datetimepicker1" class="form-control quaryTime" name="startDates" style="width:180px;">
						    <span class="input-group-addon">to</span>
						    <input type="text" id="datetimepicker2" class="form-control quaryTime" name="endDates" style="width:180px;">
						    	 <button type="button" id="querys" class="btn btn-info mt5 mr3 keepPlace" data-step="3" data-intro="点击查询按钮，按查询条件查出数据列表！">查询</button>	
						    	 <button type="reset" id="reset" class="btn btn-default mt5 keepPlace" data-step="4" data-intro="点击重置将查询条件还原成默认查询状态！">重置</button>	
						    	 <button type="button" class="btn btn-default mt5 keepPlace" data-step="4">新增</button>				    	    
					  	  </div>					  
	</form>	
</div>
<div class="tile">
	<table id="remindTable" class="display" style="width:100%">
    <thead>
        <tr>
            <th>事项简称</th>
            <th>具体事项</th>
            <th>截止时间</th>
            <th>提醒邮箱</th>
            <th>操作</th>            
        </tr>
    </thead>
    <tbody>      
    </tbody>
</table>
</div>
<!--弹出框基本框架开始-->
				<div class="modal" id="seeMethodModal">
						<div class="modal-dialog modal-lg ptn" style="width:600px;"><!--修改这个width可以改变整个模态框的宽度-->
						    <div class="modal-content" >
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						        <h4 class="modal-title"></h4>
						      </div>
						      <div class="modal-body" style="height:350px;overflow:hidden;"><!--改变这个高度可以改变整个模态框的高度，但是内部元素可能撑破-->
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
    <div class="panel mbn" style="height:550px;">
       <div class="panel-body" >
       <form id="testFrom" style="margin-top:20px;">
		<table class="table table-bordered table-hover">
          <tbody>       
            <tr>
				<td class="va-m" width="30%"><span>事项简称</span><input id="recordId" type="text" value="" style="display:none;" name="record.id" ></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input name="record.remindName" id="remindName" class="form-control validate[required]" placeholder="" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>       
			 <tr>
				<td class="va-m" width="30%"><span>具体事项</span></td>
              	<td>
	              	<div class="row">
		              	<div class="col-sm-12">
		              		  <textarea class="form-control validate[required]" rows="3" name="record.remindText" id="remindText"></textarea>    
		              	</div>                    	
	                 </div>	  
              	</td>              	
            </tr>     
            <tr>
				<td class="va-m" width="30%"><span>提醒时间</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  name="record.remindTime" id="remindTime" class="form-control validate[required] form_date" placeholder="" maxlength="20" type="text" readonly>      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>        
             <tr>
				<td class="va-m" width="30%"><span>提醒邮箱</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  name="record.mail" id="mail" class="form-control validate[required]" placeholder="" maxlength="20" type="text">      
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
<script src="${BASE_PATH}/js/pagejs/remindList.js"></script>
<script src="${BASE_PATH}/js/validation/lang/jquery.validationEngine-zh_CN.js"></script>
<script src="${BASE_PATH}/js/validation/jquery.validationEngine.min.js"></script>	
</body>
</html>
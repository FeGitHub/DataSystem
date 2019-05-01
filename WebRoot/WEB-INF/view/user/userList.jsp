<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
     <meta charset="UTF-8">
     <title>用户列表</title>
</head>
  <body>
		<div class="tile">
			<form action="" class="form-inline" id="filterForm">	
								<div class="form-group">
							        <label>用户名称</label>
							        <input type="text" class="form-control keepPlace" name="account" id="account">
							    </div>						    				       
				               <div class="input-daterange input-group keepPlace">
				               		<label>时间范围 &nbsp;&nbsp;&nbsp;</label>			                	       			                                
				                  	<span class="input-group-addon keepPlace"><i class="fa fa-calendar"></i></span>
								    <input type="text" id="datetimepicker1" class="form-control quaryTime" name="startDates" style="width:180px;">
								    <span class="input-group-addon">to</span>
								    <input type="text" id="datetimepicker2" class="form-control quaryTime" name="endDates" style="width:180px;">
								    	 <button type="button" id="querys" class="btn btn-info mt5 mr3 keepPlace" data-step="3" data-intro="点击查询按钮，按查询条件查出数据列表！">查询</button>	
								    	 <button type="button" id="reset" class="btn btn-default mt5 keepPlace" data-step="4" data-intro="点击重置将查询条件还原成默认查询状态！">重置</button>	
								    	 <button type="button" class="btn btn-default mt5 keepPlace" id="addBtn" data-step="4">新增</button>				    	    
							  	  </div>					  
			</form>	
		</div>
			<div class="tile">
				<table id="showTable" class="display" style="width:100%">
			    <thead>
			        <tr>
			            <th>用户账号</th>
			            <th>用户等级</th>
			            <th>用户邮箱</th>
			            <th>操作</th>            
			        </tr>
			    </thead>
			    <tbody>      
			    </tbody>
			</table>
			</div>
				<div class="modal" id="seeMethodModal">
						<div class="modal-dialog modal-lg ptn" style="width:600px;"><!--修改这个width可以改变整个模态框的宽度-->
						    <div class="modal-content" >
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						        <h4 class="modal-title"></h4>
						      </div>
						      <div class="modal-body">
						      </div>	
						      <div class="modal-footer">
                				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
               					 <button type="button" class="btn btn-primary" id="submitBtn">保存</button>
            				</div>     
					    </div>
					  </div>
				</div>
<template id="template">
    <div class="panel mbn">
       <div class="panel-body" >
       <form id="templateForm" style="margin-top:20px;">
		<table class="table table-bordered table-hover">
          <tbody>       
            <tr>
				<td class="va-m" width="30%"><span>用户账号</span><input id="recordId" type="text" value="" style="display:none;" name="id" ></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input name="account" id="accountId" class="form-control validate[required]" placeholder="" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>      
             <tr>
				<td class="va-m" width="30%"><span>用户邮箱</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  name="mail" id="mail" class="form-control validate[funcCall[checkMail]]" placeholder="" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>	          
			 <tr>
				<td class="va-m" width="30%"><span>用户角色</span></td>
              	<td>
	              	<div class="row">
				          <div class="col-sm-12">	                                     
			                     <div class="animated-radio-button">
						               <label>
						                   <input type="radio" name="leve" value="1"><span class="label-text">普通用户</span>
						               </label>
						                <label>
						                   <input type="radio" name="leve" value="2"><span class="label-text">管理员</span>
						                </label>						     
			                       </div>
				           </div>	                  	
	                 </div>	  
              	</td>              	
            </tr>       
            <tr>
				<td class="va-m" width="30%"><span>账号状态</span></td>
              	<td>
	              	<div class="row">
				          <div class="col-sm-12">	                                     
			                     <div class="animated-radio-button">
						               <label>
						                   <input type="radio" name="userFlag" value="0"><span class="label-text">停用</span>
						               </label>
						                <label>
						                   <input type="radio" name="userFlag" value="1"><span class="label-text">启用</span>
						                </label>						     
			                       </div>
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
<script src="${BASE_PATH}/js/pagejs/user/userList.js"></script>
<script src="${BASE_PATH}/js/validation/lang/jquery.validationEngine-zh_CN.js"></script>
<script src="${BASE_PATH}/js/validation/jquery.validationEngine.min.js"></script>	
</body>
</html>
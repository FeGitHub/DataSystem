<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>工程任务树</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" href="${BASE_PATH}/plug/ztree-bootstrap/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
</head>
<body>
    <div class="tile">
                <!--  <button id="popBtn" onclick="showProgess(this)" type="button" class="btn btn-primary">弹出</button>&nbsp;&nbsp;    	    	 -->
    	    	 <!-- <button id="testBtn" type="button" class="btn btn-primary">输出树形数据</button>&nbsp;&nbsp; -->
    	    	 <button id="projectInfBtn" type="button" class="btn btn-primary">修改工程信息</button>&nbsp;&nbsp;
    	    	<!--  <button id="updateBtn" type="button" class="btn btn-primary">提交工程任务</button>   -->	
    	    	<a class="btn btn-secondary" href="${BASE_PATH}/task/goProjectList">返回工程列表</a>&nbsp;&nbsp;    
    	    	<button id="analyseProjectInfBtn" type="button" class="btn btn-primary">分析工程信息</button>                   	    	 
    </div>
    
       <div class="card mb-3 border-info" style="display:none;">
                <div class="card-body">
                  <blockquote class="card-blockquote">
                    <p id="result"></p>
                  </blockquote>
                </div>
              </div>
    
    
     <div class="tile">   
         	   <div class="bs-component">
              <div class="progress mb-2" onclick="showProgess(this)">
                <div id="progress"  class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"  data-msg="${remindDay}" data-pnum="${pnum}" style="width:${pct}"></div>              
              </div>
            </div> 
     </div>
       <div class="tile">
          <div class="overlay">
              <div class="m-loader mr-4">
                <svg class="m-circular" viewBox="25 25 50 50">
                	<circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"/>
                </svg>
              </div>
              <h3 class="l-text">加载中</h3>
            </div>
		    <div class="row">
		    	<div class="col-md-6">	     
		    	 <div id="pieChart" style="height:400px;"></div>
		    	</div>	
		    	<div class="col-md-6">  	  
				    	 <ul id="projectTree" class="ztree"></ul>
				    	 <input type="text" style="display:none;" id="projectId" value="${projectId}"> 
				    	 <input type="text" style="display:none;" id="userId" value="${userId}"> 
				    	 <input type="text" style="display:none;" id="nodeId">   	          
		    	</div>
		    </div>
    </div>

				<div class="modal" id="seeMethodModal">
						<div class="modal-dialog modal-lg ptn" style="width:600px;"><!--修改这个width可以改变整个模态框的宽度-->
						    <div class="modal-content" >
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						        <h4 class="modal-title"></h4>
						      </div>
						      <div class="modal-body" >
						      </div>	
						      <div class="modal-footer">
                				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
               					 <button type="button" class="btn btn-primary" id="updateBtn">保存</button>
            				</div>     
					    </div>
					  </div>
				</div>
<template id="taskTemplate">
    <div class="panel mbn">
       <div class="panel-body" >
       <form id="taskForm" style="margin-top:20px;">
		<table class="table table-bordered table-hover">
          <tbody> 
          <tr>
				<td class="va-m" width="30%"><span>任务名称</span><input id="remindId" type="text" value="" style="display:none;" name="id" ></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input name="taskName" id="taskName" class="form-control validate[required]" placeholder="" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>                                     		   
            <tr>          
				<td class="va-m" width="30%"><span>开始时间</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  name="startDate" id="startDate" class="form-control  form_date" placeholder="" maxlength="20" type="text" readonly>      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>  
            <tr>
				<td class="va-m" width="30%"><span>结束时间</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  name="endDate" id="endDate" class="form-control  form_date" placeholder="" maxlength="20" type="text" readonly>      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>       
            <tr>
				<td class="va-m" width="30%"><span>任务描述</span></td>
              	<td>
	              	<div class="row">
		              	<div class="col-sm-12">
		              		  <textarea class="form-control" rows="3" name="description" id="description"></textarea>    
		              	</div>                    	
	                 </div>	  
              	</td>              	
            </tr>    
            
            <tr>
				<td class="va-m" width="30%"><span>工程进度</span></td>
              	<td>
	              	<div class="row">
				          <div class="col-sm-12">	                                     
			                     <div class="animated-radio-button">
						               <label>
						                   <input type="radio" name="underway" value="0"><span class="label-text">未开始</span>
						               </label>
						                <label>
						                   <input type="radio" name="underway" value="1"><span class="label-text">进行中</span>
						                </label>
						                <label>
						                    <input type="radio" name="underway" value="2"><span class="label-text">已完成</span>
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
<template id="projectTemplate">
    <div class="panel mbn">
       <div class="panel-body" >
       <form id="projectForm" style="margin-top:20px;">
		<table class="table table-bordered table-hover">
          <tbody>       
            <tr>
				<td class="va-m" width="30%"><span>工程名称</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="projectName" class="form-control validate[required]" placeholder="" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>       			   
            <tr>
				<td class="va-m" width="30%"><span>开始时间</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="startDate" class="form-control validate[required] form_date" placeholder="" maxlength="20" type="text" readonly>      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>        
             <tr>
				<td class="va-m" width="30%"><span>结束时间</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input   id="finshDate" class="form-control validate[required] form_date" placeholder="" maxlength="20" type="text" readonly>      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>   
             <tr>
				<td class="va-m" width="30%"><span>完成时间</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="actuallyFinshDate" class="form-control  form_date" placeholder="" maxlength="20" type="text" readonly>      
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
    <script src="${BASE_PATH}/js/plugjs/echarts.js"></script>
    <script src="${BASE_PATH}/js/pagejs/demojs/eChartsDemo/walden.js"></script> 
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.exedit.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/js/pagejs/task/projectTree.js"></script>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>

<HEAD>
    <TITLE>工程任务树</TITLE>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="${BASE_PATH}/plug/ztree-bootstrap/css/bootstrapStyle/bootstrapStyle.css" type="text/css"> 
</HEAD>

<BODY>
    <div class="tile">    	    	
    	    	 <button id="testBtn" type="button" class="btn btn-primary">输出树形数据</button>&nbsp;&nbsp;
    	    	 <button id="updateBtn" type="button" class="btn btn-primary">提交工程任务</button>
    </div>
    <div class="tile">
    	 <ul id="treeDemo" class="ztree"></ul>
    	 <input type="text" style="display:none;" id="projectId" value="${projectId}"> 
    	 <input type="text" style="display:none;" id="nodeId">   	
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
<template id="remindTemp">
    <div class="panel mbn" style="height:550px;">
       <div class="panel-body" >
       <form id="remindForm" style="margin-top:20px;">
		<table class="table table-bordered table-hover">
          <tbody>                       		   
            <tr>
				<td class="va-m" width="30%"><span>开始时间</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  name="startDate" id="startDate" class="form-control validate[required] form_date" placeholder="" maxlength="20" type="text" readonly>      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>  
            <tr>
				<td class="va-m" width="30%"><span>结束时间</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  name="endDate" id="endDate" class="form-control validate[required] form_date" placeholder="" maxlength="20" type="text" readonly>      
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

<%--     <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.min.js"></script> --%>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.exedit.js"></script>
        <script src="${BASE_PATH}/js/validation/lang/jquery.validationEngine-zh_CN.js"></script>
    <script src="${BASE_PATH}/js/validation/jquery.validationEngine.min.js"></script>	
        <script type="text/javascript" src="${BASE_PATH}/js/pagejs/task/projectTree.js"></script>
</BODY>

</HTML>
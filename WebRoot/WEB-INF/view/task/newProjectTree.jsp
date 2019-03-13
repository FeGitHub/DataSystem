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
    	    	<!--  <button id="addBtn" type="button" class="btn btn-primary">新增工程树</button>&nbsp;&nbsp; -->
    	    	 <button id="testBtn" type="button" class="btn btn-primary">输出树形数据</button>&nbsp;&nbsp;
    	    	  <button id="submitBtn" type="button" class="btn btn-primary">提交工程任务</button>
    </div>
    <div class="tile">
    	 <ul id="treeDemo" class="ztree"></ul>
    	 <input type="text" style="display:none;" id="projectId" value="${projectId}">  	
    </div> 
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.exedit.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/js/pagejs/task/newProjectTree.js"></script>
</BODY>

</HTML>
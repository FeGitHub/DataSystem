<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>菜单编辑</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${BASE_PATH}/plug/ztree-bootstrap/css/bootstrapStyle/bootstrapStyle.css" type="text/css">  
</head>
<body>
    <div class="tile">
          <div class="overlay">
              <div class="m-loader mr-4">
                <svg class="m-circular" viewBox="25 25 50 50">
                	<circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"/>
                </svg>
              </div>
              <h3 class="l-text">加载中</h3>
            </div>    
    	 <ul id="menuTree" class="ztree"></ul>
    	 <button id="updateMenuBtn" type="button" class="btn btn-primary">保存</button>
    	 <button id="testMenuBtn" type="button" class="btn btn-primary">测试</button>
    </div> 
  <%--   <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.min.js"></script> --%>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.exedit.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/js/pagejs/menuSettings.js"></script>
</body>
</html>
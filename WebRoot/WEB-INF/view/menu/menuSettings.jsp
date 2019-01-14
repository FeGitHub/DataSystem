<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>

<HEAD>
    <TITLE>菜单编辑</TITLE>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${BASE_PATH}/plug/ztree-bootstrap/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
   
</HEAD>

<BODY>

    <div class="tile">
    	 <ul id="menuTree" class="ztree"></ul>
    	 <button id="updateMenuBtn" type="button" class="btn btn-primary">保存</button>
    	 <button id="testMenuBtn" type="button" class="btn btn-primary">测试</button>
    </div> 
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.exedit.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/js/pagejs/menuSettings.js"></script>
</BODY>

</HTML>
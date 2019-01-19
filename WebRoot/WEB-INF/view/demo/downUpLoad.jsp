<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>上传和下载</title>
</head>
<body>
       <%--  <div class="tile">
	    	<form action="${BASE_PATH}/file/uploadFileToProject" enctype="multipart/form-data" method="post"> 
	    	  <input type="file" name="file"/> 
	    	  <input type="submit"/> 
	    	</form> 
	    	<a href="${BASE_PATH}/file/downfile">test.xls下载</a> 
	    </div>    --%>
	    <div class="tile">
	    
	    	  <input type="file" name="file" id="fileid"/> 
	    	  <input type="submit" id="submitBtn"/> 
	    
	    	<a href="${BASE_PATH}/file/downfile">test.xls下载</a> 
	    </div>
	    
	     <div class="tile">	    
	    	  <input type="file" name="file" id="testId"/> 
	    	  <button type="button" id="testBtn">测试</button>    
	    </div>
	     <script src="${BASE_PATH}/js/pagejs/demojs/downUpLoad.js"></script>     
</body>
</html>
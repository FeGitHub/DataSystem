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
	         <h4>上传文件到服务器(项目路径)</h4>
	    	  <input type="file" name="file" id="fileid"/> 
	    	  <button type="button" id="submitBtn">上传</button>
	    
	    	<%-- <a href="${BASE_PATH}/file/downfile">test.xls下载</a>  --%>
	    	<a class="btn btn-success mr-2 mb-2" href="${BASE_PATH}/file/downfile" target="_blank"><i class="fa fa-download"></i>下载</a>
	    </div>
	    
	     <div class="tile">	   
	       <h4>上传文件到服务器(任意路径)</h4> 
	    	  <input type="file" name="file" id="testId"/> 
	    	  <button type="button" id="testBtn">测试</button>    
	    </div>	    
	    	    
	     <div class="tile">	   
	      <h4>导入财务信息</h4>  
	    	  <input type="file" name="file" id="financeId"/> 
	    	  <button type="button" id="financeBtn">导入</button>    
	    </div>
	     <script src="${BASE_PATH}/js/pagejs/demojs/downUpLoad.js"></script>     
</body>
</html>
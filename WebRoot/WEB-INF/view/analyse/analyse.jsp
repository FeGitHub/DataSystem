<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>数据分析</title>
</head>
<body>   	    	   
	    
	     <div class="tile">	   
	       <h4>CSV</h4> 
	    	  <input type="file" name="file" id="csv" data-csv="" /> 
	    	  <button type="button" id="readCSVBtn">测试CSV</button>    
	    </div>	    
	    	    
	     <div class="tile">	   
	      <h4>导入财务信息</h4>  
	    	  <input type="file" name="file" id="financeId"/> 
	    	  <button type="button" id="financeBtn">导入</button>    
	    </div>
	    
	     <div class="tile">	   
	      <h4>测试</h4>  
	    	  <button type="button" id="testBtn">测试</button>    
	    </div>
	     <script src="${BASE_PATH}/js/pagejs/analyse/analyse.js"></script>     
</body>
</html>
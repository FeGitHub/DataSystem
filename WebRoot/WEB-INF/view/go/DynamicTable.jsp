<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery动态创建表格</title>
<script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script>
<link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/bootstrap.min.css" />
<link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/DynamicTable.css" />
</head>
<body>
	<div align="center">
		<button id="createTableBtn">生成动态表格</button>
		<div id="createTableArea"></div>
	</div>
	<div id="light" class="white_content"><!--弹出框-->
	 <div align="center">
    	<div id='popupTable'></div>	      
        	<button onclick="closePopup()">关闭</button>
            <button onclick="savePopup()">保存</button>
        </div>
    </div>
  
  <div id="fade" class="black_overlay"><!--黑布-->
  </div>
     <script src="${BASE_PATH}/js/bootstrap.min.js"></script>
    <script src="${BASE_PATH}/js/DynamicTable.js"></script>
</body>

</html>
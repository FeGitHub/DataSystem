<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
  <title>自定义弹出框</title>
  <link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/popup.css" />
  <script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script>
   <script>
   function popup(){
	   $("#light").css("display","block");
	   $("#fade").css("display","block"); 
   }
   	function closePopup(){
	   $("#light").css("display","none");
	   $("#fade").css("display","none");

    }
   </script>
</head>
<body>
  <p>可以根据自己要求修改css样式
    <a href="javascript:void(0)" onclick="popup()">点击这里打开窗口</a>
  </p>
  
  <div id="light" class="white_content"><!--弹出框-->
    		  输入框：<input type="text">
    <a href="javascript:void(0)" onclick="closePopup()">Close</a>
  </div>
  
  <div id="fade" class="black_overlay"><!--黑布-->
  </div>
</body>
</html>
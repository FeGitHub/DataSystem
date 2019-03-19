<%@ page contentType="text/html;charset=UTF-8"%>  
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>   
<!DOCTYPE html>
<html>
  <head>
  <title>
		<sitemesh:title/>
   </title>  
   <script type="text/javascript">
    var basepath= '${BASE_PATH}';
  </script> 
    <sitemesh:head/>   
     <link href="${BASE_PATH}/css/toastr.min.css" type="text/css" rel="stylesheet">  
  </head>
  <body >
  <main>
    <sitemesh:body/>        
   </main>
  <script src="${BASE_PATH}/js/toastr.min.js"></script><!--信息提示框-->
  
  </body>
</html>
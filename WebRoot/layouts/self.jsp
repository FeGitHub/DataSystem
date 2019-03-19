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
      
  </head>
  <body >
  <main>
    <sitemesh:body/>        
   </main>

  </body>
</html>
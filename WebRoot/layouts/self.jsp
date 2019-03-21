<%@ page contentType="text/html;charset=UTF-8"%>  
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
  <head>
  <title>
		<sitemesh:title/>
   </title>  
   <script type="text/javascript">
    var basepath= '${BASE_PATH}';
  </script> 
    <link rel="stylesheet" href="${BASE_PATH}/css/font-awesome.min.css"> 
    <sitemesh:head/>   
      <link rel="stylesheet" type="text/css" href="${BASE_PATH}/css/template/self.css">
     <link href="${BASE_PATH}/css/toastr.min.css" type="text/css" rel="stylesheet">  
  </head>
  <body >
  <header class="app-header"><a class="app-header__logo" href="index.html"></a>
      <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>   
       <ul class="app-nav">
        <li class="app-search">
          <input class="app-search__input" type="search" placeholder="Search">
          <button class="app-search__button"><i class="fa fa-search"></i></button>
        </li>        
      </ul>
    </header>
    <%-- <aside class="app-sidebar">
      <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="${BASE_PATH}/images/48.jpg" alt="User Image">
        <div>
        <p class="app-sidebar__user-name">用户名</p>
          <p class="app-sidebar__user-designation">描述</p>
         </div>
         </div>

           <ul class="app-menu">
               <a class="app-menu__item">
                 <i class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">首页</span>
                  <i class="treeview-indicator fa fa-angle-right"></i>
                  </a>
             </ul>
    </aside> --%>
 
  <main  class="app-content">
       <div class="app-title">
        <div>
          <h1><i class="fa fa-dashboard"></i></h1>
        </div>   
      </div>
    <sitemesh:body/>        
   </main>
  <script src="${BASE_PATH}/js/toastr.min.js"></script><!--信息提示框-->
  </body>
</html>
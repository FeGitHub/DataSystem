<%@ page contentType="text/html;charset=UTF-8"%>  
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>
			<sitemesh:title/>
    </title>      
    <link rel="stylesheet" type="text/css" href="${BASE_PATH}/plug/vali/css/main.css"><!--页面主体样式-->
   	    <%@ include file="/WEB-INF/view/component/head.jsp" %>  
   	     <sitemesh:head/> 
  </head>
  <body class="app sidebar-mini rtl">
    <header class="app-header"><a class="app-header__logo" href="index.html"></a>
      <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
      <ul class="app-nav">
        <li class="app-search">
          <input class="app-search__input" type="search" placeholder="搜索">
          <button class="app-search__button"><i class="fa fa-search"></i></button>
        </li>
        <!--通知信息菜单-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Show notifications"><i class="fa fa-bell-o fa-lg"></i></a>
          <ul class="app-notification dropdown-menu dropdown-menu-right">
            <li class="app-notification__title">你有4个新的通知</li>
            <div class="app-notification__content">	                      
                       <c:forEach items="${notifications}" var="item">                       
					<li>
	  					 <a class="app-notification__item" href="${item.url}">
							<span class="app-notification__icon">
								<span class="fa-stack fa-lg">
									<i class="${item.style}"></i><i class="${item.icon}"></i>
								</span>
							</span>
	    				<div>
	      					<p class="app-notification__message">${item.message}</p>
	      					<p class="app-notification__meta">${item.meta}</p>
	   				 	</div>
	  			 	 </a>    
  			 		</li>
			</c:forEach>                
            </div>
            <li class="app-notification__footer"><a href="#">查看所有通知</a></li>
          </ul>
        </li>
        <!--用户菜单-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i class="fa fa-user fa-lg"></i></a>
          <ul class="dropdown-menu settings-menu dropdown-menu-right">
            <li><a class="dropdown-item" href="page-user.html"><i class="fa fa-cog fa-lg"></i> 设置</a></li>
            <li><a class="dropdown-item" href="page-user.html"><i class="fa fa-user fa-lg"></i> 账号</a></li>
            <li><a class="dropdown-item" id="loginOut"><i class="fa fa-sign-out fa-lg"></i> 登出</a></li>
          </ul>
        </li>
      </ul>
    </header>
    <!--功能菜单-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <aside class="app-sidebar">
      <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="${BASE_PATH}/images/48.jpg" alt="User Image">
        <div>
          <p class="app-sidebar__user-name">用户名</p>
          <p class="app-sidebar__user-designation">描述</p>
        </div>
      </div>
      <ul class="app-menu" id="menuId">
    	<c:forEach items="${menuTree}" var="item"> 
        		<c:choose>
					<c:when test="${item.subMenuList ne null}">
						 <li class="treeview">
						 	  <a class="app-menu__item" href="${item.url}" data-toggle="treeview">
						 	  	<i class="${item.icon}"></i><span class="app-menu__label">${item.name}</span>
	        		            <i class="treeview-indicator fa fa-angle-right"></i>
						 	  </a>
						 	   <ul class="treeview-menu">	
							       <c:forEach items="${item.subMenuList}" var="subMenu"> 	       
			            		       <li><a class="treeview-item" href="${subMenu.url}"><i class="icon fa fa-circle-o"></i>${subMenu.name}</a></li>
							        </c:forEach> 
		                       </ul>
						 </li>
					</c:when>
                    <c:otherwise>
						<li>
	        			<a class="app-menu__item menuUrl" href="${item.url}">
	        		   <i class="${item.icon}"></i><span class="app-menu__label">${item.name}</span>
	        	      </a>
	                 </li>
                    </c:otherwise>
                 </c:choose>
        	</c:forEach> 
      </ul>
    </aside>
    <main class="app-content"> 
      <div class="app-title">
        <div>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="#">当前目录</a></li>
        </ul>
      </div>           
      <sitemesh:body/>       
    </main>   
	<%@ include file="/WEB-INF/view/component/footer.jsp" %>        
  </body>
</html>
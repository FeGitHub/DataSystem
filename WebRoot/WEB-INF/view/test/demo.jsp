<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%
    	List<String> list = new ArrayList<String>();
    	list.add("简单是可靠的先决条件");
    	list.add("兴趣是最好的老师");
    	list.add("知识上的投资总能得到最好的回报");
    	request.setAttribute("list", list);
     %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>

</title>
<%@ include file="/WEB-INF/view/component/headResource.jsp" %>
   <link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/plugcss/metisMenu.min.css" />
   <link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/pagecss/MyMetisMenu.css" />
   <link rel="shortcut icon" href="${BASE_PATH}/favicon.ico">
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top">
    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li class="sidebar-search">
                    <div class="input-group custom-search-form">
                        <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
                </li>
                <li>
                    <a href=""><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Charts<span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="">Flot Charts</a>
                        </li>
                        <li>
                            <a href="">Morris.js Charts</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href=""><i class="fa fa-table fa-fw"></i> Tables</a>
                </li>
                <li>
                    <a href=""><i class="fa fa-edit fa-fw"></i> Forms</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-wrench fa-fw"></i> UI Elements<span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="">Panels and Wells</a>
                        </li>
                        <li>
                            <a href="">Buttons</a>
                        </li>
                        <li>
                            <a href="">Notifications</a>
                        </li>
                        <li>
                            <a href="">Typography</a>
                        </li>
                        <li>
                            <a href=""> Icons</a>
                        </li>
                        <li>
                            <a href="">Grid</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="#">Second Level Item</a>
                        </li>
                        <li>
                            <a href="#">Second Level Item</a>
                        </li>
                        <li>
                            <a href="#">Third Level <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="active">
                    <a href="#"><i class="fa fa-files-o fa-fw"></i> Sample Pages<span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="active" href="#">Blank Page</a>
                        </li>
                        <li>
                            <a href="#">Login Page</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<script type="text/javascript" src="${BASE_PATH}/js/layer/layer.js"></script>
<script type="text/javascript" src="${BASE_PATH}/js/plugjs/jquery.metisMenu.js"></script>
<script type="text/javascript">
$(function() {
    $('#side-menu').metisMenu(); // ul.nav#side-menu
})
</script>
</body>
</html>
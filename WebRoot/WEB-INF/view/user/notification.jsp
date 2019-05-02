<%@ page contentType="text/html;charset=UTF-8"%>  
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
  <head>
  <meta charset="UTF-8">
<title>信息详情</title> 
  </head>
  <body>   
   
      <div class="tile mb-4">   
        <div class="row">
          <div class="col-lg-12">
            <h2 class="mb-3 line-head" id="type-blockquotes">${info.subject}</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <div class="bs-component">
              <blockquote class="blockquote">
                <p>${info.content}</p>     
              </blockquote>
            </div>
          </div>    
        </div>
        
        <div class="row">
          <div class="col-lg-12">
            <h2 class="mb-3 line-head" id="type-blockquotes"></h2>
          </div>
        </div>      
          <div class="row">
          <div class="col-lg-12">             
                  <a class="btn btn-secondary" href="${BASE_PATH}/demo/goMailBox"><i class="fa fa-fw fa-lg fa-times-circle"></i>返回</a>&nbsp;&nbsp;&nbsp; 
                  <c:choose>
				     <c:when test="${info.readFlag==0}">
						  <button type="button"  id="nidBtn" class="btn btn-info mt5 mr3 keepPlace" data-step="3">点击处理</button>
					  </c:when>									  									   								   
			      </c:choose>        
                  <input type="text" id="nid" value="${info.id}" style="display:none;">	        
          </div>
        </div>        
      </div>
      <script src="${BASE_PATH}/js/pagejs/user/notification.js"></script>
  </body>
</html>
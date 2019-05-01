
<%@ page language="java" pageEncoding="UTF-8"%>
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
               <!--  <footer class="blockquote-footer">信息来源 
                  <cite title="Source Title">jeff</cite>
                </footer> -->
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
             <button type="button"  id="nidBtn" class="btn btn-info mt5 mr3 keepPlace" data-step="3">点击处理</button>
              <input type="text" id="nid" value="${info.id}" style="display:none;">	
          </div>
        </div>        
      </div>
      <script src="${BASE_PATH}/js/pagejs/user/notification.js"></script>
  </body>
</html>
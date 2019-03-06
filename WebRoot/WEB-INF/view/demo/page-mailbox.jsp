<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
  <head> 
    <title>邮箱</title>
    <meta charset="utf-8">   
  </head>
  <body>
    <main>
      <div class="row">
        <div class="col-md-3"><a class="mb-2 btn btn-primary btn-block" href="">邮箱信息</a>
          <div class="tile p-0">
            <h4 class="tile-title folder-head">文件夹</h4>
            <div class="tile-body">
              <ul class="nav nav-pills flex-column mail-nav">
                <li class="nav-item active"><a class="nav-link" href="#"><i class="fa fa-inbox fa-fw"></i>信箱<span class="badge badge-pill badge-primary float-right">12</span></a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="fa fa-envelope-o fa-fw"></i>发送</a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="fa fa-file-text-o fa-fw"></i>草稿</a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="fa fa-filter fa-fw"></i>拦截<span class="badge badge-pill badge-primary float-right">8</span></a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="fa fa-trash-o fa-fw"></i>垃圾</a></li>
              </ul>
            </div>
          </div>
        </div>
        <div class="col-md-9">
          <div class="tile">
            <div class="mailbox-controls">
              <div class="animated-checkbox">
                <label>
                  <input type="checkbox" name="all"><span class="label-text"></span>
                </label>
              </div>
              <div class="btn-group">
                <button class="btn btn-primary btn-sm" type="button" id="delBtn"><i class="fa fa-trash-o"></i></button>
                <button class="btn btn-primary btn-sm" type="button"><i class="fa fa-reply"></i></button>
                <button class="btn btn-primary btn-sm" type="button"><i class="fa fa-share"></i></button>
                <button class="btn btn-primary btn-sm" type="button"><i class="fa fa-refresh"></i></button>
              </div>
            </div>
            <div class="table-responsive mailbox-messages">
              <table class="table table-hover">
                <tbody id="tb">            
                <c:forEach items="${mailList}" var="item">                      
               <tr>
                 	 <td>
                      <div class="animated-checkbox">
                        <label>
                          <input type="checkbox" name="itemBox" value="${item.id}" onclick="userCheck(this)"><span class="label-text"> </span>
                        </label>
                      </div>
                    </td>
                    <td><a href="#"><i class="fa fa-star-o"></i></a></td>
                    <td><a href="read-mail.html">${item.sender}</a></td>
                    <td class="mail-subject"><b>${item.subject}</b>${item.content}</td>
                    <td><i class="fa fa-paperclip"></i></td>
                    <td>${item.meta}</td>
                  </tr>    
                </c:forEach>                                    
                </tbody>
              </table>
            </div>
            <div class="text-right"><span class="text-muted mr-2">共${fn:length(mailList)}条信息</span>
              <div class="btn-group">
                <button class="btn btn-primary btn-sm" type="button"><i class="fa fa-chevron-left"></i></button>
                <button class="btn btn-primary btn-sm" type="button"><i class="fa fa-chevron-right"></i></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main> 
    <script src="${BASE_PATH}/js/pagejs/demojs/page-mailbox.js"></script> 
  </body>
</html>
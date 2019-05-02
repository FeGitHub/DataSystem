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
        <div class="col-md-12">
          <div class="tile">
            <div class="mailbox-controls">
              <div class="animated-checkbox">
                <label>
                  <input type="checkbox" name="all"><span class="label-text"></span>
                </label>
              </div>
              <div class="btn-group">
                <button class="btn btn-primary btn-sm" type="button" id="delBtn"><i class="fa fa-trash-o"></i></button>
              <!--   <button class="btn btn-primary btn-sm" type="button"><i class="fa fa-reply"></i></button>
                <button class="btn btn-primary btn-sm" type="button"><i class="fa fa-share"></i></button> -->
                <button class="btn btn-primary btn-sm" type="button" id="refreshBtn"><i class="fa fa-refresh"></i></button>
              </div>
            </div>
            <div class="table-responsive mailbox-messages">
              <table class="table table-hover">
                <tbody id="tb">          
                 <script id="appNotification" type="text/x-handlebars-template">
 				{{#each mailList}}
					<tr>
                 	 <td>
                      <div class="animated-checkbox">
                        <label>
                          <input type="checkbox" name="itemBox" value="{{id}}" onclick="userCheck(this)"><span class="label-text"> </span>
                        </label>
                      </div>
                    </td>
                    <td><a href="javascript:void(0);" data-id="{{id}}" class="goNotification">                        
                        {{#equal readFlag 0}}
                             <i class="fa fa-star"></i>
                        {{else}}
                             <i class="fa fa-star-o"></i>
                         {{/equal}}
                        </a>
                   </td>
                    <td><a href="javascript:void(0);" data-id="{{id}}" class="goNotification">{{sender}}</a></td>
                    <td class="mail-subject"><b>{{subject}}</b>{{content}}</td>
                    <td><i class="fa fa-paperclip"></i></td>
                    <td>{{meta}}</td>
                  </tr>  
			     {{/each}}
	         </script>                                
                </tbody>
              </table>
            </div>
            <div class="text-right"><span class="text-muted mr-2">第<span id="pageId">1</span>页，总计<span id="sizeId"></span>条信息</span>
              <div class="btn-group">
                <input type="text" id="pageNumberId" value="1" style="display:none;"/>
                <input type="text" id="endPageNumberId" value="${endPageNumber}" style="display:none;"/>
                <button class="btn btn-primary btn-sm"  id="prevId" type="button"><i class="fa fa-chevron-left"></i></button>
                <button class="btn btn-primary btn-sm" id="nextId" type="button"><i class="fa fa-chevron-right"></i></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main> 
    <script src="${BASE_PATH}/js/pagejs/demojs/page-mailbox.js"></script> 
  </body>
</html>
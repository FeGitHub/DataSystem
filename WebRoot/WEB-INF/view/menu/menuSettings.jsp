<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>菜单编辑</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${BASE_PATH}/plug/ztree-bootstrap/css/bootstrapStyle/bootstrapStyle.css" type="text/css">  
</head>
<body>
    <div class="tile">
          <div class="overlay">
              <div class="m-loader mr-4">
                <svg class="m-circular" viewBox="25 25 50 50">
                	<circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"/>
                </svg>
              </div>
              <h3 class="l-text">加载中</h3>
            </div>    
    	 <ul id="menuTree" class="ztree"></ul>
    	 <button id="updateMenuBtn" type="button" class="btn btn-primary">保存</button>
    	 <button id="testMenuBtn" type="button" class="btn btn-primary">测试</button>
    	 <input type="text" style="display:none;" id="nodeId">  
    </div> 
   <!--  -->
         <div class="modal" id="seeMethodModal">
						<div class="modal-dialog modal-lg ptn" style="width:600px;"><!--修改这个width可以改变整个模态框的宽度-->
						    <div class="modal-content" >
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						        <h4 class="modal-title"></h4>
						      </div>
						      <div class="modal-body" >
						      </div>	
						      <div class="modal-footer">
                				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
               					 <button type="button" class="btn btn-primary" id="updateBtn">保存</button>
            				</div>     
					    </div>
					  </div>
				</div>
   
   
   
   <template id="menuTemplate">
    <div class="panel mbn">
       <div class="panel-body" >
       <form id="menuForm" style="margin-top:20px;">
		<table class="table table-bordered table-hover">
          <tbody> 
          <tr>
				<td class="va-m" width="30%"><span>菜单名称</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="name" class="form-control validate[required]" placeholder="" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>                                     		   
            <tr>
				<td class="va-m" width="30%"><span>菜单链接</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="url" class="form-control" placeholder="" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>                          
            
            <tr>
				<td class="va-m" width="30%"><span>菜单可见</span></td>
              	<td>
	              	<div class="row">
				          <div class="col-sm-12">	                                     
			                     <div class="animated-radio-button">
						               <label>
						                   <input type="radio" name="visable" value="1"><span class="label-text">普通用户可见</span>
						               </label>
						                <label>
						                   <input type="radio" name="visable" value="2"><span class="label-text">仅管理员可见</span>
						                </label>						     
			                       </div>
				           </div>	                  	
	                 </div>	  
              	</td>              	
            </tr>  
                               		
          </tbody>
        </table>
        </form>
      </div>
    </div>
</template>
   <!--  -->
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.exedit.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/js/pagejs/menuSettings.js"></script>
</body>
</html>
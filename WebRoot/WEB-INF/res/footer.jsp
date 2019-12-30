<%@ page contentType="text/html;charset=UTF-8"%>          
          <div class="modal" id="pamsModal">
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
               					 <button type="button" class="btn btn-primary" id="pamsUpdateBtn">保存</button>
               					 <button type="button" class="btn btn-primary" id="userUpdateBtn">保存</button>
               				<!-- 	<button type="button" class="btn btn-primary" id="pamsClear">清除</button> -->
            				</div>     
					    </div>
					  </div>
		   </div> 
		   
		   
 <template id="pamsUserTemplate">
    <div class="panel mbn">
       <div class="panel-body" >
       <form id="pamsUserForm" style="margin-top:20px;">
		<table class="table table-bordered table-hover">
          <tbody>                                		   
            <tr>
				<td class="va-m" width="30%"><span>新的密码</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="pamsPwd" class="form-control validate[required]" placeholder="" maxlength="20" type="password">      
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>                          
              <tr>
				<td class="va-m" width="30%"><span>确认密码</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="pamsPwdcheck" class="form-control validate[funcCall[pamsCheckPwd]]" placeholder="" maxlength="20" type="password">      
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr> 
             <tr>
				<td class="va-m" width="30%"><span>用户邮箱</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="pamsMail" class="form-control validate[funcCall[pamsCheckMail]]" placeholder="" maxlength="20" type="text">      
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
    <script src="${BASE_PATH}/plug/vali/js/popper.min.js"></script>
    <script src="${BASE_PATH}/plug/vali/js/bootstrap.min.js"></script>
    <script src="${BASE_PATH}/plug/vali/js/plugins/pace.min.js"></script>
    <script src="${BASE_PATH}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/vali/js/plugins/chart.js"></script> 
	<script type="text/javascript" src="${BASE_PATH}/plug/layer/layer.js"></script>
	<script src="${BASE_PATH}/plug/vali/js/main.js"></script>
	<script type="text/javascript" src="${BASE_PATH}/js/pagejs/template/sys.js"></script>
	

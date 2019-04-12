<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>   
    <title>创建目标任务</title> 
    <link rel='stylesheet' href='${BASE_PATH}/plug/score/css/font-awesome-3.2.1.css'>
    <link rel="stylesheet" href="${BASE_PATH}/plug/score/css/prism.css">
    <link rel='stylesheet' href='${BASE_PATH}/plug/score/css/jQuery.score.css'>
    <link rel='stylesheet' href='${BASE_PATH}/plug/score/css/style.css'>
    <script src="${BASE_PATH}/plug/score/js/prism.js"></script>
    <script src="${BASE_PATH}/plug/score/js/jQuery.score.js"></script>
    <script src="${BASE_PATH}/plug/score/js/application.js"></script>
  </head>
  <body >    
    <main >   
      <div class="row">
        <div class="col-md-12">
          <div class="tile">
            <h3 class="tile-title">目标任务</h3>
            <div class="tile-body">
              <form class="form-horizontal" id="taskForm">
              
                <div class="form-group row">
                  <label class="control-label col-md-3">任务名称</label>
                  <div class="col-md-8">
                    <input class="form-control validate[required]" type="text" placeholder="" name="taskName">
                  </div>
                </div>     
              
                  <div class="form-group row">
                  <label class="control-label col-md-3">完成指标</label>                          
                  <div class="col-md-3">
                    <input class="form-control validate[required]" type="text" placeholder="填数值" name="goal">
                  </div>                 
	                  <div class="col-md-3">
	                      <div class="toggle-flip">
			                  <label>
			                    <input type="checkbox" id="change"><span class="flip-indecator" data-toggle-on="立即分配" data-toggle-off="暂不分配" style="width:100px;"></span>
			                  </label>
	                      </div>
	                 </div> 	                            
                </div>                               
           
                <div id="show" style="display:none">
                     <div class="form-group row">
                         <label class="control-label col-md-3">开始时间</label>
                         <div class="col-md-3">
                           <input  name="start"  class="form-control  form_date" placeholder="选择日期值" maxlength="20" type="text" readonly>                                                  
                         </div> 
                      </div>                       
                     <div class="form-group row">
                         <label class="control-label col-md-3">完成时间</label>
                         <div class="col-md-3">
                           <input  name="end"  class="form-control  form_date" placeholder="选择日期值" maxlength="20" type="text" readonly>                                                  
                         </div> 
                      </div>                      
                      </div>
                  <!--  -->               
                      <div class="form-group row">
                 	 <label class="control-label col-md-3">任务描述</label>
                  		<div class="col-md-8">
                    		<textarea class="form-control" rows="4" placeholder="" name="description" id="description"></textarea>
                  		</div>
                </div>     
                 <div class="form-group row">
                  <label class="control-label col-md-3">任务等级</label>
                  <div class="col-md-8">
                     <ul id="star"></ul>
                     <input type="text" id="score" style="display:none;" name="score">
                  </div>
                </div>                                         
              </form>                  
            </div>
            <div class="tile-footer">
              <div class="row">
                <div class="col-md-8 col-md-offset-3">
                  <button class="btn btn-primary" type="button" id="submitBtn"><i class="fa fa-fw fa-lg fa-check-circle"></i>提交</button>&nbsp;&nbsp;&nbsp;
                  <a class="btn btn-secondary" href="${BASE_PATH}/task/goTargetList"><i class="fa fa-fw fa-lg fa-times-circle"></i>取消</a>&nbsp;&nbsp;&nbsp;                  
                </div>
              </div>
            </div>
          </div>      
        </div>     
      </div>
    <script src="${BASE_PATH}/js/pagejs/task/createTarget.js"></script>
  </body>
</html>
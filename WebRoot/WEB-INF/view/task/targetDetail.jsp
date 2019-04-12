<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>   
    <title>任务详情</title>
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
	                  <div class="col-md-3">
	                    <input class="form-control validate[required]" type="text" value="${vo.taskName}" name="taskName"  maxlength="10" readonly>
	                    <input  type="text" value="${vo.taskId}" name="taskId" style="display:none;">
	                  </div>
                
                  
                      <div class="col-md-3">
	                      <div class="toggle-flip">
			                  <label>
			                    <input type="checkbox" id="change"><span class="flip-indecator" data-toggle-on="重新编辑" data-toggle-off="详情浏览" style="width:100px;"></span>
			                  </label>
	                      </div>
	                 </div> 	   
                </div>     
                
                  <div class="form-group row">
	                   <label class="control-label col-md-3">完成指标</label>              
	                  <div class="col-md-3">
	                    <input class="form-control validate[required]" type="text" value="${vo.goal}" name="goal" readonly>
	                  </div>
                 </div>                                            
                       
                        <div class="form-group row">
	                         <label class="control-label col-md-3">开始时间</label>
	                       <div class="col-md-3">
	                             <input class="form-control  form_date" value="${vo.start}" name="start" type="text" readonly>                                                  
	                        </div> 
                       </div> 
                                            
                      <div class="form-group row">
                         <label class="control-label col-md-3">完成时间</label>
                       <div class="col-md-3">
                             <input   class="form-control  form_date" value="${vo.end}" name="end" type="text" readonly>                                                  
                        </div> 
                      </div>                     
                     
                      <div class="form-group row">
                 	 <label class="control-label col-md-3">任务描述</label>
                  		<div class="col-md-8">
                    		<textarea class="form-control" rows="4" value="${vo.description}" name="description" id="description" readonly></textarea>
                  		</div>
                </div>    
                                      
                 <div class="form-group row">
	                  <label class="control-label col-md-3">任务等级</label>
	                  <div class="col-md-8">
	                     <ul id="star"></ul>
	                     <input type="text" id="score" style="display:none;" name="score" value="${vo.score}">
	                  </div>
                </div>   
              </form>                  
            </div>
            <div class="tile-footer">
              <div class="row">
                <div class="col-md-8 col-md-offset-3">           
                  <a class="btn btn-secondary" href="${BASE_PATH}/task/goTargetList"><i class="fa fa-fw fa-lg fa-times-circle"></i>返回</a>&nbsp;&nbsp;&nbsp;  
                  <button class="btn btn-primary" type="button" id="submitBtn" style="display:none;"><i class="fa fa-fw fa-lg fa-check-circle"></i>提交</button>                 
                </div>
              </div>
            </div>
          </div>      
        </div>     
      </div>    
    </main>
    <script src="${BASE_PATH}/js/pagejs/task/targetDetail.js"></script>
  </body>
</html>
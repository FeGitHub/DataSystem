<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>   
    <title>任务详情</title>
      <link rel="stylesheet" href="${BASE_PATH}/plug/ztree-bootstrap/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
  </head>
  <body >    
    <main >   
      <div class="row">
        <div class="col-md-12">
          <div class="tile">
            <h3 class="tile-title">工程任务</h3>
            <div class="tile-body">
              <form class="form-horizontal" id="taskForm">             
                <div class="form-group row">
                  <label class="control-label col-md-3">任务名称</label>
                  <div class="col-md-8">
                    <input class="form-control validate[required]" type="text" placeholder="${vo.taskName}"  readonly>
                  </div>
                </div>     
                
                  <div class="form-group row">
                  <label class="control-label col-md-3">完成指标</label>
               
                 
                  <div class="col-md-3">
                    <input class="form-control validate[required]" type="text" placeholder="${vo.goal}"  readonly>
                  </div>
                </div>                                            
                       
                        <div class="form-group row">
	                         <label class="control-label col-md-3">开始时间</label>
	                       <div class="col-md-3">
	                             <input class="form-control  form_date" placeholder="${vo.start}" maxlength="20" type="text" readonly>                                                  
	                        </div> 
                       </div> 
                                            
                      <div class="form-group row">
                         <label class="control-label col-md-3">完成时间</label>
                       <div class="col-md-3">
                             <input   class="form-control  form_date" placeholder="${vo.end}" maxlength="20" type="text" readonly>                                                  
                        </div> 
                      </div>                     
                     
                      <div class="form-group row">
                 	 <label class="control-label col-md-3">任务描述</label>
                  		<div class="col-md-8">
                    		<textarea class="form-control" rows="4" placeholder="${vo.description}" name="description" id="description" readonly></textarea>
                  		</div>
                </div>                          
                     
              </form>                  
            </div>
            <div class="tile-footer">
              <div class="row">
                <div class="col-md-8 col-md-offset-3">             
                  <a class="btn btn-secondary" href="${BASE_PATH}/task/goTargetList"><i class="fa fa-fw fa-lg fa-times-circle"></i>返回</a>                 
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
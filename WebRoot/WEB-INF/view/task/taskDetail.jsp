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
                    <input class="form-control validate[required]" type="text" placeholder="" name="taskName">
                  </div>
                </div>     
                
                  <div class="form-group row">
                  <label class="control-label col-md-3">完成指标</label>
               
                 
                  <div class="col-md-3">
                    <input class="form-control validate[required]" type="text" placeholder="填数值" name="taskName">
                  </div>
                </div>                               
                    
                  <div class="form-group row">
                  <label class="control-label col-md-3">立即启动</label>
                  <div class="col-md-3">
                      <div class="toggle-flip">
                  <label>
                    <input type="checkbox"><span class="flip-indecator" data-toggle-on="启动" data-toggle-off="关闭"></span>
                  </label>
                </div>
                  </div>
                </div>      
                     
                      <div class="form-group row">
                         <label class="control-label col-md-3">最迟完成时间</label>
                       <div class="col-md-3">
                    <input  name="" id="dateStr" class="form-control  form_date" placeholder="选择日期值" maxlength="20" type="text" readonly>                                                  
                  </div> 
                      </div>
                      
                     
                      <div class="form-group row">
                 	 <label class="control-label col-md-3">任务描述</label>
                  		<div class="col-md-8">
                    		<textarea class="form-control" rows="4" placeholder="" name="description" id="description"></textarea>
                  		</div>
                </div>       
                     
                     
              </form>                  
            </div>
            <div class="tile-footer">
              <div class="row">
                <div class="col-md-8 col-md-offset-3">
                  <button class="btn btn-primary" type="button" id="submitBtn"><i class="fa fa-fw fa-lg fa-check-circle"></i>提交</button>&nbsp;&nbsp;&nbsp;
                  <a class="btn btn-secondary" href="${BASE_PATH}/task/goTaskList"><i class="fa fa-fw fa-lg fa-times-circle"></i>取消</a>&nbsp;&nbsp;&nbsp;                  
                </div>
              </div>
            </div>
          </div>      
        </div>     
      </div>
      
    <!--   <div class="row">   
         <div class="col-md-6">
         <div class="tile" style="height:400px;">
         	 <div id="pieChart" style="height:400px;"></div>
         </div>
         </div>
         <div class="col-md-6">
         	<div class="tile">
	    	 <ul id="treeDemo" class="ztree"></ul>
	    	 <button id="testBtn" type="button" class="btn btn-primary">打开控制台查看数据</button>
         </div> 
         </div>          	 
        </div> -->
    </main>
  
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/ztree-bootstrap/js/jquery.ztree.exedit.js"></script>
    <script src="${BASE_PATH}/js/plugjs/echarts.js"></script>
    <script src="${BASE_PATH}/js/pagejs/demojs/eChartsDemo/walden.js"></script> <!--图标样式-->
    <script src="${BASE_PATH}/js/pagejs/task/taskDetail.js"></script>
  </body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>   
    <title>创建调度任务</title>  
    <style type="text/css">
      .form-check-label{
      	padding-right:30px;
      }
    </style>
  </head>
  <body >    
    <main >   
      <div class="row">
        <div class="col-md-12">
          <div class="tile">
            <h3 class="tile-title">调度任务</h3>
            <div class="tile-body">
              <form class="form-horizontal" id="qrtzForm">
               <!--  <div class="form-group row">
                  <label class="control-label col-md-3">任务名称</label>
                  <div class="col-md-8">
                    <input class="form-control validate[required]" type="text" placeholder="" name="jobName">
                  </div>
                </div> -->
                  <div class="form-group row">
                    <label for="jobClass" class="control-label col-md-3">类名</label>
                    <div class="col-md-8">
                    	 <select name="jobClassStr" class="form-control" id="jobClass">                                        
                    </select>
                    </div>                 
                  </div>
               <!--  <div class="form-group row">
                  <label class="control-label col-md-3">任务组名</label>
                  <div class="col-md-8">
                    <input class="form-control validate[required] col-md-8" type="email" placeholder="" name="jobGroup">
                  </div>
                </div> -->
                 <div class="form-group row">
                  <label class="control-label col-md-3">日期值转Cron表达式</label>
                  <div class="col-md-3">
                    <input  name="" id="dateStr" class="form-control  form_date" placeholder="选择日期值" maxlength="20" type="text" readonly>                                                  
                  </div>                
                  	<button class="btn btn-primary" type="button" id="transferBtn"><i class="fa fa-fw fa-lg fa-check-circle"></i>转换</button>
                   <div class="col-md-3">
                   		<input  name="cron" id="cron" class="form-control validate[required]" placeholder="借助转换工具或者自己编写" maxlength="20" type="text">                      
                   </div>
                </div>        
                                               
                 <div class="form-group row">
                  <label class="control-label col-md-3">频率</label>
                  <div class="col-md-9">
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="period" value="once" checked onclick="showOrHide(this);">一次                                                    
                      </label>
                        <label class="form-check-label">
                        	<input class="form-check-input" type="radio" name="period" value="day" onclick="showOrHide(this);">每天
                      </label>
                       <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="period" value="month" onclick="showOrHide(this);">每月
                      </label>
                       <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="period" value="week" id="weekId" onclick="showOrHide(this);">每星期
                      </label>
                    </div>                 
                  </div>
                </div>
                 <div class="form-group row" style="display:none" id="weekGroup">
                    <label for="exampleSelect1" class="col-md-3">指定星期</label>
                     <div class="col-md-8">
                     	<select name="weekType" class="form-control" id="weekType">                     
		                      <option value="MON">星期一</option>
		                      <option value="TUE">星期二</option>
		                      <option value="WED">星期三</option>
		                      <option value="THU">星期四</option>
		                      <option value="FRI">星期五</option>
		                      <option value="SAT">星期六</option>
		                      <option value="SUN">星期日</option>
                        </select>
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
                  <a class="btn btn-secondary" href="${BASE_PATH}/qrtz/goQrtzTaskList"><i class="fa fa-fw fa-lg fa-times-circle"></i>取消</a>&nbsp;&nbsp;&nbsp;                 
                </div>
              </div>
            </div>
          </div>
        </div>     
      </div>
    </main>
    <script src="${BASE_PATH}/js/pagejs/qrtz/createQuartz.js"></script>
  </body>
</html>
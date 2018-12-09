<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>   
    <title>创建调度任务</title>
  </head>
  <body >    
    <main >   
      <div class="row">
     <!--    <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title">Vertical Form</h3>
            <div class="tile-body">
              <form>
                <div class="form-group">
                  <label class="control-label">Name</label>
                  <input class="form-control" type="text" placeholder="Enter full name">
                </div>
                <div class="form-group">
                  <label class="control-label">Email</label>
                  <input class="form-control" type="email" placeholder="Enter email address">
                </div>
                <div class="form-group">
                  <label class="control-label">Address</label>
                  <textarea class="form-control" rows="4" placeholder="Enter your address"></textarea>
                </div>
                <div class="form-group">
                  <label class="control-label">Gender</label>
                  <div class="form-check">
                    <label class="form-check-label">
                      <input class="form-check-input" type="radio" name="gender">Male
                    </label>
                  </div>
                  <div class="form-check">
                    <label class="form-check-label">
                      <input class="form-check-input" type="radio" name="gender">Female
                    </label>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label">Identity Proof</label>
                  <input class="form-control" type="file">
                </div>
                <div class="form-group">
                  <div class="form-check">
                    <label class="form-check-label">
                      <input class="form-check-input" type="checkbox">I accept the terms and conditions
                    </label>
                  </div>
                </div>
              </form>
            </div>
            <div class="tile-footer">
              <button class="btn btn-primary" type="button"><i class="fa fa-fw fa-lg fa-check-circle"></i>Register</button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="#"><i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</a>
            </div>
          </div>
        </div> -->
       
        <div class="col-md-12">
          <div class="tile">
            <h3 class="tile-title">调度任务</h3>
            <div class="tile-body">
              <form class="form-horizontal">
                <div class="form-group row">
                  <label class="control-label col-md-3">任务名称</label>
                  <div class="col-md-8">
                    <input class="form-control" type="text" placeholder="" name="jobName">
                  </div>
                </div>
                  <div class="form-group row">
                    <label for="exampleSelect1" class="control-label col-md-3">类名</label>
                    <div class="col-md-8">
                    	 <select name="jobClassStr" class="form-control" id="jobClass">                                        
                    </select>
                    </div>                 
                  </div>
                <div class="form-group row">
                  <label class="control-label col-md-3">任务组名</label>
                  <div class="col-md-8">
                    <input class="form-control col-md-8" type="email" placeholder="" name="jobGroup">
                  </div>
                </div>
                <div class="form-group row">
                  <label class="control-label col-md-3">任务描述</label>
                  <div class="col-md-8">
                    <textarea class="form-control" rows="4" placeholder="" name="description"></textarea>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="control-label col-md-3">cron</label>
                  <div class="col-md-9">
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="gender1" checked>普通模式
                      </label>
                    </div>
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="gender1">开发模式
                      </label>
                    </div>
                  </div>
                </div>
                 <div class="form-group row">
                  <label class="control-label col-md-3">表达式</label>
                  <div class="col-md-8">
                    <input  name="" id="dateStr" class="form-control validate[required] form_date" placeholder="" maxlength="20" type="text" readonly>                                                  
                  </div>
                </div>
                 <div class="form-group row">
                  <label class="control-label col-md-3">频率</label>
                  <div class="col-md-9">
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="period" value="once" checked>一次
                      </label>
                    </div>
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="period" value="day">每天
                      </label>
                    </div>
                     <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="period" value="month">每月
                      </label>
                    </div>
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="period" value="week" id="weekId">每星期
                      </label>
                    </div>
                  </div>
                </div>
                 <div class="form-group row">
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
                  <label class="control-label col-md-3">Identity Proof</label>
                  <div class="col-md-8">
                    <input class="form-control" type="file">
                  </div>
                </div>
                <!-- <div class="form-group row">
                  <div class="col-md-8 col-md-offset-3">
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="checkbox">I accept the terms and conditions
                      </label>
                    </div>
                  </div>
                </div> -->
              </form>
            </div>
            <div class="tile-footer">
              <div class="row">
                <div class="col-md-8 col-md-offset-3">
                  <button class="btn btn-primary" type="button"><i class="fa fa-fw fa-lg fa-check-circle"></i>提交</button>&nbsp;&nbsp;&nbsp;
                  <a class="btn btn-secondary" href="#"><i class="fa fa-fw fa-lg fa-times-circle"></i>取消</a>&nbsp;&nbsp;&nbsp;
                  <button class="btn btn-primary" type="button" id="transferBtn"><i class="fa fa-fw fa-lg fa-check-circle"></i>转换</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="clearix"></div>
<!--         <div class="col-md-12">
          <div class="tile">
            <h3 class="tile-title">Subscribe</h3>
            <div class="tile-body">
              <form class="row">
                <div class="form-group col-md-3">
                  <label class="control-label">Name</label>
                  <input class="form-control" type="text" placeholder="Enter your name">
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label">Email</label>
                  <input class="form-control" type="text" placeholder="Enter your email">
                </div>
                <div class="form-group col-md-4 align-self-end">
                  <button class="btn btn-primary" type="button"><i class="fa fa-fw fa-lg fa-check-circle"></i>Subscribe</button>
                </div>
              </form>
            </div>
          </div>
        </div> -->
      </div>
    </main>
    <script src="${BASE_PATH}/js/pagejs/createQuartz.js"></script>
  </body>
</html>
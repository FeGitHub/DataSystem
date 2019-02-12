<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>表单组件</title>
</head>
  <body class="app sidebar-mini rtl">
      <div class="row">
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title">动画式的复选和单选按钮</h3>         
            <div class="animated-radio-button">
              <label>
                <input type="radio"><span class="label-text">单选框</span>
              </label>
            </div>
         
            <div class="animated-checkbox">
              <label>
                <input type="checkbox"><span class="label-text">复选框</span>
              </label>
            </div>
            <h4>不可操作状态</h4>
            <div class="animated-radio-button">
              <label>
                <input type="radio" disabled=""><span class="label-text">单选框</span>
              </label>
            </div>
            <div class="animated-checkbox">
              <label>
                <input type="checkbox" disabled=""><span class="label-text">复选框</span>
              </label>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title">动画式切换按钮</h3>
            <div class="row">
              <div class="col-md-6">
                <p><b>切换按钮</b></p>
                <div class="toggle">
                  <label>
                    <input type="checkbox"><span class="button-indecator"></span>
                  </label>
                </div>
                <div class="toggle lg">
                  <label>
                    <input type="checkbox"><span class="button-indecator"></span>
                  </label>
                </div>
                <h5>不可操作状态</h5>
                <div class="toggle">
                  <label>
                    <input type="checkbox" disabled=""><span class="button-indecator"></span>
                  </label>
                </div>
              </div>
              <div class="col-md-6">
                <p><b>翻转切换按钮</b></p>
                <div class="toggle-flip">
                  <label>
                    <input type="checkbox"><span class="flip-indecator" data-toggle-on="ON" data-toggle-off="OFF"></span>
                  </label>
                </div>
                <h5>不可操作</h5>
                <div class="toggle-flip">
                  <label>
                    <input type="checkbox" disabled=""><span class="flip-indecator" data-toggle-on="ON" data-toggle-off="OFF"></span>
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>
      
        <div class="col-md-6">
          <div class="tile">
            <div class="tile-title-w-btn">
              <h3 class="title">选择框1</h3>
              <p><a class="btn btn-primary icon-btn" href="https://select2.github.io/examples.html" target="_blank"><i class="fa fa-file"></i>文档</a></p>
            </div>
            <div class="tile-body">
              <p>多选框测试</p>
              <h4>案例</h4>
              <select class="form-control" id="demoSelect" multiple="">
                <optgroup label="Select Cities">
                  <option>甲</option>
                  <option>乙</option>
                  <option>丙</option>
                  <option>丁</option>                                  
                </optgroup>
              </select>
            </div>
          </div>
        </div>
  
      </div>
  </body>
</html>
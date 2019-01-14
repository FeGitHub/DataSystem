<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <title>CSS3进度条</title>
  <link rel="stylesheet" href="${BASE_PATH}/css/pagecss/democss/progressBar.css">
</head>
<body>
<div class="tile">
  <section class="container">
    <input type="radio" class="radio" name="progress" value="5" id="five">
    <label for="five" class="label">5%</label>

    <input type="radio" class="radio" name="progress" value="25" id="twentyfive">
    <label for="twentyfive" class="label">25%</label>

    <input type="radio" class="radio" name="progress" value="50" id="fifty"  checked>
    <label for="fifty" class="label">50%</label>

    <input type="radio" class="radio" name="progress" value="75" id="seventyfive">
    <label for="seventyfive" class="label">75%</label>

    <input type="radio" class="radio" name="progress" value="100" id="onehundred">
    <label for="onehundred" class="label">100%</label>

    <div class="progress">
      <div class="progress-bar"></div>
    </div>
  </section>
  <button id="progressBtn" type="button" class="btn btn-primary">查看数据</button>
  </div>
  <div style="text-align:center;clear:both;">
</div>
 <script src="${BASE_PATH}/js/pagejs/demojs/progressBar.js"></script>     
</body>
</html>

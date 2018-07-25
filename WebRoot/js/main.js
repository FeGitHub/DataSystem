//主体页面的js
$(document).ready(function(){
	//菜单的隐藏
  $("#hideMenu").click(function(){
    $("#main-nav").css('display','none'); //隐藏
    $("#menu").removeClass("col-md-2");//去掉占位
    $("#mainBody").removeClass("col-md-10").addClass("col-md-12");//增加占位
  });
	//jquery的异步加载
  $(".loadData").click(function(){
	  var url=this.href;
		$("#mainBody").load(url);
		return false;
  });
});
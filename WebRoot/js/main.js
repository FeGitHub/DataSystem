//主体页面的js
$(document).ready(function(){
	  var url="";//加载主体跳转页面的url
	  
	  //对最底层的菜单给与主体页面加载的能力(有子菜单的好好打开子菜单就行了，其他事别参和)
	  $("ul li").each(function(){		 
			  if(!($(this).has("ul").length > 0)){//没有次级菜单
				  $(this).find("a").click(function(){	
					  var url=this.href;
					  $("#mainBody").load(url);//加载主体页面
						return false;//阻止链接的直接跳转
				  });				  
			  }
		  });
	  
	//菜单的隐藏
  $("#hideMenu").click(function(){
    $("#main-nav").css('display','none'); //隐藏
    $("#menu").removeClass("col-md-2");//去掉菜单的占位
    $("#mainBody").removeClass("col-md-10").addClass("col-md-12");//增加占位，主体页面占全部
  });
});
(function () {	
	//"use strict";
	var treeviewMenu = $('.app-menu');

	// 主体部分的展开
	/*$('[data-toggle="sidebar"]').click(function(event) {
		event.preventDefault();
		$('.app').toggleClass('sidenav-toggled');
	});*/
	$('body').on('click','[data-toggle="sidebar"]',function(event){
		event.preventDefault();
		$('.app').toggleClass('sidenav-toggled');
	}) ;
	

	// Activate sidebar treeview toggle
	/*$("[data-toggle='treeview']").click(function(event) {
		event.preventDefault();
		if(!$(this).parent().hasClass('is-expanded')) {//现在处于展开状态
			treeviewMenu.find("[data-toggle='treeview']").parent().removeClass('is-expanded');//关闭
		}
		$(this).parent().toggleClass('is-expanded');//展开
	});*/
	$('body').on('click','[data-toggle="treeview"]',function(event){
		event.preventDefault();
		if(!$(this).parent().hasClass('is-expanded')) {//现在处于展开状态
			treeviewMenu.find("[data-toggle='treeview']").parent().removeClass('is-expanded');//关闭
		}
		$(this).parent().toggleClass('is-expanded');//展开
	}) ;
	
	
	// Set initial active toggle
	$("[data-toggle='treeview'].is-expanded").parent().toggleClass('is-expanded');

	//Activate bootstrip tooltips
	$("[data-toggle='tooltip']").tooltip();

})();

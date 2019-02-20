(function () {	
	var treeviewMenu = $('.app-menu');

	$('body').on('click','[data-toggle="sidebar"]',function(event){
		event.preventDefault();
		$('.app').toggleClass('sidenav-toggled');
	}) ;
	
	$('body').on('click','[data-toggle="treeview"]',function(event){
		event.preventDefault();
		if(!$(this).parent().hasClass('is-expanded')) {//现在处于展开状态
			treeviewMenu.find("[data-toggle='treeview']").parent().removeClass('is-expanded');//关闭
		}
		$(this).parent().toggleClass('is-expanded');//展开
	}) ;		
	
	$("[data-toggle='treeview'].is-expanded").parent().toggleClass('is-expanded');

	$("[data-toggle='tooltip']").tooltip();

})();

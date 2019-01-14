$("#progressBtn").click(function(){
	layer.alert($("input[name=progress]:checked").val(), {icon: 6});
});
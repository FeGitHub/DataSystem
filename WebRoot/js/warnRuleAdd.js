/***
 * 预警规则信息管理页面的js
 */

$(function(){
	 treeMenu();
});


/***
 * 树形物料菜单的处理
 * ztree
 */
function treeMenu(){
    var setting={
        check: {
            enable: true,
            chkStyle: "radio",//显示 checkbox 选择框，默认checkbox可选择值radio
            radioType: "level"
        }
    };
    var url=basepath+"/warn/getTree";
    $.post(url,function(zTreeNodes){
    $.fn.zTree.init($("#tree"), setting, zTreeNodes); 
	  },"json");
}

/***
 * 测试
 */
$("#testBtn").click(function(){	
	var url=basepath+"/warn/test";
	$.post(url,function(){
		alert("dd");
	});
});
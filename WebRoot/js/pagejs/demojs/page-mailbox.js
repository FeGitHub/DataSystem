$(function () {
    //全选,设置chheckbox name='all' tbody id=tb
    $("input[name=all]").click(function () {
        if (this.checked) {
            $("#tb :checkbox").prop("checked", true);
        } else {
            $("#tb :checkbox").prop("checked", false);
        }
    });
});
//单选 设置name=id
function userCheck(ths) {
    if (ths.checked == false) {
        $("input[name=all]:checkbox").prop('checked', false);
    }
    else {
        var count = $("input[name='itemBox']:checkbox:checked").length;
        if (count == $("input[name='itemBox']:checkbox").length) {
            $("input[name=all]:checkbox").prop("checked", true);
        }
    }
}




$("#delBtn").click(function(){
		layer.confirm('您确定要删除吗？', {			
		    btn: ['确定', '取消'], //按钮
		    skin: 'btnClass',
		    icon: 2,
		    title: "提示",
	}, function () {
		 layer.closeAll('dialog');  
		 
	});
});
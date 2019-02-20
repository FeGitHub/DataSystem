		$(function () {
			$("#ganttChart").ganttView({
                // showWeekends: true,
				data: ganttData,
				behavior: {
					onClick: function (data) { 
						var msg = "click事件:" + JSON.stringify(data);
						$("#eventMessage").text(msg);
					},
					onResize: function (data) {
					    console.log(data);
						var msg = "resize事件:" + JSON.stringify(data);
						$("#eventMessage").text(msg);
					},
					onDrag: function (data) {
					    // console.log(data);
						var msg = "drag事件:" + JSON.stringify(data);
						$("#eventMessage").text(msg);
					}
				}
			});
			$("#submit").click(function () {
                $("#ganttChart").ganttView("getDatas", function (datas) {
                    console.log(datas);
                    $("#submitData").text(JSON.stringify(datas));
                });
            });
		});
		
		 $( ".task" ).draggable({
				revert: true,
	            opacity: 0.7
			});
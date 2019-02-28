		$(function () {
			//===
			  $.ajax({
				 url:basepath+"/task/getProjectGantt",
				 type:"post",
				 data:{"projectId":"1"},
				 dataType:"json",
				 success:function(data){
					 console.log(ganttData);
					 console.log(data);
					 showGantt($("#ganttChart"),data);
				 }
			  })
			//===
			//showGantt($("#ganttChart"),ganttData);
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
		 
		 
		function showGantt($obj,data){
			  $obj.ganttView({
                // showWeekends: true,
				data: data,
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
		}
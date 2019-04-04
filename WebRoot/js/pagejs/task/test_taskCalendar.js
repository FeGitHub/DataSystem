/*****
 * 具体配置可参考：https://blog.csdn.net/qw_xingzhe/article/details/44920943
 */
$(function(){ 
	    displayTask();  
		$("#calendar").fullCalendar({
			theme: true,		
			dragOpacity: 0.5, //Event被拖动时的不透明度
            droppable: true,
            editable:true,
            drop: function(date, allDay) {//外部拖拽事件	
				if ($('#drop-remove').is(':checked')) {
					$(this).remove();
				}				
				var selDate = $.fullCalendar.formatDate(date,"YYYY-MM-DD");
				var taskName=$(this)[0].innerText;
				externalDrop(taskName,selDate,$(this).data("id"));
				
			},
            eventStartEditable:true,
            dragRevertDuration:500,          
			events: {
				url:basepath+"/task/getTaskCalendar",
				error: function() {
					toastrError("资源请求失败",3000);
				}
			},
			customButtons:{
				button1:{
					text:"新建",
					click:function(){
						initButton1();					
						dialog({
							title:"新建日程",
							content:$("#dialog-form"),
							okValue:"确定",
							ok:function(){
					           button1Create();
							},
							cancelValue:"关闭",
							cancel:function(){
							}
						}).showModal();
					}
				},

	/*			button2:{
					text:"查询",
					click:function(){
						$(".datepicker").datepicker({
							language:"zh-CN",
							format:"yyyy-mm-dd",
							todayHighlight:true,
							autoclose:true,
							weekStart:0
						});
						dialog({
							title:"查询",
							content:$("#search"),
							okValue:"查询",
							ok:function(){
								$("#ui-datepicker-div").remove();
							},
							button:[{
								value:"打印"
							}],
							cancelValue:"返回",
							cancel:function(){
								$("#ui-datepicker-div").remove();
							}
						}).showModal();

					}
				},*/
				button3:{
					text:"设置",
					click:function(){
						$("#slider").slider({
							range:true,
							min:0,
							max:24,
							values:[8,18],
							slide: function( event, ui ) {
				        		$( "#amount" ).val(ui.values[ 0 ] + ":00 - " + ui.values[ 1 ]+":00");
				        		
				      		}
						});
						$( "#amount" ).val($( "#slider" ).slider( "values", 0 ) +
                        ":00 - " + $( "#slider" ).slider( "values", 1 )+":00");
						dialog({
							title:"设置时间段",
							content:$("#set"),
							okValue:"确定",
							ok:function(){
								var minTime = $( "#slider" ).slider( "values", 0 )+":00:00";
				      			var maxTime = $( "#slider" ).slider( "values", 1 )+":00:00";
				      			$("#calendar").fullCalendar("option","minTime",minTime);
				      			$("#calendar").fullCalendar("option","maxTime",maxTime);
							},
							cancelValue:"关闭",
							cancel:function(){}
						}).showModal();
					}
				}
			},
			header: {
				left: 'prev,next today button3',
				center: 'title',
				right: 'button1 button2 month,agendaWeek,agendaDay,listMonth'
			},
			firstDay: 1,
			monthNames: ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
			monthNamesShort: ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
			dayNames:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],
			dayNamesShort:["日","一","二","三","四","五","六"],
			buttonText:{
				today: "今天",
				month: "月",
				week: "周",
				day: "天",
				listMonth:"列表"
			},
			allDayDefault:false,
			slotLabelFormat:"H",
			businessHours: {
				dow:[1,2,3,4,5],
				start:"8:00",
				end:"17:00"
			},
			allDaySlot: true,
			allDayText: "全天",
			timeFormat: "HH:mm",//设置的是添加的具体的日程上显示的时间
			views:{
				month:{
					titleFormat:"YYYY年M月"
				},
				week:{
					titleFormat:"YYYY年M月D日",
					columnFormat:"M.D dddd"
				},
				day:{
					titleFormat:"YYYY年M月D日 dddd",
					columnFormat:"M/D dddd"
				}
			},
			
			/****
			 * 内部事件拖拽完成后触发
			 */
			eventDrop: function (event, dayDelta, revertFunc) {			
				     eventDrop(event);					
            },
            /****
             *  外部事件拖进日历
             */
            eventReceive:function(info) {
            	var id=$("#itemId").val();
            	if(id!=null&&id!=""){
            		console.log("拖动");	
            		info.id=id;
            		$("#itemId").val("");
            	}					
			 },
			dayClick: function(date,allDay,jsEvent,view){
				var selDate = $.fullCalendar.formatDate(date,"YYYY-MM-DD");
				var d = dialog({
					title:"新建事件",
					content:"<textarea rows=5 class='taxt' placeholder='内容' id='eventall'></textarea><p>"+selDate+"</p>",
					width:460,
					button:[{
						value:"完整编辑",
						callback:function(){
					     InitDayClick();
							dialog({
								title:"新建日程",
								content:$("#dialog-form"),
								okValue:"确定",
								ok:function(){
					               createTask();
					               clear();
								},
								cancelValue:"关闭",
								cancel:function(){
									clear();
								}
							}).showModal();
						},	
					}],			
					
					okValue:"确定",
					ok:function(){
						var titleall = $("#eventall").val();
						if(titleall){
							simpleCreateTask(titleall,selDate);
							clear();
						}
					},
					cancelValue:"取消",
					cancel:function(){
						clear();
					}
				});
				d.showModal();
				
			},
			
			
			/****
			 * 事务点击时间
			 */
			eventClick:function(event,jsEvent,view){				
				var editstarttime = $.fullCalendar.formatDate(event.start,"YYYY-MM-DD HH:mm:ss");
				$("#edittitle").html(event.title);
				var eventtitle = event.title;
				if(event.end){
					var editendtime = $.fullCalendar.formatDate(event.end,"YYYY-MM-DD HH:mm:ss");
					$("#edittime").html(editstarttime+"  至  "+editendtime);
				}else{
					$("#edittime").html(editstarttime);
				};		
				dialog({
					title:"编辑日程",
					content:$("#edit"),
					okValue:"编辑",
					ok:function(){
						initEventClick(event);
						dialog({
							title:"新建日程",
							content:$("#dialog-form"),
							okValue:"确定",
							ok:function(){
						        update(event);
						        clear();
							},
							cancelValue:"关闭",
							cancel:function(){
								clear();
								//$("#ui-datepicker-div").remove();
							}
						}).showModal();					
					},

					//事件删除
					button:[{
						value:"删除",
						callback:function(){
							delTask(event);
						}
					}],

					//取消操作
					cancelValue:"取消",
					cancel:function(){
						clear();
					}
				}).showModal();
			}
		
		});
	});



/***
 * 简易创建
 * @param titleall
 * @param selDate
 */
function simpleCreateTask(titleall,selDate){
  var data={taskName:titleall, start:selDate};
  $.ajax({
			url:basepath+"/task/addTaskCalendar",
		   	data:data,
		   	type:'POST',
		   	dataType:'json',
		  	success:function(data){
		  		if(data.code==200){
		  			$("#calendar").fullCalendar("renderEvent",data.task,true);
					  toastrSuccess(data.msg,2000);	 
		  		}else{
		  			toastrError(data.msg,2000);
		  		}	  
		  	},
		  	error:function(){
		  		toastrError("资源请求失败",3000);
		  		}		   						
			});
}
 

/***
 * 创建新完整的事务
 */
function createTask(){
	       var data=getTaskAllData();
			$.ajax({
				url:basepath+"/task/addTaskCalendar",
				data:data,
				type:'POST',
				dataType:'json',
				success:function(data){
					if(data.code==200){
						 toastrSuccess(data.msg,2000);
						 $("#calendar").fullCalendar("renderEvent",data.task,true);
					}else{
						toastrError(data.msg,2000);
					}				  					
				},
				error:function(){
					toastrError("资源请求失败",3000);
					}					   						
				});
}


/***
 * 提交编辑数据
 * @param event
 */
function update(event){
	var task=getTaskAllData();
	var eventtitle=event.title;
	task.taskId=event.id;
	if(task.taskId==null){
		toastrError("id为null",3000);
		return;
	}
	$.ajax({//更新数据库资源
		url:basepath+"/task/updateTaskCalendar",
		data:task,
		type:'POST', 
		dataType:'json',
		success:function(data){	
			if(data.code==200){
				toastrSuccess(data.msg,2000);
				event=changeViewTask(event,task);
			    $("#calendar").fullCalendar("updateEvent",event);//更新视图资源
			} else{
				toastrError(data.msg,2000);
			}
			
		},
		error:function(){
			toastrError("资源请求失败",3000);
			}					   						
		});	
}


function initButton1(){
  clear();
  $(".datepicker").datepicker({
							language:"zh-CN",
							format:"yyyy-mm-dd",
							todayHighlight:true,
							autoclose:true,
							weekStart:0
						});
						$(".timepicki").wickedpicker({
							title:'',
							showSeconds:true,
							twentyFour:true
						});
						$("#isallday").click(function(){
							if($("#isallday").prop("checked") == true){
								$("#isallday").val("1");
								$("#starttime,#endtime").hide();
							}else{
								$("#isallday").val("0");
								$("#starttime,#endtime").show();
							};	
						});
						$("#end").click(function(){
							if($("#end").prop("checked") == true){
								$("#enddate").show();
							}else{
								$("#enddate").hide();
							};
						});
						$("#repeat").click(function(){
							if($("#repeat").prop("checked") == true){
								$("#repeattype,#repeattime").show();
							}else{
								$("#repeattype,#repeattime").hide();
							};
						});
						$("#repeatselect").change(function(){
							switch($("#repeatselect").val()){
								case "1":
									$("#repeatclock").show();
									$("#repeatmonth,#repeatweek,#repeatday").hide();
									break;
								case "2":
									$("#repeatmonth,#repeatday").hide();
									$("#repeatweek,#repeatclock").show();
									break;
								case "3":
									$("#repeatmonth,#repeatweek").hide();
									$("#repeatday,#repeatclock").show();
									break;
								case "4":
									$("#repeatweek").hide();
									$("#repeatmonth,#repeatday,#repeatclock").show();
									break;
								case "5":
									$("#repeatclock").show();
									$("#repeatmonth,#repeatweek,#repeatday").hide();
									break;
							}
						});
}

		function button1Create(){
			createTask();			
		}

     function InitDayClick(){
	            clear();
				$(".datepicker").datepicker({
								language:"zh-CN",
								format:"yyyy-mm-dd",
								todayHighlight:true,
								autoclose:true,
								weekStart:0
							});
							$(".timepicki").wickedpicker({
								title:'',
								showSeconds:true,
								twentyFour:true
							});
							$("#isallday").click(function(){
								if($("#isallday").prop("checked") == true){
									$("#isallday").val("1");
									$("#starttime,#endtime").hide();
								}else{
									$("#isallday").val("0");
									$("#starttime,#endtime").show();
								};	
							});
							$("#end").click(function(){
								if($("#end").prop("checked") == true){
									$("#enddate").show();
								}else{
									$("#enddate").hide();
								};
							});
							$("#repeat").click(function(){
								if($("#repeat").prop("checked") == true){
									$("#repeattype,#repeattime").show();
								}else{
									$("#repeattype,#repeattime").hide();
								};
							});
							$("#repeatselect").change(function(){
								switch($("#repeatselect").val()){
									case "1":
										$("#repeatclock").show();
										$("#repeatmonth,#repeatweek,#repeatday").hide();
										break;
									case "2":
										$("#repeatmonth,#repeatday").hide();
										$("#repeatweek,#repeatclock").show();
										break;
									case "3":
										$("#repeatmonth,#repeatweek").hide();
										$("#repeatday,#repeatclock").show();
										break;
									case "4":
										$("#repeatweek").hide();
										$("#repeatmonth,#repeatday,#repeatclock").show();
										break;
									case "5":
										$("#repeatclock").show();
										$("#repeatmonth,#repeatweek,#repeatday").hide();
										break;
								}
							});
}



function initEventClick(event){
   var time=getEventTime(event);
   clear();
   showTaskData(event);
   $(".datepicker").datepicker({
		language:"zh-CN",
		format:"yyyy-mm-dd",
		todayHighlight:true,
		autoclose:true,
		weekStart:0
	});                 
   $("#starttime").wickedpicker({
		now:time.starttime,
		title:'',
		showSeconds:true,
		twentyFour:true
	});	                  
				
	$("#endtime").wickedpicker({
		now:time.endtime,
		title:'',
		showSeconds:true,
		twentyFour:true
	});				
	
	$("#isallday").click(function(){
		if($("#isallday").prop("checked") == true){
			$("#isallday").val("1");
			$("#starttime,#endtime").hide();
		}else{
			$("#isallday").val("0");
			$("#starttime,#endtime").show();
		};	
	});
						$("#end").click(function(){
							if($("#end").prop("checked") == true){
								$("#enddate").show();
							}else{
								$("#enddate").hide();
							};
						});
						$("#repeat").click(function(){
							if($("#repeat").prop("checked") == true){
								$("#repeattype,#repeattime").show();
							}else{
								$("#repeattype,#repeattime").hide();
							};
						});
						$("#repeatselect").change(function(){
							switch($("#repeatselect").val()){
								case "1":
									$("#repeatclock").show();
									$("#repeatmonth,#repeatweek,#repeatday").hide();
									break;
								case "2":
									$("#repeatmonth,#repeatday").hide();
									$("#repeatweek,#repeatclock").show();
									break;
								case "3":
									$("#repeatmonth,#repeatweek").hide();
									$("#repeatday,#repeatclock").show();
									break;
								case "4":
									$("#repeatweek").hide();
									$("#repeatmonth,#repeatday,#repeatclock").show();
									break;
								case "5":
									$("#repeatclock").show();
									$("#repeatmonth,#repeatweek,#repeatday").hide();
									break;
							}
						});
}


/****
 * 获取弹出框输入数据
 * @returns 
 */
function getTaskAllData(){
	var title = $("#title").val();//标题
	var titledetail = $("#titledetail").val();//描述
	var startdate = $("#startdate").val();
	var starttime = $("#starttime").val().split(" ").join("");
	var enddate = $("#stopdate").val();
	var endtime = $("#endtime").val().split(" ").join("");
	var start=startdate+" "+starttime;//开始时间
	//var allDay = $("#isallday").val();
	var end;
	var data={taskName:title,start:start,description:titledetail};
	if($('#end').is(':checked')&&enddate!="") {//截止时间
		end=enddate+" "+endtime;
		data.end=end;
	}	
	return data;
}

/***
 * 用于更新任务的视图信息
 * @param event
 * @param data
 */
function changeViewTask(event,task){
	event.title=task.taskName;
	event.description=task.description;
	event.start=task.start;
	if(task.end!=null){
		event.end=task.end;
	}
	return event;
}


/***
 * 展示编辑数据
 */
function showTaskData(event){
	 var time=getEventTime(event);
	 $("#title").val(event.title);//标题
	 $("#titledetail").val(event.description);//描述
	 $("#startdate").val(time.startdate);
	 if(event.end!=null&&time.stopdate!=null&&time.stopdate!=""){
		 $("#end").prop("checked",true);
		 $("#enddate").show();
		 $("#stopdate").val(time.stopdate);
	 }
	
}

/****
 * 分割任务中的时间段
 * @param event
 * return :
 *    start 详细开始时间
 *    startdate 开始日期
 *    starttime 开始时间点
 *    
 *    end  详细截止时间
 *    stopdate  截止日期
 *    endtime   截止时间点
 */
function getEventTime(event){
	var data={};
	var startdate = $.fullCalendar.formatDate(event.start,"YYYY-MM-DD");
	var starttime = $.fullCalendar.formatDate(event.start,"HH:mm:ss");
	var start = $.fullCalendar.formatDate(event.start,"YYYY-MM-DD HH:mm:ss");
	var stopdate="";
	var endtime ="00:00:00";
	var end ="";
	data.starttime=starttime;
	data.startdate=startdate;	
	data.start=start;
	if(event.end!=null){
		 stopdate = $.fullCalendar.formatDate(event.end,"YYYY-MM-DD");
		 endtime = $.fullCalendar.formatDate(event.end,"HH:mm:ss");
		 end = $.fullCalendar.formatDate(event.end,"YYYY-MM-DD HH:mm:ss");			
	}	
	 data.stopdate=stopdate;	
	 data.endtime=endtime;
	 data.end=end;
	return data;	
}

/***
 * 删除任务
 * @param event
 */
function delTask(event){
	var taskId=event.id;
	var eventtitle=event.title;
	if(taskId==null){
		toastrError("id为null",3000);
		return;
	}
	$.ajax({
		url:basepath+"/task/delTarget",
		data:{taskId:taskId},
		type:'POST',
		dataType:'json',
		success:function(data){
			if(data.code==200){
				toastrSuccess(data.msg,3000);
				$("#calendar").fullCalendar("removeEvents",function(event){				
					if(event.title==eventtitle){
						return true;
					}else{
						return false;
					}
		       });
			}else{
				toastrError(data.msg,3000);
			}			
		},
		error:function(){
			toastrError("请求失败",3000);
			}					   						
		});	
}

/***
 * 内部拖拽事件的具体实现
 * @param event
 */
function eventDrop(event){
	var time=getEventTime(event);
	var data={taskId:event.id,start:time.start,taskName:event.title};
	if(time.end!=""){
		data.end=time.end;
	}
	$.ajax({
		url:basepath+"/task/updateTaskCalendar",
		data:data,
		type:'POST',
		dataType:'json',
		success:function(data){
			
		},
		error:function(){
			toastrError("请求失败",3000);
			}					   						
		});	
}

/***
 * 清除弹出框历史数据
 */
function clear(){
	 //去除选中
	 $("#end").prop("checked",false);
	 $("#isallday").prop("checked",false)
	 $("#repeat").prop("checked",false)
	 //隐藏相关
	 $("#repeattype,#repeattime").hide();
	 $("#enddate").hide();
	 //清除
	 $("#title").val("");//标题
	 $("#titledetail").val("");//描述
	 $("#startdate").val("");
	 $("#stopdate").val("");
	 $("#participant").val("");	 
}


/****
 * 外部拖拽事件
 * @param titleall
 * @param selDate
 */
function externalDrop(titleall,selDate,id){
	 if(id==null){
		 toastrError("任务id为null",3000);
		 return;
	 }
	 $("#itemId").val(id);
	  var data={"taskName":titleall, "start":selDate,"taskId":id};
	  $.ajax({
				url:basepath+"/task/updateTaskCalendar",
			   	data:data,
			   	type:'POST',
			   	dataType:'json',
			  	success:function(data){
			  		if(data.code==200){	
			  			//$('#calendar').fullCalendar( 'updateEvent', data.task);
			  			//$("#calendar").fullCalendar( 'refetchEvents');
			  			
						  toastrSuccess(data.msg,2000);	 
			  		}else{
			  			toastrError(data.msg,2000);
			  		}	  
			  	},
			  	error:function(){
			  		toastrError("请求失败",3000);
			  		}		   						
				});

}


/****
 * 请求未分配任务资源
 */
  function displayTask(){
	  $.ajax({
			url:basepath+"/task/getUndisTasks",		  
		   	type:'POST',
		   	dataType:'json',
		  	success:function(data){
		  		taskItem(data); 
		  	},
		  	error:function(){
		  		toastrError("请求失败",3000);
		  		}		   						
			});

}
/***
 * 未分配任务资源展示
 */
function taskItem(data){
	var  num=data.length;
	var _html="";
	for(var i=0;i<num;i++){
		_html+="<div class='fc-event' data-id='"+data[i].taskId+"'>"+data[i].taskName+"</div>";
	}
	$("#taskItems").html(_html);
	//激活
	$('#external-events .fc-event').each(function() {	
		//添加数据
		$(this).data('event', {
			title: $.trim($(this).text()), 
			stick: true,
			id:$(this).data("taskId")
		});		
		// 使用 jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      
			revertDuration: 0  
		});

	});
}



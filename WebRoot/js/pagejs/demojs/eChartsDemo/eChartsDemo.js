  // ECharts 3--基于准备好的dom，初始化echarts实例
	$(function(){
		getData();
		pieChart();
		barNegative();
	});
         
  function getData(){		
	  var myChart = echarts.init(document.getElementById('main'),'walden');
	  myChart.setOption({
	        title: {
	            text: '蔬菜交易数量'
	        },
	        tooltip: {},
	        legend: {
	            data:['销量']
	        },
	        xAxis: {
	            data: []
	        },
	        yAxis: {},
	        series: [{
	            name: '销量',
	            type: 'bar',
	            data: []
	        }]
	    });
	  $.ajax({
		  // url: basepath+"/json/ECharts.json",//json文件位置
		   url: basepath+"/demo/getEChartsData",
		   type: "post",//请求方式为get
		   dataType: "json", //返回数据格式为json
		   success: function(info) {//请求成功完成后要执行的方法 				  
			    var categories = new Array();
	        	var data= new Array();
	        	for(var i=0;i<info.length;i++){
	        		categories.push(info[i].categories);
	        		data.push(info[i].data);
	        	}		        					  
			   myChart.setOption({
	                xAxis: {
	                    data: categories
	                },
	                series: [{
	                    name: '销量',
	                    data: data
	                }]
	            });			
			   }
		  
		})
  }
     
        
      //============
        var dom = document.getElementById("container");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;

        data = [["2000-06-05",116],["2000-06-06",129],["2000-06-07",135],["2000-06-08",86],["2000-06-09",73],["2000-06-10",85],["2000-06-11",73],["2000-06-12",68],["2000-06-13",92],["2000-06-14",130],["2000-06-15",245],["2000-06-16",139],["2000-06-17",115],["2000-06-18",111],["2000-06-19",309],["2000-06-20",206],["2000-06-21",137],["2000-06-22",128],["2000-06-23",85],["2000-06-24",94],["2000-06-25",71],["2000-06-26",106],["2000-06-27",84],["2000-06-28",93],["2000-06-29",85],["2000-06-30",73],["2000-07-01",83],["2000-07-02",125],["2000-07-03",107],["2000-07-04",82],["2000-07-05",44],["2000-07-06",72],["2000-07-07",106],["2000-07-08",107],["2000-07-09",66],["2000-07-10",91],["2000-07-11",92],["2000-07-12",113],["2000-07-13",107],["2000-07-14",131],["2000-07-15",111],["2000-07-16",64],["2000-07-17",69],["2000-07-18",88],["2000-07-19",77],["2000-07-20",83],["2000-07-21",111],["2000-07-22",57],["2000-07-23",55],["2000-07-24",60]];
        
        var dateList = data.map(function (item) {
            return item[0];
        });
        var valueList = data.map(function (item) {
            return item[1];
        });

        option = {
            visualMap: [{
                show: false,
                type: 'continuous',
                seriesIndex: 0,
                min: 0,
                max: 400
            }],


            title: [{
                left: 'center',
                text: 'Y轴的值'
            }],
            tooltip: {
                trigger: 'axis'
            },
            xAxis: [{
                data: dateList
            }],
            yAxis: [{
                splitLine: {show: false}
            }],
         /*  grid: [{
                bottom: '60%'
            }, {
                top: '60%'
            }],*/
            series: [{
                type: 'line',
                showSymbol: false,
                data: valueList
            }]
        };;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
        
        
     function pieChart(){
    	 var dom = document.getElementById("pieChart");
    	 var pieChart = echarts.init(dom);
    	 var app = {};
    	 option = null;
    	 option = {
    	     title : {
    	         text: '某站点用户访问来源',
    	         subtext: '纯属虚构',
    	         x:'center'
    	     },
    	     tooltip : {
    	         trigger: 'item',
    	         formatter: "{a} <br/>{b} : {c} ({d}%)"
    	     },
    	     legend: {
    	         orient: 'vertical',
    	         left: 'left',
    	         data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
    	     },
    	     series : [
    	         {
    	             name: '访问来源',
    	             type: 'pie',
    	             radius : '55%',
    	             center: ['50%', '60%'],
    	             data:[
    	                 {value:335, name:'直接访问'},
    	                 {value:310, name:'邮件营销'},
    	                 {value:234, name:'联盟广告'},
    	                 {value:135, name:'视频广告'},
    	                 {value:1548, name:'搜索引擎'}
    	             ],
    	             itemStyle: {
    	                 emphasis: {
    	                     shadowBlur: 10,
    	                     shadowOffsetX: 0,
    	                     shadowColor: 'rgba(0, 0, 0, 0.5)'
    	                 }
    	             }
    	         }
    	     ]
    	 };
    	 ;
    	 if (option && typeof option === "object") {
    		 pieChart.setOption(option, true);
    	 }
     }
        
      function barNegative(){
    	  var dom = document.getElementById("barnNegative");
    	  var myChart = echarts.init(dom);
    	  var app = {};
    	  option = null;
    	  app.title = '正负条形图';

    	  option = {
    	      tooltip : {
    	          trigger: 'axis',
    	          axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    	              type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    	          }
    	      },
    	      legend: {
    	          data:['利润', '支出', '收入']
    	      },
    	      grid: {
    	          left: '3%',
    	          right: '4%',
    	          bottom: '3%',
    	          containLabel: true
    	      },
    	      xAxis : [
    	          {
    	              type : 'value'
    	          }
    	      ],
    	      yAxis : [
    	          {
    	              type : 'category',
    	              axisTick : {show: false},
    	              data : ['周一','周二','周三','周四','周五','周六','周日']
    	          }
    	      ],
    	      series : [
    	          {
    	              name:'利润',
    	              type:'bar',
    	              label: {
    	                  normal: {
    	                      show: true,
    	                      position: 'inside'
    	                  }
    	              },
    	              data:[200, 170, 240, 244, 200, 220, 210]
    	          },
    	          {
    	              name:'收入',
    	              type:'bar',
    	              stack: '总量',
    	              label: {
    	                  normal: {
    	                      show: true
    	                  }
    	              },
    	              data:[320, 302, 341, 374, 390, 450, 420]
    	          },
    	          {
    	              name:'支出',
    	              type:'bar',
    	              stack: '总量',
    	              label: {
    	                  normal: {
    	                      show: true,
    	                      position: 'left'
    	                  }
    	              },
    	              data:[-120, -132, -101, -134, -190, -230, -210]
    	          }
    	      ]
    	  };
    	  ;
    	  if (option && typeof option === "object") {
    	      myChart.setOption(option, true);
    	  }
      }
      
     $("#testBtn").click(function(){
    	
     });
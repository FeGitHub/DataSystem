var vue = new Vue({
	el:'#first',
	data:{
		name:'小明',
		work:'233'
	},
	methods:{
		sayhello: function(){				
			return "hello";
		},
		sayAxiosData: function(){
			var url="/json/data.json";
			axios.get(url)
			.then(function(result){				
			vue.name = result.data.name;
			console.log("sayAxiosData:"+result);
			});
		},
		sayAjaxData: function(){
			var url="/json/data.json";
			_this=this;
			$.getJSON(url,function(data){			
		     _this.work=data.job;
		     console.log("data:"+data);
		});
		}
	}

})




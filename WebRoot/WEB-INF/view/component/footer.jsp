    <script src="${BASE_PATH}/plug/vali/js/popper.min.js"></script>
    <script src="${BASE_PATH}/plug/vali/js/bootstrap.min.js"></script>
    <script src="${BASE_PATH}/plug/vali/js/plugins/pace.min.js"></script>
    <script src="${BASE_PATH}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/plug/vali/js/plugins/chart.js"></script> 
	<script type="text/javascript" src="${BASE_PATH}/plug/layer/layer.js"></script><!--弹出框-->	
	<script src="${BASE_PATH}/plug/vali/js/main.js"></script>
	<script type="text/javascript" src="${BASE_PATH}/js/pagejs/template/sys.js"></script>
	<script id="appNotification" type="text/x-handlebars-template">
 				{{#each notification}}
					<li>
	  					 <a class="app-notification__item" href="{{url}}">
							<span class="app-notification__icon">
								<span class="fa-stack fa-lg">
									<i class="{{style}}"></i><i class="{{icon}}"></i>
								</span>
							</span>
	    				<div>
	      					<p class="app-notification__message">{{message}}</p>
	      					<p class="app-notification__meta">{{meta}}</p>
	   				 	</div>
	  			 	 </a>    
  			 		</li>
			{{/each}}
	</script>        
	
	
<script id="appMenu" type="text/x-handlebars-template">
{{#each menuTree}}		
	{{#if subMenuList}}
	       <li class="treeview">
	        	 <a class="app-menu__item" href="{{url}}" data-toggle="treeview">
	        		 <i class="{{icon}}"></i><span class="app-menu__label">{{name}}</span>
	        		 <i class="treeview-indicator fa fa-angle-right"></i>
	        	 </a>
		          <ul class="treeview-menu">	
						{{#each subMenuList}}	       
		            		<li><a class="treeview-item" href="{{url}}"><i class="icon fa fa-circle-o"></i>{{name}}</a></li>
						{{/each}}
		          </ul>
	        </li>
			{{else}}
		    <li>
	        	<a class="app-menu__item menuUrl" href="{{url}}">
	        		<i class="{{icon}}"></i><span class="app-menu__label">{{name}}</span>
	        	</a>
	        </li>
		{{/if}}
{{/each}}
</script>  
jQuery(function($) {
	$.fn.spin = function(opts) {
		this.each(function() {
		  var $this = $(this),
			  data = $this.data();

		  if (data.spinner) {
			data.spinner.stop();
			delete data.spinner;
		  }
		  if (opts !== false) {
			data.spinner = new Spinner($.extend({color: $this.css('color')}, opts)).spin(this);
		  }
		});
		return this;
	};

	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time : new Date()
				});
				// console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});

	$('#btn-view-add').on(
			'click',
			function() {
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						'new',
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : false,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
	$('#btn-view-edit').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext)
				}
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						gr,
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
	
	
	$('#btn-view-del').on(
			'click',
			function() {
				
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				jQuery(cfg.grid_selector).jqGrid(
						'delGridRow',
						gr,
						{
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	$( "#btn-view-da" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
		// var row=jQuery(cfg.grid_selector).jqGrid('getGridParam','selarrrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		// console.log(gr);
		var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		// console.log(r);
		var ajax_button;
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:380,
			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"+r.roleName+"</div></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 保存分配的权限",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
					
						$( "#dialog-confirm" ).removeClass('hide').dialog({
							resizable: false,
							modal: false,
							title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 确认分配权限吗?</h4></div>",
							title_html: true,
							buttons: [
								{
									html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 保存分配的权限",
									"class" : "btn btn-info btn-xs",
									click: function() {
										$( this ).dialog( "close" ); 
										var nodes = $('#tt').tree('getChecked', ['checked','indeterminate']);
										var s = '';
										for(var i=0; i<nodes.length; i++){                
											if (s != '') s += ',';                
											s += nodes[i].id;            
										}
										console.log(s);
										$.ajax({
											type : "post",
											url : contextPath + "/role/insertRoleResources.do",
											data:{roleId:gr,resourcesId:s},
											beforeSend : function(XMLHttpRequest) {
												style_ajax_button('ajax_button',true);
											},
											success : function(rst, textStatus) {
												style_ajax_button('ajax_button',false);
												if (rst) {
													bootbox.dialog({
														title:'系统提示',
														message:rst.errorMessage,
														buttons: 			
														{
															"success" :
															 {
																"label" : "<i class='ace-icon fa fa-check'></i>确定",
																"className" : "btn-sm btn-success",
																"callback": function() {
																	dialog.dialog( "close" ); 
																}
															}
														}
													});
											
												}
											},
											complete : function(XMLHttpRequest, textStatus) {
												style_ajax_button('ajax_button',false);
											},
											error : function() {
												style_ajax_button('ajax_button',true);
											}
										});
									}
								}
								,
								{
									html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
									"class" : "btn btn-xs",
									click: function() {
										$( this ).dialog( "close" );
									}
								}
							]
						});
						
						
					} 
				},
				{
					html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
					"class" : "btn btn-xs",
					click: function() {
						$( this ).dialog( "close" ); 
					} 
				}
			]
		});
		
		 $('#tt').tree('options').url=contextPath+'/role/selectRoleResourceTreeList.do?roleId='+gr
		 $("#tt").tree('reload');
		 $('#tt').tree({cascadeCheck:true});
		 
		 /*spinner=$('#spinner-preview').spin({
				lines:12,//data-min="5" data-max="16" data-step="2" value="12"
				length:7,// data-min="0" data-max="30" value="7"
				width:15,// data-min="2" data-max="20" value="4"
				radius:10,// data-min="0" data-max="40" value="10"
				corners:1,// data-min="0" data-max="1" data-step="0.1" value="1"
				rotate:20,// data-min="0" data-max="90" value="0"
				trail:60,// data-min="10" data-max="100" value="60"
				speed:1// data-min="0.5" data-max="2.2" data-step="0.1" value="1"
			});*/
	});
	/*function style_ajax_button(btn,status){
	//console.log(status);
	if(status){
		$('#'+btn).find('i').removeClass('fa-check');
		$('#'+btn).find('i').addClass('fa-spinner fa-spin');
	}else{
		$('#'+btn).find('i').removeClass('fa-spinner');
		$('#'+btn).find('i').removeClass('fa-spin');
		$('#'+btn).find('i').addClass('fa-check');
	}
	$('#'+btn).disabled=status;
}*/
function style_ajax_button(btnId,status){
	console.log(status);
	var btn=$('#'+btnId);
	if(status){
		btn.find('i').removeClass('fa-check');
		btn.find('i').addClass('fa-spinner fa-spin');
		btn.attr('disabled',"true");
		
	}else{
		btn.find('i').removeClass('fa-spinner');
		btn.find('i').removeClass('fa-spin');
		btn.find('i').addClass('fa-check');
		btn.removeAttr("disabled");
	}
}
});
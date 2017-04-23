jQuery(function($) {
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
			width:750,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> "+r.name+r.userId+" 分配角色</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 保存分配的角色",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						var rowIds = jQuery("#myrole-grid-table").jqGrid('getDataIDs');
						if(rowIds.length<1){
							bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 至少要分配一个角色!</h4></div>", function(result) {
								
							});
							return;
						}
						
						$( "#dialog-confirm" ).removeClass('hide').dialog({
							resizable: false,
							modal: false,
							title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 确认分配角色吗?</h4></div>",
							title_html: true,
							buttons: [
								{
									html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 保存分配的角色",
									"class" : "btn btn-info btn-xs",
									click: function() {
										$( this ).dialog( "close" ); 
										var s='';
										var rowIds = jQuery("#myrole-grid-table").jqGrid('getDataIDs');
										if(rowIds){
											s=rowIds.join(',')
										}
										$.ajax({
											type : "post",
											url : contextPath + "/users/insertUsersRole.do",
											data:{userId:gr,roleId:s},
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
		
		jQuery('#myrole-grid-table').jqGrid('setGridParam', {url : contextPath+"/users/selectRoleListByUserId.do",postData : {userId:gr}}).trigger("reloadGrid");
	});
	$( "#btn-view-initpwd" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		var ajax_button;
		var dialog = $( "#dialog-message-initpwd" ).removeClass('hide').dialog({
			modal: true,
			width:380,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> "+r.name+r.userId+" 初始化密码</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button_initpwd',
					click: function() {
						if ($('#init_passwd').val()!=$('#init_rpasswd').val()) {
							bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 两次输入密码不一致!</h4></div>", function(result) {
								
							});
							return;
						}
						
						$( "#dialog-confirm-initpwd" ).removeClass('hide').dialog({
							resizable: false,
							modal: false,
							title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 确认初始化吗?</h4></div>",
							title_html: true,
							buttons: [
								{
									html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
									"class" : "btn btn-info btn-xs",
									click: function() {
										$( this ).dialog( "close" ); 
										$.ajax({
											type : "post",
											url : contextPath + "/users/updateUsersForInitPassword.do",
											data:{userId:gr,password:$('#init_passwd').val()},
											beforeSend : function(XMLHttpRequest) {
												style_ajax_button('ajax_button_initpwd',true);
											},
											success : function(rst, textStatus) {
												style_ajax_button('ajax_button_initpwd',false);
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
												style_ajax_button('ajax_button_initpwd',false);
											},
											error : function() {
												style_ajax_button('ajax_button_initpwd',true);
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
	});
	$( "#btn-view-da-add" ).on('click', function(e) {
		var gr = jQuery('#allrole-grid-table').jqGrid('getGridParam','selrow');
		if (!gr) {
			bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 请选择要分配的角色!</h4></div>", function(result) {
				
			});
			return;
		}
		var srows=jQuery('#allrole-grid-table').jqGrid('getGridParam','selarrrow');
		var rowIds = jQuery("#myrole-grid-table").jqGrid('getDataIDs');
		var repeat=false,rpn=[];
		$.each(srows,function(i,o){
			var rd=jQuery('#allrole-grid-table').jqGrid('getRowData',o);
			if($.inArray(o,rowIds) > -1){
				repeat=true,rpn.push(rd.roleName);
			}else{
				jQuery("#myrole-grid-table").jqGrid('addRowData',o, rd);
			}
		});
		if(repeat){
			bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 请选择要分配的角色!</h4></div>重复的角色<b>"+rpn.join(',')+"<b>!</h4></div>", function(result) {
				
			});
		}
		
	});
	$( "#btn-view-da-del" ).on('click', function(e) {
		var gr = jQuery('#myrole-grid-table').jqGrid('getGridParam','selrow');
		if (!gr) {
			bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i>请选择要移除的角色!</h4></div>", function(result) {
				
			});
			return;
		}
		var srows=jQuery('#myrole-grid-table').jqGrid('getGridParam','selarrrow');		
		$.each(srows,function(i,o){
			jQuery("#myrole-grid-table").jqGrid('delRowData',o);
		});
		$.each(srows,function(i,o){
			jQuery("#myrole-grid-table").jqGrid('delRowData',o);
		});
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
		$( "#btn-view-import" ).on('click', function(e) {
    		e.preventDefault();
    		reset_uploader();
    		var dialog = $( "#dialog-message-upload" ).removeClass('hide').dialog({
    			modal: true,
    			width:750,
    			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> 导入</h4></div>",
    			title_html: true,
    			buttons: [

    				/*{
    					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 重置",
    					"class" : "btn btn-info btn-xs",
    					id:'ajax_button',
    					click: function() {
    						reset_uploader();

    					}
    				},*/
    				{
    					html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 关闭",
    					"class" : "btn btn-xs",
    					click: function() {
    						$( this ).dialog( "close" );
    					}
    				}
    			]
    		});

    	});
});
function clearAreaCode(){
	$('#cc2').combotree('setValue', '');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {areaCode:''}
	}).trigger("reloadGrid");
}
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
						initForm();
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
	
	$('#btn-view-deploy').on(
			'click',
			function() {
				if(confirm("发布后系统将刷新字典，确定要发布吗?")){
					
				
				$.ajax({
					type : "post",
					url : contextPath+"/dict/deploy.do",
					data:{time:new Date()},
					beforeSend : function(XMLHttpRequest) {
						sb('btn-view-deploy',true,'glyphicon glyphicon-refresh');
					},
					success : function(rst, textStatus) {
						sb('btn-view-deploy',false,'glyphicon glyphicon-refresh');
						if (rst) {
							bootbox.dialog({
								title:'系统提示',
								message:rst.errorMessage,
								detail:rst.detail,
								buttons: 			
								{
									"success" :
									 {
										"label" : "<i class='ace-icon fa fa-check'></i>确定",
										"className" : "btn-sm btn-success",
										"callback": function() {
											//$( this ).dialog( "close" );
										}
									}
								}
							});
					
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
						sb('btn-view-deploy',false,'glyphicon glyphicon-refresh');
					},
					error : function() {
						sb('btn-view-deploy',false,'glyphicon glyphicon-refresh');
					}
				});
				}
				
			});
	$('#tt').tree({
		onClick: function(node){
			autotreeq(node);
		}
	});
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));

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
function sb(btnId,status,iconCss){
	console.log(status);
	var btn=$('#'+btnId);
	if(status){
		btn.find('i').removeClass(iconCss);
		btn.find('i').addClass('fa-spinner fa-spin');
		btn.attr('disabled',"true");
		
	}else{
		btn.find('i').removeClass('fa-spinner');
		btn.find('i').removeClass('fa-spin');
		btn.find('i').addClass(iconCss);
		btn.removeAttr("disabled");
	}
}
});
function initForm(){
	var form=$("#fm-search");
	var re=$(form).find("input[name=categoryId]").val();
	//alert(re);
	var FormPost=$("form[name=FormPost]");
	$(FormPost).find("input[name=categoryId]").val(re);
}

function autotreeq(node){
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {categoryId:node.id}
	}).trigger("reloadGrid");
	
}
function treeAutoSelect(){
	var node = $('#tt').tree('getSelected');
	if(node){
		$(cfg.grid_selector).setSelection(node.id);
	}
	
}
function treeappend(){
	if(!authorConfig.hasOwnProperty(cfg.grid_add_data_url)){
		alert('受限的权限！');
		return;
	}
    var t = $('#tt');
    var node = t.tree('getSelected');
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
					var FormPost=$("form[name=FormPost]");
					$(FormPost).find("input[name=categoryId]").val(node.id);
				}
			})
	$('#categoryId').val(node.id);
}
function treeedit(){
	if(!authorConfig.hasOwnProperty(cfg.grid_edit_data_url)){
		alert('受限的权限！');
		return;
	}
	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	if(!gr){
		var node = $('#tt').tree('getSelected');
		$(cfg.grid_selector).setSelection(node.id);
	}
	gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	
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
}
function treeremove(){
	if(!authorConfig.hasOwnProperty(cfg.grid_delete_data_url)){
		alert('受限的权限！');
		return;
	}
	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	if(!gr){
		var node = $('#tt').tree('getSelected');
		$(cfg.grid_selector).setSelection(node.id);
	}
	gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	jQuery(cfg.grid_selector).jqGrid(
			'delGridRow',
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
}
function collapse(){
    var node = $('#tt').tree('getSelected');
    $('#tt').tree('collapse',node.target);
}
function expand(){
    var node = $('#tt').tree('getSelected');
    $('#tt').tree('expand',node.target);
}
function treereload(){
	$('#tt').tree('reload');
}
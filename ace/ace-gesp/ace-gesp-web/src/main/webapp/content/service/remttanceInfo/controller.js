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
						
					$( "input[name=fileAddr]" ).after( renderBtn());	
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
						$( "input[name=fileAddr]" ).after( renderBtn());	
			});

	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
	
	
});

function renderBtn(){
	var html = [];
	html.push('<span title="汇款单附件" style="float:center;cursor:pointer;" ');
	html.push('class="ui-pg-div ui-inline-edit" ');
	html.push(' onclick="openDialog()" ');
	html.push('onmouseover="jQuery(this).addClass("ui-state-hover");" ');
	html.push('onmouseout="jQuery(this).removeClass("ui-state-hover")" ');
	html.push(' data-original-title="上传">');
	html.push('<span class="ui-icon ace-icon fa fa-arrow-circle-up"></span>');
	html.push('</span>');
	return html.join(' ');
}
var dialog
function openDialog(){
	reset_uploader();
	 dialog = $( "#dialog-message" ).removeClass('hide').dialog({
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
}
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
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
				'selrow');
				if (gr) {
					var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
					$('#pid').val(r.id);
				}
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
	$('#tt').tree({
		onClick : function(node) {
			autotreeq(node);
		}
	});
});

function autotreeq(node) {
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {
			categoryId : node.id
		}
	}).trigger("reloadGrid");

}
function treeAutoSelect() {
	var node = $('#tt').tree('getSelected');
	if (node) {
		$(cfg.grid_selector).setSelection(node.id);
	}

}
function treeappend() {
	if (!authorConfig.hasOwnProperty(cfg.grid_add_data_url)) {
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
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
							.wrapInner('<div class="widget-header" />')
					style_edit_form(form);
					var FormPost = $("form[name=FormPost]");
					$(FormPost).find("input[name=categoryId]").val(node.id);
				}
			})
	$('#pid').val(node.id);
	$('#cc1').combotree('setValue', node.id);
}
function treeedit() {
	if (!authorConfig.hasOwnProperty(cfg.grid_edit_data_url)) {
		alert('受限的权限！');
		return;
	}
	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
	if (!gr) {
		var node = $('#tt').tree('getSelected');
		$(cfg.grid_selector).setSelection(node.id);
	}
	gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');

	jQuery(cfg.grid_selector).jqGrid(
			'editGridRow',
			gr,
			{
				closeAfterAdd : true,
				recreateForm : true,
				viewPagerButtons : true,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
							.wrapInner('<div class="widget-header" />')
					style_edit_form(form);

				}
			})
}
function treeremove() {
	if (!authorConfig.hasOwnProperty(cfg.grid_delete_data_url)) {
		alert('受限的权限！');
		return;
	}
	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
	if (!gr) {
		var node = $('#tt').tree('getSelected');
		$(cfg.grid_selector).setSelection(node.id);
	}
	gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
	jQuery(cfg.grid_selector).jqGrid(
			'delGridRow',
			gr,
			{
				closeAfterAdd : true,
				recreateForm : true,
				viewPagerButtons : true,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
							.wrapInner('<div class="widget-header" />')
					style_edit_form(form);
				}
			})
}
function collapse() {
	var node = $('#tt').tree('getSelected');
	$('#tt').tree('collapse', node.target);
}
function expand() {
	var node = $('#tt').tree('getSelected');
	$('#tt').tree('expand', node.target);
}
function treereload() {
	$('#tt').tree('reload');
}
function clearQparams() {
	$('#cc1').combotree('setValue', '');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {
			parentDepartmentId : '',
			departmentId : ''
		}
	}).trigger("reloadGrid");
}
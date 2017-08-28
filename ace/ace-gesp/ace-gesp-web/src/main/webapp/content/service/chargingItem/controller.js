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
	/*$('#tt').tree({
		onClick: function(node){
			autotreeq(node);
		}
	});*/
	$('#btn-view-add').on('click', function() {
		updateChargItemInfo('');
		/*jQuery(cfg.grid_selector).jqGrid(
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
			
		}*/
	});
	$('#btn-view-edit').on('click', function() {
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext);
		}
		updateChargItemInfo(gr);
		/*jQuery(cfg.grid_selector).jqGrid(
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
				})*/
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

function updateChargItemInfo(gr) {
	$('#period_add').remove();
	$('#updateChargItem').form('clear');
	var name = "添加收费项目";
	if (gr) {
		name = "修改收费项目";
		url = cfg.grid_edit_data_url;
	} else {
		url = cfg.grid_add_data_url;
	}
	var dialog = $("#dialog-add-message")
			.removeClass('hide')
			.dialog(
					{
						modal : true,
						width : 500,
						title : "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"
								+ name + "</div></div>",
						title_html : true,
						buttons : [
								{
									html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
									"class" : "btn btn-info btn-xs",
									id : 'btn-pay',
									click : function() {
										var form = $('#updateChargItem');
										if (!form.form('validate')) {
											alert("请将信息补充完整！");
											return;
										}
										var params = form.serializeObject();
										$
												.ajax({
													type : "post",
													url : url,
													data : {
														jsons : JSON
																.stringify(params)
													},
													beforeSend : function(
															XMLHttpRequest) {
													},
													success : function(rst,
															textStatus) {
														if (rst) {
															bootbox
																	.dialog({
																		title : '系统提示',
																		message : rst.errorMessage,
																		detail : rst.detail,
																		buttons : {
																			"cancel" : {
																				"label" : "<i class='ace-icon fa fa-check'></i>确定",
																				"className" : "btn-sm btn-success",
																				"callback" : function() {
																					if (rst.status == '0') {
																						$(
																								dialog)
																								.dialog(
																										"close");
																						jQuery(
																								cfg.grid_selector)
																								.jqGrid(
																										'setGridParam',
																										{
																											page : 1
																										})
																								.trigger(
																										"reloadGrid");
																					}
																				}
																			}
																		}
																	});
														}
													},
													complete : function(
															XMLHttpRequest,
															textStatus) {
													},
													error : function() {
														alert("修改有误！");
													}
												});
									}
								},
								{
									html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
									"class" : "btn btn-xs",
									click : function() {
										$(this).dialog("close");
									}
								}]
					});
	var itemType = 1;
	var whetherMemberlevel = 0;
	var whetherPeriod = 0;
	//var whetherCustom = 0;
	if (gr) {
		$('#update_id').val(
				jQuery(cfg.grid_selector).jqGrid('getCell', gr, 'id'));
		$('#update_name').val(
				jQuery(cfg.grid_selector).jqGrid('getCell', gr, 'name'));
		itemType = jQuery(cfg.grid_selector).jqGrid('getCell', gr, 'itemType');
		whetherMemberlevel = jQuery(cfg.grid_selector).jqGrid('getCell', gr,
				'whetherMemberlevel');
		whetherPeriod = jQuery(cfg.grid_selector).jqGrid('getCell', gr,
				'whetherPeriod');
		var dt = document.getElementById('update_status');
		dt.checked = false;
		var stat = jQuery(cfg.grid_selector).jqGrid('getCell', gr, 'status');
		if (stat) {
			dt.checked = true;
		}
	}
	$('#update_itemType').combobox({
		url : portalPath + '/dict/findListByCategoryId.do?categoryId=103',
		method : 'post',
		valueField : 'code',
		textField : 'name',
		editable : false,
		panelHeight : 200,
		required : true,
		onLoadSuccess : function() {
			$("#update_itemType").combobox('setValue', itemType);
		}
	});
	$('#update_whetherMemberlevel').combobox(
			{
				url : portalPath
						+ '/dict/findListByCategoryId.do?categoryId=82',
				method : 'post',
				valueField : 'code',
				textField : 'name',
				editable : false,
				panelHeight : 200,
				required : true,
				onLoadSuccess : function() {
					$("#update_whetherMemberlevel").combobox('setValue',
							whetherMemberlevel);
				}
			});
	$('#update_whetherPeriod').combobox({
		url : portalPath + '/dict/findListByCategoryId.do?categoryId=82',
		method : 'post',
		valueField : 'code',
		textField : 'name',
		editable : false,
		panelHeight : 200,
		required : true,
		onLoadSuccess : function() {
			$("#update_whetherPeriod").combobox('setValue', whetherPeriod);
			if (whetherPeriod == '1') {
				appendDiv(gr);
			}
		},
		onSelect : function(record) {
			if (record.code == "1") {
				appendDiv(gr);
			} else {
				$('#period_add').remove();
			}
		}
	});
	/*$('#update_whetherCustom').combobox({
		url:portalPath +'/dict/findListByCategoryId.do?categoryId=05',
	    method:'post',
	    valueField:'code',
	    textField:'name',
	    editable:false,
	    panelHeight:200,
	    required:true,
	    onLoadSuccess:function(){
	    	$("#dia_whetherCustom").combobox('setValue', whetherCustom);
	    }
	});
	$('#dia_payMethod').combobox({
		url:contextPath +'/dict/findListByCategoryId.do?categoryId=01',
	    method:'post',
	    valueField:'code',
	    textField:'name',
	    editable:false,
	    panelHeight:200,
	    required:true,
	    onLoadSuccess:function(){
	    	$('#dia_payMethod').combobox('setValue', se);
	    }
	});*/
}

function appendDiv(gr) {
	var html = new Array();
	html = html + '<div id="period_add" class="profile-info-row">';
	html = html + '<div class="profile-info-name">收费周期</div>';
	html = html + '<div class="profile-info-value">';
	html = html
			+ '<input style="width: 80px;height:25px;margin-right:5px;" value="1" id="update_year" name="year">';
	html = html
			+ '<input class="easyui-combobox" name="period" id="upate_period" style="width: 60px;height:25px;margin-right:5px;" />';
	html = html
			+ '<input style="width: 80px;height:25px;margin-left:5px;" value="1" id="update_times" name="times" >次';
	html = html + '</div></div>';
	$('#html_whetherPeriod').parent().append(html);
	$('#upate_period').combobox(
			{
				url : portalPath
						+ '/dict/findListByCategoryId.do?categoryId=104',
				method : 'post',
				valueField : 'code',
				textField : 'name',
				editable : false,
				panelHeight : 200,
				required : true,
				onLoadSuccess : function() {
					$('#update_year').numberbox({
						min : 1,
						max : 99,
						required : true,
						precision : 0
					});
					$('#update_times').numberbox({
						min : 1,
						max : 99,
						required : true,
						precision : 0
					});
					if (gr) {
						var periods = jQuery(cfg.grid_selector).jqGrid(
								'getCell', gr, 'periods');
						if (periods) {
							$("#upate_period").combobox('setValue', periods);
							$("#update_year").numberbox(
									'setValue',
									jQuery(cfg.grid_selector).jqGrid('getCell',
											gr, 'year'));
							$("#update_times").numberbox(
									'setValue',
									jQuery(cfg.grid_selector).jqGrid('getCell',
											gr, 'times'));
						}
					} else {
						$("#upate_period").combobox('setValue', 1);
						$("#update_year").numberbox('setValue', 1);
						$("#update_times").numberbox('setValue', 1);
					}
				}
			});
}

function autotreeq(node) {
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {
			pid : node.id
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
jQuery(function($) {
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

});

function preview(id, title) {
	var dialog = $("#dialog-message-view")
			.removeClass('hide')
			.dialog(
					{
						modal : false,
						width : 800,
						title : "<div class='widget-header widget-header-small'><div class='widget-header-pd'>"
								+ title + "</div></div>",
						title_html : true,
						buttons : [

						{
							html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 关闭",
							"class" : "btn btn-info btn-xs",
							click : function() {
								$(this).dialog("close");
							}
						}]
					});
	viewNorm(id);
}
function loadView(id) {
	$.ajax({
		type : "post",
		url : cfg.view_load_data_url,
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			$.each(rst.value, function(key, value) {
				if (key == 'category') {
					value = rsd(value, '90');
				}
				if (key == 'status') {
					value = value == "1" ? "on" : "off";
				}
				if (key.indexOf('Date') != -1 || key.indexOf('time') != -1
						|| key.indexOf('Time') != -1
						|| key.indexOf('birthday') != -1) {
					value = Common.DateFormatter(value);
				}
				$("#dialog-message-view").find('#' + key).html(value);
			});
		},
		error : function() {
			alert("加载错误！");
		}
	});
}

function normCfg(id, title) {
	var dialog = $("#dialog-message")
			.removeClass('hide')
			.dialog(
					{
						modal : false,
						width : 800,
						title : "<div class='widget-header widget-header-small'><div class='widget-header-pd'>"
								+ title + "</div></div>",
						title_html : true,
						buttons : [

								{
									html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
									"class" : "btn btn-info btn-xs",
									id : "btn-submit",
									click : function() {
										$('#fm-norm-cfg').submit();
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
	selectAllNorm(id);
}

function selectAllNorm(id, name) {
	$.ajax({
		type : "post",
		url : contextPath + "/norm/selectAllNorm.do",
		data : {
			topicId : id,
			name : name
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var defaultValue = '';
			if (name) {
				defaultValue = name;
			}
			var html = [];
			html.push("<form id='fm-norm-cfg' onsubmit='return ckform()'>");
            html.push("<div style='text-align:right;padding-bottom:2px'>");
			html.push("搜索 <input type='text' id='_q' name='_q' value='"
					+ defaultValue + "' />");
			html.push("</div>");
			html.push("<div class='panel panel-default'>");
			html.push("<div class='panel-body'>");
			html.push("<input type='hidden' name='topicId' value='" + id);
			html.push("'/>");
			html.push("<table class='table'>");
			$.each(rst, function(i, o) {
				html.push("<tr><td>");
				html.push("<input type='");
				html.push("checkbox' id='");
				html.push(o.category.code);
				html.push("' class='xcheckgroup");
				html.push(o.category.code);
				html.push(" checkAllCurrent' /> ");
				html.push("<label style='");
				html.push("font-weight:800' for='");
				html.push(o.category.code);
				html.push("'>");
				html.push(o.category.name);
				html.push("</label>");
				html.push("</td></tr>");
				html.push("<tr><td>");
				$.each(o.items, function(j, e) {
					var checked = "";
					if (e.value) {
						checked = "checked";
					}
					html.push("<div class='checkboxitem'>");
					html
							.push("<input type='checkbox' name='" + e.id
									+ "' id='");
					html.push(e.id);
					html.push("' class='xcheckgroup");
					html.push(o.category.code);
					html.push(" checkItem' value='");
					html.push(e.id);
					html.push("' ");
					html.push(checked);
					html.push("/> <label for='");
					html.push(e.id);
					html.push("' >");
					html.push(e.name);
					html.push("</label>");
					html.push("</div>");
				});
				html.push("</td></tr>");
				$.XCheck({
					groupClass : ".xcheckgroup" + o.category.code,
					afterCheckItem : function() {
						var ops = this.options;
						console.log('当前的options为：' + JSON.stringify(ops));
						console.log("afterCheckItem");
					}
				});
			});
			html.push("</table>");
			html.push("</div></div></form>");
			$("#dialog-message").html(html.join(""));
			$('#_q').bind('keypress', function(event) {
            				if (event.keyCode == "13") {
            					//alert('你输入的内容为：' + $('#_q').val());
            					selectAllNorm(id, $('#_q').val());
            				}
            			});
			$('#fm-norm-cfg').ajaxForm({
				beforeSubmit : function(formData, jqForm, options) {
					var params = {};
					$.each(formData, function(n, obj) {
						params[obj.name] = obj.value;
					});
					inertTopicNorm(params.topicId, params);
					return false;
				}
			});
		},
		error : function() {
			alert("加载错误！");
		}
	});
}
function inertTopicNorm(id, p) {
	var params = [];

	$.each(p, function(key, value) {
		if ((key != 'topicId') && (key != '_q')) {
			params.push({
				topicId : id,
				normId : value
			});
		}
	});
	console.log(params);
	$.ajax({
		type : "post",
		url : contextPath + "/topic/insertTopicNorm.do",
		data : {
			topicId : id,
			name:param._q,
			jsons : JSON.stringify(params)
		},
		beforeSend : function(XMLHttpRequest) {
			$("#btn-submit").attr('disabled', true);
		},
		success : function(rst, textStatus) {
			$("#btn-submit").removeAttr("disabled");
			alert(rst.errorMessage);

		},
		error : function() {
			$("#btn-submit").removeAttr("disabled");
			alert("加载错误！");
		}
	});
}
function ckform() {
	return false;
}

function viewNorm(topicId) {
	var tableId = "detail";
	if ($('#' + tableId).hasClass('dataTable')) {
		dttable = $('#' + tableId).dataTable();
		dttable.fnClearTable(); // 清空一下table
		dttable.fnDestroy(); // 还原初始化了的datatable
	}
	$('#' + tableId).DataTable(
			{
				ajax : {
					url : contextPath + '/norm/selectNormByTopicId.do?topicId='
							+ topicId,
					dataSrc : 'data'
				},
				columns : [{
					data : 'rownum'
				}, {
					data : 'name'
				}, {
					data : 'remark'
				}],
				bAutoWidth : false,
				"fnInitComplete" : function() {
					this.fnAdjustColumnSizing(true);
				},
				"createdRow" : function(row, data, dataIndex) {
					$(row).children('td').eq(0).attr('style',
							'text-align: center;font-weight:800')

				},
				"aLengthMenu" : [5, 10, 15, 20],
				"oLanguage" : {
					"sLengthMenu" : "每页显示 _MENU_ 条记录",
					"sZeroRecords" : "对不起，查询不到任何相关数据",
					"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
					"sInfoEmpty" : "第 0 到 0 条记录，共 0 条",
					"sInfoFiltered" : "数据表中共为 _MAX_ 条记录)",
					"sProcessing" : "正在加载中...",
					"sSearch" : "搜索 ",
					"sUrl" : "",
					"oPaginate" : {
						"sFirst" : "",
						"sPrevious" : "",
						"sNext" : "",
						"sLast" : ""
					},
					"oAria" : {
						"sSortAscending" : ": 升序排列",
						"sSortDescending" : ": 降序排列"
					}
				}
			});

}
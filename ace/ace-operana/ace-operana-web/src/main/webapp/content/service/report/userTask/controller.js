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
                buid(params);
				return false;
			}
		});
	});
});

function buid(params) {
	var tableId = "report";
	if ($('#' + tableId).hasClass('dataTable')) {
		dttable = $('#' + tableId).dataTable();
		dttable.fnClearTable(); // 清空一下table
		dttable.fnDestroy(); // 还原初始化了的datatable
	}
	var table = $('#' + tableId)
			.DataTable(
					{
						ajax : {
							url : contextPath + '/anslysis/query.do?reportId=userTask',
							dataSrc : 'value',
							data:params
						},
						columns : [{
							data : 'rownum'
						}, {
							data : 'name'
						}, {
							data : 'department_name'
						}, {
                         							data : 'total'
                         						}, {
							data : 'OPEN'
						}, {
							data : 'CLOSED'
						}, {
                         							data : 'NOGOING'
                         						}],
						bAutoWidth : false,
						"fnInitComplete" : function() {
							this.fnAdjustColumnSizing(true);
							$('[data-rel=tooltip]').tooltip();
						},
						"createdRow" : function(row, data, dataIndex) {
							$(row).children('td').eq(0).attr('style',
									'text-align: center;font-weight:800');

						},
						"aLengthMenu" : [50, 100, 500, 50000],
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

function clearQparams() {
	$('#cc1').combotree('setValue', '');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {
			detpId : ''
		}
	}).trigger("reloadGrid");
}
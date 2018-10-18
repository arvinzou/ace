jQuery(function($) {
	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit: function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time: new Date()
				});
				// console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page: 1,
					postData: params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});

	$('#btn-view-add').on(
		'click',
		function() {
			location.href = contextPath + '/dynamic/service/taskCmcc/add.jsp?id=' + urlParams.id;
		});

	$("input[name=dateStart]").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		language: 'zh-CN',
		weekStart: 1,
		todayBtn: 1, //显示‘今日’按钮
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 'hour', //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
		clearBtn: true, //清除按钮
		forceParse: 0
	});
	$('input[name=dateStart]').focus(function() {
		$(this).blur(); //不可输入状态
	})


	$("input[name=dateEnd]").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		language: 'zh-CN',
		weekStart: 1,
		todayBtn: 1, //显示‘今日’按钮
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 'hour', //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
		clearBtn: true, //清除按钮
		forceParse: 0
	});
	$('input[name=dateEnd]').focus(function() {
		$(this).blur(); //不可输入状态
	})
});

function reloadGrid() {
	console.log('reloadGrid');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
}
var show = false;

function del(rowid) {
	console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid(
		'delGridRow',
		rowid, {
			beforeShowForm: function(e) {
				var form = $(e[0]);
				if (!show) {
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
				}
				show = true;
			}
		});
}

function preview(id) {
	$('#modal-preview').modal('show');
	$.ajax({
		type: "post",
		url: contextPath + "/taskCmcc/selectById.do",
		data: {
			id: id
		},
		beforeSend: function(XMLHttpRequest) {
			startLoad();
		},
		success: function(rst, textStatus) {
			var html = new Array();
			var nodes = rst.value.tel.split(';');
			$.each($(nodes), function(i, o) {
				if (o.href != '') {
					html.push('<div class="layout-user" >');
					html.push('<user  class="badge">');
					html.push(o);
					html.push('</user>');
					html.push('</div>');
				}
			});
			$('#task-content').html(html.join(''));
			$('#msg-content').html(rst.value.msg);
		},
		complete: function(XMLHttpRequest, textStatus) {
			stopLoad();
		},
		error: function() {
			stopLoad();
		}
	});
}

function resend(id){
	$.ajax({
		type: "post",
		url: contextPath + "/taskCmcc/updateTaskStatusCmccByTaskCmccId.do",
		data: {
			id: id
		},
		beforeSend: function(XMLHttpRequest) {
			startLoad();
		},
		success: function(rst, textStatus) {
			jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
		},
		complete: function(XMLHttpRequest, textStatus) {
			stopLoad();
		},
		error: function() {
			stopLoad();
		}
	});
}

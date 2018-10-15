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
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});
	$("input[name=startDate]").datetimepicker({
			format: 'yyyy-mm-dd hh:mm',
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
		$('input[name=startDate]').focus(function() {
			$(this).blur(); //不可输入状态
		})
		$("input[name=endDate]").datetimepicker({
			format: 'yyyy-mm-dd hh:mm',
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
		$('input[name=endDate]').focus(function() {
			$(this).blur(); //不可输入状态
		})
});
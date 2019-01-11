var loading = {};
var editor;
window.onload = function() {
	jQuery(function($) {
		$(".breadcrumb").append("<li><span>事故快报</span></li>");
		initPage();
		initEvents();

	});
}


/*页面渲染*/
function render(obj, data, tplId) {
	var tpl = document.getElementById(tplId).innerHTML;
	var html = juicer(tpl, {
		data: data,
	});
	$(obj).html(html);
}

function initPage() {
	initForm();
}

function initEvents() {
	/*表单验证*/
	$("#fm-add").validate({
		onfocusout: function(element) {
			$(element).valid();
		},
		rules: {
			areaCode: {
				required: true,
				maxlength: 50
			},
			weather: {
				required: true,
				maxlength: 50
			},
			vehicleType: {
				required: true,
				maxlength: 50
			}
		},
		messages: {
			areaCode: {
				required: "请输入行政区划",
				maxlength: "行政区划字符长度不能超过50"
			},
			weather: {
				required: "请输入天气",
				maxlength: "天气字符长度不能超过50"
			},
			vehicleType: {
				required: "请输入车型",
				maxlength: "车型字符长度不能超过50"
			}
		}
	});
	/*监听表单提交*/
	$('#fm-add').ajaxForm({
		beforeSubmit: function(formData, jqForm, options) {
			var params = {};
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			$.extend(params, {
				time: new Date(),
				//coverUrl: $('#coverUrl').attr("src"),
			});
			console.log(params);
			save(params);
			return false;
		}
	});
}
/*保存表单**/
function save(params) {
	$.extend(params, {});
	startLoad();
	$.ajax({
		url: contextPath + "/traAcc/insertTraAcc",
		type: "post",
		async: false,
		data: {
			jsons: JSON.stringify(params)
		},
		success: function(result) {
			stopLoad();
			alert(result.errorMessage);
			if (result.status == 0) {
				window.location.href = '../index.jsp?id=' + urlParams.id;
			}
		},
		error: function() {
			stopLoad();
			alert("对不起出错了！");
		}
	});
}

function initForm() {
	var data = staticDictObject;
	render('#fm-add-panel', data, 'tpl-fm-add');

	$("input[name=accidentTime]").datetimepicker({
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
	$('input[name=accidentTime]').focus(function() {
		$(this).blur(); //不可输入状态
	});

	$('input[name=roadManId]').combogrid({
		panelWidth: 500,
		idField: 'id',
		textField: 'name',
		url: contextPath + '/roadMan/getListByCondition',
		mode: 'remote',
		fitColumns: true,
		method: 'get',
		columns: [
			[{
				field: 'name',
				title: '姓名',
				width: 100
			}, {
				field: 'orgName',
				title: '单位',
				width: 100
			}, {
				field: 'areaName',
				title: '县区',
				width: 100
			}]
		]
	});
	
	$('input[name=roadSectionId]').combogrid({
		panelWidth: 500,
		idField: 'id',
		textField: 'name',
		url: contextPath + '/roadSection/getListByCondition',
		mode: 'remote',
		fitColumns: true,
		method: 'get',
		columns: [
			[{
				field: 'name',
				title: '路段名称',
				width: 100
			}, {
				field: 'roadManName',
				title: '路长',
				width: 100
			}, {
				field: 'startName',
				title: '路段起始',
				width: 100
			}, {
				field: 'endName',
				title: '路段截止',
				width: 100
			}]
		]
	});
}

function latitude(latitude) {
	$("input[name=latitude]").val(latitude);
}

function longitude(longitude) {
	$("input[name=longitude]").val(longitude);
}

function addr(addr) {
	$("input[name=address]").val(addr);
}

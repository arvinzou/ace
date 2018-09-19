var loading = {};
var editor;
window.onload = function() {
	jQuery(function($) {
		$(".breadcrumb").append("<li><span>编辑报道</span></li>");
		initForm();
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
	initUpload();
}

function initEvents() {
	/*表单验证*/
	$("#fm-edit").validate({
		onfocusout: function(element) {
			$(element).valid();
		},
		rules: {
			rid: {
				required: true,
				maxlength: 50
			},
			uid: {
				required: true,
				maxlength: 50
			}
		},
		messages: {
			rid: {
				required: "请输入直播间编号",
				maxlength: "直播间编号字符长度不能超过50"
			},
			uid: {
				required: "请输入用户编号",
				maxlength: "用户编号字符长度不能超过50"
			}
		}
	});
	/*监听表单提交*/
	$('#fm-edit').ajaxForm({
		beforeSubmit: function(formData, jqForm, options) {
			var params = {};
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			$.extend(params, {
				id: urlParams.did
			});
			console.log(params);
			var imgs = [];
			var data={};
			$(".image-boxs .cover").each(function(i,o){
				imgs.push({
						url: $(o).attr("src"),
						w: 0,
						h: 0
				})
			});
			data.rpt=params;
			data.imgs=imgs;
			save(data);
			return false;
		}
	});
	$(".image-box .delete").click(function(){
	  $(this).parent().remove();
	});
}
/*保存表单**/
function save(params) {
	$.extend(params, {});
	console.log(JSON.stringify(params));
	startLoad();
	$.ajax({
		url: contextPath + "/liveRpt/updateLiveRpt",
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
	startLoad();
	$.ajax({
		url: contextPath + "/liveRpt/selectLiveRptByPrimaryKey",
		type: "post",
		async: false,
		data: {
			id: urlParams.did
		},
		success: function(result) {
			stopLoad();
			if (result.status == 0) {
				var data = {};
				data['o'] = result.value;
				render('#fm-edit', data, 'tpl-fm');
				initPage();

			} else {
				alert(result.errorMessage);
			}
		},
		error: function() {
			stopLoad();
			alert("对不起出错了！");
		}
	});
}





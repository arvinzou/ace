var loading = {};
var editor;
window.onload = function() {
	jQuery(function($) {
		$(".breadcrumb").append("<li><span>发布报道</span></li>");
		initEvents();
		initPage();
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
	$("#fm-add").validate({
		onfocusout: function(element) {
			$(element).valid();
		},
		rules: {
		    rid: {
                required: true
            }
		},
		messages: {
		    rid: {
                required: "请输入直播编号"
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
				uid:userProp.openId
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
		url: contextPath + "/liveRpt/insertLiveRpt",
		type: "post",
		async: false,
		data: {
			jsons: JSON.stringify(params)
		},
		success: function(result) {
			stopLoad();
			alert(result.errorMessage);
			if (result.status == 0) {
				window.location.href = '../../live/index.jsp?id=' + urlParams.id;
			}
		},
		error: function() {
			stopLoad();
			alert("对不起出错了！");
		}
	});
}




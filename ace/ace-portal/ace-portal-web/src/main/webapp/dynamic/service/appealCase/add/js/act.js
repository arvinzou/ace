var loading = {};
var editor;
var urlParams = {};
function getQueryString() {
var qs = location.search.substr(1),
args = {},
items = qs.length ? qs.split("&") : [],
item = null,
len = items.length;

for (var i = 0; i < len; i++) {
item = items[i].split("=");
var name = decodeURIComponent(item[0]),
value = decodeURIComponent(item[1]);
if (name) {
args[name] = value;
}
}
return args;
}
urlParams = getQueryString();
window.onload = function() {
	jQuery(function($) {
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
	$("#fm-add input[name=companyName]").val(userProp.corpName);
	$("#fm-add input[name=submitName]").val(userProp.name);
	$("#fm-add input[name=tel]").val(userProp.mobile);
}

function initEvents() {
	/*表单验证*/
	$("#fm-add").validate({
		onfocusout: function(element) {
			$(element).valid();
		},
		rules: {
		    title: {
                required: true,
                maxlength:28
            },
            content: {
                required: true
            }
            ,
            companyName: {
                required: true,
                maxlength:500
            }
             ,
            submitName: {
                required: true
            }
             ,
            tel: {
                required: true
            }
		},
		messages: {
		    title: {
                required: "请输入标题（建议字数在28个字以内，不超过50个字)",
                maxlength:"标题最多50字"
            },
            content: {
                required: "请输入诉求内容",
                maxlength:"诉求内容最多500字"
            },
            companyName: {
                 required: "请输入企业/单位名称"
            },
             submitName: {
                  required: "请输入联系人姓名"
             }
             ,
              tel: {
                   required: "请输入联系电话"
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
				appealId:urlParams.did,
				mediType:'1'

			});
			console.log(params);
			var imgs = [];
			var data={};
			$(".image-boxs .cover").each(function(i,o){
				imgs.push({
						mediUrl: $(o).attr("src"),
						type:'1',
						mediType:'img',
						name:'图片'
				})
			});
			data.o=params;
			data.list=imgs;
			data.captcha=params.captcha;
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
		url: contextPath + "/www/appealCase/insertAppealCasePc.do",
		type: "post",
		async: false,
		data: {
			jsons: JSON.stringify(params)
		},
		success: function(result) {
			stopLoad();
			alert(result.errorMessage);
		},
		error: function() {
			stopLoad();
			alert("对不起出错了！");
		}
	});
}


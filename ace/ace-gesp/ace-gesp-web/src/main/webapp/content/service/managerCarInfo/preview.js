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
	loadView(id);
});

function loadView(id) {
	$.ajax({
		type : "post",
		url : contextPath + "/managerCar/selectByCarInfo.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			$.each(rst.value, function(key, value) {
				/*if (key.indexOf('Date')!=-1||key.indexOf('time')!=-1||key.indexOf('Time')!=-1) {
					value = Common.DateFormatter(value);
				}*/
				$("#tab1").find('#' + key).html(value);
			});
			$('#brandName').html(rst.value.brandName+rst.value.brandModel);
			$('#vin').html(rst.value.vin);
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}
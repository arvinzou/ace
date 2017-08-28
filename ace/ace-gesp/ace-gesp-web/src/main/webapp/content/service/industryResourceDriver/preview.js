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
		url : contextPath + "/industryResource/selectDriverInfoById.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			$.each(rst.value, function(key, value) {
				if (key == 'driverLicCarType') {//
					value = rsd(value, '77');
				}
				if (key == 'cooperationMode') {
					value = rsd(value, '75');
				}
				if (key == 'certPersonType') {//从业人员类别
					value = rsd(value, '78');
				}
				if (key == 'certType') {
					value = rsd(value, '73');
				}
				if (key == 'filingCertType') {
					value = rsd(value, '74');
				}
				if (key == 'recordTime') {
					value = rsd(value, '79');
				}
				if (key == 'sex') {
					value = rsd(value, '01');
				}
				if (key == 'certCompanyName') {
					value = rsd(value, '76');
				}
				if (key == 'birthdate'||key == 'initDrivingLicDate') {
					value = Common.DateFormatter(value);
				}
				
				$('#' + key).html(value);
			});
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}

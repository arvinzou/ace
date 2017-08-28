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
	loadViewCarProduction(id);
});

function loadView(id) {
	$.ajax({
		type : "post",
		url : contextPath + "/industryResource/selectCarInfoById.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			$.each(rst.value, function(key, value) {
				if (key == 'legalPersonIdType') {
					value = rsd(value, '70');
				}
				if (key == 'plateColor') {
					value = rsd(value, '90');
				}
				if (key == 'bussType') {
					value = rsd(value, '89');
				}
				if (key == 'bussStatus') {
					value = rsd(value, '88');
				}
				if (key == 'IsGAT') {
					value = rsd(value, '82');
				}
				if (key == 'insuranceCompany') {
					value = rsd(value, '83');
				}
				if (key == 'IsInstallOBD') {
					value = rsd(value, '82');
				}
				if (key == 'IsInstallGPS') {
					value = rsd(value, '82');
				}
				if (key == 'fuelType') {
					value = rsd(value, '87');
				}
				if (key == 'transportMode') {
					value = rsd(value, '86');
				}
				if (key.indexOf('Date')!=-1||key.indexOf('time')!=-1||key.indexOf('Time')!=-1) {
					value = Common.DateFormatter(value);
				}
				
				
				$("#tab1").find('#' + key).html(value);
			});
			// loadViewCarType(rst.value.carType);
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}
function loadViewCarProduction(id) {
	$.ajax({
		type : "post",
		url : contextPath + "/industryResource/selectCarProductionInfoById.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			$.each(rst.value, function(key, value) {
				if (key == 'legalPersonIdType') {
					value = rsd(value, '70');
				}
				if (key == 'carCategory') {
					value = rsd(value, '84');
				}
				if (key == 'carType') {
					value = rsd(value, '86');
				}
				if (key.indexOf('Date')!=-1||key.indexOf('time')!=-1||key.indexOf('Time')!=-1) {
					value = Common.DateFormatter(value);
				}
				
				$("#tab2").find('#' + key).html(value);
			});
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}
function loadViewCarType(id) {
	$.ajax({
		type : "post",
		url : contextPath + "/industryResource/selectCarTypeInfoById.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			$.each(rst.value, function(key, value) {
				if (key == 'legalPersonIdType') {
					value = rsd(value, '70');
				}
				if (key.indexOf('Date')!=-1||key.indexOf('time')!=-1||key.indexOf('Time')!=-1) {
					value = Common.DateFormatter(value);
				}
				$("#tab3").find('#' + key).html(value);
			});
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}
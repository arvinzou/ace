jQuery(function($) {
	$.fn.spin = function(opts) {
		this.each(function() {
			var $this = $(this), data = $this.data();

			if (data.spinner) {
				data.spinner.stop();
				delete data.spinner;
			}
			if (opts !== false) {
				data.spinner = new Spinner($.extend({
					color : $this.css('color')
				}, opts)).spin(this);
			}
		});
		return this;
	};

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
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});

	

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
	$('#fm-dialog').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			console.log(params);
			press(params);
			return false;
		}
	});
});
var dialog={};
function showPressDia(memberCode,status,departmentName,id){
	dialog = $( "#dialog-message" ).removeClass('hide').dialog({
		modal: true,
		width:600,
		title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"+departmentName+"</div></div>",
		title_html: true,
		buttons: [ 
			
			{
				html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
				"class" : "btn btn-info btn-xs",
				id:'btn-press',
				click: function() {
		
					params.memberCode = memberCode;
					$('#fm-dialog').submit();
				} 
			},
			{
				html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
				"class" : "btn btn-xs",
				click: function() {
					$( this ).dialog( "close" ); 
				} 
			}
		]
	});
	$('input[name=chargingItemId]').combobox(
			{
				url : contextPath
						+ '/chargingItem/selectListByDeptId.do'
						,
				valueField : 'code',
				textField : 'name',
				onLoadSuccess : function() {

				},
				onSelect : function(record) {

					getPayInfo(memberCode,$('input[name=memberLevelId]').val(), record.id);
				}
			});
	$('input[name=memberLevelId]')
	.combobox(
			{
				url : contextPath
						+ '/memberLevel/selectListByDeptId.do'
						,
				valueField : 'code',
				textField : 'name',
				onLoadSuccess : function() {
					//getPayInfo(memberCode, $('input[name=memberLevelId]').val(), $('input[name=chargingItemId]').val());
				},
				onSelect : function(record) {
					console.log(record);
					getPayInfo(memberCode, record.id, $('input[name=chargingItemId]').val());
				}
			});
	$('input[name=departmentName]').val(departmentName);
}
var params = {};
function beforeSubmit() {
	return false;
}
function press(params){
	console.log(params);
	$.ajax({
		type : "post",
		url : contextPath + "/paymentPressInfo/insertPaymentPressInfo.do",
		data:{jsons:JSON.stringify(params)},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-press').attr('disabled',"true");
		},
		success : function(rst, textStatus) {
			$('#btn-press').removeAttr("disabled");
			bootbox.dialog({
				title:'系统提示',
				message:rst.errorMessage,
				detail:rst.detail,
				buttons: 			
				{
					"cancel" :
					 {
						"label" : "<i class='ace-icon fa fa-check'></i>确定",
						"className" : "btn-sm btn-success",
						"callback": function() {
							$( dialog ).dialog( "close" ); 
						}
					}
				}
			});
			
		},
		complete : function(XMLHttpRequest, textStatus) {
			$('#btn-press').removeAttr("disabled");
		},
		error : function() {
			$('#btn-press').removeAttr("disabled");
		}
	});
}
function getPayInfo(memberCode, memberLevelId, chargingItemId) {
	console.log(params);
	$.ajax({
		type : "post",
		url : contextPath + "/payCfg/getPayInfo.do",
		data : {
			memberCode : memberCode,
			memberLevelId : memberLevelId,
			chargingItemId : chargingItemId
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			if (rst.state) {
				$('#fm-dialog').form('load', rst.value);
			} else {
				alert(rst.errorMessage);
			}

		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {

		}
	});
}
function lookMemberInfo(id,status,departmentName,memberCode){
	parent.addPanel(departmentName,contextPath+'/dynamic/service/memberCenter/index.jsp?deptId='+memberCode,true);
}
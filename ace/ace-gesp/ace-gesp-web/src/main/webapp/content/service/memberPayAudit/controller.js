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
				// console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});

	$("#btn-view-pay").on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext);
			return;
		}

		var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
		showPayDia(r.memberCode, r.status, r.departmentName);
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
			pay(params);
			return false;
		}
	});
});
var dialog={};
function showAuditDia(memberCode,status,departmentName,id,memberLevelName){
	if(status!=0){
		alert("该条数据已经审核过了！");
		return;
	}
	dialog = $( "#dialog-message" ).removeClass('hide').dialog({
		modal: true,
		width:800,
		title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"+departmentName+"</div></div>",
		title_html: true,
		buttons: [ 
			
			{
				html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
				"class" : "btn btn-info btn-xs",
				id:'btn-audit',
				click: function() {
					audit(departmentName,id,memberLevelName);
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
	loadMemberPayAudit(departmentName,id,memberLevelName);
}
function audit(departmentName,id,memberLevelName){
	var params={};
	params.id=id;
	params.status=$('input[name=auditStatus]:checked').val();
	params.auditRemark=$('textarea[name=auditRemark]').val();
	//console.log(params);
	$.ajax({
		type : "post",
		url : contextPath + "/memberPayInfo/updateAudit.do",
		data:{jsons:JSON.stringify(params)},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-audit').attr('disabled',"true");
		},
		success : function(rst, textStatus) {
			$('#btn-audit').removeAttr("disabled");
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
			jQuery(cfg.grid_selector).jqGrid('setGridParam', {
			}).trigger("reloadGrid");
		},
		complete : function(XMLHttpRequest, textStatus) {
			$('#btn-audit').removeAttr("disabled");
		},
		error : function() {
			$('#btn-audit').removeAttr("disabled");
		}
	});
}

function lookMemberInfo(id,status,departmentName,memberCode){
	parent.addPanel(departmentName,contextPath+'/dynamic/service/memberCenter/index.jsp?deptId='+memberCode,true);
}

function loadMemberPayAudit(departmentName,id,memberLevelName){
	$('#departmentName').html(departmentName);
	$('#memberLevel').html(memberLevelName);
	$.ajax({
		type : "post",
		url : contextPath + "/memberPayInfo/selectByPrimarkey.do",
		data:{id:id},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			$('#chargingItemName').html(rst.chargingItemName);
			$('#payAmount').html(rst.payAmount);
			$('#remark').html(rst.remark);
			$('#endDate').html(rst.endDate);
			$('#createDate').html(rst.createDate);
			$('#createUserName').html(rst.createUserName);
			/*jQuery(cfg.grid_selector).jqGrid('setGridParam', {
			}).trigger("reloadGrid");*/
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
		}
	});
}
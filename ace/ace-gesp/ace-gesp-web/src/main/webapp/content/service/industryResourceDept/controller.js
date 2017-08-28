var params={};
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

	$('#btn-view-add').on('click', function() {
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext)
		}else{
			var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
			console.log(r);
			showApplyDia( r.status, r.departmentName, r.departmentId,memberCode);
		}
	});
	$('#btn-view-edit').on('click', function() {
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext)
		}else{
			var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
			editApplyDia(r.departmentName, r.departmentId,memberCode);
			//parent.addPanel(r.departmentName,contextPath+'/dynamic/service/memberBaseInfo/index.jsp?deptId='+r.departmentId,true);
		}
	});
	$('#btn-view-add-depart').on('click', function() {
		addApplyDia();
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
	$('#fm1').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			console.log(params);
			applyMember(params);
			return false;
		}
	});
});

function lookMemberInfo(id, status, departmentName, memberCode,type) {
	parent.addPanel(departmentName, contextPath
			+ '/dynamic/service/memberCenter/index.jsp?deptId=' + memberCode +'&type='+type,
			true);
}

var dialog = {};
function showApplyDia( status, departmentName, deptId,memberCode) {
	$("#fm1").form('clear');
	params.memberCode=deptId;
	params.memberRemark=memberCode;
	if (status != '0') {
		alert(departmentName+"已经是协会会员了。");
		return;
	}
	dialog = $("#dialog-message")
			.removeClass('hide')
			.dialog(
					{
						modal : true,
						width : 900,
						title : "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"
								+ departmentName + "</div></div>",
						title_html : true,
						buttons : [{
									html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
									"class" : "btn btn-info btn-xs",
									id : 'btn-apply',
									click : function() {
										$("#fm1").submit();
									}
								},
								{
									html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
									"class" : "btn btn-xs",
									click : function() {
										$(this).dialog("close");
									}
								}]
					});
	loadMemberInfo(deptId,memberCode);
	loadQualifications(deptId, memberCode);
	
}

function  beforeSubmit(){
	return false;
}

function applyMember(params) {
	console.log(params);
	$.ajax({
		type : "post",
		url : contextPath + "/memberInfo/insertMemberInfoByAdmin.do",
		data : {
			jsons : JSON.stringify(params)
		},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-apply').attr('disabled', "true");
		},
		success : function(rst, textStatus) {
			$('#btn-apply').removeAttr("disabled");
			bootbox.dialog({
				title : '系统提示',
				message : rst.errorMessage,
				detail : rst.detail,
				buttons : {
					"cancel" : {
						"label" : "<i class='ace-icon fa fa-check'></i>确定",
						"className" : "btn-sm btn-success",
						"callback" : function() {
							if(rst.status!=1){
								$(dialog).dialog("close");
							}
							//parent.reloadGrid();
						}
					}
				}
			});
			jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger(
					"reloadGrid");
		},
		complete : function(XMLHttpRequest, textStatus) {
			$('#btn-apply').removeAttr("disabled");
		},
		error : function() {
			$('#btn-apply').removeAttr("disabled");
		}
	});
}

var dialogEdit = {};
function editApplyDia(departmentName, departmentId,memberCode){
	dialogEdit = $("#dialog-edit")
		.removeClass('hide')
		.dialog(
			{
				modal : true,
				width : 900,
				title : "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"
						+ departmentName + "</div></div>",
				title_html : true,
				buttons : [
						{
							html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
							"class" : "btn btn-info btn-xs",
							id : 'btn-apply',
							click : function() {
								//$("#fm2").submit();
								updateDepartInfo_save('#fm2');
							}
						},{
							html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
							"class" : "btn btn-xs",
							click : function() {
								$(this).dialog("close");
							}
						} ]
			});
	$('#departmentId').val(departmentId);
	loadEditMemberInfo(departmentId,memberCode);
	loadEditQualifications(memberCode, departmentId,'4');
}


function addApplyDia(){
	dialogAdd = $("#dialog-add").removeClass('hide')
		.dialog({
			modal : true,
			width : 800,
			title : "<div class='widget-header widget-header-small'><div class='widget-header-pd' >添加企业</div></div>",
			title_html : true,
			buttons : [
					{
						html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
						"class" : "btn btn-info btn-xs",
						id : 'btn-apply',
						click : function() {
							updateDepartInfo_save('#fm3',1);
						}
					},
					{
						html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
						"class" : "btn btn-xs",
						click : function() {
							$(this).dialog("close");
						}
					} ]
		});

	appendDc('80','add_legalPersonIdType','legalPersonIdType');
	appendCheck('81','add_transBussScope','transBussScope');
	appendCheck('94','add_businessClassify','businessClassify');

}









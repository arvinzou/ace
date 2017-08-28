jQuery(function($) {
	$('#btn-view-del').on(
			'click',
			function() {
				
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
				if(r.status!=9&&r.status!=2){
					if(confirm("确定冲正吗？")){
						deleteMemberPayInfoByMemberPayInfoId(r.id);
					}
				}else{
					alert("已冲正的数据和驳回的数据不可冲正！");
				}
			});
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

	
});
function deleteMemberPayInfoByMemberPayInfoId(id){
	$.ajax({
		type : "post",
		url : contextPath + "/memberPayInfo/deleteMemberPayInfoByMemberPayInfoId.do",
		data:{jsons:JSON.stringify({id:id})},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-view-del').attr('disabled',"true");
		},
		success : function(rst, textStatus) {
			$('#btn-view-del').removeAttr("disabled");
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
							 
						}
					}
				}
			});
			jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			$('#btn-view-del').removeAttr("disabled");
		}
	});
}

function lookMemberInfo(id,status,departmentName,memberCode){
	parent.addPanel(departmentName,contextPath+'/dynamic/service/memberCenter/index.jsp?deptId='+memberCode,true);
}
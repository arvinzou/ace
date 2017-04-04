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


	$('#btn-view-startProcess').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext)
				}
				var rowData=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
				console.log(rowData);
				startProcess(rowData.id,rowData.name);
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
});
function clearQparams() {
	$('#cc1').combotree('setValue', '');
}

function startProcess(processDefinitionId,title){
	console.log(processDefinitionId);
	$.ajax({
		type : "post",
		url : contextPath + "/workflow/getStartFormKey.do",
		data:{processDefinitionId:processDefinitionId},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-view-startProcess').attr('disabled',"true");
		},
		success : function(rst, textStatus) {
			$('#btn-view-startProcess').removeAttr("disabled");
			if(!rst.state){
				bootbox.dialog({
					title:'系统提示',
					message:rst.errorMessage,
					detail:rst.detail,
					buttons: 			
					{
						"success" :
						 {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback": function() {
								
							}
						}
					}
				});
			}else{
				var formId=rst.value;
				if(formId=="fm1"){
					formId="747b9548-b1f7-41d0-a728-457eff2f0737";
				}
				parent.addPanel(title, contextPath
						+ '/dynamic/service/workflow/form-view-start.jsp?formId='
						+ formId+"&processDefinitionId="+processDefinitionId +"&title="+title, true);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			$('#btn-view-startProcess').removeAttr("disabled");
		},
		error : function() {
			$('#btn-view-startProcess').removeAttr("disabled");
		}
	});
}

function viewHistory(id,title){
	parent.addPanel(title, contextPath
			+ '/dynamic/service/workflowHi/index.jsp?processDefinitionId='
			+ id+"&title="+title, true);
}

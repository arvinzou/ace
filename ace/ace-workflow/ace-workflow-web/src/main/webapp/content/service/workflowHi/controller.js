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
	if(processDefinitionId){
		selectModelByAppId(processDefinitionId);
	}
	$( "#btn-view-list" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		preview(r.id);
	});
	$( "#btn-view-list2" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		parent.addPanel('流程'+gd.id, contextPath
				+ '/dynamic/service/workflow/workflow-img.jsp?urlid='
				+ urlid + '&id=' + gd.id, true);
	});
});

function preview(instId){
	var dialog = $( "#dialog-message-preview").removeClass('hide').dialog({
		modal: true,
		width:600,
		title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >表单查看</div></div>",
		title_html: true,
		buttons: [ 
			{
				html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
				"class" : "btn btn-info btn-xs",
				id:"btn-view-act-form-submit",
				click: function() {
					dialog.dialog( "close" ); 
				} 
			},
			{
				html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
				"class" : "btn btn-xs",
				click: function() {
					dialog.dialog( "close" ); 
				} 
			}
		]
	});
	
	selectListByInstId(instId);
}
function selectModelByAppId(processDefinitionId) {
	$.ajax({
		type : "get",
		url : contextPath + "/actForm/selectModelByAppId.do",
		data : {appId:processDefinitionId},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst && rst.state) {
				_colNames.push("编号");
				_colModel.push({
					name : 'id',
					width : 80
				});
				_colNames.push("单位");
				_colModel.push({
					name : 'deptName',
					width : 200
				});
				$(rst.value).each(function(i,o){
					_colNames.push(o.label);
					var model={
							name : o.fieldId,
							width : 150
					};
					if(o.required!='on'){
						model['hidden']=true;
					}
					if(o.dictId){
						model['renderer']=function(value) {
							return rsd(value,o.dictId);
						}
					}
					_colModel.push(model);
				});
				_colNames.push("状态");
				_colModel.push({
					name : 'status',
					width : 60,
					renderer:function(value) {
						return value=="1"?"结束":"进行中";
					}
				});
				_colNames.push("开始时间");
				_colModel.push({
					name : 'startDate',
					width : 120
				});
				_colNames.push("完成时间");
				_colModel.push({
					name : 'endDate',
					width : 120
				});
				_colNames.push("操作");
				_colModel.push({
					name : 'opt',
					width : 60,
					renderer : function(value, cur) {
						return renderBtn(cur);
					}
				});
				buidGrid();
				
			} else {
				alert(rst.detail);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {
		}
	});
}
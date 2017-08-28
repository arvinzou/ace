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

	$('#btn-view-add').on(
			'click',
			function() {
				updatePayCfg("");
				/*jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						'new',
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : false,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})*/
			});
	$('#btn-view-edit').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext)
				}
				updatePayCfg(gr);
				/*jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						gr,
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})*/
			});
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
				jQuery(cfg.grid_selector).jqGrid(
						'delGridRow',
						gr,
						{
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
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


function updatePayCfg(gr){
	$('#memberLevel_add').remove();
	$('#updatePayCfg').form('clear');
	var name="添加收费项目";
	if(gr){
		name = "修改收费项目";
		url = cfg.grid_edit_data_url;
	}else{
		url = cfg.grid_add_data_url;
	}
	var dialog = $("#dialog-update-message").removeClass('hide')
		.dialog({
			modal : true,
			width : 500,
			title : "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"+name+"</div></div>",
			title_html : true,
			buttons : [
				{
					html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id : 'btn-pay',
					click : function() {
						var form = $('#updatePayCfg');
						if (!form.form('validate'))
						{
							alert("请将信息补充完整！");
							return;
						}
						
						var params = form.serializeObject();
						$.ajax({
							type : "post",
							url : url,
							data :  {
								jsons : JSON.stringify(params)
							},
							beforeSend : function(XMLHttpRequest) {
							},
							success : function(rst, textStatus) {
								if (rst) {
									bootbox.dialog({
										title : '系统提示',
										message : rst.errorMessage,
										detail : rst.detail,
										buttons : {
											"cancel" : {
												"label" : "<i class='ace-icon fa fa-check'></i>确定",
												"className" : "btn-sm btn-success",
												"callback" : function() {
													if(rst.status=='0'){
														$(dialog).dialog("close");
														jQuery(cfg.grid_selector).jqGrid('setGridParam', {
															page : 1
														}).trigger("reloadGrid");
													}
												}
											}
										}
									});
								}
							},
							complete : function(XMLHttpRequest, textStatus) {},
							error : function() {
								alert("修改有误！");
							}
						});
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
	var chargingItemId = "";
	if(gr){
		$('#update_id').val(jQuery(cfg.grid_selector).jqGrid('getCell',gr, 'id'));
		var r = jQuery(cfg.grid_selector).jqGrid('getCell',gr, 'payNum');
		$('#update_payNum').numberbox('setValue', r);
		chargingItemId = jQuery(cfg.grid_selector).jqGrid('getCell',gr, 'chargingItemId');
	}
	$('#update_chargingItemId').combobox({
		url : contextPath  +'/chargingItem/selectListByDeptId.do',
        method:'post',
        valueField:'code',
        textField:'name',
        editable:false,
        panelHeight:200,
        required:true,
        onLoadSuccess:function(){
        	if(chargingItemId){
        		$("#update_chargingItemId").combobox('setValue', chargingItemId);
        		loading(chargingItemId, gr);
        	}
        },
        onSelect:function(record){
        	loading(record.code);
        }
	});
}

function loading(chargingItemId, gr){
	var html = new Array();
	$.ajax({
		type : "post",
		url : contextPath + "/chargingItem/selectChargingItemByPrimaryKey.do",
		data :  {
			id : chargingItemId
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst.value) {
				var memberlevel = rst.value.whetherMemberlevel;
				var period = rst.value.whetherPeriod;
				if(memberlevel=="1"&&memberlevel){
					html = '<div id="memberLevel_add" class="profile-info-row">';
					html = html + '<div class="profile-info-name">会员等级</div>';
					html = html + '<div class="profile-info-value">';
					html = html + '<input style="width: 200px;height:25px;" id="update_memberLevelId" name="memberLevelId">';
					html = html + '</div></div>';
				}
				$('#update_ChargingItem').parent().append(html);
				if(memberlevel!=null&&memberlevel=="1"){
					$('#update_memberLevelId').combobox({
						url : contextPath +'/memberLevel/selectListByDeptId.do?selected=true',
				        method:'post',
				        valueField:'code',
				        textField:'name',
				        editable:false,
				        panelHeight:200,
				        required:true,
				        onLoadSuccess:function(){
				        	if(gr){
				        		var memberlevel = jQuery(cfg.grid_selector).jqGrid('getCell',gr, 'memberLevelId');
				        		if(memberlevel){
					        		$("#update_memberLevelId").combobox('setValue', memberlevel);
				        		}
				        	}
				        }
					});
				}
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
		}
	});
}



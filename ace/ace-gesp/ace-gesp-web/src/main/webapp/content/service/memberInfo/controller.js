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
	$('#btn-view-edit').on('click',function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext)
				}else{
					var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
					editShowDia(r);
				}
				
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
	$('#btn-view-audit').on('click', function() {
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext)
		}
	});
	
	//会员退会
	$('#btn-view-update').on('click', function() {
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext);
		}else{
			var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
			var status = r.status;
			if(status==8){
				$.jgrid.info_dialog('注意', r.departmentName+'已退会了');
			}else{
				if(confirm("确定要退会吗？")){
					editMemberInfo(r.id);
				}
			}
		}
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
function lookMemberInfo(id,status,departmentName,memberCode){
	parent.addPanel(departmentName,contextPath+'/dynamic/service/memberCenter/index.jsp?deptId='+memberCode,true);
}

function editShowDia(gr){
	var editDialog = $("#dialog-message").removeClass('hide')
		.dialog({
			modal: true,
			width:800,
			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >变更会员信息</div></div>",
			title_html: true,
			buttons: [ 
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'btn-audits',
					click: function() {
						var form = $("#fm-dialog");
						if (!form.form('validate')) {
							alert("请将信息补充完整！");
							return;
						}
						var params = form.serializeObject();
						$.ajax({
							type : "post",
							url : cfg.grid_edit_data_url,
							data:{jsons:JSON.stringify(params)},
							beforeSend : function(XMLHttpRequest) {
								$('#btn-audits').attr('disabled',"true");
							},
							success : function(rst, textStatus) {
								$('#btn-audits').removeAttr("disabled");
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
								if(rst.status=="0"){
									$("#dialog-message").dialog( "close" ); 
									jQuery(cfg.grid_selector).jqGrid('setGridParam', {
									}).trigger("reloadGrid");
								}
							},complete : function(XMLHttpRequest, textStatus) {
								$('#btn-audits').removeAttr("disabled");
							},
							error : function() {
								$('#btn-audits').removeAttr("disabled");
							}
						});
					} 
				},
				{
					html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
					"class" : "btn btn-xs",
					click: function() {
						$(this).dialog("close"); 
					} 
				}
			]
	});
	$('#edit-id').val(gr.id);
	$('#edit-departmentName').val(gr.departmentName);
	$('#edit-joinDate').datebox('setValue', gr.joinDate);
	$('#edit-memberNo').val(gr.memberNo);
	$('#edit-endDate').datebox('setValue', gr.endDate);
	$('#edit-memberLevel').combobox({
		url : contextPath +'/memberLevel/selectListByDeptId.do',
        method:'post',
        valueField:'code',
        textField:'name',
        editable:false,
        panelHeight:200,
        required:true,
        onLoadSuccess:function(){
        	$('#edit-memberLevel').combobox('setValue',gr.memberLevel);
        }
	});
}

function editMemberInfo(id){
	var params = {
		id : id
	};
	bootbox.dialog({
		title : '系统提示',
		message : '确定要退会吗？',
		buttons : {
			"success" : {
				"label" : "<i class='ace-icon fa fa-check'></i>确定",
				"className" : "btn-sm btn-success",
				"callback" : function() {
					$.ajax({
						type : "post",
						url : contextPath + "/memberInfo/updateState.do",
						data : {
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
												jQuery(cfg.grid_selector).jqGrid('setGridParam', {
													page : 1
												}).trigger("reloadGrid");
											}
										}
									}
								});
							}
						},
						complete : function(XMLHttpRequest, textStatus) {
						},
						error : function() {
						}
					});
				}
			},
			"cancel" : {
				"label" : "<i class='ace-icon fa fa-check'></i>取消",
				"className" : "btn-sm btn-cancel",
				"callback" : function() {
					
				}
			}
		}
	});
}
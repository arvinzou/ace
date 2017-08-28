var params={};
jQuery(function($) {
	$("#index_userLevel").combobox({
		url : portalPath +'/dict/findListByCategoryId.do?categoryId=05&selected=true',
		method:'get',
        valueField:'code',
        editable :false,
        textField:'name',
        panelHeight:'200',
        required:true
	});
	
	/**/
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
		addShowDia();
	});
	$('#btn-view-edit').on('click', function() {
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext);
		}else{
			var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
			/*var stat=jQuery(cfg.grid_selector).jqGrid('getCell',gr, 'userLevel');
			if(stat=="2"){
				alert("不可以修改注册联系人");
				return;
			}*/
			editShowDia(r.name, r.userId);
		}
	});
	$('#btn-view-del').on('click', function() {
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext)
		}else{
			var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
			var stat=jQuery(cfg.grid_selector).jqGrid('getCell',gr, 'userLevel');
			if(stat=="2"){
				alert("不可以删除注册联系人");
				return;
			}
			var ids=jQuery(cfg.grid_selector).jqGrid('getCell',gr, 'userId');
			$.ajax({
				type : "post",
				url : cfg.grid_delete_data_url,
				data:{id:ids},
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {
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
						jQuery(cfg.grid_selector).jqGrid('setGridParam', {
						}).trigger("reloadGrid");
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
				},
				error : function() {
				}
			});
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

	$("#edit_userLevel").combobox({
		//url : portalPath +'/dict/findListByCategoryId.do?categoryId=05&selected=false',
		method:'get',
        valueField:'code',
        editable :false,
        textField:'name',
        panelHeight:'200',
        required:true
	});
	$("#add_userLevel").combobox({
		//url : portalPath +'/dict/findListByCategoryId.do?categoryId=05&selected=false',
		method:'get',
        valueField:'code',
        editable :false,
        textField:'name',
        panelHeight:'200',
        required:true
	});
	$("#add_departmentId").combobox({
		url : contextPath +'/industryResource/findAllDeptList.do',
		method:'post',
        editable :true,
        valueField:'code',
        textField:'name',
        panelHeight:'200',
        required:true,
        filter: function(q, row){
    		var opts = $(this).combobox('options');
    		return row[opts.textField].indexOf(q)>-1;;
    	}
	});
	$("#edit_departmentId").combobox({
		url : contextPath +'/industryResource/findAllDeptList.do',
		method:'post',
        editable :true,
        valueField:'code',
        textField:'name',
        panelHeight:'200',
        required:true,
        filter: function(q, row){
    		var opts = $(this).combobox('options');
    		return row[opts.textField].indexOf(q)>-1;;
    	}

	});
	loadCategory();
});


function addShowDia(){
	var addDialog = $("#dialog-messadd").removeClass('hide')
		.dialog({
			modal: true,
			width:800,
			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >添加联系人</div></div>",
			title_html: true,
			buttons: [ 
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					"id" : "btn-audit",
					click: function() {
						var form = $("#addContactInfo");
						if (!form.form('validate')) {
							alert("请将信息补充完整！");
							return;
						}
						var params = form.serializeObject();
						$.ajax({
							type : "post",
							url : cfg.grid_add_data_url,
							data:{jsons:JSON.stringify(params),flag:"2"},
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
												
											}
										}
									}
								});
								if(rst.status=="0"){
									$(addDialog).dialog( "close" ); 
									jQuery(cfg.grid_selector).jqGrid('setGridParam', {
									}).trigger("reloadGrid");
								}
							},
							complete : function(XMLHttpRequest, textStatus) {
								$('#btn-audit').removeAttr("disabled");
							},
							error : function() {
								$('#btn-audit').removeAttr("disabled");
							}
						});
					} 
				},
				{
					html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
					"class" : "btn btn-xs",
					click: function() {
						$(this).dialog( "close" ); 
					} 
				}
			]
	});
}


function editShowDia(name,user){
	var editDialog = $("#dialog-messeditCon").removeClass('hide')
		.dialog({
			modal: true,
			width:800,
			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"+name+"</div></div>",
			title_html: true,
			buttons: [ 
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'btn-audits',
					click: function() {

						var form = $("#updateContactInfo");
						if (!form.form('validate')) {
							alert("请将信息补充完整！");
							return;
						}
						var params = form.serializeObject();
						$.ajax({
							type : "post",
							url : cfg.grid_edit_data_url,
							data:{jsons:JSON.stringify(params),flag:"2"},
							beforeSend : function(XMLHttpRequest) {
								$('#btn-audits').attr('disabled',"true");
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
												
											}
										}
									}
								});
								if(rst.status=="0"){
									$("#dialog-messeditCon").dialog( "close" ); 
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
	
	$.ajax({
		type : "post",
		url : portalPath + '/users/selectUsersByPrimaryKey.do',
		data:{userId:user},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var r = rst.value;
			$('#edit_userId').val(r.userId);
			$('#edit_departmentId').combobox('setValue', r.departmentId);
			$('#edit_name').val(r.name);
			$('#edit_userLevel').combobox('setValue', r.userLevel);
			$('#edit_mobile').val(r.mobile);
			$('#edit_email').val(r.email);
			$('#edit_telphone').val(r.telphone);
			$('#edit_qq').val(r.qq);
			$('#edit_fax').val(r.fax);
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
		}
	});
}

function loadCategory(){
	$.ajax({
		type : "post",
		url:portalPath+'/dict/findListByCategoryId.do?categoryId=05',
		data:{},
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(rst, textStatus) {
			var name = [];
			var a =0;
			if (rst) {
				for (var i = 0; i < rst.length; i++) {
					if (rst[i].code!="2") {
						name[a] = rst[i];
						a++;
					}
				}
			}
			$('#add_userLevel').combobox('loadData',name);
			$('#edit_userLevel').combobox('loadData',name);
		},
		complete : function(XMLHttpRequest, textStatus) {
			
		},
		error : function() {
			
		}
	});
}

var params={};
jQuery(function($) {
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
			editShowDia(r.name, r.userId);
		}
	});
	$('#btn-view-init').on('click', function() {
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext)
		}else{
			var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
			initShowDia(r.userId, r.name);
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
		url : portalPath +'/dict/findListByCategoryId.do?categoryId=05',
		method:'get',
        valueField:'code',
        editable :false,
        textField:'name',
        panelHeight:'200',
        required:true
	});
	$("#add_userLevel").combobox({
		url : portalPath +'/dict/findListByCategoryId.do?categoryId=05',
		method:'get',
        valueField:'code',
        editable :false,
        textField:'name',
        panelHeight:'200',
        required:true
	});
	
	$("#add_name").combobox({
		url : portalPath +'/users/selectAllUsersByDeptId.do?status=0',
		method:'post',
        editable :true,
        valueField:'code',
        textField:'name',
        panelHeight:'200',
        required:true,
        queryParams : {
            payee : $('#add_name').val()
        },
        filter: function(q, row){
    		var opts = $(this).combobox('options');
    		return row[opts.textField].indexOf(q)>-1;;
    	},
    	onSelect: function(record){
    		loadUsers(record.code, 'add');
    	} 
	});
	/*$("#edit_name").combobox({
		url : portalPath +'/users/selectAllUsersByDeptId.do?status=0',
		method:'post',
        editable :true,
        valueField:'code',
        textField:'name',
        panelHeight:'200',
        required:true,
        filter: function(q, row){
    		var opts = $(this).combobox('options');
    		return row[opts.textField].indexOf(q)>-1;;
    	},
    	onSelect: function(record){
    		loadUsers(record.code, 'edit');
    	} 
	});*/
	
	$("#edit_sex").combobox({
		url : portalPath +'/dict/findListByCategoryId.do?categoryId=01',
		method:'post',
        valueField:'code',
        editable :false,
        textField:'name',
        panelHeight:'200',
        required:true
	});
	$("#add_sex").combobox({
		url : portalPath +'/dict/findListByCategoryId.do?categoryId=01',
		method:'post',
        valueField:'code',
        editable :false,
        textField:'name',
        panelHeight:'200',
        required:true
	});
	
	//loadCategory();
});


function addShowDia(){
	$('#addContactInfo').form('clear');
	var addDialog = $("#dialog-messadd").removeClass('hide')
		.dialog({
			modal: true,
			width:800,
			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >添加用户</div></div>",
			title_html: true,
			buttons: [ 
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					"id" : "btn-audit",
					click: function() {
						var form = $("#addContactInfo");
						if (!form.form('validate')) {
							alert("请将信息补充完整!");
							return;
						}
						var name = $('#add_name').combobox('getValue');
						var params = form.serializeObject();
						if(name==undefined){
							var addName = $('#add_name').combobox('getText');
							params['name']= addName;
						}
						params['corpName'] = "添加";
						$.ajax({
							type : "post",
							url : cfg.grid_add_data_url,
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
	$('#updateContactInfo').form('clear');
	var editDialog = $("#dialog-messeditCon").removeClass('hide')
		.dialog({
			modal: true,
			width:800,
			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >变更用户信息</div></div>",
			title_html: true,
			buttons: [ 
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'btn-audits',
					click: function() {
						var form = $("#updateContactInfo");
						if (!form.form('validate')) {
							alert("请将信息补充完整!");
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
	
	loadUsers(user, 'edit');
}

function initShowDia(id, name){
	$('#initPassword').form('clear');
	var initDialog = $("#dialog-messInit").removeClass('hide')
		.dialog({
			modal: true,
			width:400,
			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >初始化用户信息</div></div>",
			title_html: true,
			buttons: [ 
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'btn-audits',
					click: function() {
						var pass = $('#init_password').val();
						var repass = $('#init_repassword').val();
						var id = $('#init_userId').val();
						if(pass==undefined||pass==""){
							alert("初始密码不能为空!");
							return ;
						}
						if(repass==undefined||repass==""){
							alert("确认密码不能为空!");
							return ;
						}
						if(repass!=pass){
							alert("两次输入的密码不一致,请重新输入!");
							return ;
						}
						$.ajax({
							type : "post",
							url : portalPath  + "/users/updateUsersForInitPassword.do",
							data:{userId:id,password:pass},
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
									$("#dialog-messInit").dialog( "close" ); 
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
	$('#init_name').html(name);
	$('#init_userId').val(id);
}

function loadUsers(user, edit){
	$.ajax({
		type : "post",
		url : portalPath + '/users/selectUsersByPrimaryKey.do',
		data:{userId:user},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var r = rst.value;
			$('#'+edit+'_userId').val(r.userId);
			$('#'+edit+'_departmentId').val(r.departmentId);
			$('#'+edit+'_account').val(r.account);
			var name = r.name;
			if(edit=='edit'){
				$('#edit_name').val(name);
			}else{
				$('#add_name').combobox('setValue', name);
			}
			var sex = r.sex;
			if(sex!=null&&sex!=undefined){
				$('#'+edit+'_sex').combobox('setValue', r.sex);
			}
			var userLevel = r.userLevel;
			$('#'+edit+'_userLevel').combobox('setValue', userLevel);
			$('#'+edit+'_mobile').numberbox('setValue', r.mobile);
			$('#'+edit+'_email').val(r.email);
			$('#'+edit+'_telphone').val(r.telphone);
			$('#'+edit+'_qq').numberbox('setValue', r.qq);
			$('#'+edit+'_fax').val(r.fax);
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

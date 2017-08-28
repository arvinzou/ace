var contactInfo = new Array();
jQuery(function($) {
	$("#editDe_userLevel").combobox({
		method:'get',
        valueField:'code',
        editable :false,
        textField:'name',
        panelHeight:'200',
        required:true
	});
	$("#addDe_userLevel").combobox({
		method:'get',
        valueField:'code',
        editable :false,
        textField:'name',
        panelHeight:'200',
        required:true
	});

	loadCategory('addDe', 'editDe');
	
	$('#btn-view-addContact').on('click', function() {
		addAddDepartDiaContact();
	});
});

/*添加企业*/
function addMemberInfo(params){
	$.ajax({
		type : "post",
		url : contextPath + "/www/reg/insert.do",
		data : {
			jsons : JSON.stringify(params) /*,jsonTwo : JSON.stringify(contactInfo)*/
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
									$("#dialog-add").dialog("close"); 
								}
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


/*加载联系人信息*/
var loadContact ="";
function loadDepartContact(){
	if ($('#addTableContact').hasClass('dataTable')) {
		dttable = $('#addTableContact').dataTable();
		dttable.fnClearTable(); // 清空一下table
		dttable.fnDestroy(); // 还原初始化了的datatable
	}
	var html = new Array();
	for(var i =0 ; i<contactInfo.length;i++){
		var tel = contactInfo[i].telphone;
		if(tel==undefined){
			tel="";
		}
		var email = contactInfo[i].email;
		if(email==undefined){
			email="";
		}
		var qq = contactInfo[i].qq;
		if(qq==undefined){
			qq="";
		}
		html.push('<tr>');
		html.push('<td width="70px">'+ contactInfo[i].name + '</td>');
		html.push('<td width="70px">' + rsd(contactInfo[i].userLevel, "05") + '</td>');
		html.push('<td width="70px">' + contactInfo[i].mobile + '</td>');
		html.push('<td width="50px">' + email + '</td>');
		html.push('<td width="50px">' + tel + '</td>');
		html.push('<td width="50px">' + qq + '</td>');
		html.push('<td width="50px">');
		if(o.userLevel!=2){
			html.push('  <a href="javascript:updateDeContact('+i+');" target="_blank"><span class="badge badge-info">修改</span></a>');
			html.push('  <a href="javascript:deleteDeContact('+i+');" target="_blank" style="float:right;margin-right:10px;"><span class="badge badge-info">删除</span></a>');
		}
		html.push('</td></tr>');
	}
	$('#addTableContact').find('tbody').empty();
	$('#addTableContact').find('tbody').append(html.join(''));

}

/*修改联系人的信息*/
function updateDeContact(id){
	var updatesDialog = $('#dialog-messageEditDeContact').removeClass('hide')
	.dialog({
		modal : true,
		width : 900,
		title : "<div class='widget-header widget-header-small'><div class='widget-header-pd' >修改联系人的信息</div></div>",
		title_html : true,
		buttons : [
			{
				html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
				"class" : "btn btn-info btn-xs",
				id : 'btn-pay',
				click : function() {
					var form = $("#editDeMessContact");
					if (!form.form('validate'))
					{
						alert("请将信息补充完整！");
						return;
					}
					var userLevel = $('#addDe_userLevel').combobox('getValue');
					if(!userLevel){
						alert("请选择职务！");
						return;
					}
					var params = form.serializeObject();
					contactInfo[id].name = params.name;
					contactInfo[id].qq = params.qq;
					contactInfo[id].userLevel = params.userLevel;
					contactInfo[id].mobile = params.mobile;
					contactInfo[id].telphone = params.telphone;
					contactInfo[id].fax = params.fax;
					contactInfo[id].email = params.email;
					
					loadDepartContact();
					$(updatesDialog).dialog("close");
				}
			},{
				html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
				"class" : "btn btn-xs",
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
	$('#editDe_name').val(contactInfo[id].name);
	$('#editDe_qq').val(contactInfo[id].qq);
	$('#editDe_userLevel').combobox('setValue' ,contactInfo[id].userLevel);
	$('#editDe_mobile').val(contactInfo[id].mobile);
	$('#editDe_telphone').val(contactInfo[id].telphone);
	$('#editDe_fax').val(contactInfo[id].fax);
	$('#editDe_email').val(contactInfo[id].email);
}

function deleteDeContact(id){
	contactInfo.splice(id,1);
	loadDepartContact();
}

/*添加联系人信息*/
function addAddDepartDiaContact(){
	var addDeDialog = $("#dialog-messageAddDeContact").removeClass('hide')
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
						
						var form = $("#addDeContactInfo");
						if (!form.form('validate')) {
							alert("请将信息补充完整！");
							return;
						}
						var userLevel = $('#addDe_userLevel').combobox('getValue');
						if(!userLevel){
							alert("请选择职务！");
							return;
						}
						var params = form.serializeObject();
						var len = contactInfo.length;
						contactInfo[len] = params;
						$("#dialog-messageAddDeContact").dialog( "close" ); 
						loadDepartContact();
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











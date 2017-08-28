jQuery(function($) {
	/*$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));

	var depatId = $('#departmentId').val();
	loadEditMemberInfo(depatId,memberCode);
	loadEditQualifications(memberCode);*/
	/*$('#fm-reg1').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			console.log(params);
			updateMemberInfo();

			return false;
		}
	});
	$('#fm-reg2').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			console.log(params);
			updateMemberInfo();
			return false;
		}
	});
	$('#fm-reg3').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			console.log(params);
			updateMemberInfo();
			return false;
		}
	});
	$('#fm-reg4').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			console.log(params);
			updateMemberInfo();
			return false;
		}
	});
	$("#btn-reg1").on('click', function(e) {
		e.preventDefault();
		$('#fm-reg1').submit();
	});
	$("#btn-reg2").on('click', function(e) {
		e.preventDefault();
		$('#fm-reg2').submit();
	});
	$("#btn-reg3").on('click', function(e) {
		e.preventDefault();
		$('#fm-reg3').submit();
	});
	$("#btn-reg4").on('click', function(e) {
		e.preventDefault();
		$('#fm-reg4').submit();
	});*/
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

	loadCategory('add', 'edit');
	
	$('#btn-view-add-contact').on('click', function() {
		var depatId = $('#departmentId').val();
		addShowDiaContact(depatId);
	});
});
var params = {};
/*function reg1Submit() {

	return false;
}
function reg2Submit() {

	return false;
}
function reg3Submit() {

	return false;
}
function reg4Submit() {

	return false;
}*/
function onLoadSuccess() {

}

function loadEditMemberInfo(depatId,memberCode) {
	appendDc('80','update_legalPersonIdType','legalPersonIdType');
	appendCheck('81','update_transBussScope','transBussScope');
	appendCheck('94','update_businessClassify','businessClassify');
	$.ajax({
		type : "post",
		async:false,
		url : contextPath + "/memberInfo/loadMemberBaseInfo.do",
		data : {
			id : depatId,parentDepartmentId:memberCode
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			$('#edit_memberCode').val(memberCode);
			 $('#edit_deptCode').val(depatId);
			var r = rst.value;
			$('#fm2').form('load', r);
			var idType = r.legalPersonIdType;
			$("#legalPersonIdType"+idType).attr("checked",true);
			var transBussScope = r.transBussScope;
			if(transBussScope!=undefined){
				transBussScope = r.transBussScope.split(",");
				for (var i = 0; i < transBussScope.length; i++) {
					$('#transBussScope'+transBussScope[i]).prop({checked:true});
				}
			}
			var businessClassify = r.businessClassify;
			if(businessClassify!=undefined){
				businessClassify = r.businessClassify.split(",");
				for (var i = 0; i < businessClassify.length; i++) {
					$('#businessClassify'+businessClassify[i]).prop({checked:true});
				}
			}
			if(r.simage){
				var simage = fastdfs_server+r.simage; 
				$('#index_avatar').attr("src", simage); 
			}
			$('#index_level').html('');
			if(r.memberLevel=="行业用户"){
				$('#index_checkLevel').val('1');
				$('#index_level').html('行业用户 ');
				$('#index_btnLevel').html('<a class="btn btn-info" style="margin-left:8px;" href="javascript:index_addDepar();">加入协会</a>');
			}else{
				$('#index_checkLevel').val('2');
				$('#index_level').html(r.memberLevel+',&nbsp;&nbsp;有效期止'+(r.endDate).substring(0,10));
				$('#index_btnLevel').html('&nbsp;&nbsp;<br><a class="btn btn-info" style="margin-left:8px;" >会员升级</a>');
			}
			/*loadMemberContact(depatId);*/
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}


function loadEditQualifications(pdeptId,deptCodes) {
	$.ajax({
		type : "get",
		url : contextPath + "/qualifications/selectQualificationsList.do",
		data : {deptId:deptCodes,pdeptId:pdeptId,flag:""},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst && rst.state) {
				butiEditQualificationsTable(rst.value,deptCodes,pdeptId);
			} else {
				bootbox.dialog({
					title : '系统提示',
					message : rst.errorMessage,
					detail : rst.detail,
					buttons : {
						"cancel" : {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback" : function() {
								
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

/*附件*/
function butiEditQualificationsTable(data,deptCodes,pdeptId) {
	if ($('#table9').hasClass('dataTable')) {
		dttable = $('#table9').dataTable();
		dttable.fnClearTable(); // 清空一下table
		dttable.fnDestroy(); // 还原初始化了的datatable
	}
	var html = new Array();
	var flag = '';
	document.getElementById("update-hiddenSubmit").style.display = "none";
	$(data).each(function(index, o) {
		var is=o.isMust;
		var status = o.status;
		if(is=='1'){
			is='(<span style="color:red;">*</span>)';
			if(status!='4'){
				var s = $('#update-quali').val();
				if(s!='2'){
					$('#update-quali').val(status);
				}
			}
		}else{
			is='';
		}
		var remark = o.remark;
		if(remark==undefined){
			remark = "";
		}
		var path = '';
		if(o.path){
			path = '<a href="' +fastdfs_server+ o.path + '" target="_blank"><span class="badge badge-info">查看</span></a>';
		}
		html.push('<tr>');
		html.push('<td width="50px" class="center"><span class="badge badge-success">'+ (index + 1) + '</span></td>');
		html.push('<td width="150px">' + o.name+is + '</td>');
		html.push('<td width="150px">' + remark + '</td>');
		html.push('<td width="50px">' + rsd(status, "97") + '</td>');
		html.push('<td width="50px">'+path+'</td>');
		html.push('<td width="300px">');
		html.push('<span id="filelist' + o.code + '"></span>');
		html.push('<span id="container' + o.code + '">');
		if(o.fileSrc){
			html.push(' <a href="' +fastdfs_server+ o.fileSrc + '" target="_blank"><span class="badge badge-info">查看</span></a>');
			if(o.status==1||o.status==3){
				var file="'"+ o.fileSrc+"'";
				var id="'"+o.id+"'";
				var deptId="'"+ deptCodes+"'";
				var pdeptIds="'"+pdeptId+"'";
				html.push('<a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteFile('+file+','+id+','+deptId+','+pdeptIds+');" ></a> ');
			}
		}
		if(!o.fileSrc||status=='3'||status=='1'){
			html.push('<a id="pickfiles' + o.code + '" href="javascript:;">[选择附件]</a> <a ');
			html.push('id="uploadfiles' + o.code + '" href="javascript:;">[上传]</a>');
			html.push('</span><span id="fileTarget' + o.code + '"></span>');
			document.getElementById("update-hiddenSubmit").style.display = "";
		}
		html.push('</td></tr>');
	});
	flag = $('#update-quali').val();
	if(flag!="4"){
		$('#step2').attr('class', 'active');
	}else{
		$('#step2').attr('class', 'active');
		$('#step3').attr('class', 'active');
		$('#table9_content').html("已完成企业认证");
		$('#index_qualifications').html("已认证");
		document.getElementById("update-hiddenSubmit").style.display = "none";
	}
	
	$('#table9').find('tbody').empty();
	$('#table9').find('tbody').append(html.join(''));
	$(data).each(function(index, o) {
		var qid = o.id;
		if(o.code==o.id){
			qid="";
		}
		buidUploader(upload[index],'fileTarget' + o.code,insertEditQualifications,o.code,'filelist'+ o.code,'pickfiles'+ o.code,'container'+ o.code,'console2','uploadfiles'+ o.code,'1',qid)
	});
}

/*删除文件*/
function deleteFile(fileSrc, id, deptId, pdeptId){
	$.ajax({
		type : "post",
		url : contextPath + "/qualifications/deleteByFile.do",
		data : {
			filePath : fileSrc, id : id
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
								
							}
						}
					}
				});
				if(rst.status==0){
					loadEditQualifications(pdeptId,deptId);
				}
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
		}
	});
}

/*修改会员信息*/
function updateMemberInfo(params,status) {
	$.ajax({
		type : "post",
		url : contextPath + "/memberInfo/updateByPrimaryKeySelective.do",
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
								if(status=='2'){
									dialogEdit.dialog("close");
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

function getPayInfo(memberCode, payLevel) {
	$.ajax({
		type : "post",
		url : contextPath + "/payCfg/getPayInfo.do",
		data : {
			memberCode : memberCode,
			payLevel : payLevel
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			$('#level').html("会员级别：" + rst.value.name);

		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {

		}
	});
}

function insertEditQualifications(targetInput,fileSrc,ids,id){
	var memberCodes =  $('#edit_memberCode').val();
	var deptCodes = $('#edit_deptCode').val();
	var params={};
	params.fileSrc=fileSrc;
	params.category=ids;
	params.id = id;
	params.deptId = deptCodes;
	$.ajax({
		type : "post",
		url : contextPath + "/qualifications/insertQualifications.do",
		data : {
			jsons : JSON.stringify(params)
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst && rst.state) {
				$('#update-quali').val('2');
				loadEditQualifications(memberCodes,deptCodes);
			} else {
				bootbox.dialog({
					title : '系统提示',
					message : rst.errorMessage,
					detail : rst.detail,
					buttons : {
						"cancel" : {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback" : function() {
								
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

/*提交企业认证审核*/
function updateDepartFiles_save(pdeptId,deptCodes) {
	var flag = $('#update-quali').val();
	if(flag!='2'){
		alert("请上传文件您要提交的文件");
		return ;
	}
	$.ajax({
		type : "post",
		url : contextPath + "/qualifications/updateQualifi.do",
		data : {},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst) {
				var state = rst.status;
				if(state=="0"){
					$('#step2').attr('class', 'active');
					//document.getElementById("update-hiddenSubmit").style.display = "none";
					loadEditQualifications(pdeptId,deptCodes);
				}
				bootbox.dialog({
					title : '系统提示',
					message : rst.errorMessage,
					buttons : {
						"cancel" : {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback" : function() {
								
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

function appendDc(num,id,name){
	var trleng = parent.staticDictObject[num];
	$('#'+id).html("");
	for (var i = 0; i <trleng.length; i++) {
		$('#'+id).append('<label><input name="'+name+'" type="radio"\
				class="ace" id="'+name+''+trleng[i].CODE+'" value="'+trleng[i].CODE+'" /><span class="lbl ">'+trleng[i].NAME+'</span></label>');
	}
}

function appendCheck(num,id,name){
	var trleng = parent.staticDictObject[num];
	$('#'+id).html("");
	var a = 1;
	for (var i = 0; i < trleng.length; i++) {
		$('#'+id).append('<label><input name="'+name+'" type="checkbox"\
				class="ace" id="'+name+''+trleng[i].CODE+'" value="'+trleng[i].CODE+'" /><span class="lbl ">'+trleng[i].NAME+'</span></label>');
		if(a%2==0&&a!=0){
			$('#'+id).append('<br>');
		}
		a++;
	}
}

function updateDepartInfo_save(form,flag){
	var form = $(form);
	if (!form.form('validate'))
	{
		alert("请将信息补充完整！");
		return;
	}
	var params = form.serializeObject();
	params['legalPersonIdType']=$('input[name=legalPersonIdType]:checked').val();
	params['transBussScope'] = "";
	$("input[name='transBussScope']:checked").each(function(){ 
	     params['transBussScope'] = params['transBussScope']+$(this).attr('value')+',';
	});
	params['businessClassify'] = "";
	$("input[name='businessClassify']:checked").each(function(){ 
	     params['businessClassify'] = params['businessClassify']+$(this).attr('value')+',';
	});
	if(flag&&flag=='1'){
		addMemberInfo(params);
	}else{
		updateMemberInfo(params,2);
	}
}

function loadCategory(add,edit){
	$.ajax({
		type : "post",
		url : portalPath+'/dict/findListByCategoryId.do?categoryId=05&selected=false',
		data : {},
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
			$('#'+add+'_userLevel').combobox('loadData',name);
			$('#'+edit+'_userLevel').combobox('loadData',name);
		},
		complete : function(XMLHttpRequest, textStatus) {
			
		},
		error : function() {
			
		}
	});
}

/*加载联系人信息*/
/*var loadContact ="";
function loadMemberContact(depatId){
	if ($('#tableContact').hasClass('dataTable')) {
		dttable = $('#tableContact').dataTable();
		dttable.fnClearTable(); // 清空一下table
		dttable.fnDestroy(); // 还原初始化了的datatable
	}
	$.ajax({
		type : "post",
		url : portalPath + "/users/findDeIdByUsersList.do",
		data : {
			departmentId : depatId
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var html = new Array();
			$(rst.rows).each(function(index, o) {
				var tel = o.telphone;
				if(tel==undefined){
					tel="";
				}
				var email = o.email;
				if(email==undefined){
					email="";
				}
				var qq = o.qq;
				if(qq==undefined){
					qq="";
				}
				html.push('<tr>');
				html.push('<td  width="70px">'+ o.name + '</td>');
				html.push('<td width="70px">' + rsd(o.userLevel, "05") + '</td>');
				html.push('<td width="70px">' + o.mobile + '</td>');
				html.push('<td width="50px">' + email + '</td>');
				html.push('<td width="50px">' + tel + '</td>');
				html.push('<td width="50px">' + qq + '</td>');
				html.push('<td width="50px">');
				if(o.userLevel!=2){
					var id = "'"+o.userId+"'";
					html.push('  <a href="javascript:updateContact('+id+');" target="_blank"><span class="badge badge-info">修改</span></a>');
					html.push('  <a href="javascript:deleteContact('+id+');" target="_blank" style="float:right;maring-right:10px;"><span class="badge badge-info">删除</span></a>');
				}
				html.push('</td></tr>');
			});
			$('#tableContact').find('tbody').empty();
			$('#tableContact').find('tbody').append(html.join(''));

		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}*/

/*修改联系人的信息*/
function updateContact(id){
	var updateDialog = $('#dialog-messageEditContact').removeClass('hide')
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
					var form = $("#editMessContact");
					if (!form.form('validate'))
					{
						alert("请将信息补充完整！");
						return;
					}
					var params = form.serializeObject();
					$.ajax({
						type : "post",
						url : portalPath + "/users/updateUsersById.do",
						data : {
							jsons : JSON.stringify(params),flag:"2"
						},
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
								var deptId = $('#edit_departmentId').val();
								loadMemberContact(deptId);
								$('#dialog-messageEditContact').dialog("close");
							}
						},
						complete : function(XMLHttpRequest, textStatus) {
						},
						error : function() {
							alert("加载错误！");
						}
					});
				}
			},{
				html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
				"class" : "btn btn-xs",
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
	
	$.ajax({
		type : "post",
		url : portalPath + '/users/selectUsersByPrimaryKey.do',
		data:{userId:id},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var r = rst.value;
			$('#edit_userId').val(r.userId);
			$('#edit_departmentId').val(r.departmentId);
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

/*删除联系人*/
function deleteContact(id){
	$.ajax({
		type : "post",
		url :portalPath + "/users/deleteConUsers.do",
		data : {
			id : id
		},
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
				var deptId = $('#departmentId').val();
				loadMemberContact(deptId);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}

/*添加联系人信息*/
function addShowDiaContact(depatId){
	var addDialog = $("#dialog-messageAddContact").removeClass('hide')
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
						params['departmentId'] = depatId;
						$.ajax({
							type : "post",
							url : contextPath + "/www/reg/insertUsers.do",
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
								if(rst.status=='0'){
									loadMemberContact(depatId);
									$(addDialog).dialog( "close" ); 
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
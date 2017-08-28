var uploaders={};
var dialogImgs;
jQuery(function($) {
	var flags = $('#index_flagId').val();
	if (flags == '3102') {
		document.getElementById("index_safe").style.display = "";
		document.getElementById("index_updateImg").style.display = "none";
		document.getElementById("index_baseInfo").style.display = "none";
		document.getElementById("btn_updateQua").style.display = "none";
		document.getElementById("index_btnLevel").style.display = "none";
		loadEditMemberInfo(depatId, parDeptId);
		loadEditQualifications(parDeptId, depatId);
	} else if (flags == '3101') {
		document.getElementById("index_updateImg").style.display = "";
		document.getElementById("index_baseInfo").style.display = "";
		document.getElementById("index_safe").style.display = "none";
		document.getElementById("btn_updateQua").style.display = "";
		document.getElementById("index_btnLevel").style.display = "";
		loadEditMemberInfo(depatId, parDeptId);
		loadEditQualifications(parDeptId, depatId);
	}

	buidUploader(uploaders,'file',updateDeImg,'','fileinput-list','btn-fileImages','files-container','console2','btn_image','2');

	var parUserIds = $('#index_usersId').val();
	loadInfomation(parUserIds);
});

function loadInfomation(parUserIds) {
	$.ajax({
		type : "post",
		url : portalPath + "/users/selectUsersByPrimaryKey.do",
		data : {
			userId : parUserIds
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst.value) {
				$('#index_regtime').html(rst.value.createTime);
				var email = rst.value.email;
				$('#index_safe_emails').html(email);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {

		}
	});
}

function updateDepartInfo_save(form) {
	var form = $(form);
	if (!form.form('validate')) {
		alert("请将信息补充完整！");
		return;
	}
	var params = form.serializeObject();
	params['legalPersonIdType'] = $('input[name=legalPersonIdType]:checked')
			.val();
	params['transBussScope'] = "";
	$("input[name='transBussScope']:checked").each(
			function() {
				params['transBussScope'] = params['transBussScope']
						+ $(this).attr('value') + ',';
			});
	params['businessClassify'] = "";
	$("input[name='businessClassify']:checked").each(
			function() {
				params['businessClassify'] = params['businessClassify']
						+ $(this).attr('value') + ',';
			});
	updateMemberInfo(params, 1);
}

/* 修改密码 */
function updatePassword() {
	var dialog = $("#dialog-messagepass").removeClass('hide').dialog({
		resizable : false,
		modal : true,
		width : 400,
		title : "密码修改",
		buttons : [ {
			html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
			"class" : "btn btn-info btn-xs",
			click : function() {
				var oldpass = $('#oldpassword').val();
				var pass = $('#password').val();
				var repass = $('#repassword').val();
				if(oldpass == ''){
					alert("请输入原密码！");
					return;
				}
				if (pass == '') {
					alert("请输入密码！");
					return;
				}
				if (repass == '') {
					alert("请输入确认密码！");
					return;
				}
				if (pass != repass) {
					alert("两次输入的密码不一致！");
					return;
				}
				if (confirm("确定要修改吗？")) {
					$.ajax({
						type : "post",
						url : portalPath + "/system/updatePassword.do",
						data : {
							oldpass : oldpass,
							password : pass,
							repassword : repass
						},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {
							if (rst.state) {
								alert(rst.errorMessage);
								dialog.dialog("close");
							} else {
								alert(rst.errorMessage);
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {

						}
					});
				}
			}
		}, {
			html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
			"class" : "btn btn-xs",
			click : function() {
				$(this).dialog("close");
			}
		} ]
	});
}

/* 修改邮箱 */
function updateEmail() {
	var dialog = $("#dialog-messageemail").removeClass('hide').dialog({
		resizable : false,
		modal : true,
		width : 450,
		title : "修改邮箱",
		buttons : [ {
			html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
			"class" : "btn btn-info btn-xs",
			click : function() {
				var email = $('#dia_email').val();
				var code = $('#dia_remarkcode').val();
				if (email == '') {
					alert("请输入邮箱！");
					return;
				}
				if (code == '') {
					alert("请输入验证码！");
					return;
				}
				if (confirm("确定要修改吗？")) {
					$.ajax({
						type : "post",
						url : portalPath + "/system/updateEmail.do",
						data : {
							email : email,
							code : code
						},
						beforeSend : function(XMLHttpRequest) {

						},
						success : function(rst, textStatus) {
							if (rst.state) {
								alert(rst.errorMessage);
								dialog.dialog("close");
							} else {
								alert(rst.errorMessage);
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {

						}
					});
				}
			}
		}, {
			html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
			"class" : "btn btn-xs",
			click : function() {
				$(this).dialog("close");
			}
		} ]
	});
}

function sendCode() {
	var email = $('#dia_email').val();
	if (email == '') {
		alert("请输入邮箱！");
		return;
	}
	$.ajax({
		type : "post",
		url : portalPath + "/system/sendEmailCode.do",
		data : {
			email : email
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			if (rst.state) {
				alert(rst.errorMessage);
			} else {
				alert(rst.errorMessage);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {

		}
	});
}

function update_qualifiction() {
	document.getElementById("index_baseInfo").style.display = "";
	document.getElementById("index_safe").style.display = "none";
	$("#tab6").removeClass("active");
	$("#li_tab6").removeClass("active");
	$("#tab7").removeClass("active");
	$("#li_tab7").removeClass("active");
	$("#tab8").removeClass("active");
	$("#li_tab8").removeClass("active");
	$("#tab9").addClass("active");
	$("#li_tab9").addClass("active");
}

/* 了解规章制度 */
function index_assregualtions() {
	var flag = $('#index_checkLevel').val();
	parent.addPanel("入会邀请函", contextPath
			+ '/dynamic/service//companyUserCenter/' + parDeptId
			+ '/invitation.jsp?flag=' + flag, true);
}

/* 加入协会 */
function index_addDepar() {
	parent.addPanel("填写申请", contextPath
			+ '/dynamic/service/companyUserCenter/' + parDeptId
			+ '/application.jsp', true);
}

/**/
function updateDepartQual_save(form) {
	var form = $(form);
	if (!form.form('validate')) {
		alert("请将信息补充完整！");
		return;
	}
	var scopeBuss = $('#scopeBuss').val();
	if (scopeBuss == null || scopeBuss == undefined) {
		alert("经营范围不能为空！");
		return;
	}
	var params = form.serializeObject();
	$.ajax({
		type : "post",
		url : contextPath + "/www/reg/updateByPrimary.do",
		data : {
			jsons : JSON.stringify(params)
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			alert(rst.errorMessage);
			if(rst.status==0){
				var a = "/" + parDeptId;
				window.location.href = contextPath
						+ "/dynamic/service/companyUserCenter" + a
						+ "/appover.jsp";
			}
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {

		}
	});
}

/* 下载申请表 */
function download_application() {
	window.location.href = contextPath + "/companyUserCenter/downloadWord.do";
}

/* 修改企业图片*/
function editIma() {
	//uploadDepartImg(updateDeImage, "index_chIma", "index_editIma");
	dialogImgs = $("#dialog-messageImg").removeClass('hide').dialog({
		resizable : false,
		modal : true,
		width : 450,
		title : "修改企业图片",
		buttons : [ {
			html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 上传",
			"class" : "btn btn-info btn-xs",
			"id": "btn_image",
			click : function() {
				loadImg();
				var img = $("#index_avatar").attr("src");
				var img2 = '/portal/content/portal/images/-text.png';
				if(img!=img2){
					$(this).dialog("close");
					alert("修改成功");
				}
			}
		}, {
			html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
			"class" : "btn btn-xs",
			click : function() {
				$(this).dialog("close");
			}
		} ]
	});
}

/*更新图片*/
function updateDeImg(targetInput,fileSrc,id) {
	var params = {};
	params.simage = fileSrc;
	$.ajax({
		type : "post",
		url : contextPath + "/www/reg/updateByPrimary.do",
		data : {
			jsons : JSON.stringify(params)
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			//loadImg();
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {

		}
	});
}

function loadImg(){
	$.ajax({
		type : "post",
		url : contextPath + "/www/reg/selectInfoByPrimary.do",
		data : {},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var simage = fastdfs_server+rst.value.simage;
		    $('#index_avatar').attr("src", simage); 
		},
		error : function() {

		}
	});
}

/**/
function approver_end(){
	parent.removePanel();
}

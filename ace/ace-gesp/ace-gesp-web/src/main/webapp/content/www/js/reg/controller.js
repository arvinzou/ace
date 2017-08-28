jQuery(function($) {
	$("#flashImage").click(
			function() {
				$('#imageF').hide().attr(
						'src',
						'${portalPath}/captcha/image.do' + '?'
								+ Math.floor(Math.random() * 100)).fadeIn();
			});
	$('#btn-share').on('click', function() {
		getCheckCode();
	});
	$('#btn-sendRegMail').on('click', function() {
		sendRegMail();
	});
	$('#fuelux-wizard')
			.ace_wizard({
			//step: 2 //optional argument. wizard will jump to step "2" at first
			})
			.on(
					'change',
					function(e, info) {
						$('#fm-reg1').submit();
						$('#fm-reg2').submit();
						if (info.step == 1) {
							var reslut = $("#fm-reg1").form('validate');
							if (reslut) {
								reslut = checkCode();
								if (reslut == "true") {
									return true;
								} else {
									return false;
								}
							}
							$('#reg_account').val(" ");
							$('#reg_password').val(" ");
							return reslut;
						}
						if (info.step == 2) {
							//$("#view-email").append($('input[name=contactEmail]').val());
							var result = $("#fm-reg2").form('validate');
							if (result) {
								result = reg();
								if (result == "true") {
									return true;
								} else {
									return false;
								}
							}
							return result;
						}
						if (info.step == 3) {
							var a = $('#reg_departmentIds').val();
							var html = "";
							$('#btn_loginpages').html("");
							if (a) {
								html = '<a href="'
										+ portalPath
										+ '/dynamic/portal/security/'
										+ a
										+ '/index.jsp" class="btn btn-info" style="margin-left:8px;">跳到登录页面</a>';
							} else {
								'<a href="'
										+ portalPath
										+ '/dynamic/portal/security/login.jsp" class="btn btn-info" style="margin-left:8px;">跳到登录页面</a>'
							}
							$('#btn_loginpages').html(html);
						}
					}).on('finished', function(e) {

			}).on('stepclick', function(e) {

			});

	$('#fm-reg1').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			console.log(params);
			return false;
		}
	});
	$('#modal-wizard .modal-header').ace_wizard();
	$('#modal-wizard .wizard-actions .btn[data-dismiss=modal]').removeAttr(
			'disabled');
	$('[data-rel=tooltip]').tooltip({
		container : 'body'
	});

	$('[data-rel=popover]').popover({
		container : 'body'
	});

	$('#fm-reg2').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			console.log(params);
			return false;
		}
	});
	$('#bussPro').combotree({
		url : portalPath + '/system/selectProvinceTreeList.do?id=00&&level=5',
		method : 'get',
		animate : true,
		lines : false,
		required : true,
		onSelect : function(node) {
			$('#bussCity').combotree('setValue', '');
			reload('4', node.id, "#bussCity");
		}
	});

	$('#bussCity').combotree({
		url : "",
		method : 'get',
		animate : true,
		lines : false,
		required : true,
		onSelect : function(node) {
			$('#bussAreaCode').combotree('setValue', '');
			reload('3', node.id, "#bussAreaCode");
		}
	});

	$('#bussAreaCode').combotree({
		url : "",
		method : 'get',
		animate : true,
		lines : false,
		required : true
	});

	$('#reg_imgcheckCode')
			.blur(
					function() {
						var newValue = $('#reg_imgcheckCode').val();
						if (newValue == "" || newValue == undefined) {
							return false;
						}
						$
								.ajax({
									type : "post",
									url : contextPath
											+ "/www/reg/selectByImage.do",
									data : {
										imageCheckCode : newValue
									},
									beforeSend : function(XMLHttpRequest) {
									},
									success : function(rst, textStatus) {
										if (rst.status == '1') {
											bootbox
													.dialog({
														title : '系统提示',
														message : rst.errorMessage,
														detail : rst.detail,
														buttons : {
															"success" : {
																"label" : "<i class='ace-icon fa fa-check'></i>确定",
																"className" : "btn-sm btn-success",
																"callback" : function() {

																}
															}
														}
													});
										} else {
											$("#ReturnrReason").css('display',
													'');
										}
									},
									complete : function(XMLHttpRequest,
											textStatus) {
									},
									error : function() {
									}
								});
					});
	$('#reg_account').blur(function() {
		var newValue = $('#reg_account').val();
		if (newValue == "" || newValue == undefined) {
			return false;
		}
		$.ajax({
			type : "post",
			url : contextPath + "/www/reg/selectByAccount.do",
			data : {
				accounts : newValue
			},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(rst, textStatus) {
				var message = " ";
				if (rst.status == '1') {
					message = newValue + rst.errorMessage;
					$('#reg_account').val("");
				}
				$('#reg_accountText').html(message);
			},
			complete : function(XMLHttpRequest, textStatus) {
			},
			error : function() {
			}
		});
	});
	$('#reg_departmentName').blur(function() {
		var newValue = $('#reg_departmentName').val();
		var transNo = $('#reg_transBussLicenseNo').val();
		if (newValue == "" || newValue == undefined) {
			return false;
		}
		$.ajax({
			type : "post",
			url : contextPath + "/www/reg/selectBy.do?flag=1",
			data : {
				departmentName : newValue,
				transBussLicenseNo : transNo
			},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(rst, textStatus) {
				var message = " ";
				var messages = " ";
				if (rst.status == 1) {
					message = rst.errorMessage;
					$('#reg_departmentName').val("");
				} else if (rst.status == 2) {
					messages = rst.errorMessage;
					$('#reg_transBussLicenseNo').val("");
				}
				$('#reg_departmentNameText').html(message);
				$('#reg_transBussLicenseNoText').html(messages);
			},
			complete : function(XMLHttpRequest, textStatus) {
			},
			error : function() {
			}
		});
	});

	$('#reg_transBussLicenseNo').blur(function() {
		var deptName = $('#reg_departmentName').val();
		var newValue = $('#reg_transBussLicenseNo').val();
		if (newValue == "" || newValue == undefined) {
			return false;
		}
		if (deptName == "" || deptName == undefined) {
			return false;
		}
		$.ajax({
			type : "post",
			url : contextPath + "/www/reg/selectBy.do?flag=2",
			data : {
				departmentName : deptName,
				transBussLicenseNo : newValue
			},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(rst, textStatus) {
				var message = " ";
				var messages = " ";
				if (rst.status == 1) {
					message = rst.errorMessage;
					$('#reg_departmentName').val("");
				} else if (rst.status == 2) {
					messages = rst.errorMessage;
					$('#reg_transBussLicenseNo').val("");
				}
				$('#reg_departmentNameText').html(message);
				$('#reg_transBussLicenseNoText').html(messages);
			},
			complete : function(XMLHttpRequest, textStatus) {
			},
			error : function() {
			}
		});
	});

});

var params = {};
function reg1Submit() {

	return false;
}
function reg2Submit() {

	return false;
}

function reg() {
	var form = $("#fm-reg2");
	if (!form.form('validate')) {
		alert("请将信息补充完整！");
		return false;
	}
	//var params = form.serializeObject();
	var accounts = $('#reg_account').val();
	var pass = $('#reg_password').val();
	if (accounts.trim() == null && accounts.trim() == undefined) {
		$('#reg_accountText').html("账号不能为空！");
		return;
	}
	if (pass.trim() == null && pass.trim() == undefined) {
		$('#reg_passwordText').html("密码不能为空！");
		return;
	}
	var reg = /^([a-zA-Z0-9]){6,20}$/;
	if (!pass.match(reg)) {
		$('#reg_passwordText').html("6-20个字母、数字或符号组成");
		return;
	} else {
		$('#reg_passwordText').html(" ");
	}
	params['account'] = accounts;
	params['password'] = pass;
	console.log(params);
	$.ajax({
		type : "post",
		async : false,
		url : contextPath + "/www/reg/reg.do",
		data : {
			jsons : JSON.stringify(params)
		},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-reg').attr('disabled', "true");
		},
		success : function(rst, textStatus) {
			$('#btn-reg').removeAttr("disabled");
			if (rst.status != 0) {
				bootbox.dialog({
					title : '系统提示',
					message : rst.errorMessage,
					detail : rst.detail,
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback" : function() {

							}
						}
					}
				});
				$('#reg_flag').val(false);
			} else {
				$('#reg_flag').val(true);
				document.getElementById("btn_footer").style.display = "none";
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			$('#btn-reg').removeAttr("disabled");
		},
		error : function() {
			$('#btn-reg').removeAttr("disabled");
		}
	});
	return $('#reg_flag').val();
}

/*发送验证码*/
function getCheckCode() {
	console.log(params);
	var to = $('input[name=contactEmail]').val();
	if (to == null || to == '') {
		alert("邮箱地址不能为空，请先填写");
		return;
	}
	params.to = to;
	$.ajax({
		type : "get",
		url : contextPath + "/www/reg/getCheckCode.do",
		data : {
			jsons : JSON.stringify(params)
		},
		//url : portalPath + "/system/sendEmailCode.do",
		//data:{email:to},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-share').attr('disabled', "true");
		},
		success : function(rst, textStatus) {
			$('#btn-share').removeAttr("disabled");
			settime(60);
			bootbox.dialog({
				title : '系统提示',
				message : rst.errorMessage,
				detail : rst.detail,
				buttons : {
					"success" : {
						"label" : "<i class='ace-icon fa fa-check'></i>确定",
						"className" : "btn-sm btn-success",
						"callback" : function() {

						}
					}
				}
			});
		},
		complete : function(XMLHttpRequest, textStatus) {
			$('#btn-share').removeAttr("disabled");
		},
		error : function() {
			$('#btn-share').removeAttr("disabled");
		}
	});
}

function settime(countdown) {
	if (countdown == 0) {
		document.getElementById('btn-share').removeAttribute("disabled");
		$('#btn-share').text(
				'获取验证码<i class="ace-icon fa fa-reply icon-on-right"></i>');
		countdown = 60;
	} else {
		$('#btn-share').text("重新发送(" + countdown + ")");
		document.getElementById('btn-share').setAttribute("disabled", true);
		countdown--;
	}
	setTimeout(function() {
		settime(countdown)
	}, 1000);
}

function checkCode() {
	var email = $('#reg_contactEmail').val();
	var checkCode = $('#reg_checkCode').val();
	if (email == undefined || email == "") {
		alert("邮箱地址不能为空！");
		return false;
	}
	if (checkCode == undefined || checkCode == "") {
		alert("邮箱验证码不能为空！");
		return false;
	}
	$.ajax({
		type : "post",
		async : false,
		url : contextPath + "/www/reg/checkCodeMethod.do",
		data : {
			email : email,
			checkCode : checkCode
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst.status != 0) {
				bootbox.dialog({
					title : '系统提示',
					message : rst.errorMessage,
					detail : rst.detail,
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback" : function() {
							}
						}
					}
				});
				$('#reg_flag').val(false);
			} else {
				$('#reg_flag').val(true);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
		}
	});
	return $('#reg_flag').val();
}

function sendRegMail() {
	console.log(params);
	var to = $('input[name=contactEmail]').val();
	if (to == null || to == '') {
		alert("联系人Email不能为空，请先填写");
		return;
	}
	params.to = to;
	$.ajax({
		type : "post",
		url : contextPath + "/www/reg/sendRegMail.do",
		data : {
			account : to
		},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-sendRegMail').attr('disabled', "true");
		},
		success : function(rst, textStatus) {
			$('#btn-sendRegMail').removeAttr("disabled");
			bootbox.dialog({
				title : '系统提示',
				message : rst.errorMessage,
				detail : rst.detail,
				buttons : {
					"success" : {
						"label" : "<i class='ace-icon fa fa-check'></i>确定",
						"className" : "btn-sm btn-success",
						"callback" : function() {

						}
					}
				}
			});
		},
		complete : function(XMLHttpRequest, textStatus) {
			$('#btn-sendRegMail').removeAttr("disabled");
		},
		error : function() {
			$('#btn-sendRegMail').removeAttr("disabled");
		}
	});
}

function reload(level, node, id) {
	$.ajax({
		type : "post",
		url : portalPath + "/system/selectProvinceTreeList.do",
		data : {
			level : level,
			id : node
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			if (rst) {
				$(id).combotree('loadData', rst);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {

		}
	});
}

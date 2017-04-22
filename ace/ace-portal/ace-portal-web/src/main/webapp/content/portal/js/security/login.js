/**
 * 
 */
jQuery(function($) {
	$(document).on('click', '.toolbar a[data-target]', function(e) {
		e.preventDefault();
		var target = $(this).data('target');
		$('.widget-box.visible').removeClass('visible');// hide others
		$(target).addClass('visible');// show target
	});
	// 默认灰色样式
	$('body').attr('class', 'login-layout light-login');
	$('#id-text2').attr('class', 'grey');
	$('#id-company-text').attr('class', 'blue');
});

// you don't need this, just used for changing background
jQuery(function($) {
	$('#btn-login-dark').on('click', function(e) {
		$('body').attr('class', 'login-layout');
		$('#id-text2').attr('class', 'white');
		$('#id-company-text').attr('class', 'blue');

		e.preventDefault();
	});
	$('#btn-login-light').on('click', function(e) {
		$('body').attr('class', 'login-layout light-login');
		$('#id-text2').attr('class', 'grey');
		$('#id-company-text').attr('class', 'blue');

		e.preventDefault();
	});
	$('#btn-login-blur').on('click', function(e) {
		$('body').attr('class', 'login-layout blur-login');
		$('#id-text2').attr('class', 'white');
		$('#id-company-text').attr('class', 'light-blue');

		e.preventDefault();
	});
	$('#btn-login-submit').on('click', function(e) {
		j_submit();
	});
	$('#btn-login-rp')
			.on(
					'click',
					function(e) {
						$
								.ajax({
									type : "post",
									url : contextPath
											+ "/system/retrievePassword.do",
									data : {
										email : $('#email').val()
									},
									beforeSend : function(XMLHttpRequest) {
										style_ajax_button('btn-login-rp', true);
									},
									success : function(rst, textStatus) {
										style_ajax_button('btn-login-rp', false);
										if (rst) {
											bootbox
													.dialog({
														title : '系统提示',
														message : rst.message,
														detail : '',
														buttons : {
															"success" : {
																"label" : "<i class='ace-icon fa fa-check'></i>确定",
																"className" : "btn-sm btn-success",
																"callback" : function() {

																}
															}
														}
													});

										}
									},
									complete : function(XMLHttpRequest,
											textStatus) {
										// style_ajax_button('btn-login-rp',false);
									},
									error : function(XMLHttpRequest,
											textStatus, errorThrown) {
										style_ajax_button('btn-login-rp', false);
										bootbox
												.dialog({
													title : '系统提示',
													message : "<h2>"
															+ XMLHttpRequest.status
															+ " " + textStatus
															+ "</h2>",
													buttons : {
														"success" : {
															"label" : "<i class='ace-icon fa fa-check'></i>确定",
															"className" : "btn-sm btn-success",
															"callback" : function() {

															}
														}
													}
												});
									}
								});
					});
	// e.preventDefault();
});
function style_ajax_button(btnId, status) {
	console.log(status);
	var btn = $('#' + btnId);
	if (status) {
		btn.find('i').removeClass('fa-check');
		btn.find('i').addClass('fa-spinner fa-spin');
		btn.attr('disabled', "true");

	} else {
		btn.find('i').removeClass('fa-spinner');
		btn.find('i').removeClass('fa-spin');
		btn.find('i').addClass('fa-check');
		btn.removeAttr("disabled");
	}
}

function onLoad() {
	document.getElementById("j_username").focus();
}
function j_submit() {
	var userName = document.getElementById("j_username");
	var password = document.getElementById("j_password");
	var captcha = document.getElementById("j_captcha");
	var reg = /(?=.*[a-z])(?=.*\d)(?=.*[#@!~%^&*])[a-z\d#@!~%^&*]{8,16}/i;
	if (userName.value == "") {
		userName.focus();
		alert("请输入用户名");
		return false;
	}
	if (password.value == "") {
		password.focus();
		alert("请输入密码");
		return false;
	}
	if (captcha.value == "") {
		captcha.focus();
		alert("请输入验证码");
		return false;
	}
	if (userName.value.length > 20) {
		userName.focus();
		alert("用户名长度不能超过20");
		return false;
	}

	if (password.value.length > 20) {
		password.focus();
		alert("密码 长度不能超过20");
		return false;
	}
	if (!reg.test(password.value)) {
		alert("尊敬的用户您的密码过于简单，建议您把密码修改为，长度大于8位 、包含字母、数字、特殊符号，谢谢！");
	}
	var form = document.getElementById("login_form");
	form.submit();
	$('.widget-box.visible').removeClass('visible');// hide others
	$('#progress-bar-box').addClass('visible');
}
window.onload = new function() {
	if (window.top != window.self)
		window.top.window.location.href = contextPath
				+ "/dynamic/portal/security/login.jsp";
}
jQuery(function($) {
	var userName = document.getElementById("j_username");
	var password = document.getElementById("j_password");
	var captcha = document.getElementById("j_captcha");
	$("#flashImage").click(
			function() {
				$('#imageF').hide().attr(
						'src',
						contextPath+'/captcha/image.do'
								+ '?' + Math.floor(Math.random() * 100))
						.fadeIn();
			});

	$('#j_username').keydown(function(e) {
		if (e.keyCode == 13) {
			password.focus();
		}
	});
	$('#j_password').keydown(function(e) {
		if (e.keyCode == 13) {
			captcha.focus();
		}
	});
	$('#j_captcha').keydown(function(e) {
		if (e.keyCode == 13) {
			j_submit();
		}
	});
	$(":radio").click(function(){
			$("#id-text2").html(sys_name[$(this).val()]);
	});
	//$("#id-text2").html(sys_name[0]);
});

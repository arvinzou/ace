<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>密码修改</title>
</head>
<jsp:include page="../common/common.jsp" />
<body>

	<div class="page-content">


		<div style="width:100%;">
			<div style="width:60%;padding-left:30%;padding-top:10%;">
			<form id="fm-password">
				<div class="form-group">
					<label class="control-label">新设密码：</label>
					<input id="password" name="password" type="password" class="form-control"></div>
				<div class="form-group">
					<label class="control-label">重复输入：</label>
					<input id="repassword" name="repassword" type="password" class="form-control"></div>

				<button type="submit" class="btn btn-info" authority="false">提交</button>
			</form>


			</div>

		</div>

    </div>
	<jsp:include page="../common/footer-1.jsp" />
	<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
	<script src="${portalPath}/content/common/js/jquery.format.js?v=${cfg.version}"></script>
	<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
	<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/localization/messages_zh.js?v=${cfg.version}"></script>

<script>
 var reg = /(?=.*[a-z])(?=.*\d)(?=.*[#@!~%^&*])[a-z\d#@!~%^&*]{8,16}/i;
window.onload=function(){

		$('#fm-password').ajaxForm({
				beforeSubmit: function (formData, jqForm, options) {
					var params = {};
					$.each(formData, function (n, obj) {
						params[obj.name] = obj.value;
					});
					$.extend(params, {
						time: new Date()
					});
					console.log(params);
					edit(params);
					return false;
				}
			});
			$.validator.setDefaults({
			 	submitHandler: function(form) { alert("submitted!");form.submit(); }
			});
			jQuery.validator.addMethod("isPwdCode", function(value, element) {
				var tel = /(?=.*[a-z])(?=.*\d)(?=.*[#@!~%^&*])[a-z\d#@!~%^&*]{8,16}/i;
				return this.optional(element) || (tel.test(value));
			}, "尊敬的用户您的密码过于简单，建议您把密码修改为，长度大于8位 、包含字母、数字、特殊符号，谢谢！");
 		$("#fm-password").validate({
            onkeyup: function(element) { $(element).valid(); },
			rules: {
				password: {
					required: true,
					minlength: 6,
					maxlength:20,
					isPwdCode:true
				},
				repassword: {
					required: true,
					minlength: 6,
					maxlength:20,
					equalTo: "#password",
					isPwdCode:true
				}
			},
			messages: {
				repassword:{
				    required: "请输入确认密码",
					minlength: jQuery.format("密码不能小于{0}个字符"),
					maxlength: jQuery.format("密码不能大于{0}个字符"),
					equalTo: "两次输入密码不一致不一致"
				},
				password: {
					required: "请输入密码",
					minlength: jQuery.format("密码不能小于{0}个字符"),
					maxlength: jQuery.format("密码不能大于{0}个字符")
				}
			}
		});
}
function edit(params) {
		if (!reg.test(params.password)) {
            alert("尊敬的用户您的密码过于简单，建议您把密码修改为，长度大于8位 、包含字母、数字、特殊符号，谢谢！");
            return;
        }
	if (confirm("确定要修改吗？")) {
		$.ajax({
			type : "post",
			url : contextPath + "/system/updatePassword.do",
			data :params,
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
}
</script>

 <style>
	  .error{
            color:red;
            padding-top: 7px;
         }
 </style>
</body>
</html>
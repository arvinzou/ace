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
<jsp:include page="/dynamic/common/header.jsp"/>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

	<div class="portlet-body">
		<div class="form-panel">
			<!--具体界面元素开始-->
			<form class="form-horizontal" id="fm-password" role="form">

					<div class="form-group">
						<label class="col-md-2 control-label">新密码<span class="required">*</span></label>
						<div class="col-md-4">
							<input type="text" class="form-control" id="password" name="password" type="password">
							<span class="help-block"> </span>
						</div>
					</div>

				<div class="form-group">
					<label class="col-md-2 control-label">重复输入<span class="required">*</span></label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="repassword" name="repassword" type="password">
						<span class="help-block"> </span>
					</div>
				</div>


				<div class="form-actions">
					<div class="row">
						<div class="col-md-offset-3 col-md-7">
							<button class="btn   green" type="submit" style="width:30%">保存</button>
						</div>
					</div>
				</div>
			</form>
		</div>

	</div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<jsp:include page="/dynamic/common/footer.jsp" />
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jquery.format.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/localization/messages_zh.js?v=${cfg.version}"></script>

<script>
 var reg = /(?=.*[a-z])(?=.*\d)(?=.*[#@!~%^&*])[a-z\d#@!~%^&*]{8,16}/i;
window.onload=function(){
 $(".todo-header").html("密码修改");
			jQuery.validator.addMethod("isPwdCode", function(value, element) {
				var tel = /(?=.*[a-z])(?=.*\d)(?=.*[$#@!~%^&*])[a-z\d$#@!~%^&*]{6,16}/i;
				return this.optional(element) || (tel.test(value));
			}, "尊敬的用户您的密码过于简单，建议您把密码修改为，长度大于6位 、包含字母、数字、特殊符号，谢谢！");
			var rule={
            onkeyup: function(element) { $(element).valid(); },
			rules: {
				password: {
					required: true,
					minlength: 6,
					maxlength:16,
					isPwdCode:true
				},
				repassword: {
					required: true,
					minlength: 6,
					maxlength:16,
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
		};
		$("#fm-password").validate(rule);
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


}
function edit(params) {
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
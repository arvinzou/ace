<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>role</title>
</head>
<jsp:include page="../common/common.jsp" />
<body>

	<div class="page-content">


		<div>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">

						<h4 class="modal-title">密码修改</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<form id="fm-password">
                                    <div class="form-group">
                                        <label class="control-label">新设密码：</label>
                                        <input id="password" type="password" class="form-control"></div>
                                    <div class="form-group">
                                        <label class="control-label">重复输入：</label>
                                        <input id="repassword" type="password" class="form-control"></div>
								</form>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" authority="false" onclick="submitform()">提交</button>
					</div>
				</div>
			</div>
		</div>

    </div>
	<jsp:include page="../common/footer-1.jsp" />

<script>


function submitform() {
	if ($('#password').val() == '') {
		alert("请输入密码！");
		return;
	}
	if ($('#password').val() != $('#repassword').val()) {
		alert("两次输入的密码不一致！");
		return;
	}
	if (confirm("确定要修改吗？")) {
		$.ajax({
			type : "post",
			url : contextPath + "/system/updatePassword.do",
			data : {
				password : $('#password').val(),
				repassword : $('#repassword').val()
			},
			beforeSend : function(XMLHttpRequest) {

			},
			success : function(rst, textStatus) {
				if (rst.state) {
					alert(rst.errorMessage);
					$('#stack1').modal('hide')
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

 
</body>
</html>
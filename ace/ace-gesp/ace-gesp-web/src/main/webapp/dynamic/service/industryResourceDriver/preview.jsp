<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>industryResourceCar</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">
	var id = '${param.id}';
</script>
<body>
	<div class="page-content">


		<fieldset>
			<legend>基本信息</legend>


			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<!-- <div class="profile-info-name">id</div>
				<div class="profile-info-value">
					<span class="editable editable-click" id="id"></span>
				</div> -->
					<div class="profile-info-name">姓名</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="name"></span>
					</div>
					<div class="profile-info-name">性别</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="sex"></span>
					</div>
					<div class="profile-info-name">身份证号码</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="idCard"></span>
					</div>
				</div>

				<div class="profile-info-row">
					<!-- <div class="profile-info-name">人员状态</div>
				<div class="profile-info-value">
					<span class="editable editable-click" id="status"></span>
				</div> -->
					<div class="profile-info-name">所属企业</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="deptId"></span>
					</div>
					<div class="profile-info-name">出生日期</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="birthdate"></span>
					</div>
					<div class="profile-info-name">初领驾驶证日期</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="initDrivingLicDate"></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">从业资格证号</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="certNumber"></span>
					</div>
					<div class="profile-info-name">从业人员类别</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="certPersonType"></span>
					</div>
					<div class="profile-info-name">从业资格类别</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="certType"></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">备案资格类别</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="filingCertType"></span>
					</div>
					<div class="profile-info-name">驾驶证号</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="driverLicNo"></span>
					</div>
					<div class="profile-info-name">驾驶证准驾车型</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="driverLicCarType"></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">入职时间</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="entryTime"></span>
					</div>
					<div class="profile-info-name">从业发证机构名称</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="certCompanyName"></span>
					</div>
					<div class="profile-info-name">合作方式</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="cooperationMode"></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">联系电话</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="tel"></span>
					</div>
					<div class="profile-info-name">备案状态</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="recordTime"></span>
					</div>
					<div class="profile-info-name">备注</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="remark"></span>
					</div>
				</div>
			</div>
		</fieldset>


		<fieldset>
			<legend>修订信息</legend>
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">创建时间</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="createDate"></span>
					</div>
					<div class="profile-info-name">创建用户名</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="createUserName"></span>
					</div>
					<div class="profile-info-name">最后修改时间</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="lastModifyDate"></span>
					</div>
				</div>
				<div class="profile-info-row">
					<!-- <div class="profile-info-name">创建的用户ID</div>
				<div class="profile-info-value">
					<span class="editable editable-click" id="createUserId"></span>
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">最后修改用户ID</div>
				<div class="profile-info-value">
					<span class="editable editable-click" id="lastModifyUserId"></span>
				</div> -->
					<div class="profile-info-name">最后修改用户名</div>
					<div class="profile-info-value">
						<span class="editable editable-click" id="lastModifyUserName"></span>
					</div>
				</div>
			</div>
		</fieldset>
	</div>

	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/industryResourceDriver/preview.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />

</body>
</html>
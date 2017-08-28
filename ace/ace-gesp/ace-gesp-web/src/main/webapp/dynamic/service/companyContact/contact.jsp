<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="profile-user-info profile-user-info-striped">
	<div class="profile-info-row">
		<div class="profile-info-name">所属企业</div>
		<div class="profile-info-value">
			<input type="hidden" name="userId" id="edit_userId" />
			<input name="departmentId" id="edit_departmentId" style="width: 260px; height: 25px; line-height: 25px;">
		</div>
		<div class="profile-info-name">姓名</div>
		<div class="profile-info-value">
			<input class="easyui-validatebox textbox" name="name" id="edit_name" data-options="required:true" style="width: 260px;line-height: 25px;height: 25px;"/>
		</div>
	</div>
	<div class="profile-info-row">
		<div class="profile-info-name">职务</div>
		<div class="profile-info-value">
			<input name="userLevel" id="edit_userLevel" style="width: 260px;line-height: 25px;height: 25px;"/>
		</div>
		<div class="profile-info-name">手机号</div>
		<div class="profile-info-value">
			<input class="easyui-validatebox textbox" id="edit_mobile" name="mobile" data-options="required:true" style="width: 260px;line-height: 25px;height: 25px;">
		</div>
	</div>
	<div class="profile-info-row">
		<div class="profile-info-name">电话号码</div>
		<div class="profile-info-value">
			<input class="easyui-textbox" id="edit_telphone" name="telphone" style="width: 260px; height: 25px; line-height: 25px;">
		</div>
		<div class="profile-info-name">传真</div>
		<div class="profile-info-value">
			<input class="easyui-textbox" name="fax" id="edit_fax" style="width: 260px; height: 25px; line-height: 25px;">
		</div>
	</div>
	<div class="profile-info-row">
		<div class="profile-info-name">QQ</div>
		<div class="profile-info-value">
			<input class="easyui-textbox" name="qq" id="edit_qq" style="width: 260px; height: 25px; line-height: 25px;">
		</div>
		<div class="profile-info-name">邮箱</div>
		<div class="profile-info-value">
			<input class="easyui-textbox" name="email" id="edit_email" style="width: 260px; height: 25px; line-height: 25px;">
		</div>
	</div>
</div>
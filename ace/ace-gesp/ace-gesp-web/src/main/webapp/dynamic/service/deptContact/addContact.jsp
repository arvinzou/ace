<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="profile-user-info profile-user-info-striped">
	<div class="profile-info-row">
		<div class="profile-info-name">姓名</div>
		<div class="profile-info-value">
			<input type="hidden" name="departmentId" id="add_departmentId" value="${SESSION_USERPROP_KEY.corpId}" />
			<input name="name" id="add_name" class="easyui-validatebox textbox" 
				data-options="required:true,validType:'name'" 
				style="width: 260px;line-height: 25px;height: 25px;"/>
		</div>
		<div class="profile-info-name">QQ</div>
		<div class="profile-info-value">
			<input name="qq" id="add_qq" class="easyui-validatebox textbox" 
				data-options="validType:'qq'"
				style="width: 260px; height: 25px; line-height: 25px;">
		</div>
	</div>
	<div class="profile-info-row">
		<div class="profile-info-name">职务</div>
		<div class="profile-info-value">
			<input name="userLevel" id="add_userLevel" data-options="required:true" style="width: 260px;line-height: 25px;height: 25px;"/>
		</div>
		<div class="profile-info-name">手机号</div>
		<div class="profile-info-value">
			<input class="easyui-numberbox" id="add_mobile" name="mobile" 
				data-options="required:true,precision:0,validType:'mobile'" 
				style="width: 260px;line-height: 25px;height: 25px;">
		</div>
	</div>
	<div class="profile-info-row">
		<div class="profile-info-name">电话号码</div>
		<div class="profile-info-value">
			<input id="add_telphone" name="telphone" class="easyui-numberbox" 
				data-options="validType:'phone'"
				style="width: 260px; height: 25px; line-height: 25px;">
		</div>
		<div class="profile-info-name">传真</div>
		<div class="profile-info-value">
			<input name="fax" id="add_fax" class="easyui-validatebox textbox" 
				data-options="validType:'faxno'"
				style="width: 260px; height: 25px; line-height: 25px;">
		</div>
	</div>
	<div class="profile-info-row">
		<div class="profile-info-name">邮箱</div>
		<div class="profile-info-value">
			<input name="email" id="add_email" class="easyui-validatebox textbox" 
				data-options="validType:'email'"
				style="width: 260px; height: 25px; line-height: 25px;">
		</div>
	</div>
</div>
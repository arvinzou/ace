<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="profile-user-info profile-user-info-striped">
	<div class="profile-info-row">
		<div class="profile-info-name"><span style="font-size:16px;color:red;">*</span>用户名</div>
		<div class="profile-info-value">
			<input type="hidden" name="userId" id="add_userId" />
			<input type="hidden" name="departmentId" id="add_departmentId">
			<input name="name" id="add_name" data-options="required:true" style="width: 260px;line-height: 25px;height: 25px;"/>
		</div>
		<div class="profile-info-name"><span style="font-size:16px;color:red;">*</span>账号</div>
		<div class="profile-info-value">
			<input class="easyui-validatebox textbox" name="account" id="add_account" 
				data-options="required:true,validType:'username'" style="width: 260px; height: 25px; line-height: 25px;">
		</div>
	</div>
	<div class="profile-info-row">
		<div class="profile-info-name"><span style="font-size:16px;color:red;">*</span>密码</div>
		<div class="profile-info-value">
			<input class="easyui-validatebox textbox" name="password" id="add_password" 
				data-options="required:true,validType:'password'" style="width: 260px; height: 25px; line-height: 25px;">
		</div>
		<div class="profile-info-name"><span style="font-size:16px;color:red;">*</span>性别</div>
		<div class="profile-info-value">
			<input name="sex" id="add_sex" style="width: 260px; height: 25px; line-height: 25px;">
		</div>
	</div>
	<div class="profile-info-row">
		<div class="profile-info-name"><span style="font-size:16px;color:red;">*</span>职务</div>
		<div class="profile-info-value">
			<input name="userLevel" id="add_userLevel" 
				data-options="required:true" 
				style="width: 260px;line-height: 25px;height: 25px;"/>
		</div>
		<div class="profile-info-name"><span style="font-size:16px;color:red;">*</span>手机号</div>
		<div class="profile-info-value">
			<input class="easyui-numberbox" id="add_mobile" name="mobile" 
				data-options="required:true,precision:0,validType:'mobile'" 
				style="width: 260px;line-height: 25px;height: 25px;">
		</div>
	</div>
	<div class="profile-info-row">
		<div class="profile-info-name"><span style="font-size:16px;color:red;">*</span>邮箱</div>
		<div class="profile-info-value">
			<input name="email" id="add_email" class="easyui-validatebox textbox" 
				data-options="required:true,precision:0,validType:'email'" 
				style="width: 260px; height: 25px; line-height: 25px;">
		</div>
		<div class="profile-info-name">电话号码</div>
		<div class="profile-info-value">
			<input id="add_telphone" name="telphone" class="easyui-validatebox textbox"
				data-options="required:true,precision:0,validType:'phone'" 
				style="width: 260px; height: 25px; line-height: 25px;">
		</div>
	</div>
	<div class="profile-info-row">
		<div class="profile-info-name">QQ</div>
		<div class="profile-info-value">
			<input name="qq" class="easyui-textbox" style="width:260px;height:25px;line-height:25px;">
		</div>
		<div class="profile-info-name">传真</div>
		<div class="profile-info-value">
			<input name="fax" id="add_fax" class="easyui-textbox" 
				style="width:260px;height:25px;line-height:25px;">
		</div>
	</div>
</div>
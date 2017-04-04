<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="dialog-message-datebox" class="hide">
	<form action="#" id="fm-datebox" onsubmit="return false">
		<div class="profile-user-info profile-user-info-striped profile-bg">
			<div class="profile-info-row">
				<div class="profile-group-title">基本</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">
					<span class="red">∗</span>label
				</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="label" data-options="required:true,validType:'length[1,20]'">
				</div>
				<div class="profile-info-name">
					<span class="red">∗</span>name
				</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="name" data-options="required:true,validType:'length[1,20]'">
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">placeholder</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="placeholder"
						data-options="required:false,validType:'length[1,20]'">
				</div>
				<div class="profile-info-name">defaultValue</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="defaultValue"
						data-options="required:false,validType:'length[1,20]'">
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">
					<span class="red">∗</span>dataType
				</div>
				<div class="profile-info-value">
					<select class="easyui-combobox" name="dataType"
						style="width: 150px">

						<option value="datetime">datetime</option>
					</select>
				</div>
				<div class="profile-info-name"><span class="red">∗</span>type</div>
				<div class="profile-info-value">
					<select class="easyui-combobox" name="type" style="width: 150px">
						<option value="date">date</option>
						<option value="datetime">datetime</option>
					</select>
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-group-title">验证&样式</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">required</div>
				<div class="profile-info-value">
					<input type="checkbox" name="required" />
				</div>
				<div class="profile-info-name">styleWidth</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="styleWidth" value="150"
						data-options="required:false,validType:'length[1,20]'">
				</div>
			</div>
		</div>
	</form>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="dialog-message-combobox" class="hide">
	<form action="#" id="fm-combobox" onsubmit="return false">
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
				<div class="profile-info-name">selected</div>
				<div class="profile-info-value">
					<select class="easyui-combobox" name="selected"
						style="width: 150px">
						<option value="false">false</option>
						<option value="true">true</option>
					</select>
				</div>
				<div class="profile-info-name">defaultValue</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="defaultValue"
						data-options="required:false,validType:'length[1,20]'">
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name"><span class="red">∗</span>dictId</div>
				<div class="profile-info-value">
					<input class="easyui-combobox"  style="width:150px"
            name="dictId" 
            data-options="
                    url:'${portalPath}/dictCategory/findDictCategoryListAll.do',
                    method:'get',
                    valueField:'categoryId',
                    textField:'name',
                    panelHeight:'200'
            ">
				</div>
				<div class="profile-info-name">dataUrl</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="dataUrl"
						data-options="required:false,validType:'length[1,100]'">
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name"><span class="red">∗</span>dataType</div>
				<div class="profile-info-value">
					<select class="easyui-combobox" name="dataType"
						style="width: 150px">
						<option value="string">string</option>
						<option value="long">long</option>
						<option value="double">double</option>
						<option value="datetime">datetime</option>
					</select>
				</div>
				<div class="profile-info-name"></div>
				<div class="profile-info-value"></div>
			</div>
			<div class="profile-info-row">
				<div class="profile-group-title">验证</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">required</div>
				<div class="profile-info-value">
					<input type="checkbox" name="required" />
				</div>
				<div class="profile-info-name">multiple</div>
				<div class="profile-info-value">

					<select class="easyui-combobox" name="multiple"
						style="width: 150px">
						<option value="false">false</option>
						<option value="true">true</option>
					</select>
				</div>
			</div>

			
			<div class="profile-info-row">
				<div class="profile-group-title">样式</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">styleWidth</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="styleWidth" value="150"
						data-options="required:false,validType:'length[1,20]'">
				</div>
				<div class="profile-info-name">panelHeight</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="panelHeight"
						data-options="required:false,validType:'length[1,20]'">
				</div>
			</div>
		</div>
	</form>
</div>
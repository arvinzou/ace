<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="dialog-message-filesbox" class="hide">
	<form action="#" id="fm-filesbox" onsubmit="return false">
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
				<div class="profile-info-name">fileTypes</div>
				<div class="profile-info-value">
					<select class="easyui-combobox" id="fileTypes" name="fileTypes"
						style="width: 150px"
						data-options="
                    multiple:true,
                    panelHeight:'auto'
                    ">
						<option value="doc">doc</option>
						<option value="jpg">jpg</option>
						<option value="xls">xls</option>
						<option value="pdf">pdf</option>
					</select>
				</div>
				<div class="profile-info-name">demoUrl</div>
				<div class="profile-info-value">
					
						<input class="easyui-validatebox textbox" style="width: 80px"
							name="demoUrl"
							data-options="required:false,validType:'length[1,500]'">
				
					
						<a href="javascript:uploadFile('demoUrl')">上传</a>
						<a href="javascript:downloadFile('demoUrl')">下载</a>
					

				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">
					<span class="red">∗</span>dataType
				</div>
				<div class="profile-info-value">
					<select class="easyui-combobox" name="dataType"
						style="width: 150px">
						<option value="string">file</option>
					</select>
				</div>
				<div class="profile-info-name">discribtion</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="discribtion"
						data-options="required:false,validType:'length[1,500]'">
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

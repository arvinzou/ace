<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="dialog-message-grouptitle" class="hide">
	<form action="#" id="fm-grouptitle" onsubmit="return false">
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
						<input type="hidden" name="dataType" value="string">
				</div>
				
			</div>
			
		</div>
	</form>
</div>
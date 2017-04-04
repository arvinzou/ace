<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="dialog-message-textbox" class="hide">
	<form action="#" id="fm-textbox" onsubmit="return false">
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
				<div class="profile-info-name">multiline</div>
				<div class="profile-info-value">
				<select class="easyui-combobox" name="multiline"
						style="width: 150px">
						<option value="false">false</option>
						<option value="true">true</option>
					</select>
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-group-title">验证</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">required</div>
				<div class="profile-info-value">
					<input type="checkbox" name="required" />
				</div>
				<div class="profile-info-name">validatorType</div>
				<div class="profile-info-value">

					<select class="easyui-combobox" name="validatorType"
						style="width: 150px">
						<option value="">请选择</option>
						<option value="idcard">idcard</option>
						<option value="minLength">minLength</option>
						<option value="length">length</option>
						<option value="phone">phone</option>

						<option value="mobile">mobile</option>
						<option value="intOrFloat">intOrFloat</option>
						<option value="currency">currency</option>
						<option value="qq">qq</option>


						<option value="integer">integer</option>
						<option value="age">age</option>
						<option value="chinese">chinese</option>
						<option value="english">english</option>


						<option value="unnormal">unnormal</option>
						<option value="username">username</option>
						<option value="faxno">faxno</option>
						<option value="zip">zip</option>


						<option value="ip">ip</option>
						<option value="name">name</option>
						<option value="date">date</option>
						<option value="zip">zip</option>

						<option value="msn">msn</option>
						<option value="same">same</option>
					</select>
				</div>
			</div>

			<div class="profile-info-row">
				<div class="profile-info-name">minlength</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="minlength"
						data-options="required:false,validType:'length[1,20]'">
				</div>
				<div class="profile-info-name">maxlength</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="maxlength" value="20"
						data-options="required:false,validType:'length[1,20]'">
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
				<div class="profile-info-name">styleHeight</div>
				<div class="profile-info-value">
					<input class="easyui-validatebox textbox" style="width: 150px"
						name="styleHeight"
						data-options="required:false,validType:'length[1,20]'">
				</div>
			</div>
		</div>
	</form>
</div>

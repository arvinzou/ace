
<%@page import="com.huacainfo.ace.common.tools.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>企业实名注册</title>
</head>
<style type="text/css">
	.form_title{
		color:red;
		width:20px;
		text-align: center;
	}
	.flBox{
	    text-align: right;
	    font-size:16px;
	}
	.step-pane{
		margin-top: 30px;
	}
	.formText{
		font-size:18px;
		font-weight: bolder;
	}
</style>
<jsp:include page="../../common/common-www.jsp" />

<body>
	<div class="page-content">
		<div class="col-sm-10 col-sm-offset-1">
			<div class="center">
				<h1>${cfg.sys_name}企业用户注册</h1>
				<%-- <div class="pull-left alert alert-info no-margin" align="center">
					<button type="button" class="close" data-dismiss="alert">
						<i class="ace-icon fa fa-times"></i>
					</button>
					<i class="ace-icon fa fa-umbrella bigger-120 blue"></i>
					温馨提示：企业注册采用实名制认证，请如实填写信息，信息提交成功后，后台验证成功后，将以邮件的方式将登录账号和密码发送至“联系人邮箱”中，
					请留意！
				</div> --%>
				<div style="height: 60px"></div>
			</div>
			<div style="height: 20px"></div>
			<div id="fuelux-wizard" data-target="#step-container">
				<ul class="wizard-steps">
					<li data-target="#step1" id="li-step1" class="active"><span class="step">1</span>
						<span class="title">验证邮箱</span></li>
					<li data-target="#step2" id="li-step2"><span class="step">2</span> <span
						class="title">填写账号信息</span></li>
					<li data-target="#step3" id="li-step3"><span class="step">3</span> <span
						class="title">完成注册</span></li>
				</ul>
			</div>
			<div style="height: 20px"></div>
			<div class="step-content pos-rel" id="step-container">
				<form id="fm-reg1" onsubmit="return reg1Submit()">
					<div class="step-pane active" id="step1" align="center">
						<!-- #section:pages/profile.info -->
						<table>
							<tr>
								<td class="flBox">
									<span class="form_title">*</span>邮箱地址:
								</td>
								<td style="width:20px;"></td>	
								<td>
									<input type="hidden" id="reg_flag" />
									<input class="easyui-validatebox textbox" placeholder="请输入邮箱地址" 
										style="width: 300px;height:35px;" id="reg_contactEmail"
										name="contactEmail" data-options="required:true,validType:'email'">
								</td>
							</tr>
							<tr style="height:10px;"></tr>
							<tr>
								<td class="flBox">
									<span class="form_title">*</span>验证码:
								</td>
								<td style="width:20px;"></td>
								<td>
									<input class="easyui-validatebox textbox" style="width: 180px;height:35px;" 
										data-rel="tooltip" data-placement="bottom" id="reg_imgcheckCode"
										data-options="validType:'length[1,20]'" placeholder="请输入验证码">
										<img id="imageF" src="${portalPath }/captcha/image.do?date=${date}" />
										<a href="#" id="flashImage">换一张</a>
								</td>
							</tr>
							<tr style="height:10px;"></tr>
							<tr id="ReturnrReason" style="display: none">
								<td class="flBox">
									<span class="form_title">*</span>邮箱验证码:
								</td>
								<td style="width:20px;"></td>
								<td>
									<input class="easyui-validatebox textbox" name="checkCode" style="width: 200px;height:35px;" 
										data-rel="tooltip" data-placement="bottom" id="reg_checkCode"
										data-options="required:true,validType:'length[1,50]'" placeholder="请输入验证码">
									<button class="btn btn-success" id="btn-share" >
										获取验证码<i class="ace-icon fa fa-reply icon-on-right"></i>
									</button>
								</td>
							</tr>
						</table>	
					</div>
				</form>
				<form id="fm-reg2" onsubmit="return reg2Submit()">
					<div class="step-pane" id="step2" align="center">
						<table>
							<tr style="height:20px;"></tr>
							<tr>
								<td class="formText">设置账号\密码</td>
								<td></td>	
								<td></td>	
							</tr>
							<tr style="height:30px;"></tr>
							<tr>
								<td class="flBox">
									<span class="form_title">*</span>登录账号:
								</td>
								<td style="width:20px;"></td>
								<td>
									<input class="easyui-validatebox textbox" style="width: 300px;height:35px;" id="reg_account"
										data-options="required:true,validType:'username'" placeholder="请输入登录账号">
									<span id="reg_accountText" class="form_title"></span>
								</td>
							</tr>
							<tr style="height:10px;"></tr>
							<tr>
								<td class="flBox">
									<span class="form_title">*</span>登录密码:
								</td>
								<td style="width:20px;"></td>
								<td>
									<input class="easyui-validatebox textbox" type="password" placeholder="请输入登录密码" 
										style="width: 300px;height:35px;" id="reg_password"
										data-options="required:true,validType:'password'">
									<span id="reg_passwordText" class="form_title"></span>
								</td>
							</tr>
							<tr style="height:20px;"></tr>
							<tr>
								<td class="formText">设置公司信息</td>
								<td></td>
								<td></td>
							</tr>
							<tr style="height:30px;"></tr>
							<tr>
								<td class="flBox">
									<span class="form_title">*</span>公司全称:
								</td>
								<td style="width:20px;"></td>
								<td>
									<input type="hidden" id="reg_departmentIds" name="parentDepartmentId" value="${cfg.sys_dept_id}" />
									<input class="easyui-validatebox textbox" placeholder="请按照营业执照填写公司名称" 
										style="width:300px;height:35px;" id="reg_departmentName"
										name="departmentName" data-options="required:true,validType:'chinese'">
									<span id="reg_departmentNameText" class="form_title"></span>
								</td>
							</tr>
							<tr style="height:10px;"></tr>						
							<tr>
								<td class="flBox">
									<span class="form_title">*</span>工商营业执照号:
								</td>
								<td style="width:20px;"></td>
								<td>
									<input class="easyui-numberbox" placeholder="请按照营业执照填写营业执照号" 
										style="width:300px;height:35px;" id="reg_bussLicenseNo"
										name="bussLicenseNo" data-options="required:true">
								</td>
							</tr>
							<tr style="height:10px;"></tr>					
							<tr>
								<td class="flBox">
									<span class="form_title">*</span>道路经营许可证号:
								</td>
								<td style="width:20px;"></td>
								<td>
									<input class="easyui-numberbox" placeholder="请按照道路经营许可证填写道路经营许可证号" 
										style="width:300px;height:35px;" id="reg_transBussLicenseNo"
										name="transBussLicenseNo" data-options="required:true">
									<span id="reg_transBussLicenseNoText" class="form_title"></span>
								</td>
							</tr>
							<tr style="height:10px;"></tr>					
							<tr>
								<td class="flBox">
									<span class="form_title">*</span>所属地区:
								</td>
								<td style="width:20px;"></td>
								<td>
									<input style="width: 120px;height:35px;" id="bussPro">
									<input style="width: 120px;height:35px;" id="bussCity">
									<input style="width: 120px;height:35px;" name="areaCode" id="bussAreaCode">
								</td>
							</tr>
							<tr style="height:10px;"></tr>
							<tr>
								<td class="flBox">
									<span class="form_title">*</span>注册联系人:
								</td>
								<td style="width:20px;"></td>
								<td>
									<input class="easyui-validatebox textbox" placeholder="请输入注册联系人姓名" 
										style="width:300px;height:35px;" id="reg_contactName"
										name="contactName" data-options="required:true,validType:'name'">
								</td>
							</tr>
							<tr style="height:10px;"></tr>
							<tr>
								<td class="flBox">
									<span class="form_title">*</span>手机号码:
								</td>
								<td style="width:20px;"></td>
								<td>
									<input class="easyui-numberbox" placeholder="请输入手机号码" style="width:300px;height:35px;" id="reg_contactMobile"
										name="contactMobile" data-options="required:true,validType:'mobile'">
								</td>
							</tr>
						</table>
					</div>
				</form>
				<div class="step-pane" id="step3">
					<div class="center">
						<h4>恭喜您，注册成功！</h4>
						<span id="btn_loginpages">
							<a href="${portalPath}/dynamic/portal/security/login.jsp" class="btn btn-info" style="margin-left:8px;" >跳到登录页面</a>
						</span>
						<%-- <h4>请提交完成注册,账号信息将发送到<span id="view-email" class="red"></span>,请接收并激活账号</h4>
						<button class="btn btn-success" id="btn-sendRegMail">
							重新发送邮件<i class="ace-icon fa fa-reply icon-on-right"></i>
						</button> --%>
					</div>
				</div>
			</div>
			<div id="btn_footer" class="modal-footer wizard-actions center">
				 <%-- <button class="btn btn-prev">
					<i class="ace-icon fa fa-arrow-left"></i> 上一步
				</button>  --%>
				<button class="btn btn-success btn-next" id="btn-reg" data-last="提交注册">
					下一步<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</button>
			</div>
			
			
			<%-- <div class="profile-user-info profile-user-info-striped">
				<form id="regInfo" method="post" novalidate>
					<div class="profile-info-row">
						<div class="profile-info-name">
							<span class="red">∗</span>企业全称
						</div>
						<div class="profile-info-value">
							<input type="hidden" name="parentDepartmentId" value="${cfg.sys_dept_id}" />
							<input class="easyui-validatebox textbox" style="width: 200px;height:25px;"
								name="departmentName"
								data-options="required:true,validType:['chinese','length[0,20]']">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">
							<span class="red">∗</span>联系人姓名
						</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px;height:25px;"
								name="contactName"
								data-options="required:true,validType:['chinese','length[0,20]']">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">
							<span class="red">∗</span>联系人手机号
						</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px;height:25px;"
								name="contactMobile" data-options="required:true,validType:'mobile'">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">
							<span class="red">∗</span>联系人Email
						</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px;height:25px;"
								name="contactEmail" data-options="required:true,validType:['email','length[0,30]']">
							<button class="btn btn-success" id="btn-share">
								获取验证码<i class="ace-icon fa fa-reply icon-on-right"></i>
							</button>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">
							<span class="red">∗</span>验证码
						</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px;height:25px;" data-rel="tooltip"
								name="checkCode" title="击获取验证码按钮，系统会通过邮箱发送验证码，获取验证码并且正确输入验证码才能注册成功， 验证邮箱地址将作为登录账号!" data-placement="bottom"
								data-options="required:true,validType:'length[1,50]'">
								<span class="help-button" data-rel="popover" data-trigger="hover" data-placement="right" data-content="" title="点击获取验证码按钮，系统会通过邮箱发送验证码，获取验证码并且正确输入验证码才能注册成功， 验证邮箱地址将作为登录账号!">?</span>
						</div>
					</div>
				</form>
			</div>
			<div align="center" style="margin-top:10px;">
				<button class="btn btn-success btn-next" id="btn-reg" onclick="javascript:reg();" style="font-size:14px;height:30px;">
					提交注册
				</button>
			</div> --%>
		
			<%--<div id="fuelux-wizard" data-target="#step-container">
				<ul class="wizard-steps">
					<li data-target="#step1" class="active"><span class="step">1</span>
						<span class="title">企业基本信息</span></li>

					<li data-target="#step2"><span class="step">2</span> <span
						class="title">注册联系人信息</span></li>

					<li data-target="#step3"><span class="step">3</span> <span
						class="title">完成注册</span></li>


				</ul>


			</div>
			<div style="height: 20px"></div>



			<div class="step-content pos-rel" id="step-container">
				<form id="fm-reg1" onsubmit="return reg1Submit()">
				<input type="hidden" name="status" value="1"/>
					<div class="step-pane active" id="step1">


						<!-- #section:pages/profile.info -->
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>企业名称
								</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="departmentName"
										data-options="required:true,validType:'length[1,50]'">
								</div>


								<div class="profile-info-name">
									<span class="red">∗</span>企业简称
								</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="shortName"
										data-options="required:true,validType:'length[1,50]'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>所属地区
								</div>

								<div class="profile-info-value">
									<input name="areaCode" class="easyui-combotree"
										data-options="url:'${portalPath}/system/selectProvinceTreeList.do',method:'get',animate: true,
                lines:false,required:true"
										style='width: 200px'>
								</div>

								<div class="profile-info-name">
									<span class="red">∗</span>法人代表姓名
								</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="legalPersonName"
										data-options="required:true,validType:['chinese','length[0,20]']">
								</div>


							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>证件类型
								</div>

								<div class="profile-info-value">
									<label> <input name="legalPersonIdType" type="radio"
										class="ace" checked="checked" value="0" /> <span class="lbl">
											身份证</span>
									</label> <label> <input name="legalPersonIdType" type="radio"
										class="ace" value="1" /> <span class="lbl"> 护照</span>
									</label> <label> <input name="legalPersonIdType" type="radio"
										class="ace" value="2" /> <span class="lbl"> 非大陆人士身份证</span>
									</label>
								</div>
								<div class="profile-info-name">
									<span class="red">∗</span>法人证件号
								</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="legalPersonIdNo"
										data-options="required:true,validType:'length[1,50]'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>经济性质
								</div>

								<div class="profile-info-value">
									<input type="hidden" value="${cfg.sys_dept_id}"
										name="parentDepartmentId"
										>
										<input type="hidden" value="3"
										name="category"
										>
										<input class="easyui-combobox" style="width: 200px"
										name="nature"
										data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=03',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">
								</div>
								<div class="profile-info-name">
									<span class="red">∗</span>营业执照号
								</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="bussLicenseNo"
										data-options="required:true,validType:'length[1,50]'">

								</div>
							</div>
							
							<div class="profile-info-row">
							
							<div class="profile-info-name">
									<span class="red">∗</span>企业类型
								</div>

								<div class="profile-info-value">
									<input class="easyui-combobox" style="width: 200px"
										name="type"
										data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=04',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">
								</div>
								
								<div class="profile-info-name">企业邮箱</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="email"
										data-options="required:false,validType:['email','length[0,30]']">

								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">法人电话</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="legalPersonTel"
										data-options="required:false,validType:'phone'">

								</div>
								<div class="profile-info-name">法人手机号</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="legalPersonMobile"
										data-options="required:false,validType:'mobile'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">法人地址</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="legalPersonAddr"
										data-options="required:false,validType:'length[1,200]'">
								</div>
								
								<div class="profile-info-name">企业传真</div>

								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="fax"
										data-options="required:false,validType:'faxno'">

								</div>
								
								
							</div>
							
							




						</div>

					</div>
				</form>
				<form id="fm-reg2" onsubmit="return reg2Submit()">
				<div class="step-pane" id="step2">


					<!-- #section:pages/profile.info -->
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">
								<span class="red">∗</span>联系人姓名
							</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox" style="width: 200px"
									name="contactName"
									data-options="required:true,validType:['chinese','length[0,20]']">
							</div>
							<div class="profile-info-name">
								<span class="red">∗</span>联系人手机号
							</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox" style="width: 200px"
									name="contactMobile"
									data-options="required:true,validType:'mobile'">
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">联系人电话</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox" style="width: 200px"
									name="contactTel"
									data-options="required:false,validType:'phone'">
							</div>
							<div class="profile-info-name">联系人QQ</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox" style="width: 200px"
									name="contactQq"
									data-options="required:false,validType:'qq'" size="30">
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<span class="red">∗</span>联系人Email
							</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox" style="width: 200px"
									name="contactEmail"
									data-options="required:true,validType:['email','length[0,30]']">

								<button class="btn btn-success" id="btn-share">
									获取验证码<i class="ace-icon fa fa-reply icon-on-right"></i>
								</button>
							</div>
							<div class="profile-info-name">联系人传真</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox" style="width: 200px"
									name="contactFax"
									data-options="required:false,validType:'paxno'">
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<span class="red">∗</span>验证码
							</div>

							<div class="profile-info-value">
								<input class="easyui-validatebox textbox" style="width: 200px" data-rel="tooltip"
									name="checkCode" title="击获取验证码按钮，系统会通过邮箱发送验证码，获取验证码并且正确输入验证码才能注册成功， 验证邮箱地址将作为登录账号!" data-placement="bottom"
									data-options="required:true,validType:'length[1,50]'">
									
									<span class="help-button" data-rel="popover" data-trigger="hover" data-placement="right" data-content="" title="点击获取验证码按钮，系统会通过邮箱发送验证码，获取验证码并且正确输入验证码才能注册成功， 验证邮箱地址将作为登录账号!">?</span>
									

							</div>
							<div class="profile-info-name"></div>

							<div class="profile-info-value"></div>
						</div>


					</div>
					
				</div>
				</form>

				<div class="step-pane" id="step3">

					<div class="center">
						<h4>请提交完成注册,账号信息将发送到<span id="view-email" class="red"></span>,请接收并激活账号</h4>
						<button class="btn btn-success" id="btn-sendRegMail">
									重新发送邮件<i class="ace-icon fa fa-reply icon-on-right"></i>
								</button>
					</div>

				</div>


			</div>


			<div class="modal-footer wizard-actions center">
				<button class="btn btn-prev">
					<i class="ace-icon fa fa-arrow-left"></i> 上一步
				</button>

				<button class="btn btn-success btn-next" id="btn-reg" data-last="提交注册">
					下一步<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</button>
			</div>--%>
		</div>
	</div>

	<jsp:include page="../../common/footer-1-www.jsp" />

	<jsp:include page="../../common/footer-2-www.jsp" />

	<script
		src="${pageContext.request.contextPath}/content/www/js/reg/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/js/jquery.serializeObject.min.js?version=${cfg.version}"></script>
</body>
</html>
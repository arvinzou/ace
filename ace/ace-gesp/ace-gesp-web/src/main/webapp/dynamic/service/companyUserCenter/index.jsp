<%@page import="com.huacainfo.ace.common.tools.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>memberCenter</title>

<jsp:include page="../../common/common.jsp" />
<link rel="stylesheet"
	href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/css/fileupload.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/css/main.css" />
<script type="text/javascript">
	var depatId = '${SESSION_USERPROP_KEY.corpId}';
	var parDeptId = '${SESSION_USERPROP_KEY.parentCorpId}';
</script>
<style>
	.secure-set-list li{
		text-decoration: none;
		list-style: none;
	}
</style>
</head>
<body>
	<div class="page-content">
		<div class="col-xs-12 col-sm-3 center">
			<div>
				<!-- #section:pages/profile.picture -->
				<div id="index_editIma">
					<div style="boder:0px;">
						<div class="profile-picture" class="hide"  align="center"> 
							<img id="index_avatar"
								class="editable img-responsive editable-click editable-empty"
								alt="无图片" src="/portal/content/portal/images/qy.jpg" style="text-align: center;"/>
						</div> <%-- /portal/content/portal/images/-text.png --%>
						<div id="index_updateImg">
							<a href="#" id="index_chIma" class="index_chIma" style="font-size:16px;color:blue;" onClick="javascript:editIma();">修改企业图片</a>
						</div>
					</div>
				</div>
				<!-- /section:pages/profile.picture -->
				<div class="space-4"></div>
				<div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
					<div class="inline position-relative">
						<input type="hidden" id="index_flagId" value="${param.id}" />
						<input type="hidden" id="index_usersId" value="${SESSION_USERPROP_KEY.userId}"/>
						<span class="white" id="level">${SESSION_USERPROP_KEY.corpName}</span>
					</div>
				</div>
			</div>
			<div class="space-6"></div>
			<div align="left">
				登录账号：<span id="index_username">${SESSION_USERPROP_KEY.username}</span>
			</div>
			<div class="space-6"></div>
			<div align="left">
				账号注册时间：<span id="index_regtime"></span>
			</div>
			<div class="space-6"></div>
			<div align="left">
				企业资质认证：<span id="index_qualifications">未认证  
				<a id="btn_updateQua" class="btn btn-info" style="margin-left:8px;" href="javascript:update_qualifiction();">点击进行认证</a></span>
			</div>
			<div class="hr hr12 dotted"></div>
			<div align="left">
				会员等级：<span id="index_level"></span><span id="index_btnLevel"></span>
			</div>
			<div class="hr hr16 dotted"></div>
			<div>
				<input type="hidden" id="index_checkLevel" />
				<span class="help-button" title="">?</span>
				<a href="javascript:index_assregualtions();" ><span></span>了解关于协会与协会会员制度！</a>
			</div>
		</div>

		<div class="col-xs-12 col-sm-9">
			<div id="index_baseInfo" style="display:none;">
				<form id="fm2" onsubmit="return beforeSubmit()">
					<jsp:include page="../memberBaseInfo/index.jsp" />
				</form>
			</div>
			<div id="index_safe" style="display:none;">
				<h3>安全设置</h3>
				<div class="hr hr12 dotted"></div>
				<div style="height:370px;margin-left: 20px; margin-right: 30px;margin-top: 30px;-moz-box-shadow: 0px 1px 1px 1px #888888;box-shadow: 0px 1px 1px 1px #888888;">
					<div class="row" style="height:95px;margin:10px 5px;padding: 30px 5px;">
						<div class="col-md-3">您当前的账号安全程度</div>
						<div class="col-md-4">
							<div style="display:inline;width:50%;"><div id="index_progressbar" class="easyui-progressbar" data-options="value:60" style="width:auto;"></div> </div> 
						</div>
						<div class="col-md-3">
							<div align="center">
								安全级别: <span class="text-warning">中</span>
							</div>
						</div>
						<div class="col-md-2">
							<div class="">
								继续保持
							</div>
						</div>
					</div>
					<div class="hr hr12 dotted"></div>
					<ul class="secure-set-list">
						<li style="height:95px;margin:10px 5px;padding: 30px 5px;">
							<div class="row">
								<div class="col-md-2"><div class="bold">登录密码</div></div>
								<div class="col-md-7"><div class="">
									<span style="color:red;">首次登录请修改默认密码</span>，安全性高的密码可以使帐号更安全。建议您定期更换密码，设置一个包含字母，符号或数字中至少两项且长度超过6位的密码。
								</div></div>
								<div class="col-md-3">
									<div class="text-center">
										<span style="color: #00c600;"><i class="icon-check"></i> 已设置</span> <span class="cheng-text-explode">|</span> <a href="javascript:updatePassword();">修改</a>
									</div>
								</div>
							</div>
						</li>
						<li><div class="hr hr12 dotted"></div></li>
						<li style="height:95px;margin:10px 5px;padding: 30px 5px;">
							<div class="row">
								<div class="col-md-2"><div class="bold">邮箱绑定</div></div>
								<div class="col-md-7">
									<div id="cheng-safe-phone">
										已绑定<span class="cheng-list-icon-done" id="index_safe_emails">suqing@163.com</span>为保障您账户安全，可以通过绑定邮箱找回密码！
									</div>
								</div>
								<div class="col-md-3">
									<div class="text-center">
										<span style="color:#00c600;"><i class="icon-info"></i> 已设置</span> <span class="cheng-text-explode">|</span> <a href="javascript:updateEmail();">修改</a>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<%-- 修改密码 --%>
	<div id="dialog-messagepass" class="hide">
		<form id="fm-password">
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">原密码：</div>
				 	<div class="profile-info-value"><input id="oldpassword" type="password" style="width: 200px;" /></div>
				</div>
			</div>
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">新密码：</div>
				 	<div class="profile-info-value"><input id="password" type="password" style="width: 200px;" /></div>
				</div>
			</div>
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">确认密码： </div>
				 	<div class="profile-info-value"><input id="repassword" type="password" style="width: 200px;" /></div>
				</div>
			</div>
		</form>
	</div>
	

	<%-- 修改邮箱 --%>
	<div id="dialog-messageemail" class="hide">
		<form id="fm1" onsubmit="return beforeSubmit()">
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">新的绑定Email：</div>
					<div class="profile-info-value">
						<input style="width: 155px" id="dia_email" name="email">
						<a class="btn btn-info" id="btn-editDepetInfo" href="javascript:sendCode();" authority="false" style="width:80px;margin-left:10px;" >发送验证码</a>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">验证码：</div>
					<div class="profile-info-value">
						<input style="width: 250px" id="dia_remarkcode" name="remarkcode" />
					</div>
				</div>
			</div>
		</form>
	</div>

	<%-- 修改头像--%>
	<div id="dialog-messageImg" class="hide">
		<form id="update_form" action="${ctx }/Energy/User/updateImage" enctype="multipart/form-data" method="post" target="nm_iframe">
           <div class="form-group" align="center">
               <div id="files-container" class="fileinput fileinput-new">
                   <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                       <img src="/portal/content/portal/images/-text.png" id="update_img"/> </div>
                   <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;"> </div>
                   <div style="color:red;">只支持格式为jpg,gif,png的图片</div>
                   <div align="center" style="margin-top:10px;">
                       <span class="btn default btn-file">
                           <span id="fileinput-new"> 选择图片 </span>
                           <span class="fileinput-exists" id="fileinput-list"> 选择图片 </span>
                           <input type="file" name="file" id="btn-fileImages"> 
						   <div id="fileTarget" style="display: none;"></div>
                       </span>
                   </div>
               </div>
           </div>
       </form>
	</div>





<%-- 修改头像--%>
	<%-- <div id="dialog-messageImg" class="hide">
		<form id="update_form" action="${ctx }/Energy/User/updateImage" enctype="multipart/form-data" method="post" target="nm_iframe">
           <div class="form-group" align="center">
               <div id="files-container" class="fileinput fileinput-new">
                   <div class="fileinput-new thumbnail" id="dia_img" align="center">
                       <img src="/portal/content/portal/images/-text.png" id="update_img"/> </div>
                   <div class="fileinput-preview fileinput-exists thumbnail"> </div>
                   <div align="center" style="margin-top:10px;">
                       <span class="btn default btn-file">
                           <span id="fileinput-new"> 选择图片 </span>
                           <span class="fileinput-exists" id="fileinput-list"> 选择图片 </span>
                           <input type="file" name="file" id="btn-fileImages"> 
						   <div id="fileTarget" style="display: none;"></div>
                       </span>
                   </div>
               </div>
           </div>
       </form>
       <input type="hidden" id="dataWidth"  type="text" placeholder="width">
       <input type="hidden" id="dataHeight" type="text" placeholder="height">
	</div> --%>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/companyUserCenter/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberBaseInfo/controller.js?version=${cfg.version}"></script>
	<%-- <script
		src="${pageContext.request.contextPath}/content/service/companyUserCenter/ajaxfileupload.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/companyUserCenter/cropbox.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/companyUserCenter/gethead.js?version=${cfg.version}"></script> --%>
	<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
	<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
	<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberBaseInfo/upload.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/js/jquery.serializeObject.min.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		window.onresize = function() {
			autoSize();
		}
		parent.onresize = function() {
			autoSize();
		}
		function autoSize() {
			parent.autoWidth();
		}
	</script>
</body>
</html>


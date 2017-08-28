<%@page import="com.huacainfo.ace.common.tools.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>memberCenter</title>
</head>
<jsp:include page="../../../common/common.jsp" />
<script type="text/javascript">
	var depatId = "  ";
	var parDeptId = '${SESSION_USERPROP_KEY.parentCorpId}';
</script>
<body>
	<div class="page-content">
		<div class="col-xs-12 col-sm-12">
			<h2 align="center">深圳市公路货运与物流行业协会入会申请表</h2>
			<div class="tab-content no-border padding-24">
				<form id="update_company">
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">拟入社团名称:</div>
						<div class="profile-info-value">深圳市公路货运与物流行业协会</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">申请单位名称:</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								id="departmentName" name="departmentName"
								data-options="required:true,validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">工商登记号:</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="bussNum" id="bussNum"
								data-options="required:true,validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">成立时间:</div>
						<div class="profile-info-value">
							<input class="easyui-datebox" style="width: 200px" name="regDate"
								id="regDate" data-options="required:true,validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">机构代码:</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="orgCode" id="orgCode"
								data-options="required:true,validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">注册资金:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								id="regCapital" name="regCapital"
								data-options="required:true,validType:'length[1,50]'">万元
						</div>
						<div class="profile-info-name">资产总额:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								name="assetTotal" id="assetTotal"
								data-options="required:true,validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">净资产:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								id="netAsset" name="netAsset"
								data-options="required:true,validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">职工人数:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								name="employeesNum" id="employeesNum"
								data-options="required:true,validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">上年销售额:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								id="yearSale" name="yearSale"
								data-options="required:true,validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">上年纳税额:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								name="yearShall" id="yearShall"
								data-options="required:true,validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">自有车辆数:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								id="ownerVehicle" name="ownerVehicle"
								data-options="validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">协议车辆数:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								name="vehicleNum" id="vehicleNum"
								data-options="validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">企业类型:</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								id="departType" name="departType"
								data-options="validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">自有仓储面积:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								name="ownStorageArea" id="ownStorageArea"
								data-options="validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">协议仓储面积:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								id="storageArea" name="storageArea"
								data-options="validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">注册所在地:</div>
						<div class="profile-info-value">
							<input style="width: 90px;" name="regPro" id="regPro"> <input
								style="width: 90px;" name="regCity" id="regCity"> <input
								style="width: 90px;" name="regAreaCode" id="regAreaCode">
							<input class="easyui-validatebox textbox" style="width: 100px"
								name="regAddr" id="regAddr"
								data-options="required:true,validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">全国网点数:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								id="intnetNum" name="intnetNum"
								data-options="required:true,validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">办公地址:</div>
						<div class="profile-info-value">
							<input style="width: 90px;" name="bussPro" id="bussPro">
							<input style="width: 90px;" name="bussCity" id="bussCity">
							<input style="width: 90px;" name="bussAreaCode" id="bussAreaCode">
							<input class="easyui-validatebox textbox" style="width: 100px"
								name="bussAddr" id="bussAddr"
								data-options="required:true,validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">主要负责人:</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								id="mainPeo" name="mainPeo"
								data-options="required:true,validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">职务:</div>
						<div class="profile-info-value">
							<input style="width: 200px" data-options="required:true"
								name="mainPost" id="mainPost">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">座机:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								id="mainPhone" name="mainPhone"
								data-options="required:true,validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">邮箱:</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="mainEmail" id="mainEmail"
								data-options="required:true,validType:['email','length[0,30]']">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">手机号码:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								name="mainTel" id="mainTel"
								data-options="required:true,validType:'mobile'">
						</div>
						<div class="profile-info-name">公司网址:</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="companyIp" id="companyIp"
								data-options="required:true,validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">经营路线:</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="ruote" id="ruote"
								data-options="required:true,validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">主要联系人:</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="contactName" id="contactName"
								data-options="required:true,validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">职务:</div>
						<div class="profile-info-value">
							<input style="width: 200px" data-options="required:true"
								name="contactPost" id="contactPost">
						</div>
						<div class="profile-info-name">座机:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								name="contactTel" id="contactTel"
								data-options="required:true,validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">邮箱:</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="contactEmail" id="contactEmail"
								data-options="required:true,validType:['email','length[0,30]']">
						</div>
						<div class="profile-info-name">手机号码:</div>
						<div class="profile-info-value">
							<input class="easyui-numberbox" style="width: 200px"
								name="contactTel" id="contactTel"
								data-options="required:true,validType:'mobile'">
						</div>
					</div>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-name">经营范围:</div>
						<div>
							<textarea cols="120" rows="5" name="scopeBuss" id="scopeBuss"></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="space-6"></div>
			<div
				style="border: 1px solid #888; margin-top: 10px; margin-bottom: 10px; margin-left: 10px; padding: 10px 0px;">
				注：1.请务必填完整表里的内容,以方便协会更准确、快速的将信息传达到企业；
				</p>
				2.此表格需要下载打印，一式二份，单位负责人签名，加盖公司公章。
			</div>
			<div class="space-6"></div>
		</div>
		<div class="space-12"></div>
		<div align="center">
			<a class="btn btn-info" id="btn-editDepetInfo" href="#"
				authority="false"
				style="width: 100px; font-size: 16px; text-align: 'center'"
				onclick="javascript:updateDepartQual_save('#update_company')">提交</a>
		</div>
	</div>

	<jsp:include page="../../../common/footer-1.jsp" />
	<jsp:include page="../../../common/footer-2.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/companyUserCenter/view.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/companyUserCenter/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/js/jquery.serializeObject.min.js?version=${cfg.version}"></script>
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
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%-- <!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>memberBaseInfo</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">
	var memberCode = '${SESSION_USERPROP_KEY.corpId}';
</script>
<link rel="stylesheet"
	href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css" media="screen" />
<body>
	<div class="page-content"> --%>
		<%--<ul class="nav nav-tabs padding-18">
			<li id="li_tab6" class="active"><a data-toggle="tab" href="#tab6"> <i
					class="green ace-icon fa fa-leaf bigger-120"></i> 基本信息
			</a></li>

			<li id="li_tab7" class=""><a data-toggle="tab" href="#tab7"> <i
					class="green ace-icon fa fa-user bigger-120"></i> 联系人信息
			</a></li>
			<li id="li_tab8" class=""><a data-toggle="tab" href="#tab8"> <i
					class="green ace-icon fa fa-address-card-o bigger-120"></i> 法人信息
			</a></li>

			<li id="li_tab9" class=""><a data-toggle="tab" href="#tab9"> <i
					class="green ace-icon fa fa-th-large bigger-120"></i> 其他信息
			</a></li>
			<li id="li_tab10" class=""><a data-toggle="tab" href="#tab10"> <i
					class="green ace-icon fa fa-graduation-cap bigger-120"></i> 企业认证
			</a></li>
		</ul>
		<div class="tab-content no-border padding-24">
			<form id="updateForm" method="post" novalidate>
				<div id="tab6" class="tab-pane active">
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>企业名称
								</div>
								<div class="profile-info-value">
									<input type="hidden" name="departmentId" id="departmentId" value="${param.deptId}"/>
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="departmentName" data-options="required:true,validType:'length[1,50]'">
								</div>
								<div class="profile-info-name">
									企业简称
								</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="shortName"
										data-options="validType:'length[1,50]'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>所属地区
								</div>
								<div class="profile-info-value">
									<input name="areaCode" class="easyui-combotree"
										data-options="url:'${portalPath}/system/selectProvinceTreeList.do',method:'post',animate: true,
	                								  lines:false,required:true"
										style='width: 200px'>
								</div>
								<div class="profile-info-name">企业传真</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="fax" data-options="required:false,validType:'faxno'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>所属行业协会
								</div>
								<div class="profile-info-value">
									<input class="easyui-combobox" style="width: 200px"
										name="parentDepartmentId" readOnly="true"
										data-options="url:'${portalPath}/dict/findListByCategoryId.do?categoryId=65',
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
								<div class="profile-info-name">企业邮箱</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="email"
										data-options="required:false,validType:['email','length[0,30]']">
								</div>
								<div class="profile-info-name">
									<span class="red">∗</span>经济性质
								</div>
								<div class="profile-info-value">
									<input class="easyui-combobox" style="width: 200px"
										name="nature"
										data-options="
						                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=03',
						                    method:'get',
						                    valueField:'code',
						                    textField:'name',
						                    panelHeight:'auto'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">注册时间</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox easyui-datebox"
										style="width: 200px" name="regDate"
										data-options="required:false,validType:['date']">
	
								</div>
								<div class="profile-info-name">注册资本</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="regCapital"
										data-options="required:false,validType:'intOrFloat'">万元
								</div>
							</div>
						</div>
						<div align="center" class="update-button" style="margin-top:25px;">
							<a class="btn btn-info" id="btn-editDepetInfo" href="#" authority="false" style="width:90px;font-size:16px;" 
								onclick="javascript:updateDepartInfo_save('#fm2','2')">提交</a>
						</div>
				</div>
				<div id="tab7" class="tab-pane">
						<button class="btn btn-info" id="btn-view-add-contact" authority="false" style="margin-bottom:6px;">
							<i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right">添加联系人</i>
						</button>
						<table id="tableContact" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center">姓名</th>
									<th class="center">职位</th>
									<th class="center">手机</th>
									<th class="center">邮箱</th>
									<th class="center">电话</th>
									<th class="center">QQ</th>
									<th class="center">操作</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
				</div>
				<div id="tab8" class="tab-pane">
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>法人代表姓名
								</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="legalPersonName"
										data-options="required:true,validType:['chinese','length[0,20]']">
								</div>
								<div class="profile-info-name">法人手机号</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="legalPersonMobile"
										data-options="required:false,validType:'mobile'">
								</div>
							 </div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>证件类型
								</div>
								<div class="profile-info-value" id="update_legalPersonIdType">--%>
									<%-- <label> <input name="legalPersonIdType" type="radio"
										class="ace" checked="checked" value="0" /><span class="lbl ">
											身份证</span>
									</label> <label> <input name="legalPersonIdType" type="radio"
										class="ace" value="1" /> <span class="lbl"> 护照</span>
									</label> <label> <input name="legalPersonIdType" type="radio"
										class="ace" value="2" /> <span class="lbl "> 非大陆人士身份证</span>
									</label> --%>
								<%--</div>
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
								<div class="profile-info-name">法人电话</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="legalPersonTel"
										data-options="required:false,validType:'phone'">
								</div>
							</div>
						</div>
						<div align="center" class="update-button" style="margin-top:25px;">
							<a class="btn btn-info" id="btn-editDepetInfo" href="#" authority="false" style="width:90px;font-size:16px;" 
								onclick="javascript:updateDepartInfo_save('#fm2','2')">提交</a>
						</div>
					<!-- </form> -->
				</div>
				<div id="tab9" class="tab-pane">
					<!-- <form id="fm-reg4" action="#" onsubmit="return reg4Submit()"> -->
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name">注册区号</div>
								<div class="profile-info-value">
									<input name="regAreaCode" class="easyui-combotree"
										data-options="url:'${portalPath}/system/selectProvinceTreeList.do',method:'get',animate: true,
	                								  lines:false,required:false"
										style='width: 200px'>
								</div>
								<div class="profile-info-name">注册地址</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="regAddr"
										data-options="required:false,validType:'length[1,200]'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">经营区号</div>
								<div class="profile-info-value">
									<input name="bussAreaCode" class="easyui-combotree"
										data-options="url:'${portalPath}/system/selectProvinceTreeList.do',method:'get',animate: true,
	                							      lines:false,required:false"
										style='width: 200px'>
								</div>
								<div class="profile-info-name">经营地址</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="bussAddr"
										data-options="required:false,validType:'length[1,200]'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">道路运输经营许可证号</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="transBussLicenseNo"
										data-options="required:false,validType:'length[1,200]'">
								</div>
								<div class="profile-info-name">许可证有效期</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox easyui-datebox"
										style="width: 200px" name="transBussLicenseValidDate"
										data-options="required:false,validType:['date']">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">道路运输经营许可证经营范围</div>
								<div id="update_transBussScope" class="profile-info-value">
									<%-- <input class="easyui-validatebox textbox" style="width: 200px"
										name="transBussScope"
										data-options="required:false,validType:'length[1,200]'"> --%>
								<%--</div>
								<div class="profile-info-name">业务性质</div>
								<div id="update_businessClassify" class="profile-info-value">--%>
									 <%-- <input class="easyui-validatebox textbox" style="width: 200px"
										name="transBussScope"
										data-options="required:false,validType:'length[1,200]'"> --%>
								<%--</div> 
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">从业人员数</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="employeesNum"
										data-options="required:false,validType:'integer'">
								</div>
								<div class="profile-info-name">车辆数</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="driverNum"
										data-options="required:false,validType:'integer'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">交通部门批文号</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="transDepartApprovalNo"
										data-options="required:false,validType:'length[1,200]'">
								</div>
							</div>--%>
							<%-- <div class="profile-info-row">
								<div class="profile-info-name">无证人数</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="unlicensedDriverNum"
										data-options="required:false,validType:'integer'">
								</div>
								<div class="profile-info-name">持证人数</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="licensedDriverNum"
										data-options="required:false,validType:'integer'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">安全事故情况</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="accidentOfYear"
										data-options="required:false,validType:'length[1,200]'">
								</div>
								<div class="profile-info-name">投诉处方情况</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="complaintsRemark"
										data-options="required:false,validType:'length[1,200]'">
								</div>
							</div>
							<div class="profile-info-row" >
								<div class="profile-info-name">企业图片</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px" type="hidden"
										name="simage" id="simage" data-options="required:false,validType:'length[1,200]'">
									<span id="filelist"></span>
									<span id="container">
										<a id="pickfiles" href="javascript:;">[选择图片]</a> <a
											id="uploadfiles" href="javascript:;">[上传]</a>
									</span>
								</div>
							</div> --%>
						<!-- </div> -->
						<%-- <br>
						<pre id="console"></pre> --%>
						<%--<div align="center" class="update-button" style="margin-top:25px;">
							<a class="btn btn-info" id="btn-editDepetInfo" href="#" authority="false" style="width:90px;font-size:16px;" 
								onclick="javascript:updateDepartInfo_save('#fm2','2')">提交</a>
						</div>
					<!-- </form> -->
				</div>
			</form>
			<div id="tab10" class="tab-pane">
				<div class="update-button">
					<div id="table10_content" style="text-align: center;color:red;font-size:16px;">尚未完成企业认证</div>
					<div id="fuelux-wizard" data-target="#step-container">
						<ul class="wizard-steps">
							<li id="step1" class="active"><span class="step">1</span>
								<span class="title">上传资质认证材料</span></li>
							<li id="step2" class=""><span class="step">2</span> <span
								class="title">人工审核</span></li>
							<li id="step3" class=""><span class="step">3</span> <span
								class="title">认证结果</span></li>
						</ul>
					</div>
					<div class="hr hr12 dotted"></div>
					<span style="color:orange;">注：按要求完成以下材料的上传，并全部获得审核通过，才能完成企业资质认证，完成企业资质认证能获得更多服务与权限。</span>
					<div class="space-6"></div>
				</div>
				<table id="table10" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center">序号</th>
							<th>材料名称</th>
							<th>备注</th>
							<th>状态</th>
							<th>材料范本</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<%-- <pre id="console2"></pre> --%>
				<%--<div align="center" id="update-hiddenSubmit" class="update-button" style="margin-top:25px;">
					<input type="hidden" id="update-quali" />
					<a class="btn btn-info" id="btn-editDepetInfo" href="javascript:updateDepartFiles_save();" 
						authority="false" style="width:120px;font-size:16px;">提交审核</a>
				</div>
			</div>
		</div>--%>
		
	
	
		<ul class="nav nav-tabs padding-18">
			<li id="li_tab6" class="active"><a data-toggle="tab" href="#tab6"> <i
					class="green ace-icon fa fa-leaf bigger-120"></i> 营业信息
			</a></li>

			<li id="li_tab7" class=""><a data-toggle="tab" href="#tab7"> <i
					class="green ace-icon fa fa-user bigger-120"></i> 经营信息
			</a></li>
			<%-- <li id="li_tab8" class=""><a data-toggle="tab" href="#tab8"> <i
					class="green ace-icon fa fa-user bigger-120"></i> 联系人信息
			</a></li> --%>
			<li id="li_tab9" class=""><a data-toggle="tab" href="#tab9"> <i
					class="green ace-icon fa fa-graduation-cap bigger-120"></i> 企业实名认证
			</a></li>
		</ul>
		<div class="tab-content no-border padding-24">
			<form id="updateForm" method="post" novalidate>
				<div id="tab6" class="tab-pane active">
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>企业名称
								</div>
								<div class="profile-info-value">
									<input type="hidden" name="departmentId" id="departmentId" value="${param.deptId}"/>
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="departmentName" data-options="required:true,validType:'length[1,50]'">
								</div>
								<div class="profile-info-name">
									企业简称
								</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="shortName"
										data-options="validType:'length[1,50]'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>营业执照号
								</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="bussLicenseNo"
										data-options="required:true,validType:'length[1,50]'">
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
								<div class="profile-info-value" id="update_legalPersonIdType">
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
								<div class="profile-info-name">企业成立日期</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox easyui-datebox"
										style="width: 200px" name="regDate"
										data-options="required:false,validType:['date']">
	
								</div>
								<div class="profile-info-name">注册资本</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="regCapital"
										data-options="required:false,validType:'intOrFloat'">万元
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">
									<span class="red">∗</span>所属地区
								</div>
								<div class="profile-info-value">
									<input name="areaCode" class="easyui-combotree"
										data-options="url:'${portalPath}/system/selectProvinceTreeList.do',method:'post',animate: true,
	                								  lines:false,required:true"
										style='width: 200px'>
								</div>
								<div class="profile-info-name">企业传真</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="fax" data-options="required:false,validType:'faxno'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">企业邮箱</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="email"
										data-options="required:false,validType:['email','length[0,30]']">
								</div>
								<div class="profile-info-name">企业电话</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="telphone" data-options="required:false">
								</div>
							</div>
						</div>
						<div align="center" class="update-button" style="margin-top:25px;">
							<a class="btn btn-info" id="btn-editDepetInfo" href="#" authority="false" style="width:90px;font-size:16px;" 
								onclick="javascript:updateDepartInfo_save('#fm2','2')">提交</a>
						</div>
				</div>
				<div id="tab7" class="tab-pane">
					<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name">道路运输经营许可证号</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="transBussLicenseNo"
										data-options="required:false,validType:'length[1,200]'">
								</div>
								<div class="profile-info-name">经营地址</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="bussAddr"
										data-options="required:false,validType:'length[1,200]'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">经营许可证有效期</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox easyui-datebox"
										style="width: 200px" name="transBussLicenseValidDate"
										data-options="required:false,validType:['date']">
								</div>
								<div class="profile-info-name">
									<span class="red">∗</span>经济性质
								</div>
								<div class="profile-info-value">
									<input class="easyui-combobox" style="width: 200px"
										name="nature"
										data-options="
						                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=03',
						                    method:'get',
						                    valueField:'code',
						                    textField:'name',
						                    panelHeight:'auto'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">从业人员数</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="employeesNum"
										data-options="required:false,validType:'integer'">
								</div>
								<div class="profile-info-name">车辆数</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="driverNum"
										data-options="required:false,validType:'integer'">
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">交通部门批文号</div>
								<div class="profile-info-value">
									<input class="easyui-validatebox textbox" style="width: 200px"
										name="transDepartApprovalNo"
										data-options="required:false,validType:'length[1,200]'">
								</div>
								<div class="profile-info-name">经营范围</div>
								<div id="update_transBussScope" class="profile-info-value">
								</div>
							</div>
						</div>
						<div align="center" class="update-button" style="margin-top:25px;">
							<a class="btn btn-info" id="btn-editDepetInfo" href="#" authority="false" style="width:90px;font-size:16px;" 
								onclick="javascript:updateDepartInfo_save('#fm2','2')">提交</a>
						</div>
				</div>
			</form>
			<%-- <div id="tab8" class="tab-pane">
				<button class="btn btn-info" id="btn-view-add-contact" authority="false" style="margin-bottom:6px;">
					<i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right">添加联系人</i>
				</button>
				<table id="tableContact" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center">姓名</th>
							<th class="center">职位</th>
							<th class="center">手机</th>
							<th class="center">邮箱</th>
							<th class="center">电话</th>
							<th class="center">QQ</th>
							<th class="center">操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div> --%>
			<div id="tab9" class="tab-pane">
				<div class="update-button">
					<div id="table9_content" style="text-align: center;color:red;font-size:16px;">尚未完成企业实名认证</div>
					<div id="fuelux-wizard" data-target="#step-container">
						<ul class="wizard-steps">
							<li id="step1" class="active"><span class="step">1</span>
								<span class="title">上传企业实名认证材料</span></li>
							<li id="step2" class=""><span class="step">2</span> <span
								class="title">人工审核</span></li>
							<li id="step3" class=""><span class="step">3</span> <span
								class="title">认证结果</span></li>
						</ul>
					</div>
					<div class="hr hr12 dotted"></div>
					<span style="color:orange;">注：按要求完成以下材料的上传，并全部获得审核通过，才能完成企业实名认证，完成企业实名认证能获得更多服务与权限。</span>
					<div class="space-6"></div>
				</div>
				<table id="table9" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center">序号</th>
							<th>材料名称</th>
							<th>备注</th>
							<th>状态</th>
							<th>材料范本</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div align="center" id="update-hiddenSubmit" class="update-button" style="margin-top:25px;">
					<input type="hidden" id="update-quali" />
					<a class="btn btn-info" id="btn-editDepetInfo" href="javascript:updateDepartFiles_save('${SESSION_USERPROP_KEY.parentCorpId}','${SESSION_USERPROP_KEY.corpId}');" 
						authority="false" style="width:120px;font-size:16px;">提交审核</a>
				</div>
			</div>
		</div>
		
	<%-- 修改联系人信息 --
	<div id="dialog-messageEditContact" class="hide">
		<input type="hidden" id="edit_memberCode" />
		<input type="hidden" id="edit_deptCode" />
		<form id="editMessContact" onsubmit="return beforeSubmit()">
			<jsp:include page="../deptContact/contact.jsp" />
		</form>
	</div>
	
	<！--新增联系人信息 -->	
	<div id="dialog-messageAddContact" class="hide">
		<form id="addContactInfo" method="post" novalidate>
			<jsp:include page="../deptContact/addContact.jsp"></jsp:include>
		</form>
	</div>%>
	
	<%-- </div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/memberBaseInfo/controller.js?version=${cfg.version}"></script>
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
</html> --%>
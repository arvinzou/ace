<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>industryResourceCar</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">
	var id = '${param.id}';
</script>
<body>
	<div class="page-content">
		<ul class="nav nav-tabs padding-18">
			<li id="active_tab1" class="active"><a data-toggle="tab" href="#tab1"> <i
					class="blue ace-icon fa fa-bars bigger-120"></i> 车辆基础信息
			</a></li>
			<li id="active_tab1" class=""><a data-toggle="tab" href="#tab2"> <i
					class="blue ace-icon fa fa-bars bigger-120"></i>车辆技术档案
			</a></li>
		</ul>
		<div class="tab-content no-border padding-24">
			<div id="tab1" class="tab-pane active">
				<fieldset>
					<legend>行驶信息</legend>
					<div class="profile-user-info profile-user-info-striped control-group">
						<div class="profile-info-row">
							<div class="profile-info-name">车辆牌号</div>
							<div class="profile-info-value">
								<span id="plateNo"></span>
							</div>
							<div class="profile-info-name">车牌颜色</div>
							<div class="profile-info-value">
								<span id="color"></span>
							</div>
							<div class="profile-info-name">车牌类型</div>
							<div class="profile-info-value">
								<span id="carBigType"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">所有人</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="companyName"></span>
							</div>
							<div class="profile-info-name">品牌型号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="brandName"></span>
							</div>
							<div class="profile-info-name">使用性质</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="managementStatus"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">车辆识别代码</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="vin"></span>
							</div>
							<div class="profile-info-name">发动机号码</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="engineNumber"></span>
							</div>
							<div class="profile-info-name">注册日期</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="changeDate"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">准牵引总质量</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="approvedTotalMass"></span>
							</div>
							<div class="profile-info-name">核定载人数</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="approvedQualityCrew"></span>
							</div>
							<div class="profile-info-name">总质量</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="totalMass"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">整备质量</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="crew"></span>
							</div>
							<div class="profile-info-name">核定载质量</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="approvedQualityCrew"></span>
							</div>
							<div class="profile-info-name">外观尺寸</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="dimensions"></span>
							</div>
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>运营信息</legend>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">道路运输号</div>
							<div class="profile-info-value">
								<span id="roadTransportCertificate"></span>
							</div>
							<div class="profile-info-name">核发机关</div>
							<div class="profile-info-value">
								<span id="certCompanyName"></span>
							</div>
							<div class="profile-info-name">发证时间</div>
							<div class="profile-info-value">
								<span id="initDrivingLicDate"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">车辆尺寸</div>
							<div class="profile-info-value">
								<span id="dimensions"></span>
							</div>
							<div class="profile-info-name">吨(座)位</div>
							<div class="profile-info-value">
								<span id="approvedTon"></span>
							</div>
							<div class="profile-info-name"></div>
							<div class="profile-info-value">
								<span id="ww"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">经营范围</div>
							<div style="line-height:25px;height:25px;">
								<span id="managementScope"></span>
							</div>
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>车辆商业保险信息</legend>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">保险公司</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="insuranceCompany"></span>
							</div>
							<div class="profile-info-name">保额</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="coverage"></span>
							</div>
							<div class="profile-info-name">投保时间</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="insuranceDate"></span>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
			<div id="tab2" class="tab-pane">
			<iframe id="techDocIframe" width="100%" height="490px" frameborder="0" scrolling="auto"  ></iframe>
			</div>
			<%--<div id="tab2" class="tab-pane">
				<fieldset>
					<legend>综合性能检测信息</legend>
					<div>
						<table id="tableContactDe" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center">检测单号</th>
									<th class="center">车牌号</th>
									<th class="center">车牌颜色</th>
									<th class="center">检测站</th>
									<th class="center">检测类别</th>
									<th class="center">检测结果</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">检测单号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="checkTicketsNo"></span>
							</div>
							<div class="profile-info-name">车牌号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="plateNo"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">车牌颜色</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="color"></span>
							</div>
							<div class="profile-info-name">检测站</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="checkCompany"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">检测类别</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="checkType"></span>
							</div>
							<div class="profile-info-name">检测结果</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="checkResult"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">检测日期</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="checkDate"></span>
							</div>
							<div class="profile-info-name">审核结果</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="auditResult"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">审核人</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="auditUserName"></span>
							</div>
							<div class="profile-info-name">审核单位</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="auditDepartment"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">审核日期</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="auditDate"></span>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
			<div id="tab3" class="tab-pane">
				<fieldset>
					<legend>二级维护信息</legend>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">检测单号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="repairTicketsNo"></span>
							</div>
							<div class="profile-info-name">车牌号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="plateNo"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">车牌颜色</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="color"></span>
							</div>
							<div class="profile-info-name">检测站</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="repairCompany"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">检测类别</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="repairType"></span>
							</div>
							<div class="profile-info-name">检测结果</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="repairResult"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">检测日期</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="repairDate"></span>
							</div>
							<div class="profile-info-name">审核结果</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="auditResult"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">审核人</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="auditUserName"></span>
							</div>
							<div class="profile-info-name">审核单位</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="auditDepartment"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">审核日期</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="auditDate"></span>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
			<div id="tab4" class="tab-pane">
				<fieldset>
					<legend>违法违章信息</legend>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">执法机关</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="Organization"></span>
							</div>
							<div class="profile-info-name">案件编号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="Code"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">企业名称</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="companyName"></span>
							</div>
							<div class="profile-info-name">车牌号码</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="plateNo"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">车牌颜色</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="color"></span>
							</div>
							<div class="profile-info-name">案由</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="Name"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">罚款金额</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="Amerce"></span>
							</div>
							<div class="profile-info-name">违法时间</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="Time"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">违法地点</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="BelongPlace"></span>
							</div>
							<div class="profile-info-name">当前状态</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="Tache"></span>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
			--%>
		</div>
	</div>

	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/managerCarInfo/preview.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
    <script>
    $(function(){
    	var plateNo = '${param.plateNo}';
    	var color = '${param.color}';
    	$("#techDocIframe").height($(window).height()-120);
    	$("#techDocIframe").attr("src",contextPath+'/dynamic/service/managerCarInfo/techdoc.jsp?plateNo='+plateNo+'&color='+color);
    });
    </script>
</body>
</html>
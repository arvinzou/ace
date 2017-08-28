<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<ul class="nav nav-tabs padding-18">
				<li class="active"><a data-toggle="tab" href="#tab1"> <i
						class="green ace-icon fa fa-leaf bigger-120"></i> 营业信息
				</a></li>
				<li class=""><a data-toggle="tab" href="#tab2"> <i
						class="green ace-icon fa fa-user bigger-120"></i> 经营信息
				</a></li>
				<li class=""><a data-toggle="tab" href="#tab3"> <i
						class="green ace-icon fa fa-address-card-o bigger-120"></i> 联系人信息
				</a></li> 
				<li class=""><a data-toggle="tab" href="#tab4"> <i
						class="green ace-icon fa fa-graduation-cap bigger-120"></i> 企业认证
				</a></li>
			</ul>
			<div class="tab-content no-border padding-24">
				<div id="tab1" class="tab-pane active">
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">企业名称</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="departmentName"></span>
							</div>
							<div class="profile-info-name">企业简称</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="shortName"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">营业执照号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="bussLicenseNo"></span>
							</div>
							<div class="profile-info-name">法人代表姓名</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="legalPersonName"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">证件类型</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="legalPersonIdType"></span>
							</div>
							<div class="profile-info-name">法人证件号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="legalPersonIdNo"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">企业成立日期</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="regDate"></span>
							</div>
							<div class="profile-info-name">注册资本</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="regCapital"></span>万
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">注册地址</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="regAddr"></span>
							</div>
							<div class="profile-info-name">企业传真</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="fax"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">企业邮箱</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="email"></span>
							</div>
							<div class="profile-info-name">企业电话</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="telphone"></span>
							</div>
						</div>
					</div>
				</div>
				<div id="tab2" class="tab-pane">
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">道路运输经营许可证号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="transBussLicenseNo"></span>
							</div>
							<div class="profile-info-name">经营地址</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="bussAddr"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">经营许可证有效期</div>
							<div class="profile-info-value">
								<span class="editable editable-click"
									id="transBussLicenseValidDate"></span>
							</div>
							<div class="profile-info-name">经济性质</div>
	
							<div class="profile-info-value">
								<span class="editable editable-click" id="nature"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">从业人员数</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="employeesNum"></span>
							</div>
							<div class="profile-info-name">车辆数</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="driverNum"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">交通部门批文号</div>
							<div class="profile-info-value">
								<span class="editable editable-click"
									id="transDepartApprovalNo"></span>
							</div>
							<div class="profile-info-name">经营范围</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="transBussScope"></span>
							</div>
						</div>
					</div>
				</div>
				<div id="tab3" class="tab-pane">
					<div class="profile-user-info profile-user-info-striped">
						<table id="tableContactDe" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center">姓名</th>
									<th class="center">职位</th>
									<th class="center">手机</th>
									<th class="center">邮箱</th>
									<th class="center">电话</th>
									<th class="center">QQ</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div id="tab4" class="tab-pane">
					<table id="table4"
						class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center">序号</th>
								<th>材料名称</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			
			
<%-- 			<ul class="nav nav-tabs padding-18">
				<li class="active"><a data-toggle="tab" href="#tab1"> <i
						class="green ace-icon fa fa-leaf bigger-120"></i> 基本信息
				</a></li>
				<li class=""><a data-toggle="tab" href="#tab2"> <i
						class="green ace-icon fa fa-user bigger-120"></i> 联系人信息
				</a></li>
				<li class=""><a data-toggle="tab" href="#tab3"> <i
						class="green ace-icon fa fa-address-card-o bigger-120"></i> 法人信息
				</a></li>
				<li class=""><a data-toggle="tab" href="#tab4"> <i
						class="green ace-icon fa fa-th-large bigger-120"></i> 其他信息
				</a></li>
				<li class=""><a data-toggle="tab" href="#tab5"> <i
						class="green ace-icon fa fa-graduation-cap bigger-120"></i> 行业资质
				</a></li>
			</ul>
			<div class="tab-content no-border padding-24">
				<div id="tab1" class="tab-pane active">
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">企业名称</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="departmentName"></span>
							</div>
							<div class="profile-info-name">企业简称</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="shortName"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">所属地区</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="areaName"></span>
							</div>
							<div class="profile-info-name">企业传真</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="fax"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">所属行业协会</div>
							<div class="profile-info-value">
								<span class="editable editable-click"
									id="parementDepartmentName"></span>
							</div>
							<div class="profile-info-name">营业执照号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="bussLicenseNo"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">经济性质</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="nature"></span>
							</div>
							<div class="profile-info-name">企业邮箱</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="email"></span>

							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">注册时间</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="regDate"></span>
							</div>
							<div class="profile-info-name">注册资本</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="regCapital"></span>万
							</div>
						</div>
					</div>
				</div>
				<div id="tab2" class="tab-pane">
					<div class="profile-user-info profile-user-info-striped">
						<table id="tableContactDe" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center">姓名</th>
									<th class="center">职位</th>
									<th class="center">手机</th>
									<th class="center">邮箱</th>
									<th class="center">电话</th>
									<th class="center">QQ</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div id="tab3" class="tab-pane">
						#section:pages/profile.info
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name">法人代表姓名</div>
								<div class="profile-info-value">
									<span class="editable editable-click" id="legalPersonName"></span>
								</div>
								<div class="profile-info-name">法人手机号</div>

								<div class="profile-info-value">
									<span class="editable editable-click" id="legalPersonMobile"></span>
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">证件类型</div>
								<div class="profile-info-value">
									<span class="editable editable-click" id="legalPersonIdType"></span>
								</div>
								<div class="profile-info-name">法人证件号</div>
								<div class="profile-info-value">
									<span class="editable editable-click" id="legalPersonIdNo"></span>
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">法人电话</div>
								<div class="profile-info-value">
									<span class="editable editable-click" id="legalPersonTel"></span>
								</div>
							</div>
							<div class="profile-info-row"></div>
							<div class="profile-info-row"></div>
						</div>
				</div>
				<div id="tab4" class="tab-pane">
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name">注册区号</div>

								<div class="profile-info-value">
									<span class="editable editable-click" id="regAreaCode"></span>

								</div>
								<div class="profile-info-name">注册地址</div>

								<div class="profile-info-value">
									<span class="editable editable-click" id="regAddr"></span>
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">经营区号</div>
								<div class="profile-info-value">
									<span class="editable editable-click" id="bussAreaCode"></span>
								</div>
								<div class="profile-info-name">经营地址</div>
								<div class="profile-info-value">
									<span class="editable editable-click" id="bussAddr"></span>
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">道路运输经营许可证号</div>

								<div class="profile-info-value">
									<span class="editable editable-click" id="transBussLicenseNo"></span>

								</div>
								<div class="profile-info-name">许可证有效期</div>

								<div class="profile-info-value">
									<span class="editable editable-click"
										id="transBussLicenseValidDate"></span>
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">交通部门批文号</div>

								<div class="profile-info-value">
									<span class="editable editable-click"
										id="transDepartApprovalNo"></span>
								</div>
								<div class="profile-info-name">经营范围</div>

								<div class="profile-info-value">
									<span class="editable editable-click" id="transBussScope"></span>

								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">从业人员数</div>

								<div class="profile-info-value">
									<span class="editable editable-click" id="employeesNum"></span>
								</div>
								<div class="profile-info-name">车辆数</div>

								<div class="profile-info-value">
									<span class="editable editable-click" id="driverNum"></span>

								</div>
							</div>
						</div>
				</div>

				<div id="tab5" class="tab-pane">
					<table id="table1"
						class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center">序号</th>
								<th>材料名称</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div> --%>
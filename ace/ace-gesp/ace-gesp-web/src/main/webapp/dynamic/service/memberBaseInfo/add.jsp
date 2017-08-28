<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<ul class="nav nav-tabs padding-18">
		<li class="active"><a data-toggle="tab" href="#tab12"> <i
				class="green ace-icon fa fa-leaf bigger-120"></i>  营业信息
		</a></li>

		<li class=""><a data-toggle="tab" href="#tab13"> <i
				class="green ace-icon fa fa-user bigger-120"></i> 经营信息
		</a></li>
		<%-- <li class=""><a data-toggle="tab" href="#tab14"> <i
				class="green ace-icon fa fa-address-card-o bigger-120"></i> 联系人信息
		</a></li>

		<li class=""><a data-toggle="tab" href="#tab15"> <i
				class="green ace-icon fa fa-th-large bigger-120"></i> 企业认证
		</a></li> --%>
	</ul>
	<div class="tab-content no-border padding-24">
		<form id="updateForm" method="post" novalidate>
			<div id="tab12" class="tab-pane active">
				<div class="profile-user-info profile-user-info-striped">
					<div class="profile-info-row">
						<div class="profile-info-name">
							<span class="red">∗</span>企业名称
						</div>
						<div class="profile-info-value">
							<input type="hidden" name="departmentId" id="add_departmentId" value=""/>
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="departmentName" data-options="required:true,validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">企业简称</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="shortName" data-options="validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">
							<span class="red">∗</span>营业执照号
						</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="bussLicenseNo" data-options="required:true,validType:'length[1,50]'">
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
						<div class="profile-info-value" id="add_legalPersonIdType">
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
								data-options="url:'${portalPath}/system/selectProvinceTreeList.do',method:'post',animate: true,lines:false,required:true"
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
								name="email" data-options="required:false,validType:['email','length[0,30]']">
						</div>
						<div class="profile-info-name">企业电话</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
										name="telphone" data-options="required:false">
						</div>
					</div>
				</div>
			</div>
			<div id="tab13" class="tab-pane">
				<div class="profile-user-info profile-user-info-striped">
					<div class="profile-info-row">
						<div class="profile-info-name">
							<span class="red">∗</span>道路运输经营许可证号
						</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="transBussLicenseNo" data-options="required:true,validType:'length[1,200]'">
						</div>
						<div class="profile-info-name">经营地址</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="bussAddr" data-options="required:false,validType:'length[1,200]'">
						</div>
					</div>
					<div class="profile-info-row">	
						<div class="profile-info-name">
							<span class="red">∗</span>许可证有效期
						</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox easyui-datebox"
								style="width: 200px" name="transBussLicenseValidDate"
								data-options="required:true,validType:['date']">
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
								name="employeesNum" data-options="required:false,validType:'integer'">
						</div>
						<div class="profile-info-name">车辆数</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="driverNum" data-options="required:false,validType:'integer'">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">交通部门批文号</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="transDepartApprovalNo"
								data-options="required:false,validType:'length[1,200]'">
						</div>
						<div class="profile-info-name">道路运输经营许可证经营范围</div>
						<div id="add_transBussScope" class="profile-info-value"></div>
					</div>
				</div>
			</div>
			<%-- <div id="tab14" class="tab-pane">
				<button class="btn btn-info" id="btn-view-addContact" authority="false" style="margin-bottom:6px;">
					<i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right">添加联系人</i>
				</button>
				<table id="addTableContact" class="table table-striped table-bordered table-hover">
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
		</form>
	</div>
	
	
	
	
	
	<%-- <ul class="nav nav-tabs padding-18">
		<li class="active"><a data-toggle="tab" href="#tab12"> <i
				class="green ace-icon fa fa-leaf bigger-120"></i> 基本信息
		</a></li>

		<li class=""><a data-toggle="tab" href="#tab13"> <i
				class="green ace-icon fa fa-user bigger-120"></i> 联系人信息
		</a></li>
		<li class=""><a data-toggle="tab" href="#tab14"> <i
				class="green ace-icon fa fa-address-card-o bigger-120"></i> 法人信息
		</a></li>

		<li class=""><a data-toggle="tab" href="#tab15"> <i
				class="green ace-icon fa fa-th-large bigger-120"></i> 其他信息
		</a></li>
	</ul>
	<div class="tab-content no-border padding-24">
		<form id="updateForm" method="post" novalidate>
			<div id="tab12" class="tab-pane active">
				<div class="profile-user-info profile-user-info-striped">
					<div class="profile-info-row">
						<div class="profile-info-name">
							<span class="red">∗</span>企业名称
						</div>
						<div class="profile-info-value">
							<input type="hidden" name="departmentId" id="add_departmentId" value=""/>
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="departmentName" data-options="required:true,validType:'length[1,50]'">
						</div>
						<div class="profile-info-name">企业简称</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="shortName" data-options="validType:'length[1,50]'">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">
							<span class="red">∗</span>所属地区
						</div>
						<div class="profile-info-value">
							<input name="areaCode" class="easyui-combotree"
								data-options="url:'${portalPath}/system/selectProvinceTreeList.do',method:'post',animate: true,lines:false,required:true"
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
							<span class="red">∗</span>营业执照号
						</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="bussLicenseNo" data-options="required:true,validType:'length[1,50]'">
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
						<div class="profile-info-name">企业邮箱</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="email" data-options="required:false,validType:['email','length[0,30]']">
						</div>
						<div class="profile-info-name">注册时间</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox easyui-datebox"
								style="width: 200px" name="regDate"
								data-options="required:false,validType:['date']">

						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">注册资本</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="regCapital"
								data-options="required:false,validType:'intOrFloat'">万元
						</div>
					</div>
				</div>
			</div>
			<div id="tab13" class="tab-pane">
				<button class="btn btn-info" id="btn-view-addContact" authority="false" style="margin-bottom:6px;">
					<i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right">添加联系人</i>
				</button>
				<table id="addTableContact" class="table table-striped table-bordered table-hover">
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
			<div id="tab14" class="tab-pane">
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
						<div class="profile-info-value" id="add_legalPersonIdType">
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
						<div class="profile-info-name">法人电话</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="legalPersonTel"
								data-options="required:false,validType:'phone'">
						</div>
					</div>
				</div>
			</div>
			<div id="tab15" class="tab-pane">
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
								name="regAddr" data-options="required:false,validType:'length[1,200]'">
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
								name="bussAddr" data-options="required:false,validType:'length[1,200]'">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">
							<span class="red">∗</span>道路运输经营许可证号
						</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="transBussLicenseNo" data-options="required:true,validType:'length[1,200]'">
						</div>
						<div class="profile-info-name">
							<span class="red">∗</span>许可证有效期
						</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox easyui-datebox"
								style="width: 200px" name="transBussLicenseValidDate"
								data-options="required:true,validType:['date']">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">道路运输经营许可证经营范围</div>
						<div id="add_transBussScope" class="profile-info-value"></div>
						<div class="profile-info-name">业务性质</div>
						<div id="add_businessClassify" class="profile-info-value"></div> 
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">从业人员数</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="employeesNum" data-options="required:false,validType:'integer'">
						</div>
						<div class="profile-info-name">车辆数</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="driverNum" data-options="required:false,validType:'integer'">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">交通部门批文号</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 200px"
								name="transDepartApprovalNo"
								data-options="required:false,validType:'length[1,200]'">
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
		
	修改联系人信息
	<div id="dialog-messageEditDeContact" class="hide">
		<form id="editDeMessContact"  method="post" novalidate>
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">姓名</div>
					<div class="profile-info-value">
						<input class="easyui-validatebox textbox" id="editDe_name" name="name" data-options="required:true" style="width: 260px;line-height: 25px;height: 25px;"/>
					</div>
					<div class="profile-info-name">QQ</div>
					<div class="profile-info-value">
						<input class="easyui-textbox"id="editDe_qq" name="qq" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">职务</div>
					<div class="profile-info-value">
						<input id="editDe_userLevel" name="userLevel" style="width: 260px;line-height: 25px;height: 25px;"/>
					</div>
					<div class="profile-info-name">手机号</div>
					<div class="profile-info-value">
						<input class="easyui-validatebox textbox" id="editDe_mobile" name="mobile" data-options="required:true" style="width: 260px;line-height: 25px;height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">电话号码</div>
					<div class="profile-info-value">
						<input class="easyui-textbox" id="editDe_telphone" name="telphone" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
					<div class="profile-info-name">传真</div>
					<div class="profile-info-value">
						<input class="easyui-textbox" id="editDe_fax" name="fax" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">邮箱</div>
					<div class="profile-info-value">
						<input class="easyui-textbox" id="editDe_email" name="email" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<%--新增联系人信息
	<div id="dialog-messageAddDeContact" class="hide">
		<form id="addDeContactInfo" method="post" novalidate>
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">姓名</div>
					<div class="profile-info-value">
						<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="width: 260px;line-height: 25px;height: 25px;"/>
					</div>
					<div class="profile-info-name">QQ</div>
					<div class="profile-info-value">
						<input name="qq" class="easyui-textbox" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">职务</div>
					<div class="profile-info-value">
						<input name="userLevel" id="addDe_userLevel" data-options="required:true" style="width: 260px;line-height: 25px;height: 25px;"/>
					</div>
					<div class="profile-info-name">手机号</div>
					<div class="profile-info-value">
						<input class="easyui-validatebox textbox" name="mobile" data-options="required:true" style="width: 260px;line-height: 25px;height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">电话号码</div>
					<div class="profile-info-value">
						<input class="easyui-textbox" name="telphone" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
					<div class="profile-info-name">传真</div>
					<div class="profile-info-value">
						<input name="fax" class="easyui-textbox" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">邮箱</div>
					<div class="profile-info-value">
						<input name="email" class="easyui-textbox" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
			</div>
		</form>
	</div> --%>

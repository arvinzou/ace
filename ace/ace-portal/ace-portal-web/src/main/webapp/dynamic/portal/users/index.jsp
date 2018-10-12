<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<title>用户管理</title>
	</head>
	<jsp:include page="/dynamic/common/header.jsp" />
	<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />
	<body>
		<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
		<div class="portlet light ">
			<div class="portlet-body">
				<div class="row custom-toolbar">
					<div class="col-md-6">
						<button type="button" class="btn  green" id="btn-view-add" authority="${pageContext.request.contextPath}/users/insertUsers.do"></button>
					</div>


					<div class="col-md-6">
						<form action="#" id="fm-search">
							<div class="input-group" style="float:left">
								归属
								<input name="departmentId" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath}/system/selectDepartmentTreeList.do?id=01',method:'get',animate: true,
                lines:false,"
								 style='width: 250px;height:30px;line-height:30px'>
							</div>
							<div class="input-group" style="float:right;width:250px">
								<input type="text" name="name" class="form-control" placeholder="请输入姓名">
								<span class="input-group-btn">
									<button class="btn  btn-default search_btn" id="btn-search" authority="${pageContext.request.contextPath}/users/findUsersList.do">
										搜索
									</button>
								</span>
							</div>
						</form>
					</div>

				</div>

				<table id="grid-table"></table>

				<div class="paginationbar">
					<ul id="grid-pager" class="pagination"></ul>
				</div>
			</div>
		</div>

		<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


		<div class="modal fade" role="dialog" id="modal-role">
			<div class="modal-dialog" role="document" style="width: 830px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">分配角色</h4>
					</div>
					<div class="modal-body">
						<table>
							<tr>
								<td align="center" valign="top">
									<table id="allrole-grid-table"></table>
									<div id="allrole-grid-pager"></div>
								</td>
								<td align="center" valign="middle">
									<button class="btn btn-info" id="btn-view-da-add" authority="${pageContext.request.contextPath}/users/insertUsersRole.do">
										分配<i class="ace-icon fa   fa-angle-double-right  align-middle bigger-125 icon-on-right"></i>
									</button>
									<div style="height:3px"></div>
									<button class="btn btn-info" id="btn-view-da-del" authority="${pageContext.request.contextPath}/users/insertUsersRole.do">
										移除<i class="ace-icon fa  fa-angle-double-left  align-middle bigger-125 icon-on-right"></i>
									</button>
								</td>
								<td align="center" valign="top">
									<table id="myrole-grid-table"></table>
									<div id="myrole-grid-pager"></div>
								</td>
							</tr>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
						<button type="button" class="btn green" authority="false" id="btn-view-save">确定</button>
					</div>
				</div>
			</div>
		</div>


		<div class="modal fade" role="dialog" id="modal-passwd">
			<div class="modal-dialog" role="document" style="width: 830px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">重置密码</h4>
					</div>
					<div class="modal-body">
						<form action="/users/updateUsersForInitPassword.do" id="fm-initpwd">
							<fieldset>
								初始密码： <input id="init_passwd" type="password" style="width: 200px;" />
							</fieldset>
							<fieldset>
								重复输入： <input id="init_rpasswd" type="password" style="width: 200px;" />
							</fieldset>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
						<button type="button" class="btn green" authority="false" id="btn-view-passwd">确定</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" role="dialog" id="modal-user1">
			<div class="modal-dialog" role="document" style="width:90%">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">微信</h4>
					</div>
					<div class="modal-body">
						<div>
							昵称： <input type="text" name="q" id="q1" />
							<button class="btn btn-info" id="btn-search-weixin" authority="false">
								查询
								<i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
							</button>
						</div>
						<div id="box-weixin">

						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="/dynamic/common/footer.jsp" />
		<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
		<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
		<script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
		<script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>

		<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
		<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/portal/js/users/config.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/portal/js/users/model.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/portal/js/users/controller.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/portal/js/users/view.js?version=${cfg.version}"></script>
		<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>



	</body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>

<script
		src="${pageContext.request.contextPath}/content/common/assets/js/gz/jquery-ui.min.js?version=${cfg.version}"></script>
<script
	src="${pageContext.request.contextPath}/content/common/assets/js/gz/bootstrap.min.js?version=${cfg.version}"></script>
<script
	src="${pageContext.request.contextPath}/content/common/assets/js/gz/bootbox.min.js?version=${cfg.version}"></script>

<script
	src="${pageContext.request.contextPath}/content/common/assets/js/date-time/bootstrap-datepicker.min.js?version=${cfg.version}"></script>

<script
	src="${pageContext.request.contextPath}/content/common/assets/js/gz/jquery.jqGrid.js?version=${cfg.version}"></script>
<script
	src="${pageContext.request.contextPath}/content/common/assets/js/uncompressed/jqGrid/ui.multiselect.js?version=${cfg.version}"></script>
	
<script
	src="${pageContext.request.contextPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>

<!- 新增部分开始-->


<script src="${pageContext.request.contextPath}/content/common/assets/pages/scripts/ui-modals.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/portal/js/main/menu5.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/portal/js/main/portal5.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>
<!- 新增部分结束-->

<script type="text/javascript">
		function checkPwd() {
			var passwd = '${SystemUser.users.password}';
			if (passwd == '4297F44B13955235245B2497399D7A93'
					|| passwd == '4297f44b13955235245b2497399d7a93') {
				alert("${SystemUser.users.name}，你好！您目前使用的是默认密码为了确保安全，请您及时更换密码！");
				$('#stack1').modal('show')
			}
		}

</script>

<style>
 .page-content-wrapper .page-content {
    margin-left: 200px;
    margin-top: 0;
    min-height: 600px;
    padding: 0px 0px 0px;
}
.page-sidebar {
    width: 200px;
    float: left;
    position: relative;
    margin-right: -100%;
}
.tabs-header, .tabs-tool {
    background-color: #ffffff;
}
a, button, code, div, img, input, label, li, p, pre, select, span, svg, table, td, textarea, th, ul {
    -webkit-border-radius: 0!important;
    -moz-border-radius: 0!important;
    border-radius: 2px!important;
}
.page-header.navbar.navbar-fixed-top, .page-header.navbar.navbar-static-top {
    z-index: 90;
}
</style>

<div id="stack1" class="modal fade" tabindex="-1" data-width="300">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">密码修改</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form id="fm-password">
							<fieldset>
								新设密码： <input id="password" type="password" style="width: 200px;"/>
							</fieldset>
							<fieldset>
								重复输入： <input id="repassword" type="password" style="width: 200px;"/>
							</fieldset>
						</form>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn blue" onclick="submitform()">提交</button>
				<button type="button" data-dismiss="modal" class="btn red">取消</button>
			</div>
		</div>
	</div>
</div>
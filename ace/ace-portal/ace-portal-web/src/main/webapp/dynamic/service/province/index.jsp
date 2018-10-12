<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="viewport"
		  content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<title>系统配置</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

	<div class="portlet-body">

		<div class="row custom-toolbar">
			<div class="col-md-6">

				<button type="button" class="btn  green" id="btn-view-add" authority="${portalPath}/province/insertProvince.do"></button>



			</div>


			<div class="col-md-6">

				<form action="#" id="fm-search">

							<div class="input-group" style="width:55%;float:left">
								上级区划
								<input id="cc1" name="parent_code"
									   class="easyui-combotree form-control"
									   data-options="url:'${portalPath}/system/selectProvinceTreeList.do',method:'get',animate: true,
                lines:false,"
									   style='width: 200px; line-height: 30px; height: 30px;'>
								<a
										href="javascript:clearQparams()">清除</a>
							</div>


							<div class="input-group" style="width:45%;float:right">

								<input type="text"
									   name="name"
									   class="form-control"
									   placeholder="请输入名称">

								<span class="input-group-btn">
									<button class="btn  btn-default search_btn"  id="btn-search"
											authority="${portalPath}/province/findProvinceList.do">
											搜索
									</button>
								</span>
							</div>


				</form>
			</div>

		</div>

		<table id="grid-table"></table>

		<div class="paginationbar"><ul id="grid-pager" class="pagination"></ul></div>
	</div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<jsp:include page="/dynamic/common/footer.jsp" />

<link rel="stylesheet" type="text/css"
	  href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
	  href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
		src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
		src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>


<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
<script
		src="${portalPath}/content/service/province/config.js?version=${cfg.version}"></script>
<script
		src="${portalPath}/content/service/province/model.js?version=${cfg.version}"></script>
<script
		src="${portalPath}/content/service/province/controller.js?version=${cfg.version}"></script>
<script
		src="${portalPath}/content/service/province/view.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>


<style>

</style>
</body>
</html>
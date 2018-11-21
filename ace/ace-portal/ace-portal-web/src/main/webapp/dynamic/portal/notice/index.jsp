<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="viewport"
		  content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<title>公告管理</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

	<div class="portlet-body">

		<div class="row custom-toolbar">
			<div class="col-md-4">

				<button type="button" class="btn  green" id="btn-view-add" authority="${pageContext.request.contextPath}/notice/insertNotice.do"></button>




			</div>


			<div class="col-md-8">
				<form action="#" id="fm-search" >

					<div class="btn-group" role="group"  style="float:left;padding-right:5px">
						<button type="button" authority="false" class="btn btn-default"  onclick="setParams('category','');">全部</button>
						<button type="button" authority="false" class="btn btn-default"  onclick="setParams('category','1');">系统</button>
						<button type="button" authority="false" class="btn btn-default" onclick="setParams('category','2');">业务</button>
					</div>
					<div class="input-group">
						<input type="text"
							   name="title"
							   class="form-control"
							   placeholder="请输入标题">
						<span class="input-group-btn">
							<button class="btn  btn-default search_btn"  id="btn-search"
									authority="${pageContext.request.contextPath}/notice/findNoticeList.do">
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

<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/notice/config.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/notice/model.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/notice/controller.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/notice/view.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>



</body>
</html>
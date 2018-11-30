<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>议题</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />

<style>

.dataTables_filter{
    float:right;
}

.dataTables_paginate {
    text-align: right;
}
.dataTables_paginate .pagination {
    margin: 0 12px;
}
.pagination > li > a, .pagination > li > span {
    padding: 1px 3px;
    line-height: 0.8;
}
.pagination > .active > a, .pagination > .active > a:hover, .pagination > .active > a:focus, .pagination > .active > span, .pagination > .active > span:hover, .pagination > .active > span:focus {
    color:#1890ff;
    background-color:#fff;
    border-color:#fff;
}
.pagination > li > a, .pagination > li > span {
    color: #1a9fff;
    background-color: #fff;
    border: 0px solid #fff;
    margin-left: -1px;
}
.pagination > li > a:hover, .pagination > li > a:focus, .pagination > li > span:hover, .pagination > li > span:focus {
    background-color: #1a9fff;
    border-color: #fff;
    color: #fff;
}
.table>tbody>tr>td {
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 6px;
}

.block__list {
    padding: 20px 0;
    max-width: 360px;
    margin-top: -8px;
    margin-left: 5px;
    background-color: #fff;
}

ul {
    margin: 0;
    padding: 0;
    list-style: none;
}
.block__list_words li {
    background-color: #fff;
    padding: 10px 40px;
}
.block__list li {
    cursor: move;
}
</style>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

	<div class="portlet-body">

		<div class="row custom-toolbar">
			<div class="col-md-4">

				<button type="button" class="btn  green" id="btn-view-add" authority="${pageContext.request.contextPath}/topic/insertTopic.do"></button>



			</div>
			<div class="col-md-4">
			</div>

			<div class="col-md-4">

				<form action="#" id="fm-search" >


					<div class="input-group">
						<input type="text"
							   name="name"
							   class="form-control"
							   placeholder="请输入名称">
						<span class="input-group-btn">
                                                                <button class="btn  btn-default search_btn"  id="btn-search"
																		authority="${pageContext.request.contextPath}/topic/findTopicList.do">
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
<script
		src="${portalPath}/content/common/assets/global/plugins/sortable/Sortable.min.js?version=${cfg.version}"></script>
<script
		src= "${portalPath}/content/common/js/dataTable/jquery.dataTables.min.js?version=${cfg.version}"></script>
<script
		src= "${portalPath}/content/common/js/dataTable/dataTables.bootstrap.min.js?version=${cfg.version}"></script>

<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
	<script
			src="${portalPath}/content/common/js/xcheck/XCheck.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/topic/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/topic/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/topic/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/topic/view.js?version=${cfg.version}"></script>




<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>



<div class="modal fade"  role="dialog" id="modal-norm">
	<div class="modal-dialog" role="document" style="width: 80%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">指标列表</h4>
			</div>
			<div class="modal-body">
				<table id="detail"
					   class="table  table-bordered table-hover">
					<thead>
					<tr>
						<th class="center" style="width:80px;">序号</th>
						<th>指标名称</th>
						<th>说明</th>
					</tr>
					</thead>
					<tbody>
					</tbody>
				</table>

			</div>
			<div class="modal-footer">
				<button type="button" authority="false" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>





<div class="modal fade"  role="dialog" id="modal-norm-cfg">
	<div class="modal-dialog" role="document" style="width: 80%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">配置指标</h4>
			</div>
			<div class="modal-body">


			</div>
			<div class="modal-footer">
				<button type="button" authority="false" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" authority="false" class="btn green"  id="btn-norm-cfg-submit">确定</button>
			</div>
		</div>
	</div>
</div>


<div class="modal fade"  role="dialog" id="modal-sort">
	<div class="modal-dialog" role="document" style="width: 80%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">指标排序</h4>
			</div>
			<div class="modal-body">
				<ul class="sortable block__list" id="sortable">

				</ul>

			</div>
			<div class="modal-footer">
				<button type="button" authority="false" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

	<style type="text/css">
			*{padding:0;margin:0;}
			ul li{list-style:none;}
			.ulinput{margin-left:50px;}

.checkboxitem {
	width: 250px;
	height: 20px;
	float: left;
	margin: 2px 2px 2px;
}
		</style>

	<div id="dialog-sort" class="hide">
		<div class="sortable">
			<li class="dd-handle"  id="1">第1项</li>
			<li class="dd-handle"  id="2" >第2项</li>
			<li class="dd-handle"  id="3">第3项</li>
		</div>
	</div>
</body>
</html>
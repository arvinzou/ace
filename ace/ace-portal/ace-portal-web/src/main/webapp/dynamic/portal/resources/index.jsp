<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>资源管理</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
<style type="text/css">
		.excel{ background-color:#999; font-size:13px;}
		.excel td{ background-color:#fff; white-space:nowrap;}
		.excel th{ background-color:#E7E7E7; font-weight:normal;}
</style>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

	<div class="portlet-body">

		<div class="row custom-toolbar">
			<div class="col-md-4">


				<button class="btn green" id="btn-view-add" authority="${pageContext.request.contextPath}/resources/insertResources.do">

				</button>

				<button class="btn green" id="btn-view-sort" authority="false">
					排序
				</button>
				<button class="btn green" id="btn-view-import" authority="${pageContext.request.contextPath}/resources/insertResources.do">
					Excel导入
				</button>



			</div>
			<div class="col-md-2">
			</div>

			<div class="col-md-6">

				<form action="#" id="fm-search" >

					<div class="input-group" style="float:left">
						上级资源：
						<input id="cc1" name="parentResourcesId" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath}/resources/selectResourcesTreeList.do?id=01',method:'get',animate: true,
                lines:true," style='width:200px;line-height: 30px;height: 30px;'>
						<a href="javascript:clearQparams()" style="padding-left:10px">清除</a>
					</div>




					<div class="input-group" style="float:right;width:220px">
						<input type="text"
							   name="resourcesName"
							   class="form-control"
							   placeholder="请输入名称">
						<span class="input-group-btn">
							<button class="btn  btn-default search_btn"  id="btn-search"
									authority="${pageContext.request.contextPath}/resources/findResourcesList.do">
									搜索
							</button>
						</span>
					</div>
				</form>
			</div>

		</div>


		<div class="easyui-layout" id="cc" style="width:100%;height:400px">



			<div data-options="region:'center',border:false,fit:true" id="easyui-center">
					<table id="grid-table"></table>
					<div style="text-align:left">
						<ul id="grid-pager" class="pagination"></ul>
					</div>
			</div>
		<div id="cc-west" class="easyui-west" data-options="region:'west',split:true" title="我的树" style="width:200px;">
		 <ul id="tt" class="easyui-tree" data-options="
               url: '${pageContext.request.contextPath}/resources/selectResourcesTreeList.do?id=01',
                method: 'get',
                animate: true,
                lines:false,
                onContextMenu: function(e,node){
                    e.preventDefault();
                    $(this).tree('select',node.target);
                    autotreeq(node);
                    $('#mm').menu('show',{
                        left: e.pageX,
                        top: e.pageY
                    });
                }
            "></ul>

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



<div id="mm" class="easyui-menu" style="width:120px;">
        <div onclick="treeappend()" data-options="iconCls:'icon-add'">添加</div>
        <div onclick="treeedit()" data-options="iconCls:'icon-edit'">编辑</div>
        <div onclick="treeremove()" data-options="iconCls:'icon-remove'">删除</div>
        <div class="menu-sep"></div>
        <div onclick="treereload()" data-options="iconCls:'icon-refresh'">刷新</div>
        <div onclick="expand()">展开</div>
        <div onclick="collapse()">收回</div>
    </div>


		<script
				src="${pageContext.request.contextPath}/content/common/assets/global/plugins/sortable/Sortable.min.js?version=${cfg.version}"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>

		<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>


		<script
				src="${pageContext.request.contextPath}/content/portal/js/resources/config.js?version=${cfg.version}"></script>
		<script
				src="${pageContext.request.contextPath}/content/portal/js/resources/model.js?version=${cfg.version}"></script>
		<script
				src="${pageContext.request.contextPath}/content/portal/js/resources/controller.js?version=${cfg.version}"></script>
		<script
				src="${pageContext.request.contextPath}/content/portal/js/resources/view.js?version=${cfg.version}"></script>
		<script
				src="${pageContext.request.contextPath}/content/portal/js/resources/upload.js?version=${cfg.version}"></script>

		<div class="modal fade"  role="dialog" id="modal-upload">
			<div class="modal-dialog" role="document" style="width: 90%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close"  authority="false" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Excel导入</h4>
					</div>
					<div class="modal-body">
						<div id="uploader">
						</div>
						<div style="margin:5px">
							<a href="rs.xls" style="color:red">下载模板</a>.<br>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
						<button type="button" class="btn green upload" authority="false">确定</button>
					</div>
				</div>
			</div>
		</div>


		<div class="modal fade"  role="dialog" id="modal-sort">
			<div class="modal-dialog" role="document" style="width: 90%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button"  authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">排序</h4>
					</div>
					<div class="modal-body">
						<div class="sortable" id="sortable">
							<li class="dd-handle"  id="1">第1项</li>
							<li class="dd-handle"  id="2" >第2项</li>
							<li class="dd-handle"  id="3">第3项</li>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
						<button type="button" class="btn green upload" authority="false">确定</button>
					</div>
				</div>
			</div>
		</div>





<style>
.panel-header {
    background-color: #edf2f74f;
}
.panel-header, .panel-body {
    border-color: #edf2f74f;
}
.panel-header {
    padding: 9px;
}
</style>


</body>
</html>
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
<link rel="stylesheet" href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

	<div class="portlet-body">

		<div class="row custom-toolbar">
			<div class="col-md-4">

				<button type="button" class="btn  green" id="btn-view-add" authority="${portalPath}/dict/insertDict.do"></button>
				<button type="button" class="btn  green" id="btn-view-deploy" authority="${portalPath}/dict/deploy.do"></button>
				<button type="button" class="btn  green" id="btn-view-import" authority="false">Excel导入</button>


			</div>
			<div class="col-md-2">
			</div>

			<div class="col-md-6">

				<form action="#" id="fm-search" >

					<div class="input-group" style="width:55%;float:left">
						字典类型
						<input class="easyui-combobox" style="width:200px;line-height: 30px; height: 30px;"
							   name="categoryId"
							   data-options="
                    url:'${portalPath}/dictCategory/findDictCategoryListAll.do',
                    method:'get',
                    valueField:'categoryId',
                    textField:'name',
                    panelHeight:'200'">

					</div>


					<div class="input-group" style="width:45%;float:right">

						<input type="text"
							   name="name"
							   class="form-control"
							   placeholder="请输入名称">

						<span class="input-group-btn">
									<button class="btn  btn-default search_btn"  id="btn-search"
											authority="${portalPath}/dict/findDictList.do">
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


<div class="modal fade"  role="dialog" id="modal-upload">
	<div class="modal-dialog" role="document" style="width: 90%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
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

<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>
<script
		src="${portalPath}/content/portal/js/dict/upload.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/portal/js/dict/config.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/portal/js/dict/model.js?version=${cfg.version}"></script>
<script
		src="${portalPath}/content/portal/js/dict/controller.js?version=${cfg.version}"></script>
<script
		src="${portalPath}/content/portal/js/dict/view.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>


<style>

</style>
</body>
</html>
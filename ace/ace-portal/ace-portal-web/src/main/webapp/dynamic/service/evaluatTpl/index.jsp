<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>评测</title>
    <link rel="stylesheet" href="${portalPath}/content/common/assets/css/colorbox.css"/>
    <link rel="stylesheet"
          href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
          type="text/css" media="screen"/>

</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">


</script>
<body>
	<div class="page-content">
		<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">设置查询条件</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">
					<form action="#" id="fm-search">
					
				类别：<input
							class="easyui-combobox" style="width: 200px" name="category"
							data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=69&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                     名称： <input name="name" type="text"
                                        							style="width: 200px;" />
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/evaluatTpl/findEvaluatTplList.do">
							 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

						
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">

						
						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/evaluatTpl/insertEvaluatTpl.do">
							 <i
								class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/evaluatTpl/updateEvaluatTpl.do">
							 <i
								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/evaluatTpl/deleteEvaluatTplByEvaluatTplId.do">
							 <i
								class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
						</button>
						
					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>
		
		
	</div>
	<div id="dialog-message" class="hide">
        		<div id="uploader">
        			<p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
        		</div>
    </div>
    <div id="dialog-message-file" class="hide">
        <div id="load" class="loading"></div>
    </div>

    <div id="dialog-message-view" class="hide">
<h5 class="header-title">基本信息</h5><div  class="row"  style="padding:10px">
<div class="labelItem"><span class="labelItemHeader">
主键</span>
<br>
<span id="id">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
名称</span>
<br>
<span id="name">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
类型</span>
<br>
<span id="category">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
封面</span>
<br>
<span id="cover">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
二维码</span>
<br>
<span id="qrcoteUrl">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
测评介绍</span>
<br>
<span id="introduce">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
测评须知</span>
<br>
<span id="notice">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
原价</span>
<br>
<span id="originalCost">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
折扣价</span>
<br>
<span id="discountCost">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
满分值</span>
<br>
<span id="score">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
状态</span>
<br>
<span id="status">
</span>
</div>
</div>
<h5 class="header-title">操作信息</h5><div  class="row"  style="padding:10px">
<div class="labelItem"><span class="labelItemHeader">
创建人编号</span>
<br>
<span id="createUserId">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
创建人姓名</span>
<br>
<span id="createUserName">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
入库日期</span>
<br>
<span id="createDate">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
最后更新人编码</span>
<br>
<span id="lastModifyUserId">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
最后更新人姓名</span>
<br>
<span id="lastModifyUserName">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
最后更新时间</span>
<br>
<span id="lastModifyDate">
</span>
</div>
</div>

    </div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/evaluatTpl/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/evaluatTpl/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/evaluatTpl/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/evaluatTpl/view.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
	//parent.autoWidth();
}
</script>


    <script src="${pageContext.request.contextPath}/content/portal/js/dict/upload.js?version=${cfg.version}"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>


</body>
</html>
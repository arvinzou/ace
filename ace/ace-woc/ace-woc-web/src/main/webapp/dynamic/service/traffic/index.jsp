<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>案件审核记录</title>
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
							authority="${pageContext.request.contextPath}/traffic/findTrafficList">
							 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

						
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">

						
						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/traffic/insertTraffic">
							 <i
								class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/traffic/updateTraffic">
							 <i
								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/traffic/deleteTrafficByTrafficId">
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
记录卡点</span>
<br>
<span id="siteId">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
记录监控点</span>
<br>
<span id="monitorSiteId">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
检查时间</span>
<br>
<span id="inspectTime">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
地点</span>
<br>
<span id="locale">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
车牌号</span>
<br>
<span id="plateNo">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
方向</span>
<br>
<span id="direction">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
轴数</span>
<br>
<span id="axleCount">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
总重量</span>
<br>
<span id="totalMass">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
超限</span>
<br>
<span id="overMass">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
超限率</span>
<br>
<span id="overRate">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
速度</span>
<br>
<span id="speed">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
备注</span>
<br>
<span id="remark">
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
最后更新人编号</span>
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
		src="${pageContext.request.contextPath}/content/service/traffic/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/traffic/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/traffic/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/traffic/view.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
	parent.autoWidth();
}
</script>
</body>
</html>
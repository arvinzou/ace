<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>resources</title>
</head>
<jsp:include page="../../common/common.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
<style type="text/css">
		.excel{ background-color:#999; font-size:13px;}
		.excel td{ background-color:#fff; white-space:nowrap;}
		.excel th{ background-color:#E7E7E7; font-weight:normal;}
</style>
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
					上级资源： 
							<input id="cc1" name="parentResourcesId" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath}/resources/selectResourcesTreeList.do?id=01',method:'get',animate: true,
                lines:true," style='width:200px;line-height: 25px;height: 25px;'>
							<a href="javascript:clearQparams()">清除</a>
						资源名称： <input name="resourcesName" type="text" maxlength="20"
							style="width: 200px;"/>
								<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/resources/findResourcesList.do">
							<i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/resources/insertResources.do">
							<i
								class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/resources/updateResources.do">
							<i
								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/resources/deleteResourcesByResourcesId.do">
							<i
								class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-sort"
                            authority="false">
                            排序<i
                                class="ace-icon fa fa-sort  align-middle bigger-125 icon-on-right"></i>
                        </button>
						<button class="btn btn-info" id="btn-view-import"
							authority="${pageContext.request.contextPath}/resources/insertResources.do">
							Excel导入<i
								class="ace-icon glyphicon glyphicon-upload  align-middle bigger-125 icon-on-right"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
	<div class="easyui-layout" id="cc" style="width:100%;height:300px;">
	
		

			<div data-options="region:'center',border:false,fit:true" id="easyui-center">
			<table id="grid-table"></table>
	
			<div id="grid-pager"></div>
		</div>
		<div id="cc-west" class="easyui-west" data-options="region:'west',split:true" title="我的树" style="width:200px;">
		 <ul id="tt" class="easyui-tree" data-options="
               url: '${pageContext.request.contextPath}/resources/selectResourcesTreeList.do?id=01',
                method: 'get',
                animate: true,
                lines:true,
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
		<!--  
		<div data-options="region:'south',split:true" style="height:50px;"></div>
        <div data-options="region:'east',split:true" title="East" style="width:100px;"></div>
        -->
</div>
	</div>

<div id="mm" class="easyui-menu" style="width:120px;">
        <div onclick="treeappend()" data-options="iconCls:'icon-add'">添加</div>
        <div onclick="treeedit()" data-options="iconCls:'icon-edit'">编辑</div>
        <div onclick="treeremove()" data-options="iconCls:'icon-remove'">删除</div>
        <div class="menu-sep"></div>
        <div onclick="treereload()" data-options="iconCls:'icon-refresh'">刷新</div>
        <div onclick="expand()">展开</div>
        <div onclick="collapse()">收回</div>
    </div>
    <script type="text/javascript">
       
    </script>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/portal/js/resources/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/resources/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/resources/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/resources/view.js?version=${cfg.version}"></script>

	<jsp:include page="../../common/footer-2.jsp" />

<script type="text/javascript">
window.onresize = function () {
	//autoResize();
	setTimeout("autoResize()",100);
	setTimeout("autoResize()",1000);
}
function autoResize(){
	jQuery('.panel-tool').find('a').on( 'click', function(e) {
		setTimeout("autoResize()",1000);
	});
	var h=window.innerHeight-130;
	if(portalType=='2'){
                    h=window.innerHeight-250;
    }
	$('#cc').layout('resize', {
		width:$(".page-content").width(),
		height:h
	});
	$('#cc').css("height",h);
	$(cfg.grid_selector).jqGrid('setGridHeight', h-65);
	var display=$('#cc-west').css('display');
	console.log(display)
	if(display=='none'){
		$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width()-10);
	}else{
		$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width()-183);
	}
	console.log('autoResize:'+h);
	//parent.autoWidth();
}
jQuery(function($) {
	jQuery('.layout-button-left').on( 'click', function(e) {
		setTimeout("autoResize()",1000);
	});
	
});

</script>

<div id="dialog-message" class="hide">
		
		<div id="uploader">
    <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
</div>
	
 <div style="margin:5px">

		           <a href="rs.xls" style="color:red">下载模板</a>.<br>
		      
		
 </div>



		</div>
		<div id="dialog-sort" class="hide">
		      <div class="sortable">
                <li class="dd-handle"  id="1">第1项</li>
                <li class="dd-handle"  id="2" >第2项</li>
                <li class="dd-handle"  id="3">第3项</li>
              </div>
        </div>

<script
		src="${pageContext.request.contextPath}/content/portal/js/resources/upload.js?version=${cfg.version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>

</body>
</html>
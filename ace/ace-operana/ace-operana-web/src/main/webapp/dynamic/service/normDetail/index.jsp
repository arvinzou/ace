<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>运营数据</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<script type="text/javascript">
var meetingId='${param.meetingId}';
var topicId='${param.topicId}';
var normId='${param.normId}';


</script>
<body>
<div class="page-content">

    <div>
        <div class="div-left header-title-custom">不良现象明细</div>
        <div class="div-right header-title-custom">
            <div style="text-align:right"><a class="blue" href="javascript:add()" data-rel="tooltip" data-placement="top"
                                             title="添加异常现象明细数据"><i class="ace-icon fa fa-plus-square"></i></a>
                <a class="blue" href="javascript:reload()" data-rel="tooltip" data-placement="top" title="刷新"><i
                        class="ace-icon glyphicon glyphicon-refresh"></i></a>
            </div>
        </div>
    </div>
    <div class="action-buttons">
        <a>
        </a>
    </div>

    <table id="grid-table"></table>


</div>


<!-- basic scripts -->
<!--[if lte IE 8]>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/js/gz/jquery1x.min.js?version=${cfg.version}"></script>
<![endif]-->
<script type="text/javascript">
			window.jQuery || document.write("<script src='${portalPath}/content/common/assets/js/gz/jquery.min.js?version=${cfg.version}'>"+"<"+"/script>");


</script>
<script
        src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>

<script
        src="${portalPath}/content/common/assets/js/gz/bootstrap.min.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/assets/js/gz/bootbox.min.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/assets/js/gz/jquery-ui.min.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/assets/js/date-time/bootstrap-datepicker.min.js?version=${cfg.version}"></script>


<script src="${portalPath}/content/common/assets/js/uncompressed/jqGrid/ui.multiselect.js?version=${cfg.version}"></script>
<!--
<script
        src="${pageContext.request.contextPath}/content/common/js/jquery.jqGrid.min.js?version=${cfg.version}"></script>

-->
<script
        src="${portalPath}/content/common/assets/js/gz/jquery.jqGrid.js?version=${cfg.version}"></script>

<script
        src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>

<script
        src="${portalPath}/content/common/assets/js/gz/ace.min.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/assets/js/gz/ace-elements.min.js?version=${cfg.version}"></script>

<script
        src="${portalPath}/content/common/assets/js/gz/ace-extra.min.js?version=${cfg.version}"></script>


<script
        src="${pageContext.request.contextPath}/content/service/normDetail/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/normDetail/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/normDetail/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/normDetail/view.js?version=${cfg.version}&t=1"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight+100);
	try{
	parent.autoWidth();
	}catch(e){
	}

}



</script>
<style>
.ui-jqgrid-btable .ui-widget-content.ui-priority-secondary {
    background-image: none;
    background-color: #eeeeee;
    opacity: 1;
}
.ui-jqgrid tr.jqgrow, .ui-jqgrid tr.ui-row-ltr, .ui-jqgrid tr.ui-row-rtl {
    border: none;
     background-color: #FFFFFF;
}
.div-left{ float:left;width:90%;}
.div-right{ float:right;width:10%;}

</style>
</body>
</html>
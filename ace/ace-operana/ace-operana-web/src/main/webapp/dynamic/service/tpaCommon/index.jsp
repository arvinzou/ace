<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>任务</title>
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
        <div class="div-left header-title-custom">任务</div>
        <div class="div-right header-title-custom">
            <div style="text-align:right"><a class="blue" href="javascript:add()" data-rel="tooltip" data-placement="top"
                                             title="添加"><i class="ace-icon fa fa-plus-square"></i></a>

                <a class="blue" href="javascript:reload()" data-rel="tooltip" data-placement="top" title="刷新"><i
                        class="ace-icon glyphicon glyphicon-refresh"></i></a>


            </div>
        </div>
    </div>

    <div class="btn-toolbar">
        <div class="jqgrid-export"></div>

    </div>
        <table id="grid-table"></table>

    <div id="grid-pager"></div>
</div>




<jsp:include page="../../common/footer-1.jsp"/>

<script
        src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/tpaCommon/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/tpaCommon/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/tpaCommon/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/tpaCommon/view.js?version=${cfg.version}"></script>

<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight+100);

}

</script>
<script type="text/javascript" src="${portalPath}/content/common/tableExport/pdfmake/pdfmake.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/tableExport/pdfmake/vfs_fonts.js"></script>
<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/jsPDF/jspdf.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/jsPDF-AutoTable/jspdf.plugin.autotable.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/export.js?version=${cfg.version}"></script>
<div id="tableExport"></div>
</body>
</html>
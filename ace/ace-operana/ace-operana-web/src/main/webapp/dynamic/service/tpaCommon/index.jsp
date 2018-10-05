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

    <div class="widget-box" id="widget-box">
        <div class="widget-header">
            <h5 class="widget-title smaller">设置查询条件</h5>

            <div class="widget-toolbar"></div>
        </div>

        <div class="widget-body">
            <div class="widget-main padding-6">
                <form action="#" id="fm-search">

                   责任人： <select  class="easyui-combogrid" name="liable" style="width:300px;"
                            data-options="
           idField : 'USER_ID',
					textField : 'NAME',
					url : '/portal/system/selectUsers.do',
					mode : 'remote',
					fitColumns : true,
					method : 'get',
					columns : [[{
						field : 'NAME',
						title : '姓名',
						width : 100
					}, {
						field : 'DEPARTMENT_NAME',
						title : '部门',
						width : 100
					}]]
        "></select>


                    产品编码： <input name="productId" type="text"
                               style="width: 100px;"/>


                    状态：
                    <input
                            class="easyui-combobox" style="width: 100px" name="status"
                            data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=92&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'"/>


                    任务创建时间：
                    <input class="easyui-datebox" name="startDate" style="width:100px;">
                    至
                    <input class="easyui-datebox" name="endDate" style="width:100px;">
                    <button class="btn btn-info" id="btn-search"
                            authority="false">
                        查询
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>


            </div>
        </div>
    </div>
    <div>
        <div class="div-left header-title-custom">任务</div>
        <div class="div-right header-title-custom">
            <div style="text-align:right"><a class="blue" href="javascript:add()" data-rel="tooltip" data-placement="top"
                                             title="添加"><i class="ace-icon fa fa-plus-square"></i></a>

                <a class="blue" href="javascript:reload()"  title="刷新"><i
                        class="ace-icon glyphicon glyphicon-refresh"></i></a>
                <a href="javascript:exportExcel()" style="color:blue">Excel</a>


            </div>
        </div>
    </div>


        <table id="grid-table"></table>

    <div id="grid-pager"></div>

</div>




<jsp:include page="../../common/footer-1.jsp"/>

<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}//content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>


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
<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>



<script id="tpl-uploader" type="text/template"><span id="filelist-history"></span><br><span id="filelist"></span><br><span id="container"><a id="pickfiles" href="javascript:;">[添加附件]</a><br><a id="uploadfiles" href="javascript:;">[上传]</a><br></span><br><span id="console"></span>
</script>
<div id="tableExport"></div>
<style>

.ui-jqgrid tr.jqgrow td { white-space: normal !important; height:auto; }

</style>
</body>
</html>

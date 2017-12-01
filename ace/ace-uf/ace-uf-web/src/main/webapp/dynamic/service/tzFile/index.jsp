<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>统战文件</title>
</head>
<jsp:include page="../../common/common.jsp"/>

<link rel="stylesheet" href="${portalPath}/content/common/assets/css/colorbox.css"/>
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
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
                    <%--名称模糊搜索--%>
                    名称： <input name="name" type="text" style="width: 200px;"/>
                    <%--备注的模糊搜索--%>
                    文件类型：<input
                        class="easyui-combobox" style="width: 200px" name="type"
                        data-options="
                            url:'${portalPath}/dict/findListByCategoryId.do?categoryId=105&selected=false',
                            method:'get',
                            valueField:'code',
                            textField:'name',
                            panelHeight:'auto'">
                    <button class="btn btn-info" id="searchFile"
                            authority="${pageContext.request.contextPath}/file/findFilesList.do">
                        查找
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>

                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">
                    <%--添加文件--%>
                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/file/insertFile.do">添加文件
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <%--修改文件--%>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/file/updateFile.do">修改
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <%--删除文件--%>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/file/deleteFileByFileId.do">删除
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
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
<jsp:include page="../../common/footer-1.jsp"/>

<script
        src="${pageContext.request.contextPath}/content/service/file/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/file/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/file/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/file/view.js?version=${cfg.version}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/service/file/upload.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
        parent.autoWidth();
    }
</script>
</body>
</html>
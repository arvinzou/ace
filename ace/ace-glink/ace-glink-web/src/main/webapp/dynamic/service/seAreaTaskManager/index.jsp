<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <title>强电-任务管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-7">
            </div>

            <div class="col-md-5">

                <form id="fm-search">

                    <div class="input-group">
                        所属区域：
                        <input id="areaNodeID" class="easyui-combotree"
                               data-options="url:'${pageContext.request.contextPath}/seProjectArea/selectTreeList?id=01',method:'get',animate: true,
                lines:true," style='width:255px;line-height: 30px;height: 30px;'>
                    </div>

                </form>
            </div>

        </div>
        <%--page content--%>
        <div class="table-scrollable">
            <div id="page-list">

            </div>
        </div>
        <%--分页页脚--%>
        <div class="paginationbar">
            <ul class="pagination" id="pagination1"></ul>
        </div>

    </div>


    <%--=============common jsp-suffix===============--%>
    <jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
    <%--==============common jsp-suffix==============--%>
</body>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <div class="mt-element-card mt-element-overlay">
        <div class="mt-card-item card-box-item">

            <div class="mt-card-avatar card-box-avatar">
                <p class="mt-card-name task-name">\${item.taskName}</p>
            </div>

            <div class="mt-card-content card-box-content">

                <div class="mt-card-social">
                    {@if item.exeState == 'ok'}
                    <p class="mt-card-desc p-ok">\${parseExeState(item.exeState)}</p>
                    {@else}
                    <p class="mt-card-desc p-error">\${parseExeState(item.exeState)}</p>
                    {@/if}
                    <button type="button" class="btn" onclick="execute('\${item.areaNodeID}', '\${item.taskNo}')">执行
                    </button>
                </div>

            </div>
        </div>
    </div>
    {@/each}
</script>
﻿
<style>

</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>

<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

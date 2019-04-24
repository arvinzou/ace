<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
</head>
<script src="js/index.min.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="css/common.css"/>
<link rel="stylesheet" href="css/index.css"/>
<%
    session.setAttribute("portalPath", "/portal");
%>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var version = '${cfg.version}';
    var fastdfs_server = '${cfg.fastdfs_server}';
    var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
    var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
    var default_page_list = [${cfg.default_page_list}];
</script>
<body>
<div class="head">
    <div class="logo">
        <img src="img/logo.png" alt=""/> 江汉区照明控制平台
    </div>
    <div class="btns">
        <div class="btn">
            <p class="cn">任务管理</p>
            <p class="en">Task Management</p>
        </div>
        <div class="btn">
            <p class="cn">场景执行</p>
            <p class="en">Scenario Execution</p>
        </div>
        <div class="btn">
            <p class="cn">定时设置</p>
            <p class="en">Timing Settings</p>
        </div>
        <div class="btn">
            <p class="cn">总控设置</p>
            <p class="en">Control Set</p>
        </div>
    </div>
</div>
<div class="content">

    <div class="modal task">
        <div class="modal-head">
            <span class="title">任务管理</span>
            <div class="area-search">
                所属区域：
                <input id="areaNodeID" class="easyui-combotree"
                       data-options="url:'${pageContext.request.contextPath}/seProjectArea/selectTreeList?id=01',method:'get',animate: true,
                lines:true," style='min-width:245px;height: 25px;'>
            </div>

        </div>
        <div class="modal-body">
            <%--list--%>
            <ul class="taskList" id="page-list">

            </ul>

            <%--分页页脚--%>
            <div class="paginationbar">
                <ul class="pagination" id="pagination1"></ul>
            </div>
        </div>
    </div>
</div>
</body>
<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <li>
        <div class="top">
            \${item.taskName}
        </div>
        <div class="bottom">
            {@if item.exeState == 'ok'}
            <span class="status success">\${parseExeState(item.exeState)}</span>
            {@else}
            <span class="status error">\${parseExeState(item.exeState)}</span>
            {@/if}
            <button name="btnExecute" onclick="execute('\${item.areaNodeID}', '\${item.taskNo}')"> 执行</button>
        </div>
    </li>
    {@/each}
</script>

<style type="text/css">

</style>
<%----%>
<script src="https://cdn.bootcss.com/jquery/3.4.0/jquery.min.js"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=V1.0.3"
        type="text/javascript"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<%--easyui--%>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
</html>

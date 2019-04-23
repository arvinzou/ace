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
    <div class="modal Timing">
        <div class="modal-head">
            <span class="title">定时设置</span>
            <div class="inputGroup">
                <input type="text" placeholder="输入站点名称"/>
                <button>

                </button>
            </div>
        </div>
        <div class="modal-body">
            <table class="table-tg">
                <tr>
                    <td class="tg-mvxc" width="10%">定时任务序号</td>
                    <td class="tg-mvxc" width="25%">定时任务名称</td>
                    <td class="tg-mvxc" width="10%">是否有效</td>
                    <td class="tg-mvxc" width="25%">任务名称</td>
                    <td class="tg-mvxc" width="15%">启动时间</td>
                    <td class="tg-mvxc" width="15%">操作</td>
                </tr>
                <tr class="tr">
                    <td class="tg-84q5">1</td>
                    <td class="tg-84q5">定时任务名称1</td>
                    <td class="tg-84q5">有效</td>
                    <td class="tg-84q5">任务名称1</td>
                    <td class="tg-84q5">2019-04-12   12:00:00</td>
                    <td class="tg-84q5">查看  更新</td>

                </tr>
                <tr class="tr">
                    <td class="tg-84q5">1</td>
                    <td class="tg-84q5">定时任务名称1</td>
                    <td class="tg-84q5">有效</td>
                    <td class="tg-84q5">任务名称1</td>
                    <td class="tg-84q5">2019-04-12   12:00:00</td>
                    <td class="tg-84q5"></td>
                </tr>
                <tr class="tr">
                    <td class="tg-84q5">1</td>
                    <td class="tg-84q5">定时任务名称1</td>
                    <td class="tg-84q5">有效</td>
                    <td class="tg-84q5">任务名称1</td>
                    <td class="tg-84q5">2019-04-12   12:00:00</td>
                    <td class="tg-84q5">查看  更新</td>
                </tr>
            </table>
            <div class="paginationbar">
                <ul class="pagination" id="pagination1"></ul>
            </div>
        </div>
    </div>
</div>
</body>
<style type="text/css">


</style>
<script src="https://cdn.bootcss.com/jquery/3.4.0/jquery.min.js"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
</html>
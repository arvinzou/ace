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
            <form id="fm-search">
                <div class="inputGroup">
                    <input type="text" placeholder="输入任务名称" name="timerName"/>
                    <button type="submit">

                    </button>
                </div>
            </form>
        </div>
        <div class="modal-body">
            <table class="table-tg" style="border:1px solid rgba(12,129,135);">
                <tr>
                    <td class="tg-mvxc" width="10%">定时任务序号</td>
                    <td class="tg-mvxc" width="30%">定时任务名称</td>
                    <td class="tg-mvxc" width="10%">是否有效</td>
                    <td class="tg-mvxc" width="20%">启动时间</td>
                    <td class="tg-mvxc" width="20%">操作</td>
                </tr>
                <tbody id="page-list">

                </tbody>
            </table>
            <div class="paginationbar" style="float: right">
                <ul class="pagination" id="pagination1"></ul>
            </div>
        </div>
    </div>

    <div class="modal Control">
        <div class="modal-head">
            <span class="title">定时设置</span>
                <button class="activeBtn">
                        执行
                </button>
        </div>
        <div class="modal-body">
            <div class="panel">
                <div class="left">
                    <ul class="months">
                        <li class="active">一月</li>
                        <li>二月</li>
                        <li>三月</li>
                        <li>四月</li>
                        <li>五月</li>
                        <li>六月</li>
                        <li>七月</li>
                        <li>八月</li>
                        <li>九月</li>
                        <li>十月</li>
                        <li>十一月</li>
                        <li>十二月</li>
                    </ul>
                </div>
                <div class="right">
                    <div class="heads">
                        <button class="h-btn">全部日程模式</button>
                        <button class="h-btn">全部日程模式</button>
                        <button class="h-btn">全部日程模式</button>
                    </div>
                    <div class="table">
                        <ul class="days">
                            <li>
                                <div class="day">
                                        1
                                </div>
                                <div class="seled">
                                    <div class="checkboxGroup">
                                        <input id="d1-1" name="d1"  type="radio">
                                        <label class="check1"  for="d1-1">日程模式</label>
                                    </div>
                                    <div class="checkboxGroup">
                                        <input id="d1-2" name="d1" type="radio">
                                        <label class="check2"  for="d1-2">节日模式</label>
                                    </div>
                                    <div class="checkboxGroup">
                                        <input id="d1-3" name="d1" type="radio">
                                        <label class="check3"  for="d1-3">重大节日模式</label>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr class="tr">

        <td class="tg-84q5"> \${item.timerID}</td>
        <td class="tg-84q5"> \${item.timerName}</td>
        <td class="tg-84q5"> {@if item.timerEnable==0}
            <span style="color: #FF616D;">无效</span>
            {@else if item.timerEnable==1}
            <span style="color: #0AFD99;">有效</span>
            {@else}
            {@/if}
        </td>
        <td class="tg-84q5">\${item.startTime}</td>
        <td class="tg-84q5">

            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.timerName}"
               data-target="#modal-preview" style="color: #53FDFF;">更新</a>

        </td>
    </tr>
    {@/each}
</script>
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-preview">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script id="tpl-preview" type="text/template">
    <div class="form-group">
        <label class="col-md-2 view-label">主键</label>
        <div class="col-md-10">
            \${data.o.id}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">定时任务序号</label>
        <div class="col-md-10">
            \${data.o.timerID}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">定时任务名称</label>
        <div class="col-md-10">
            \${data.o.timerName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">定时任务状态</label>
        <div class="col-md-10">
            \${data.o.timerEnable}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">启动时间</label>
        <div class="col-md-10">
            \${data.o.startTime}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">调度任务号</label>
        <div class="col-md-10">
            \${data.o.taskNo}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">备注</label>
        <div class="col-md-10">
            \${data.o.remark}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">状态</label>
        <div class="col-md-10">
            \${data.o.status}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${data.o.createDate}
        </div>
    </div>


</script>

<style type="text/css">

</style>
<script src="https://cdn.bootcss.com/jquery/3.4.0/jquery.min.js"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=V1.0.3"
        type="text/javascript"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
</html>
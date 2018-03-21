<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>监控点档案</title>
</head>
<jsp:include page="../../common/common.jsp"/>
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

                    <%--类别：<input--%>
                    <%--class="easyui-combobox" style="width: 200px" name="category"--%>
                    <%--data-options="--%>
                    <%--url:'${portalPath}/dict/findListByCategoryId.do?categoryId=69&selected=false',--%>
                    <%--method:'get',--%>
                    <%--valueField:'code',--%>
                    <%--textField:'name',--%>
                    <%--panelHeight:'auto'">--%>

                    名称： <input name="name" type="text" style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/monitorSite/findMonitorSiteList">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>

                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">

                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/monitorSite/insertMonitorSite">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/monitorSite/updateMonitorSite">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-purple" id="btn-view-da"
                            authority="${pageContext.request.contextPath}/monitorSite/insertMonitorSiteDetail">
                        <i class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/monitorSite/deleteMonitorSiteByMonitorSiteId">
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
    <table>
        <tr>
            <td align="center" valign="top">
                <table id="alldevice-grid-table"></table>
                <div id="alldevice-grid-pager"></div>
            </td>
            <td align="center" valign="middle">
                <button class="btn btn-info" id="btn-view-da-add"
                        authority="${pageContext.request.contextPath}/monitorSite/insertMonitorSiteDetail">
                    分配<i
                        class="ace-icon fa   fa-angle-double-right  align-middle bigger-125 icon-on-right"></i>
                </button>
                <div style="height:3px"></div>
                <button class="btn btn-info" id="btn-view-da-del"
                        authority="${pageContext.request.contextPath}/monitorSite/insertMonitorSiteDetail">
                    移除<i
                        class="ace-icon fa  fa-angle-double-left  align-middle bigger-125 icon-on-right"></i>
                </button>
            </td>
            <td align="center" valign="top">
                <table id="mydevice-grid-table"></table>
                <div id="mydevice-grid-pager"></div>
            </td>
        </tr>
    </table>
</div>
<div id="dialog-confirm" class="hide">
    <div class="alert alert-info bigger-110">
        重新分配角色后，分配此角色的用户将获取新的权限.
    </div>

    <div class="space-6"></div>

    <p class="bigger-110 bolder center grey">
        <i class="ace-icon fa fa-hand-o-right blue bigger-120"></i> 您确定吗?
    </p>
</div>
<div id="dialog-message-file" class="hide">
    <div id="load" class="loading"></div>
</div>
<div id="dialog-message-view" class="hide">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">主键</span>
            <br>
            <span id="id"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">所属卡点名称</span>
            <br>
            <span id="siteId" class="hide"></span>
            <span id="siteName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">监控点名称</span>
            <br>
            <span id="monitorSiteName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">监控点编号</span>
            <br>
            <span id="monitorSiteNo"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">监控点状态</span>
            <br>
            <span id="monitorSiteStatus"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">所在地区</span>
            <br>
            <span id="areaCode"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">所处道路方向</span>
            <br>
            <span id="direction"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">维度</span>
            <br>
            <span id="latitude"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">经度</span>
            <br>
            <span id="longitude"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">备注</span>
            <br>
            <span id="remark"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">状态</span>
            <br>
            <span id="status"></span>
        </div>
    </div>
    <h5 class="header-title">设备信息</h5>
    <div style="max-height:150px;
        width: 100%;
        overflow-y: auto;">
        <table class="table table-striped" id="device-table">
        </table>
    </div>
    <%--<table id="onlinedevice-grid-table"></table>--%>
    <%--<div id="onlinedevice-grid-pager"></div>--%>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">创建人编号</span>
            <br>
            <span id="createUserId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">创建人姓名</span>
            <br>
            <span id="createUserName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">入库日期</span>
            <br>
            <span id="createDate"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">最后更新人编号</span>
            <br>
            <span id="lastModifyUserId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">最后更新人姓名</span>
            <br>
            <span id="lastModifyUserName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">最后更新时间</span>
            <br>
            <span id="lastModifyDate"></span>
        </div>
    </div>

</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/js/jquery.colorbox.js"></script>
<script
        src="${pageContext.request.contextPath}/content/service/monitorSite/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/monitorSite/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/monitorSite/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/monitorSite/view.js?version=${cfg.version}"></script>
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
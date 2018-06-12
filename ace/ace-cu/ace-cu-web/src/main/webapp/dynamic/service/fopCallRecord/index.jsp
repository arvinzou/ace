<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>企业/协会活动</title>
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
                    类别：<select name="status" style="width: 200px; height: 25px">
                    <option value="">-请选择-</option>
                    <option value="1">未发送</option>
                    <option value="2">已发送</option>
                </select>

                    名称： <input name="payTitle" type="text" style="width: 200px;height: 25px"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/fopCallRecord/findFopCallRecordList">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">

                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/fopCallRecord/insertFopCallRecord">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/fopCallRecord/updateFopCallRecord">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/fopCallRecord/deleteFopCallRecordByFopCallRecordId">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <%--添加催缴对象--%>
                    <button class="btn btn-purple" id="btn-view-add-call-detail"
                            authority="${pageContext.request.contextPath}/fopCallRecord/insertCallRecordDetail">
                        <i class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <%--发送催缴通知--%>
                    <button class="btn btn-purple" id="btn-view-send-notice"
                            authority="${pageContext.request.contextPath}/fopCallRecord/sendCallNotice">
                        <i class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <div id="dialog-message" class="hide">
        <table>
            <tr>
                <td align="center" valign="top">
                    <table id="all-mlist-grid-table"></table>
                    <div id="all-mlist-grid-pager"></div>
                </td>
                <td align="center" valign="middle">
                    <button class="btn btn-info" id="btn-view-m-add"
                            authority="${pageContext.request.contextPath}/fopCallRecord/insertCallRecordDetail">
                        添加<i class="ace-icon fa fa-angle-double-right align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <div style="height:3px"></div>
                    <button class="btn btn-info" id="btn-view-m-del"
                            authority="${pageContext.request.contextPath}/fopCallRecord/insertCallRecordDetail">
                        移除<i class="ace-icon fa fa-angle-double-left align-middle bigger-125 icon-on-right"></i>
                    </button>
                </td>
                <td align="center" valign="top">
                    <table id="my-mlist-grid-table"></table>
                    <div id="my-mlist-grid-pager"></div>
                </td>
            </tr>
        </table>
    </div>

</div>

<div id="dialog-message-file" class="hide">
    <div id="load" class="loading"></div>
</div>

<div id="dialog-message-view" class="hide">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem"><span class="labelItemHeader">
主键</span>
            <br>
            <span id="id">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
催缴标题</span>
            <br>
            <span id="payTitle">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
催缴项目</span>
            <br>
            <span id="payCategory">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
截止日期</span>
            <br>
            <span id="closingDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
消息模板Code</span>
            <br>
            <span id="msgTmplCode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
缴费金额</span>
            <br>
            <span id="payAmount">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
备注</span>
            <br>
            <span id="remark">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
状态</span>
            <br>
            <span id="status">
</span>
        </div>
    </div>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem"><span class="labelItemHeader">
创建人编号</span>
            <br>
            <span id="createUserId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
创建人姓名</span>
            <br>
            <span id="createUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
入库日期</span>
            <br>
            <span id="createDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新人编号</span>
            <br>
            <span id="lastModifyUserId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新人姓名</span>
            <br>
            <span id="lastModifyUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新时间</span>
            <br>
            <span id="lastModifyDate">
</span>
        </div>
    </div>

</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script src="${pageContext.request.contextPath}/content/service/fopCallRecord/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/fopCallRecord/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/fopCallRecord/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/fopCallRecord/view.js?version=${cfg.version}"></script>
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
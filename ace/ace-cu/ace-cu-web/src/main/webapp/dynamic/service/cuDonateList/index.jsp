<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>捐献记录</title>
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

                    索要票据：<input name="needReceipt" class="easyui-combobox" style="width: 200px"
                                data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=146&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">
                    项目名称： <input name="projectName" type="text" style="width: 200px;"/>
                    微信昵称： <input name="nickname" type="text" style="width: 200px;"/>
                    <%--查询--%>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/cuDonateList/findCuDonateListList">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <%--=====================================--%>
                    <div class="space10"></div>
                    捐献时间：<input class="easyui-datetimebox" name="startDate"
                                style="width:200px;height:25px;line-height: 25px;">
                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    至： <input class="easyui-datetimebox" name="endDate"
                              style="width:200px;height:25px;line-height: 25px;">
                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">

                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/cuDonateList/insertCuDonateList">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/cuDonateList/updateCuDonateList">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/cuDonateList/deleteCuDonateListByCuDonateListId">
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

<div id="dialog-message-view" class="hide">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">主键</span>
            <br>
            <span id="id"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">项目名称</span>
            <br>
            <span id="projectName"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">用户ID</span>
            <br>
            <span id="userId"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">支付订单ID</span>
            <br>
            <span id="orderId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">微信昵称</span>
            <br>
            <span id="nickname"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">捐献金额(元)</span>
            <br>
            <span id="donateAmount"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">捐献时间</span>
            <br>
            <span id="donateDate"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">索要票据</span>
            <br>
            <span id="needReceipt"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">收货人名称</span>
            <br>
            <span id="consigneeName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">收货人号码</span>
            <br>
            <span id="consigneeMobileNumber"></span>
        </div>
    </div>
    <h5 class="header-title">收货人地址</h5>
    <div id="consigneeAddr" class="row" style="padding:10px"></div>

    <h5 class="header-title">备注</h5>
    <div id="remark" class="row" style="padding:10px"></div>

    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">状态</span>
            <br>
            <span id="status"></span>
        </div>
        <div class="labelItem hide">
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
        <div class="labelItem hide">
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
<script src="${pageContext.request.contextPath}/content/service/cuDonateList/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuDonateList/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuDonateList/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuDonateList/view.js?version=${cfg.version}"></script>
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
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>案件</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/xcConfirm.css">
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
                    案件状态：<input class="easyui-combobox" style="width: 200px" name="category"
                                data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=69&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">
                    车牌号： <input name="plateNo" type="text" style="width: 200px;"/>
                    时间：
                    <input class="easyui-datetimebox" name="startDate" style="width:200px;">
                    至
                    <input class="easyui-datetimebox" name="endDate" style="width:200px;">

                    <%--查询按钮--%>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/cases/findCasesList">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </form>

                <div class="space10"></div>
                <div id="toolbar" class="toolbar">
                    <%--提交审核--%>
                    <button class="btn btn-purple" id="btn-view-submit-audit"
                            authority="${pageContext.request.contextPath}/cases/submitAudit">
                        <i class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <%--案件审核--%>
                    <button class="btn btn-purple" id="btn-view-audit"
                            authority="${pageContext.request.contextPath}/cases/audit">
                        <i class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <%--案件撤销--%>
                    <button class="btn btn-purple" id="btn-view-repeal"
                            authority="${pageContext.request.contextPath}/cases/repeal">
                        <i class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
                    </button>

                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/cases/insertCases">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/cases/updateCases">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/cases/deleteCasesByCasesId">
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
    <h5 class="header-title">超限信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">主键</span>
            <br>
            <span id="id"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">通行记录</span>
            <br>
            <span id="trafficId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">轴数</span>
            <br>
            <span id="axleCount"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">总重（吨）</span>
            <br>
            <span id="totalMass"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">超限（吨）</span>
            <br>
            <span id="overMass"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">速度（km/h）</span>
            <br>
            <span id="speed"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">罚款金额</span>
            <br>
            <span id="fines"></span>
        </div>
    </div>

    <h5 class="header-title">车辆信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">车牌号</span>
            <br>
            <span id="vehicleId" class="hide"></span>
            <span id="plateNo"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">车辆颜色</span>
            <br>
            <span id="vehicleColor"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">车辆类型</span>
            <br>
            <span id="vehicleType"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">总质量</span>
            <br>
            <span id="vehicleTotalMass"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">核定载重量</span>
            <br>
            <span id="vehicleApprovedMass"></span>
        </div>
    </div>

    <h5 class="header-title">驾驶人信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">驾驶人</span>
            <br>
            <span id="driver" class="hide"></span>
            <span id="driverName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">证件号码</span>
            <br>
            <span id="paperworkId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">手机号码</span>
            <br>
            <span id="phoneNumber"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">住址</span>
            <br>
            <span id="address"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">从业资格证</span>
            <br>
            <span id="certNumber"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">照片</span>
            <br>
            <span id="headImgUrl"></span>
        </div>
    </div>

    <h5 class="header-title">当事人信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">当事人</span>
            <br>
            <span id="party" class="hide"></span>
            <span id="partyName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">当事公司</span>
            <br>
            <span id="partyDeptName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">当事人类型</span>
            <br>
            <span id="partyType"></span>
        </div>
    </div>

    <h5 class="header-title">执法信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">案件号</span>
            <br>
            <span id="caseNo"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">立案日期</span>
            <br>
            <span id="caseDate"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">案件处理人1</span>
            <br>
            <span id="chp1" class="hide"></span>
            <span id="chp1Name"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">执法证号</span>
            <br>
            <span id="lecn"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">案件处理人2</span>
            <br>
            <span id="chp2" class="hide"></span>
            <span id="chp2Name"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">记录人</span>
            <br>
            <span id="recorder" class="hide"></span>
            <span id="recorderName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">笔录时间</span>
            <br>
            <span id="recordTime"></span>
        </div>

        <div class="labelItem">
            <span class="labelItemHeader">审核部门</span>
            <br>
            <span id="auditDept" class="hide"></span>
            <span id="auditDeptName"></span>
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
<script src="${pageContext.request.contextPath}/content/common/js/xcConfirm.js"></script>
<script src="${pageContext.request.contextPath}/content/service/cases/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cases/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cases/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cases/view.js?version=${cfg.version}"></script>
<%--弹框插件--%>

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
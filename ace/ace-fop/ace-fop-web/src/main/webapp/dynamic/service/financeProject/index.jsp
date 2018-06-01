<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>流程记录</title>
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

                    年限：<input
                        class="easyui-combobox" style="width: 200px" name="financeYear"
                        data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=128&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                    名称： <input name="financeTitle" type="text"
                               style="width: 200px;"/>
                    年收益： <input name="btmYield" type="text"
                                style="width: 50px;"/>% — <input name="topYield" type="text"
                                                                 style="width: 50px;"/>%
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/fopFinanceProject/findFopFinanceProjectList">
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/fopFinanceProject/insertFopFinanceProject">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/fopFinanceProject/updateFopFinanceProject">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/fopFinanceProject/deleteFopFinanceProjectByFopFinanceProjectId">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>

                    <%--审核--%>
                    <button class="btn btn-purple" id="btn-view-audit"
                            authority="${pageContext.request.contextPath}/fopFinanceProject/audit">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <div id="dialog-message-audit" class="hide">
        <form action="/fopFinanceProject/audit" id="fm-audit">
            <fieldset>
                审核结果：
                <input id="audit_pass" name="audit_result" type="radio" value="0"/> 通过
                <input id="audit_unpass" name="audit_result" type="radio" value="1"/> 不通过
            </fieldset>
            <div class="space-6"></div>
            <fieldset>
                审核备注： <textarea id="audit_opinion" cols="30" rows="10"></textarea>

                <%--<input id="" type="password" style="width: 200px;"/>--%>
            </fieldset>
        </form>
    </div>
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
        <div class="labelItem"><span class="labelItemHeader">
主键</span>
            <br>
            <span id="id">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
融资名称</span>
            <br>
            <span id="financeTitle">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
融资代码</span>
            <br>
            <span id="financeCode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
所属区域</span>
            <br>
            <span id="areaCode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
所属公司</span>
            <br>
            <span id="relationId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
发布日期</span>
            <br>
            <span id="releaseDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
融资金额</span>
            <br>
            <span id="financeAmount">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
融资年限</span>
            <br>
            <span id="financeYear">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
融资内容</span>
            <br>
            <span id="financeContent">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
预期年收益</span>
            <br>
            <span id="yearYield">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
点击数</span>
            <br>
            <span id="clicks">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
点赞数</span>
            <br>
            <span id="likes">
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
<script
        src="${pageContext.request.contextPath}/content/service/financeProject/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/financeProject/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/financeProject/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/financeProject/view.js?version=${cfg.version}"></script>


<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
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
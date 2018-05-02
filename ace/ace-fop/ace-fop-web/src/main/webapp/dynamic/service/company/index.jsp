<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>企业管理</title>
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

                    类别：<input
                        class="easyui-combobox" style="width: 200px" name="category"
                        data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=69&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                    名称： <input name="name" type="text"
                               style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/fopCompany/findFopCompanyList">
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/fopCompany/insertFopCompany">
                        <i
                                class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/fopCompany/updateFopCompany">
                        <i
                                class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/fopCompany/deleteFopCompanyByFopCompanyId">
                        <i
                                class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
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
        <div class="labelItem"><span class="labelItemHeader">
主键</span>
            <br>
            <span id="id">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
portal.department.id</span>
            <br>
            <span id="departmentId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
统一社会信用代码</span>
            <br>
            <span id="creditCode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业类型</span>
            <br>
            <span id="companyType">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业全称</span>
            <br>
            <span id="fullName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业简称</span>
            <br>
            <span id="shortName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业编码</span>
            <br>
            <span id="companyCode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业LOGO</span>
            <br>
            <span id="companyLogo">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业门户地址</span>
            <br>
            <span id="companyLinkUrl">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
所在地区</span>
            <br>
            <span id="areaCode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业性质</span>
            <br>
            <span id="companyProperty">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
成立时间</span>
            <br>
            <span id="establishDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
职工人数</span>
            <br>
            <span id="crewSize">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
注册资金（万元）</span>
            <br>
            <span id="registeredCapital">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
固定资产（万元）</span>
            <br>
            <span id="fixedAssets">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
自有流动资金（万元）</span>
            <br>
            <span id="workingCapital">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
自有生产（经营）场地（㎡ ）</span>
            <br>
            <span id="ownSpace">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
租赁生产（经营）场地（㎡ ）</span>
            <br>
            <span id="tenancySpace">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业法人代表</span>
            <br>
            <span id="personId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业通讯地址</span>
            <br>
            <span id="adress">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
维度</span>
            <br>
            <span id="latitude">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
经度</span>
            <br>
            <span id="longitude">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
第一联系人</span>
            <br>
            <span id="firstPersonId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
第二联系人</span>
            <br>
            <span id="secPersonId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
邮政编码</span>
            <br>
            <span id="postcode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
传真</span>
            <br>
            <span id="fax">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
电子邮箱</span>
            <br>
            <span id="email">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业生产（经营）主要品种</span>
            <br>
            <span id="majorVariety">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
劳动合同签订人数</span>
            <br>
            <span id="laborContractNum">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
是否建立劳动关系三方协调机制</span>
            <br>
            <span id="thirdLaborRelation">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
社会保险参保地</span>
            <br>
            <span id="socialInsuranceAddr">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
社会保险登记证号</span>
            <br>
            <span id="socialInsuranceNum">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
累计纳税</span>
            <br>
            <span id="accTaxAmount">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
当年纳税</span>
            <br>
            <span id="yearTaxAmount">
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
        src="${pageContext.request.contextPath}/content/service/fopCompany/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopCompany/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopCompany/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopCompany/view.js?version=${cfg.version}"></script>
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
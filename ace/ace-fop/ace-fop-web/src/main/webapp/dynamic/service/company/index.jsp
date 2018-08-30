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

<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
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

                    会员状态：<input class="easyui-combobox" style="width: 200px" name="status"
                                data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=149&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                    名称： <input name="fullName" type="text" style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/fopCompany/findFopCompanyList">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">
                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/fopCompany/insertFopCompany">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/fopCompany/updateFopCompany">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/fopCompany/deleteFopCompanyByFopCompanyId">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-recover"
                            authority="${pageContext.request.contextPath}/fopCompany/recoverData">
                        <%--<i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>--%>
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
<%--查看页面dialog--%>
<div id="dialog-message-view" class="hide">

</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/juicer/juicer-min.js"></script>
<script src="${pageContext.request.contextPath}/content/service/company/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/company/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/company/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/company/view.js?version=${cfg.version}"></script>

<script src="${pageContext.request.contextPath}/content/service/company/upload.js?version=${cfg.version}"></script>

<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>

<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
        parent.autoWidth();
    }
</script>
<%--渲染模板--%>
<script id="tpl-company" type="text/template">
    <h5 class="header-title" style="text-align:center;">企业基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">企业名称</span>
            <br>
            <span name="fullName">\${fullName}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">统一社会信用代码</span>
            <br>
            <span name="creditCode">\${creditCode}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">所属工商联</span>
            <br>
            <span name="belongTo">\${belongTo}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">企业类型</span>
            <br>
            <span name="companyType">\${companyType}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">企业性质</span>
            <br>
            <span name="companyProperty">\${companyProperty}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">成立时间</span>
            <br>
            <span name="establishDate">\${establishDate}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">职工人数</span>
            <br>
            <span name="crewSize">\${crewSize}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">所在地区</span>
            <br>
            <span name="areaCodeName">\${areaCodeName}</span>
        </div>
    </div>

    <h5 class="header-title" style="text-align:center;">注册资本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">注册资金（万元）</span>
            <br>
            <span name="registeredCapital">\${registeredCapital}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">固定资产（万元）</span>
            <br>
            <span name="fixedAssets">\${fixedAssets}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">自有流动资金（万元）</span>
            <br>
            <span name="workingCapital">\${workingCapital}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">自有生产（经营）场地（㎡ ）</span>
            <br>
            <span name="ownSpace">\${ownSpace}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">租赁生产（经营）场地（㎡ ）</span>
            <br>
            <span name="tenancySpace">\${tenancySpace}</span>
        </div>
    </div>

    <h5 class="header-title" style="text-align:center;">法人代表信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">法人姓名</span>
            <br>
            <span name="realName">\${realName}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">手机号码</span>
            <br>
            <span name="lpMobile">\${lpMobile}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">性别</span>
            <br>
            <span name="lpSex">\${lpSex}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">出生年月</span>
            <br>
            <span name="lpBirthDt">\${lpBirthDt}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">籍贯</span>
            <br>
            <span name="lpNativePlace">\${lpNativePlace}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">民族</span>
            <br>
            <span name="lpNationality">\${lpNationality}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">政治面貌</span>
            <br>
            <span name="lpPolitical">\${lpPolitical}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">参加工作时间</span>
            <br>
            <span name="lpRecruitmentDate">\${lpRecruitmentDate}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">文化程度</span>
            <br>
            <span name="lpEducation">\${lpEducation}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">技术职称</span>
            <br>
            <span name="lpSkillJobTitle">\${lpSkillJobTitle}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">单位职务</span>
            <br>
            <span name="lpDeptPost">\${lpDeptPost}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">身份证号码</span>
            <br>
            <span name="lpIdentityCard">\${lpIdentityCard}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">主要社会职务</span>
            <br>
            <span name="lpSocietyPost">\${lpSocietyPost}</span>
        </div>
    </div>
    <h5 class="header-title">个人简历</h5>
    <div class="row" style="padding:10px" name="lpResume">
        \${lpResume}
    </div>
    <h5 class="header-title">特长及成就</h5>
    <div class="row" style="padding:10px" name="lpAchievement">
        \${lpAchievement}
    </div>


    <h5 class="header-title" style="text-align:center;">企业通讯地址</h5>
    <div class="row" style="padding:10px" name="address">
        \${address}
    </div>
    <h5 class="header-title">企业联系方式</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">邮政编码</span>
            <br>
            <span name="postcode">\${postcode}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">传真</span>
            <br>
            <span name="fax">\${fax}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">电子邮箱</span>
            <br>
            <span name="email">\${email}</span>
        </div>
    </div>
    <h5 class="header-title">企业生产（经营）主要品种</h5>
    <div class="row" style="padding:10px" name="majorVariety">
        \${majorVariety}
    </div>

    <h5 class="header-title" style="text-align:center;">党组织建立情况</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">党组织类型</span>
            <br>
            <span name="laborContractNum">\${partyCategory}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">党组织成立时间</span>
            <br>
            <span name="laborContractNum">\${partyCrtDt}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">党员人数</span>
            <br>
            <span name="laborContractNum">\${partyPeoples}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">党组织负责人</span>
            <br>
            <span name="laborContractNum">\${partyDutyMan}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">联系电话</span>
            <br>
            <span name="laborContractNum">\${partyPhone}</span>
        </div>
    </div>

    <h5 class="header-title" style="text-align:center;">工会组织建立情况</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">工会组织成立时间</span>
            <br>
            <span name="laborContractNum">\${unionCrtDt}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">工会组织负责人</span>
            <br>
            <span name="laborContractNum">\${unionDutyMan}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">联系电话</span>
            <br>
            <span name="laborContractNum">\${unionPhone}</span>
        </div>
    </div>

    <h5 class="header-title" style="text-align:center;">构建和谐劳动关系情况</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">劳动合同签订人数</span>
            <br>
            <span name="laborContractNum">\${laborContractNum}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">是否建立劳动关系三方协调机制</span>
            <br>
            <span name="thirdLaborRelation">\${thirdLaborRelation}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">社会保险参保地</span>
            <br>
            <span name="socialInsuranceAddr">\${socialInsuranceAddr}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">社会保险登记证号</span>
            <br>
            <span name="socialInsuranceNum">\${socialInsuranceNum}</span>
        </div>
    </div>

    <h5 class="header-title" style="text-align:center;">对社会公益事业做过何种贡献</h5>
    <h5 class="header-title">安排下岗职工再就业</h5>
    <div class="row" style="padding:10px" name="reemployContribution">
        \${reemployContribution}
    </div>
    <h5 class="header-title">助学兴教</h5>
    <div class="row" style="padding:10px" name="educationContribution">
        \${educationContribution}
    </div>
    <h5 class="header-title">帮困扶贫</h5>
    <div class="row" style="padding:10px" name="helpPoorContribution">
        \${helpPoorContribution}
    </div>
    <h5 class="header-title">其 他</h5>
    <div class="row" style="padding:10px" name="otherContribution">
        \${otherContribution}
    </div>

    <h5 class="header-title" style="text-align:center;">纳税信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">累计纳税（万元）</span>
            <br>
            <span name="accTaxAmount">\${accTaxAmount}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">当年纳税（万元）</span>
            <br>
            <span name="yearTaxAmount">\${yearTaxAmount}</span>
        </div>
    </div>

    <h5 class="header-title" style="text-align:center;">其他</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">备注</span>
            <br>
            <span name="remark">\${remark}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">状态</span>
            <br>
            <span name="status">\${status}</span>
        </div>
    </div>

    <h5 class="header-title" style="text-align:center;">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">创建人编号</span>
            <br>
            <span name="createUserId">\${createUserId}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">创建人姓名</span>
            <br>
            <span name="createUserName">\${createUserName}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">入库日期</span>
            <br>
            <span name="createDate">\${createDate}</span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">最后更新人编号</span>
            <br>
            <span name="lastModifyUserId">\${lastModifyUserId}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">最后更新人姓名</span>
            <br>
            <span name="lastModifyUserName">\${lastModifyUserName}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">最后更新时间</span>
            <br>
            <span name="lastModifyDate">\${lastModifyDate}</span>
        </div>
    </div>
</script>
<%--弹窗模式--%>
<script id="tpl-dialog" type="text/template">
    <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table">
        <div>
            <form name="FormPost" id="FrmGrid_grid-table" class="FormGrid" onsubmit="return false;"
                  style="width:auto;overflow:auto;position:relative;max-height:478px;">
                <table id="TblGrid_grid-table" class="EditTable" cellspacing="0" cellpadding="0" border="0">
                    <tbody>
                    <tr id="FormError" style="display:none">
                        <td class="ui-state-error" colspan="4"></td>
                    </tr>
                    <tr style="display:none" class="tinfo">
                        <td class="topinfo" colspan="4"></td>
                    </tr>
                    <tr rowpos="1" class="FormData" id="tr_title1">
                        <td class="CaptionTD" colspan="4" style="text-align: left; padding-left: 12px;"><h5
                                class="header-title-jqgrid">企业基本信息</h5></td>
                        <td class="CaptionTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="3" class="FormData" id="tr_creditCode">
                        <td class="CaptionTD">统一社会信用代码</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" name="creditCode"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        role="textbox" disabled="disabled"
                                                        value="\${creditCode}"/></td>
                        <td class="CaptionTD">所属工商联</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50"
                                                        name="belongTo" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled" value="\${belongTo}"/></td>
                    </tr>
                    <tr rowpos="6" class="FormData" id="tr_fullName">
                        <td class="CaptionTD">企业名称</td>
                        <td class="DataTD" colspan="3">&nbsp;<input type="text" style="width:95%;height:25px;"
                                                                    colspan="true" name="fullName"
                                                                    role="textbox"
                                                                    class="FormElement ui-widget-content ui-corner-all"
                                                                    disabled="disabled" value="\${fullName}"/></td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="7" class="FormData" id="tr_companyProperty">
                        <td class="CaptionTD">企业性质</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        name="companyProperty"
                                                        disabled="disabled" value="\${companyProperty}"/></td>
                        <td class="CaptionTD">成立时间</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="establishDate" value="\${establishDate}"/></td>
                    </tr>
                    <tr rowpos="9" class="FormData" id="tr_crewSize">
                        <td class="CaptionTD">职工人数</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="crewSize" value="\${crewSize}"/></td>
                        <td class="CaptionTD">所在地区</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="areaCodeName" value="\${areaCodeName}"/></td>
                    </tr>
                    <tr rowpos="11" class="FormData" id="tr_title2">
                        <td class="CaptionTD" colspan="4" style="text-align: left; padding-left: 12px;"><h5
                                class="header-title-jqgrid">注册资本信息</h5></td>
                        <td class="CaptionTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="13" class="FormData" id="tr_registeredCapital">
                        <td class="CaptionTD">注册资金（万元）</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="registeredCapital" value="\${registeredCapital}"/></td>
                        <td class="CaptionTD">固定资产（万元）</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="fixedAssets" value="\${fixedAssets}"/></td>
                    </tr>
                    <tr rowpos="15" class="FormData" id="tr_workingCapital">
                        <td class="CaptionTD">自有流动资金（万元）</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="workingCapital" value="\${workingCapital}"/></td>
                        <td class="CaptionTD">自有生产（经营）场地（㎡ ）</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="ownSpace" value="\${ownSpace}"/></td>
                    </tr>
                    <tr rowpos="18" class="FormData" id="tr_tenancySpace">
                        <td class="CaptionTD">租赁生产（经营）场地（㎡ ）</td>
                        <td class="DataTD" colspan="3">&nbsp;<input type="text" size="35"
                                                                    colspan="true" role="textbox"
                                                                    class="FormElement ui-widget-content ui-corner-all"
                                                                    disabled="disabled"
                                                                    name="tenancySpace" value="\${tenancySpace}"/></td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="19" class="FormData" id="tr_title3">
                        <td class="CaptionTD" colspan="4" style="text-align: left; padding-left: 12px;"><h5
                                class="header-title-jqgrid">法人代表信息</h5></td>
                        <td class="CaptionTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="21" class="FormData" id="tr_realName">
                        <td class="CaptionTD">法人姓名</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="realName" value="\${realName}"/></td>
                        <td class="CaptionTD">手机号码</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="lpMobile" value="\${lpMobile}"/></td>
                    </tr>
                    <tr rowpos="23" class="FormData" id="tr_lpSex">
                        <td class="CaptionTD">性别</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="lpSex" value="\${lpSex}"/></td>
                        <td class="CaptionTD">出生年月</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="lpBirthDt" value="\${lpBirthDt}"/></td>
                    </tr>
                    <tr rowpos="25" class="FormData" id="tr_lpNativePlace">
                        <td class="CaptionTD">籍贯</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="lpNativePlace" value="\${lpNativePlace}"/></td>
                        <td class="CaptionTD">民族</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="lpNationality" value="\${lpNationality}"/></td>
                    </tr>
                    <tr rowpos="27" class="FormData" id="tr_lpPolitical">
                        <td class="CaptionTD">政治面貌</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="lpPolitical" value="\${lpPolitical}"/></td>
                        <td class="CaptionTD">参加工作时间</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="lpRecruitmentDate" value="\${lpRecruitmentDate}"/></td>
                    </tr>
                    <tr rowpos="29" class="FormData" id="tr_lpEducation">
                        <td class="CaptionTD">文化程度</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="lpEducation" value="\${lpEducation}"/></td>
                        <td class="CaptionTD">技术职称</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="lpSkillJobTitle" value="\${lpSkillJobTitle}"/></td>
                    </tr>
                    <tr rowpos="31" class="FormData" id="tr_lpDeptPost">
                        <td class="CaptionTD">单位职务</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="lpDeptPost" value="\${lpDeptPost}"/></td>
                        <td class="CaptionTD">身份证号码</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35"
                                                        colspan="true" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"
                                                        disabled="disabled"
                                                        name="lpIdentityCard" value="\${lpIdentityCard}"/></td>
                    </tr>
                    <tr rowpos="34" class="FormData" id="tr_lpSocietyPost">
                        <td class="CaptionTD">主要社会职务</td>
                        <td class="DataTD" colspan="3">&nbsp;<input type="text" size="35"
                                                                    colspan="true" role="textbox"
                                                                    class="FormElement ui-widget-content ui-corner-all"
                                                                    disabled="disabled"
                                                                    name="lpSocietyPost" value="\${lpSocietyPost}"/>
                        </td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="36" class="FormData" id="tr_lpResume">
                        <td class="CaptionTD">个人简历</td>
                        <td class="DataTD" colspan="3">&nbsp;<textarea colspan="true" size="20" maxlength="250"
                                                                       style="width:95%;height: 120px;"
                                                                       cols="20" rows="2" role="textbox"
                                                                       multiline="true"
                                                                       class="FormElement ui-widget-content ui-corner-all"
                                                                       disabled="true"
                                                                       name="lpResume">\${lpResume}</textarea>
                        </td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="38" class="FormData" id="tr_lpAchievement">
                        <td class="CaptionTD">特长及成就</td>
                        <td class="DataTD" colspan="3">&nbsp;<textarea colspan="true" size="20" maxlength="250"
                                                                       style="width:95%;height: 120px;" cols="20"
                                                                       rows="2" role="textbox" multiline="true"
                                                                       class="FormElement ui-widget-content ui-corner-all"
                                                                       disabled="true" name="lpAchievement">\${lpAchievement}</textarea>
                        </td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="39" class="FormData" id="tr_title4">
                        <td class="CaptionTD" colspan="4" style="text-align: left; padding-left: 12px;"><h5
                                class="header-title-jqgrid">企业联系方式</h5></td>
                        <td class="CaptionTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="42" class="FormData" id="tr_address">
                        <td class="CaptionTD">企业通讯地址</td>
                        <td class="DataTD" colspan="3">&nbsp;<input type="text" style="width:90%" colspan="true"
                                                                    disabled="disabled"
                                                                    name="address" role="textbox" value="\${address}"
                                                                    class="FormElement ui-widget-content ui-corner-all">
                        </td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="43" class="FormData" id="tr_latitude">
                        <td class="CaptionTD">维度</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        name="latitude" role="textbox" value="\${latitude}"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                        <td class="CaptionTD">经度</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        name="longitude" role="textbox" value="\${longitude}"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                    </tr>
                    <tr rowpos="45" class="FormData" id="tr_postcode">
                        <td class="CaptionTD">邮政编码</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        name="postcode" role="textbox" value="\${postcode}"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                        <td class="CaptionTD">传真</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        name="fax" value="\${fax}"
                                                        role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                    </tr>
                    <tr rowpos="47" class="FormData" id="tr_email">
                        <td class="CaptionTD">电子邮箱</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        name="email" value="\${email}"
                                                        role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                        <td class="CaptionTD">企业生产（经营）主要品种</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        value="\${majorVariety}" name="majorVariety" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                    </tr>
                    <tr rowpos="49" class="FormData" id="tr_title8">
                        <td class="CaptionTD" colspan="4" style="text-align: left; padding-left: 12px;"><h5
                                class="header-title-jqgrid">党组织建立情况</h5></td>
                        <td class="CaptionTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="51" class="FormData" id="tr_partyCategory">
                        <td class="CaptionTD">党组织类型</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        value="\${partyCategory}"
                                                        name="partyCategory" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                        <td class="CaptionTD">党组织成立时间</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        value="\${partyCrtDt}"
                                                        name="partyCrtDt" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                    </tr>
                    <tr rowpos="53" class="FormData" id="tr_partyPeoples">
                        <td class="CaptionTD">党员人数</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50"
                                                        disabled="disabled"
                                                        value="\${partyPeoples}"
                                                        name="partyPeoples" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                        <td class="CaptionTD">党组织负责人</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        value="\${partyDutyMan}"
                                                        name="partyDutyMan" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                    </tr>
                    <tr rowpos="56" class="FormData" id="tr_partyPhone">
                        <td class="CaptionTD">联系电话</td>
                        <td class="DataTD" colspan="3">&nbsp;<input type="text" size="35" maxlength="50" colspan="true"
                                                                    disabled="disabled"
                                                                    value="\${partyPhone}" name="partyPhone"
                                                                    role="textbox"
                                                                    class="FormElement ui-widget-content ui-corner-all">
                        </td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="57" class="FormData" id="tr_title9">
                        <td class="CaptionTD" colspan="4" style="text-align: left; padding-left: 12px;"><h5
                                class="header-title-jqgrid">工会组织建立情况</h5></td>
                        <td class="CaptionTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="59" class="FormData" id="tr_unionCrtDt">
                        <td class="CaptionTD">工会组织成立时间</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" colspan="true"
                                                        disabled="disabled"
                                                        value="\${unionCrtDt}" name="unionCrtDt"
                                                        role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                        <td class="CaptionTD">工会组织负责人</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        value="\${unionDutyMan}"
                                                        name="unionDutyMan" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                    </tr>
                    <tr rowpos="62" class="FormData" id="tr_unionPhone">
                        <td class="CaptionTD">联系电话</td>
                        <td class="DataTD" colspan="3">&nbsp;<input type="text" size="35" maxlength="50" colspan="true"
                                                                    disabled="disabled"
                                                                    value="\${unionPhone}" name="unionPhone"
                                                                    role="textbox"
                                                                    class="FormElement ui-widget-content ui-corner-all">
                        </td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="63" class="FormData" id="tr_title5">
                        <td class="CaptionTD" colspan="4" style="text-align: left; padding-left: 12px;"><h5
                                class="header-title-jqgrid">构建和谐劳动关系情况</h5></td>
                        <td class="CaptionTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="65" class="FormData" id="tr_laborContractNum">
                        <td class="CaptionTD">劳动合同签订人数</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        value="\${laborContractNum}"
                                                        name="laborContractNum" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                        <td class="CaptionTD">是否建立劳动关系三方协调机制</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        value="\${thirdLaborRelation}"
                                                        name="thirdLaborRelation" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                    </tr>
                    <tr rowpos="67" class="FormData" id="tr_socialInsuranceAddr">
                        <td class="CaptionTD">社会保险参保地</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        value="\${socialInsuranceAddr}"
                                                        name="socialInsuranceAddr" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                        <td class="CaptionTD">社会保险登记证号</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        value="\${socialInsuranceNum}"
                                                        name="socialInsuranceNum" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                    </tr>
                    <tr rowpos="69" class="FormData" id="tr_title10">
                        <td class="CaptionTD" colspan="4" style="text-align: left; padding-left: 12px;"><h5
                                class="header-title-jqgrid">对社会公益事业做过何种贡献</h5></td>
                        <td class="CaptionTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="72" class="FormData" id="tr_reemployContribution">
                        <td class="CaptionTD">安排下岗职工再就业</td>
                        <td class="DataTD" colspan="3">&nbsp;<textarea colspan="true" size="20" maxlength="250"
                                                                       disabled="true"
                                                                       style="width:95%;height: 120px;"
                                                                       name="reemployContribution" cols="20" rows="2"
                                                                       role="textbox" multiline="true"
                                                                       class="FormElement ui-widget-content ui-corner-all">\${reemployContribution}</textarea>
                        </td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="74" class="FormData" id="tr_educationContribution">
                        <td class="CaptionTD">助学兴教</td>
                        <td class="DataTD" colspan="3">&nbsp;<textarea colspan="true" size="20" maxlength="250"
                                                                       disabled="true"
                                                                       style="width:95%;height: 120px;"
                                                                       name="educationContribution" cols="20" rows="2"
                                                                       role="textbox" multiline="true"
                                                                       class="FormElement ui-widget-content ui-corner-all">\${educationContribution}</textarea>
                        </td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="76" class="FormData" id="tr_helpPoorContribution">
                        <td class="CaptionTD">帮困扶贫</td>
                        <td class="DataTD" colspan="3">&nbsp;<textarea colspan="true" size="20" maxlength="250"
                                                                       disabled="true"
                                                                       style="width:95%;height: 120px;"
                                                                       name="helpPoorContribution" cols="20" rows="2"
                                                                       role="textbox" multiline="true"
                                                                       class="FormElement ui-widget-content ui-corner-all">\${helpPoorContribution}</textarea>
                        </td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="78" class="FormData" id="tr_otherContribution">
                        <td class="CaptionTD">其 他</td>
                        <td class="DataTD" colspan="3">&nbsp;<textarea colspan="true" size="20" maxlength="250"
                                                                       style="width:95%;height: 120px;"
                                                                       name="otherContribution" disabled="true"
                                                                       cols="20" rows="2" role="textbox"
                                                                       multiline="true"
                                                                       class="FormElement ui-widget-content ui-corner-all">\${otherContribution}</textarea>
                        </td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="79" class="FormData" id="tr_title6">
                        <td class="CaptionTD" colspan="4" style="text-align: left; padding-left: 12px;"><h5
                                class="header-title-jqgrid">纳税信息</h5></td>
                        <td class="CaptionTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="81" class="FormData" id="tr_accTaxAmount">
                        <td class="CaptionTD">累计纳税（万元）</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        value="\${accTaxAmount}"
                                                        name="accTaxAmount" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                        <td class="CaptionTD">当年纳税（万元）</td>
                        <td class="DataTD">&nbsp;<input type="text" size="35" maxlength="50" disabled="disabled"
                                                        value="\${yearTaxAmount}"
                                                        name="yearTaxAmount" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                    </tr>
                    <tr rowpos="83" class="FormData" id="tr_title7">
                        <td class="CaptionTD" colspan="4" style="text-align: left; padding-left: 12px;"><h5
                                class="header-title-jqgrid">其他</h5></td>
                        <td class="CaptionTD">&nbsp;</td>
                    </tr>
                    <tr rowpos="85" class="FormData" id="tr_remark">
                        <td class="CaptionTD">备注</td>
                        <td class="DataTD">&nbsp;<input type="text" size="30" disabled="disabled"
                                                        value="\${remark}" name="remark" role="textbox"
                                                        class="FormElement ui-widget-content ui-corner-all"></td>
                        <td class="CaptionTD">&nbsp;</td>
                        <td class="DataTD">&nbsp;</td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</script>
</body>
</html>
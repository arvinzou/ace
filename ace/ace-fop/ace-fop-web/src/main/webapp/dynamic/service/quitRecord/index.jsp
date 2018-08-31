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
                    审核结果：<select name="status" style="width: 200px; height: 25px">
                    <option value="">-请选择-</option>
                    <option value="1">未审核</option>
                    <option value="2">已审核</option>
                    <option value="3">不通过</option>
                </select>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/fopFlowRecord/findFopFlowRecordList">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">
                    <button class="btn btn-purple" id="btn-view-audit"
                            authority="${pageContext.request.contextPath}/fopFlowRecord/audit">
                        <i class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
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

<div id="fc-dialog-message-view" class="hide">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">记录来源ID</span>
            <br>
            <span id="fromId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">流程类型</span>
            <br>
            <span id="flowType"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">审核人</span>
            <br>
            <span id="personId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">审核结果</span>
            <br>
            <span id="auditResult"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">审核意见</span>
            <br>
            <span id="auditOpinion"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">审核时间</span>
            <br>
            <span id="auditDate"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">备注</span>
            <br>
            <span id="remark"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">状态</span>
            <br>
            <span id="status"></span>
        </div>
    </div>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
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
<%--企业信息展示--%>
<div id="c-dialog-message-view" class="hide">

</div>
<%--商协会信息展示--%>
<div id="a-dialog-message-view" class="hide">

</div>

<jsp:include page="../../common/footer-1.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/juicer/juicer-min.js"></script>
<script src="${pageContext.request.contextPath}/content/service/quitRecord/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/quitRecord/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/quitRecord/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/quitRecord/view.js?version=${cfg.version}"></script>


<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
        parent.autoWidth();
    }
</script>
<%--企业渲染模板--%>
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
<%--团体渲染模板--%>
<script id="tpl-association" type="text/template">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">协会全称</span>
            <br>
            <span name="fullName">\${fullName}</span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">协会简称</span>
            <br>
            <span name="shortName">\${shortName}</span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">拼音代码</span>
            <br>
            <span name="abcCode">\${abcCode}</span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">所在地区</span>
            <br>
            <span id="areaCodeName">\${areaCodeName}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">协会地址</span>
            <br>
            <span name="address">\${address}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">办公电话</span>
            <br>
            <span name="phoneNumber">\${phoneNumber}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">创建时间</span>
            <br>
            <span name="establishDate">\${establishDate}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">会长姓名</span>
            <br>
            <span name="pseronId">\${pseronId}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">理事数量</span>
            <br>
            <span name="directorNum">\${directorNum}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">副会长数量</span>
            <br>
            <span name="viceNum">\${viceNum}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">备注</span>
            <br>
            <span name="remark">\${remark}</span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">状态</span>
            <br>
            <span name="status">\${status}</span>
        </div>
    </div>
    <h5 class="header-title">操作信息</h5>
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
</body>
</html>
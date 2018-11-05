<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%
    String companyId = request.getParameter("companyId");
    request.setAttribute("companyId", companyId);
%>

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
    var default_page_list=[${cfg.default_page_list}];
</script>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>基本信息</title>
</head>

<script type="text/javascript" src="/portal/content/common/js/dict_fop.js?version=V1.0"></script>


<script type="text/javascript">
    var taskId = '${taskId}';
    var edit = '${edit}';
</script>
<style>
    .layout-user {
        width: 248px;
        height: 20px;
        float: left;
        margin: 1px 1px 1px;
    }

</style>
<style>
    .main_box {
        width: 95%;
        margin: 0 auto;
        background-color: #FFFFFF;
        padding-left: 30px;
        padding-right: 30px;
        padding-bottom: 30px;
    }

    .title_box_title_01 {
        font-size: 30px;
        color: #1A56A8;
        font-weight: bolder;
    }

    .info_title_01 {
        width: 300px;
        height: 35px;
        line-height: 35px;
        display: block;
        float: left;
        font-size: 16px;
        color: #1A56A8;
    }

    .info_title_btn {
        width: 50px;
        height: 35px;
        line-height: 35px;
        display: block;
        float: right;
        cursor: pointer;
    }

    .form_info {
        width: 100%;
        margin: auto;
        font-size: 14px;
        border-collapse: collapse;
    }

    .form_info td {
        padding-left: 15px;
        padding-right: 15px;
        border: 1px solid #E1E5ED;
    }

    .form_info tr {
        height: 30px;
        line-height: 30px;
    }

    .form_info textarea {
        border: none;
        outline: none;
    }

    .form_info input {
        border: none;
        outline: none;
        background-color: #fff;
    }

    .form_info select {
        border: none;
        outline: none;
        background-color: #fff;
    }

    .maper {
        color: #1A56A8;
    }

    .tdbg {
        background-color: #FBFCFD;
    }

    textarea {
        height: 200px;
        width: 100%;
        resize: none;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        border: #C9D8DB 1px solid;
    }

    input {
        border: none !important;
    }
</style>


<body>
<div class="page-content">

    <div class="main_box">

    </div>
</div>
<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/content/common/juicer/juicer-min.js"></script>
<script src="${pageContext.request.contextPath}/content/service/company/act.js"></script>

<script id="tpl-view-page" type="text/template">
    <div class="title_box">
        <div style="width:100%;height:35px;text-align: center;">
            <span class="title_box_title_01">企业信息</span>
        </div>
    </div>
    <div class="form_box">
        <!--基本信息-->
        <div class="info">
            <div class="info_title">
                <span class="info_title_01">基本信息</span>
            </div>
            <div id="companyInfo">
                <table class="form_info" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15%" align="right" class="tdbg">企业名称</td>
                        <td width="35%">
                            <span name="fullName">\${fullName}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">法人代表身份证号码</td>
                        <td width="35%">
                            <span name="lpIdentityCard">\${lpIdentityCard}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">企业性质</td>
                        <td width="35%">
                            <span name="companyProperty">\${companyProperty}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">成立时间</td>
                        <td width="35%">
                            <span name="establishDate">\${establishDate}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">职工人数</td>
                        <td width="35%">
                            <span name="crewSize">\${crewSize}</span>
                        <td width="15%" align="right" class="tdbg">注册资金（万元）</td>
                        <td width="35%">
                            <span name="registeredCapital">\${registeredCapital}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">固定资金（万元）</td>
                        <td width="35%">
                            <span name="fixedAssets">\${fixedAssets}</span>
                        <td width="15%" align="right" class="tdbg">自有流动资金（万元）</td>
                        <td width="35%">
                            <span name="workingCapital">\${workingCapital}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">企业通讯地址</td>
                        <td width="35%">
                            <a name="address" href="location.jsp?did=\${id}" target="_blank">\${address} </a>
                        </td>
                        <td width="15%" align="right">所属工商联</td>
                        <td width="35%">
                            <span name="belongTo">\${belongTo}</span>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <!--企业法人代表信息-->
        <div class="info">
            <div class="info_title">
                <span class="info_title_01">企业法人代表信息</span>
            </div>
            <div id="person">
                <table class="form_info" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="15%" align="right" class="tdbg">姓名</td>
                        <td width="35%">
                            <span name="realName">\${realName}</span>
                        <td width="15%" align="right" class="tdbg">性别</td>
                        <td width="35%">
                            <span name="lpSex">\${lpSex}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">出生年月</td>
                        <td width="35%">
                            <span name="lpBirthDt">\${lpBirthDt}</span>
                        <td width="15%" align="right" class="tdbg">籍贯</td>
                        <td width="35%">
                            <span name="lpNativePlace">\${lpNativePlace}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">民族</td>
                        <td width="35%">
                            <span name="lpNationality">\${lpNationality}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">政治面貌</td>
                        <td width="35%">
                            <span name="lpPolitical">\${lpPolitical}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">参加工作时间</td>
                        <td width="35%">
                            <span name="lpRecruitmentDate">\${lpRecruitmentDate}</span>
                        <td width="15%" align="right" class="tdbg">文化程度</td>
                        <td width="35%">
                            <span name="lpEducation">\${lpEducation}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">技术职称</td>
                        <td width="35%">
                            <span name="lpSkillJobTitle">\${lpSkillJobTitle}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">单位职务</td>
                        <td width="35%">
                            <span name="lpDeptPost">\${lpDeptPost}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">主要社会职务</td>
                        <td width="35%">
                            <span name="lpSocietyPost">\${lpSocietyPost}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">邮政编码</td>
                        <td width="35%">
                            <span name="postcode">\${postcode}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">传真</td>
                        <td width="35%">
                            <span name="fax">\${fax}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">电子邮箱</td>
                        <td width="35%">
                            <span name="email">\${email}</span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center" class="tdbg">个人简历</td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <textarea disabled="disabled" name="lpResume">\${lpResume}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center" class="tdbg">
                            特长及成就
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <textarea disabled="disabled" name="lpAchievement">\${lpAchievement}</textarea>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <!--企业生产经营信息-->
        <div class="info">
            <div class="info_title">
                <span class="info_title_01">企业生产（经营）信息</span>
            </div>
            <div id="production">
                <table class="form_info" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15%" align="right" class="tdbg">自有生产（经营）场地（㎡）</td>
                        <td width="35%">
                            <span name="ownSpace">\${ownSpace}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">租赁生产（经营）场地（㎡）</td>
                        <td width="35%">
                            <span name="tenancySpace">\${tenancySpace}</span>
                    </tr>
                    <tr>
                        <td colspan="4" align="center" class="tdbg">
                            企业生产（经营）主要品种
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <textarea disabled="disabled" name="majorVariety">\${majorVariety}</textarea>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <!--党组织建立情况-->
        <div class="info">
            <div class="info_title">
                <span class="info_title_01">党组织建立情况</span>
            </div>
            <div id="group">
                <table class="form_info" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="15%" align="right" class="tdbg">党组织类型</td>
                        <td width="35%">
                            <span name="partyCategory">\${partyCategory}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">党员人数</td>
                        <td width="35%">
                            <span name="partyPeoples">\${partyPeoples}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">党组织成立时间</td>
                        <td width="35%">
                            <span name="partyCrtDt">\${partyCrtDt}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">党组织负责人</td>
                        <td width="35%">
                            <span name="partyDutyMan">\${partyDutyMan}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">联系电话</td>
                        <td width="35%">
                            <span name="partyPhone">\${partyPhone}</span>
                        <td width="15%">&nbsp;</td>
                        <td width="35%">&nbsp;</td>
                    </tr>
                </table>
            </div>
        </div>

        <!--工会组织建立情况-->
        <div class="info">
            <div class="info_title">
                <span class="info_title_01">工会组织建立情况</span>
            </div>
            <div id="gonghui">
                <table class="form_info" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15%" align="right" class="tdbg">工会组织成立时间</td>
                        <td width="35%">
                            <span name="unionCrtDt">\${unionCrtDt}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">工会组织负责人</td>
                        <td width="35%">
                            <span name="unionDutyMan">\${unionDutyMan}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">联系电话</td>
                        <td width="35%">
                            <span name="unionPhone">\${unionPhone}</span>
                        </td>
                        <td width="15%">&nbsp;</td>
                        <td width="35%">&nbsp;</td>
                    </tr>
                </table>
            </div>
        </div>

        <!--构建和谐劳动关系情况-->
        <div class="info">
            <div class="info_title">
                <span class="info_title_01">构建和谐劳动关系情况</span>
            </div>
            <div id="labor">
                <table class="form_info" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="15%" align="right" class="tdbg">劳动合同签订人数</td>
                        <td width="35%">
                            <span name="laborContractNum">\${laborContractNum}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">是否建立劳动关系三方协调机制</td>
                        <td width="35%">
                            <span name="thirdLaborRelation">\${thirdLaborRelation}</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" class="tdbg">社会保险参保地</td>
                        <td width="35%">
                            <span name="socialInsuranceAddr">\${socialInsuranceAddr}</span>
                        </td>
                        <td width="15%" align="right" class="tdbg">社会保险登记证号</td>
                        <td width="35%">
                            <span name="socialInsuranceNum">\${socialInsuranceNum}</span>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <!--对社会公益事业作过何种贡献-->
    <div class="info">
        <div class="info_title">
            <span class="info_title_01">对社会公益事业作过何种贡献</span>
        </div>
        <div id="contribution">
            <table class="form_info" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="50%" align="center" class="tdbg">安排下岗职工再就业</td>
                    <td width="50%" align="center" class="tdbg">助学兴教</td>
                </tr>
                <tr>
                    <td width="50%">
                        <textarea disabled="disabled" name="reemployContribution">\${reemployContribution}</textarea>
                    </td>
                    <td width="50%">
                        <textarea disabled="disabled" name="educationContribution">\${educationContribution}</textarea>
                    </td>
                </tr>
                <tr>
                    <td width="50%" align="center" class="tdbg">帮困扶贫</td>
                    <td width="50%" align="center" class="tdbg">其他</td>
                </tr>
                <tr>
                    <td width="50%" align="center">
                        <textarea disabled="disabled" name="helpPoorContribution">\${helpPoorContribution}</textarea>
                    </td>
                    <td width="50%" align="center">
                        <textarea disabled="disabled" name="otherContribution">\${otherContribution}</textarea>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <!--纳税情况-->
    <div class="info">
        <div class="info_title">
            <span class="info_title_01">纳税情况</span>
        </div>
        <div id="tax">
            <table class="form_info" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="15%" align="right" class="tdbg">累计纳税（元）</td>
                    <td width="35%">
                        <span name="accTaxAmount">\${accTaxAmount}</span>
                    </td>
                    <td width="15%" align="right" class="tdbg">当年纳税（元）</td>
                    <td width="35%">
                        <span name="yearTaxAmount">\${yearTaxAmount}</span>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</script>

<script type="text/javascript">
    window.onload = function () {
        var url = window.location.search.substring(1);
        var cid = url.substring(url.indexOf('=') + 1);

        loadView(cid);

    }
</script>
</body>
</html>
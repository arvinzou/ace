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
    var default_page_list = [${cfg.default_page_list}];
</script>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>基本信息</title>
    <script type="text/javascript" src="${portalPath}/system/getUserProp.do"></script>
    <script type="text/javascript" src="${portalPath}/system/getButtonAuthority.do?id=${param.id}"></script>
    <link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/photoswipe.css">
    <link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/default-skin/default-skin.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/global/css/components.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/layout.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/themes/default.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/custom.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>

    <script src="${portalPath}/content/common/photoview/photoswipe.js"></script>
    <script src="${portalPath}/content/common/photoview/photoswipe-ui-default.min.js"></script>

    <script>

        var urlParams = {};

        function rsd(value, kernelKey, staticDictObjects) {
            try {
                if (!staticDictObjects) {
                    staticDictObjects = parent.staticDictObject;
                }

                var name = value;

                if ((value + "") && ("" + value).indexOf(',') < 0) {
                    if (staticDictObjects && kernelKey && staticDictObjects[kernelKey]) {
                        for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
                            if (staticDictObjects[kernelKey][i].CODE == value) {
                                name = staticDictObjects[kernelKey][i].NAME;
                                break;
                            }
                        }
                    }
                } else {
                    if (value) {
                        var nameArray = [];
                        var v = (value + "").split(',');
                        for (var j = 0; j < v.length; j++) {
                            for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
                                if (staticDictObjects[kernelKey][i].CODE == v[j]) {
                                    nameArray.push(staticDictObjects[kernelKey][i].NAME);
                                    break;
                                }
                            }
                        }
                        name = nameArray.join(',');
                    }
                }
            } catch (err) {
                console.log("渲染错误", value + ":" + kernelKey + ":" + err);
            }
            return name;
        }

        function odparse(kernelKey, staticDictObjects) {
            var rst = [];
            try {
                if (!staticDictObjects) {
                    staticDictObjects = parent.staticDictObject;
                }
                if (staticDictObjects && kernelKey && staticDictObjects[kernelKey]) {
                    for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
                        rst.push(staticDictObjects[kernelKey][i].CODE + ":"
                            + staticDictObjects[kernelKey][i].NAME);
                    }
                }
            } catch (err) {
                console.log("渲染错误", value + ":" + kernelKey + ":" + err);
            }
            return rst.join(";");
        }
        function getQueryString() {
            var qs = location.search.substr(1),
                args = {},
                items = qs.length ? qs.split("&") : [],
                item = null,
                len = items.length;

            for (var i = 0; i < len; i++) {
                item = items[i].split("=");
                var name = decodeURIComponent(item[0]),
                    value = decodeURIComponent(item[1]);
                if (name) {
                    args[name] = value;
                }
            }
            return args;
        }
        urlParams = getQueryString();
    </script>
</head>


<script type="text/javascript">
    var taskId = '${taskId}';
    var edit = '${edit}';
</script>

<style>
    .cover {
        width: 80px;
        height: 80px;
        object-fit: cover;
    }

    .ta-text {
        width: 100%;
        height: 100%;
        padding: 1px;
    }

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
        font-weight: bolder;
    }

    .info_title_01 {
        width: 300px;
        height: 35px;
        line-height: 35px;
        display: block;
        float: left;
        font-size: 16px;
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
        /*text-align: center;*/
    }


</style>


<body>
<div class="page-content">

    <div class="main_box">

    </div>
</div>


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
                            <textarea class="ta-text" disabled="disabled" name="lpResume">\${lpResume}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center" class="tdbg">
                            特长及成就
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <textarea class="ta-text" disabled="disabled"
                                      name="lpAchievement">\${lpAchievement}</textarea>
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
                            <textarea class="ta-text" disabled="disabled"
                                      name="majorVariety">\${majorVariety}</textarea>
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

        <div class="info">
            <div class="info_title">
                <span class="info_title_01">党员信息 </span>
            </div>


            <div id="pm" class="portlet-body">


                <table id="grid-table"></table>


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
                        <textarea class="ta-text" disabled="disabled" name="reemployContribution">\${reemployContribution}</textarea>
                    </td>
                    <td width="50%">
                        <textarea class="ta-text" disabled="disabled" name="educationContribution">\${educationContribution}</textarea>
                    </td>
                </tr>
                <tr>
                    <td width="50%" align="center" class="tdbg">帮困扶贫</td>
                    <td width="50%" align="center" class="tdbg">其他</td>
                </tr>
                <tr>
                    <td width="50%" align="center">
                        <textarea class="ta-text" disabled="disabled" name="helpPoorContribution">\${helpPoorContribution}</textarea>
                    </td>
                    <td width="50%" align="center">
                        <textarea class="ta-text" disabled="disabled"
                                  name="otherContribution">\${otherContribution}</textarea>
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

    <%--=====================================附件资源列表==============================--%>
    <%--电子表格--%>
    <div class="info">
        <div class="info_title row" style="margin: 0 !important;">
            <span class="info_title_01">电子表格</span>
        </div>
        <div class="row" style="margin: 0 !important;">
            {@each esAnnex as item, idx}
                <div class="my-gallery" style="float:left;padding:5px"><img src="\${item.fileUrl}" class="cover"/>
            {@/each}
        </div>
    </div>
    <%--同心助学--%>
    <div class="info">
        <div class="info_title row" style="margin: 0 !important;">
            <span class="info_title_01">同心助学</span>
        </div>
        <div class="row" style="margin: 0 !important;">
            {@each csAnnex as item, idx}
                <div class="my-gallery" style="float:left;padding:5px"><img src="\${item.fileUrl}" class="cover"/></div>
            </div>
            {@/each}
        </div>
    </div>
    <%--精准扶贫--%>
    <div class="info">
        <div class="info_title row" style="margin: 0 !important;">
            <span class="info_title_01">精准扶贫</span>
        </div>
        <div class="row" style="margin: 0 !important;">
            {@each paAnnex as item, idx}
                <div class="my-gallery" style="float:left;padding:5px"><img src="\${item.fileUrl}" class="cover"/></div>
            </div>
            {@/each}
        </div>
    </div>
    <%--企业党建--%>
    <div class="info">
        <div class="info_title row" style="margin: 0 !important;">
            <span class="info_title_01">企业党建</span>
        </div>
        <div class="row" style="margin: 0 !important;">
            {@each epbAnnex as item, idx}
                <div class="my-gallery" style="float:left;padding:5px"><img src="\${item.fileUrl}" class="cover"/></div>
            </div>
            {@/each}
        </div>
    </div>

    <%--市工商局_企业变更记录信息--%>
    {@if sgsj != null}
    <div class="info">
        <div class="info_title">
            <span class="info_title_01">市工商局-企业变更记录信息</span>
        </div>
        <div id="sgjs">
            <table id="tb_sgsj" class="form_info" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="50%" align="right" class="tdbg" style="text-align: center;">变更日期</td>
                    <td width="50%" name="updDateTime" style="text-align: center;">\${sgsj.变更日期}</td>
                </tr>
                <tr>
                    <td width="50%" align="center" class="tdbg">变更前内容</td>
                    <td width="50%" align="center" class="tdbg">变更后内容</td>
                </tr>
                <tr>
                    <td width="50%" height="150px" align="center">
                        <textarea class="ta-text" disabled="disabled" name="gsjBefore">
                            \${sgsj.变更前内容}
                        </textarea>
                    </td>
                    <td width="50%" height="150px" align="center">
                        <textarea class="ta-text" disabled="disabled" name="gsjAfter">
                            \${sgsj.变更后内容}
                        </textarea>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    {@/if}
    <%--市国税局_税务登记信息--%>
    {@if guosj != null}
    <div class="info">
        <div class="info_title">
            <span class="info_title_01">市国税局_税务登记信息</span>
        </div>
        <div id="guosj">
            <table class="form_info" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="15%" align="right" class="tdbg">法人代表</td>
                    <td width="35%"><span>\${guosj.法人代表}</span></td>
                    <td width="15%" align="right" class="tdbg">经营范围</td>
                    <td width="35%"><span>\${guosj.经营范围}</span></td>
                </tr>
                <tr>
                    <td width="15%" align="right" class="tdbg">法人代表身份证号</td>
                    <td width="35%"><span>\${guosj.法人代表身份证号}</span></td>
                    <td width="15%" align="right" class="tdbg">纳税人状态</td>
                    <td width="35%"><span>\${guosj.纳税人状态}</span></td>
                </tr>
                <tr>
                    <td width="15%" align="right" class="tdbg">经营地址</td>
                    <td width="35%"><span>\${guosj.经营地址}</span></td>
                    <td width="15%" align="right" class="tdbg">纳税人识别号</td>
                    <td width="35%"><span>\${guosj.纳税人识别号}</span></td>
                </tr>
                <tr>
                    <td width="15%" align="right" class="tdbg">注册地址</td>
                    <td width="35%"><span>\${guosj.注册地址}</span></td>
                    <td width="15%" align="right" class="tdbg">登记注册类型</td>
                    <td width="35%"><span>\${guosj.登记注册类型}</span></td>
                </tr>
                <tr>
                    <td width="15%" align="right" class="tdbg">纳税人名称</td>
                    <td width="35%"><span>\${guosj.纳税人名称}</span></td>
                    <td width="15%" align="right" class="tdbg">开业设立日期</td>
                    <td width="35%"><span>\${guosj.开业设立日期}</span></td>
                </tr>
                <tr>
                    <td width="15%" align="right" class="tdbg">行业</td>
                    <td width="35%"><span>\${guosj.行业}</span></td>
                    <td width="15%" align="right" class="tdbg"></td>
                    <td width="35%"><span></span></td>
                </tr>
            </table>
        </div>
    </div>
    {@/if}
    <%--市人社局_企业养老保险单位参保信息--%>
    {@if srsj != null}
    <div class="info">
        <div class="info_title">
            <span class="info_title_01">市人社局-企业养老保险单位参保信息</span>
        </div>
        <div id="srsj">
            <table class="form_info" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="15%" align="right" class="tdbg">累计纳税（元）</td>
                    <td width="35%">
                        <span name="accTaxAmount">\${srsj.A}</span>
                    </td>
                    <td width="15%" align="right" class="tdbg">当年纳税（元）</td>
                    <td width="35%">
                        <span name="yearTaxAmount">\${srsj.B}</span>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    {@/if}
</script>

<script type="text/javascript">
    window.onload = function () {
        var url = window.location.search.substring(1);
        var cid = url.substring(url.indexOf('=') + 1);

        loadView(cid);
        //api content
    }
</script>


<script src="${portalPath}/content/common/assets/global/plugins/respond.min.js"></script>
<script src="${portalPath}/content/common/assets/global/plugins/excanvas.min.js"></script>
<script src="${portalPath}/content/common/assets/global/plugins/ie8.fix.min.js"></script>
<![endif]-->
<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}"
        type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=${cfg.version}"
        type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/js.cookie.min.js?v=${cfg.version}"
        type="text/javascript"></script>
<script src="${portalPath}/content/common/js/init-rem.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>

<script src="${pageContext.request.contextPath}/content/service/company/act.js"></script>

<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/pm/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/pm/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/pm/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/pm/view.js?version=${cfg.version}"></script>


<div id="j-pswp" class="pswp" role="dialog" aria-hidden="true">
    <div class="pswp__bg"></div>
    <div class="pswp__scroll-wrap">
        <div class="pswp__container">
            <div class="pswp__item"></div>
            <div class="pswp__item"></div>
            <div class="pswp__item"></div>
        </div>
        <div class="pswp__ui pswp__ui--hidden">
            <div class="pswp__top-bar">
                <div class="pswp__counter"></div>
                <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
                <button class="pswp__button pswp__button--share" title="Share"></button>
                <button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button>
                <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
                <div class="pswp__preloader">
                    <div class="pswp__preloader__icn">
                        <div class="pswp__preloader__cut">
                            <div class="pswp__preloader__donut"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                <div class="pswp__share-tooltip"></div>
            </div>
            <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)"></button>
            <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)"></button>
            <div class="pswp__caption">
                <div class="pswp__caption__center"></div>
            </div>
        </div>
    </div>
</body>
</html>
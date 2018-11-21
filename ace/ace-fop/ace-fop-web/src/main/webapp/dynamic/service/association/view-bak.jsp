<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%
    String companyId = request.getParameter("companyId");
//    request.setAttribute("edit", "true");
//    if (CommonUtils.isBlank(companyId)) {
//        companyId = String.valueOf(new java.util.Date().getTime());
//        request.setAttribute("edit", "false");
//    }
    request.setAttribute("companyId", companyId);
%>
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

<jsp:include page="../../common/common.jsp"/>
<body>
<div class="page-content">
    <%--<div class="widget-box" id="widget-box">--%>
    <%--<div class="widget-header">--%>
    <%--<h5 class="widget-title smaller">查看详情</h5>--%>
    <%--<div class="widget-toolbar"></div>--%>
    <%--</div>--%>

    <%--<div class="widget-body">--%>
    <%----%>
    <%--</div>--%>
    <%--</div>--%>

    <%--render 渲染div--%>
    <div class="main_box">

    </div>
</div>

<jsp:include page="../../common/footer-1.jsp"/>

<script src="${pageContext.request.contextPath}/content/common/juicer/juicer-min.js"></script>
<script src="${pageContext.request.contextPath}/content/service/association/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/association/controller.js"></script>


<jsp:include page="../../common/footer-2.jsp"/>

<script id="tpl-view-page" type="text/template">

    <div class="main_box">
        <div class="title_box">
            <div class="col-lg-12 col-md-12 col-xs-12" style="text-align: center">
                <span class="title_box_title_01">团体信息</span>
            </div>
        </div>
        <div class="form_box">
            <!--基本信息-->
            <div class="info">
                <div class="info_title"><span class="info_title_01">基本信息</span></div>
                <div class="info_box">
                    <table class="form_info" cellspacing="0" cellpadding="0" border="1">
                        <tr>
                            <td align="right" class="tdbg">团体名称</td>
                            <td>
                                <input type="text" name="fullName" readonly="true" value="\${fullName}"/></td>
                            <td align="right" class="tdbg">创建时间</td>
                            <td>
                                <input type="text" name="establishDate" readonly="true" value="\${establishDate}"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" class="tdbg">办公电话</td>
                            <td><input type="text" name="phoneNumber" readonly="true" value="\${phoneNumber}"/></td>
                            <td align="right" class="tdbg">地址</td>
                            <td>
                                <a name="address" href="location.jsp?did=\${id}" target="_blank">\${address} </a>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" class="tdbg">所属工商联</td>
                            <td><input type="text" name="belongTo" readonly="true" value="\${belongTo}"/></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr style="display: none;">
                            <td colspan="4">
                                <input type="text" id="latitude"/>
                                <input type="text" id="longitude"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <!--商协会领导班子情况-->
            <div class="info">
                <div class="info_title"><span class="info_title_01">商协会领导班子情况</span></div>
                <div class="info_box">
                    <table class="form_info" cellpadding="0" cellspacing="0" border="1">
                        <tr>
                            <td align="right" class="tdbg">理事数量</td>
                            <td><input type="text" name="directorNum" readonly="true" value="\${directorNum}"/></td>
                            <td align="right" class="tdbg">副会长数量</td>
                            <td><input type="text" name="viceNum" readonly="true" value="\${viceNum}"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <!--商协会领导班子企业情况-->
            <%--<div class="info">--%>
            <%--<div class="info_title"><span class="info_title_01">商协会领导班子企业情况</span></div>--%>
            <%--<div class="info_box">--%>
            <%--<table class="form_info" cellspacing="0" cellpadding="0" border="1">--%>
            <%--<tr>--%>
            <%--<td align="right" class="tdbg">姓名</td>--%>
            <%--<td><input type="text" name="pname"  readonly="true" value="\${viceNum}"/></td>--%>
            <%--<td align="right" class="tdbg">商协会职务</td>--%>
            <%--<td><input type="text" name="assPost" ng-model="group.assPost" placeholder="输入职务"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td align="right" class="tdbg">联系电话</td>--%>
            <%--<td><input type="text" name="phoneNum" ng-model="group.phoneNum" placeholder="输入电话"/></td>--%>
            <%--<td align="right" class="tdbg">企业名称(多个企业逗号隔开)</td>--%>
            <%--<td><input type="text" name="companyName" ng-model="group.companyName"--%>
            <%--placeholder="输入企业名称"/></td>--%>
            <%--</tr>--%>
            <%--</table>--%>
            <%--</div>--%>
            <%--</div>--%>
        </div>
    </div>
</script>

<script type="text/javascript">
    window.onload = function () {
        var url = window.location.search.substring(1);
        var id = url.substring(url.indexOf('=') + 1);
        //
        loadView(id);

    }
</script>
</body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <title>总控全年排程设置</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">

            </div>

            <div class="col-md-9">

                <form id="fm-search">

                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <select id="month" name="month" onchange="setParams(this.value)">
                            <option value="m1">一月</option>
                            <option value="m2">二月</option>
                            <option value="m3">三月</option>
                            <option value="m4">四月</option>
                            <option value="m5">五月</option>
                            <option value="m6">六月</option>
                            <option value="m7">七月</option>
                            <option value="m8">八月</option>
                            <option value="m9">九月</option>
                            <option value="m10">十月</option>
                            <option value="m11">十一月</option>
                            <option value="m12">十二月</option>
                        </select>
                    </div>
                </form>
            </div>

        </div>


        <div class="table-scrollable">
            <table class="table table-hover table-light" style="overflow: auto;">
                <thead>
                <tr>
                    <th width="15%">日</th>
                    <th width="15%">模式</th>
                    <th width="15%">操作</th>

                </tr>
                </thead>
                <tbody id="page-list">

                </tbody>
            </table>
        </div>


    </div>
</div>


<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${+index+1}</td>
        <td> \${parseStatus(item)}</td>
        <td>

            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
            <a onclick="update();">更新</a>

        </td>
    </tr>
    {@/each}
</script>
﻿
<style>
    <%--custom style--%>
</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>


</html>

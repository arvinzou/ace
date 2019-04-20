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
    <title>节点能耗信息</title>
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
            <div class="col-md-8">
            </div>

            <div class="col-md-4">

                <form id="fm-search">
                    <div class="btn-group" role="group" style="float:left;width: 70%;">
                        <select id="topNode" name="nodeCode"  class="form-control" style="height: 31px;"
                                onchange="setParams('nodeCode',this.value)">
                        </select>
                    </div>
                    <span style="width: 10%;"><button class="btn  btn-default search_btn" type="submit">搜索</button></span>
                </form>
            </div>

        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="25%"> 节点名称</th>
                    <th width="25%"> 能耗类型</th>
                    <th width="25%"> 能耗统计</th>
                    <th width="25%"> 统计时间</th>
                </tr>
                </thead>
                <tbody id="page-list">

                </tbody>
            </table>
        </div>
        <div class="paginationbar">
            <ul class="pagination" id="pagination1"></ul>
        </div>

    </div>

</div>

<script id="nodelist-tpl" type="text/template">
    {@each data as item, index}
    <option value="\${item.code}">\${item.name}</option>
    {@/each}
</script>
<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td width="25%">\${item.topNodeVo.name}</td>
        <td width="25%">\${item.itemKey}</td>
        <td width="25%">\${item.itemValue}</td>
        <td width="25%">\${item.createDate}</td>
    </tr>
    {@/each}
</script>

<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

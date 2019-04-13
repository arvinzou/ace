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
    <title>场景控制</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body light">
        <div id="page-list">

        </div>
        <br>
        <div id="station-list">

        </div>
        <br>
        <br>
        <div id="allCheck" style="display: none">
            <input name="checkList" type="checkbox" style="width: 16px; height:16px;"/>全选
            <span style="color:#1890FF">当前选中<span id="checkCount">0</span>条</span>
        </div>


        <br>
        <br>
        <div id="animaLnk-list">
            <ul class="videolist">

            </ul>
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
    <ul>
        <li><a href="#" data-id="\${item.id}" data-title="\${item.name}" onclick="stationList('\${item.code}');">\${item.name}</a>
        </li>

    </ul>
    {@/each}
</script>

<%--站点列表模板--%>
<script id="tp2-list" type="text/template">

    {@each data as item, index}
    <ul>
        <li><a href="#" data-id="\${item.id}" data-title="\${item.name}"
               onclick="animaList(this,'\${item.code}');"><font color="black">\${item.name}</font></a></li>
    </ul>
    {@/each}
</script>

<%--节目列表模板--%>
<script id="tp3-list" type="text/template">
    {@each data as item, index}
    <%-- <ul class="videolist">
         <li><div>
             <input name="checkanima" type="checkbox" value="\${item.id}" onclick="checkanima();"/>
             <span>\${item.topBuildingName}</span>
            </div>
             <video src="\${item.prePlayUrl}" controls="controls">
             </video>
         </li>

     </ul>--%>
    {@/each}
</script>


<style>
    <%--custom style--%>
</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>

<%--easyui--%>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>

</html>

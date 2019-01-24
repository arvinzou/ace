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
    <title>事故分析</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet" type="text/css"/>

</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>

<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">

            <form action="#" id="fm-search">
                <div class="col-md-5">

                    <div class="btn-group" role="group1" style="float:left;padding-right:10px">
                        <button type="button" authority="false" class="btn btn-default"
                                onclick="setParams('category','year');">年度查询
                        </button>
                        <button type="button" authority="false" class="btn btn-default"
                                onclick="setParams('category','season');">季度查询
                        </button>
                        <button type="button" authority="false" class="btn btn-default"
                                onclick="setParams('category','month');">月份查询
                        </button>
                    </div>

                    <div class="btn-group" role="group2" style="float:left;">
                        <button type="button" authority="false" class="btn btn-default"
                                onclick="setParams('field','deadthToll');">死亡人数分析
                        </button>
                        <button type="button" authority="false" class="btn btn-default"
                                onclick="setParams('field','injuries');">受伤人数分析
                        </button>
                    </div>
                </div>

                <div class="col-md-5">
                    <div class="input-group" style="float: right;padding: 10px;">
                        路段<input type="text" id="roadSectionId" class="form-control" style="width:235px"
                                 name="roadSectionId">
                    </div>
                    <div class="input-group" style="float: right;padding: 10px;">
                        路长<input type="text" id="roadManId" class="form-control" style="width:235px" name="roadManId">
                    </div>
                </div>

            </form>

        </div>


        <div class="table-scrollable" style="width: 100%;height:100%">
            <div id="div-chart">

            </div>
        </div>
        <div class="paginationbar">

        </div>

    </div>

</div>

<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>

<script type="text/javascript">
    window.onresize = function () {
        var windowWidth = $(".page-content").width();
        $('#div-chart').attr('style', 'width: ' + windowWidth + ';height: 600px');
        initChart(params);
    }
</script>
</body>

<jsp:include page="/dynamic/common/footer.jsp"/>

<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>


<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
<script src="js/echarts.js?v=${cfg.version}"></script>

</html>

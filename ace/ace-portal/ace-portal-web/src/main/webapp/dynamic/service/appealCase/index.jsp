<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>诉求</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<script type="text/javascript">


</script>
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<link rel="stylesheet" href="${portalPath}/content/common/assets/css/colorbox.css"/>
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



                    标题： <input name="title" type="text" style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/appealCase/findAppealCaseList.do">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/appealCase/insertAppealCase.do">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/appealCase/updateAppealCase.do">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/appealCase/deleteAppealCaseByAppealCaseId.do">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>

                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>


</div>
<div id="dialog-message-accept" class="hide">
    <form action="/www/appealCase/updateAccept.do" id="fm-accept">
        <fieldset>
            受理科室： <input id="answer_dept" type="text" class="FormElement ui-widget-content ui-corner-all"/>
        </fieldset>
        <div class="space-6"></div>
        <fieldset>
            经办领导： <input id="operator" type="text" class="FormElement ui-widget-content ui-corner-all"/>
        </fieldset>
    </form>
</div>

<div id="dialog-message-progress" class="hide">
    <form action="/www/appealCase/updateDetailsOfProgress.do" id="fm-progress">
        <fieldset>
            处理详情：<textarea id="details_of_progress" cols="20" rows="2" style="width:550px;height:120px"
                           class="FormElement ui-widget-content ui-corner-all"></textarea>
        </fieldset>
    </form>
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



    <h5 class="header-title">诉求内容</h5>

    <div class="row" style="padding:10px">
        <span id="content"></span>

    </div>

    <div class="row" style="padding:10px">
        <span id="createDate"></span>

    </div>

    <h5 class="header-title">处理详情</h5>

    <div class="row" style="padding:10px">
        <span id="detailsOfProgress"></span>

    </div>


    <h5 class="header-title">答复内容</h5>

    <div class="row" style="padding:10px">
        <span id="answerContent"></span>

    </div>
    <div class="row" style="padding:10px">
        <span id="answerTime"></span>

    </div>

</div>

<jsp:include page="../../common/footer-1.jsp"/>

<script src="${portalPath}/content/common/assets/js/uncompressed/jquery.colorbox.js"></script>

<script src="${pageContext.request.contextPath}/content/service/appealCase/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/appealCase/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/appealCase/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/appealCase/view.js?version=${cfg.version}"></script>


<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script src="${pageContext.request.contextPath}/content/service/appealCase/upload.js?version=${cfg.version}"></script>

<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
        parent.autoWidth();
    }
</script>
<style>
    .photo {
        height: 25px;
        max-height: 25px;
        max-width: 25px;
        vertical-align: middle;
    }

    #cboxContent {
        background-color: rgb(255, 255, 255);
        border-width: 1px;
        border-style: solid;
        border-color: #ddd;
        border-image: initial;
        padding: 5px;
    }

    .ace-thumbnails > li {
        float: left;
        display: block;
        position: relative;
        overflow: hidden;
        margin: 2px;
        border-width: 1px;
        border-style: solid;
        border-color: #ddd;
        border-image: initial;
    }
</style>
</body>
</html>
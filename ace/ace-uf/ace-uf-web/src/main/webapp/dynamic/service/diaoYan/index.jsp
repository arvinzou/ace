<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>推文</title>
</head>
<jsp:include page="../../common/common.jsp"/>

<link rel="stylesheet" href="${portalPath}/content/common/assets/css/colorbox.css"/>
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


                    名称： <input name="name" type="text" style="width: 200px;"/>
                    作者： <input name="author" type="text" style="width: 200px;"/>
                    媒体：<input name="media" type="text" style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/diaoYan/findDiaoYanList.do">
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>

                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/diaoYan/insertDiaoYan.do">
                        <i
                                class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/diaoYan/updateDiaoYan.do">
                        <i
                                class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/diaoYan/deleteDiaoYanByDiaoYanId.do">
                        <i
                                class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
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

<div id="dialog-message-view" class="hide">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem"><span class="labelItemHeader">
活动名称</span>
            <br>
            <span id="name">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
发布作者</span>
            <br>
            <span id="author">
</span>
        </div>

        <div class="labelItem"><span class="labelItemHeader">
作者单位</span>
            <br>
            <span id="dept">
</span>
        </div>


        <div class="labelItem"><span class="labelItemHeader">
媒体</span>
            <br>
            <span id="media">
</span>
        </div>

        <div class="labelItem"><span class="labelItemHeader">
发布时间</span>
            <br>
            <span id="published">
</span>
        </div>
    </div>
    <h5 class="header-title">网址</h5>
    <div class="row" style="padding:10px" id="url">
    </div>
    <h5 class="header-title">调研封面</h5>
    <div class="row" style="padding:10px" id="cover">
    </div>
    <h5 class="header-title">详细情况</h5>
    <div class="row" style="padding:10px" id="content">
    </div>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem"><span class="labelItemHeader">
创建人姓名</span>
            <br>
            <span id="createUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
入库日期</span>
            <br>
            <span id="createDate">
</span>
        </div>

        <div class="labelItem"><span class="labelItemHeader">
最后更新人姓名</span>
            <br>
            <span id="lastModifyUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新时间</span>
            <br>
            <span id="lastModifyDate">
</span>
        </div>
    </div>
</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script src="${pageContext.request.contextPath}/content/service/diaoYan/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/diaoYan/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/diaoYan/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/diaoYan/view.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/diaoYan/upload.js?version=${cfg.version}"></script>

<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
        parent.autoWidth();
    }
</script>
</body>
</html>
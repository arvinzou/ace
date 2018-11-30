<%@page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>运营分析</title>

</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>

<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<div class="portlet light ">

    <div class="portlet-body">


    <div class="row">
        <div class="col-md-2">


            <div class="div-left header-title-custom">即将进行的会议</div>
            <div class="div-right header-title-custom">
                <div style="text-align:right">
                    <a class="blue" href="javascript:viewMeeting()" data-rel="tooltip" data-placement="top"
                       title="刷新"><i
                            class="ace-icon glyphicon glyphicon-refresh"></i></a>
                </div>
            </div>



            <table id="meeting-grid" class="table table-bordered">


            </table>

            <div style="margin-top:5px;background:#eeeeee">
                <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input"
                       autocomplete="off"/>

                <a class="blue" href="javascript:act3()"><i class="ace-icon fa fa-search nav-search-icon"></i></a>
            </div>
        </div>
        <div class="col-md-7">
            <jsp:include page="/dynamic/common/meeting.jsp"/>

        </div>
        <div class="col-md-3">

            <div class="row">
                <div class="div-left header-title-custom">部门任务关闭情况</div>
                <div class="div-right header-title-custom">
                    <div style="text-align:right">
                        <a class="blue" href="javascript:myTask()" data-rel="tooltip" data-placement="top"
                           title="执行我的任务"><i
                                class="ace-icon fa fa-plus-circle"></i></a>
                    </div>
                </div>
                <div id="ct2" style="width:300px;height:200px"></div>
            </div>
            <div class="row">
                <h5 class="header-title">会议统计</h5>
                <div id="ct1" style="width:300px;height:200px"></div>
            </div>
        </div>

    </div>


    </div>
</div>



<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<!-- /.page-content -->
<jsp:include page="/dynamic/common/footer.jsp"/>


<div class="modal fade"  role="dialog" id="modal-upload">
    <div class="modal-dialog" role="document" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">文件上传</h4>
            </div>
            <div class="modal-body">
                <div id="uploader">
                    <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button"  authority="false" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/gray/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>

<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/js/dataTable/jquery.dataTables.min.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/js/dataTable/dataTables.bootstrap.min.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/index/view.js?version=${cfg.version}&t=1"></script>
<script
        src="${pageContext.request.contextPath}/content/index/config-1.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/index/config-2.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/js/xcheck/XCheck.js?version=${cfg.version}"></script>
				<script
				        src="${pageContext.request.contextPath}/content/index/meeting.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/index/controller.js?version=${cfg.version}"></script>





<script
        src="${pageContext.request.contextPath}/content/service/meeting/upload.js"></script>
<script type="text/javascript">

</script>

</body>
</html>
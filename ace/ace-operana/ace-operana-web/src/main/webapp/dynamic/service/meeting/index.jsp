<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <title>系统配置</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-4">

                <button type="button" class="btn  green" id="btn-view-add" authority="${pageContext.request.contextPath}/meeting/insertMeeting.do"></button>



            </div>
            <div class="col-md-4">
            </div>

            <div class="col-md-4">

                <form action="#" id="fm-search" >


                    <div class="input-group">
                        <input type="text"
                               name="name"
                               class="form-control"
                               placeholder="请输入名称">
                        <span class="input-group-btn">
                                                                <button class="btn  btn-default search_btn"  id="btn-search"
                                                                        authority="${pageContext.request.contextPath}/meeting/findMeetingList.do">
                                                                        搜索
                                                                </button>
                                                            </span>
                    </div>
                </form>
            </div>

        </div>

        <table id="grid-table"></table>

        <div class="paginationbar"><ul id="grid-pager" class="pagination"></ul></div>
    </div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<div class="modal fade"  role="dialog" id="modal-meeting">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" authority="false"  class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">会议设置</h4>
            </div>
            <div class="modal-body" style="padding: 50px;">
							<jsp:include page="/dynamic/common/meeting.jsp" />
            </div>
            <div class="modal-footer">
                <button type="button" authority="false"  class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


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
<jsp:include page="/dynamic/common/footer.jsp" />
<script
        src="${portalPath}/content/common/js/xcheck/XCheck.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/js/dataTable/jquery.dataTables.min.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/js/dataTable/dataTables.bootstrap.min.js?version=${cfg.version}"></script>

<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/meeting/upload.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/index/meeting.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/meeting/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/meeting/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/meeting/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/meeting/view.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>


<style>

</style>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <title>银行</title>
</head>
<link rel="stylesheet" href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <form action="#" id="fm-search" >
                <div class="col-md-2 toolbar">

                        <button type="button" class="btn  green" id="btn-view-add" authority="false">添加</button>



                </div>

                <div class="col-md-4">



                </div>
                <div class="col-md-3">

                    <div class="btn-group" role="group"  style="float:right;padding-right:5px">
                        状态 <input class="easyui-combobox" style="width: 150px;height:30px;" name="status"
                                  data-options="
                                url:'${portalPath}/dict/findListByCategoryId.do?categoryId=149&selected=false',
                                method:'get',
                                valueField:'code',
                                textField:'name',
                                panelHeight:'auto'">
                    </div>

                </div>

                <div class="col-md-3">
                    <div class="input-group">
                        <input type="text"
                               name="fullName"
                               class="form-control"
                               placeholder="请输入名称">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn"  id="btn-search"
                                    authority="false">
									搜索
							</button>
						</span>
                    </div>

                </div>

            </form>
        </div>

        <table id="grid-table"></table>

        <div class="paginationbar"><ul id="grid-pager" class="pagination"></ul></div>
    </div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


<div class="modal fade"  role="dialog" id="modal-upload">
    <div class="modal-dialog" role="document" style="width: 830px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">图片上传</h4>
            </div>
            <div class="modal-body">

                <div id="uploader">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade"  role="dialog" id="modal-file">
    <div class="modal-dialog" role="document" style="width: 830px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">图片</h4>
            </div>
            <div class="modal-body">

                <div id="load" class="loading"></div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/dynamic/common/footer.jsp" />

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

<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/association/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/association/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/association/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/association/view.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/company/upload.js?version=${cfg.version}"></script>




<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/export.js?version=${cfg.version}"></script>
</body>
</html>
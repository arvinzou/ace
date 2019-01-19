<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>班级文件</title>
</head>
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>

<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">
    <div class="portlet-body">
        <div class="row custom-toolbar">
            <form action="#" id="fm-search">
                <div class="col-md-4 toolbar">


                    <button type="button" class="btn  green" id="btn-view-add" authority="false">上传</button>

                </div>
                <div class="col-md-8">

                    <div class="btn-group" role="group" style="float:left;padding-right:15px">
                        <button type="button"
                                authority="false" class="btn btn-default active" onclick="setParams('category','1');">文档
                        </button>
                        <button type="button"
                                authority="false" class="btn btn-default" onclick="setParams('category','2');">图片
                        </button>
                    </div>

                    <div class="input-group" style="float: left;margin-top: 5px;margin-right: 5px;">
                        <label>班次 </label>
                    </div>
                    <div class="input-group" style="float: left;margin-right: 5px;" id="select1">

                    </div>
                    <div class="input-group">
                        <input type="text" name="title" class="form-control" placeholder="请输入文件名称">
                        <span class="input-group-btn">
									<button class="btn  btn-default search_btn" id="btn-search" authority="false">
										搜索
									</button>
								</span>
                    </div>

                </div>

            </form>
        </div>

        <table id="grid-table"></table>

        <div class="paginationbar">
            <ul id="grid-pager" class="pagination"></ul>
        </div>
    </div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<jsp:include page="/dynamic/common/footer.jsp"/>


<script id="tpl-select-list" type="text/template">

    <select name="classesId" id="classesId" class="form-control" style="height: 30px;line-height: 35px;">
        {@each data as item, index}
        <option value="\${item.id}">\${item.name}</option>
        {@/each}
    </select>
</script>

<div class="modal fade" role="dialog" id="modal-import">
    <div class="modal-dialog" role="document" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" authority="false" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">文件上传</h4>
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


<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>

<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>


<script src="${pageContext.request.contextPath}/content/service/files/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/files/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/files/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/files/view.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/files/upload.js?version=${cfg.version}"></script>

<%--权限管理--%>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>


</body>
<style>
    /* css code area*/
</style>
</html>

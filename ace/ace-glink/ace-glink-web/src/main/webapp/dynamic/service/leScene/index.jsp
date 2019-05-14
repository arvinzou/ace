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
    <title>弱电场景管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>

    <link rel="stylesheet" href="css/style.css">
    <%--sweetalert--%>
    <script src="${pageContext.request.contextPath}/content/common/js/sweetalert/js/sweet-alert.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/content/common/js/sweetalert/css/sweet-alert.css">
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-7">
                <a href="javascript:syncData()" class="btn green sync-data">数据同步</a>
            </div>

            <div class="col-md-5">

                <form id="fm-search">

                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入场景编号/场景名称">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn" type="submit"> 搜索</button>
                        </span>
                    </div>
                </form>
            </div>

        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="15%"> 场景编号</th>
                    <th width="15%"> 场景名称</th>
                    <th width="25%"> 场景描述</th>
                    <th width="15%"> 播放状态</th>
                    <th width="15%">操作</th>
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


<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.sceneNum}</td>
        <td> \${item.sceneName}</td>
        <td> \${item.sceneDepict}</td>
        <td> \${parsePlayStatus(item.playStatus)}</td>
        <td>
            <a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">预览</a>
        </td>
    </tr>
    {@/each}
</script>
﻿
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-preview">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<script id="tpl-preview" type="text/template">
    <div class="form-group hide">
        <label class="col-md-2 view-label">主键</label>
        <div class="col-md-10">
            \${data.o.id}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">场景编号</label>
        <div class="col-md-10">
            \${data.o.sceneNum}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">场景名称</label>
        <div class="col-md-10">
            \${data.o.sceneName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">场景描述</label>
        <div class="col-md-10">
            \${data.o.sceneDepict}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">播放状态</label>
        <div class="col-md-10">
            \${parsePlayStatus(data.o.playStatus)}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">备注</label>
        <div class="col-md-10">
            \${data.o.remark}
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2 view-label">预览视频</label>
        <div class="col-md-10">

            <video src='\${data.o.playUrl}' style='width: 400px;height: 400px' controls=\"controls\"></video>

        </div>
    </div>
</script>
<style>

</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

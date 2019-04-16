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
    <title>节目上传</title>
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
                <%--<a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>--%>
            </div>

            <div class="col-md-4">

                <form id="fm-search">
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入建筑物名称">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn"
                                    type="submit">
                                    搜索
                            </button>
                        </span>
                    </div>
                </form>
            </div>

        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="10%">编号</th>
                    <th width="20%">建筑物名称</th>
                    <th width="20%">分区名称</th>
                    <th width="20%">地址</th>
                    <th width="10%">节目数量</th>
                    <th width="20%">操作</th>
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
        <td width="10%">\${item.code}</td>
        <td width="20%">\${item.name}</td>
        <td width="20%">\${item.topStationVo.subareaName}</td>
        <td width="20%">\${item.address}</td>
        <td width="10%">\${item.animaCount}</td>
        <td>
            <a href="#" data-toggle="modal" data-id="\${item.code}" data-title="\${item.name}"
               data-target="#modal-option">下发</a>
            <a href="#" data-toggle="modal" data-id="\${item.code}" data-title="\${item.name}"
               data-target="#modal-preview">节目列表</a>
        </td>
    </tr>
    {@/each}
</script>
﻿
<div class="modal fade " id="modal-option">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 1000px;height: 700px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">下发节目</h4>
            </div>
            <div class="modal-body" style="width: 100%;height: 550px;">
                    <div class="form-body">
                        <div class="row custom-toolbar">
                            <div class="col-md-3"></div>
                            <div class="col-md-5"></div>
                            <div class="col-md-4">
                                    <div class="input-group">
                                        <input type="text" name="name" id="animaName" class="form-control" placeholder="请输入节目名称">
                                        <span class="input-group-btn">
                                            <button class="btn  btn-default search_btn" type="button"  onclick="query();">搜索</button>
                                        </span>
                                    </div>
                            </div>
                        </div>
                        <div class="row" id="animaList">

                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
               <%-- <button type="button" class="btn green confirm">确定</button>--%>
            </div>
        </div>
    </div>
</div>

<!--节目清单渲染-->
<script id="animaList-tpl" type="text/template">
    {@each data as item, index}
    <div class="layout-user">
        <a href="javascript:bindAnimaEvent('\${item.code}','\${item.prePlayUrl}')">
            <img class="photo"
                 src="\${item.preImgUrl}"></a>
        <div style="text-align:center"> \${item.name}</div>
    </div>
    {@/each}
</script>

<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">该建筑下的节目列表</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body">
                        <div class="table-scrollable">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th width="30%">节目名称</th>
                                    <th width="20%">封面</th>
                                    <th width="20%">时长</th>
                                    <th width="10%"></th>
                                    <th width="20%">操作</th>
                                </tr>
                                </thead>
                                <tbody id="fm-preview">

                                </tbody>
                            </table>
                        </div>
                        <div class="paginationbar">
                            <ul class="pagination" id="pagination2"></ul>
                        </div>
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
    {@each data as item, index}
    <tr>
        <td width="30%">\${item.animaResVo.name}</td>
        <td width="20%">
            {@if item.animaResVo.preImgUrl}
            <img src="\${item.animaResVo.preImgUrl}" style="width: 70px;height: 70px;object-fit: cover">
            {@/if}
        </td>
        <td width="20%">\${item.animaResVo.duration}</td>
        <td width="10%">\${item.animaResVo.rsoWidth} x \${item.animaResVo.rsoHeight}</td>
        <td>
            <a href="#" data-toggle="modal" data-url="\${item.animaResVo.prePlayUrl}" data-type="\${item.animaResVo.type}"
               data-target="#modal-preUrl">预览</a>
            <a href="javascript:removeAnimaEvent('\${item.id}')" >移除</a>
        </td>
    </tr>
    {@/each}
</script>

<div class="modal fade " id="modal-preUrl">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 900px;height: 700px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">媒体文件预览</h4>
            </div>
            <div class="modal-body" style="width: 90%;height: 550px;">
                <form class="form-horizontal"  role="form">
                    <div class="form-body" id="sourcePreview">

                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn green status">确定</button>
            </div>
        </div>
    </div>
</div>

<style>
    .layout-user {
        width: 120px;
        height: 90px;
        float: left;
        margin: 10px 10px 10px;
        text-align: center;
    }

    .photo {
        height: 90px;
        width: 120px;
        vertical-align: middle;
        border-radius: 60px;
        object-fit: cover;
    }
</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

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
    <title>媒体文件</title>
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
            <div class="col-md-3">
                <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>
            </div>

            <div class="col-md-9">

                <form id="fm-search">
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入节目名称">
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
                    <th width="10%"> 编号</th>
                    <th width="10%"> 类别</th>
                    <th width="15%"> 名称</th>
                    <th width="10%"> 封面</th>
                    <th width="10%"> 时长</th>
                    <th width="15%"> 播放地址</th>
                    <th width="10%">分辨率</th>
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
        <td width="10%"> \${item.code}</td>
        <td width="10%"> \${parseSourseType(item.type)}</td>
        <td width="15%"> \${item.name}</td>
        <td width="10%">
            {@if item.preImgUrl}
            <img src="\${item.preImgUrl}" style="width: 70px;height: 70px;object-fit: cover">
            {@/if}
        </td>
        <td width="10%">\${item.duration}</td>
        <td width="15%">\${item.playUrl}</td>
        <td width="10%">\${item.rsoWidth} x \${item.rsoHeight}</td>
        <td>
            ﻿<a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            <a href="#" data-toggle="modal" data-url="\${item.prePlayUrl}" data-type="\${item.type}"
               data-target="#modal-show">预览</a>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>

            <a href="javascript:del('\${item.id}');">删除</a>

        </td>
    </tr>
    {@/each}
</script>
﻿
<div class="modal fade " id="modal-show">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 900px;height: 700px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">媒体文件预览</h4>
            </div>
            <div class="modal-body" style="width: 90%;height: 550px;">
                <form class="form-horizontal" id="fm-status" role="form">
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



<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
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
           <div class="form-group">
                <label class="col-md-2 view-label">节目编号</label>
                <div class="col-md-10">
                    \${data.o.code}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">节目名称</label>
                <div class="col-md-10">
                    \${data.o.name}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">节目类型</label>
                <div class="col-md-10">
                    \${parseSourseType(data.o.type)}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">播放时长</label>
                <div class="col-md-10">
                    \${data.o.duration}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">播放地址</label>
                <div class="col-md-10">
                    \${data.o.playUrl}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">预览地址</label>
                <div class="col-md-10">
                    \${data.o.prePlayUrl}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">效果图地址</label>
                <div class="col-md-10">
                    \${data.o.preImgUrl}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">分辨率-宽</label>
                <div class="col-md-10">
                    \${data.o.rsoWidth}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">分辨率-高</label>
                <div class="col-md-10">
                    \${data.o.rsoHeight}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">备注</label>
                <div class="col-md-10">
                    \${data.o.remark}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">状态 </label>
                <div class="col-md-10">
                    {@if data.o.status == "1"}
                        正常
                    {@else}
                        不正常
                    {@/if}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">创建人姓名</label>
                <div class="col-md-10">
                    \${data.o.createUserName}
                </div>
            </div>
           <div class="form-group">
                <label class="col-md-2 view-label">创建日期</label>
                <div class="col-md-10">
                    \${data.o.createDate}
                </div>
            </div>

</script>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

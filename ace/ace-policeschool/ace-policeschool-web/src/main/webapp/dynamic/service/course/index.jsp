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
    <title>课程管理</title>
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
            <div class="col-md-6">
                <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>
            </div>

            <div class="col-md-6">

                <form onsubmit="return t_query()">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('category','');">全部</button>
                        <button type="button" class="btn btn-default" onclick="setParams('category','1');">现场教学</button>
                        <button type="button" class="btn btn-default" onclick="setParams('category','2');">室内教学</button>
                    </div>
                    <div class="input-group" style="float: left;margin-right: 5px;">
                        <input type="text"
                               name="keyword1"
                               class="form-control"
                               placeholder="请输入讲师名字">
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入课程名称">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn" type="submit">
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
                    <th width="50%"> 名称</th>

                    <th width="10%"> 类型</th>
                    <th width="15%"> 测评方案</th>
                    <th width="10%"> 讲师</th>

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
        <td>\${item.name}</td>

        <td>\${item.codeName}</td>
        <td>\${item.evaluatingName}</td>
        <td>\${item.teacherName}</td>

        <td>
            ﻿ <a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
            <a href="javascript:del('\${item.id}');">删除</a>

        </td>
    </tr>
    {@/each}
</script>

<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">$times;</span>
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
        <label class="col-md-2 view-label">名称</label>
        <div class="col-md-10">
            \${data.name}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">类别</label>
        <div class="col-md-10">
            \${data.codeName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">测评方案</label>
        <div class="col-md-10">
            \${data.evaluatingName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">讲师id</label>
        <div class="col-md-10">
            \${data.teacherName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">创建人姓名</label>
        <div class="col-md-10">
            \${data.createUserName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${data.createDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">更新人名称</label>
        <div class="col-md-10">
            \${data.lastModifyUserName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">更新日期</label>
        <div class="col-md-10">
            \${data.lastModifyDate}
        </div>
    </div>
</script>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
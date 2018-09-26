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
    <title>轮播图</title>
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
                                                                <a href="add/index.jsp?id=${param.id}"  class="btn green">创建</a>
                                                            </div>

            <div class="col-md-3">

            </div>

                                                            <div class="col-md-6">

                                                                <form onsubmit="return t_query()">

                                                                    <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                                                                        <button type="button" class="btn btn-default"  onclick="setParams('status','');">全部</button>
                                                                        <button type="button" class="btn btn-default"  onclick="setParams('status','0');">下线</button>
                                                                        <button type="button" class="btn btn-default" onclick="setParams('status','1');">在线</button>
                                                                    </div>
                                                                    <div class="input-group">
                                                                        <input type="text"
                                                                               name="keyword"
                                                                               class="form-control"
                                                                               placeholder="请输入名称">
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

                                                    <th width="50%">轮播图</th>

                                                    <th width="15%"> 状态</th>
                                                    <th width="20%"> 入库日期</th>
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
<td>
        <div class="col-md-2"><img src="\${item.imgUrl}" class="cover"/></div>
        <div class="col-md-10">
            <div class="describtion">
                \${item.title}

            </div>

        </div>
</td>

                            <td>
                                {@if item.status==1}<span class="label label-lg label-success">在线</span>{@/if}
                                {@if item.status==0}<span class="label label-lg label-danger">下线</span>{@/if}
                            </td>
                            <td> \${item.createTime}</td>

        <td>
           ﻿ <a  href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>


             <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-preview">查看</a>

             <a href="javascript:del('\${item.id}');">删除</a>

        </td>
    </tr>
    {@/each}
</script>
﻿

<div class="modal fade"  role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                        <div class="form-body"  id="fm-preview">

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
        <label class="col-md-2 view-label">标题</label>
        <div class="col-md-10">
            \${data.o.title}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">图片URL</label>
        <div class="col-md-10">
            <img src="\${data.o.imgUrl}" class="cover"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">资源地址</label>
        <div class="col-md-10">
            \${data.o.url}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">状态</label>
        <div class="col-md-10">
            {@if data.o.status==1}<span class="label label-lg label-info">在线</span>{@/if}
            {@if data.o.status==0}<span class="label label-lg label-danger">下线</span>{@/if}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">入库日期</label>
        <div class="col-md-10">
            \${data.o.createTime}
        </div>
    </div>

</script>
<style>
    .cover{
        width: 70px;
        height: 70px;
        object-fit: cover;
    }
    .describtion{
        padding-left:15px;
        height:50px;
    }
    .cost{
          padding-top: 5px;
          padding-left:15px;
          color:#FE6500;
    }
</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
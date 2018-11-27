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
    <title>管理员列表</title>
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
                                                                <a href="javascript:add()"  class="btn green">添加</a>
                                                            </div>
                                                            <div class="col-md-5">

                                                            </div>

                                                            <div class="col-md-4">

                                                                <form onsubmit="return t_query()">
                                                                    <div class="input-group">
                                                                        <input type="text"
                                                                               name="keyword"
                                                                               class="form-control"
                                                                               placeholder="请输入昵称">
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
                    <th width="20%">昵称</th>
                    <th width="50%"></th>
                    <th width="20%">入库时间</th>
                    <th width="10%">操作</th>
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
            <div class="row">
                <div class="col-md-2">
                    <img src="\${item.headimg}" class="cover" />
                </div>
                <div class="col-md-10">
                    <div class="describtion">\${item.nickname}</div>
                </div>
            </div>
        </td>
        <td></td>
        <td>
            \${item.createDate}
        </td>

        <td>
             <a href="javascript:del('\${item.id}');">删除</a>
        </td>
    </tr>
    {@/each}
</script>


<script id="tpl-user" type="text/template">
    {@each data as item, index}
    <div class="layout-user">
    <a href="javascript:insertWxUser('\${item.unionid}','å\${item.nickname}','\${item.headimgurl}')">
    <img class="photo" src="\${item.headimgurl}"></a>
        <div style="text-align:center">
            \${item.nickname}
        </div>
    </div>
    {@/each}
</script>
﻿
<div class="modal fade"  role="dialog" id="modal-add">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">微信用户</h4>
            </div>
            <div class="modal-body">

                <div class="portlet light">

                    <div class="portlet-body">

                        <div class="row custom-toolbar">
                            <div class="col-md-3">

                            </div>
                            <div class="col-md-5">

                            </div>

                            <div class="col-md-4">

                                <form onsubmit="return query()">
                                    <div class="input-group">
                                        <input type="text"
                                               name="nickname"
                                               class="form-control"
                                               placeholder="请输入昵称">
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
                        <div id="user-List" class="row">


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
<style>
        .cover {
            width: 50px;
            height: 50px;
            object-fit: cover;
        }

        .describtion {
            padding-left: 15px;
        }

        .certification {
            padding-top: 5px;
            padding-left: 8px;
        }

        #info img {

            max-height: 200px;
            max-width: 200px;
            border-radius: 5px;
        }
		.pswp {
			z-index: 99999;
		}
		.my-gallery img{
			padding: 10px;
			float: left;
		}
    </style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
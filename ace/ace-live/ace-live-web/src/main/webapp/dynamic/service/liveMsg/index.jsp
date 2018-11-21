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
    <title>互动</title>
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
                                                            <div class="col-md-7">

                                                            </div>

                                                            <div class="col-md-5">

                                                                <form onsubmit="return t_query()">

                                                                    <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                                                                        <button type="button" class="btn btn-default"  onclick="setParams('status','');">全部</button>
                                                                        <button type="button" class="btn btn-default"  onclick="setParams('status','1');">待审</button>
                                                                        <button type="button" class="btn btn-default" onclick="setParams('status','2');">通过</button>
                                                                        <button type="button" class="btn btn-default" onclick="setParams('status','3');">屏蔽</button>
                                                                    </div>

                                                                    <div class="input-group">
                                                                        <input type="text"
                                                                               name="keyword"
                                                                               class="form-control"
                                                                               placeholder="请输入昵称或内容">
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


                                                    <th width="20%">用户</th>
                                                    <th width="50%">内容</th>
                                                     <th width="10%">时间</th>
                                                    <th width="10%"> 状态</th>

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
                                <div class="col-md-4 my-gallery"><img src="\${parseJson(item.content).header.wxuser.headimgurl}" class="cover"/></div>
                                <div class="col-md-8">
                                    <div class="describtion">\${parseJson(item.content).header.wxuser.nickname}</div>
                                </div>


                            </td>
                            <td> \${parseJson(item.content).content}</td>

                            <td> \${item.createTime}</td>
                            <td>


            {@if item.status==1}
                <span class="label label-lg label-info">待审</span>
            {@else if item.status==2}
                <span class="label label-lg label-success">通过</span>
            {@else}
                                <span class="label label-lg label-danger">屏蔽</span>
            {@/if}
        </td>
        <td>

             <a href="javascript:audit('\${item.id}','3');">屏蔽</a>
             <a href="javascript:audit('\${item.id}','2');">通过</a>

        </td>
    </tr>
    {@/each}
</script>
﻿


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
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
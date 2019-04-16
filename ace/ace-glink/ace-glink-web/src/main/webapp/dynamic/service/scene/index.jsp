<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>场景控制</title>
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
        <div id="page-list">

        </div>
        <br><br>
        <div id="station-list">

        </div>

        <br> <br><br>
        <div id="allCheck" style="display: none">
            &nbsp;<input name="checkList" type="checkbox" style="width: 15px; height:15px;" class="all"/><span
                class="checkall">全选</span>&nbsp;&nbsp;&nbsp;
            <span class="count">当前选中<span id="checkCount">0</span>条</span>

            <div class="wrap">

                <input type="checkbox" id="a0" class="a" name="checkonline"/>
                <label class="slider-v2" for="a0"></label>

                <input type="checkbox" id="a1" class="a" name="checkpause"/>
                <label class="slider-v3" for="a1"></label>

            </div>
            <label style=" margin-left: 330px;margin-top: 20px;" class="batch">批量操作</label>
        </div>
        <br><br>

        <div id="animaLnk-list" style="overflow: auto;">
            <ul class="videolist">

            </ul>
        </div>

    </div>
</div>

<div class="modal fade " id="modal-option">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 1000px;height: 700px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选择节目</h4>
            </div>
            <div class="modal-body" style="width: 100%;height: 550px;">
                <form class="form-horizontal" id="fm-status" role="form">
                    <div class="form-body">
                        <div class="row custom-toolbar">
                            <div class="col-md-3"></div>
                            <div class="col-md-5"></div>
                            <div class="col-md-4">
                                <form onsubmit="return query()">
                                    <div class="input-group">
                                        <%-- <input type="text" name="nickname" class="form-control" placeholder="请输入昵称">
                                         <span class="input-group-btn">
                                             <button class="btn  btn-default search_btn" type="submit">搜索</button>
                                         </span>--%>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="row" id="animaList">

                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <%-- <button type="button" class="btn green confirm">确定</button>--%>
            </div>
        </div>
    </div>
</div>

<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    <ul>
        {@each data as item, index}
        <li><a href="#" data-id="\${item.id}" data-title="\${item.name}" onclick="stationList('\${item.code}');">\${item.name}</a>
        </li>
        {@/each}
    </ul>
</script>

<%--站点列表模板--%>
<script id="tp2-list" type="text/template">
    <ul>
    {@each data as item, index}

        <li><a href="#" data-id="\${item.id}" data-title="\${item.name}" style="color: black"
               onclick="animaList(this,'\${item.code}');">\${item.name}</a></li>
        {@/each}
    </ul>
</script>

<%--节目列表模板--%>
<script id="tp3-list" type="text/template">

</script>

<!--节目清单渲染-->
<script id="animaList-tpl" type="text/template">
    <ul>
        {@each data.list as item, index}
        <li>
            <div class="layout-user">

                <video src="\${item.prePlayUrl}" controls="controls" style="width: 200px;height: 200px;">
                </video>
                <div> \${item.name}</div>
                <a href="javascript:updatePrePlayUrl('\${data.lnkid}','\${item.id}','\${item.prePlayUrl}')"
                   style="margin-right: 20px;" class="btn  green"> 确定</a>
            </div>
        </li>
        {@/each}
    </ul>
</script>
<style>
    <%--custom style--%>
</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>


</html>

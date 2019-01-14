<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>通讯录</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>

<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">
    <div class="portlet-body">
        <div class="row custom-toolbar">
            <form action="#" id="fm-search">
                <div class="col-md-9 toolbar">

                    <button type="button" class="btn  green" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/mailList/insertMailList">添加
                    </button>

                </div>
                <div class="col-md-3" id="select1">




                </div>

            </form>
        </div>

        <div class="row" style="padding:10px">

            <div class="panel panel-default">
                <div class="panel-heading">班级成员</div>
                <div class="panel-body" style="padding:10px" id="classStudent" data-groupid="0" ondrop="drop(event)" ondragover="allowDrop(event)">

                </div>
            </div>
        </div>


            <div class="row" id="groupStudent">




            </div>





    </div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<jsp:include page="/dynamic/common/footer.jsp"/>

<%--查看详情--%>
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 75%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
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
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>




<script id="tpl-select-list" type="text/template">

    <select name="classId" id="classId" style="float:right" onchange="initClasses(this.value)">
    {@each data as item, index}
           <option value="\${item.id}">\${item.name}</option>
    {@/each}
    </select>
</script>

<script id="tpl-student" type="text/template">
        {@each data as item, index}
        <div class="badge badge-pill badge-success" onmousemove="addMove(this)" onmouseout="delMove(this)" style="float:left;margin:10px" id="student\${item.id}" data-studentid="\${item.id}" draggable="true" ondragstart="drag(event)">\${item.name}</div>
        {@/each}

</script>

<script id="tpl-group" type="text/template">
    {@each data as item, index}
        <div class="panel panel-default" style="width:250px;float:Left;margin:10px">
            <div class="panel-heading">\${item.name}</div>
            <div class="panel-body" style="padding:10px" ondrop="drop(event)" ondragover="allowDrop(event)" data-groupid="\${item.id}">
                {@each item.list as o, index}
                    <div class="badge badge-pill badge-success" onmousemove="addMove(this)" onmouseout="delMove(this)" style="float:left;margin:10px" id="student\${o.id}" data-studentid="\${o.id}" draggable="true" ondragstart="drag(event)">\${o.name}</div>
                {@/each}
            </div>
        </div>
    {@/each}

</script>

<%--easyui--%>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>



<script type="text/javascript" src="js/act.js"></script>
</body>
<link rel="stylesheet" type="text/css" href="css/style.css?version=${cfg.version}">
</html>
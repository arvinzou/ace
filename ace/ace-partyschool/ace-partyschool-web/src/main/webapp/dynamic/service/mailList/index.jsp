<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
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
<div style="background:#eff3f8">


    <div class="row">
        <div class="col-md-3">
            <div class="portlet light">
                <div class="portlet-body">
                    <div class="row">


                        <div style="float:left" id="select1">


                        </div>
                        <div style="float:right">

                            <button type="button" class="btn  green" id="btn-view-add" authority="false">添加
                            </button>

                        </div>
                    </div>
                </div>
            </div>


            <div class="portlet light">
                <div class="portlet-title">班级分组</div>
                <div class="portlet-body">

                    <div class="row">


                        <ul id="tt"></ul>
                    </div>

                </div>
            </div>

        </div>
        <div class="col-md-9">


            <div class="portlet light">
                <div class="portlet-title">未分组学员</div>
                <div class="portlet-body">
                    <div class="row content" id="classStudent" data-groupid="0" ondrop="drop(event)"
                         ondragover="allowDrop(event)" ondragenter="allowDrop(event)">


                    </div>

                </div>
            </div>


            <div id="groupStudent">


            </div>

        </div>

    </div>


</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<jsp:include page="/dynamic/common/footer.jsp"/>


<script id="tpl-select-list" type="text/template">

    <select name="classId" id="classId" style="float:right" onchange="initClasses(this.value)" class="form-control">
        {@each data as item, index}
        <option value="\${item.id}">\${item.name}</option>
        {@/each}
    </select>
</script>

<script id="tpl-student" type="text/template">
    {@each data as item, index}
    <div class="badge badge-pill badge-success student" onmousemove="addMove(this)" onmouseout="delMove(this)"
         id="student\${item.id}" data-studentid="\${item.id}" draggable="true" ondragstart="drag(event)">\${item.name}
    </div>
    {@/each}

</script>

<script id="tpl-group" type="text/template">
    {@each data as item, index}
    <div class="portlet light" style="width:32.1%;float:Left;margin-right:10px;">
        <div class="portlet-title tabbable-line">
            \${item.name} {@if item.list.length==0}<a style="float:right"
                                                      href="javascript:remove('\${item.id}');">删除</a>{@/if}
        </div>
        <div class="portlet-body">
            <div class="row content" ondrop="drop(event)" ondragover="allowDrop(event)" ondragenter="allowDrop(event)"
                 data-groupid="\${item.id}">
                {@each item.list as o, index}
                <div class="badge badge-pill badge-success student" onmousemove="addMove(this)"
                     onmouseout="delMove(this)" id="student\${o.id}" data-studentid="\${o.id}" draggable="true"
                     ondragstart="drag(event)">\${o.name}
                </div>
                {@/each}
            </div>
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

<script src="${portalPath}/content/common/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>

<script type="text/javascript" src="js/act.js"></script>
</body>
<link rel="stylesheet" type="text/css" href="css/style.css?version=${cfg.version}">
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>政企服务</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<script type="text/javascript">
</script>
<body>
<div class="page-content">
    <div class="widget-box" id="widget-box">
        <div class="widget-header">
            <h5 class="widget-title smaller">设置查询条件</h5>

            <div class="widget-toolbar"></div>
        </div>

        <div class="widget-body">
            <div class="widget-main padding-6">
                <form action="#" id="fm-search">
                    名称： <input name="title" type="text" style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/fopGeHelp/findFopGeHelpList">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">
                    <%--<button class="btn btn-info" id="btn-view-add"--%>
                    <%--authority="${pageContext.request.contextPath}/fopGeHelp/insertFopGeHelp">--%>
                    <%--<i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>--%>
                    <%--</button>--%>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/fopGeHelp/updateFopGeHelp">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/fopGeHelp/deleteFopGeHelpByFopGeHelpId">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <%--审核发布--%>
                    <button class="btn btn-purple" id="btn-view-audit"
                            authority="${pageContext.request.contextPath}/fopGeHelp/audit">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <div id="dialog-message-audit" class="hide">
        <form action="/fopGeHelp/audit" id="fm-audit">
            <fieldset>
                审核结果：
                <input id="audit_pass" name="audit_result" type="radio" value="0"/> 通过
                <input id="audit_unpass" name="audit_result" type="radio" value="1"/> 不通过
            </fieldset>
            <div class="space-6"></div>
            <fieldset>
                审核备注： <textarea id="audit_opinion" cols="30" rows="10"></textarea>

                <%--<input id="" type="password" style="width: 200px;"/>--%>
            </fieldset>
        </form>
    </div>
</div>
<div id="dialog-message" class="hide">
    <div id="uploader">
        <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
    </div>
</div>
<div id="dialog-message-file" class="hide">
    <div id="load" class="loading"></div>
</div>

<div id="dialog-message-view" class="hide">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">发起人ID</span>
            <br>
            <span id="requestId"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">发起人类型</span>
            <br>
            <span id="requestType"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">父节点ID</span>
            <br>
            <span id="parentId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">标题</span>
            <br>
            <span id="title"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">发布时间</span>
            <br>
            <span id="releaseDate"></span>
        </div>

        <div class="labelItem hide">
            <span class="labelItemHeader">所含关键字</span>
            <br>
            <span id="keyWord"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">累计提问次数</span>
            <br>
            <span id="accCount"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">备注</span>
            <br>
            <span id="remark"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">状态</span>
            <br>
            <span id="status"></span>
        </div>
    </div>
    <h5 class="header-title">回复内容</h5>
    <div class="row" style="padding:10px" id="reply">
    </div>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">创建人编号</span>
            <br>
            <span id="createUserId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">创建人姓名</span>
            <br>
            <span id="createUserName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">入库日期</span>
            <br>
            <span id="createDate"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">最后更新人编号</span>
            <br>
            <span id="lastModifyUserId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">最后更新人姓名</span>
            <br>
            <span id="lastModifyUserName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">最后更新时间</span>
            <br>
            <span id="lastModifyDate"></span>
        </div>
    </div>

</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script
        src="${pageContext.request.contextPath}/content/service/fopGeHelp/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopGeHelp/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopGeHelp/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopGeHelp/view.js?version=${cfg.version}"></script>


<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>


<style>
    .simditor {
        border: none;
    }

    .simditor .simditor-body {
        width: 750px;
    }
</style>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
        parent.autoWidth();
    }
</script>
</body>
</html>
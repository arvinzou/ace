<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>入驻成员列表</title>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/schedule.css"/>--%>
    <link rel="stylesheet"
          href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
          type="text/css" media="screen"/>
    <link rel="stylesheet" href="${portalPath}/content/common/assets/css/colorbox.css"/>
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
                    企业类型：<input name="type" class="easyui-combobox" style="width: 200px"
                                data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=147&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                    名称： <input name="departmentName" type="text" style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/vipDepartment/findVipDepartmentList">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">

                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/vipDepartment/insertVipDepartment">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/vipDepartment/updateVipDepartment">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/vipDepartment/deleteVipDepartmentByVipDepartmentId">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <%--入驻审核--%>
                    <button class="btn btn-purple" id="btn-view-audit"
                            authority="${pageContext.request.contextPath}/vipDepartment/audit">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <%--信息公示--%>
                    <button class="btn btn-purple" id="btn-view-publicity"
                            authority="${pageContext.request.contextPath}/vipDepartment/publicity">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>


    <div id="dialog-message-audit" class="hide">
        <div>
            <div>
                <form action="/vipDepartment/audit" id="fm-audit">
                    <fieldset name="node-field">
                        入驻节点： <input name="node-list" class="easyui-combobox" style="width: 200px"
                                     data-options="
                            url:'${portalPath}/dict/findListByCategoryId.do?categoryId=148&selected=false',
                            method:'get',
                            valueField:'code',
                            textField:'name',
                            panelHeight:'auto'">
                    </fieldset>
                    <div class="space-8"></div>
                    <fieldset name="audit-rs-field">
                        审核结果：
                        <input id="audit-pass" name="auditResult" type="radio" value="1"/> 通过
                        &nbsp&nbsp&nbsp&nbsp
                        <input id="audit-unpass" name="auditResult" type="radio" value="2"/> 拒绝
                    </fieldset>
                    <div class="space-8"></div>
                    <fieldset name="audit-opinion-field">
                        审核备注： <input type="text" id="auditOpinion" style="width: 200px"/>
                    </fieldset>
                </form>
            </div>
        </div>
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
            <span class="labelItemHeader">编号</span>
            <br>
            <span id="departmentId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">企业名称</span>
            <br>
            <span id="departmentName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">企业类型</span>
            <br>
            <span id="type"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">所属机构名称</span>
            <br>
            <span id="parentDepartmentName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">联系人手机号</span>
            <br>
            <span id="contactMobile"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">法人代表</span>
            <br>
            <span id="legalPersonName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">注册时间</span>
            <br>
            <span id="regDate"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">注册资本(万元)</span>
            <br>
            <span id="regCapital"></span>
        </div>
        <div class="labelItem ">
            <span class="labelItemHeader">入驻状态</span>
            <br>
            <%--1=入驻中、2=vip--%>
            <span id="status"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">系统标示</span>
            <br>
            <span id="syid"></span>
        </div>
    </div>
    <h5 class="header-title">注册地址</h5>
    <div id="regAddr" class="row" style="padding:10px">
    </div>

    <h5 class="header-title">附件资源</h5>
    <div id="attachment-list" class="row" style="padding:10px">
        <div id='filelist-history'></div>
        <div id='filelist'></div>
        <div id='container'>
            附件：<a id='pickfiles' href='javascript:;'>[添加附件]</a>
            <a id='uploadfiles' href='javascript:;'>[上传]</a>
        </div>
    </div>

    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader"></span>
            <br>
            <span id="createUserId"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">创建的用户名</span>
            <br>
            <span id="createUserName"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader"></span>
            <br>
            <span id="createTime"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">最后修改时间</span>
            <br>
            <span id="lastModifyTime"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">最后修改用户ID</span>
            <br>
            <span id="lastModifyUserId"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">最后修改用户名</span>
            <br>
            <span id="lastModifyUserName"></span>
        </div>
    </div>

</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script src="${pageContext.request.contextPath}/content/service/vipDepartment/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/vipDepartment/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/vipDepartment/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/vipDepartment/view.js?version=${cfg.version}"></script>

<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script src="${pageContext.request.contextPath}/content/service/vipDepartment/upload.js?version=${cfg.version}"></script>

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
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>企业管理</title>
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

                    类别：<input
                        class="easyui-combobox" style="width: 200px" name="category"
                        data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=69&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                    名称： <input name="name" type="text"
                               style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/fopPerson/findFopPersonList">
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/fopPerson/insertFopPerson">
                        <i
                                class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/fopPerson/updateFopPerson">
                        <i
                                class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/fopPerson/deleteFopPersonByFopPersonId">
                        <i
                                class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>

                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>


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
        <div class="labelItem"><span class="labelItemHeader">
姓名</span>
            <br>
            <span id="realName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
性别</span>
            <br>
            <span id="sex">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
出生年月</span>
            <br>
            <span id="birthDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
籍贯</span>
            <br>
            <span id="nativePlace">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
民族</span>
            <br>
            <span id="nationality">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
政治面貌</span>
            <br>
            <span id="political">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
参加工作时间</span>
            <br>
            <span id="recruitmentDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
文化程度</span>
            <br>
            <span id="education">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
技术职称</span>
            <br>
            <span id="skillJobTitle">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
单位职务</span>
            <br>
            <span id="deptPost">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
身份证号码</span>
            <br>
            <span id="identityCard">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
主要社会职务</span>
            <br>
            <span id="societyPost">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
电话号码</span>
            <br>
            <span id="phoneNumber">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
手机号码</span>
            <br>
            <span id="mobileNumer">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
电子邮箱</span>
            <br>
            <span id="email">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
个人简历</span>
            <br>
            <span id="resume">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
特长及成就</span>
            <br>
            <span id="achievement">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
备注</span>
            <br>
            <span id="remark">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
状态</span>
            <br>
            <span id="status">
</span>
        </div>
    </div>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem"><span class="labelItemHeader">
创建人编号</span>
            <br>
            <span id="createUserId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
创建人姓名</span>
            <br>
            <span id="createUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
入库日期</span>
            <br>
            <span id="createDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新人编号</span>
            <br>
            <span id="lastModifyUserId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新人姓名</span>
            <br>
            <span id="lastModifyUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新时间</span>
            <br>
            <span id="lastModifyDate">
</span>
        </div>
    </div>

</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script
        src="${pageContext.request.contextPath}/content/service/fopPerson/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopPerson/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopPerson/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopPerson/view.js?version=${cfg.version}"></script>
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
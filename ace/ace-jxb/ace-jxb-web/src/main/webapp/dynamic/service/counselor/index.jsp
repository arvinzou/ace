<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>咨询师</title>
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
                    <%--类别：<input name="category" class="easyui-combobox" style="width: 200px"--%>
                    <%--data-options="--%>
                    <%--url:'${portalPath}/dict/findListByCategoryId.do?categoryId=69&selected=false',--%>
                    <%--method:'get',--%>
                    <%--valueField:'code',--%>
                    <%--textField:'name',--%>
                    <%--panelHeight:'auto'">--%>

                    名称： <input name="name" type="text" style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/counselor/findCounselorList">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">

                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/counselor/insertCounselor">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/counselor/updateCounselor">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/counselor/deleteCounselorByCounselorId">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-purple" id="btn-view-audit"
                            authority="${pageContext.request.contextPath}/counselor/audit">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
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

</div>
<jsp:include page="../../common/footer-1.jsp"/>

<script src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script src="${pageContext.request.contextPath}/content/service/counselor/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/counselor/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/counselor/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/counselor/view.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
        parent.autoWidth();
    }
</script>
<script id="render-template" type="text/template">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">主键</span>
            <br>
            <span name="id">\${id}</span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">所属工作室</span>
            <br>
            <span name="studioId">\${studioId}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">姓名</span>
            <br>
            <span name="name">\${name}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">职业名称</span>
            <br>
            <span name="certification">\${certification}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">擅长领域</span>
            <br>
            <span name="tags">\${tags}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">从业资质</span>
            <br>
            <span name="years">\${years}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">手机号</span>
            <br>
            <span name="mobile">\${mobile}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">形象照</span>
            <br>
            <span name="imagePhotoUrl">\${imagePhotoUrl}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">电子名片</span>
            <br>
            <span name="visitCardUrl">\${visitCardUrl}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">身份证号</span>
            <br>
            <span name="idCard">\${idCard}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">身份证件电子档</span>
            <br>
            <span name="idCardImgUrl">\${idCardImgUrl}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">资格从业证书号</span>
            <br>
            <span name="certificateNo">\${certificateNo}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">资格从业证书电子档</span>
            <br>
            <span name="certificateImgUrl">\${certificateImgUrl}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">身份证持胸前自拍照</span>
            <br>
            <span name="evidenceImgUrl">\${evidenceImgUrl}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">个人简介</span>
            <br>
            <span name="profile">\${profile}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">爱心币</span>
            <br>
            <span name="lm">\${lm}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">累计收益</span>
            <br>
            <span name="income">\${income}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">账户余额</span>
            <br>
            <span name="account">\${account}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">级别</span>
            <br>
            <span name="level">\${level}</span>
        </div>
    </div>

    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">入库日期</span>
            <br>
            <span name="createDate">\${createDate}</span>
        </div>
    </div>
</script>
</body>
</html>
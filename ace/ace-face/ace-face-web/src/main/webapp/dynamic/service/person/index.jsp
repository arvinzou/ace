<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>人脸建档</title>
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


                    名称： <input name="name" type="text"
                               style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/person/findPersonList.do">
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/person/insertPerson.do">
                        <i
                                class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/person/updatePerson.do">
                        <i
                                class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/person/deletePersonByPersonId.do">
                        <i
                                class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-detect"
                            authority="false">
                        人脸检测<i
                                class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-faceadd"
                            authority="false">
                        添加到检测库<i
                            class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
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
            <span id="name">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
性别</span>
            <br>
            <span id="sex">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
出生日期</span>
            <br>
            <span id="birthday">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
单位</span>
            <br>
            <span id="dept">
</span>
        </div>

        <div class="labelItem"><span class="labelItemHeader">
人脸的标识</span>
            <br>
            <span id="faceFoken">
</span>
        </div>


    </div>
    <h5 class="header-title">照片</h5>
    <div class="row" style="padding:10px" id="photo"></div>
    <h5 class="header-title">人脸检测信息</h5>
    <div class="row hide" style="padding:10px" id="html_rst">
        <div class="labelItem"><span class="labelItemHeader">性别</span><br><span id="gender"></span></div>
        <div class="labelItem"><span class="labelItemHeader">年龄</span><br><span id="age"></span></div>
        <div class="labelItem"><span class="labelItemHeader">微笑程度</span><br><span id="smile"></span></div>
        <div class="labelItem"><span class="labelItemHeader">是否佩戴眼镜</span><br><span id="glass"></span></div>
        <div class="labelItem"><span class="labelItemHeader">人脸姿势</span><br><span id="headpose"></span></div>

        <div class="labelItem"><span class="labelItemHeader">情绪</span><br><span id="emotion"></span></div>
        <div class="labelItem"><span class="labelItemHeader">人种</span><br><span id="ethnicity"></span></div>
        <div class="labelItem"><span class="labelItemHeader">颜值</span><br><span id="beauty"></span></div>
        <div class="labelItem"><span class="labelItemHeader">健康</span><br><span id="health"></span></div>
        <div class="labelItem"><span class="labelItemHeader">色斑</span><br><span id="stain"></span></div>
        <div class="labelItem"><span class="labelItemHeader">青春痘</span><br><span id="acne"></span></div>
        <div class="labelItem"><span class="labelItemHeader">黑眼圈</span><br><span id="dark_circle"></span></div>
    </div>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">

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

<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script
        src="${pageContext.request.contextPath}/content/service/person/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/person/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/person/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/person/view.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/person/face.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/person/upload.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
	parent.autoWidth();
}

</script>
</body>
</html>
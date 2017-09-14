<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>TOP问题分析</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<script type="text/javascript">
var meetingId='${param.meetingId}';
var topicId='${param.topicId}';
var normId='${param.normId}';
var liable='${SESSION_USERPROP_KEY.userId}';
</script>
<body>

<div class="page-content">
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
编码</span>
            <br>
            <span id="id">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
会议编码</span>
            <br>
            <span id="meetingId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
议题编码</span>
            <br>
            <span id="topicId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
指标编码</span>
            <br>
            <span id="normId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
产品编码</span>
            <br>
            <span id="productId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
问题描述</span>
            <br>
            <span id="probDiscri">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
原因分析</span>
            <br>
            <span id="probAnsys">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
改善措施</span>
            <br>
            <span id="actions">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
责任人</span>
            <br>
            <span id="liable">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
任务状态</span>
            <br>
            <span id="status">
</span>
        </div>
    </div>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem"><span class="labelItemHeader">
创建人编码</span>
            <br>
            <span id="createUserId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
入库日期</span>
            <br>
            <span id="createDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新人编码</span>
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
        src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/tpaUserTask/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/tpaUserTask/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/tpaUserTask/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/tpaUserTask/view.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight+120);

}

</script>
</body>
</html>
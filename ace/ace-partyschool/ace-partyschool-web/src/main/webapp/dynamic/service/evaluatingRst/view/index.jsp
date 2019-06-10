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
    <title>课程表管理</title>
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
            <div class="col-md-9">
                <button onclick="exportData()" class="btn green">导出数据</button>
            </div>
            <div class="col-md-3">
                <form onsubmit="return t_query()">
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="按学员姓名搜索">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn" type="submit">
                                    搜索
                            </button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12" id="echartMain" style="height:300px;">

            </div>
        </div>
        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="10%"> 课程</th>
                    <th width="10%"> 评价</th>
                    <th width="10%"> 固定题</th>
                    <th width="10%"> 学生</th>
                    <th width="10%"> 日期</th>
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
        <td> \${item.course.name}</td>
        <td> \${item.evaluationRstContent?item.evaluationRstContent.content:''}</td>
        <td> \${formatdate(item.evaluationRstContent.judge)}</td>
        <td> \${item.student.name}</td>
        <td> \${item.createDate}</td>
        <td>
            <a href="javascript:view('\${item.classScheduleId}','\${item.userId}');">查看</a>
            <a href="javascript:del('\${item.classScheduleId}','\${item.userId}');">删除</a>
        </td>

    </tr>
    {@/each}
</script>
﻿
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body">
                <div class="table-scrollable">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th width="10%"> 题目</th>
                            <th width="10%"> 打分</th>
                        </tr>
                        </thead>
                        <tbody id="pageLists">

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script id="tpl-preview" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.name}：\${item.introduce}</td>
        <td> \${item.score}</td>
    </tr>
    {@/each}
</script>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/common/js/echarts.min.js"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

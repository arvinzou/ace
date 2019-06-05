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
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/select2/css/select2-bootstrap.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/select2/css/select2.css">
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">
                <button type="button" class="btn green" id="btn-report" authority="false"> 统计报表</button>
            </div>
            <div class="col-md-9">

                <form onsubmit="return t_query()">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <select style="width: 300px"
                                class="form-control js-example-basic-single js-example-basic-single2"
                                name="teacherId"></select>
                    </div>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <select style="width: 300px"
                                class="form-control js-example-basic-single js-example-basic-single1"
                                name="classesId"></select>
                    </div>
                    <div class="input-group">
                        <input style="height: 35px;" type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入课程名称">
                        <span class="input-group-btn">
                            <button style="height: 35px;" class="btn  btn-default search_btn" type="submit">
                                    搜索
                            </button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="30%"> 课程</th>
                    <th width="10%"> 上课日期</th>
                    <th width="25%"> 班次</th>
                    <th width="10%"> 授课人</th>
                    <th width="5%"> 平均分值</th>
                    <th width="5%"> 未测评人数</th>
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
        <td> \${formatdate(item.courseDate)}</td>
        <td> \${item.classes.name}</td>
        <td> \${item.course.teacherNames}</td>
        <td> \${item.averageScore}</td>
        <td> \${item.studentNum-item.userNum}</td>
        <td>
            <a href="javascript:view('\${item.id}');">查看</a>
        </td>
    </tr>
    {@/each}
</script>
﻿
<%--统计报表--%>
<div class="modal fade" role="dialog" id="modal-report">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">统计报表</h4>
            </div>
            <div class="modal-body">
                <%--导出报表查询窗体--%>
                <div class="portlet light ">
                    <div class="portlet-body">
                        <div class="row custom-toolbar">
                            <div class="col-md-7">
                                <button id="btn-ext-report" type="button" class="btn green hide" authority="false">
                                    导出
                                </button>
                            </div>

                            <div class="col-md-5">
                                <select name="classesId" style="width: 100%"
                                        class="form-control js-example-basic-single js-example-basic-single1">
                                </select>
                            </div>

                        </div>

                        <div class="table-scrollable">
                            <table class="table table-hover" id="tb-ext-report">

                            </table>
                        </div>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>

<script id="tpl-ext-report" type="text/template">
    <thead>
    <tr>
        <th width="10%"> 教师姓名</th>
        <th width="10%"> 测评人数</th>
        <th width="10%"> 去掉3个最高分和3个最低分后的最后总分</th>
        <th width="10%"> 去掉3个最高分和3个最低分后的最后平均分</th>
        <th width="10%"> 等次</th>
        <th width="10%"> 名次</th>
        <th width="35%"> 课程名称</th>
        <th width="10%"> 很有必要</th>
        <th width="10%"> 可以开设</th>
        <th width="10%"> 可有可无</th>
        <th width="10%"> 无需开设</th>
    </tr>
    </thead>
    <tbody>

    {@each data.report as item, index}
    <tr>
        <td width="10%">\${item.teacherName}</td>
        <td width="10%">\${item.testNum}</td>
        <td width="10%">\${item.total}</td>
        <td width="10%">\${item.avg}</td>
        <td width="10%">\${item.rstLevel}</td>
        <td width="10%">\${item.rank}</td>
        <td width="10%">\${item.courseName}</td>
        <td width="10%">\${item.opt1}</td>
        <td width="10%">\${item.opt2}</td>
        <td width="10%">\${item.opt3}</td>
        <td width="10%">\${item.opt4}</td>
    </tr>
    {@/each}

    </tbody>
</script>


<!--审核弹框-->
<div class="modal fade" role="dialog" id="modal-audit">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn green audit">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
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
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script id="tpl-fm" type="text/template">
    <div class="form-body">

        <div class="form-group">
            <label class="col-md-2 view-label">主键</label>
            <div class="col-md-10">
                \${id}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">班次</label>
            <div class="col-md-10">
                \${classesId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">日期</label>
            <div class="col-md-10">
                \${courseDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">课节am:上午 pm:下午</label>
            <div class="col-md-10">
                \${courseIndex}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">授课人</label>
            <div class="col-md-10">
                \${teacherId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">课程</label>
            <div class="col-md-10">
                \${courseId}
            </div>
        </div>


        <h4>结果</h4>
        <hr>
        <div class="form-group " id="operation">
            <label class="col-md-2 control-label">结果</label>
            <div class="col-md-10">
                <div class="radio-group-container">
                    <label>
                        <input type="radio" name="rst" value="2"><span style="padding:10px">通过</span>
                    </label>
                    <label>
                        <input type="radio" name="rst" value="3"><span style="padding:10px">退回</span>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">说明</label>
            <div class="col-md-10">
                <input type="hidden" name="id" value="\${data.o.id}">
                <textarea name="text" style="width: 100%;height: 100px;"></textarea>
            </div>
        </div>
    </div>

</script>

<script id="tpl-preview" type="text/template">
    <div class="form-group">
        <label class="col-md-2 view-label">主键</label>
        <div class="col-md-10">
            \${id}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">班次</label>
        <div class="col-md-10">
            \${classesId}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">日期</label>
        <div class="col-md-10">
            \${courseDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">课节am:上午 pm:下午</label>
        <div class="col-md-10">
            \${courseIndex}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">授课人</label>
        <div class="col-md-10">
            \${teacherId}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">课程</label>
        <div class="col-md-10">
            \${courseId}
        </div>
    </div>
</script>

<style>
    body > span.select2-container {
        z-index: 99999;
    }
</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/select2/js/select2.js"></script>
<%--page excel export--%>
<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
</html>

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
    <title>定时任务数据</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>

    <link rel="stylesheet" href="css/style.css">
    <%--sweetalert--%>
    <%-- <script src="${pageContext.request.contextPath}/content/common/js/sweetalert/js/sweet-alert.min.js"></script>
     <link rel="stylesheet" type="text/css"
           href="${pageContext.request.contextPath}/content/common/js/sweetalert/css/sweet-alert.css">--%>
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">

                <a href="javascript:syncData();" class="btn green">同步数据</a>
            </div>

            <div class="col-md-9">

                <form id="fm-search">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">

                    </div>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">

                    </div>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">

                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="timerName"
                               class="form-control"
                               placeholder="请输入任务名称">
                        <span class="input-group-btn">
                                                                        <button class="btn  btn-default search_btn"
                                                                                type="submit">
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

                    <th width="15%"> 定时任务序号</th>
                    <th width="10%"> 定时任务名称</th>
                    <th width="10%"> 定时任务状态</th>
                    <th width="10%"> 启动时间</th>
                    <th width="10%"> 调度任务号</th>

                    <th width="10%"> 状态</th>
                    <th width="15%"> 创建日期</th>
                    <th width="15%">操作</th>
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

        <td> \${item.timerID}</td>
        <td> \${item.timerName}</td>
        <td> {@if item.timerEnable==0}
            <span class="label label-lg label-info">无效</span>
            {@else if item.timerEnable==1}
            <span class="label label-lg label-info">有效</span>
            {@else}
            {@/if}
        </td>
        <td> \${item.startTime}</td>
        <td> \${item.taskNo}</td>
        <td> {@if item.status==0}
            <span class="label label-lg label-info">无效</span>
            {@else if item.status==1}
            <span class="label label-lg label-info">有效</span>
            {@else}
            {@/if}
        </td>
        <td> \${item.createDate}</td>
        <%-- <td>
             {@if item.status==0}
             <span class="label label-lg label-danger">删除</span>
             {@else if item.status==1}
             <span class="label label-lg label-info">暂存</span>
             {@else if item.status==2}
             <span class="label label-lg label-warning">待审</span>
             {@else if item.status==3}
             <span class="label label-lg label-info">通过</span>
             <div style="padding-top:10px">\${item.auditRemark}</div>
             {@else if item.status==4}
             <span class="label label-lg label-info">驳回</span>
             <div style="padding-top:10px">\${item.auditRemark}</div>
             {@else}
             <span class="label label-lg label-danger">暂存</span>
             {@/if}
         </td>--%>
        <td>
            <%--   ﻿ <a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
               <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
                  data-target="#modal-status">设置状态</a>
               {@if item.auditStatus==1}
               <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-audit">审核</a>
               {@/if}--%>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">更新</a>

            <%-- <a href="javascript:del('\${item.id}');">删除</a>--%>

        </td>
    </tr>
    {@/each}
</script>
﻿
<div class="modal fade " id="modal-status">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">设置状态</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-status" role="form">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-2 view-label">对象</label>
                            <div class="col-md-10 status-title">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">状态</label>
                            <div class="col-md-10">
                                <div class="radio-group-container">
                                    <input type="hidden" name="id">
                                    <label>
                                        <input type="radio" name="status" value="1"><span style="padding:10px">预播</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="status" value="2"><span
                                            style="padding:10px">直播中</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="status" value="3"><span style="padding:10px">历史</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn green status">确定</button>
            </div>
        </div>
    </div>
</div>


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
            <label class="col-md-2 view-label">定时任务序号</label>
            <div class="col-md-10">
                \${timerID}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">定时任务名称</label>
            <div class="col-md-10">
                \${timerName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">定时任务状态</label>
            <div class="col-md-10">
                \${timerEnable}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">启动时间</label>
            <div class="col-md-10">
                \${startTime}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">调度任务号</label>
            <div class="col-md-10">
                \${taskNo}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">备注</label>
            <div class="col-md-10">
                \${remark}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">状态</label>
            <div class="col-md-10">
                \${status}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建日期</label>
            <div class="col-md-10">
                \${createDate}
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
            \${data.o.id}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">定时任务序号</label>
        <div class="col-md-10">
            \${data.o.timerID}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">定时任务名称</label>
        <div class="col-md-10">
            \${data.o.timerName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">定时任务状态</label>
        <div class="col-md-10">
            \${data.o.timerEnable}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">启动时间</label>
        <div class="col-md-10">
            \${data.o.startTime}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">调度任务号</label>
        <div class="col-md-10">
            \${data.o.taskNo}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">备注</label>
        <div class="col-md-10">
            \${data.o.remark}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">状态</label>
        <div class="col-md-10">
            \${data.o.status}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${data.o.createDate}
        </div>
    </div>
    <div class="portlet light">
        <div class="portlet-title">
            <div class="caption font-green-sharp">
                <i class="icon-share font-green-sharp"></i>
                <span class="caption-subject bold uppercase"> 月数据列表</span>
            </div>
        </div>
        <div class="portlet-body">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <table class="table table-hover table-light">
                        <thead>
                        <tr>
                            <th width="8%"> 1月</th>
                            <th width="8%"> 2月</th>
                            <th width="8%"> 3月</th>
                            <th width="8%"> 4月</th>
                            <th width="8%"> 5月</th>
                            <th width="8%"> 6月</th>
                            <th width="8%"> 7月</th>
                            <th width="8%"> 8月</th>
                            <th width="8%"> 9月</th>
                            <th width="8%"> 10月</th>
                            <th width="8%"> 11月</th>
                            <th width="8%"> 12月</th>
                        </tr>
                        </thead>
                        <tbody>
                        {@each data.monthList as item, index}
                        <tr>
                            <td> \${parseStatus(item.m1)}</td>
                            <td> \${parseStatus(item.m2)}</td>
                            <td> \${parseStatus(item.m3)}</td>
                            <td> \${parseStatus(item.m4)}</td>
                            <td> \${parseStatus(item.m5)}</td>
                            <td> \${parseStatus(item.m6)}</td>
                            <td> \${parseStatus(item.m7)}</td>
                            <td> \${parseStatus(item.m8)}</td>
                            <td> \${parseStatus(item.m9)}</td>
                            <td> \${parseStatus(item.m10)}</td>
                            <td> \${parseStatus(item.m11)}</td>
                            <td> \${parseStatus(item.m12)}</td>
                        </tr>
                        {@/each}
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>

    <div class="portlet light">
        <div class="portlet-title">
            <div class="caption font-green-sharp">
                <i class="icon-share font-green-sharp"></i>
                <span class="caption-subject bold uppercase"> 周数据列表</span>
            </div>
        </div>
        <div class="portlet-body">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <table class="table table-hover table-light">
                        <thead>
                        <tr>
                            <th width="8%"> 1周</th>
                            <th width="8%"> 2周</th>
                            <th width="8%"> 3周</th>
                            <th width="8%"> 4周</th>
                            <th width="8%"> 5周</th>
                            <th width="8%"> 6周</th>
                            <th width="8%"> 7周</th>

                        </tr>
                        </thead>
                        <tbody>
                        {@each data.weekList as item, index}
                        <tr>
                            <td> \${parseStatus(item.w1)}</td>
                            <td> \${parseStatus(item.w2)}</td>
                            <td> \${parseStatus(item.w3)}</td>
                            <td> \${parseStatus(item.w4)}</td>
                            <td> \${parseStatus(item.w5)}</td>
                            <td> \${parseStatus(item.w6)}</td>
                            <td> \${parseStatus(item.w7)}</td>

                        </tr>
                        {@/each}
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
    <div class="portlet light">
        <div class="portlet-title">
            <div class="caption font-green-sharp">
                <i class="icon-share font-green-sharp"></i>
                <span class="caption-subject bold uppercase"> 日数据列表</span>
            </div>
        </div>
        <div class="portlet-body">
            <div class="row">
                <div class="table-scrollable">
                    <table class="table table-hover table-light">
                        <thead>
                        <tr>
                            <th width="5%"> 1日</th>
                            <th width="5%"> 2日</th>
                            <th width="5%"> 3日</th>
                            <th width="5%"> 4日</th>
                            <th width="5%"> 5日</th>
                            <th width="5%"> 6日</th>
                            <th width="5%"> 7日</th>
                            <th width="5%"> 8日</th>
                            <th width="5%"> 9日</th>
                            <th width="5%"> 10日</th>
                            <th width="5%"> 11日</th>
                            <th width="5%"> 12日</th>
                            <th width="5%"> 13日</th>
                            <th width="5%"> 14日</th>
                            <th width="5%"> 15日</th>
                            <th width="5%"> 16日</th>
                            <th width="5%"> 17日</th>
                            <th width="5%"> 18日</th>
                            <th width="5%"> 19日</th>
                            <th width="5%"> 20日</th>
                            <th width="5%"> 21日</th>
                            <th width="5%"> 22日</th>
                            <th width="5%"> 23日</th>
                            <th width="5%"> 24日</th>
                            <th width="5%"> 25日</th>
                            <th width="5%"> 26日</th>
                            <th width="5%"> 27日</th>
                            <th width="5%"> 28日</th>
                            <th width="5%"> 29日</th>
                            <th width="5%"> 30日</th>
                            <th width="5%"> 31日</th>
                        </tr>
                        </thead>
                        <tbody>
                        {@each data.dayList as item, index}
                        <tr>
                            <td> \${parseStatus(item.d1)}</td>
                            <td> \${parseStatus(item.d2)}</td>
                            <td> \${parseStatus(item.d3)}</td>
                            <td> \${parseStatus(item.d4)}</td>
                            <td> \${parseStatus(item.d5)}</td>
                            <td> \${parseStatus(item.d6)}</td>
                            <td> \${parseStatus(item.d7)}</td>
                            <td> \${parseStatus(item.d8)}</td>
                            <td> \${parseStatus(item.d9)}</td>
                            <td> \${parseStatus(item.d10)}</td>
                            <td> \${parseStatus(item.d11)}</td>
                            <td> \${parseStatus(item.d12)}</td>
                            <td> \${parseStatus(item.d13)}</td>
                            <td> \${parseStatus(item.d14)}</td>
                            <td> \${parseStatus(item.d15)}</td>
                            <td> \${parseStatus(item.d16)}</td>
                            <td> \${parseStatus(item.d17)}</td>
                            <td> \${parseStatus(item.d18)}</td>
                            <td> \${parseStatus(item.d19)}</td>
                            <td> \${parseStatus(item.d20)}</td>
                            <td> \${parseStatus(item.d21)}</td>
                            <td> \${parseStatus(item.d22)}</td>
                            <td> \${parseStatus(item.d23)}</td>
                            <td> \${parseStatus(item.d24)}</td>
                            <td> \${parseStatus(item.d25)}</td>
                            <td> \${parseStatus(item.d26)}</td>
                            <td> \${parseStatus(item.d27)}</td>
                            <td> \${parseStatus(item.d28)}</td>
                            <td> \${parseStatus(item.d29)}</td>
                            <td> \${parseStatus(item.d30)}</td>
                            <td> \${parseStatus(item.d31)}</td>
                        </tr>
                        {@/each}
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>

</script>
<style>
    .cover {
        width: 70px;
        height: 70px;
        object-fit: cover;
    }

    .describtion {
        padding-left: 15px;
        height: 50px;
    }

    .cost {
        padding-top: 5px;
        padding-left: 15px;
        color: #FE6500;
    }
</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

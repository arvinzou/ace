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

                <a href="javascript:syncData();" class="btn green">同步</a>
            </div>

            <div class="col-md-6" style="float: right;">

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


                    <th width="20%"> 定时任务名称</th>
                    <th width="20%"> 是否有效</th>
                    <th width="20%"> 启动时间</th>


                    <th width="25%"> 创建日期</th>
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


        <td> \${item.timerName}</td>
        <td> {@if item.timerEnable==0}
            <span>无效</span>
            {@else if item.timerEnable==1}
            <span>有效</span>
            {@else}
            {@/if}
        </td>
        <td> \${item.startTime}</td>


        <td> \${item.createDate}</td>

        <td>

            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
            <%-- <a href="edit/index.jsp?id=${param.id}&did=\${item.id}">更新</a>--%>
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
    <%--<div class="form-group">
        <label class="col-md-2 view-label">状态</label>
        <div class="col-md-10">
            \${data.o.status}
        </div>
    </div>--%>
    <div class="form-group">
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${data.o.createDate}
        </div>
    </div>
    <div class="portlet light">
        <h4 class="modal-title">月数据列表</h4>
        <div class="portlet-body">
            <div class="row">
                <div class="table-scrollable">
                    <ul class="dayul">
                        {@each data.monthList as item, index}
                        <li>
                            <div class="day">1月</div>
                            <div class="status">\${parseStatus(item.m1)}</div>
                        </li>
                        <li>
                            <div class="day">2月</div>
                            <div class="status">\${parseStatus(item.m2)}</div>
                        </li>
                        <li>
                            <div class="day">3月</div>
                            <div class="status">\${parseStatus(item.m3)}</div>
                        </li>
                        <li>
                            <div class="day">4月</div>
                            <div class="status">\${parseStatus(item.m4)}</div>
                        </li>
                        <li>
                            <div class="day">5月</div>
                            <div class="status">\${parseStatus(item.m5)}</div>
                        </li>
                        <li>
                            <div class="day">6月</div>
                            <div class="status">\${parseStatus(item.m6)}</div>
                        </li>
                        <li>
                            <div class="day">7月</div>
                            <div class="status">\${parseStatus(item.m7)}</div>
                        </li>
                        <li>
                            <div class="day">8月</div>
                            <div class="status">\${parseStatus(item.m8)}</div>
                        </li>
                        <li>
                            <div class="day">9月</div>
                            <div class="status">\${parseStatus(item.m9)}</div>
                        </li>
                        <li>
                            <div class="day">10月</div>
                            <div class="status">\${parseStatus(item.m10)}</div>
                        </li>
                        <li>
                            <div class="day">11月</div>
                            <div class="status">\${parseStatus(item.m11)}</div>
                        </li>
                        <li>
                            <div class="day">12月</div>
                            <div class="status">\${parseStatus(item.m12)}</div>
                        </li>
                        {@/each}
                    </ul>

                </div>
            </div>

        </div>
    </div>

    <div class="portlet light">

        <h4 class="modal-title"> 周数据列表</h4>

        <div class="portlet-body">
            <div class="row">
                <div class="table-scrollable">
                    <ul class="dayul">
                        {@each data.weekList as item, index}
                        <li>
                            <div class="day">周一</div>
                            <div class="status">\${parseStatus(item.w1)}</div>
                        </li>
                        <li>
                            <div class="day">周二</div>
                            <div class="status">\${parseStatus(item.w2)}</div>
                        </li>
                        <li>
                            <div class="day">周三</div>
                            <div class="status">\${parseStatus(item.w3)}</div>
                        </li>
                        <li>
                            <div class="day">周四</div>
                            <div class="status">\${parseStatus(item.w4)}</div>
                        </li>
                        <li>
                            <div class="day">周五</div>
                            <div class="status">\${parseStatus(item.w5)}</div>
                        </li>
                        <li>
                            <div class="day">周六</div>
                            <div class="status">\${parseStatus(item.w6)}</div>
                        </li>
                        <li>
                            <div class="day">周日</div>
                            <div class="status">\${parseStatus(item.w7)}</div>
                        </li>
                        {@/each}
                    </ul>
                </div>
            </div>

        </div>
    </div>
    <div class="portlet light">

        <h4 class="modal-title"> 日数据列表</h4>

        <div class="portlet-body">
            <div class="row">
                <div class="table-scrollable">
                    <ul class="dayul">
                        {@each data.dayList as item, index}
                        <li>
                            <div class="day">1日</div>
                            <div class="status">\${parseStatus(item.d1)}</div>
                        </li>
                        <li>
                            <div class="day">2日</div>
                            <div class="status">\${parseStatus(item.d2)}</div>
                        </li>
                        <li>
                            <div class="day">3日</div>
                            <div class="status">\${parseStatus(item.d3)}</div>
                        </li>
                        <li>
                            <div class="day">4日</div>
                            <div class="status">\${parseStatus(item.d4)}</div>
                        </li>
                        <li>
                            <div class="day">5日</div>
                            <div class="status">\${parseStatus(item.d5)}</div>
                        </li>
                        <li>
                            <div class="day">6日</div>
                            <div class="status">\${parseStatus(item.d6)}</div>
                        </li>
                        <li>
                            <div class="day">7日</div>
                            <div class="status">\${parseStatus(item.d7)}</div>
                        </li>
                        <li>
                            <div class="day">8日</div>
                            <div class="status">\${parseStatus(item.d8)}</div>
                        </li>
                        <li>
                            <div class="day">9日</div>
                            <div class="status">\${parseStatus(item.d9)}</div>
                        </li>
                        <li>
                            <div class="day">10日</div>
                            <div class="status">\${parseStatus(item.d10)}</div>
                        </li>
                        <li>
                            <div class="day">11日</div>
                            <div class="status">\${parseStatus(item.d11)}</div>
                        </li>
                        <li>
                            <div class="day">12日</div>
                            <div class="status">\${parseStatus(item.d12)}</div>
                        </li>
                        <li>
                            <div class="day">13日</div>
                            <div class="status">\${parseStatus(item.d13)}</div>
                        </li>
                        <li>
                            <div class="day">14日</div>
                            <div class="status">\${parseStatus(item.d14)}</div>
                        </li>
                        <li>
                            <div class="day">15日</div>
                            <div class="status">\${parseStatus(item.d15)}</div>
                        </li>
                        <li>
                            <div class="day">16日</div>
                            <div class="status">\${parseStatus(item.d16)}</div>
                        </li>
                        <li>
                            <div class="day">17日</div>
                            <div class="status">\${parseStatus(item.d17)}</div>
                        </li>
                        <li>
                            <div class="day">18日</div>
                            <div class="status">\${parseStatus(item.d18)}</div>
                        </li>
                        <li>
                            <div class="day">19日</div>
                            <div class="status">\${parseStatus(item.d19)}</div>
                        </li>
                        <li>
                            <div class="day">20日</div>
                            <div class="status">\${parseStatus(item.d20)}</div>
                        </li>
                        <li>
                            <div class="day">21日</div>
                            <div class="status">\${parseStatus(item.d21)}</div>
                        </li>
                        <li>
                            <div class="day">22日</div>
                            <div class="status">\${parseStatus(item.d22)}</div>
                        </li>
                        <li>
                            <div class="day">23日</div>
                            <div class="status">\${parseStatus(item.d23)}</div>
                        </li>
                        <li>
                            <div class="day">24日</div>
                            <div class="status">\${parseStatus(item.d24)}</div>
                        </li>
                        <li>
                            <div class="day">25日</div>
                            <div class="status">\${parseStatus(item.d25)}</div>
                        </li>
                        <li>
                            <div class="day">26日</div>
                            <div class="status">\${parseStatus(item.d26)}</div>
                        </li>
                        <li>
                            <div class="day">27日</div>
                            <div class="status">\${parseStatus(item.d27)}</div>
                        </li>
                        <li>
                            <div class="day">28日</div>
                            <div class="status">\${parseStatus(item.d28)}</div>
                        </li>
                        <li>
                            <div class="day">29日</div>
                            <div class="status">\${parseStatus(item.d29)}</div>
                        </li>
                        <li>
                            <div class="day">30日</div>
                            <div class="status">\${parseStatus(item.d30)}</div>
                        </li>
                        <li>
                            <div class="day">31日</div>
                            <div class="status">\${parseStatus(item.d31)}</div>
                        </li>
                        {@/each}
                    </ul>

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

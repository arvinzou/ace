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
    <title>爱心场地</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <%--common css--%>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <%--custom css--%>
    <link rel="stylesheet" href="fullcalendar/fullcalendar.min.css">
    <%--<link rel="stylesheet" href="fullcalendar/fullcalendar.print.css">--%>
</head>
<body>
<%--==============common jsp-prefix==============--%>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-prefix==============--%>

<div class="portlet light">
    <div class="portlet-body">
        <div class="row custom-toolbar">
            <div class="col-sm-7">
                <a onclick="javascript:add()" class="btn  green">
                    <i class="fa fa-plus"></i> 添加
                </a>
            </div>
            <div class="col-sm-5">
                <form onsubmit="return t_query()">
                    <div class="input-group">
                        <input type="text" name="keyword" class="form-control " placeholder="请输入场地名称">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn" type="submit">搜索</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="30%"> 场地名称</th>
                    <%--<th width="20%"> 场地分类</th>--%>
                    <th width="30%"> 爱心币</th>
                    <th width="20%"> 状态</th>
                    <%--<th width="20%"> 已预约场次</th>--%>
                    <%--<th width="10%"> 备注</th>--%>
                    <th width="20%">操作</th>
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

<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-lock">
    <div class="modal-dialog" role="document" style="width: 80%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">爱心场地锁定</h4>
            </div>
            <div class="modal-body">
                <div id='wrap'>
                    <div id='external-events'>
                        <h4>可选时间</h4>
                        <div class='fc-event'>AM 9:00-11:00</div>
                        <div class='fc-event'>PM 15:00-17:00</div>
                    </div>
                    <div id='calendar'></div>
                    <div style='clear:both'></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td>
            <img src="\${item.commodityCover}" class="cover"/>
            \${item.commodityName}
        </td>
        <%--<td> \${item.category}</td>--%>
        <td> \${item.costPoints}</td>
        <td>
            {@if item.state==1}
            <span class="label label-lg label-success">在售</span>
            {@else}
            <span class="label label-lg label-danger">已下架</span>
            {@/if}
        </td>
        <%--<td> \${item.remark}</td>--%>
        <%--<td> 0</td>--%>
        <td>
            <a class="operation" href="javascript:detail('\${item.id}');">查看</a>
            {@if item.state==1}
            <a class="operation" href="#" data-toggle="modal" data-target="#modal-lock" data-id="\${item.id}">锁定</a>
            <a class="operation" href="javascript:outline('\${item.id}');">下架</a>
            {@else}
            <a class="operation" href="javascript:edit('\${item.id}');">编辑</a>
            <a class="operation" href="javascript:del('\${item.id}');">删除</a>
            <a class="operation" href="javascript:online('\${item.id}');">上架</a>
            {@/if}
        </td>
    </tr>
    {@/each}
</script>
<%--详情juicer模板--%>
<script id="tpl-detail" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">场地编号</label>
            <div class="col-md-10">
                \${data.id}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">场地名称</label>
            <div class="col-md-10">
                \${data.commodityName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">场地封面</label>
            <div class="col-md-10">
                <img src="\${data.commodityCover}" style="max-width:480px;"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">场地简介</label>
            <div class="col-md-10">
                \$\${data.summary}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">爱心币</label>
            <div class="col-md-10">
                \${data.costPoints}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">状态</label>
            <div class="col-md-10">
                \${parseState(data.state)}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">备注</label>
            <div class="col-md-10">
                \${data.remark}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建人姓名</label>
            <div class="col-md-10">
                \${data.createUserName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建日期</label>
            <div class="col-md-10">
                \${data.createDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新人名称</label>
            <div class="col-md-10">
                \${data.lastModifyUserName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新日期</label>
            <div class="col-md-10">
                \${data.lastModifyDate}
            </div>
        </div>
    </div>
</script>
<%--查看详情--%>
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document" style="width: 90%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">爱心场地详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-detail" role="form">
                    <%--详情模板填充--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <%--
                <button type="button" class="btn btn-primary">确定</button>
                --%>
            </div>
        </div>
    </div>
</div>

<!--审核弹框-->
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-audit">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">爱心商品审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">
                    <div class="form-body">
                        <div class="form-group " id="operation">
                            <label class="col-md-2 control-label">审核结果</label>
                            <div class="col-md-10">
                                <div class="radio-group-container">
                                    <label>
                                        <input type="radio" name="rst" value="3"><span style="padding:10px">通过</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="rst" value="4"><span style="padding:10px">退回</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">审核说明</label>
                            <div class="col-md-10">
                                <input type="hidden" name="id"/>
                                <textarea name="message" style="width: 100%;height: 100px;"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>
<div id="calendar1"></div>
</body>
<style>
    .cover {
        width: 70px;
        height: 70px;
        object-fit: cover;
    }

    .description {
        padding-left: 15px;
        height: 50px;
    }

    .cost {
        padding-top: 5px;
        padding-left: 15px;
        color: #FE6500;
    }

    #wrap {
        width: 100%;
        margin: 0 auto;
    }

    #external-events {
        float: left;
        width: 15%;
        padding: 0 10px;
        border: 1px solid #ccc;
        background: #eee;
        text-align: left;
    }

    #external-events h4 {
        font-size: 16px;
        margin-top: 0;
        padding-top: 1em;
    }

    #external-events .fc-event {
        height: 25px;
        text-align: center;
        padding-bottom: 5px;
        padding-top: 5px;
        margin: 10px 0;
        cursor: pointer;
    }

    #external-events p {
        margin: 1.5em 0;
        font-size: 11px;
        color: #666;
    }

    #external-events p input {
        margin: 0;
        vertical-align: middle;
    }

    #calendar {
        float: right;
        width: 80%;
    }

    .fc-content {
        text-align: center;
    }

    .custom-occupy {
        color: black;
        background-color: yellow;
        border: 0px solid white;
        height: 20px;
        text-align: center;
        vertical-align: middle;
        padding-top: 5px;
    }

    .admin-occupy {
        color: white;
        background-color: red;
        border: 0px solid white;
        height: 20px;
        text-align: center;
        vertical-align: middle;
        padding-top: 5px;
    }
</style>


<%--==============common footer==============--%>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<%--custom js--%>
<script src="fullcalendar/moment.min.js"></script>
<script src="fullcalendar/jquery-ui.custom.min.js"></script>
<script src="fullcalendar/fullcalendar.min.js"></script>

<script src="js/act.js?v=${cfg.version}"></script>

</html>
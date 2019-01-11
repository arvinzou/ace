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
    <link rel="stylesheet" type="text/css" href="CommonUI/Content/common-base.css"/>
    <link rel="stylesheet" type="text/css" href="CommonUI/font4.4/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="CommonUI/Content/common/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="CommonUI/Content/common/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" type="text/css" href="CommonUI/Content/common/dataTables.bootstrap.min.css"/>

    <!--引用js-->
    <script src="CommonUI/Scripts/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="CommonUI/Scripts/jquery-ui.js"></script>
    <script type="text/javascript" src="CommonUI/Scripts/bootstrap.min.js"></script>
    <script type="text/javascript" src="CommonUI/Scripts/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="CommonUI/Scripts/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="CommonUI/layer/layer.js"></script>


    <link rel="stylesheet" type="text/css" href="css/fc_schedule.plug_in.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="CommonUI/fullcalendar2.9/fullcalendar.min.css"/>
    <script>

    </script>

</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">
                <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>
            </div>

            <div class="col-md-9">

                <form onsubmit="return t_query()">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('status','');">全部</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','1');">预播</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','2');">直播</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','3');">历史</button>
                    </div>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('auditStatus','');">全部</button>
                        <button type="button" class="btn btn-default" onclick="setParams('auditStatus','1');">待审
                        </button>
                        <button type="button" class="btn btn-default" onclick="setParams('auditStatus','2');">通过
                        </button>
                        <button type="button" class="btn btn-default" onclick="setParams('auditStatus','3');">驳回
                        </button>
                    </div>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('category','');">全部</button>
                        <button type="button" class="btn btn-default" onclick="setParams('category','1');">图文</button>
                        <button type="button" class="btn btn-default" onclick="setParams('category','2');">视频</button>
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入直播名称">
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
        <div class="row">
            <div class="col-md-12">
                <div class="b-container" id="b_container">
                    <div class="col-sm-12 clearfix margin0 ">
                        <!--<div id='selectdate'>-->
                        <!--初始化年份工作日：<input id="initDateInput" type="text" class="t-input" value="2016-07-29">-->
                        <!--<input id="initDateBtn" type="button" class="t-button ml10" value="初始化">-->
                        <!--</div>-->
                        <div class="col-sm-2 margin0 padding0">
                            <div id='external-events' class="external-events" >
                                <div class="form-group">
                                    <input type="text" class="form-control" id="exampleInputName2" placeholder="按老师或课程名搜索">
                                </div>
                                <ul class="external-events-box" id="courseList">

                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-8 margin0 padding0">
                            <div id='calendar' class="ml5 padding5" ></div>
                        </div>
                        <div class="col-sm-2 margin0 padding0">

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script id="tpl-courseList" type="text/template">
    {@each data as item, index}
    <li>
        <h5 ><i class="fa fa-hand-o-right mr5"></i>\${index}<i class="fa fa-angle-double-down fr"></i></h5>
        <ul>
            {@each item as ite, ind}
            <li class='fc-event event-item bg-crew-yingji' data-teacher="\${index}" data-class="fc-event bg-crew-yingji">
                \${ite.name}
            </li>
            {@/each}
        </ul>
    </li>
    {@/each}
</script>

<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>
<jsp:include page="/dynamic/common/footer.jsp"/>

<script src='CommonUI/fullcalendar2.9/moment.min.js'></script>
<script type="text/javascript" src="CommonUI/fullcalendar2.9/fullcalendar.min.js"></script>
<script type="text/javascript" src="CommonUI/fullcalendar2.9/zh-cn.js"></script>
<script type="text/javascript" src="CommonUI/fullcalendar2.9/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="js/fc_schedule.plug_in.js"></script>
</html>
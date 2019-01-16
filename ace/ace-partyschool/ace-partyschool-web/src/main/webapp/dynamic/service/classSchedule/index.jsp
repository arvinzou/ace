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
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/select2/css/select2-bootstrap.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/select2/css/select2.css">

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
            <div class="col-md-12">
                <form onsubmit="return t_query()">
                    <div class="btn-group" id="classList" role="group" style="float:left;padding-right:5px">
                    </div>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <select style="width: 200px" class="form-control js-example-basic-single js-example-basic-single2" name="classId"></select>
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
                                <div class="search">
                                    <input class="courseNameTeacher" type="text"><button class="searchCourse"><span class="searchIcon"></span></button>
                                </div>
                                <ul class="external-events-box" id="courseList">

                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-8 margin0 padding0">
                            <div id='calendar' class="ml5 padding5" ></div>
                        </div>
                        <div class="col-sm-2 margin0 padding0">
                            <div col-sm-12>
                                <h4>课程名称</h4>
                            </div>
                            <p col-sm-12>
                                <h5>学习习总书记的党的领导学习习总书记的党的领导</h5>
                            </p>
                            <div col-sm-12>
                                <h4>授课老师</h4>
                            </div>
                            <p col-sm-12>
                                <h5>学习习总书记的党的领导学习习总书记的党的领导</h5>
                            </p>
                            <div col-sm-12>
                                <h4>学习教室</h4>
                            </div>
                            <p col-sm-12>
                                <h5>学习习总书记的党的领导学习习总书记的党的领导</h5>
                            </p>
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
            <li class='fc-event event-item bg-crew-yingji' data-teacherId="\${ite.teacherId}"  data-courseId="\${ite.id}" data-teacher="\${index}" data-class="fc-event bg-crew-yingji">
                \${ite.name}
            </li>
            {@/each}
        </ul>
    </li>
    {@/each}
</script>


<script id="tpl-classList" type="text/template">
    {@each data as item, index}
    {@if index==0}
        <button type="button" data-id="\${item.id}" class="btn btn-default btn-primary">\${item.name}</button>
    {@else}
        <button type="button" data-id="\${item.id}" class="btn btn-default">\${item.name}</button>
    {@/if}
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
<script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/select2/js/select2.js"></script>
</html>
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
    <%--<link rel="stylesheet" type="text/css" href="CommonUI/font4.4/css/font-awesome.min.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="CommonUI/Content/common/bootstrap.min.css"/>--%>
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/select2/css/select2-bootstrap.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/select2/css/select2.css">

    <!--引用js-->



    <link rel="stylesheet" type="text/css" href="css/fc_schedule.plug_in.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="CommonUI/fullcalendar2.9/fullcalendar.min.css"/>
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
                            <div id='calendar' ></div>
                        </div>
                        <div class="col-sm-2 margin0 padding0">
                            <div class="classinfo">
                                <div class="head">课程详情</div>
                                <div class="body" id="courseDetail">

                                </div>
                            </div>
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
        <h5 class="teacherNameList"><i class="fa fa-user" aria-hidden="true"></i>\${index}<i class="fa fa-angle-double-down fr"></i></h5>
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

<script id="tpl-courseDetail" type="text/template">
    <p class="title">课程名称</p>
    <p class="text">\${data.course.name}</p>
    <p class="title">授课人</p>
    <p class="text">\${data.teacher.name}</p>
    <p class="title">班次</p>
    <p class="text">\${data.classes.name}</p>
    <p class="title">是否测评</p>
    <div class="switch_box box_4">
        <div class="input_wrapper">
            <input type="checkbox" data-id="\${data.id}" \${data.ifTest==1?'checked':''} class="switch_4">
            <svg class="is_checked" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 426.67 426.67">
                <path d="M153.504 366.84c-8.657 0-17.323-3.303-23.927-9.912L9.914 237.265c-13.218-13.218-13.218-34.645 0-47.863 13.218-13.218 34.645-13.218 47.863 0l95.727 95.727 215.39-215.387c13.218-13.214 34.65-13.218 47.86 0 13.22 13.218 13.22 34.65 0 47.863L177.435 356.928c-6.61 6.605-15.27 9.91-23.932 9.91z"/>
            </svg>
            <svg class="is_unchecked" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 212.982 212.982">
                <path d="M131.804 106.49l75.936-75.935c6.99-6.99 6.99-18.323 0-25.312-6.99-6.99-18.322-6.99-25.312 0L106.49 81.18 30.555 5.242c-6.99-6.99-18.322-6.99-25.312 0-6.99 6.99-6.99 18.323 0 25.312L81.18 106.49 5.24 182.427c-6.99 6.99-6.99 18.323 0 25.312 6.99 6.99 18.322 6.99 25.312 0L106.49 131.8l75.938 75.937c6.99 6.99 18.322 6.99 25.312 0 6.99-6.99 6.99-18.323 0-25.313l-75.936-75.936z" fill-rule="evenodd" clip-rule="evenodd"/>
            </svg>
        </div>
    </div>
    <p class="title">操作</p>
    <div>
        <button type="button" data-id="\${data.id}" class="btn btn-default delete">删除</button>
    </div>
</script>


<script id="tpl-classList" type="text/template">
    {@each data as item, index}
    {@if index==0}
        <button type="button" data-id="\${item.id}" class="btn btn-default active">\${item.name}</button>
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
<script type="text/javascript" src="CommonUI/Scripts/jquery-ui.js"></script>
<script type="text/javascript" src="CommonUI/layer/layer.js"></script>
<script src='CommonUI/fullcalendar2.9/moment.min.js'></script>
<script type="text/javascript" src="CommonUI/fullcalendar2.9/fullcalendar.min.js"></script>
<script type="text/javascript" src="CommonUI/fullcalendar2.9/zh-cn.js"></script>
<script type="text/javascript" src="CommonUI/fullcalendar2.9/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="js/fc_schedule.plug_in.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/select2/js/select2.js"></script>
</html>
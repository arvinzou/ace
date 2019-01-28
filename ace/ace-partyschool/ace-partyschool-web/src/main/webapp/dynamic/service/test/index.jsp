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
    <title>课程管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>

</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-9">

            </div>

            <div class="col-md-3">

                <form onsubmit="return t_query()">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="getNotDoneTestList();">未评测</button>
                        <button type="button" class="btn btn-default" onclick="getDoneTestList();">已评测</button>
                    </div>
                </form>
            </div>

        </div>


        <div class="table-scrollable">
            <div class="listInfo">
                <div class="content" id="testList">

                </div>
            </div>
        </div>
    </div>

</div>







<script id="tpl_notDoneTest" type="text/template">
    {@each data as item}
    <div class="temp temp2" data-eid="\${item.course.evaluatingId}" data-cid="\${item.id}">
        <div class="left">
            <p class="title">\${item.course.name}</p>
            <p class="otherInfo">
                <img class="icon" src="img/icon_time.png" alt="">
                <span class="margin30">\${item.courseDate|dateTimeToDate} \${item.courseIndex=='am'?'上午':'下午'}</span>
                <img class="icon" src="img/icon_person.png" alt="">
                <span>\${item.teacher.name}</span></p>
        </div>
        <div class="right view">评测</div>
    </div>
    {@/each}
</script>


<script id="tpl_doneTest" type="text/template">
    {@each data as item}
    <div class="temp temp1" data-eid="\${item.course.evaluatingId}" data-cid="\${item.id}">
        <div class="left">
            <p class="title">\${item.course.name}</p>
            <p class="otherInfo">
                <img class="icon" src="img/icon_time.png" alt="">
                <span class="margin30">\${item.courseDate|dateTimeToDate} \${item.courseIndex=='am'?'上午':'下午'}</span>
                <img class="icon" src="img/icon_person.png" alt="">
                <span>\${item.teacher.name}</span></p>
        </div>
        <div class="right viewed">查看</div>
    </div>
    {@/each}
</script>






<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script src="js/index.js"></script>
</html>
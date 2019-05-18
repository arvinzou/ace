<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>常德警官培训中心</title>
    <jsp:include page="../../common/common.jsp"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
</head>
<body>
<div class="container">
    <div class="userinfo">
        <div class="user-inner" id="userInfo"></div>
    </div>
    <div class="index">
        <div class="box">
            <div class="today">今日课程</div>
            <div class="calendar" onclick="weekCourse();">
                <div class="icon-calendar"><img src="img/icon-calendar.png"/></div>
                <div class="calendar-title"><span>周课表</span></div>
            </div>
        </div>
        <div id="todayCourse">

        </div>
        <div id="noticeTop">

        </div>
    </div>
    <div class="menu" id="menu">

    </div>
</div>

<script id="course-tpl" type="text/template">
    {@if data.length > 0}
    {@each data as item, index}
    <div class="course morning">
        <div class="course-left">
            <span>\${item.startTime.substring(10,16)}-</span>
            <span>\${item.endTime.substring(10,16)}</span>
        </div>
        <div class="course-right">
            <div class="inner-middle">
                <span>\${item.course.name}</span>
                <span>(主讲:\${item.teacher.name}）</span>
            </div>
        </div>
    </div>
    {@/each}
    {@else}
    <div class="course none">
        暂无课程
    </div>
    {@/if}
</script>

<script id="userinfo-tpl" type="text/template">
    <div class="header" onclick="showPersonInfo();">
        {@if data.isBindWx == "1"}
        <img src="\${data.avatarUrl}"/>
        {@else}
        <img src="img/default_header.png"/>
        {@/if}
    </div>
    <div class="username" onclick="showPersonInfo();">
        {@if data.regType == 'student'}
        <span>\${data.student.name}</span>
        {@else}
        <span>\${data.teacher.name}</span>
        {@/if}
    </div>
    <div class="dorm">
        {@if data.regType == 'student'&&data.student.dorm}
        <span>宿舍房号：\${data.student.dorm}</span>
        {@/if}
    </div>
   <%-- <div class="show-card" onclick="toConsumption();">
        <span>查询一卡通</span>
        <img src="img/icon_select.png"/>
    </div>--%>
</script>

<script id="noticeTop-tpl" type="text/template">
    {@if data.title}
    <div class="notice">
        <div class="notice-icon"><img src="img/icon-message.png"/></div>
        <div class="notice-title">\${data.title}</div>
        <div id="more" class="more" onclick="moreNotice();">
            <span>全部</span><img src="img/icon_select.png"/>
        </div>
    </div>
    {@/if}
</script>

<script id="menu-tpl" type="text/template">
    {@if data.regType == 'student'}
    <div class="row">
        <div class="item mr" onclick="classNotice();">
            <div class="item-left">
                <img src="img/icon-notice.png" class="menu-icon"/>
            </div>
            <div class="item-right">
                <div class="menu-title">学员须知</div>
                <div class="menu-content">已绑定班级</div>
            </div>
        </div>
        <div class="item" onclick="toClassFiles();">
            <div class="item-left">
                <img src="img/icon-files.png" class="menu-icon"/>
            </div>
            <div class="item-right">
                <div class="menu-title">班级文件</div>
                <div class="menu-content">共\${data.file}个文件</div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="item mr" onclick="toMailList();">
            <div class="item-left">
                <img src="img/icon-mail.png" class="menu-icon"/>
            </div>
            <div class="item-right">
                <div class="menu-title">通讯录</div>
                <div class="menu-content">共
                    {@if data.regType == 'student'}
                    \${data.student}
                    {@else}
                    \${data.teacher}
                    {@/if}
                    人
                </div>
            </div>
        </div>
        <div class="item" onclick="attendtion();">
            <div class="item-left">
                <img src="img/icon-appear.png" class="menu-icon"/>
            </div>
            <div class="item-right">
                <div class="menu-title">考勤记录</div>
                <div class="menu-content">查看考勤记录</div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="item mr" onclick="toTest();">
            <div class="item-left">
                <img src="img/icon-test.png" class="menu-icon"/>
            </div>
            <div class="item-right">
                <div class="menu-title">教学测评</div>
                <div class="menu-content">共有\${data.test}个待评</div>
            </div>
        </div>
    </div>
    {@else}

    <div class="row">
        <div class="item mr" onclick="toClassFiles();">
            <div class="item-left">
                <img src="img/icon-files.png" class="menu-icon"/>
            </div>
            <div class="item-right">
                <div class="menu-title">班级文件</div>
                <div class="menu-content">查看文件</div>
            </div>
        </div>
        <div class="item" onclick="toMailList();">
            <div class="item-left">
                <img src="img/icon-mail.png" class="menu-icon"/>
            </div>
            <div class="item-right">
                <div class="menu-title">通讯录</div>
                <div class="menu-content">共
                    {@if data.regType == 'student'}
                    \${data.student}
                    {@else}
                    \${data.teacher}
                    {@/if}
                    人
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="item mr" onclick="attendtion();">
            <div class="item-left">
                <img src="img/icon-appear.png" class="menu-icon"/>
            </div>
            <div class="item-right">
                <div class="menu-title">考勤记录</div>
                <div class="menu-content">查看考勤记录</div>
            </div>
        </div>
    </div>
    {@/if}
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</body>
</html>

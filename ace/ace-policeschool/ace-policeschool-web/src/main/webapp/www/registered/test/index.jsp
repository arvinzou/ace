<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
    <jsp:include page="../../common/common.jsp"/>
</head>
<body>
<div class="headInfo">
    <div class="content">
        <div class="userInfo" id="userInfo">
            <div class="headImg">
                <img src="http://suo.im/4C98Qc">
            </div>
            <div class="name">
                你的名字
            </div>
        </div>
        <div class="listBtn" id="testNumber">

        </div>
    </div>
</div>
<div class="listInfo">
    <div class="content" id="testList">

    </div>
</div>

<script id="tpl_userInfo" type="text/template">
    <div class="headImg">
        {@if data.isBindWx == "1"}
        <img src="\${data.avatarUrl}"/>
        {@else}
        <img src="img/default_header.png"/>
        {@/if}
    </div>
    <div class="name">
        \${data.student.name}
    </div>
</script>


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
        <div class="right view">测评</div>
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


<script id="tpl_testNumber" type="text/template">
    <span class="action notDoneBtn">未评(\${data.notDoneSize})</span><span class="doneBtn">已评(\${data.doneSize})</span>
</script>


</body>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="js/index.js"></script>
</html>

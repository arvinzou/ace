<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>班牌</title>
    <jsp:include page="../common/common.jsp"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel="stylesheet" href="css/viewer.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/swiper/css/swiper.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
</head>

<body>
<div class="content">
    <div class="head">
        <%--<div class="room_name active_changeRoom pointer">一教室</div>--%>
        <span style="
    font-size: 78px;
    font-weight: bold;
    color: #fff;
    text-shadow: 2px 7px 5px rgba(0, 0, 0, 0.28);
"><img src="./img/logo.png" alt="">常德警官培训中心</span>
        <div class="dateTime" id="clock">
            <p>1921年7月1日 </p>
            <p>12:00 星期六</p>
        </div>
    </div>
    <div class="body">
        <div class="info_box">
            <div STYLE="width: 60% ;    margin-right: 24px;">
                <table id="" class="tftable" border="1">
                    <thead>
                    <tr>
                        <th class="style1">班级</th>
                        <th class="style2 active_changeRoom pointer class_name">课程</th>
                        <th class="style4"><span class="active_classInfo">详情</span></th>
                    </tr>
                    </thead>
                    <tbody id="classList">

                    </tbody>
                </table>
            </div>
            <div style="width: 40%">
                <div class="message">
                    <div class="title">
                        <span>通知公告</span>
                    </div>
                    <div class="fileMessage">
                        <div class="swriper-box">

                            <div id="swiper_container" class="swiper-container">
                                <div class="swiper-wrapper">
                                </div>
                                <!-- 如果需要导航按钮 -->
                                <div class="swiper-button-prev"></div>
                                <div class="swiper-button-next"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="hide modal modal1">
    <div class="modal_content classInfo">
        <div class=" modal_head">
            <span>班级信息</span> <span class="hideModal pointer">×</span>
        </div>
        <div class="modal_body" id="classTemp">


        </div>
    </div>
</div>

<div class=" hide modal modal2">
    <div class="modal_content weeekClass">
        <div class=" modal_head">
            <span>周课表</span> <span class="hideModal pointer">×</span>
        </div>
        <div class="modal_body">
            <div id="weeekCourseList">

            </div>
            <div class="prevWeek"></div>
            <div class="nextWeek"></div>
        </div>
    </div>
</div>

<div class="hide modal modal3">
    <div class="modal_content photoWall">
        <div class=" modal_head">
            <span>相册</span> <span class="hideModal pointer">×</span>
        </div>
        <div class="modal_body">
            <div class="imgBox" id="imgTemp">

            </div>
        </div>
    </div>
</div>

<div class="hide modal modal4">
    <div class="modal_content addBook">
        <div class=" modal_head">
            <span>通讯录</span> <span class="hideModal pointer">×</span>
        </div>
        <div class="modal_body">
            <ul id="student">
            </ul>
        </div>
    </div>
</div>


<div class="hide  modal modal5">
    <div class="modal_content classRoom">
        <div class=" modal_head">
            <span>选择班级</span> <span class="hideModal pointer">×</span>
        </div>
        <div class="modal_body">
            <div class="classRoomList" id="roomTemp">

            </div>
        </div>

    </div>
</div>


</body>

<script id="tpl-classTemp" type="text/template">
    <div class="title">
        <img src="img/icon_base.png" alt=""/><span>基本信息</span>
    </div>
    <div class="table">
        <table id="tfhover" class="tftable" border="1">
            <tr>
                <td class="style5">班次</td>
                <td>\${data.list.name}</td>
                <td class="fontBlue active_photos">相册</td>
            </tr>
            <tr>
                <td class="style5">班级教室</td>
                <td colspan="2">\${data.list.classroom.name}</td>
            </tr>
            <tr>
                <td class="style5">具体位置</td>
                <td colspan="2">\${data.list.classroom.introduce}</td>
            </tr>
        </table>
    </div>

    <div class="title titleMT">
        <img src="img/icon_member.png" alt=""/><span>班级成员</span>
    </div>
    <div class="table">
        <table class="tftable" border="1">
            <tr>
                <td class="style5">班主任</td>
                <td>\${data.list.teacher.name}</td>
                <td>\${data.list.teacher.mobile}</td>
            </tr>
            <tr>
                <td class="style5">跟班老师</td>
                <td>\${data.list.clsteacher?data.list.clsteacher.name:''}</td>
                <td>\${data.list.clsteacher?data.list.clsteacher.mobile:''}</td>
            </tr>
            <tr>
                <td class="style5">跟班干部</td>
                <td>\${data.list.student?data.list.student.name:''}</td>
                <td>\${data.list.student?data.list.student.mobile:''}</td>
            </tr>
            <tr>
                <td class="style5">班级学员</td>
                <td class="fontBlue active_student" colspan="2"> 查看班级通讯录(\${data.count})</td>
            </tr>
        </table>
    </div>
</script>


<script id="tpl-imgTemp" type="text/template">
    {@each data as item}
    <li class="img pointer">
        <img src="\${item.url}" alt=""/>
    </li>
    {@/each}
</script>
<script id="tpl-student" type="text/template">
    {@each data as item}
    <li><span>\${item.name}</span> <span class="style5">\${item.mobile}</span></li>
    {@/each}
</script>

<script id="tpl-roomTemp" type="text/template">
    {@each data as item}
    <div class="roomItem" data-id="\${item.id}">\${item.name}</div>
    {@/each}
</script>

<script id="tpl-classList" type="text/template">
    {@each data as item}
    <tr>
        <td class="style3">\${substringDate(item.startTime)}-\${substringDate(item.endTime)}</td>
        <td class="amClass"> \${item.course.name}</td>
        <td class="style3"> \${item.teacher.name}</td>
    </tr>
    {@/each}


</script>

<script type="text/javascript" src="${portalPath}/content/common/swiper/js/swiper.min.js"></script>
<script src="js/viewer.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
</html>
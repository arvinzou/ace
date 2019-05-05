<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>弱电</title>
</head>
<script src="js/index.min.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="css/common.css"/>
<link rel="stylesheet" href="css/foundation-datepicker.min.css"/>
<link rel="stylesheet" href="css/index.css"/>
<%
    session.setAttribute("portalPath", "/portal");
%>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var version = '${cfg.version}';
    var fastdfs_server = '${cfg.fastdfs_server}';
    var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
    var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
    var default_page_list = [${cfg.default_page_list}];
</script>

<body>
<div class="head">
    <div class="logo">
        <img src="img/logo.png" alt=""/> 江汉区照明控制平台
    </div>
    <div class="btns">
        <div  data-type="sceneControl" class="btn">
            <p class="cn">场景控制</p>
            <p class="en">Scene Control</p>
        </div>

        <div data-type="strategyPart" class="btn strategySend">
            <p class="cn">策略下发</p>
            <p class="en">Strategy Send</p>
        </div>
    </div>
    <div class="userInfo">
        <div class="up">
            <div class="headImg">
                <img alt="" class="img-circle"
                     src="${portalPath}/content/common/assets/layouts/layout/img/avatar3_small.jpg"/>
            </div>
            <span class="username username-hide-on-mobile"> ${SESSION_USERPROP_KEY.name} </span>

            <div class="menu-wrap">
                <ul class="menu-ul">
                    <li><a href="${portalPath}/index.jsp">返回首页</a></li>
                    <li><a href="${portalPath}/dynamic/portal/security/loginOut.jsp">安全退出</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<%--场景控制--%>
<div class="sceneControl content">
    <div class="left">
        <div class="control">
            <div class="title">
                场景控制
            </div>
            <div class="btns">
                <div id="play">
                    <img data-state="1" src="img/start.png" />
                    <p>恢复</p>
                </div>
                <div id="pause">
                    <img data-state="2" src="img/stop.png" />
                    <p>停止</p>
                </div>
                <div>
                    <img src="img/change.png" />
                    <p>切换</p>
                </div>
            </div>
            <div class="radio">
            </div>
            <div class="wrap switch-wrap">
                <input type="checkbox" id="s6" value="1"/>
                <label class="slider-v3 label-switch" for="s6"></label>
            </div>
            <!--/wrap-->

        </div>
    </div>
    <div class="center">
        <div class="checkList">
            <ul id="checked">

            </ul>
        </div>
        <div class="videoList">
            <ul id="videos">
                <div class="default">
                    <img src="img/default.png" alt="">
                    <p>当前暂无场景，请先选择站点</p>
                </div>
            </ul>
        </div>
    </div>
    <div class="right">
        <div class="stations">
            <div class="title">江汉区站点(多选)</div>
            <div class="inputGroup">
                <input type="text" placeholder="输入站点名称"/>
                <button>

                </button>
            </div>
            <div class="sList">
                <ul id="check">

                </ul>
            </div>
        </div>
    </div>
</div>

<%--策略下发--%>
<div style="display:none" class="content strategyPart content1">
    <div class="modals strategy">
        <div class="modals-head">
            <span class="title">定时设置</span>
            <div class="operation">
                <button class="addStrategy">创建</button>
                <div class="inputGroup">
                    <input class="strategyName" type="text" placeholder="搜索" name="name"/>
                    <button class="searchBtn">
                    </button>
                </div>
            </div>
        </div>
        <div class="modals-body">
            <div class="table">
                <table class="table-tg" style="border:1px solid rgb(12,129,135);">
                    <tr>
                        <td class="tg-mvxc" width="10%">策略编号</td>
                        <td class="tg-mvxc" width="30%">策列名称</td>
                        <td class="tg-mvxc" width="10%">分区编号</td>
                        <td class="tg-mvxc" width="20%">操作</td>
                    </tr>
                    <tbody id="strategyList">

                    </tbody>
                </table>
            </div>
            <div class="paginationbar" style="float: right">
                <ul class="pagination" id="pagination3"></ul>
            </div>
        </div>
    </div>
</div>

<%--切换--%>
<div style="display: none" class="modal">
    <div class="modal-content">
        <div class="bg">
            <div class="lefgBg">
                <div class="l1"></div>
                <div class="l2"></div>
                <div class="l3"></div>
                <div class="l2 l22"></div>
                <div class="l4"></div>

            </div>
            <div class="centerBg">
                <div class="c1"></div>
                <div class="c2"></div>
                <div class="c3"></div>
            </div>
            <div class="rightBg">
                <div class="r1"></div>
                <div class="r2"></div>
                <div class="r3"></div>
                <div class="r2 r22"></div>
                <div class="r4"></div>
            </div>
        </div>
        <div class="modal-body">
            <div class="inputGroup">
                <input type="text" placeholder="输入站点名称"/>
                <button>
                </button>
            </div>

            <div class="inputGroup buttonGroup">
                <button>
                    确定
                </button>
            </div>
            <div class="videoList">
                <ul>
                    <li class="videoGroup">
                        <div class="info">
                            <label class="label" for="c1">
                                <input id="c1" type="checkbox"/> 视频名称
                            </label>
                        </div>
                        <div class="video">
                            <video controls="true" autoplay="true">
                                <source src="http://www.w3school.com.cn/example/html5/mov_bbb.mp4"
                                        type="video/mp4">
                                </source>
                            </video>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>


<%----%>
<div class="modal scenario-modal" style="display: none">
    <div class="modal-content">
        <div class="bg">
            <div class="lefgBg">
                <div class="l1"></div>
                <div class="l2"></div>
                <div class="l3"></div>
                <div class="l2 l22"></div>
                <div class="l4"></div>

            </div>
            <div class="centerBg">
                <div class="c1"></div>
                <div class="c2"></div>
                <div class="c3"></div>
            </div>
            <div class="rightBg">
                <div class="r1"></div>
                <div class="r2"></div>
                <div class="r3"></div>
                <div class="r2 r22"></div>
                <div class="r4"></div>
            </div>
        </div>
        <div class="modal-body">
            <div class="ulList">
                <ul id="presets">
                </ul>
            </div>
        </div>
        <div class="modal-close">
            <img src="img/close.png" alt="">
        </div>
    </div>
</div>

<%--添加策略--%>
<div class="modal addStrategy-modal" style="display: none">
    <div class="modal-content">
        <div class="bg">
            <div class="lefgBg">
                <div class="l1"></div>
                <div class="l2"></div>
                <div class="l3"></div>
                <div class="l2 l22"></div>
                <div class="l4"></div>

            </div>
            <div class="centerBg">
                <div class="c1"></div>
                <div class="c2"></div>
                <div class="c3"></div>
            </div>
            <div class="rightBg">
                <div class="r1"></div>
                <div class="r2"></div>
                <div class="r3"></div>
                <div class="r2 r22"></div>
                <div class="r4"></div>
            </div>
        </div>
        <div class="modal-body">
                <div class="wrap">
                    <div class="title">添加/修改策略</div>
                    <div class="form">
                        <form id="strategyInfo">

                        </form>
                    </div>
                    <div class="bottom">
                        <button type="submit" form="strategyInfo">确定</button>
                    </div>
                </div>
        </div>
        <div class="modal-close">
            <img src="img/close.png" alt="">
        </div>
    </div>
</div>


<%--设置定时--%>
<div class="modal setTime-modal" style="display: none">
    <div class="modal-content">
        <div class="bg">
            <div class="lefgBg">
                <div class="l1"></div>
                <div class="l2"></div>
                <div class="l3"></div>
                <div class="l2 l22"></div>
                <div class="l4"></div>

            </div>
            <div class="centerBg">
                <div class="c1"></div>
                <div class="c2"></div>
                <div class="c3"></div>
            </div>
            <div class="rightBg">
                <div class="r1"></div>
                <div class="r2"></div>
                <div class="r3"></div>
                <div class="r2 r22"></div>
                <div class="r4"></div>
            </div>
        </div>
        <div class="modal-body">
            <div class="box">
                <div class="unit">
                    <div class="piece week active" data-type="week">日程数据<br/>(周)</div>
                    <div class="piece month" data-type="month">日程数据<br/>(月)</div>
                    <div class="piece holiday" data-type="holiday">假日模式</div>
                    <div class="piece event" data-type="event">事件模式</div>
                </div>
                <div class="info">
                    <div class="btn-list">
                        <button class="style1" form="setS">确定</button>
                    </div>
                    <div class="show" id="page-update">
                        <form id="setS" action="">
                            <div class="form" id="inputList">
                            </div>
                            <div class="inputTime">
                                <p>时间范围</p>
                                <input name="startTime" type="text" autocomplete="off">
                                <input name="stopTime" type="text" autocomplete="off">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-close">
            <img src="img/close.png" alt="">
        </div>
    </div>
</div>

</body>

<script id="tpl-check" type="text/template">
    {@each data as item, index}
    <li data-name="\${item.name}" data-code="\${item.code}">\${item.name}</li>
    {@/each}
</script>

<script id="tpl-checked" type="text/template">
    {@each data as item, index}
    <li data-name="\${item.name}" data-code="\${item.code}">\${item.name}</li>
    {@/each}
</script>

<script id="tpl-videos" type="text/template">
    {@each data as item, index}
    <li class="videoGroup video\${item.stationCode}">
        <div class="info">
            <label class="label" for="c\${item.id}">
                <input id="c\${item.id}" type="checkbox"/> \${item.animaResVo.name}
            </label>
        </div>
        <div class="video">
            <video controls="true">
                <source src="\${item.prePlayUrl}"
                        type="video/mp4">
                </source>
            </video>
        </div>
    </li>
    {@/each}
</script>


<script id="tpl-strategyList" type="text/template">
    {@each data as item, index}
    <tr class="tr">
        <td class="tg-84q5"> \${item.code}</td>
        <td class="tg-84q5"> \${item.name}</td>
        <td class="tg-84q5">\${item.areaCode}</td>
        <td class="tg-84q5">
            <a href="javascript:editStrategy('\${item.id}');" style="color: #53FDFF;">修改</a>
            <a href="javascript:setTimer('\${item.id}');" style="color: #53FDFF;">定时</a>
            <a href="javascript:selectPreset('\${formatObject(item)}');" style="color: #53FDFF;">下发</a>
        </td>
    </tr>
    {@/each}
</script>

<%--<script id="tpl-strategyList" type="text/template">--%>
    <%--{@each data as item, index}--%>
    <%--<li>--%>
        <%--<div class="top">--%>
            <%--\${item.code}-\${item.name}--%>
        <%--</div>--%>
        <%--<div class="bottom">--%>
            <%--<button data-areaNo="\${item.areaNo}" data-areaNodeID="\${item.areaNodeID}">--%>
                <%--选择--%>
            <%--</button>--%>
        <%--</div>--%>
    <%--</li>--%>
    <%--{@/each}--%>
<%--</script>--%>

<script id="tpl-presets" type="text/template">
    {@each data as item, index}
    <li data-strategyNum="\${item.strategyNum}">\${item.strategyExplain}</li>
    {@/each}
</script>


<script id="tpl-weeks" type="text/template">
    <ul class="list weeks">
        {@each data.daysShort as item, index}
        <li>
            <span>\${item}</span>
            <div class="wrap">
                <input type="checkbox" id="w\${item}" name="weeks" \${isChecked(data.weeks, +index+1)} value="\${+index+1}"/>
                <label class="slider-v3" for="w\${item}"></label>
            </div>
        </li>
        {@/each}
    </ul>
</script>

<script id="tpl-months" type="text/template">

    <ul class="list months">
        {@each data.months as item, index}
        <li>
            <span>\${item}</span>
            <div class="wrap">
                <input type="checkbox" id="m\${item}" name="months" \${isChecked(data.mons, +index+1)} value="\${+index+1}"/>
                <label class="slider-v3" for="m\${item}"></label>
            </div>
        </li>
        {@/each}
    </ul>

</script>

<script id="tpl-strategyInfo" type="text/template">
        <input type="text" name="id" style="display: none;" value="\${data.id}">
        <div class="input-Group">
            <p>策略编号*</p>
            <input name="code" type="text"  value="\${data.code}">
        </div>
        <div class="input-Group">
            <p>策略名称*</p>
            <input name="name" type="text"  value="\${data.name}">
        </div>
        <div class="input-Group">
            <p>分区编号*</p>
            <input name="areaCode" type="text"  value="\${data.areaCode}">
        </div>
        <div class="input-Group">
            <p>策略描述</p>
            <textarea name="remark">\${data.remark}</textarea>
        </div>
</script>

<script src="https://cdn.bootcss.com/jquery/3.4.0/jquery.min.js"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="js/foundation-datepicker.min.js"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>

</html>
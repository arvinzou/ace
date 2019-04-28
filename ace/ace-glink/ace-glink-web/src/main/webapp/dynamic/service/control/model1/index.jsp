<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>弱电</title>
</head>
<script src="js/index.min.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="css/common.css"/>
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
        <div class="btn">
            <p class="cn">场景控制</p>
            <p class="en">Scene Control</p>
        </div>
        <div class="btn">
            <p class="cn">场景列表</p>
            <p class="en">Scene List</p>
        </div>
        <div class="btn strategySend">
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
        </div>
        <div class="menu-wrap">
            <ul class="menu-ul">
                <li><a href="${portalPath}/index.jsp">返回首页</a></li>
                <li><a href="${portalPath}/dynamic/portal/security/loginOut.jsp">安全退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div  class="sceneControl content">
    <div class="left">
        <div class="control">
            <div class="title">
                场景控制
            </div>
            <div class="btns">
                <div>
                    <img src="img/start.png" alt=""/>
                    <p>暂停</p>
                </div>
                <div>
                    <img src="img/stop.png" alt=""/>
                    <p>恢复</p>
                </div>
                <div>
                    <img src="img/change.png" alt=""/>
                    <p>切换</p>
                </div>
            </div>
            <div class="radio">

            </div>
            <div class="wrap">
                <input type="checkbox" id="s6" checked=""/>
                <label class="slider-v3" for="s6"></label>
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

<div style="display: none"  class="content strategySend content1">
    <div class="modals task scenario">
        <div class="modals-head">
            <span class="title">场景执行</span>
            <div class="inputGroup margin-0">
                <input class="timerName" type="text" placeholder="输入任务名称" name="timerName"/>
                <button class="submit">

                </button>
            </div>
        </div>
        <div class="modals-body">
            <ul class="taskList" id="strategyList">

            </ul>
            <%--分页页脚--%>
            <div class="paginationbar">
                <ul class="pagination" id="pagination3"></ul>
            </div>
        </div>
    </div>
</div>



<div class="modal" style="display: none">
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
            <div class="presetList">
                <div class="ulList">
                    <ul id="presets">
                    </ul>
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
    <li>
        <div class="top">
            \${item.code}-\${item.name}
        </div>
        <div class="bottom">
            <button data-areaNo="\${item.areaNo}" data-areaNodeID="\${item.areaNodeID}">
                选择
            </button>
        </div>
    </li>
    {@/each}
</script>

<script id="tpl-presets" type="text/template">
    {@each data as item, index}
    <li data-presetNo="\${item.presetNo}">\${item.strategyExplain}</li>
    {@/each}
</script>


<style type="text/css">

</style>
<script src="https://cdn.bootcss.com/jquery/3.4.0/jquery.min.js"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<script>
    //菜单显示与隐藏
    document.onclick = function(e) {
        $('.menu-wrap').hide();
    };
    $('.userInfo>.up').on("click", function(e) {
        if($('.menu-wrap').css("display") == "none") {
            $('.menu-wrap').show();
        } else {
            $('.menu-wrap').hide();
        }
        e = e || event;
        stopFunc(e);
    });

    $('.menu-wrap').on("click", function(e) {
        e = e || event;
        stopFunc(e);
    });
    function stopFunc(e) {
        e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
    }
</script>
</html>
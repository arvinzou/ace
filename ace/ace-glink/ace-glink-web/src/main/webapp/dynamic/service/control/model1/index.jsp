<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
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
        <div class="btn">
            <p class="cn">策略下发</p>
            <p class="en">Strategy Send</p>
        </div>
    </div>
</div>
<div class="content">
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

<style type="text/css">
    .modal {
        position: fixed;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.9);
        display: flex;
        align-items: center;
        top: 0;
        justify-content: center;
    }

    .modal .modal-content {
        position: relative;
    }

    .modal .modal-body {
        position: absolute;
        width: 100%;
        height: 100%;
        top: 0;
        padding: 0.3rem 0.15rem 0.15rem;
    }

    .modal .modal-body .videoList {
        padding-top: 0.35rem;
        height: 100%;
    }

    .modal .modal-body .buttonGroup {
        width: 0.5rem;
    }

    .modal .modal-body .buttonGroup button {
        border-radius: 0.020833rem;
        background-image: none;
        color: white;
        cursor: pointer;
    }

    .modal .modal-body .inputGroup {
        margin: 0;
        float: right;
    }

    .modal .modal-body .videoGroup {
        width: 2.020833rem;
    }

    .modal .modal-body .videoGroup .info {
        height: 0.260416rem;
        line-height: 0.260416rem;
    }

    .modal .modal-body .videoGroup .video {
        height: 1.145833rem;
    }

    .modal .modal-content {
        width: 8.75rem;
        height: 4.166666rem;
    }

    .modal .bg {
        display: flex;
        width: 100%;
        height: 100%;
    }

    .modal .bg .lefgBg {
        width: 1.770833rem;
        display: flex;
        flex-direction: column;
    }

    .modal .bg .lefgBg .l1,
    .modal .bg .rightBg .r1,
    .modal .bg .centerBg .c1 {
        width: 100%;
        height: 0.322916rem;
        background: url(./modalImg/l1.png) no-repeat;
        background-size: 100% 100%;
    }

    .modal .bg .rightBg .r1 {
        background: url(./modalImg/r1.png) no-repeat;
        background-size: 100% 100%;
    }

    .modal .bg .centerBg .c1 {
        background: url(./modalImg/c1.png);
        background-repeat-y: no-repeat;
        background-repeat-x: repeat;
        background-size: auto 100%;
    }

    .modal .bg .lefgBg .l2,
    .modal .bg .rightBg .r2 {
        width: 100%;
        background: url(./modalImg/l2.png) repeat;
        flex-grow: 1;
        background-repeat-y: repeat;
        background-repeat-x: no-repeat;
        background-size: 100%;
    }

    .modal .bg .rightBg .r2 {
        background: url(./modalImg/r2.png) repeat;
        background-repeat-y: repeat;
        background-repeat-x: no-repeat;
        background-size: 100%;
    }

    .modal .bg .lefgBg .l22 {
        flex-grow: 2;
    }

    .modal .bg .rightBg .r22 {
        flex-grow: 1;
    }

    .modal .bg .lefgBg .l3,
    .modal .bg .rightBg .r3 {
        width: 100%;
        height: 0.520833rem;
        background: url(./modalImg/l3.png) no-repeat;
        background-size: 100% 100%;
    }

    .modal .bg .rightBg .r3 {
        background: url(./modalImg/r3.png) no-repeat;
        background-size: 100% 100%;
    }

    .modal .bg .lefgBg .l4,
    .modal .bg .rightBg .r4 {
        width: 100%;
        height: 0.520833rem;
        background: url(./modalImg/l4.png) no-repeat;
        background-size: 100% 100%;
    }

    .modal .bg .rightBg .r4 {
        background: url(./modalImg/r4.png) no-repeat;
        background-size: 100% 100%;
    }

    .modal .bg .centerBg {
        flex-grow: 1;
        display: flex;
        flex-direction: column;
    }

    .modal .bg .centerBg .c2 {
        flex-grow: 1;
    }

    .modal .bg .centerBg .c3 {
        width: 100%;
        height: 0.520833rem;
        background: url(./modalImg/c3.png);
        background-repeat-y: no-repeat;
        background-repeat-x: repeat;
        background-size: auto 100%;
    }

    .modal .bg .rightBg {
        width: 1.5625rem;
        display: flex;
        flex-direction: column;
    }
</style>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="https://cdn.bootcss.com/jquery/3.4.0/jquery.min.js"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
</html>
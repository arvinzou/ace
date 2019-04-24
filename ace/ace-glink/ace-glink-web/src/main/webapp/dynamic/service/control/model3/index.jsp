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
            <p class="cn">任务管理</p>
            <p class="en">Task Management</p>
        </div>
        <div class="btn">
            <p class="cn">场景执行</p>
            <p class="en">Scenario Execution</p>
        </div>
        <div class="btn">
            <p class="cn">定时设置</p>
            <p class="en">Timing Settings</p>
        </div>
        <div class="btn">
            <p class="cn">总控设置</p>
            <p class="en">Control Set</p>
        </div>
    </div>
</div>
<div class="content">
    <div class="modals Timing" id="Timer" style="display: none;">
        <div class="modals-head">
            <span class="title">定时设置</span>
            <form id="fm-search">
                <div class="inputGroup">
                    <input type="text" placeholder="输入任务名称" name="timerName"/>
                    <button type="submit">

                    </button>
                </div>
            </form>
        </div>
        <div class="modals-body">
            <table class="table-tg" style="border:1px solid rgb(12,129,135);">
                <tr>
                    <td class="tg-mvxc" width="10%">定时任务序号</td>
                    <td class="tg-mvxc" width="30%">定时任务名称</td>
                    <td class="tg-mvxc" width="10%">是否有效</td>
                    <td class="tg-mvxc" width="20%">启动时间</td>
                    <td class="tg-mvxc" width="20%">操作</td>
                </tr>
                <tbody id="page-list">

                </tbody>
            </table>
            <div class="paginationbar" style="float: right">
                <ul class="pagination" id="pagination1"></ul>
            </div>
        </div>
    </div>


    <div class="modals Control" id="yearCron">
        <div class="modals-head">
            <span class="title">总控设置</span>
            <button class="activeBtn" onclick="monthList();">
                执行
            </button>
        </div>
        <div class="modals-body">
            <div class="panel">
                <div class="left">
                    <ul class="months" id="months">
                        <li id="m1">一月</li>
                        <li id="m2">二月</li>
                        <li id="m3">三月</li>
                        <li id="m4">四月</li>
                        <li id="m5">五月</li>
                        <li id="m6">六月</li>
                        <li id="m7">七月</li>
                        <li id="m8">八月</li>
                        <li id="m9">九月</li>
                        <li id="m10">十月</li>
                        <li id="m11">十一月</li>
                        <li id="m12">十二月</li>
                    </ul>
                </div>
                <div class="right">
                    <div class="heads">
                        <button class="h-btn" id="dayCron">全部日程模式</button>
                        <button class="h-btn" id="jieri">全部节日模式</button>
                        <button class="h-btn" id="zhong">全部重大节日模式</button>
                    </div>
                    <div class="table">
                        <ul class="days" id="page-YearCronlist">

                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div style="display: none;" class="modals task">
        <div class="modals-head">
            <span class="title">任务管理</span>
            <button class="activeBtn">
                执行
            </button>
        </div>
        <div class="modals-body">
            <ul class="taskList">
                <li>
                    <div class="top">
                        文字
                    </div>
                    <div class="bottom">
                        <span class="status success">成功</span>
                        <button>
                            执行
                        </button>
                    </div>
                </li>
                <li>
                    <div class="top">
                        文字
                    </div>
                    <div class="bottom">
                        <span class="status error">失败</span>
                        <button>
                            执行
                        </button>
                    </div>
                </li>
            </ul>
        </div>
    </div>


    <div style="display: none" class="modals task scenario">
        <div class="modals-head">
            <span class="title">场景执行</span>
            <button class="activeBtn">
                执行
            </button>
        </div>
        <div class="modals-body">
            <ul class="taskList">
                <li>
                    <div class="top">
                        文字
                    </div>
                    <div class="bottom">
                        <button>
                            执行
                        </button>
                    </div>
                </li>
                <li>
                    <div class="top">
                        文字
                    </div>
                    <div class="bottom">
                        <button>
                            执行
                        </button>
                    </div>
                </li>
            </ul>
        </div>
    </div>

</div>


<div class="modal timing-modal">
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
                    <div class="piece active">月数据</div>
                    <div class="piece">周数据</div>
                    <div class="piece">日数据</div>
                </div>
                <div class="info">
                    <div class="btn-list">
                        <button class="style1">全部有效</button>
                        <button class="style1">全部无效</button>
                    </div>
                    <div class="show">
                        <ul class="list">
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>

                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                            <li>
                                <span>12月</span>
                                <div class="wrap">
                                    <input type="checkbox" id="s6"/>
                                    <label class="slider-v3" for="s6"></label>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr class="tr">

        <td class="tg-84q5"> \${item.timerID}</td>
        <td class="tg-84q5"> \${item.timerName}</td>
        <td class="tg-84q5"> {@if item.timerEnable==0}
            <span style="color: #FF616D;">无效</span>
            {@else if item.timerEnable==1}
            <span style="color: #0AFD99;">有效</span>
            {@else}
            {@/if}
        </td>
        <td class="tg-84q5">\${item.startTime}</td>
        <td class="tg-84q5">

            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.timerName}"
               data-target="#modal-preview" style="color: #53FDFF;">更新</a>

        </td>
    </tr>
    {@/each}
</script>
<script id="tpl-YearCronlist" type="text/template">
    {@each data as item, index}
    <li id="s\${+index+1}">
        <div class="day">
            \${+index+1}
        </div>
        <div class="seled">
            <div class="checkboxGroup">
                <input id="\${+index+1}-a" name="m\${+index+1}" type="radio" value="1" {@if item==1} checked {@/if} />
                <label class="check1" for="\${+index+1}-a">日程模式</label>
            </div>
            <div class="checkboxGroup">
                <input id="\${+index+1}-b" name="m\${+index+1}" type="radio" value="2" {@if item==2} checked {@/if}>
                <label class="check2" for="\${+index+1}-b">节日模式</label>
            </div>
            <div class="checkboxGroup">
                <input id="\${+index+1}-c" name='m\${+index+1}' type="radio" value="3" {@if item==3} checked {@/if}>
                <label class="check3" for="\${+index+1}-c">重大节日模式</label>
            </div>
        </div>
    </li>
    {@/each}
</script>

<style type="text/css">
    .timing-modal .box {
        width: 8.364583rem;
        height: 3.770833rem;
        display: flex;
        margin: auto;
    }

    .timing-modal .box .unit .piece {
        width: 100%;
        height: calc(100% / 3);
        background: rgba(37, 52, 61, 1);
        font-size: 0.125rem;
        font-family: MicrosoftYaHei;
        font-weight: 400;
        color: rgba(167, 233, 234, 1);
        display: flex;
        align-items: center;
        justify-content: center;

    }

    .timing-modal .box .unit .piece:hover {
        background: rgb(31, 44, 51);
    }

    .timing-modal .box .unit .piece.active {
        position: relative;
        background: rgba(7, 200, 202, 1);
        color: #fff;
    }

    .timing-modal .box .unit .piece.active.active::after {
        position: absolute;
        display: block;
        content: '';
        width: 0.0625rem;
        height: 0.0625rem;
        top: 50%;
        left: 100%;
        transform: translate(-50%, -50%) rotate(45deg);
        background: #1A2933;
    }

    .timing-modal .box .unit {
        width: 0.947916rem;
    }

    .timing-modal .box .info {
        flex-grow: 1;
        display: flex;
        flex-direction: column;
        width:8.364583rem;
    }

    .timing-modal .box .info .btn-list {
        width: 100%;
        height: 0.260416rem;
        background: rgba(40, 53, 62, 1);
        display: flex;
        align-items: center;
        justify-content: flex-end;
        padding:0 0.15625rem;
    }

    .timing-modal .box .info .show {
        flex-grow: 1;
        background: rgba(26, 41, 51, 1);
        font-size: 0.125rem;
        font-family: MicrosoftYaHei;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
        padding: 0.15625rem;
        overflow: auto;
    }

    .timing-modal .box .info .list:after {
        display: block;
        clear: both;
        content: '';
    }

    .timing-modal .box .info .list {
        display: flex;
        flex-wrap: wrap;
    }

    .timing-modal .box .info .list > li {
        display: flex;
        width: 1.135416rem;
        height: 0.489583rem;
        background: rgba(13, 67, 72, 1);
        border: 1px solid rgba(7, 200, 202, 1);
        border-radius: 0.020833rem;
        padding: 0 0.104166rem;
        justify-content: space-between;
        align-items: center;
        float: left;
        margin: 0 0.077rem 0.1rem 0;
    }
    .timing-modal .box .info .list > li:nth-child(6n+6){
        margin: 0;
    }

    .timing-modal .box .info .show input {
        display: none;
    }

    .timing-modal .box .info .btn-list >button{
        margin-left:0.08rem;
    }

</style>
<%--<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}"
        type="text/javascript"></script>--%>
<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}"
        type="text/javascript"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=V1.0.3"
        type="text/javascript"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
</html>
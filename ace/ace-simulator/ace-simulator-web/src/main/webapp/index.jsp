<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/index.css">
    <title>${cfg.sys_name}</title>
</head>

<jsp:include page="/dynamic/common/header.jsp"/>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="index">
    <div class="part01">
        <div class="meter-title">快捷入口</div>
        <div class="meter-menu">
            <div class="menu-item cjkz mr" onclick="window.location.href='/simulator/dynamic/service/scene/index.jsp?id=5302'">
                <div class="icon-box">
                    <img src="${pageContext.request.contextPath}/content/common/img/icon-cjkz.png" class="icon-image">
                </div>
                <div class="menu-detail">
                    <div class="content-box">
                        <div class="title-01">场景控制</div>
                        <div class="title-02">启动、关闭、暂停恢复场景</div>
                    </div>
                </div>
            </div>
            <div class="menu-item clgl mr" onclick="window.location.href='/simulator/dynamic/service/ltStrategy/index.jsp?id=5303'">
                <div class="icon-box">
                    <img src="${pageContext.request.contextPath}/content/common/img/icon-clgl.png" class="icon-image">
                </div>
                <div class="menu-detail">
                    <div class="content-box">
                        <div class="title-01">策略管理</div>
                        <div class="title-02">策略生成与下发</div>
                    </div>
                </div>
            </div>
            <div class="menu-item gzjk mr" onclick="window.location.href='/simulator/dynamic/service/errFeedback/index.jsp?id=530502'">
                <div class="icon-box">
                    <img src="${pageContext.request.contextPath}/content/common/img/icon-gzjk.png" class="icon-image">
                </div>
                <div class="menu-detail">
                    <div class="title-01">故障监控</div>
                    <div class="title-02">监控设备故障情况</div>
                </div>
            </div>
            <div class="menu-item jmsc mr" onclick="window.location.href='/simulator/dynamic/service/animaLnk/index.jsp?id=530401'">
                <div class="icon-box">
                    <img src="${pageContext.request.contextPath}/content/common/img/icon-jmsc.png" class="icon-image">
                </div>
                <div class="menu-detail">
                    <div class="title-01">节目上传</div>
                    <div class="title-02">上传节目媒体文件</div>
                </div>
            </div>
            <div class="menu-item gismap" onclick="window.location.href='/simulator/dynamic/service/gis/index.jsp?id=5307'">
                <div class="icon-box">
                    <img src="${pageContext.request.contextPath}/content/common/img/icon-gis.png" class="icon-gis">
                </div>
                <div class="menu-detail">
                    <div class="title-01">GIS地图</div>
                    <div class="title-02">展示建筑物信息及故障情况</div>
                </div>
            </div>
        </div>
        <div class="meter-title">数据统计</div>
        <div class="statics-list" id="totalCount">

        </div>
    </div>

    <div class="part02">
        <div class="static-left">
            <div class="top-title-box">
                <div class="top-title"><span class="top-title-01">故障监控</span></div>
                <div class="chart-select-box">
                    <div class="chart-option">
                        <%--<div class="selectBox">
                            <div class="inputCase">
                                <input class="imitationSelect" type="text" oulName="" oulId="" value="2019" readonly/>
                                <i class="fa fa-caret-down"></i>
                            </div>
                            <ul class="selectUl">
                                <li oliName="2019" oliId="2019" class="actived_li">2019</li>
                                <li oliName="2018" oliId="2018">2018</li>
                            </ul>
                        </div>--%>
                            <select id="year" onchange="ErrorChart();">
                                <option value="2019">2019</option>
                                <option value="2018">2018</option>
                            </select>
                            <select id="month" onchange="ErrorChart();">
                                <option value="04">04</option>
                                <option value="01">01</option>
                                <option value="02">02</option>
                                <option value="03">03</option>
                                <option value="05">05</option>
                                <option value="06">06</option>
                                <option value="07">07</option>
                                <option value="08">08</option>
                                <option value="09">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                            </select>
                    </div>
                </div>
            </div>
            <div class="chart-box" id="chartBox" style="min-width: 700px;max-width: 900px;height: 450px;">

            </div>
        </div>
        <div class="static-right">
            <div class="top-title-box">
                <div class="top-title"><span class="top-title-01">故障报警TOP10</span><span class="top-title-02">(故障时间倒叙)</span></div>
                <div class="top-more" onclick="window.location.href='/simulator/dynamic/service/errFeedback/index.jsp?id=530502'"><img src="${pageContext.request.contextPath}/content/common/img/icon-more.png"></div>
            </div>
            <div class="top-list">
                <div class="form-horizontal" role="form">
                    <div class="form-body">
                        <div class="table-scrollable">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th width="10%" style="text-align: center;">故障等级</th>
                                    <th width="20%" style="text-align: center;">设备名称</th>
                                    <th width="20%" style="text-align: center;">故障位置</th>
                                    <th width="20%" style="text-align: center;">故障类型</th>
                                    <th width="10%" style="text-align: center;">故障数量</th>
                                    <th width="20%" style="text-align: center;">故障时间</th>
                                </tr>
                                </thead>
                                <tbody id="top10-list">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script id="top10-tpl" type="text/template">
    {@each data as item, index}
    <tr>
        <td width="10%">
            {@if item.errLevel == '03'}
            <div class="serious"></div>
            {@else if item.errLevel == '02'}
            <div class="more-serious"></div>
            {@else if item.errLevel == '01'}
            <div class="less-serious"></div>
            {@/if}
        </td>
        <td width="20%" style="text-align: center;font-size: 8px !important;">\${item.deviceName}</td>
        <td width="20%" style="text-align: center;font-size: 8px !important;">\${item.subareaName}</td>
        <td width="20%" style="text-align: center;font-size: 8px !important;">\${item.errContent}</td>
        <td width="10%" style="text-align: center;font-size: 8px !important;">\${item.errLoopNum}</td>
        <td width="20%" style="text-align: center;font-size: 8px !important;">\${item.errDate}</td>
    </tr>
    {@/each}
</script>

<script id="total-count-tpl" type="text/template">
    <div class="statics-item mr">
        <div class="total-count">\${data.topBuildingCount}</div>
        <div class="total-count-tips"><span class="tip-01">建筑物总数</span></div>
    </div>
    <div class="statics-item mr">
        <div class="total-count">\${data.offlineDeviceCount}</div>
        <div class="total-count-tips">
            <span class="tip-01">离线设备  |</span>
            <span class="tip-02">总设备数:\${data.totalDeviceCount}</span>
        </div>
    </div>
    <div class="statics-item mr">
        <div class="total-count">\${data.totalErrLoopNum}</div>
        <div class="total-count-tips"><span class="tip-01">故障回路</span></div>
    </div>
    <div class="statics-item mr">
        <div class="total-count">\${data.totalIngStrategy}</div>
        <div class="total-count-tips">
            <span class="tip-01">执行中策略 |</span>
            <span class="tip-02">总策略数:\${data.totalStrategy}</span>
        </div>
    </div>
    <div class="statics-item mr">
        <div class="total-count">\${data.totalScene}</div>
        <div class="total-count-tips">
            <span class="tip-01">场景总数</span>
        </div>
    </div>
    <div class="statics-item">
        <div class="total-count">\${data.totalEnergy}kw</div>
        <div class="total-count-tips">
            <span class="tip-01">累计能耗</span></div>
    </div>
</script>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/common/js/echarts.js"></script>
<script src="${pageContext.request.contextPath}/content/common/js/index.js"></script>
</body>
</html>

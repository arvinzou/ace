<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>强电</title>
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
        <div data-type="tasks" class="btn">
            <p class="cn">任务管理</p>
            <p class="en">Task Management</p>
        </div>
        <div data-type="scenario" class="btn">
            <p class="cn">场景执行</p>
            <p class="en">Scenario Execution</p>
        </div>
        <div data-type="Timing" class="btn">
            <p class="cn">定时设置</p>
            <p class="en">Timing Settings</p>
        </div>
        <div data-type="Control" class="btn">
            <p class="cn">总控设置</p>
            <p class="en">Control Set</p>
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
<div class="content">
    <div class="modals Timing" style="display: none;">
        <div class="modals-head">
            <span class="title">定时设置</span>
            <div class="inputGroup">
                <input class="timerName" type="text" placeholder="输入任务名称" name="timerName"/>
                <button class="submit">

                </button>
            </div>
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
            <button class="activeBtn style1" onclick="postList();">
                执行
            </button>
        </div>
        <div class="modals-body">
            <div class="panel">
                <div class="left">
                    <ul class="months" id="months">
                        <li data-id="1">一月</li>
                        <li data-id="2">二月</li>
                        <li data-id="3">三月</li>
                        <li data-id="4">四月</li>
                        <li data-id="5">五月</li>
                        <li data-id="6">六月</li>
                        <li data-id="7">七月</li>
                        <li data-id="8">八月</li>
                        <li data-id="9">九月</li>
                        <li data-id="10">十月</li>
                        <li data-id="11">十一月</li>
                        <li data-id="12">十二月</li>
                    </ul>
                </div>
                <div class="right">
                    <div class="heads">
                        <button class="style1" data-type="1">全部日程模式</button>
                        <button class="style1" data-type="2">全部节日模式</button>
                        <button class="style1" data-type="3">全部重大节日模式</button>
                    </div>
                    <div class="table">
                        <ul class="days" id="page-YearCronlist">

                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div style="display: block" class="modals tasks task">
        <div class="modals-head">
            <span class="title">任务管理</span>
            <input placeholder="请选择分区" id="taskAreaNode" class="easyui-combotree"
                   data-options="url:'${pageContext.request.contextPath}/seProjectArea/selectTreeList?id=01',method:'get',animate: true,
                lines:true," style='min-width:225px;height:0.260416rem;'>
        </div>
        <div class="modals-body">
            <%--list--%>
            <ul class="taskList" id="taskList">

            </ul>

            <%--分页页脚--%>
            <div class="paginationbar">
                <ul class="pagination" id="pagination2"></ul>
            </div>
        </div>
    </div>


    <div class="modals task scenario">
        <div class="modals-head">
            <span class="title">场景执行</span>
            <input placeholder="请选择分区" id="areaNodeID1" class="easyui-combotree"
                   data-options="url:'${pageContext.request.contextPath}/seProjectArea/selectTreeList?id=01',method:'get',animate: true,
                lines:true," style='min-width:225px;height:0.260416rem;'>
        </div>
        <div class="modals-body">
            <ul class="taskList" id="scenarioList">

            </ul>
            <%--分页页脚--%>
            <div class="paginationbar">
                <ul class="pagination" id="pagination3"></ul>
            </div>
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
                    <div class="piece active" data-type="monthData">月数据</div>
                    <div class="piece" data-type="weekData">周数据</div>
                    <div class="piece" data-type="dayData">日数据</div>
                </div>
                <div class="info">
                    <div class="btn-list">
                        <button class="style1" onclick="TimerUpdate();">执行</button>
                        <button class="style1" id="AllEffective">全部有效</button>
                        <button class="style1" id="AllInvalid">全部无效</button>
                    </div>
                    <div class="show" id="page-update">

                    </div>
                </div>
            </div>
        </div>
        <div class="modal-close">
            <img src="img/close.png" alt="">
        </div>
    </div>
</div>


<div class="modal scenario-modal">

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
                <div class="top">
                    <span class="title">定时设置</span>
                    <div class="inputGroup">
                        <input class="presetName" type="text" placeholder="输入场景名称"/>
                        <button class="submit"></button>
                    </div>
                </div>
                <div class="ulList">
                    <ul id="presets">
                    </ul>
                    <div class="paginationbar">
                        <ul class="pagination" id="pagination4"></ul>
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

            <a href="javascript:selectTimerDate('\${item.id}');" style="color: #53FDFF;">更新</a>

        </td>
    </tr>
    {@/each}
</script>
<script id="tpl-YearCronlist" type="text/template">
    {@each data.m as item, index}
    <li id="s\${+index+1}" class="\${index<data.d?'':'hidden'}">
        <div class="day">
            \${+index+1}
        </div>
        <div class="seled">
            <div class="checkboxGroup">
                <input id="a\${index}" name="m\${index}" type="radio" value="1" \${item==1?'checked':''}/>
                <label class="check1" for="a\${index}">平日模式</label>
            </div>
            <div class="checkboxGroup">
                <input id="b\${index}" name="m\${index}" type="radio" value="2" \${item==2?'checked':''}/>
                <label class="check2" for="b\${index}">节日模式</label>
            </div>
            <div class="checkboxGroup">
                <input id="g\${index}" name='m\${index}' type="radio" value="3" \${item==3?'checked':''}/>
                <label class="check3" for="g\${index}">重大节日模式</label>
            </div>
        </div>
    </li>
    {@/each}
</script>

<script id="tpl-monthslist" type="text/template">
    <ul class="list monthData">
        {@each data.monthList as item, index}
        <li>
            <span> 1月</span>
            <div class="wrap">
                <input type="checkbox" id="c1" name="checkMonth" value="\${item.m1}" \${item.m1==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c1"></label>
            </div>
        </li>
        <li>
            <span> 2月</span>
            <div class="wrap">
                <input type="checkbox" id="c2" name="checkMonth" value="\${item.m2}" \${item.m2==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c2"></label>
            </div>
        </li>
        <li>
            <span> 3月</span>
            <div class="wrap">
                <input type="checkbox" id="c3" name="checkMonth" value="\${item.m3}" \${item.m3==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c3"></label>
            </div>
        </li>
        <li>
            <span> 4月</span>
            <div class="wrap">
                <input type="checkbox" id="c4" name="checkMonth" value="\${item.m4}" \${item.m4==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c4"></label>
            </div>
        </li>
        <li>
            <span> 5月</span>
            <div class="wrap">
                <input type="checkbox" id="c5" name="checkMonth" value="\${item.m5}" \${item.m5==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c5"></label>
            </div>
        </li>
        <li>
            <span> 6月</span>
            <div class="wrap">
                <input type="checkbox" id="c6" name="checkMonth" value="\${item.m6}" \${item.m6==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c6"></label>
            </div>
        </li>
        <li>
            <span> 7月</span>
            <div class="wrap">
                <input type="checkbox" id="c7" name="checkMonth" value="\${item.m7}" \${item.m7==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c7"></label>
            </div>
        </li>
        <li>
            <span>8月</span>
            <div class="wrap">
                <input type="checkbox" id="c8" name="checkMonth" value="\${item.m8}" \${item.m8==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c8"></label>
            </div>
        </li>
        <li>
            <span> 9月</span>
            <div class="wrap">
                <input type="checkbox" id="c9" name="checkMonth" value="\${item.m9}" \${item.m9==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c9"></label>
            </div>
        </li>
        <li>
            <span>10月</span>
            <div class="wrap">
                <input type="checkbox" id="c10" name="checkMonth" value="\${item.m10}" \${item.m10==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c10"></label>
            </div>
        </li>
        <li>
            <span> 11月</span>
            <div class="wrap">
                <input type="checkbox" id="c11" name="checkMonth" value="\${item.m11}" \${item.m11==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c11"></label>
            </div>
        </li>
        <li>
            <span>12月</span>
            <div class="wrap">
                <input type="checkbox" id="c12" name="checkMonth" value="\${item.m12}" \${item.m12==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="c12"></label>
            </div>
        </li>
        {@/each}
    </ul>
    <ul class="list weekData" style="display: none;">
        {@each data.weekList as item, index}
        <li>
            <span>周一</span>
            <div class="wrap">
                <input type="checkbox" id="e1" name="checkWeek" value="\${item.w1}" \${item.w1==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="e1"></label>
            </div>
        </li>
        <li>
            <span>周二</span>
            <div class="wrap">
                <input type="checkbox" id="e2" name="checkWeek" value="\${item.w2}" \${item.w2==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="e2"></label>
            </div>
        </li>
        <li>
            <span>周三</span>
            <div class="wrap">
                <input type="checkbox" id="e3" name="checkWeek" value="\${item.w3}" \${item.w3==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="e3"></label>
            </div>
        </li>
        <li>
            <span>周四</span>
            <div class="wrap">
                <input type="checkbox" id="e4" name="checkWeek" value="\${item.w4}" \${item.w4==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="e4"></label>
            </div>
        </li>
        <li>
            <span>周五</span>
            <div class="wrap">
                <input type="checkbox" id="e5" name="checkWeek" value="\${item.w5}" \${item.w5==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="e5"></label>
            </div>
        </li>
        <li>
            <span>周六</span>
            <div class="wrap">
                <input type="checkbox" id="e6" name="checkWeek" value="\${item.w6}" \${item.w6==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="e6"></label>
            </div>
        </li>
        <li>
            <span>周日</span>
            <div class="wrap">
                <input type="checkbox" id="e7" name="checkWeek" value="\${item.w7}" \${item.w7==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="e7"></label>
            </div>
        </li>
        {@/each}
    </ul>
    <ul class="list dayData" style="display: none;">
        {@each data.dayList as item, index}
        <li>
            <span>1日</span>
            <div class="wrap">
                <input type="checkbox" id="f1" name="checkDay" value="\${item.d1}" \${item.d1==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f1"></label>
            </div>
        </li>
        <li>
            <span>2日</span>
            <div class="wrap">
                <input type="checkbox" id="f2" name="checkDay" value="\${item.d2}" \${item.d2==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f2"></label>
            </div>
        </li>
        <li>
            <span>3日</span>
            <div class="wrap">
                <input type="checkbox" id="f3" name="checkDay" value="\${item.d3}" \${item.d3==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f3"></label>
            </div>
        </li>
        <li>
            <span>4日</span>
            <div class="wrap">
                <input type="checkbox" id="f4" name="checkDay" value="\${item.d4}" \${item.d4==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f4"></label>
            </div>
        </li>
        <li>
            <span>5日</span>
            <div class="wrap">
                <input type="checkbox" id="f5" name="checkDay" value="\${item.d5}" \${item.d5==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f5"></label>
            </div>
        </li>
        <li>
            <span>6日</span>
            <div class="wrap">
                <input type="checkbox" id="f6" name="checkDay" value="\${item.d6}" \${item.d6==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f6"></label>
            </div>
        </li>
        <li>
            <span>7日</span>
            <div class="wrap">
                <input type="checkbox" id="f7" name="checkDay" value="\${item.d7}" \${item.d7==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f7"></label>
            </div>
        </li>
        <li>
            <span>8日</span>
            <div class="wrap">
                <input type="checkbox" id="f8" name="checkDay" value="\${item.d8}" \${item.d8==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f8"></label>
            </div>
        </li>
        <li>
            <span>9日</span>
            <div class="wrap">
                <input type="checkbox" id="f9" name="checkDay" value="\${item.d9}" \${item.d9==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f9"></label>
            </div>
        </li>
        <li>
            <span>10日</span>
            <div class="wrap">
                <input type="checkbox" id="f10" name="checkDay" value="\${item.d10}" \${item.d10==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f10"></label>
            </div>
        </li>
        <li>
            <span>11日</span>
            <div class="wrap">
                <input type="checkbox" id="f11" name="checkDay" value="\${item.d11}" \${item.d11==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f11"></label>
            </div>
        </li>
        <li>
            <span>12日</span>
            <div class="wrap">
                <input type="checkbox" id="f12" name="checkDay" value="\${item.d12}" \${item.d12==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f12"></label>
            </div>
        </li>
        <li>
            <span>13日</span>
            <div class="wrap">
                <input type="checkbox" id="f13" name="checkDay" value="\${item.d13}" \${item.d13==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f13"></label>
            </div>
        </li>
        <li>
            <span>14日</span>
            <div class="wrap">
                <input type="checkbox" id="f14" name="checkDay" value="\${item.d14}" \${item.d14==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f14"></label>
            </div>
        </li>
        <li>
            <span>15日</span>
            <div class="wrap">
                <input type="checkbox" id="f15" name="checkDay" value="\${item.d15}" \${item.d15==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f15"></label>
            </div>
        </li>
        <li>
            <span>16日</span>
            <div class="wrap">
                <input type="checkbox" id="f16" name="checkDay" value="\${item.d16}" \${item.d16==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f16"></label>
            </div>
        </li>
        <li>
            <span>17日</span>
            <div class="wrap">
                <input type="checkbox" id="f17" name="checkDay" value="\${item.d17}" \${item.d17==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f17"></label>
            </div>
        </li>
        <li>
            <span>18日</span>
            <div class="wrap">
                <input type="checkbox" id="f18" name="checkDay" value="\${item.d18}" \${item.d18==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f18"></label>
            </div>
        </li>
        <li>
            <span>19日</span>
            <div class="wrap">
                <input type="checkbox" id="f19" name="checkDay" value="\${item.d19}" \${item.d19==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f19"></label>
            </div>
        </li>
        <li>
            <span>20日</span>
            <div class="wrap">
                <input type="checkbox" id="f20" name="checkDay" value="\${item.d20}" \${item.d20==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f20"></label>
            </div>
        </li>
        <li>
            <span>21日</span>
            <div class="wrap">
                <input type="checkbox" id="f21" name="checkDay" value="\${item.d21}" \${item.d21==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f21"></label>
            </div>
        </li>
        <li>
            <span>22日</span>
            <div class="wrap">
                <input type="checkbox" id="f22" name="checkDay" value="\${item.d22}" \${item.d22==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f22"></label>
            </div>
        </li>
        <li>
            <span>23日</span>
            <div class="wrap">
                <input type="checkbox" id="f23" name="checkDay" value="\${item.d23}" \${item.d23==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f23"></label>
            </div>
        </li>
        <li>
            <span>24日</span>
            <div class="wrap">
                <input type="checkbox" id="f24" name="checkDay" value="\${item.d24}" \${item.d24==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f24"></label>
            </div>
        </li>
        <li>
            <span>25日</span>
            <div class="wrap">
                <input type="checkbox" id="f25" name="checkDay" value="\${item.d25}" \${item.d25==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f25"></label>
            </div>
        </li>
        <li>
            <span>26日</span>
            <div class="wrap">
                <input type="checkbox" id="f26" name="checkDay" value="\${item.d26}" \${item.d26==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f26"></label>
            </div>
        </li>
        <li>
            <span>27日</span>
            <div class="wrap">
                <input type="checkbox" id="f27" name="checkDay" value="\${item.d27}" \${item.d27==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f27"></label>
            </div>
        </li>
        <li>
            <span>28日</span>
            <div class="wrap">
                <input type="checkbox" id="f28" name="checkDay" value="\${item.d28}" \${item.d28==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f28"></label>
            </div>
        </li>
        <li>
            <span>29日</span>
            <div class="wrap">
                <input type="checkbox" id="f29" name="checkDay" value="\${item.d29}" \${item.d29==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f29"></label>
            </div>
        </li>
        <li>
            <span>30日</span>
            <div class="wrap">
                <input type="checkbox" id="f30" name="checkDay" value="\${item.d30}" \${item.d30==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f30"></label>
            </div>
        </li>
        <li>
            <span>31日</span>
            <div class="wrap">
                <input type="checkbox" id="f31" name="checkDay" value="\${item.d31}" \${item.d31==1?'checked':''}
                       onclick="this.value=(this.value==0)?1:0"/>
                <label class="slider-v3" for="f31"></label>
            </div>
        </li>
        {@/each}
    </ul>
</script>

<%--Task--%>
<script id="tpl-taskList" type="text/template">
    {@each data as item, index}
    <li>
        <div class="top">
            \${item.taskNo}-\${item.taskName}
        </div>
        <div class="bottom">
            {@if item.exeState == 'ok'}
            <span class="status success">\${parseExeState(item.exeState)}</span>
            {@else}
            <span class="status error">\${parseExeState(item.exeState)}</span>
            {@/if}
            <button name="btnExecute" onclick="executeTask('\${item.areaNodeID}', '\${item.taskNo}')"> 执行</button>
        </div>
    </li>
    {@/each}
</script>
<%--scenarioList--%>
<script id="tpl-scenarioList" type="text/template">
    {@each data as item, index}
    <li>
        <div class="top">
            \${item.areaNodeID}-\${item.areaName}
        </div>
        <div class="bottom">
            <button data-areano="\${item.areaNo}" data-areanodeid="\${item.areaNodeID}">
                选择
            </button>
        </div>
    </li>
    {@/each}
</script>
<script id="tpl-presets" type="text/template">
    {@each data as item, index}
    <li data-presetNo="\${item.presetNo}">\${item.presetName}</li>
    {@/each}
</script>
<%--<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}"
        type="text/javascript"></script>--%>
<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}"
        type="text/javascript"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=V1.0.3"
        type="text/javascript"></script>
<%--easyui--%>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
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

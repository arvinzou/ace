<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="/cu/www/common/js/layui/css/layui.css?v=<%=System.currentTimeMillis() %>"/>
    <link rel="stylesheet" type="text/css" href="css/me.css?v=<%=System.currentTimeMillis() %>"/>
    <link rel="stylesheet" type="text/css" href="/cu/www/common/css/nav.css?v=<%=System.currentTimeMillis() %>"/>
</head>
<body>
<div class="contactbox" onclick="aboutus();"><img src="img/contact.png" style="width: 100%; height: 100%;"/></div>
<div class="mebox">
    <div class="bg">
        <div class="userinfo" id="userInfo">

        </div>
        <div class="statics">
            <div class="statics01">
                <p id="amount"></p>
                <p>累计捐款(元)</p>
            </div>
            <div class="statics01">
                <p id="times"></p>
                <p>爱心次数</p>
            </div>
            <div class="statics01">
                <p id="totalPoint"></p>
                <p>爱心积分</p>
            </div>
            <div style="clear: both;"></div>
        </div>
    </div>
    <div class="me_nav_box">
        <div class="navigation">
            <div class="news-title">
                <ul class="news-module nav_box_ul clear">
                    <li class="active" onclick="hoverli('nav_record');">爱心记录</li>
                    <li onclick="hoverli('nav_help');">我的求助</li>
                    <li onclick="hoverli('nav_score');">爱心积分</li>
                </ul>
                <div class="news-slider"></div>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="dis" id="nav_record">

        </div>
        <div class="undis" id="nav_help">
            <div class="help_box" id="projectList">

            </div>
            <div class="raise_btn">
                <button onclick="apply();">发起筹款</button>
            </div>
        </div>
        <div class="undis" id="nav_score">
            <div class="scores_box" id='scoreBox'>

            </div>
        </div>
    </div>
</div>

<script id="userInfo-tpl" type="text/template">
    <div class="userimg">
        <img src="\${data.headimgurl}"/>
    </div>
    <div class="username">
        <span>\${data.nickname}</span>
    </div>
    <div style="clear: both;"></div>
</script>

<script id="project-tpl" type="text/template">
    {@each data as item, index}
    <div class="help_list"  onclick="showProgress('\${item.id}');">
        <div class="help_list_left">
            {@if item.coverUrl == '' || item.coverUrl == null || item.coverUrl == undefined}
            <img src="img/default.png" style="width: 100%;height: 100%;"/>
            {@else}
            <img src="\${item.coverUrl}" style="width: 100%;height: 100%;"/>
            {@/if}
        </div>
        <div class="help_list_right">
            <p class="help_title">\${item.title}</p>
            <p class="help_remark">\$\${item.description}</p>
            <p class="help_info">
                <span>已筹集
                    {@if item.status == '2'}
                    <span  class="money">\${item.collectAmount}</span>
                    {@else if item.status != '2'}
                    <span  class="money">0</span>
                    {@/if}
                    元</span>


                {@if item.status == '2'}
                <span style="padding-left: 1rem;">剩余<span class="money">\${item.balanceDays}</span>天</span>
                {@else if item.status == '1'}
                <span style="padding-left: 1rem;">待审核</span>
                {@else if item.status == '3'}
                <span style="padding-left: 1rem;">驳回</span>
                {@/if}
            </p>
        </div>
    </div>
    {@/each}
</script>

<script id="record-tpl" type="text/template">
    <ul class="layui-timeline">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"><img src="img/love_icon.png"
                                                           style="width:20px; height: 15px;"/></i>
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title">
                    <div class="love_title">
                        <div class="love_cmt_01">共献爱心 \${data.total} 次，累计金额<span id="totalAmount"></span>元</div>
                    </div>
                </div>
            </div>
        </li>
        {@each data.rows as item, index}
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title">
                    <div class="love_cmt">
                        <div class="content_01">\${item.projectName}</div>
                        <div class="content_02"><span>捐款<span class="money">\${item.donateAmount}</span>元</span><span
                                class="dotime">\${item.donateDate}</span></div>
                    </div>
                </div>
            </div>
        </li>
        {@/each}
    </ul>
</script>

<script id="score-tpl" type="text/template">
    {@each data.rows as item, index}
    <div class="score_list">
        <div class="score_list_left">
            <p class="score_name">为\${item.projectName}捐款</p>
            <p class="score_time">\${item.donateDate}</p>
        </div>
        <div class="score_list_right">
            {@if item.points != '' && item.points != undefined && item.points != null}
            <p>+ \${item.points}</p>
            {@else}
            <p>+0</p>
            {@/if}
        </div>
    </div>
    {@/each}
</script>
<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/nav.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="js/me.js?v=<%=System.currentTimeMillis() %>"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <title>我要捐款</title>
    <link rel="stylesheet" type="text/css" href="/cu/www/common/css/nav.css?v=<%=System.currentTimeMillis() %>"/>
    <link rel="stylesheet" type="text/css" href="/cu/www/common/js/layui/css/layui.css?v=<%=System.currentTimeMillis() %>"/>
    <link rel="stylesheet" type="text/css" href="css/donation.css?v=<%=System.currentTimeMillis() %>"/>

</head>

<body>
<div class="projectbox">
    <div class="project_item" id="projectInfo">

    </div>
    <div class="project_nav">
        <!--导航栏-->
        <div class="navigation">
            <div class="news-title">
                <ul class="news-module project_nav_ul clear">
                    <li class="active" onclick="hoverli('nav_info01');">项目详情</li>
                    <li onclick="hoverli('nav_info02');">使用记录</li>
                    <li onclick="hoverli('nav_info03');">捐赠列表</li>
                </ul>
                <div class="news-slider"></div>
            </div>
        </div>
    </div>
    <div class="nav_info">
        <div id="nav_info01" class="nav_info_content dis">
            <p id="projectDetail"></p>
        </div>

        <div id="nav_info02" class="nav_info_content undis">
            <div class="project_record" id="record">

            </div>
        </div>

        <div id="nav_info03" class="nav_info_content undis">
            <div class="donation_list" id="donateList">

            </div>
        </div>
    </div>
</div>

<!--我要捐赠按钮-->
<div class="donation_btn" onclick="donate();">
    <img src="img/donation_btn.png" width="100%" height="100%"/>
</div>

<script id="detail-tpl" type="text/template">
    \$\${data.description}
</script>
<script id="project-tpl" type="text/template">
    <div class="project_item_image" style="height: 6rem;">
        {@if data.coverUrl != '' && data.coverUrl != undefined && data.coverUrl != null}
        <img src="\${data.coverUrl}" width="100%" height="100%"/>
        {@else}
        <img src="img/project_default.png" width="100%" height="100%"/>
        {@/if}
    </div>
    <div class="project_item_name">
        <p class="name_01">\${data.projectName}</p>
        {@if data.type != 2}
        <p style="padding-bottom: 0.5rem;" class="name_02">\${data.title}</p>
        {@/if}
    </div>
    <div class="project_item_raise" style="display: none;">
        <div class="raise_01">
            {@if data.type != 2}
            <p class="raise_money">\${data.collectAmount}</p>
            <p class="raise_price">已募集(元)</p>
            {@else}
            <p class="raise_money">\${data.targetAmount}</p>
            <p class="raise_price">目标金额(元)</p>
            {@/if}
        </div>
        <div class="raise_01">
            {@if data.type != 2}
            <p class="raise_money">\${data.payoutAmount}</p>
            <p class="raise_price">已支出(元)</p>
            {@else}
            <p class="raise_money">\${data.collectAmount}</p>
            <p class="raise_price">已筹集(元)</p>
            {@/if}
        </div>
        <div class="raise_01">
            {@if data.type != 2}
            <p class="raise_money">\${data.balanceAmount}</p>
            <p class="raise_price">结余(元)</p>
            {@else}
            <p class="raise_money"> \${data.endDate}</p>
            <p class="raise_price">截止时间</p>
            {@/if}
        </div>
        <div style="clear: both;"></div>
    </div>
</script>

<script id="record-tpl" type="text/template">
    <ul class="layui-timeline">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"><img src="img/love_icon.png" style="width:20px; height: 15px;"/></i>
            <div class="layui-timeline-content layui-text">
                <div class="love_title">
                    <div class="love_cmt_01">已捐赠于 \${data.total} 个项目</div>
                </div>
            </div>
        </li>
        {@each data.rows as item, index}
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title">
                    <div class="project_record_title">
                        <div class="project_record_title01">\${item.useToProjectName}</div>
                        <div class="project_record_title02">使用资金：<span class="donation_money">\${item.useAmount}</span>
                        </div>
                    </div>
                    <div class="project_record_info troggle">
                        \$\${item.useToProjectDesc}
                    </div>
                    <div class="upordown"><p name="down" onclick="troggle(this);"><span
                            class="opt">展开</span><img src="img/down.png"
                                                      style="width: 0.3rem;height: 0.2rem;"></p></div>
                </div>
            </div>
        </li>
        {@/each}
    </ul>
</script>

<script id="donate-tpl" type="text/template">
    {@each data as item, index}
    <div class="donation_item">
        <div class="donation_user_image">
            <img src="\${item.headimgurl}"/>
        </div>
        <div class="donation_user_info">
            <div class="donation_users"><span class="username">\${item.nickname}</span>捐赠了<span
                    class="donation_money">\${item.donateAmount}</span>元
            </div>
            <div class="donation_msg">\${item.remark}</div>
            <div class="donation_time">\${item.donateDate}</div>
        </div>
        <div style="clear: both;"></div>
    </div>
    {@/each}
</script>
<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/nav.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="js/donation.js?v=<%=System.currentTimeMillis() %>"></script>
</body>

</html>
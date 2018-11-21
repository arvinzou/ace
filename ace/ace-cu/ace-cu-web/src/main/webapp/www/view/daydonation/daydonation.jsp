<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
        <meta http-equiv="Pragma" content="no-cache"/>
		<title>慈善一日捐</title>
        <link rel="stylesheet" type="text/css" href="/cu/www/common/js/layer.mobile-v2.0/layer_mobile/need/layer.css?v=<%=System.currentTimeMillis() %>"/>
        <link rel="stylesheet" type="text/css" href="/cu/www/common/js/layui/css/layui.css?v=<%=System.currentTimeMillis() %>"/>
        <link rel="stylesheet" type="text/css" href="/cu/www/common/css/bootstrap.min.css?v=<%=System.currentTimeMillis() %>">
		<link rel="stylesheet" type="text/css" href="css/daydonation.css?v=<%=System.currentTimeMillis() %>" />

	</head>

	<body>
		<div class="dua_box">
			<div class="dua_banner">
				<!--<img src="img/banner.png" />-->
				<div style="width: 100%;height: 100%;" id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- 轮播（Carousel）指标 -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>
					<!-- 轮播（Carousel）项目 -->
					<div class="carousel-inner">
						<div class="item active">
							<img class="slideImg" src="img/banner.png" alt="First slide">
						</div>
						<div class="item">
							<img class="slideImg" src="img/banner1.png" alt="Second slide">
						</div>
						<div class="item">
							<img class="slideImg" src="img/banner2.png" alt="Third slide">
						</div>
					</div>
				</div>
			</div>
			<div class="banner_words">
				<div class="words">
					<p class="title_01">慈善一日捐</p>
					<!--<p class="title_small">已累计筹款 <span class="title_01" ng-bind="projectInfo.collectAmount"></span>元</p>-->
				</div>
			</div>
			<div class="content_01">
				<div class="statistics" id="statistics">

				</div>
				<div class="donate_list marquee" id="donatiteList">
					<!--捐赠列表渲染区域-->
				</div>
			</div>
			<div class="content_02">
				<div class="content_title" id="totalAmount">
					<!--一日捐总数统计-->
				</div>
				<div class="donate_rank">
					<div class="donate_rank_title">
						<span>
							<img src="img/rank.png" style="width: 0.3rem;height: 0.4rem;"/>
							<span>爱心贡献榜</span>
						</span>
					</div>
					<div class="donate_rank_list" id="rankList">
						<!--爱心贡献榜单渲染区域-->
					</div>
				</div>
			</div>
			<div class="content_03">
				<div class="project_title">项目详情</div>
				<div class="project_content troggle" id="desscription">
                    <!--项目详情渲染区域-->
				</div>
				<div class="upordown"><p name="down" onclick="troggle(this, '.project_content');"><span class="opt">展开</span><img src="img/down.png" style="width: 0.3rem;height: 0.2rem;"></p></div>
			</div>

			<div class="content_03">
				<div class="project_title">
					<span class="project_title_left">使用记录</span>
					<span class="project_title_right"><a href="#" onclick="showMore();">查看全部</a></span>
				</div>
				<div class="project_content" id="useRecord">
					<!--使用记录渲染区域-->
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="footer_content">
				<div class="footer_left">
					<p style="padding-top: 0.3rem;">
						<img src="img/share.png" style="width: 0.8rem;height: 0.6rem;" onclick="transmit();"/>
					</p>
					<p>传递爱心</p>
				</div>
				<div class="footer_right">
					<img src="img/donate.png" onclick="donate();"/>
				</div>
			</div>
		</div>

        <!--项目数据统计-->
        <script id="project-tpl" type="text/template">
            <div class="statistics_left">
                <div style="width: 100%;height: 1rem;margin: 0 auto;">
                    <div class="statics01">
                        <span><img src="img/statics02.png" style="width: 0.5rem;height: 0.4rem;margin-bottom: 0.15rem;"/></span>
                        <span class="money_larger">\${data.todayDonateAmount}</span>
                        <div style="clear: both;"></div>
                    </div>
                </div>
                <div class="statics02">今日捐款数(元)</div>
            </div>
            <div class="statistics_right">
                <div  style="width: 100%;height: 1rem;margin: 0 auto;">
                    <div class="statics01">
                        <span><img src="img/statics01.png" style="width: 0.5rem;height: 0.5rem;margin-bottom: 0.15rem;"/></span>
                        {@if data.todayDonateCount != undefined && data.todayDonateCount != null && data.todayDonateCount != ''}
                        <span class="times_larger">\${data.todayDonateCount}</span>
                        {@else}
                        <span class="times_larger">0</span>
                        {@/if}
                        <div style="clear: both;"></div>
                    </div>
                </div>
                <div class="statics02">本期累计捐款数(元)</div>
            </div>
            <div style="clear: both;"></div>
        </script>

        <!--项目详情-->
        <script id="des-tpl" type="text/template">
            <div>\$\${data.description}</div>
        </script>

        <!--使用记录渲染模板-->
        <script id="record-tpl" type="text/template">
            <ul class="layui-timeline">
                <li class="layui-timeline-item"  style="padding-bottom: 0.5rem !important;">
                    <i class="layui-icon layui-timeline-axis"><img src="img/love_icon.png" style="width:20px; height: 15px;"/></i>
                    <div class="layui-timeline-content layui-text">
                        <div class="layui-timeline-title">
                            <div class="love_title">
                                <div class="love_cmt_01">善款已用于 \${data.total} 个项目</div>
                            </div>
                        </div>
                    </div>
                </li>
                {@each data.rows as record, index}
                <li class="layui-timeline-item">
                    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                    <div class="layui-timeline-content layui-text">
                        <div class="layui-timeline-title">
                            <div class="project_record_title">
                                <div class="project_record_title01">\${record.useToProjectName}</div>
                                <div class="project_record_title02">使用资金：<span class="money_default">\${record.useAmount}</span></div>
                            </div>
                            <div class="project_record_info troggle">
                                \$\${record.useToProjectDesc}
                            </div>
                            <div class="upordown"><p name="down" onclick="troggle(this,'.project_record_info');"><span class="opt">展开</span><img src="img/down.png" style="width: 0.3rem;height: 0.2rem;"></p></div>
                        </div>
                    </div>
                </li>
                {@/each}
            </ul>
        </script>
        <!--捐赠列表渲染模板-->
        <script id="doante-tpl" type="text/template">
            <ul style="height: 2.4rem;background: #FBDAD7;">
                <marquee scrollamount="2" direction="up" behaviour="scroll" style="height: 2.4rem;">
                    {@each data as dua, index}
                    <li>
                        <span class="list_left">\${dua.nickname} &nbsp; 捐赠<span class="money_default">\${dua.donateAmount}</span>元</span>
                        <span class="list_right">\${(dua.donateDate).substring(10,dua.donateDate.length+1)}</span>
                    </li>
                    {@/each}
                </marquee>
            </ul>
        </script>

        <!--捐赠排行渲染模板-->
        <script id="rank-tpl" type="text/template">
            <ul style="width: 5.2rem;">
                {@if data[0] != '' && data[0] != null && data[0] != undefined}
                <li class="topone">
                    <img src="\${data[0].headimgurl}"/>
                </li>
                {@/if}
                {@if data[1] != '' && data[1] != null && data[1] != undefined}
                <li class="toptwo">
                    <img src="\${data[1].headimgurl}" />
                </li>
                {@/if}
                {@if data[2] != '' && data[2] != null && data[2] != undefined}
                <li class="topthree">
                    <img src="\${data[2].headimgurl}" />
                </li>
                {@/if}
                {@if data[3] != '' && data[3] != null && data[3] != undefined}
                <li class="topfour">
                    <img src="\${data[3].headimgurl}" />
                </li>
                {@/if}
                {@if data[0] != '' && data[0] != null && data[0] != undefined}
                <li>
                    <a href="#" onclick="donateRank();" style="color: #E2E2E2;">...</a>
                </li>
                {@/if}

            </ul>
        </script>

        <!--一日捐总数统计渲染模板-->
        <script id="total-tpl" type="text/template">
            <span>\${data}人参与一日捐</span>
        </script>

        <!--分享页面渲染-->
       <!-- <script id="share-tpl" type="text/template">
            <div  class="share_box">
                <div class="head_img_box">
                    <img src="\${data.headimgurl}" />
                </div>
                <div class="dua_user_info">
                    <p>\${data.nickname}</p>
                    <p>感谢你的第 \${data.donateCount} 次捐赠，你已经</p>
                </div>
                <div class="dua_statics">
				<span class="dua_statics01">
					<p>捐赠天数</p>
					<p><span class="dua_number">\${data.donateDays}</span>天</p>
				</span>
                    <span class="dua_statics01">
					<p>捐赠金额</p>
					<p><span class="dua_number">\${data.totalDonateAmount}</span>元</p>
				</span>
                    <span style="clear: both;"></span>
                </div>
                <div class="dua_footer">
                    <div class="dua_left">
                        <img src="img/weixin.png" />
                    </div>
                    <div class="dua_right">
                        <p>长按识别二维码</p>
                        <p>关注常德慈善</p>
                    </div>
                    <div style="clear: both;"></div>
                </div>
            </div>
        </script>-->
        <script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
        <script type="text/javascript" src="/cu/www/common/js/bootstrap.min.js?v=<%=System.currentTimeMillis() %>"></script>
        <script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis() %>"></script>
        <script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis() %>"></script>
        <script type="text/javascript" src="/cu/www/common/js/layer.mobile-v2.0/layer_mobile/layer.js?v=<%=System.currentTimeMillis() %>"></script>
		<script type="text/javascript" src="js/daydonation.js?v=<%=System.currentTimeMillis() %>"></script>
	</body>

<!--分享页面-->
	<!--<div id="share_box" style="display: none;">

	</div>-->
</html>
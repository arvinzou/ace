<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>直播</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="data-spm" content="zy-spot-web.v3">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/www/common/plugins/prismplayer/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/www/common/plugins/photoswipe-4.1.1/photoswipe.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/www/common/plugins//photoswipe-4.1.1/default-skin.css">
    <link rel="stylesheet" href="css/iconfont.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="css/mobase.css"/>
    <link rel="stylesheet" href="css/mimax.css"/>
    <script src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
    <script src="${pageContext.request.contextPath}/www/oauth2/cfg"></script>

</head>
<body>
<div class="fn-contain">
    <div id="j-liveinfo" class="xcy-liveinfo">
        <script type="text/template">
            <div class="view" id="j-video-wrap">

                {@if data.type == 1}

                {@if data.state == 1}
                <img src="\${data.cover}">
                {@else}
                <img src="\${data.cover}">
                {@/if}
                <div id="j-video-text" class="fn-hide" style="width:5rem;">

                    <div class="text" style="padding:.4rem"><span style="color:#d4d4d4;font-size:.3rem">即将播放：</span><p style="color:#fff;font-size:.35rem">\${data.topic}</p></div>
                </div>

                {@/if}

                {@if data.type == 2}


                {@if data.state == 1}
                <img src="\${data.cover}">

                {@else}
                        {@if data.state == 2}
                         <video src="\${data.playStreamUrl}" x-webkit-airplay="true" webkit-playsinline="true" playsinline="true" x5-video-player-type="h5" x5-video-player-fullscreen="true" controls="controls" style="width: 10rem; height: 5.62rem;"></video>
                        {@/if}
                        {@if data.state == 3}
                        <video src="\${data.mp4Url}" x-webkit-airplay="true" webkit-playsinline="true" playsinline="true" x5-video-player-type="h5" x5-video-player-fullscreen="true" controls="controls" style="width: 10rem; height: 5.62rem;"></video>
                        {@/if}



                {@/if}
                <div id="j-video-text" class="fn-hide" style="width:5rem;">

                    <div class="text" style="padding:.4rem"><span style="color:#d4d4d4;font-size:.3rem">即将播放：</span><p style="color:#fff;font-size:.35rem">\${data.topic}</p></div>
                </div>

                {@/if}

            </div>

            <div class="content">
                <div class="title">\${data.topic}</div>
                <div class="org">
                    <p id="j-orglogo" class="icon j-orgname-back" data-spm="org"
                       data-spm-click="category=item;action=orgclick"></p>
                    <div id="j-orgname" class="orgname j-orgname-back" data-spm="org"
                         data-spm-click="category=item;action=orgclick"></div>
                    <div id="j-city" class="other">
                        <!--
                        <div class="xc-share" id="j-share"><i></i></div>
                        -->
                        <div class="xc-liker" id="j-xc-liker">
                            <i><em class="zan zaning"></em></i>
                            {@if data.likeNums}
                            <span class="zan-num">\${data.likeNums}</span>
                            {@else}
                            <span class="zan-num">0</span>
                            {@/if}
                        </div>
                        <div id="j-city-right" class="fn-right">
                            {@if data.state == 1}
                            <div class="disabled">预告</div>
                            {@else if data.state == 3}
                            <div class="disabled">已结束</div>
                            {@else}
                            <em class="playx"></em>
                            <div class="playing">直播中</div>

                            {@/if}

                        </div>

                        <div class="fn-left">浏览<span id="visit">\${data.numOfPartake}</span></div>

                    </div>
                    <!--
                   <ul class="share-list none" id="j-tpList">
                       <li class="j-share-weibo"><img src="${pageContext.request.contextPath}/www/common/img/share_weibo.png"></li>
                       <li class="j-share-weixin"><img src="${pageContext.request.contextPath}/www/common/img/share_weixin.png"></li>
                       <li class="j-share-qq"><img src="${pageContext.request.contextPath}/www/common/img/share_qq.png"></li>
                   </ul>

                   <p class="bd-icon"></p>
                   <p class="bd">342个现场</p>
                   -->
                </div>
                {@if data.remark}
                <div id="j-desc" class="desc">
                    <em class="act fn-hide"></em>
                    <p><em>摘要：</em>\${data.remark}</p>
                </div>
                {@/if}
                {@if data.city}
                <div class="location"><i></i><span>\${data.city}</span></div>
                {@/if}
            </div>
            <div class="none" id="j-getDataState">\${data.state}</div>
        </script>
    </div>
    <div class="mask none" id="mask"></div>
    <div class="tab_menu">
        <ul>
            <li><a class="selected" href="#">图文直播</a></li>
            <li><a class="unselected" href="#">互动聊天</a></li>
            <li><a class="unselected" href="#">活动介绍</a></li>
        </ul>
    </div>
    <div class="tab_box">
        <div id="livesub" style="min-height:10rem">
            <div class="xcy-sort-place">
                <div class="xcy-sort">
                    <p id="j-sort-c">
                        <span id="startTime" class="startTime"></span>
                        <span class="sort-btn none" id="j-sort">倒序</span>


                    </p>
                </div>
            </div>
            <div id="j-report" class="xcy-report">
                <script type="text/template">
                    <ul>
                        {@each data as item}
                        <li data-id="\${item.reportId}">
                            <div class="wrap">
                                <div class="headpic">
                                    <img src="${pageContext.request.contextPath}/www/common/img/live_ing.gif" style="width:.70rem">
                                    \${item.createDate}
                                </div>
                                <div class="reporter fn-clear">

                                    <div style="float:left;color:#999999">
                                        {@if item.rpt.headimgurl}
                                        <img src="\${item.rpt.headimgurl}" class="headimgp">
                                    {@else}
                                        <img src="${pageContext.request.contextPath}/www/common/img/head_default.png" class="headimg">
                                    {@/if}

                                          \${item.rpt.nickname}
                                    </div>

                                    <em{@if item.createDate == "刚刚"} class="ganggang"{@/if}></em>
                                </div>
                                {@if item.content}
                                <div class="content">$\${item.content|reportContent}</div>
                                {@/if}
                                {@if item.type == 1}
                                <div class="video" data-v-poster="\${item.thumbnail}" data-v-src="\${item.video}">
                                    <div class="videocontrols fn-hide">
                                        <div class="controls">
                                            <span class="c_play"></span>
                                            <span class="c_fullscren"></span>
                                            <!-- <div class="c_timeline fn-hide"> -->
                                            <div class="c_timeline fn-hide">
                                                <span class="playtime">00:00/00:00</span>
                                                <span class="starttime">00:00</span>
                                                <span class="endtime">00:00</span>
                                                <div class="timeline">
                                                    <p class="line overline"><em></em></p>
                                                    <p class="line"></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="playposter">
                                        <div class="v-mask fn-hide"></div>
                                        <span class="playbtn"></span>
                                        <div class="tip fn-hide">
                                            <p>获取不到视频画面，请刷新重试</p>
                                        </div>
                                        {@if item.thumbnail}
                                        <img src="\${item.thumbnail}">
                                        {@else}
                                        <img src="">
                                        {@/if}
                                    </div>
                                    <!-- <div class="v-loading-new fn-hide">
                                      <div class="v-logo"></div>
                                      <div class="v-line"><div class="v-line-s-box"><div></div></div></div>
                                      <p>即将播放...</p>
                                    </div> -->
                                    <div class="v-loading fn-hide"><i></i></div>
                                    <video playsinline="true" x-webkit-airplay="true" webkit-playsinline="true"
                                           x5-video-player-type="h5" x5-video-player-fullscreen="true"></video>
                                </div>
                                {@/if}
                                {@if item.type == 3}
                                <audio src="\${item.video}" controls="controls" class="audio"/>
                                {@/if}

                                {@if item.imageList && item.imageList.length > 0}
                                <div class="pictures" data-count="\${item.imageList.length}">
                                    <div class="j-photoswiper imglist\${item.imageList.length}">
                                        {@each item.imageList as imgitem, imgindex}
                                        <span class="img\${imgindex}" data-url="\${imgitem.url}"
                                              data-water="\${imgitem.watermarkConfig}"
                                              data-water-bus="\${item.watermarkConfig}"
                                              data-w="\${imgitem.w}" data-h="\${imgitem.h}">

                                        </span>
                                        {@/each}
                                    </div>
                                </div>
                                {@/if}
                                <div class="down fn-clear">
                                    <div class="city">

                                        {@if item.likeNum}
                                        <span class="zan-num"><img src="${pageContext.request.contextPath}/www/common/img/zan-cmt@2x.png"
                                                                   style="width:.35rem;height:.35rem"/> <span
                                                id="liker\${item.reportId}">\${item.likeNum}</span></span>
                                        {@else}
                                        <span class="zan-num"><img src="${pageContext.request.contextPath}/www/common/img/zan-cmt@2x.png"
                                                                   style="width:.35rem;height:.35rem"/> <span
                                                id="liker\${item.reportId}">0</span></span>
                                        {@/if}


                                    </div>
                                    <div class="act fn-hide"></div>
                                    <div class="actlayer">
                                        <p class="j-actzan" data-id="\${item.reportId}">
                                            <i class="zan-w"><em class="zan"></em></i><span>赞</span>
                                        </p>
                                        <p class="line"></p>
                                        <p class="j-actping" data-id="\${item.reportId}">
                                            <i class="ping-w"></i><span>评论</span>
                                        </p>
                                    </div>
                                </div>
                                <div id="j-remark-\${item.reportId}" class="remark ">
                                    <em class="arrow {@if item.comments.length==0} fn-hide {@/if}"></em>
                                    <div class="liker fn-hide" data-liker="\${item.liker}"></div>
                                    <div class="line fn-hide"></div>
                                    <div class="list {@if item.comments.length==0} fn-hide {@/if}"
                                         id="cmtlist\${item.reportId}">
                                        {@each item.comments as citem}
                                        <div class="live_msg">
                                            <div class="wxuser">
                                                <div class="wxuser-img"><img src="\${citem.headimgurl}"
                                                                             class="headimg"></div>

                                            </div>
                                            <div class="bubbleItem">
                                                <div class="wxuser-nickname">\${citem.nickname}</div>
                                                <div class="createTime">\${citem.createTime}</div>
                                                <div>\${citem.content}</div>
                                            </div>
                                        </div>
                                        {@/each}
                                    </div>
                                </div>
                        </li>
                        {@/each}
                    </ul>
                </script>
            </div>
            <div id="j-nonews" class="xcy-nonews fn-hide">
                稍等，新闻马上就到

            </div>
            <div id="j-listmore" class="xcy-more">
                <span>正在加载...</span>
            </div>
            <div class="xcy-totalcount"></div>

        </div>
        <div class="hide  chat" id="livemsg" style="min-height:10rem">
            <div class="scroll-content J_scrollContent" >
                <div class="chatContent" id="chatContent">

                </div>
            </div>
        </div>
        <div class="hide tab_content">
            <p class="content-text" id="content" style="min-height:10rem">

            </p>

        </div>
    </div>


    <!-- <div id="j-listmore" class="xcy-more">
      <div class="-v"></div>
      <div class="loding-con"></div>
    </div> -->

    <div class="xcy-copyright">
        <p>Copyright &copy; 2018 华彩</p>
        <p>由华彩团队提供技术支持</p>
    </div>
    <div id="add_note_icon" class="fn-hide"></div>

    <div id="j-fullview" class="xcy-fullview">
        <div class="wrap">
            <div id="j-viewpic" class="pic"></div>
            <div id="j-viewicon" class="icon"></div>
            <div class="download fn-hide">
                <label><input type="checkbox"> 原图</label>
                <a download target="_blank" href="#"></a>
            </div>
            <div class="close"></div>
        </div>
    </div>

    <div class="app-download-place"></div>

        <div class="app-download">
            <div id="j-remark" class="fn-hide">
                <!-- <div class="xcy-remark-place"></div> -->
                <div class="xcy-remark">
                    <div class="wrap">
                        <div class="bar">
                            <form id="j-remarkform">
                                <input type="hidden" name="rptId" value="0">
                                <input type="hidden" name="type" value="1">
                                <input type="text" placeholder="评论" name="content" autocomplete="off" maxlength="200">
                                <input type="submit" value="发送">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>

<div class="share-box" id="shareTpList">
    <div class="s-arrows"></div>
    <div class="s-text"></div>
    <div class="s-btn"></div>
</div>


<!-- Root element of PhotoSwipe. Must have class pswp. -->
<div id="j-pswp" class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="pswp__bg"></div>
    <div class="pswp__scroll-wrap">
        <div class="pswp__container">
            <div class="pswp__item"></div>
            <div class="pswp__item"></div>
            <div class="pswp__item"></div>
        </div>
        <div class="pswp__ui pswp__ui--hidden">
            <div class="pswp__top-bar">
                <div class="pswp__counter"></div>
                <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
                <button class="pswp__button pswp__button--share" title="Share"></button>
                <button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button>
                <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
                <!-- Preloader demo http://codepen.io/dimsemenov/pen/yyBWoR -->
                <!-- element will get class pswp__preloader--active when preloader is running -->
                <div class="pswp__preloader">
                    <div class="pswp__preloader__icn">
                        <div class="pswp__preloader__cut">
                            <div class="pswp__preloader__donut"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                <div class="pswp__share-tooltip"></div>
            </div>
            <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)"></button>
            <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)"></button>
            <div class="pswp__caption">
                <div class="pswp__caption__center"></div>
            </div>
        </div>
    </div>
</div>

<script id="tpl-msg" type="text/template">
    <div class="clearfix">
        <div class="message others">
            <div class="avatar" data-author-id="lj">
                <img src="\${header.wxuser.headimgurl}" class="avatar" alt="\${header.wxuser.nickname}"/>
            </div>
            <div class="content">
                <p class="author_name">\${header.wxuser.nickname} \${createTime}</p>
                <div class="bubble  bubble_default left">
                    <div class="bubble_cont">
                        <div class="plain">
                            <pre>\${content}</pre>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</script>


<script id="tpl-cmt" type="text/template">
    <div class="live_msg">
        <div class="wxuser">
            <div class="wxuser-img"><img src="\${header.wxuser.headimgurl}" class="headimg"></div>
        </div>
        <div class="bubbleItem">
            <div class="wxuser-nickname">\${header.wxuser.nickname}</div>
            <div class="createTime">\${createTime}</div>
            <div>\${content}</div>
        </div>
    </div>
</script>



<script src="${pageContext.request.contextPath}/www/common/js/jweixin-1.0.0.js"></script>
<script src="${pageContext.request.contextPath}/www/common/plugins/prismplayer/prism-min.js"></script>
<script src="${pageContext.request.contextPath}/www/common/js/zepto-1.2.0.min.js"></script>
<script src="${pageContext.request.contextPath}/www/common/plugins/jquery-1.11.0-min.js"></script>
<script src="${pageContext.request.contextPath}/www/common/js/juicer-min.js"></script>
<script src="${pageContext.request.contextPath}/www/common/js/sugar-h5.js"></script>
<script src="${pageContext.request.contextPath}/www/common/js/roll.js"></script>
<script src="${pageContext.request.contextPath}/www/common/plugins/photoswipe-4.1.1/photoswipe.js"></script>
<script src="${pageContext.request.contextPath}/www/common/plugins/photoswipe-4.1.1/photoswipe-ui-default.js"></script>
<script src="${pageContext.request.contextPath}/www/common/js/common.js"></script>

<script src="${pageContext.request.contextPath}/www/common/js/photoswipe.js"></script>
<script src="${pageContext.request.contextPath}/www/common/js/soshm.js"></script>
<script src="js/reconnecting-websocket.js"></script>
<script src="js/act.js"></script>
<script src="js/socket.js"></script>
<script src="js/leftTime.min.js"></script>


</body>
</html>
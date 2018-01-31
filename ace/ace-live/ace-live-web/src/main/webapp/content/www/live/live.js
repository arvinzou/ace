var isLogin=false;
var userDict={};
userDict.identityNo="9999";
userDict.identityName="HHHH";
userDict.identityType="youke";
function createH5Video(e, i, a, n) {
    if ($("#j-video-wrap").data("poster", e).data("src", i), "ios" == videoOsType) {
        if ($("#j-video-controls .c_timeline").addClass("fn-hide"), $("#j-video-controls").removeClass("fn-hide"), $("#j-video-controls .c_timeline").length) $("#j-video-controls .c_play").on(tap,
        function() {
            $(this).hasClass("c_pause") ? ($("#j-video-wrap video")[0].pause(), $(this).removeClass("c_pause"), $("#j-livevideo-tip").removeClass("fn-hide").find("img").hide()) : ($("#j-video-wrap video")[0].play(), $(this).addClass("c_pause"), $("#j-livevideo-tip").addClass("fn-hide"))
        });
        else {
            var o = !0;
            $("#j-video-controls .c_play").on(tap,
            function() {
                $(this).hasClass("c_pause") ? ($("#j-livevideo-main").addClass("fn-hide"), $("#j-video-wrap").html(""), $("#j-livevideo-tip").removeClass("fn-hide")) : (playH5Video(o), o = !1)
            })
        }
        $("#j-video-controls .c_fullscren").on(tap,
        function() {
            fullH5Video($("#j-video-wrap video")[0]);


        })
    } else $("#j-video-controls").remove();

}
function playH5Video(e) {
    function i() {

        if (r[0].duration && !isNaN(r[0].duration)) {
            var e = Math.ceil(r[0].duration);
            $("#j-video-controls .c_timeline").removeClass("fn-hide"),
            $("#j-video-controls .c_timeline .endtime").html(videoFn.formatVideoTime(e));
            var a = Math.ceil(r[0].currentTime);
            $("#j-video-controls .c_timeline .starttime").html(videoFn.formatVideoTime(a)),
            $("#j-video-controls .c_timeline .playtime").html(videoFn.formatVideoTime(a) + "/" + videoFn.formatVideoTime(e)),
            $("#j-video-controls .c_timeline .overline").width(a / e * 100 + "%")
        }
        s && !r[0].paused ? ($("#j-video-controls .c_play").addClass("c_pause"), setTimeout(i, 500)) : (s = !1, $("#j-video-controls .c_play").removeClass("c_pause"))
    }
    $("#j-livevideo-main").removeClass("fn-hide"),
    $("#j-livevideo-tip").addClass("fn-hide");
    var a = $("#j-video-controls"),
    n = !1,
    o = $("#j-video-wrap").data("poster"),
    t = $("#j-video-wrap").data("src");

    t.indexOf("m3u8") > -1 && (e = !0),
    e && ("ios" == videoOsType ? ($("#j-livevideo").hasClass("minvideobar") ? $("#j-v-loading").removeClass("fn-hide") : $("#j-v-loading-new").removeClass("fn-hide"), $("#j-video-wrap").html('<video poster="' + o + '" src="' + t + '" playsinline="true" x-webkit-airplay="true" webkit-playsinline="true" x5-video-player-type="h5" x5-video-player-fullscreen="true"></video>')) : $("#j-video-wrap").html('<video poster="' + o + '" playsinline="true" x-webkit-airplay="true" webkit-playsinline="true" x5-video-player-type="h5" x5-video-player-fullscreen="true" src="' + t + '" controls="controls"></video>'));
    var r = $("#j-video-wrap video");

    if (r[0].play(), $("#j-video-controls .c_play").addClass("c_pause"), $("#j-video-controls .c_timeline").length) {
        var s = !1;
        i()
    }
    r.off("waiting").on("waiting",
    function() {
        $("#j-v-loading").removeClass("fn-hide")
    }),
    r.off(tap).on(tap,
    function() {
        a.hasClass("fn-hide") ? a.removeClass("fn-hide") : ($("#j-livevideo-tip").removeClass("fn-hide").find("img").hide(), this.pause())
    });
    var d = setInterval(function() {
        n && (a.addClass("fn-hide"), clearInterval(d))
    },
    5e3);
    r.off("play").on("play",
    function() {
        n = !0,
        s || (s = !0, i(), clickSetSpeed($("#j-livevideo-main")))
    }),
    r.off("playing").on("playing",
    function() {
        $("#j-v-loading").addClass("fn-hide"),
        $("#j-v-loading-new").addClass("fn-hide"),
        $("#j-video-controls").removeClass("fn-hide")
    }),
    r.off("pause").on("pause",
    function() {
        $("#j-livevideo-tip").removeClass("fn-hide").find("img").hide(),
        n = !1,
        s || (s = !1)
    }),
    r.off("ended").on("ended",
    function(e) {
        $("#j-livevideo-tip").removeClass("fn-hide").find("img").show()
    }),
    $("#j-livevideo-main video").off("error").on("error",
    function(e) {
        $("#j-livevideo-main, #j-livevideo-tip .playbtn").addClass("fn-hide"),
        $("#j-livevideo-tip, #j-livevideo-tip .tip, #j-livevideo-tip .v-mask").removeClass("fn-hide"),
        $("#j-v-loading-new").addClass("fn-hide"),
        $("#j-v-loading").addClass("fn-hide")
    })
}
function clickSetSpeed(e) {
    var i = e,
    a = i.find(".overline"),
    n = i.find("video");
    i.on("click", ".timeline",
    function(e) {
        var o = i.find(".timeline").width(),
        t = e.offsetX,
        r = Math.ceil(n[0].duration),
        s = Math.round(t / o * 100) / 100;
        a.width(100 * s + "%"),
        n[0].currentTime = r * s
    })
}
function fullH5Video(e) {
    e.requestFullscreen ? e.requestFullscreen() : e.webkitRequestFullscreen ? e.webkitRequestFullscreen() : e.webkitEnterFullscreen ? e.webkitEnterFullscreen() : e.webkitDisplayingFullscreen && e.webkitDisplayingFullscreen()
}

function getInfo() {
    var e = {
        id: lvsCmd.urlParams.id,
        upNum: 1,
        identityType: webviewType,
        userId: lvsCmd.urlParams.id
    };
    lvsCmd.ajax(apiServer + "/www/live/getInfo.do", e,
    function(e, i) {
        function a() {
            $(window).scrollTop() <= 10 && (d = !1),
            d || ($(window).scrollTop() < c - l ? ($("#j-livevideo").removeClass("minvideobar"), $("#j-video-wrap").height(c)) : ($("#j-livevideo").addClass("minvideobar"), $("#j-video-wrap").height(l)))
        }
        function n() {
            var e = {
                companyId: lvsCmd.urlParams.companyId,
                id: lvsCmd.urlParams.id,
                type:"1"
            },
            i = !0;
            if(wxuser.role&&wxuser.role=='admin'){
                $("#adminrpt").removeClass("fn-hide");
                $("#adminrpt2").removeClass("fn-hide");
            };
            //alert(wxuser.role);
            $("#adminrpt2").on(tap,
                         function(){
                            console.log(i);
                            location.href="rpt.html?id="+lvsCmd.urlParams.id+"&title="+$(".title").html()+"&companyId="+lvsCmd.urlParams.companyId;
             }),
             $("#adminrpt").on(tap,
             function(){
                console.log(i);
                location.href="rpt.html?id="+lvsCmd.urlParams.id+"&title="+$(".title").html()+"&companyId="+lvsCmd.urlParams.companyId;
             }),
            $("#j-xc-liker").on(tap,
            function() {
                var a = $(this);
                a.addClass("xc-liker-ing"),
                setTimeout(function() {
                    a.removeClass("xc-liker-ing")
                },
                600),
                i && lvsCmd.ajax(apiServer + "/www/live/addLike.do", e,
                function(e, n) {
                    if ("0" == n.status) {
                        var o = a.find(".zan-num"),
                        t = o.html();
                        "w" == t.substr( - 1) || (t = parseInt(t), o.html(++t))
                    }
                    i = !1
                })
            })
        }
        if (e) {
            $("#content").html(i.data.content);
            if (void 0 == window.orientation && (i.ispc = !0), $("#j-liveinfo").html(liveinfoTpl.render(i)), $("#j-livevideo").length) {
                var o = "/live/content/www/img/ic_default_pic@2x.png";
                o=i.data.cover;
                var t = i.data.playStreamUrl;
                if (void 0 == window.orientation) {
                    $("#j-livevideo-main").addClass("prism-player").removeClass("fn-hide"),
                    $("#j-livevideo-tip").addClass("fn-hide");
                    var r = {
                        id: "j-video-wrap",
                        source: t,
                        cover: o,
                        autoplay: !1,
                        width: "100%",
                        height: $("#j-liveinfo .view").height() + "px"
                    };
                    t.indexOf(".m3u8") > -1 && (r.useFlashPrism = !0),
                    new prismplayer(r)
                } else {

                    createH5Video(o, t, i.data.playStreamUrlHd, i.data.playStreamUrlSd);
                    var s = !0;
                    if ($("#j-livevideo-tip").on(tap,
                    function() {
                        playH5Video(s),
                        s && (s = !1),
                        $("#j-video-wrap video").on("error",
                        function() {
                            s = !0
                        })
                    }), userAgent.indexOf("iphone") > -1 || userAgent.indexOf("ipad") > -1 || userAgent.indexOf("mac") > -1) {
                        var d = !1,
                        l = 3 * containWidth / 10,
                        c = 5.62 * containWidth / 10;
                        $(window).scroll(a),
                        a(),
                        $("#j-v-close").on(tap,
                        function() {
                            $("#j-livevideo").removeClass("minvideobar"),
                            $("#j-video-wrap").height(c),
                            $("#j-video-wrap video")[0] ? ($("#j-video-wrap video")[0].pause(), $("#j-livevideo-tip").removeClass("fn-hide").find("img").hide()) : $("#j-livevideo-tip").removeClass("fn-hide"),
                            d = !0
                        })
                    }
                };

                 $("#j-video-wrap").find("video").attr("x-webkit-airplay","true");
                 $("#j-video-wrap").find("video").attr("webkit-playsinline","true");
                 $("#j-video-wrap").find("video").attr("playsinline","true");

                 $("#j-video-wrap").find("video").attr("x5-video-player-type","h5");
                 $("#j-video-wrap").find("video").attr("x5-video-player-fullscreen","true");
                 $("#j-video-wrap").find("video").attr("controls","controls");

            }
            n(),
            joeFn.shareObj({
                clickObj: $("#j-share"),
                shareParams: {}
            });
            var p =  i.data.state;
            $("#j-sort").show();
            $("#startTime").html(i.data.startTime);
            if("3" == p){
               // $(".xcy-sort p").append('<span class="xc-state end"><i>已结束</i></span>');
            }
            if("2" == p){
                //$("#j-sort-c").append('<span class="xc-state ing"><i>直播中</i></span>');
            }
            /*$("#j-desc p").height() > $("#j-desc").height() && ($("#j-desc").addClass("descclamp"), $("#j-desc .act").removeClass("fn-hide"), $("#j-desc").on(tap,
            function() {
                $(this).toggleClass("descclamp")
            })),*/
            $("#j-desc").css("height", "auto"),
            1 == i.data.partakeState && $(".xcy-totalcount").addClass("fn-hide")
            //$(".xcy-totalcount").html("浏览人数" + i.data.numOfPartake);
            var f = i.data.startTime;
            i && i.data && (i.data.topic && (wxShareDict.title = i.data.topic, document.title = wxShareDict.title), i.data.remark && (wxShareDict.desc = i.data.remark), i.data.cover && (wxShareDict.imgUrl = i.data.cover), onshare()),
            increateNumTimeFn(),
            lvsCmd.ajax(apiServer + "/www/live/getTotalNumAndOrgInfo.do", {
                companyId: companyId,
                id: lvsCmd.urlParams.id
            },
            function(e, i) {
                if (e) {
                    var a = i.data.companyAlias,
                    n = i.data.companyName;
                    i.data.totalNum;
                    a && $("#j-orgname").append("<p>" + a + "</p>"),
                    n && $("#j-orgname").append('<p class="small">' + n + "</p>"),
                    "FFFF" == wxShareDict.desc && (wxShareDict.desc = a + formatDate(f, "YYYY-mm-dd") + "LLLLMMM", onshare()),
                    $(".j-orgname-back").on(tap,
                    function() {
                       location.href = "index.html?companyId=" + companyId + "&showlink=" + showlink
                    })
                }
                lvsCmd.ajax(apiServer + "/www/live/getShareContent.do", {
                    companyId: companyId

                },
                function(e, i) {
                    if (e) {
                        var a = i.data.logo;
                        if (0 == a.indexOf("http:") && 0 == a.indexOf("https:") || (a = "" + a + ""), $("#j-orglogo, .app-download .icon").html('<img src="' + i.data.logo + '">'), 0 == showlink) $(".app-download-place, .app-download").remove();
                        else if (i.data.downloadTitle) {
                            $(".app-download h4").append("<span>" + i.data.downloadTitle + "</span>"),
                            i.data.downloadDesc && $(".app-download h4").append('<p class="span">' + i.data.downloadDesc + "</p>"),
                            $(".app-download .close").on(tap,
                            function() {
                                $(".app-download-place, .app-download").remove()
                            });
                            var n = i.data.iosDownload;
                            n || (n = i.data.downAddress);
                            var o = i.data.androidDownload;
                            n || (o = i.data.downAddress),
                            userAgent.indexOf("iphone") > -1 || userAgent.indexOf("ipad") > -1 || userAgent.indexOf("mac") > -1 ? n && $(".app-download .download").on(tap,
                            function() {
                                location.href = n
                            }).show() : o && $(".app-download .download").on(tap,
                            function() {
                                location.href = o
                            }).show()
                        } else $(".app-download-place, .app-download").remove()
                    }
                })
            })
        } else lvsCmd.alert(i.errMsg)
    })
}
function reportContent(e) {
    return e.replace(/\n/g, "<br>")
}
var load=false;
function getReport(e, i) {
    if(load){
        return;
    }
    reprtLoading = !0,
    i || $("#j-listmore").removeClass("fn-hide"),
    lvsCmd.ajax(apiServer + "/www/live/getLiveRptList.do", {
        rid: lvsCmd.urlParams.id,
        sort: reportSort,
        page: e
    },
    function(a, n) {
            console.log(reportLoading);
            load=true;
        if (reportLoading = !1, a) {
            if (i && firstReportString == JSON.stringify(n.data)) return ! 1;
            if (n && n.data && n.data.length) {
                var o = $(reportTpl.render(n));
                if (reportBind(o), 1 == e ? ($("#j-report").html(o), firstReportString = JSON.stringify(n.data), reportPage = 1, $(".xcy-sort").removeClass("fn-hide"), $("#j-nonews").remove(), 1 == n.data.length && $(".xcy-sort").addClass("fn-hide")) : $("#j-report").append(o), void 0 != window.orientation, $("#j-report").height("auto"), joeFn.imgVideoLazy({
                    v: {
                        obj: $("#j-report .video")
                    },
                    bottom: 200,
                    top: 100
                }), joeFn.imgVideoLazy({
                    i: {
                        obj: $("#j-report img")
                    },
                    bottom: 200,
                    top: 100
                }), "android" == videoOsType) {
                    var t = $("#j-report .video");
                    t.find(".videocontrols").hide(),
                    t.find(".playposter").hide(),
                    t.find(".v-loading").hide(),
                    t.find("video").attr("controls", "controls")
                }
                initPhotoSwipe(e, o),
                reportLoadEnd = n.currentpage >= n.totalpage,
                $("#j-listmore").addClass("fn-hide")
            } else $("#j-listmore").addClass("fn-hide"),
            reportLoadEnd = !0,
            1 == e && ($(".xcy-sort").addClass("fn-hide"), $("#j-nonews").removeClass("fn-hide"))
        } else lvsCmd.alert(n.errMsg)
    })
}
function reportBind(e) {
    e.find(".pictures").each(function() {
        var e = $(this),
        i = e.data("count");
        if (1 == i) e.find("span").each(function() {
            var e = $(this).data("url"),
            i = $(this).data("water-bus"),
            a = $(this).data("water"),
            n = $(this).data("w"),
            o = $(this).data("h");
            if (e.indexOf(".gif") > -1) {
                var t = e;
                i = ""
            } else if (void 0 == window.orientation) var t = e + "?x-oss-process=image/" + a;
            else if (n > 2e3) var t = e + "?x-oss-process=image/resize,w_2000/" + a;
            else if (o > 2e3) var t = e + "?x-oss-process=image/resize,h_2000/" + a;
            else if (n > 0) var t = e + "?x-oss-process=image/resize,w_" + n + "/" + a;
            else var t = e + "?x-oss-process=image/" + a;
            var r = "data-lazy-img";
            if ("0" == n || "0" == o) var s = e + "?x-oss-process=image/resize,m_lfit,w_610,h_386,limit_0/" + i,
            d = $("<img " + r + '="' + s + '" class="normal">');
            else if (n / o >= 2) var s = e + "?x-oss-process=image/resize,m_fill,w_610,h_202,limit_0/" + i,
            d = $("<img " + r + '="' + s + '" class="horizontal">');
            else if (n / o * 16 <= 9) {
                var s = e + "?x-oss-process=image/resize,w_216";
                n / o * 32 <= 9 ? $(this).append("<em>闀垮浘</em>") : s += "/" + i;
                var d = $("<img " + r + '="' + s + '" class="vertical">')
            } else {
                var s = e + "?x-oss-process=image/resize,m_mfit,w_610,h_386,limit_0/" + i;
                if ($(this).parents(".j-photoswiper").css("maxHeight", "100%"), n / o > 610 / 386) var d = $("<img " + r + '="' + s + '" class="normal2">');
                else var d = $("<img " + r + '="' + s + '" class="normal2">')
            }
            $(this).data("url", t),
            $(this).append(d)
        });
        else {
            if (2 == i) var a = [{
                w: 304,
                h: 342
            },
            {
                w: 304,
                h: 342
            }];
            else if (3 == i) var a = [{
                w: 304,
                h: 342
            },
            {
                w: 304,
                h: 170
            },
            {
                w: 304,
                h: 170
            }];
            else var a = [{
                w: 304,
                h: 170
            },
            {
                w: 304,
                h: 170
            },
            {
                w: 304,
                h: 170
            },
            {
                w: 304,
                h: 170
            }];
            e.find("span").each(function(e) {
                var i = $(this).data("url"),
                n = $(this).data("water-bus"),
                o = $(this).data("water"),
                t = $(this).data("w"),
                r = $(this).data("h");
                if (i.indexOf(".gif") > -1) {
                    var s = i;
                    n = ""
                } else if (void 0 == window.orientation) var s = i + "?x-oss-process=image/" + n;
                else if (t > 2e3) var s = i + "?x-oss-process=image/resize,w_2000/" + o;
                else if (r > 2e3) var s = i + "?x-oss-process=image/resize,h_2000/" + o;
                else if (t > 0) var s = i + "?x-oss-process=image/resize,w_" + t + "/" + o;
                else var s = i + "?x-oss-process=image/" + o;
                a[e] || (a[e] = {
                    w: 304,
                    h: 170
                });
                var d = i + "?x-oss-process=image/resize,m_fill,w_" + a[e].w + ",h_" + a[e].h + ",limit_0",
                l = new Image;
                l.setAttribute("data-lazy-img", d);
                var c = $(l);
                $(this).data("url", s),
                $(this).append(c),
                l.complete && c.height() < c.parent().height() && (c.width("auto"), c.height(c.parent().height())),
                l.onload = function() {
                    c.height() < c.parent().height() && (c.width("auto"), c.height(c.parent().height()))
                }
            })
        }
    }),
    e.find(".act").removeClass("fn-hide"),
    e.find("li").each(function() {
        var i = $(this);
        i.find(".act").on(tap,
        function() {
            return "block" == i.find(".actlayer").css("display") ? e.find(".actlayer").hide() : (e.find(".actlayer").hide(), i.find(".actlayer").show()),
            !1
        })
    }),
    e.find(".j-actzan").on(tap,
    function() {
        dianZan($(this).data("id"));
        var e = $(this);
        return e.find(".zan").addClass("zaning"),
        e.find("i").addClass("bg-hide"),
        setTimeout(function() {
            e.find(".zan").removeClass("zaning"),
            e.find("i").removeClass("bg-hide"),
            $(".actlayer").hide()
        },
        800),
        !1
    }),
    e.find(".j-actping").on(tap,
    function() {
        openRemark($(this).data("id"));
    }),
    e.find(".remark").each(function() {
        showRemark($(this))
    })
}
function showRemark(e) {
    var i = e.find(".liker").data("liker").toString();
    if (i) {
        var a = i.split(",");
        if (a.length) {
            var n = "";
            e.removeClass("fn-hide"),
            e.find(".liker").removeClass("fn-hide");
            for (var o = 0; o < a.length; o++) n && (n += ", "),
            n += $.trim(a[o]);
            n = '<i class="iconfont icon-heart"></i> ' + n,
            e.find(".liker").html(n)
        }
    }
    e.find(".list p").length && (e.removeClass("fn-hide"), e.find(".list").removeClass("fn-hide")),
    e.find(".liker").hasClass("fn-hide") || e.find(".list").hasClass("fn-hide") || e.find(".line").removeClass("fn-hide")
}
function dianZan(e) {
    var i = {
        companyId: companyId,
        id: e,
        type:"2"
    };
    lvsCmd.ajax(apiServer + "/www/live/addLike.do", i,
    function(i, a) {
        if ("0" == a.status) {
            var n = $("#j-remark-" + e);
            var likerNum=parseInt($("#liker"+e).html());
            likerNum=likerNum+1;
            $("#liker"+e).html(likerNum)
            showRemark(n);
        }
    })
}
function openRemark(e) {
   $("#j-remark").removeClass("fn-hide");
   $("#j-remark input[name=rptId]").val(e);
}
function closeRemark() {
    $("#j-remark").addClass("fn-hide");
    $("#j-remark input[name=content]").blur();
}
function onshare() {
    wxReady && (wx.onMenuShareTimeline({
        title: wxShareDict.title,
        link: wxShareDict.link,
        imgUrl: wxShareDict.imgUrl,
        success: function() {},
        cancel: function() {}
    }), wx.onMenuShareAppMessage({
        title: wxShareDict.title,
        desc: wxShareDict.desc,
        link: wxShareDict.link,
        imgUrl: wxShareDict.imgUrl,
        type: "link",
        dataUrl: "",
        success: function() {},
        cancel: function() {}
    }), wx.onMenuShareQQ({
        title: wxShareDict.title,
        desc: wxShareDict.desc,
        link: wxShareDict.link,
        imgUrl: wxShareDict.imgUrl,
        success: function() {},
        cancel: function() {}
    }))
}
function increateNumTimeFn() {
    if ($(window).scrollTop() > cutWindowScrollTop && (cutWindowScrollTop = $(window).scrollTop()), $("#j-report li").each(function() {
        var e = $(this).offset();
        cutWindowScrollTop + windowHeight > e.top + e.height && (viewReportList["id_" + $(this).data("id")] || (viewReportList["id_" + $(this).data("id")] = "view"))
    }), isPostView) setTimeout(increateNumTimeFn, 1e3);
    else {
        var e = 0;
        if ($.each(viewReportList,
        function(i, a) {
            return e < 50 && void("view" == a && (e++, viewReportList[i] = "viewed"))
        }), e > 0) {
            isPostView = !0;
            var i = {
                id: id,
                increaseNum: e
            };
            isLogin ? (i.identityNo = userDict.identityNo, i.identityType = userDict.identityType) : i.identityType = webviewType
            /*
            lvsCmd.ajax(apiServer + "/h5/live/increaseNum.json", i,
            function(e, i) {
                isPostView = !1,
                increateNumTimeFn()
            })*/
        } else setTimeout(increateNumTimeFn, 1e3)
    }
}
var id = 0;
var userAgent=navigator.userAgent;
if (userAgent.indexOf("micromessenger") > -1) var webviewType = "weixin";
else var webviewType = "weixin";
$(window).scrollTop(0);
var wxShareDict = {
    title: "uuuurr",
    desc: "fdafdafdas",
    link: "llllll",
    imgUrl: ""
};
wxShareDict.imgUrl = document.location.origin + "/static/img/logo.png";
var liveinfoTpl = juicer($("#j-liveinfo script").html());
if ($("#j-liveinfo script").remove(), userAgent.indexOf("iphone") > -1 || userAgent.indexOf("ipad") > -1 || userAgent.indexOf("mac") > -1) var videoOsType = "ios";
else var videoOsType = "android";
//alert(videoOsType);
var videoFn = {
    formatVideoTime: function(e) {
        if (e > 60) {
            var i = e % 60,
            a = Math.floor(e / 60);
            if (i < 10 && (i = "0" + i), a < 10) return "0" + a + ":" + i;
            if (a < 60) return a + ":" + i;
            var n = Math.floor(a / 60);
            return a %= 60,
            a < 10 ? n + ":0" + a + ":" + i: n + ":" + a + ":" + i
        }
        return e < 10 && (e = "0" + e),
        "00:" + e
    }
};
$("#j-report").on(tap, ".playposter",
function() {
    function e() {
        s[0].play(),
        r.data("playing", !0),
        d.addClass("fn-hide").find("img").hide(),
        l.removeClass("fn-hide"),
        f.removeClass("fn-hide"),
        c.addClass("c_pause"),
        n = !0,
        a(),
        m && (m.find("video")[0].pause(), m.find(".c_play").removeClass("c_pause"), m.find(".playposter").removeClass("fn-hide")),
        t = !0
    }
    function i() {
        s[0].pause(),
        c.removeClass("c_pause"),
        d.removeClass("fn-hide"),
        r.data("playing", ""),
        n = !1,
        t = !1
    }
    function a() {
        if (s[0].duration && !isNaN(s[0].duration)) {
            var e = Math.ceil(s[0].duration);
            l.find(".c_timeline").removeClass("fn-hide"),
            l.find(".c_timeline .endtime").html(videoFn.formatVideoTime(e));
            var i = Math.ceil(s[0].currentTime);
            l.find(".c_timeline .starttime").html(videoFn.formatVideoTime(i)),
            l.find(".c_timeline .playtime").html(videoFn.formatVideoTime(i) + "/" + videoFn.formatVideoTime(e)),
            l.find(".c_timeline .overline").width(i / e * 100 + "%")
        }
        n && !s[0].paused ? (c.addClass("c_pause"), setTimeout(a, 500)) : (n = !1, $("#j-video-controls .c_play").removeClass("c_pause"))
    }
    var n = !1,
    o = !1,
    t = !1,
    r = $(this).parents(".video"),
    s = r.find("video"),
    d = r.find(".playposter"),
    l = r.find(".videocontrols"),
    c = l.find(".c_play"),
    p = l.find(".c_fullscren"),
    f = l.find(".c_timeline"),
    m = null;
    clickSetSpeed(r),
    $("#j-report .video").each(function() {
        $(this).data("playing") && (m = $(this), $(this).data("playing", ""))
    }),
    c.off(tap).on(tap,
    function() {
        n ? i() : e()
    });
    var v = setInterval(function() {
        t && (l.addClass("fn-hide"), clearInterval(v))
    },
    5e3);
    e(),
    p.on(tap,
    function() {
        fullH5Video(s[0])
    }),
    s.off(tap).on(tap,
    function() {
        l.hasClass("fn-hide") ? l.removeClass("fn-hide") : i()
    }),
    s.off("play").on("play",
    function() {
        r.find(".v-loading").removeClass("fn-hide")
    }),
    s.off("playing").on("playing",
    function() {
        r.find(".v-loading").addClass("fn-hide")
    }),
    s.off("ended").on("ended",
    function(e) {
        c.removeClass("c_pause"),
        i(),
        o = !0
    }),
    s.off("pause").on("pause",
    function(e) {
        r.find(".v-loading").addClass("fn-hide"),
        i()
    }),
    s.off("error").on("error",
    function(e) {
        d.removeClass("fn-hide"),
        d.find(".playbtn").addClass("fn-hide"),
        d.find(".tip").removeClass("fn-hide"),
        r.find(".v-loading").addClass("fn-hide")
    })
});
var joeFn = {
    imgVideoLazy: function(e) {
        function i() {
            var i = $(window).scrollTop();
            e.i && e.i.obj.each(function(e) {
                var n = $(this);
                if (a(n, i) && !n.attr("src")) {
                    var o = n.data("lazy-img");
                    n.attr("src", o)
                }
            }),
            e.v && e.v.obj.each(function(e) {
                var n = $(this);
                if (a(n, i)) {
                    var o = $(this).find("video"),
                    t = n.data("v-src"),
                    r = n.data("v-poster");
                    o.attr("src") || (o.attr("src", t), o.attr("poster", r))
                }
            })
        }
        function a(e, i) {
            return posb = e.offset().top - (i + n) - o.bottom,
            post = e.offset().top - i + o.top,
            posb <= 0 && post >= 0
        }
        var n = $(window).height(),
        o = {
            bottom: 0,
            top: 0
        };
        $.extend(o, e),
        $(window).on("scroll",
        function() {
            i()
        }),
        i()
    },
    setContentSize: function(e) {
        if (arguments[0]) if ("set" == e) $(".fn-contain").height($(window).height() + $(window).scrollTop()).css({
            position: "relative",
            "margin-top": $(window).scrollTop() * -1,
            overflow: "hidden"
        });
        else {
            var i = $(".fn-contain").css("margin-top").replace("px", "") * -1;
            $(".fn-contain").css({
                position: "static",
                "margin-top": 0
            }),
            $(".fn-contain").height("auto"),
            $(window).scrollTop(i)
        }
    },
    tpInfo: function() {
        var e = navigator.userAgent.toLowerCase();
        return e.indexOf("micromessenger") > -1 ? "weixin": (e.indexOf("mqqbrowser") > -1 || e.indexOf("qq") > -1) && "qq"
    },
    createYKId: function() {
        for (var e = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"], i = "", a = 0; a < 5; a++) {
            var n = Math.ceil(35 * Math.random());
            i += e[n]
        }
        var o = (new Date).getTime();
        return "yk" + o + i
    },
    shareObj: function(e) {
        function i() {
            var i = {
                url: location.href,
                title: document.title,
                pic: ""
            };
            e.shareParams instanceof Object && "object" == typeof e.shareParams && (e.shareParams.url && (i.url = e.shareParams.url), e.shareParams.title && (i.title = e.shareParams.title), e.shareParams.pic && (i.pic = e.shareParams.pic));
            var a = (new Date, "https://service.weibo.com/share/share.php?url=" + encodeURIComponent(i.url) + "&title=" + encodeURIComponent(i.title) + "&pic=" + encodeURIComponent(i.pic));
            window.open(a); ({
                access_token: "847545354",
                status: encodeURIComponent(i.title)
            })
        }
        function a() {
            o.mask.show(),
            o.tpList.show()
        }
        function n() {
            o.mask.hide(),
            o.tpList.hide()
        }
        e.tpNames = "weibo";
        var o = {
            mask: $("#mask"),
            tpList: $("#j-tpList")
        };
        e.clickObj.on(tap,
        function() {
            a(),
            joeFn.setContentSize("set")
        }),
        o.mask.on(tap,
        function() {
            n(),
            joeFn.setContentSize("hide")
        }),
        $(".j-share-" + e.tpNames).on(tap,
        function() {
            n(),
            joeFn.setContentSize("hide"),
            i()
        }),
        $(".s-btn").on(tap,
        function() {
            $("#shareTpList").hide(),
            joeFn.setContentSize("hide")
        }),
        "weixin" == joeFn.tpInfo() || "qq" == joeFn.tpInfo() ? ($(".j-share-weixin").on(tap,
        function() {
            $("#shareTpList").show().find(".s-text").removeClass("qq").addClass("weixin"),
            n()
        }), $(".j-share-qq").on(tap,
        function() {
            $("#shareTpList").show().find(".s-text").removeClass("weixin").addClass("qq"),
            n()
        })) : ($(".j-share-weixin").hide(), $(".j-share-qq").hide())
    }
};


juicer.register("reportContent", reportContent);
var reportLoading = !1,
reportLoadEnd = !1,
reportPage = 1,
reportSort = 0,
reportTpl = juicer($("#j-report script").html());
$("#j-report script").remove();
var firstReportString = "";
if (getReport(reportPage), setInterval(function() {
    return ! (reportLoading || $(window).scrollTop() > $("#j-liveinfo").height() || $(".fn-contain").hasClass("j-hasremark") || 1 == reportSort) && void getReport(1, !0)
},
1e4), $("#j-sort").on(tap,
function() {
     load=false;
    return ! reportLoading && (0 == reportSort ? (reportSort = 1, $(this).html("正序")) : (reportSort = 0, $(this).html("倒序")), reportLoadEnd = !1, reportPage = 1, $("#j-report").height($("#j-report").height()), $("#j-report").html(""), getReport(reportPage), void(cutWindowScrollTop = 0))
}),

 $(window).scroll(function() {
    if (!reportLoading && !reportLoadEnd && !$(".fn-contain").hasClass("j-hasremark")) {
        var e = $(window).scrollTop() + $(window).height();
        e > $("body").height() - 100 && (reportPage++, getReport(reportPage))
    }
}), $(".fn-contain").on(tap,
function() {
    $(".actlayer").hide()
    //$("#j-remark").addClass("fn-hide")
}), $(".fn-contain").on("touchmove",
function() {
    $(".actlayer").hide()
    //$("#j-remark").addClass("fn-hide")
}), $("#j-remarkform input[name=content]").on("click",
function() {
    setTimeout(function() {
        $(window).scrollTop($(".fn-contain").height())
    },
    200)
}), $(".fn-contain").on(touchstart,
function() {
    /*if ($(this).hasClass("j-hasremark")) return closeRemark(),
    !1*/
}), $("#j-remarkform").submit(function(e) {
    e.preventDefault();
    var i = $(this).find("input[name=rptId]").val(),
    type = $(this).find("input[name=type]").val(),
    a = $.trim($(this).find("input[name=content]").val());
    if (a.length > 200) return void lvsCmd.alert("内容不能超过200字");
    if(type==2){
        submitMsg();
        return;
    }

    if (a) {
            var message={};
             message.header={
                type:2,
                wxuser:wxuser
             };
             message.content=a;
             message.id=i;
             message.createTime=new Date().pattern("hh:mm:ss");
        var n = {
            companyId: lvsCmd.urlParams.companyId,
            rid: lvsCmd.urlParams.id,
            rptId: i,
            message: JSON.stringify(message),
            topic:"cmt",
            uid: wxuser.openid
        };
        lvsCmd.ajax(apiServer + "/www/live/cmt.do", n,
        function(e, n) {
            if (e) if (0 == n.status) {
                $("#j-remarkform input[name=content]").val("");
                closeRemark();
                //var o = $("#j-remark-" + i);
                //o.find(".list").append("<p><span>" + userDict.identityName + ": </span>" + a + "</p>"),
                //showRemark(o)
            } else lvsCmd.alert(n.errorMessage);
            else lvsCmd.alert("提交失败")
        })
    }
    return ! 1
}), "weixin" == webviewType) {
    var wxConfig = {
        noncestr: "",
        timestamp: (new Date).getTime(),
        url: wxShareDict.link,
        appid: ""
    };
    lvsCmd.ajax(apiServer + "/www/live/getWxJsSign.do", {companyId: companyId},
    function(e, i) {
        wx.config({
            debug: !1,
            appId: i.data.appId,
            timestamp: wxConfig.timestamp,
            nonceStr: i.data.nonceStr,
            signature: i.data.signature,
            jsApiList: ["onMenuShareTimeline", "onMenuShareAppMessage", "onMenuShareQQ"]
        })
        console.log( i.data);
    })
}
var wxReady = !1;

wx.ready(function() {
    wxReady = !0,
    onshare()
});
var cutWindowScrollTop = 0,
viewReportList = {},
windowHeight = $(window).height(),
isPostView = !1;
function getMsg(){
    lvsCmd.ajax(apiServer + "/www/live/getLiveMsgList.do", {rid: lvsCmd.urlParams.id},
    function(e, rst) {
        var tpl = document.getElementById('tpl-msg').innerHTML;
        $(rst).each(function(k,data){
                try{
                    var msg=JSON.parse(data.content);
                    var html = juicer(tpl,msg);
                    setMessageInnerHTML(html);
                }catch(e){}
        });

    })
}
getInfo();
getMsg();
$(function(){
    var li_a= $(".tab_menu ul li a");
    li_a.click(function(){
        $(this).removeClass("unselected");
        $(this).addClass("selected");
        $(this).parent().siblings().children().removeClass("selected");
        $(this).parent().siblings().children().addClass("unselected");
        var index =  li_a.index(this);
        $(".tab_box > div").eq(index).show().siblings().hide();
        if(index==0){
            $("#j-remark input[name=type]").val(1);
             $("#j-remark input[name=content]").attr("placeholder","评论");
            closeRemark();
        }else if(index==1){
            openRemark("0");
            $("#j-remark input[name=type]").val(2);
            $("#j-remark input[name=content]").attr("placeholder","写点什么唄~");
        }else{

                          closeRemark();
                      }
    });
});

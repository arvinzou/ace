var isLogin = false;
var userDict = {};
userDict.identityNo = "9999";
userDict.identityName = "HHHH";
userDict.identityType = "youke";

function clickSetSpeed(e) {
    var i = e,
        a = i.find(".overline"),
        n = i.find("video");
    i.on("click", ".timeline",
        function (e) {
            var o = i.find(".timeline").width(),
                t = e.offsetX,
                r = Math.ceil(n[0].duration),
                s = Math.round(t / o * 100) / 100;
            a.width(100 * s + "%"),
                n[0].currentTime = r * s
        })
}

function getInfo() {
    var e = {
        id: lvsCmd.urlParams.id,
        upNum: 1,
        identityType: webviewType,
        userId: lvsCmd.urlParams.id
    };
    lvsCmd.ajax(apiServer + "/www/live/getInfo", e,
        function (e, i) {
            function a() {
                $(window).scrollTop() <= 10 && (d = !1),
                d || ($(window).scrollTop() < c - l ? ($("#j-livevideo").removeClass("minvideobar"), $("#j-video-wrap").height(c)) : ($("#j-livevideo").addClass("minvideobar"), $("#j-video-wrap").height(l)))
            }

            function n() {
                var e = {
                        companyId: lvsCmd.urlParams.companyId,
                        id: lvsCmd.urlParams.id,
                        type: "1"
                    },
                    i = !0;
                    $("#j-xc-liker").on(tap,
                        function () {
                            var a = $(this);
                            a.addClass("xc-liker-ing"),
                                setTimeout(function () {
                                        a.removeClass("xc-liker-ing")
                                    },
                                    600),
                            i && lvsCmd.ajax(apiServer + "/www/live/addLike", e,
                                function (e, n) {
                                    if ("0" == n.status) {
                                        var o = a.find(".zan-num"),
                                            t = o.html();
                                        "w" == t.substr(-1) || (t = parseInt(t), o.html(++t))
                                    }
                                    i = !1
                                })
                        })
            }

            if (e) {
                $("#content").html(i.data.content);
                $("#j-liveinfo").html(liveinfoTpl.render(i));
                n();
                    joeFn.shareObj({
                        clickObj: $("#j-share"),
                        shareParams: {}
                    });
                var p = i.data.state;
                $("#j-sort").show();
                $("#startTime").html(i.data.startTime);
                if ("3" == p) {
                    // $(".xcy-sort p").append('<span class="xc-state end"><i>已结束</i></span>');
                }
                if ("2" == p) {
                    //$("#j-sort-c").append('<span class="xc-state ing"><i>直播中</i></span>');
                }
                $("#j-desc p").height() > $("#j-desc").height() && ($("#j-desc").addClass("descclamp"), $("#j-desc .act").removeClass("fn-hide"), $("#j-desc").on(tap,
                    function () {
                        $(this).toggleClass("descclamp")
                    })),
                    $("#j-desc").css("height", "auto"),
                1 == i.data.partakeState && $(".xcy-totalcount").addClass("fn-hide")
                //$(".xcy-totalcount").html("浏览人数" + i.data.numOfPartake);
                var f = i.data.startTime;
               $.leftTime(i.data.startTime,function(d){
               							if(d.status){
               								var $dateShow1=$(".count_down_content");
               								$dateShow1.find(".day_num").html(d.d);
               								$dateShow1.find(".hour_num").html(d.h);
               								$dateShow1.find(".min_num").html(d.m);
               								$dateShow1.find(".sec_num").html(d.s);
               							}
               						});

                i && i.data && (i.data.topic , i.data.remark , i.data.cover),
                    increateNumTimeFn(),
                    lvsCmd.ajax(apiServer + "/www/live/getTotalNumAndOrgInfo", {
                            companyId: companyId,
                            id: lvsCmd.urlParams.id
                        },
                        function (e, i) {
                            if (e) {
                                var a = i.data.companyAlias,
                                    n = i.data.companyName;
                                i.data.totalNum;
                                a && $("#j-orgname").append("<p>" + a + "</p>"),
                                n && $("#j-orgname").append('<p class="small">' + n + "</p>"),
                                "FFFF" == wxShareDict.desc && (wxShareDict.desc = a + formatDate(f, "YYYY-mm-dd") + "LLLLMMM", onshare()),
                                    $(".j-orgname-back").on(tap,
                                        function () {
                                            location.href = "index.html?companyId=" + companyId + "&showlink=" + showlink
                                        })
                            }
                            lvsCmd.ajax(apiServer + "/www/live/getShareContent", {
                                    companyId: companyId

                                },
                                function (e, i) {
                                    if (e) {
                                        var a = i.data.logo;
                                        if (0 == a.indexOf("http:") && 0 == a.indexOf("https:") || (a = "" + a + ""), $("#j-orglogo, .app-download .icon").html('<img src="' + i.data.logo + '">'), 0 == showlink) $(".app-download-place, .app-download").remove();
                                        else if (i.data.downloadTitle) {
                                            $(".app-download h4").append("<span>" + i.data.downloadTitle + "</span>"),
                                            i.data.downloadDesc && $(".app-download h4").append('<p class="span">' + i.data.downloadDesc + "</p>"),
                                                $(".app-download .close").on(tap,
                                                    function () {
                                                        $(".app-download-place, .app-download").remove()
                                                    });
                                            var n = i.data.iosDownload;
                                            n || (n = i.data.downAddress);
                                            var o = i.data.androidDownload;
                                            n || (o = i.data.downAddress),
                                                userAgent.indexOf("iphone") > -1 || userAgent.indexOf("ipad") > -1 || userAgent.indexOf("mac") > -1 ? n && $(".app-download .download").on(tap,
                                                        function () {
                                                            location.href = n
                                                        }).show() : o && $(".app-download .download").on(tap,
                                                        function () {
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
var load = false;
function getReport(e, i) {
    if (load) {
        return;
    }
    reprtLoading = !0,
    i || $("#j-listmore").removeClass("fn-hide"),
        lvsCmd.ajax(apiServer + "/www/live/getLiveRptList", {
                rid: lvsCmd.urlParams.id,
                sort: reportSort,
                page: e,
                time:new Date()
            },
            function (a, n) {
                console.log(reportLoading);
                load = true;
                if (reportLoading = !1, a) {
                    if (i && firstReportString == JSON.stringify(n.data)) return !1;
                    if (n && n.data && n.data.length) {
                        var o = $(reportTpl.render(n));
                        if (reportBind(o), 1 == e ? ($("#j-report").html(o), firstReportString = JSON.stringify(n.data), reportPage = 1, $(".xcy-sort").removeClass("fn-hide"), $("#j-nonews").remove(), 1 == n.data.length && $(".xcy-sort").addClass("fn-hide2")) : $("#j-report").append(o), void 0 != window.orientation, $("#j-report").height("auto"), joeFn.imgVideoLazy({
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
    e.find(".pictures").each(function () {
        var e = $(this),
            c = e.data("count");
        e.find("span").each(function (e) {
            var i = $(this).data("url"),
                n = $(this).data("water-bus"),
                o = $(this).data("water"),
                t = $(this).data("w"),
                r = $(this).data("h");
            $(this).data("url", i);
            if (c == 1) {
                $(this).append("<img data-lazy-img='" + i + "' src='" + i + "' style='width:100%;height:auto'/>");
            } else {
                 var l = new Image;
                 l.setAttribute("data-lazy-img", i);
                 l.setAttribute("src", i);
                 var c = $(l);
                $(this).append(c);
                l.complete && c.height() < c.parent().height() && (c.width("auto"), c.height(c.parent().height())),
                l.onload = function() {
                    c.height() < c.parent().height() && (c.width("auto"), c.height(c.parent().height()))
                }
            }

        });
    }),
        e.find(".act").removeClass("fn-hide"),
        e.find("li").each(function () {
            var i = $(this);
            i.find(".act").on(tap,
                function () {
                    return "block" == i.find(".actlayer").css("display") ? e.find(".actlayer").hide() : (e.find(".actlayer").hide(), i.find(".actlayer").show()),
                        !1
                })
        }),
        e.find(".j-actzan").on(tap,
            function () {
                dianZan($(this).data("id"));
                var e = $(this);
                return e.find(".zan").addClass("zaning"),
                    e.find("i").addClass("bg-hide"),
                    setTimeout(function () {
                            e.find(".zan").removeClass("zaning"),
                                e.find("i").removeClass("bg-hide"),
                                $(".actlayer").hide()
                        },
                        800),
                    !1
            }),
        e.find(".j-actping").on(tap,
            function () {
                openRemark($(this).data("id"));
            }),
        e.find(".remark").each(function () {
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
        type: "2"
    };
    lvsCmd.ajax(apiServer + "/www/live/addLike", i,
        function (i, a) {
            if ("0" == a.status) {
                var n = $("#j-remark-" + e);
                var s = $("#liker" + e).html();
                var likerNum = parseInt(s);
                likerNum = likerNum + 1;
                $("#liker" + e).html(likerNum)
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

function increateNumTimeFn() {
    if ($(window).scrollTop() > cutWindowScrollTop && (cutWindowScrollTop = $(window).scrollTop()), $("#j-report li").each(function () {
            var e = $(this).offset();
            cutWindowScrollTop + windowHeight > e.top + e.height && (viewReportList["id_" + $(this).data("id")] || (viewReportList["id_" + $(this).data("id")] = "view"))
        }), isPostView) setTimeout(increateNumTimeFn, 1e3);
    else {
        var e = 0;
        if ($.each(viewReportList,
                function (i, a) {
                    return e < 50 && void("view" == a && (e++, viewReportList[i] = "viewed"))
                }), e > 0) {
            isPostView = !0;
            var i = {
                id: id,
                increaseNum: e
            };
            isLogin ? (i.identityNo = userDict.identityNo, i.identityType = userDict.identityType) : i.identityType = webviewType

        } else setTimeout(increateNumTimeFn, 1e3)
    }
}
var id = 0;
var userAgent = navigator.userAgent;
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
var videoFn = {
    formatVideoTime: function (e) {
        if (e > 60) {
            var i = e % 60,
                a = Math.floor(e / 60);
            if (i < 10 && (i = "0" + i), a < 10) return "0" + a + ":" + i;
            if (a < 60) return a + ":" + i;
            var n = Math.floor(a / 60);
            return a %= 60,
                a < 10 ? n + ":0" + a + ":" + i : n + ":" + a + ":" + i
        }
        return e < 10 && (e = "0" + e),
        "00:" + e
    }
};
$("#j-report").on(tap, ".playposter",
    function () {
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
            $("#j-report .video").each(function () {
                $(this).data("playing") && (m = $(this), $(this).data("playing", ""))
            }),
            c.off(tap).on(tap,
                function () {
                    n ? i() : e()
                });
        var v = setInterval(function () {
                t && (l.addClass("fn-hide"), clearInterval(v))
            },
            5e3);
        e(),
            p.on(tap,
                function () {
                    fullH5Video(s[0])
                }),
            s.off(tap).on(tap,
                function () {
                    l.hasClass("fn-hide") ? l.removeClass("fn-hide") : i()
                }),
            s.off("play").on("play",
                function () {
                    r.find(".v-loading").removeClass("fn-hide")
                }),
            s.off("playing").on("playing",
                function () {
                    r.find(".v-loading").addClass("fn-hide")
                }),
            s.off("ended").on("ended",
                function (e) {
                    c.removeClass("c_pause"),
                        i(),
                        o = !0
                }),
            s.off("pause").on("pause",
                function (e) {
                    r.find(".v-loading").addClass("fn-hide"),
                        i()
                }),
            s.off("error").on("error",
                function (e) {
                    d.removeClass("fn-hide"),
                        d.find(".playbtn").addClass("fn-hide"),
                        d.find(".tip").removeClass("fn-hide"),
                        r.find(".v-loading").addClass("fn-hide")
                })
    });
var joeFn = {
    imgVideoLazy: function (e) {
        function i() {
            var i = $(window).scrollTop();
            e.i && e.i.obj.each(function (e) {
                var n = $(this);
                if (a(n, i) && !n.attr("src")) {
                    var o = n.data("lazy-img");
                    n.attr("src", o)
                }
            }),
            e.v && e.v.obj.each(function (e) {
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

            i()
    },
    setContentSize: function (e) {
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
    tpInfo: function () {
        var e = navigator.userAgent.toLowerCase();
        return e.indexOf("micromessenger") > -1 ? "weixin" : (e.indexOf("mqqbrowser") > -1 || e.indexOf("qq") > -1) && "qq"
    },
    createYKId: function () {
        for (var e = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"],
                 i = "", a = 0; a < 5; a++) {
            var n = Math.ceil(35 * Math.random());
            i += e[n]
        }
        var o = (new Date).getTime();
        return "yk" + o + i
    },
    shareObj: function (e) {
        function i() {
            var i = {
                url: location.href,
                title: document.title,
                pic: ""
            };
            e.shareParams instanceof Object && "object" == typeof e.shareParams && (e.shareParams.url && (i.url = e.shareParams.url), e.shareParams.title && (i.title = e.shareParams.title), e.shareParams.pic && (i.pic = e.shareParams.pic));
            var a = (new Date, "https://service.weibo.com/share/share.php?url=" + encodeURIComponent(i.url) + "&title=" + encodeURIComponent(i.title) + "&pic=" + encodeURIComponent(i.pic));
            window.open(a);
            ({
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
            function () {
                a(),
                    joeFn.setContentSize("set")
            }),
            o.mask.on(tap,
                function () {
                    n(),
                        joeFn.setContentSize("hide")
                }),
            $(".j-share-" + e.tpNames).on(tap,
                function () {
                    n(),
                        joeFn.setContentSize("hide"),
                        i()
                }),
            $(".s-btn").on(tap,
                function () {
                    $("#shareTpList").hide(),
                        joeFn.setContentSize("hide")
                }),
            "weixin" == joeFn.tpInfo() || "qq" == joeFn.tpInfo() ? ($(".j-share-weixin").on(tap,
                function () {
                    $("#shareTpList").show().find(".s-text").removeClass("qq").addClass("weixin"),
                        n()
                }), $(".j-share-qq").on(tap,
                function () {
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
if (getReport(reportPage), setInterval(function () {
            return !(reportLoading || $(window).scrollTop() > $("#j-liveinfo").height() || $(".fn-contain").hasClass("j-hasremark") || 1 == reportSort) && void getReport(1, !0)
        },
        1e4), $("#j-sort").on(tap,
        function () {
            load = false;
            return !reportLoading && (0 == reportSort ? (reportSort = 1, $(this).html("正序")) : (reportSort = 0, $(this).html("倒序")), reportLoadEnd = !1, reportPage = 1, $("#j-report").height($("#j-report").height()), $("#j-report").html(""), getReport(reportPage), void(cutWindowScrollTop = 0))
        }),
        $(window).scroll(function () {
            if (!reportLoading && !reportLoadEnd && !$(".fn-contain").hasClass("j-hasremark")) {
                var e = $(window).scrollTop() + $(window).height();
                e > $("body").height() - 100 && (reportPage++, getReport(reportPage))
            }
        }), $(".fn-contain").on(tap,
        function () {
            $(".actlayer").hide()
            //$("#j-remark").addClass("fn-hide")
        }), $(".fn-contain").on("touchmove",
        function () {
            $(".actlayer").hide()
            //$("#j-remark").addClass("fn-hide")
        }), $("#j-remarkform input[name=content]").on("click",
        function () {
            setTimeout(function () {
                    $(window).scrollTop($(".fn-contain").height())
                },
                200)
        }), $(".fn-contain").on(touchstart,
        function () {
            /*if ($(this).hasClass("j-hasremark")) return closeRemark(),
             !1*/
        }), $("#j-remarkform").submit(function (e) {
        e.preventDefault();
        var i = $(this).find("input[name=rptId]").val(),
            type = $(this).find("input[name=type]").val(),
            a = $.trim($(this).find("input[name=content]").val());
        if (a.length > 200) return void lvsCmd.alert("内容不能超过200字");
        if (type == 2) {
            submitMsg();
            return;
        }
        if (a) {
            var n = {
                rptId: i,
                content: a,
                uid: wxuser.unionid
            };
            lvsCmd.ajax(apiServer + "/liveCmt/www/insertLiveCmt", {jsons:JSON.stringify(n)},
                function (e, n) {
                    if (e) if (0 == n.status) {
                        $("#j-remarkform input[name=content]").val("");
                        closeRemark();
                        //var o = $("#j-remark-" + i);
                        //o.find(".list").append("<p><span>" + userDict.identityName + ": </span>" + a + "</p>"),
                        //showRemark(o)
                        var e = $('#j-remark-' + i).offset().top - $('#j-remark-' + i).height();
                        console.log("===============>" + e);
                        $(window).scrollTop(e)
                    } else lvsCmd.alert(n.errorMessage);
                    else lvsCmd.alert("提交失败")
                })
        }
        return !1
    }), "weixin" == webviewType) {
}


var cutWindowScrollTop = 0,
    viewReportList = {},
    windowHeight = $(window).height(),
    isPostView = !1;
function getMsg() {
    lvsCmd.ajax(apiServer + "/www/live/getLiveMsgList", {rid: lvsCmd.urlParams.id},
        function (e, rst) {
            var tpl = document.getElementById('tpl-msg').innerHTML;
            $(rst).each(function (k, data) {
                try {
                    var msg = JSON.parse(data.content);
                    var html = juicer(tpl, msg);
                    setMessageInnerHTML(html);
                } catch (e) {
                }
            });

        })
};
function visit() {
    var i = {
        id: lvsCmd.urlParams.id
    };
    lvsCmd.ajax(apiServer + "/www/live/visit", i,
        function (i, a) {
            if ("0" == a.status) {
                var visit = parseInt($("#visit").html());
                visit = visit + 1;
                $("#visit").html(visit)
            }
        })
};
getInfo();
getMsg();
visit();
$(function () {
    var li_a = $(".tab_menu ul li a");
    li_a.click(function () {
        $(this).removeClass("unselected");
        $(this).addClass("selected");
        $(this).parent().siblings().children().removeClass("selected");
        $(this).parent().siblings().children().addClass("unselected");
        var index = li_a.index(this);
        $(".tab_box > div").eq(index).show().siblings().hide();
        if (index == 0) {
            $("#j-remark input[name=type]").val(1);
            $("#j-remark input[name=content]").attr("placeholder", "评论");
            closeRemark();
        } else if (index == 1) {
            openRemark("0");
            $("#j-remark input[name=type]").val(2);
            $("#j-remark input[name=content]").attr("placeholder", "写点什么唄~");
        } else {

            closeRemark();
        }
    });
});
$(window).on("scroll",
    function () {
        if ($(window).scrollTop() >= 50) {
            $("#j-liveinfo .view").css("height", "2.62rem");
            $("#j-liveinfo .view").css("position", "fixed");
            $("#j-liveinfo .view").css("z-index", 10);
            $("#j-liveinfo .content").css("padding-top", "2.62rem");
            $("#j-video-wrap video").css("width", "5rem");
            $("#j-video-wrap video").css("height", "2.62rem");
            $("#j-video-wrap img").css("width", "5rem");
            $("#j-video-wrap img").css("height", "2.62rem");
            $("#j-video-wrap img").css("float", "left");
            $("#j-video-wrap .prism-cover").css("width", "5rem");
            $("#j-video-wrap .prism-cover").css("height", "2.62rem");
            $("#j-video-wrap .prism-cover").css("float", "left");
            $("#j-video-wrap").css("width", "10rem");
            $("#j-video-text").removeClass("fn-hide");
            $("#j-video-wrap video").css("float", "left");
            $("#j-video-text").css("float", "right");
            $(".count_down").addClass("fn-hide");
        } else {
            $("#j-liveinfo .view").css("height", "5.62rem");
            $("#j-liveinfo .view").css("position", "relative");
            $("#j-liveinfo .view").css("z-index", 0);
            $("#j-video-wrap video").css("width", "10rem");
            $("#j-video-wrap video").css("height", "5.62rem");
            $("#j-video-wrap img").css("width", "10rem");
            $("#j-video-wrap img").css("height", "5.62rem");
            $("#j-video-wrap img").css("float", "null");
            $("#j-video-wrap .prism-cover").css("width", "10rem");
            $("#j-video-wrap .prism-cover").css("height", "5.62rem");
            $("#j-video-wrap").css("width", "10rem");
            $("#j-video-text").addClass("fn-hide");
            $("#j-video-wrap").css("float", "null");
            $("#j-video-text").css("float", "null");
            $("#j-video-wrap .prism-cover").css("float", "null");
            $(".count_down").removeClass("fn-hide");
            $("#j-livevideo").css("height", "5.62rem");
            $("#j-liveinfo .content").css("padding-top", "0.2rem");

        }
    });
function openOrder(userId){
    $.modal({
      title: "打赏",
      text: "请选择打赏金额",
      buttons: [
        { text: "0.1元", onClick: function(){
            createOrder(userId,0.1);
        } },
        { text: "1元", onClick: function(){
            createOrder(userId,1);
        } },
         { text: "5元", onClick: function(){
                    createOrder(userId,5);
                } },
        { text: "取消", className: "default", onClick: function(){ console.log(3)} },
      ]
    });
}

function createOrder(userId,payMoney){
    var data = {
        "base": {
            "businessId": userId,
            "payMoney": payMoney
        }
   }
    $.ajax({
        url: "/jxb/www/order/createTipOrder",
        type:"post",
        async:false,
        data:{data:JSON.stringify(data),unionid:userId},
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                var orderResultData = result.data;
                $.ajax({
                    url: "/jxb/www/wxpay/unifiedorder",
                    type:"post",
                    async:false,
                    data:{
                        fee: orderResultData.payMoney,
                        body: '咨询预约产品',
                        attach: orderResultData.orderId
                    },
                    success:function(result){
                        if(result.status == 0) {
                            var payData = result.data;
                            onBridgeReady(payData, orderResultData.orderId);
                            console.log(result);
                        }else {
                            if(result.info){
                                alert(result.info);
                            }else if(result.errorMessage){
                                alert(result.errorMessage);
                            }
                            return;
                        }
                    },
                    error:function(){
                        alert("系统服务内部异常！");
                        return;
                    }
                });
            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
            return;
        }
    });
}


function onBridgeReady(obj, orderId){
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: obj.appId, // 必填，
        timestamp: obj.timeStamp, // 必填，
        nonceStr: obj.nonceStr, // 必填，
        signature: obj.paySign,// 必填，签名，见附录1
        jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function () {
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
        wx.chooseWXPay({
            timestamp: obj.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
            nonceStr: obj.nonceStr, // 支付签名随机串，不长于 32 位
            package: obj.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
            signType: obj.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
            paySign: obj.paySign, // 支付签名
            complete: function (res) {
                window.pay_tag = true;
                if (res.errMsg == "chooseWXPay:ok") {
                    alert("打赏成功！");
                } else {
                    WeixinJSBridge.invoke('closeWindow', {}, function (res) {
                        if (res.err_msg =="get_brand_wcpay_request:ok")
                        {
                             WeixinJSBridge.invoke('closeWindow', {}, function (res) {
                            });

                         }
                    });
                }
            }
        });
    });
}



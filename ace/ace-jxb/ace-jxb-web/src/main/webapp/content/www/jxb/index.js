function getList() {
    loadList || (loadList = !0, lvsCmd.ajax(apiServer + "/www/jxb/getListByCompany.do", {
            companyId: "0001",
            page: listPage || 1
        },
        function (i, g) {
            //新增直播按钮显示/隐藏
            if (wxuser.role && wxuser.role == 'admin') {
                $("#add_note_icon").removeClass("fn-hide");
            }
            ;
            $("#add_note_icon").on(tap,
                function () {
                    console.log(i);
                    location.href = "jxb_create.html?id=" + lvsCmd.urlParams.id + "&companyId=0001";
                    // console.log("**********跳转创建直播*********");
                });

            if (i) {
                $("#j-title").html(g.companyName),
                    document.title = g.companyName + "",
                    $("#j-jxbnum em").html(g.totalcount);
                var e = $(jxblistTpl.render(g));
                $("#j-jxblist").append(e),
                    e.find("li").on(tap,
                        function () {
                            location.href = "jxb.html?companyId=0001" + "&showlink=" + showlink + "&id=" + $(this).data("id")
                        })
            } else lvsCmd.alert(g.errMsg);
            loadList = !1
        }))
}
function onshare() {
    wxReady && (wx.onMenuShareTimeline({
        title: wxShareDict.title,
        link: wxShareDict.link,
        imgUrl: wxShareDict.imgUrl,
        success: function () {
        },
        cancel: function () {
        }
    }), wx.onMenuShareAppMessage({
        title: wxShareDict.title,
        desc: wxShareDict.desc,
        link: wxShareDict.link,
        imgUrl: wxShareDict.imgUrl,
        type: "link",
        dataUrl: "",
        success: function () {
        },
        cancel: function () {
        }
    }))
}

var listPage = 1,
    wxShareDict = {
        title: "",
        desc: "",
        link: document.URL
    };
lvsCmd.ajax(apiServer + "/www/jxb/getShareContent.do", {
        companyId: "0001"
    },
    function (i, g) {
        if (i) {
            var e = g.data.logo;
            $("#j-logo").html('<img src="' + e + '">'),
                wxShareDict.desc = g.data.downloadTitle
        } else lvsCmd.alert(g.errMsg)
    });
var jxblistTpl = juicer($("#j-jxblist script").html());
$("#j-jxblist script").remove();
var loadList = !1;
getList(),
    $(window).scroll(function () {
        if (!loadList) {
            var i = $(window).scrollTop() + $(window).height();
            i > $("body").height() - 100 && (listPage++, getList())
        }
    });
var wxConfig = {
    noncestr: "huacaiapp",
    timestamp: (new Date).getTime(),
    url: document.URL.split("#")[0],
    appid: "wx8ecfbf8357e25e45"
};
function getList() {
    loadList || (loadList = !0, lvsCmd.ajax(apiServer + "/www/live/getListByCompany.do", {
        companyId: companyId,
        page: listPage||1
    },
    function(i, g) {
        if (i) {
            $("#j-title").html(g.companyName),
            document.title = g.companyName + "",
            $("#j-livenum em").html(g.totalcount);
            var e = $(livelistTpl.render(g));
            $("#j-livelist").append(e),
            e.find("li").on(tap,
            function() {
                   location.href="live.html?companyId="+companyId+"&showlink="+showlink+"&id="+$(this).data("id")
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
    }))
}

var listPage = 1,
wxShareDict = {
    title: "",
    desc: "",
    link: document.URL
};
lvsCmd.ajax(apiServer + "/www/live/getShareContent.do", {
    companyId:companyId
},
function(i, g) {
    if (i) {
        var e = g.data.logo;
        $("#j-logo").html('<img src="' + e + '">'),
        wxShareDict.desc = g.data.downloadTitle
    } else lvsCmd.alert(g.errMsg)
});
var livelistTpl = juicer($("#j-livelist script").html());
$("#j-livelist script").remove();
var loadList = !1;
getList(),
$(window).scroll(function() {
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
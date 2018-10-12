function getList() {
    loadList || (loadList = !0, lvsCmd.ajax(apiServer + "/www/live/getListByCompany", {
            companyId: companyId,
            page: listPage || 1
        },
        function (i, g) {


            if (i) {
                $("#j-title").html(g.companyName),
                    document.title = g.companyName + "",
                    $("#j-livenum em").html(g.totalcount);
                var e = $(livelistTpl.render(g));
                $("#j-livelist").append(e),
                    e.find("li").on('click',
                        function () {
                            location.href = "../jsp/live/index.jsp?companyId=" + companyId + "&id=" + $(this).data("id")
                        })
            } else lvsCmd.alert(g.errMsg);
            loadList = !1
        }))
}


var listPage = 1;
lvsCmd.ajax(apiServer + "/www/live/getShareContent", {
        companyId: companyId
    },
    function (i, g) {
        if (i) {
            var e = g.data.logo;
            $("#j-logo").html('<img src="' + e + '">');
        } else lvsCmd.alert(g.errMsg)
    });
var livelistTpl = juicer($("#j-livelist script").html());
var loadList = !1;
getList(),
    $(window).scroll(function () {
        if (!loadList) {
            var i = $(window).scrollTop() + $(window).height();
            i > $("body").height() - 100 && (listPage++, getList())
        }
    });
function formatDate(e, a) {
    if (e) var r = new Date(e);
    else var r = new Date;
    var t = r.getFullYear(),
    n = t.toString().substr(2, 2),
    o = 1 + r.getMonth(),
    i = o > 9 ? o: "0" + o,
    s = r.getDate(),
    c = s > 9 ? s: "0" + s,
    l = r.getHours(),
    p = l > 9 ? l: "0" + l,
    m = r.getMinutes(),
    d = m > 9 ? m: "0" + m,
    u = r.getSeconds(),
    v = u > 9 ? u: "0" + u,
    g = a.replace(/YY/g, t).replace(/Y/g, n).replace(/MM/g, i).replace(/M/g, o).replace(/DD/g, c).replace(/D/g, s).replace(/hh/g, p).replace(/h/g, l).replace(/mm/g, d).replace(/m/g, m).replace(/ss/g, v).replace(/s/g, u);
    return g
}
var hostnameArray = location.hostname.split("."),
domain = hostnameArray[1] + "." + hostnameArray[2];
 var executeServer = location.protocol + "//zx.huacainfo.com";
 var apiServer = "/live",
lvsCmd = {};
if (lvsCmd.urlParams = function() {
    var e = window.location.search.substr(1);
    if (e) {
        var a = e.split("&"),
        r = {};
        return $.each(a,
        function() {
            var e = this.split("=");
            r[e[0]] = decodeURIComponent(e[1])
        }),
        r
    }
    return {}
} (), lvsCmd.cookie = {
    get: function(e) {
        var a, r = new RegExp("(^| )" + e + "=([^;]*)(;|$)");
        return (a = document.cookie.match(r)) ? unescape(a[2]) : null
    },
    set: function(e, a, r) {
        var t = r.substr( - 1),
        n = parseInt(r),
        o = {
            s: 1e3,
            m: 6e4,
            h: 36e5,
            d: 864e5
        };
        o[t] || (t = "s");
        var i = n * o[t],
        s = new Date;
        s.setTime(s.getTime() + i),
        document.cookie = e + "=" + escape(a) + ";path=/;expires=" + s.toGMTString()
    },
    del: function(e) {
        lvsCmd.cookie.set(e, "null", "-1")
    }
},
lvsCmd.urlParams.companyId && !isNaN(lvsCmd.urlParams.companyId))
var companyId = +lvsCmd.urlParams.companyId;
else var companyId = +lvsCmd.cookie.get("companyId");
var showlink = 1;0 == lvsCmd.urlParams.showlink && (showlink = 0),
lvsCmd.alert = function(e) {
    if (0 == $("#j-livealert").length) {
        var a = $('<div id="j-livealert" style="position:fixed;left:0;right:0;top:50%;text-align:center;z-index:99;"><span style="display:inline-block;margin:auto;padding:0.5rem;background-color:rgba(0,0,0,0.5);color:#fff;border-radius:0.2rem;"></span></div>');
        $("body").append(a)
    } else var a = $("#j-livealert");
    a.find("span").html(e),
    a.css("display", "block"),
    a.css("margin-top", -a.height() / 2);
    var r = setTimeout(function() {
        a.css("display", "none")
    },
    2e3);
    a.find("p").on(tap,
    function() {
        a.css("display", "none"),
        clearTimeout(r)
    })
},
lvsCmd.ajax = function(e, a, r) {
    $.ajax({
        type: "post",
        dataType: "json",
        contentType: "application/json",
        url: e,
        data: JSON.stringify(a),
        success: function(e) {
            r(!0, e)
        },
        error: function(e) {
            r(!1, e)
        }
    })
},
lvsCmd.ajaxUc = function(e, a, r) {
    var t = "executeCallback" + (new Date).getTime(),
    n = executeServer + "/execute/?action=" + e + "&callback=" + t;
    a && (a = escape(JSON.stringify(a)), n += "&actionBody=" + a);
    var o = $('<iframe src="' + n + '" style="display:none;"></iframe>');
    window[t] = function(e, a) {
        o.remove(),
        r(e, a)
    },
    $("body").append(o)
},
juicer.register("formatDate", formatDate);
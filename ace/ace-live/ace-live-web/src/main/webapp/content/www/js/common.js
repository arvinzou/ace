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
Date.prototype.pattern=function(fmt) {
  var o = {
  "M+" : this.getMonth()+1, //月份
  "d+" : this.getDate(), //日
  "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
  "H+" : this.getHours(), //小时
  "m+" : this.getMinutes(), //分
  "s+" : this.getSeconds(), //秒
  "q+" : Math.floor((this.getMonth()+3)/3), //季度
  "S" : this.getMilliseconds() //毫秒
  };
  var week = {
  "0" : "/u65e5",
  "1" : "/u4e00",
  "2" : "/u4e8c",
  "3" : "/u4e09",
  "4" : "/u56db",
  "5" : "/u4e94",
  "6" : "/u516d"
  };
  if(/(y+)/.test(fmt)){
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
  }
  if(/(E+)/.test(fmt)){
    fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
  }
  for(var k in o){
    if(new RegExp("("+ k +")").test(fmt)){
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    }
  }
  return fmt;
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
lvsCmd.urlParams.companyId)
var companyId = lvsCmd.urlParams.companyId;
else var companyId = lvsCmd.cookie.get("companyId");
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
        url: e,
        data: a,
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
lvsCmd.cutimg = function(e, t, o) {
    this.obj = e,
    this.config = {
        filetype: 5,
        width: 400,
        height: 300,
        scale: 1,
        original: !0
    },
    t && $.extend(this.config, t),
    o && (this.callback = o),
    this.init()
},
lvsCmd.cutimg.prototype = {
    init: function() {
        function e() {
            o.fileBtn.html(a),
            o.fileBtn.find("input").on("change",
            function() {
                t(this.files[0])
            })
        }
        function t(t) {
            var a = o.config;
            a.file = t;
            var i = juicer(pizzaTpl.pzCutimg, a);
            new parent.pizzaCmd.overlay(i,
            function(t) {
                t.show();
                var i = new pizzaCmd.cutimg(t.obj, a);
                a.original || t.obj.find(".j-cutimg-original").parent().addClass("fn-hide"),
                t.obj.find(".j-overlay-close").click(function() {
                    t.remove(),
                    e()
                }),
                t.obj.find(".j-cutimg-true").click(function() {
                    var a = i.save();
                    a ? ($(this)[0].disabled = !0, $(this).val("上传截图中..."), lvsCmd.ajax(apiServer + "/video/processingImages.json", {
                        fileType: o.config.filetype,
                        cover: a
                    },
                    function(a, i) {
                        a && (0 == i.status ? o.callback && o.callback(i.data.cover) : alert(i.errMsg)),
                        t.remove(),
                        e()
                    })) : (t.remove(), e())
                })
            })
        }
        if (!window.FileReader) return this.obj.html('<div class="file fn-textcenter"><p class="nosupport">该浏览器不支持图片截取功能，建议使用谷歌(Chrome)浏览器</p></div>'),
        !1;
        this.fileBtn = this.obj.find(".j-file-input");
        var o = this,
        a = this.fileBtn.html();
        pizzaTpl.pzCutimg ? e() : pizzaCmd.tpl("../../cutimg.html",
        function() {
            e()
        }),
        this.config.padding || (this.config.padding = this.config.width < this.config.height ? Math.ceil(this.config.width / 4) : Math.ceil(this.config.height / 4))
    }
};
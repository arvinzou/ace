var urlContent = "http://zx.huacainfo.com/";
var html=[];
html.push("<div class=\"login_box\">");
html.push("<div class=\"login\">");
try{
    if(userProp){
        html.push("<span class=\"companyName\"  ng-if=\"userProp\"><a href=\"/fop/www/html/member/member.html\" title='"+userProp.name+"' target=\"_blank\">"+userProp.name+"</a><i title='修改密码' style='padding-left: 10px;' id='editUserInfo' class='glyphicon glyphicon-edit'></i></span>");
        html.push("<span class=\"userNav\" ng-if=\"userProp\"><a href=\"/portal/dynamic/portal/security/loginOut.jsp\">退出</a></span>");
    }else{
        html.push("<span class=\"userNav\" ng-if=\"!userProp\"><a href=\"/portal/dynamic/portal/security/login.jsp\">登录</a></span>");
    }
}catch(e){
    html.push("<span class=\"userNav\" ng-if=\"!userProp\"><a href=\"/portal/dynamic/portal/security/login.jsp\">登录</a></span>");
}

html.push("<span>|</span>");
html.push("<span class=\"userNav\"><a href=\"/fop/www/html/login/regist.html\">注册</a></span>");
html.push("</div>");
html.push("</div>");
html.push("<div class=\"logo_box\">");
html.push("<div class=\"logo\">");
html.push("<div class=\"logo_image\"><img src=\"/fop/www/images/logo.png\" /></div>");
html.push("<div class=\"logo_word\">承办单位：常德市工商业联合会·总商会</div>");
html.push("</div>");
html.push("<div class=\"words\">");
html.push("<marquee id='Marquee'>");
html.push("<span id=\"effect\" style='font-size: 38px;'>开放强市&nbsp;&nbsp;产业立市</span>");
html.push("<span id=\"effect01\">&nbsp;&nbsp;&nbsp;成就企业家&nbsp;&nbsp;厚待投资者</span>");
html.push("</marquee>");
html.push("</div>");
html.push("</div>");
html.push("<div class=\"header-nav\">");
html.push("<div class=\"contain\">");
html.push("<ul class=\"nav-list\">");
html.push("<li>");
html.push("<a href=\"/fop/www/index.html\">首页<span class=\"trig\"></span></a>");
html.push("</li>");
html.push("<li><a href=\"/fop/www/html/information/information_index.html\">市情要闻</a></li>");
html.push("<li><a href=\"/fop/www/html/information/information_dynamic.html\">工作动态</a></li>");
html.push("<li><a href=\"/fop/www/html/information/information_commerce.html\">商会动态</a></li>");
html.push("<li>");
html.push("<a href=\"#\">领导之窗<span class=\"trig\"></span></a>");
html.push("<ul>");
html.push("<li><a href=\"/fop/www/html/leader/shiwei_index.html\">常德市委</a></li>");
html.push("<li><a href=\"/fop/www/html/leader/shirenda_index.html\">常德市人大</a></li>");
html.push("<li><a href=\"/fop/www/html/leader/zhengfu_index.html\">常德市政府</a></li>");
html.push("<li><a href=\"/fop/www/html/leader/shizhengxie_index.html\">常德市政协</a></li>");
html.push("</ul>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"#\">信息服务<span class=\"trig\"></span></a>");
html.push("<ul>");
html.push("<li><a href=\"/fop/www/html/policy/policy.html\">政策信息</a></li>");
html.push("<li><a href=\"/fop/www/html/investment/investment.html\">招商信息</a></li>");
html.push("<li><a href=\"/fop/www/html/personnel/personnel.html\">人才信息</a></li>");
html.push("<li><a href=\"/fop/www/html/cooperation/cooperation_index.html\">项目信息</a></li>");
html.push("<li><a href=\"/fop/www/html/finance/product.html\" >金融产品</a></li>");
html.push("<li><a href=\"/fop/www/html/product/product.html\" >企业产品</a></li>");
html.push("<li><a href=\"/fop/www/html/corporateStyle/costyle.html\">风采展示</a></li>");
html.push("<li><a href=\"/fop/www/html/sincerity/sincerity.html\">诚信建设 </a></li>");
html.push("</ul>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"/fop/www/html/band/band.html\">品牌推广<span class=\"trig\"></span></a>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"#\">在线服务<span class=\"trig\"></span></a>");
html.push("<ul>");
html.push("<li><a href=\"/fop/www/html/gov_service/gov_service1.html\">政企服务</a></li>");
html.push("<li><a href=\"/fop/www/html/finance/finance.html\" >银企服务</a></li>");
html.push("<li><a href=\"/fop/www/html/law/lawer_index.html\" >法律服务</a></li>");
html.push("</ul>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"/fop/www/html/group/group.html\">组织建设<span class=\"trig\"></span></a>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"/fop/www/html/publicity/publicity.html\">会员信息<span class=\"trig\"></span></a>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"#\">留言箱<span class=\"trig\"></span></a>");
html.push("<ul>");
html.push("<li><a href=\"/fop/www/html/satisfy/satisfy.html\" >满意度调查</a></li>");
html.push("</ul>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"/fop/www/html/share/search.html\">信息搜索<span class=\"trig\"></span></a>");
html.push("</li>");
html.push("</ul>");
html.push("</div>");
html.push("<div class=\"second-bg\"></div>");
html.push("</div>");

try {
    if (userProp) {
        html.push("<div id=\"editMsg\" style=\"display: none;\">");
        html.push("<div class='editForm'>");
        html.push("<p>");
        html.push("<span class='edit_title'>用户名：</span>");
        html.push("<span class='edit_input'><input name=\"edit_name\" type=\"text\" value='" + userProp.username + "' disabled/></span>");
        html.push("</p>");
        html.push("<p>");
        html.push("<span class='edit_title'>密码：</span>");
        html.push("<span class='edit_input'><input name=\"edit_pwd\" type=\"password\"/></span>");
        html.push("</p>");
        html.push("<p>");
        html.push("<span class='edit_title'>请重复密码：</span>");
        html.push("<span class='edit_input'><input name=\"edit_pwdsec\" type=\"password\"/></span>");
        html.push("</p>");
        html.push("</div>");
        html.push("</div>");
    }
}catch(e){
}

var text=html.join("\n");

$(function(){
    $("#header").append(text);

    $('.nav-list>li').hover(function(){
        var $ul=$(this).find('ul');
        var oW=$(this).width();//li
        var otrigW=$(this).find('.trig').width();
        var oNavListL=$('.nav-list').offset().left;
        var oTL=$(this).offset().left-oNavListL;//距离最左边的距离
        var oTR=$('.nav-list').width()-oTL-oW;//距离最右边的距离
        // console.log(oNavListL+":"+oTL);

        if($ul.find('li').length>0){
            $('.second-bg').show();
            $(this).find('.trig').show();
            $ul.show();
            var sum=0;
            var oLeft=0;
            for(var i=0;i<$ul.find('li').length;i++){
                sum+=$ul.find('li').eq(i).width()+4;
            }
            $ul.width(sum);
            oLeft=(sum-oW)/2;
            if(oLeft>oTL){//到达左侧边界
                oLeft=oTL;
                $ul.css('left',-oLeft+'px');
                return ;
            }
            if(oLeft>oTR){
                $ul.css('right',-oTR+'px');
                return ;
            }
            $ul.css('left',-oLeft+'px');

        }
    },function(){
        $('.second-bg').hide();
        $(this).find('ul').hide();
        $(this).find('.trig').hide();
    });

    /**
     * 滚动字幕开始
     */
   /* var Mar = document.getElementById("Marquee");
    var child_div = Mar.getElementsByTagName("div")
    var picH = 60; //移动高度
    var scrollstep = 5; //移动步幅,越大越快
    var scrolltime = 20; //移动频度(毫秒)越大越慢
    var stoptime = 2000; //间断时间(毫秒)
    var tmpH = 0;
    Mar.innerHTML += Mar.innerHTML;

    function start() {
        if(tmpH < picH) {
            tmpH += scrollstep;
            if(tmpH > picH) tmpH = picH;
            Mar.scrollTop = tmpH;
            setTimeout(start, scrolltime);
        } else {
            tmpH = 0;
            Mar.appendChild(child_div[0]);
            Mar.scrollTop = 0;
            setTimeout(start, stoptime);
        }
    }

    setTimeout(start, stoptime);
    var Mar = document.getElementById("Marquee");
    var child_div = Mar.getElementsByTagName("div")
    var picH = 60; //移动高度
    var scrollstep = 3; //移动步幅,越大越快
    var scrolltime = 20; //移动频度(毫秒)越大越慢
    var stoptime = 3000; //间断时间(毫秒)
    var tmpH = 0;
    Mar.innerHTML += Mar.innerHTML;

    function start() {
        if(tmpH < picH) {
            tmpH += scrollstep;
            if(tmpH > picH) tmpH = picH;
            Mar.scrollTop = tmpH;
            setTimeout(start, scrolltime);
        } else {
            tmpH = 0;
            Mar.appendChild(child_div[0]);
            Mar.scrollTop = 0;
            setTimeout(start, stoptime);
        }
    }

    setTimeout(start, stoptime)*/
    /**
     * 滚动字幕结束
     */

    $("#editUserInfo").click(function() {
        layer.open({
            type: 1,
            title:"密码修改",
            content: $("#editMsg"),
            btn: '保存修改',
            shadeClose: false,
            skin: 'myskin',
            area:['500px','300px'],
            yes: function (index) {
                var edit_name = $("input[name='edit_name']").val();
                var edit_pwd = $("input[name='edit_pwd']").val();
                var edit_pwdsec = $("input[name='edit_pwdsec']").val();
                if (edit_pwd == '' || edit_pwd == undefined) {
                    layer.alert("请输入密码！", {
                        icon: 5,
                        skin: 'myskin'
                    });
                    return;
                }
                if (edit_pwdsec == '' || edit_pwdsec == undefined) {
                    layer.alert("请重复密码！", {
                        icon: 5,
                        skin: 'myskin'
                    });
                    return;
                }
                if (edit_pwd != edit_pwdsec) {
                    layer.alert("两次输入的密码不一致！", {
                        icon: 5,
                        skin: 'myskin'
                    });
                    return;
                }
                if (confirm("确定要修改吗？")) {
                    $.ajax({
                        type: "post",
                        url: "/portal/system/updatePassword.do",
                        data: {
                            password: edit_pwd,
                            repassword: edit_pwdsec
                        },
                        beforeSend: function (XMLHttpRequest) {

                        },
                        success: function (rst, textStatus) {
                            if (rst.state) {
                                layer.alert(rst.errorMessage, {
                                    icon: 1,
                                    skin: 'myskin'
                                });
                                layer.close(index);
                            } else {
                                layer.alert(rst.errorMessage, {
                                    icon: 5,
                                    skin: 'myskin'
                                });
                            }
                        },
                        complete: function (XMLHttpRequest, textStatus) {

                        },
                        error: function () {

                        }
                    });
                }
            }
        });
    });
});


/*jQuery.easing['jswing'] = jQuery.easing['swing'];

jQuery.extend(jQuery.easing,
    {
        def: 'easeOutQuad',
        swing: function (x, t, b, c, d) {
            //alert(jQuery.easing.default);
            return jQuery.easing[jQuery.easing.def](x, t, b, c, d);
        },
        easeInQuad: function (x, t, b, c, d) {
            return c * (t /= d) * t + b;
        },
        easeOutQuad: function (x, t, b, c, d) {
            return -c * (t /= d) * (t - 2) + b;
        },
        easeInOutQuad: function (x, t, b, c, d) {
            if ((t /= d / 2) < 1) return c / 2 * t * t + b;
            return -c / 2 * ((--t) * (t - 2) - 1) + b;
        },
        easeInCubic: function (x, t, b, c, d) {
            return c * (t /= d) * t * t + b;
        },
        easeOutCubic: function (x, t, b, c, d) {
            return c * ((t = t / d - 1) * t * t + 1) + b;
        },
        easeInOutCubic: function (x, t, b, c, d) {
            if ((t /= d / 2) < 1) return c / 2 * t * t * t + b;
            return c / 2 * ((t -= 2) * t * t + 2) + b;
        },
        easeInQuart: function (x, t, b, c, d) {
            return c * (t /= d) * t * t * t + b;
        },
        easeOutQuart: function (x, t, b, c, d) {
            return -c * ((t = t / d - 1) * t * t * t - 1) + b;
        },
        easeInOutQuart: function (x, t, b, c, d) {
            if ((t /= d / 2) < 1) return c / 2 * t * t * t * t + b;
            return -c / 2 * ((t -= 2) * t * t * t - 2) + b;
        },
        easeInQuint: function (x, t, b, c, d) {
            return c * (t /= d) * t * t * t * t + b;
        },
        easeOutQuint: function (x, t, b, c, d) {
            return c * ((t = t / d - 1) * t * t * t * t + 1) + b;
        },
        easeInOutQuint: function (x, t, b, c, d) {
            if ((t /= d / 2) < 1) return c / 2 * t * t * t * t * t + b;
            return c / 2 * ((t -= 2) * t * t * t * t + 2) + b;
        },
        easeInSine: function (x, t, b, c, d) {
            return -c * Math.cos(t / d * (Math.PI / 2)) + c + b;
        },
        easeOutSine: function (x, t, b, c, d) {
            return c * Math.sin(t / d * (Math.PI / 2)) + b;
        },
        easeInOutSine: function (x, t, b, c, d) {
            return -c / 2 * (Math.cos(Math.PI * t / d) - 1) + b;
        },
        easeInExpo: function (x, t, b, c, d) {
            return (t == 0) ? b : c * Math.pow(2, 10 * (t / d - 1)) + b;
        },
        easeOutExpo: function (x, t, b, c, d) {
            return (t == d) ? b + c : c * (-Math.pow(2, -10 * t / d) + 1) + b;
        },
        easeInOutExpo: function (x, t, b, c, d) {
            if (t == 0) return b;
            if (t == d) return b + c;
            if ((t /= d / 2) < 1) return c / 2 * Math.pow(2, 10 * (t - 1)) + b;
            return c / 2 * (-Math.pow(2, -10 * --t) + 2) + b;
        },
        easeInCirc: function (x, t, b, c, d) {
            return -c * (Math.sqrt(1 - (t /= d) * t) - 1) + b;
        },
        easeOutCirc: function (x, t, b, c, d) {
            return c * Math.sqrt(1 - (t = t / d - 1) * t) + b;
        },
        easeInOutCirc: function (x, t, b, c, d) {
            if ((t /= d / 2) < 1) return -c / 2 * (Math.sqrt(1 - t * t) - 1) + b;
            return c / 2 * (Math.sqrt(1 - (t -= 2) * t) + 1) + b;
        },
        easeInElastic: function (x, t, b, c, d) {
            var s = 1.70158;
            var p = 0;
            var a = c;
            if (t == 0) return b;
            if ((t /= d) == 1) return b + c;
            if (!p) p = d * .3;
            if (a < Math.abs(c)) {
                a = c;
                var s = p / 4;
            }
            else var s = p / (2 * Math.PI) * Math.asin(c / a);
            return -(a * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b;
        },
        easeOutElastic: function (x, t, b, c, d) {
            var s = 1.70158;
            var p = 0;
            var a = c;
            if (t == 0) return b;
            if ((t /= d) == 1) return b + c;
            if (!p) p = d * .3;
            if (a < Math.abs(c)) {
                a = c;
                var s = p / 4;
            }
            else var s = p / (2 * Math.PI) * Math.asin(c / a);
            return a * Math.pow(2, -10 * t) * Math.sin((t * d - s) * (2 * Math.PI) / p) + c + b;
        },
        easeInOutElastic: function (x, t, b, c, d) {
            var s = 1.70158;
            var p = 0;
            var a = c;
            if (t == 0) return b;
            if ((t /= d / 2) == 2) return b + c;
            if (!p) p = d * (.3 * 1.5);
            if (a < Math.abs(c)) {
                a = c;
                var s = p / 4;
            }
            else var s = p / (2 * Math.PI) * Math.asin(c / a);
            if (t < 1) return -.5 * (a * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b;
            return a * Math.pow(2, -10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p) * .5 + c + b;
        },
        easeInBack: function (x, t, b, c, d, s) {
            if (s == undefined) s = 1.70158;
            return c * (t /= d) * t * ((s + 1) * t - s) + b;
        },
        easeOutBack: function (x, t, b, c, d, s) {
            if (s == undefined) s = 1.70158;
            return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
        },
        easeInOutBack: function (x, t, b, c, d, s) {
            if (s == undefined) s = 1.70158;
            if ((t /= d / 2) < 1) return c / 2 * (t * t * (((s *= (1.525)) + 1) * t - s)) + b;
            return c / 2 * ((t -= 2) * t * (((s *= (1.525)) + 1) * t + s) + 2) + b;
        },
        easeInBounce: function (x, t, b, c, d) {
            return c - jQuery.easing.easeOutBounce(x, d - t, 0, c, d) + b;
        },
        easeOutBounce: function (x, t, b, c, d) {
            if ((t /= d) < (1 / 2.75)) {
                return c * (7.5625 * t * t) + b;
            } else if (t < (2 / 2.75)) {
                return c * (7.5625 * (t -= (1.5 / 2.75)) * t + .75) + b;
            } else if (t < (2.5 / 2.75)) {
                return c * (7.5625 * (t -= (2.25 / 2.75)) * t + .9375) + b;
            } else {
                return c * (7.5625 * (t -= (2.625 / 2.75)) * t + .984375) + b;
            }
        },
        easeInOutBounce: function (x, t, b, c, d) {
            if (t < d / 2) return jQuery.easing.easeInBounce(x, t * 2, 0, c, d) * .5 + b;
            return jQuery.easing.easeOutBounce(x, t * 2 - d, 0, c, d) * .5 + c * .5 + b;
        }
    });


(function (u) {
    u.cjTextFx = function (L) {
        function M(s, r) {
            if (v.length)
                if (s)
                    for (; v.length;) A(v[0]), v.shift();
                else
                    for (var u = r.length, w; u--;)
                        for (w = v.length; w--;)
                            if (r[u] === v[w].object) {
                                A(v[w]);
                                v.splice(w, 1);
                                break
                            }
        }

        function A(s) {
            clearTimeout(s.start);
            clearInterval(s.timer);
            for (var r = s.array; r.length;) u(r[0]).stop(!0).removeData("callback").removeData("isOut"), r.shift();
            r = u(s.object);
            r.unbind("click", s.linkHandler);
            s.restore && (r.empty(), r.html(s.html));
            r.css("visibility", "hidden")
        }

        var v = [];
        u.cjTextFx.animate = function (s, r) {
            function A(a) {
                for (var b in a) a.hasOwnProperty(b) && (p[b] = a[b]);
                w()
            }

            function w() {
                F = "in" === p.animationType.toLowerCase() ? !0 : !1;
                h = p.onComplete;
                G = p.onStart;
                N = p.onCompleteParams;
                O = p.onStartParams;
                P = p.linkTarget;
                H = p.hyperlink;
                k = p.sequenceDelay;
                I = p.startDelay;
                n = p.offsetX;
                j = p.offsetY;
                x = p.animation;
                d = p.easing;
                e = p.speed
            }

            function U() {
                var d = p.backwards,
                    e = q;
                if (11 === x || 12 === x || 14 === x) {
                    var g = [],
                        h;
                    for (h = 0; h < q; h++) g[h] = b[h];
                    for (b = []; 0 < g.length;) h = Math.random() * g.length | 0, b[b.length] = g[h], g.splice(h, 1)
                }
                for (; e--;) f[e] = b[e].offset().left - Q;
                if (F) switch (x) {
                    case 1:
                        d ? (a = l, c = setInterval(V, k)) : c = setInterval(W, k);
                        break;
                    case 2:
                        d ? (a = l, c = setInterval(X, k)) : c = setInterval(Y, k);
                        break;
                    case 3:
                        d ? (a = l, c = setInterval(Z, k)) : c = setInterval($, k);
                        break;
                    case 4:
                        d ? (a = l, c = setInterval(aa, k)) : c = setInterval(ba, k);
                        break;
                    case 5:
                        d ? (a = l, c = setInterval(ca, k)) : c = setInterval(da, k);
                        break;
                    case 6:
                        d ? (a = l, c = setInterval(ea, k)) : c = setInterval(fa, k);
                        break;
                    case 7:
                        d ? (a = l, c = setInterval(ga, k)) : c = setInterval(ha, k);
                        break;
                    case 8:
                        d ? (a = l, c = setInterval(ia, k)) : c = setInterval(ja, k);
                        break;
                    case 9:
                        d ? (a = l, c = setInterval(ka, k)) : c = setInterval(la, k);
                        break;
                    case 10:
                        d ? (a = l, c = setInterval(ma, k)) : c = setInterval(na, k);
                        break;
                    case 11:
                        c = setInterval(oa, k);
                        break;
                    case 12:
                        for (e = q; e--;) 0 === e % 2 ? b[e].css({
                            position: "absolute",
                            left: f[e] + Math.random() * n | 0,
                            top: Math.random() * -j | 0
                        }) : b[e].css({
                            position: "absolute",
                            left: f[e] + Math.random() * -n | 0,
                            top: Math.random() * j | 0
                        });
                        c = setInterval(pa, k);
                        break;
                    case 13:
                        d ? (a = l, c = setInterval(qa, k)) : c = setInterval(R, k);
                        break;
                    case 14:
                        c = setInterval(R, k);
                        break;
                    case 15:
                        d ? (a = l, c = setInterval(ra, k)) : c = setInterval(sa, k)
                } else {
                    for (e = q; e--;) b[e].css({
                        position: "absolute",
                        left: f[e],
                        top: 0
                    });
                    switch (x) {
                        case 1:
                            d ? (a = l, c = setInterval(ta, k)) : c = setInterval(ua, k);
                            break;
                        case 2:
                            d ? (a = l, c = setInterval(va, k)) : c = setInterval(wa, k);
                            break;
                        case 3:
                            d ? (a = l, c = setInterval(xa, k)) : c = setInterval(ya, k);
                            break;
                        case 4:
                            d ? (a = l, c = setInterval(za, k)) : c = setInterval(Aa, k);
                            break;
                        case 5:
                            d ? (a = l, c = setInterval(Ba, k)) : c = setInterval(Ca, k);
                            break;
                        case 6:
                            d ? (a = l, c = setInterval(Da, k)) : c = setInterval(Ea, k);
                            break;
                        case 7:
                            d ? (a = l, c = setInterval(Fa, k)) : c = setInterval(Ga, k);
                            break;
                        case 8:
                            d ? (a = l, c = setInterval(Ha, k)) : c = setInterval(Ia, k);
                            break;
                        case 9:
                            d ? (a = l, c = setInterval(Ja, k)) : c = setInterval(Ka, k);
                            break;
                        case 10:
                            d ? (a = l, c = setInterval(La, k)) : c = setInterval(Ma, k);
                            break;
                        case 11:
                            c = setInterval(Na, k);
                            break;
                        case 12:
                            c = setInterval(Oa, k);
                            break;
                        case 13:
                            d ? (a = l, c = setInterval(S, k)) : c = setInterval(Pa, k);
                            break;
                        case 14:
                            a = l;
                            c = setInterval(S, k);
                            break;
                        case 15:
                            d ? (a = l, c = setInterval(Qa, k)) : c = setInterval(Ra, k)
                    }
                }
                G && (C = setTimeout(Sa, k));
                for (e = v.length; e--;)
                    if (v[e].id === D) {
                        v[e].timer = c;
                        v[e].start = C;
                        break
                    }
                D = p = null
            }

            function T() {
                "_parent" === P ? window.location = H : window.open(H)
            }

            function Sa() {
                G(O)
            }

            function m() {
                h(N)
            }

            function g() {
                J && u(this).data("isOut") && u(this).get(0).style.removeAttribute("filter");
                u(this).data("callback") && u(this).data("callback")()
            }

            function W() {
                a < q ? (h && a === l && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a] + n,
                    top: 0,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function V() {
                -1 < a ? (h && 0 === a && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a] - n,
                    top: 0,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function ta() {
                -1 < a ? (h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    left: f[a] + n,
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function ua() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    left: f[a] - n,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function Y() {
                a < q ? (h && a === l && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: -j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function X() {
                -1 < a ? (h && 0 === a && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: -j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function va() {
                -1 < a ? (h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    top: -j,
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function wa() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    top: -j,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function $() {
                a < q ? (h && a === l && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function Z() {
                -1 < a ? (h && 0 === a && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function xa() {
                -1 < a ? (h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    top: j,
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function ya() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    top: j,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function ba() {
                a < q ? (h && a === l && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a] + n,
                    top: -j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    left: f[a],
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function aa() {
                -1 < a ? (h && 0 === a && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a] + n,
                    top: -j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    left: f[a],
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function za() {
                -1 < a ? (h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    left: f[a] + n,
                    top: -j,
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function Aa() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    left: f[a] + n,
                    top: -j,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function da() {
                h && a === l && b[a].data("callback", m);
                a < q ? (b[a].css({
                    position: "absolute",
                    left: f[a] + n,
                    top: j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    left: f[a],
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function ca() {
                h && 0 === a && b[a].data("callback", m);
                -1 < a ? (b[a].css({
                    position: "absolute",
                    left: f[a] + n,
                    top: j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    left: f[a],
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function Ba() {
                h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                });
                -1 < a ? (b[a].animate({
                    left: f[a] + n,
                    top: j,
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function Ca() {
                h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                });
                a < q ? (b[a].animate({
                    left: f[a] + n,
                    top: j,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function fa() {
                a < q ? (h && a === l && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a] - n,
                    top: -j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    left: f[a],
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function ea() {
                -1 < a ? (h && 0 === a && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a] - n,
                    top: -j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    left: f[a],
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function Da() {
                -1 < a ? (h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    left: f[a] - n,
                    top: -j,
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function Ea() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    left: f[a] - n,
                    top: -j,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function ha() {
                h && a === l && b[a].data("callback", m);
                a < q ? (b[a].css({
                    position: "absolute",
                    left: f[a] - n,
                    top: j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    left: f[a],
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function ga() {
                h && 0 === a && b[a].data("callback", m);
                -1 < a ? (b[a].css({
                    position: "absolute",
                    left: f[a] - n,
                    top: j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    left: f[a],
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function Fa() {
                h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                });
                -1 < a ? (b[a].animate({
                    left: f[a] - n,
                    top: j,
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function Ga() {
                h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                });
                a < q ? (b[a].animate({
                    left: f[a] - n,
                    top: j,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function ja() {
                a < q ? (h && a === l && b[a].data("callback", m), 0 === a % 2 ? b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    opacity: 1
                }, e, d, g) : b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: -j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function ia() {
                -1 < a ? (h && 0 === a && b[a].data("callback", m), 0 === a % 2 ? b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    opacity: 1
                }, e, d, g) : b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: -j,
                    visibility: "visible"
                }).animate({
                    top: 0,
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function Ha() {
                -1 < a ? (h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                }), 0 === a % 2 ? b[a].animate({
                    top: j,
                    opacity: 0
                }, e, d, g) : b[a].animate({
                    top: -j,
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function Ia() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), 0 === a % 2 ? b[a].animate({
                    top: j,
                    opacity: 0
                }, e, d, g) : b[a].animate({
                    top: -j,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function la() {
                a < q ? (h && a === l && b[a].data("callback", m), 0 === a % 2 ? b[a].css({
                    position: "absolute",
                    left: f[a] + n,
                    top: j,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    top: 0,
                    opacity: 1
                }, e, d, g) : b[a].css({
                    position: "absolute",
                    left: f[a] + n,
                    top: -j,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    top: 0,
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function ka() {
                -1 < a ? (h && 0 === a && b[a].data("callback", m), 0 === a % 2 ? b[a].css({
                    position: "absolute",
                    left: f[a] + n,
                    top: j,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    top: 0,
                    opacity: 1
                }, e, d, g) : b[a].css({
                    position: "absolute",
                    left: f[a] + n,
                    top: -j,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    top: 0,
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function Ja() {
                -1 < a ? (h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                }), 0 === a % 2 ? b[a].animate({
                    left: f[a] + n,
                    top: j,
                    opacity: 0
                }, e, d, g) : b[a].animate({
                    left: f[a] + n,
                    top: -j,
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function Ka() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), 0 === a % 2 ? b[a].animate({
                    left: f[a] + n,
                    top: j,
                    opacity: 0
                }, e, d, g) : b[a].animate({
                    left: f[a] + n,
                    top: -j,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function na() {
                a < q ? (h && a === l && b[a].data("callback", m), 0 === a % 2 ? b[a].css({
                    position: "absolute",
                    left: f[a] - n,
                    top: j,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    top: 0,
                    opacity: 1
                }, e, d, g) : b[a].css({
                    position: "absolute",
                    left: f[a] - n,
                    top: -j,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    top: 0,
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function ma() {
                -1 < a ? (h && 0 === a && b[a].data("callback", m), 0 === a % 2 ? b[a].css({
                    position: "absolute",
                    left: f[a] - n,
                    top: j,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    top: 0,
                    opacity: 1
                }, e, d, g) : b[a].css({
                    position: "absolute",
                    left: f[a] - n,
                    top: -j,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    top: 0,
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function La() {
                -1 < a ? (h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                }), 0 === a % 2 ? b[a].animate({
                    left: f[a] - n,
                    top: j,
                    opacity: 0
                }, e, d, g) : b[a].animate({
                    left: f[a] - n,
                    top: -j,
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function Ma() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), 0 === a % 2 ? b[a].animate({
                    left: f[a] - n,
                    top: j,
                    opacity: 0
                }, e, d, g) : b[a].animate({
                    left: f[a] - n,
                    top: -j,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function oa() {
                a < q ? (h && a === l && b[a].data("callback", m), 0 === a % 2 ? b[a].css({
                    position: "absolute",
                    left: f[a] + Math.random() * n | 0,
                    top: Math.random() * -j | 0,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    top: 0,
                    opacity: 1
                }, e, d, g) : b[a].css({
                    position: "absolute",
                    left: f[a] + Math.random() * -n | 0,
                    top: Math.random() * j | 0,
                    visibility: "visible"
                }).animate({
                    left: f[a],
                    top: 0,
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function Na() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), 0 === a % 2 ? b[a].animate({
                    left: f[a] + Math.random() * n | 0,
                    top: Math.random() * -j | 0,
                    opacity: 0
                }, e, d, g) : b[a].animate({
                    left: f[a] + Math.random() * -n | 0,
                    top: Math.random() * j | 0,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function pa() {
                a < q ? (h && a === l && b[a].data("callback", m), b[a].animate({
                    left: f[a],
                    top: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function Oa() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), 0 === a % 2 ? b[a].animate({
                    left: f[a] + Math.random() * n | 0,
                    top: Math.random() * -j | 0
                }, e, d, g) : b[a].animate({
                    left: f[a] + Math.random() * -n | 0,
                    top: Math.random() * j | 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function R() {
                a < q ? (h && a === l && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: 0,
                    visibility: "visible"
                }).animate({
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function qa() {
                -1 < a ? (h && 0 === a && b[a].data("callback", m), b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: 0,
                    visibility: "visible"
                }).animate({
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function S() {
                -1 < a ? (h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function Pa() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            function sa() {
                a < q ? (h && a === l && b[a].data("callback", m), E = b[a].height(), b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: 0,
                    height: 0,
                    visibility: "visible"
                }).animate({
                    height: E,
                    opacity: 1
                }, e, d, g), a++) : clearInterval(c)
            }

            function ra() {
                -1 < a ? (h && 0 === a && b[a].data("callback", m), E = b[a].height(), b[a].css({
                    position: "absolute",
                    left: f[a],
                    top: 0,
                    height: 0,
                    visibility: "visible"
                }).animate({
                    height: E,
                    opacity: 1
                }, e, d, g), a--) : clearInterval(c)
            }

            function Qa() {
                -1 < a ? (h && 0 === a && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    height: 0,
                    opacity: 0
                }, e, d, g), a--) : clearInterval(c)
            }

            function Ra() {
                a < q ? (h && a === l && b[a].data({
                    callback: m,
                    isOut: !0
                }), b[a].animate({
                    height: 0,
                    opacity: 0
                }, e, d, g), a++) : clearInterval(c)
            }

            var N, O, h, P, H, t, F, Q, n, j, C, G, d, k, I, e, x, a, K, B, E, c, l, f, D, q, y, b, z, p = {};
            t = navigator.userAgent.toLowerCase();
            var J = -1 !== t.search("msie");
            J && (J = 9 > parseFloat(t.substr(t.indexOf("msie") + 4)));

            p.animation = 1;
            p.animationType = "in";
            p.backwards = !1;
            p.easing = "easeOutQuint";
            p.sequenceDelay = 100;
            p.startDelay = 0;
            p.speed = 1E3;
            p.offsetX = 100;
            p.offsetY = 50;
            p.color = "#000";
            p.linked = !1;
            p.hyperlink = null;
            p.linkTarget = null;
            p.onComplete = null;
            p.onStart = null;
            p.onCompleteParams = null;
            p.onStartParams = null;
            p.restoreHTML = !0;
            w();
            L && A(L);
            r && A(r);
            t = u(s);
            if (t.length && !(1 < t.length)) {
                M(!1, [s]);
                Q = t.offset().left | 0;
                f = [];
                b = [];
                a = 0;
                p.linked && t.css("cursor", "pointer").click(T);
                t.css({
                    color: p.color,
                    height: t.height(),
                    visibility: "visible",
                    display: "block"
                });
                K = t.html();
                y = K.replace(/<\/?[^>]+>/igm, "");
                y = y.replace(/^\s+|\s+$/g, "");
                t.empty();
                q = y.length;
                l = q - 1;
                for (z = 0; z < q; z++) B = y.charAt(z), " " === B && (B = "&nbsp;"), span = u("<span />").html(B).appendTo(t), 12 !== x && F && span.css({
                    opacity: 0,
                    visibility: "hidden"
                }), b[z] = span;
                C = setTimeout(U, 0 === I ? 100 : I);
                D = Math.random();
                v[v.length] = {
                    object: s,
                    id: D,
                    html: K,
                    start: C,
                    array: b,
                    linkHandler: T,
                    restore: p.restoreHTML
                };
                z = y = B = t = null
            }
        };
        u.cjTextFx.remove = function (s, r) {
            M(s, r)
        }
    }
})(jQuery);


(function ($) {
    "use strict";
    var isOn = 0, sets, fx, toAnimate = "#effect", settings = {
        animation: 8,
        animationType: "in",
        backwards: false,
        easing: "easeOutQuint",
        speed: 1000,
        sequenceDelay: 100,
        startDelay: 0,
        offsetX: 200,
        offsetY: 100,
        onComplete: fireThis,
        restoreHTML: true
    };

    jQuery(document).ready(function () {
        fx = jQuery("#effect");
        jQuery.cjTextFx(settings);
        jQuery.cjTextFx.animate(toAnimate);
    });

    function fireThis() {
        if (isOn === 9) return;
        (isOn < 9) ? isOn++ : isOn = 0;
        // console.log(isOn);
        switch (isOn) {
            case 1:
                sets = {animation: 8, animationType: "out", restoreHTML: false};
                break;

            case 2:
                fx.html("产业立市");
                sets = {animation: 11};
                break;

            case 3:
                sets = {animation: 11, animationType: "out", restoreHTML: false};
                break;

            case 4:
                fx.html("成就企业家");
                sets = {animation: 1};
                break;

            case 5:
                sets = {animation: 1, animationType: "out", restoreHTML: false};
                break;

            case 6:
                fx.html("厚待投资者");
                sets = {animation: 6, backwards: true};
                break;

            case 7:
                sets = {animation: 4, animationType: "out", backwards: true, restoreHTML: false};
                break;

            case 8:
                fx.html("开放强市");
                sets = {animation: 2, easing: "easeOutBounce"};
                break;

            /!*case 10:
                fx.html("文字效果6");
                sets = {animation: 14, startDelay: 1000, easing: "easeInBack", restoreHTML: false};
            break;

            case 11:
                sets = {animation: 6, animationType: "out", speed: 500, easing: "easeInBack", restoreHTML: false};
            break;*!/


            default :
                sets = {animation: 2, animationType: "out", speed: 500, easing: "easeInBack", restoreHTML: false};
                isOn = 1;//在这设定返回从头开始
                break;


        }
        jQuery.cjTextFx.animate(toAnimate, sets);
    }

})(jQuery);*/



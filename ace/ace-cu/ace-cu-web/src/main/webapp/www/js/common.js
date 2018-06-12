var urlContent = "http://zx.huacainfo.com/";
var html = [];
html.push("<div class=\"login_box\">");
html.push("<div class=\"login\">");
try {
    if (userProp) {
        html.push("<span class=\"companyName\" title='" + userProp.name + "' ng-if=\"userProp\"><a href=\"/fop/www/html/member/member.html\" target=\"_blank\">" + userProp.name + "</a></span>");
        html.push("<span class=\"userNav\" ng-if=\"userProp\"><a href=\"/portal/dynamic/portal/security/loginOut_fop.jsp\">退出</a></span>");
    } else {
        html.push("<span class=\"userNav\" ng-if=\"!userProp\"><a href=\"/portal/dynamic/portal/security/login_fop.jsp\">登录</a></span>");
    }
} catch (e) {
    html.push("<span class=\"userNav\" ng-if=\"!userProp\"><a href=\"/portal/dynamic/portal/security/login_fop.jsp\">登录</a></span>");
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
html.push("<div class=\"words_1\"><img class='anim_fade_image' src=\"/fop/www/images/words1.png\" /></div>");
html.push("<div class=\"words_2\"><img class='anim_fade_image' src=\"/fop/www/images/words2.png\" /></div>");
html.push("</div>");
html.push("</div>");
html.push("<div class=\"header-nav\">");
html.push("<div class=\"contain\">");
html.push("<ul class=\"nav-list\">");
html.push("<li>");
html.push("<a href=\"/fop/www/index.html\">首页<span class=\"trig\"></span></a>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"/fop/www/html/information/information_index.html\" target=\"_blank\">市情要闻<span class=\"trig\"></span></a>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"#\">领导之窗<span class=\"trig\"></span></a>");
html.push("<ul>");
html.push("<li><a href=\"/fop/www/html/leader/shiwei_index.html\" target=\"_blank\">常德市委</a></li>");
html.push("<li><a href=\"/fop/www/html/leader/zhengfu_index.html\" target=\"_blank\">常德市政府</a></li>");
html.push("<li><a href=\"/fop/www/html/leader/gongshanglian_index.html\" target=\"_blank\">常德市工商联</a></li>");
html.push("</ul>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"#\">信息服务<span class=\"trig\"></span></a>");
html.push("<ul>");
html.push("<li><a href=\"/fop/www/html/policy/policy.html\" target=\"_blank\">政策文件</a></li>");
html.push("<li><a href=\"/fop/www/html/investment/investment.html\" target=\"_blank\">招商信息</a></li>");
html.push("<li><a href=\"/fop/www/html/personnel/personnel.html\" target=\"_blank\">人才信息</a></li>");
html.push("<li><a href=\"/fop/www/html/cooperation/cooperation_index.html\" target=\"_blank\">项目信息</a></li>");
html.push("<li><a href=\"/fop/www/html/finance/product.html\" target=\"_blank\">金融产品</a></li>");
html.push("<li><a href=\"/fop/www/html/product/product.html\" target=\"_blank\">企业产品</a></li>");
html.push("<li><a href=\"/fop/www/html/corporateStyle/costyle.html\" target=\"_blank\">企业风采</a></li>");
html.push("<li><a href=\"/fop/www/html/sincerity/sincerity.html\">诚信公示</a></li>");
html.push("</ul>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"/fop/www/html/band/band.html\" target=\"_blank\">品牌推广<span class=\"trig\"></span></a>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"#\">在线服务<span class=\"trig\"></span></a>");
html.push("<ul>");
html.push("<li><a href=\"/fop/www/html/gov_service/gov_service1.html\" target=\"_blank\">政企服务</a></li>");
html.push("<li><a href=\"/fop/www/html/finance/finance.html\" target=\"_blank\">银企服务</a></li>");
html.push("<li><a href=\"/fop/www/html/law/lawer_index.html\" target=\"_blank\">法务服务</a></li>");
html.push("</ul>");
html.push("</li>");
html.push("<li>");
html.push("<a href=\"#\">留言箱<span class=\"trig\"></span></a>");
html.push("<ul>");
html.push("<li><a href=\"/fop/www/html/satisfy/satisfy.html\" target=\"_blank\">满意度调查</a></li>");
html.push("</ul>");
html.push("</li>");
html.push("</ul>");
html.push("</div>");
html.push("<div class=\"second-bg\"></div>");
html.push("</div>");
var text = html.join("\n");

$(function () {
    $("#header").append(text);

    $('.nav-list>li').hover(function () {
        var $ul = $(this).find('ul');
        var oW = $(this).width();//li
        var otrigW = $(this).find('.trig').width();
        var oNavListL = $('.nav-list').offset().left;
        var oTL = $(this).offset().left - oNavListL;//距离最左边的距离
        var oTR = $('.nav-list').width() - oTL - oW;//距离最右边的距离
        console.log(oNavListL + ":" + oTL);

        if ($ul.find('li').length > 0) {
            $('.second-bg').show();
            $(this).find('.trig').show();
            $ul.show();
            var sum = 0;
            var oLeft = 0;
            for (var i = 0; i < $ul.find('li').length; i++) {
                sum += $ul.find('li').eq(i).width() + 4;
            }
            $ul.width(sum);
            oLeft = (sum - oW) / 2;
            if (oLeft > oTL) {//到达左侧边界
                oLeft = oTL;
                $ul.css('left', -oLeft + 'px');
                return;
            }
            if (oLeft > oTR) {
                $ul.css('right', -oTR + 'px');
                return;
            }
            $ul.css('left', -oLeft + 'px');

        }
    }, function () {
        $('.second-bg').hide();
        $(this).find('ul').hide();
        $(this).find('.trig').hide();
    });

    // change();
    // $(window).resize(function(){  //窗口大小改变时进行改变
    //     change();
    // })
});

// /**
//  * 底部绝对定位，当页面的高度撑开页面时，去掉绝对定位
//  */
// function change() {
//     var $body = $('body');
//     var $footer = $('.footer');
//
//     var bodyHeight = $body.height();  //网页可见元素的高度
//
//     var allHeight = bodyHeight;
//
//     var isAbsExist = false;
//
//     if($footer.hasClass('abs-bottom')){  //如果尾部存在绝对定位,网页总高度要加上尾部的高度
//         isAbsExist = true;
//         allHeight += $footer.height();
//     }
//
//     if(getWinHeight() < allHeight){  //窗口高度小于网页总高度时
//         if(isAbsExist){
//             $('.footer').removeClass("abs-bottom");
//         }
//     }else {  //当窗口高度大于网页总高度时
//         if(!isAbsExist){
//             $('.footer').addClass("abs-bottom");
//         }
//     }
// }
// function getWinHeight(){ //获取窗口高度的函数。
//     var e = window,
//         a = 'inner';
//     if (!('innerWidth' in window )){
//         a = 'client';
//         e = document.documentElement || document.body;
//     }
//     return e[ a+'Height'];
// }

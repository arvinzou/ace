
var html=[];
html.push("<div class=\"login_box\">");
html.push("<div class=\"login\">");
try{
    if(userProp){
        html.push("<span ng-if=\"userProp\"><a href=\"/fop/www/html/member/member.html\" target=\"_blank\">"+userProp.name+"</a></span>");
        html.push("<span ng-if=\"userProp\"><a href=\"/portal/dynamic/portal/security/loginOut.jsp\">退出</a></span>");
    }else{
        html.push("<span ng-if=\"!userProp\"><a href=\"/portal/dynamic/portal/security/login.jsp\">登录</a></span>");
    }
}catch(e){
    html.push("<span ng-if=\"!userProp\"><a href=\"/portal/dynamic/portal/security/login.jsp\">登录</a></span>");
}

html.push("<span>|</span>");
html.push("<span><a href=\"/fop/www/html/login/regist.html\">注册</a></span>");
html.push("</div>");
html.push("</div>");
html.push("<div class=\"logo_box\">");
html.push("<div class=\"logo\">");
html.push("<div class=\"logo_image\"><img src=\"/fop/www/images/logo.png\" /></div>");
html.push("<div class=\"logo_word\">承办单位：常德市工商业联合会</div>");
html.push("</div>");
html.push("<div class=\"words\">");
html.push("<div class=\"words_1\"><img class='anim_fade_image' src=\"/fop/www/images/words1.png\" /></div>");
html.push("<div class=\"words_2\"><img class='anim_fade_image' src=\"/fop/www/images/words2.png\" /></div>");
html.push("</div>");
html.push("</div>");
var text=html.join("\n");

$(function(){
    $("#header").append(text);
});

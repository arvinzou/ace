var urlContent = "http://zx.huacainfo.com/";
var html=[];
html.push("<div class=\"login_box\">");
html.push("<div class=\"login\">");
try{
    if(userProp){
        html.push("<span class=\"companyName\"  ng-if=\"userProp\"><a href=\"/fop/www/html/member/member.html\" title='"+userProp.name+"' target=\"_blank\">"+userProp.name+"</a><i title='修改密码' style='padding-left: 10px;' id='editUserInfo' class='glyphicon glyphicon-edit'></i></span>");
        html.push("<span class=\"userNav\" ng-if=\"userProp\"><a href=\"/portal/dynamic/portal/security/loginOut_fop.jsp\">退出</a></span>");
    }else{
        html.push("<span class=\"userNav\" ng-if=\"!userProp\"><a href=\"/portal/dynamic/portal/security/login_fop.jsp\">登录</a></span>");
    }
}catch(e){
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
html.push("<div class=\"words_1\"><p><marquee style='color: red' scrollamount=5>开放强市&nbsp;产业立市&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成就企业家&nbsp;厚待投资者</marquee></p></div>");
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
        console.log(oNavListL+":"+oTL);

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



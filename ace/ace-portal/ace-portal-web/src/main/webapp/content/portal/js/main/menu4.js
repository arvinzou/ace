var loading=getBusyOverlay('viewport',{color:'#eff3f8', opacity:0.75, text:'viewport: loading...', style:'font-size:14px;color:#000000'},{color:'#00000', size:80, type:'o'});
if(loading){
      loading.remove();
}

var buildMenu = function(menus) {
	var buildMenuHtml = function(menus) {
		var html = [];
		var num = 0;

		$.each(menus,function(i, menu) {


		                    if(urlParams.id){
		                        if(urlParams.id==menu.id){
                                    html.push('<li class="menu-dropdown mega-menu-dropdown active" aria-haspopup="true">');
                                }else{
                                    html.push('<li class="menu-dropdown mega-menu-dropdown" aria-haspopup="true">');
                                }
		                    }else{
		                        if(i==0){
		                            html.push('<li class="menu-dropdown mega-menu-dropdown active" aria-haspopup="true">');
		                        }else{
		                            html.push('<li class="menu-dropdown mega-menu-dropdown" aria-haspopup="true">');
		                        }
		                    }
							if (menu.leaf != true && menu.leaf != 'true') {
								html.push('<a class="nav-link nav-toggle" href="#" url="'+menu.href+'"><i class=""></i>'+ menu.text + '<span class="arrow"></span>');
							}else{
							    html.push('<a class="nav-link" href="#" url="'+menu.href+'"><i class=""></i>'+ menu.text + '<span class="arrow"></span>');
							}
							html.push('</a>');
							//html.push('<b class="arrow"></b>');
							var initSubMenu = function(menu) {
								if (menu.leaf != true && menu.leaf != 'true') {
									var childrens = menu.children, len = childrens.length;
									html.push('<ul class="dropdown-menu">');
									for (var i = 0; i < len; i++) {
										if (childrens[i].href) {
										    html.push('<li class="" aria-haspopup="true">');
											html.push('<a  class="nav-link" href="#" title="'
															+ childrens[i].text
															+ '" url="'
															+ childrens[i].href
															+ '" ><i class=""></i>'
															+ childrens[i].text
															+ '<span class="arrow"></span></a>');
										} else {
										    html.push('<li class="dropdown-submenu" aria-haspopup="true">');
											html.push('<a class="nav-toggle" href="#"><i class=""></i>'
															+ childrens[i].text
															+ '<span class="arrow"></span></a>');
										}
										initSubMenu(childrens[i]);
										html.push('</li>');

									}
									html.push('</ul>');
								}
							}
							initSubMenu(menu);
							html.push('</li>');
						});
		return html.join('');

	};

	var htmlFrame = buildMenuHtml(menus);
	$('#menu').empty().append(htmlFrame);
	$('#menu a[url]').bind('click', function() {

		var url = $(this).attr("url");
		$('#menu a[url]').parent('li').removeClass("active")
		$(this).parent('li').addClass("active");
		if (url) {
			if(url.indexOf("/")!=-1){
				location.href=url;
			}else{
				//工作流
				var key=url.split('?')[0];
				var name=$(this).attr("title");
				addWorkflow(key, name)
			}
		}
	});

}


jQuery(function($) {
    if(sessionStorage&&sessionStorage.getItem("html_nav")){
        var data=sessionStorage.getItem("html_nav");
         $("body .page-wrapper div:first").before(data);
         initMenu();
         initBottom();
     }else{
        $.ajax({
            url : portalPath + '/dynamic/common/tpl/html_nav.jsp',
            type : 'POST',
            timeout : 30000,
            dataType : 'text',
            success : function(data) {
               $("body .page-wrapper div:first").before(data);
               initMenu();
               initBottom();
               if(sessionStorage){
                   console.log("sessionStorage");
                   sessionStorage.setItem("html_nav",data);
               }
            }
        });
    }

});
function initBottom(){
    if(sessionStorage&&sessionStorage.getItem("html_bottom")){
        var data=sessionStorage.getItem("html_bottom");
         $(".bottom").before(data);
     }else{
        $.ajax({
                 url : portalPath + '/dynamic/common/tpl/html_bottom.jsp',
                 type : 'POST',
                 timeout : 30000,
                 dataType : 'text',
                 success : function(data) {
                    $(".bottom").before(data);
                    if(sessionStorage){
                       console.log("sessionStorage");
                       sessionStorage.setItem("html_bottom",data);
                    }
                 }
             });
     }

}
function initMenu(){
    if(sessionStorage&&sessionStorage.getItem("menu")){
        var srt=sessionStorage.getItem("menu");
        var data=JSON.parse(srt);
        console.log(data);
        buildMenu(data);
     }else{
        $.ajax({
                    url : portalPath + '/system/getTreeList.do?loadButton=false&client=c',
                    type : 'POST',
                    timeout : 30000,
                    dataType : 'json',
                    beforeSend:function(){
                        if(loading) {
                           loading.settext("正在加载，请稍后......");
                        }

                    },
                    success : function(data) {
                        buildMenu(data);
                        if(loading){
                            loading.remove();
                        }
                        if(sessionStorage){
                            console.log("sessionStorage");
                            sessionStorage.setItem("menu",JSON.stringify(data));
                        }
                    }
                });
     }

}
function modifyPasswd() {
	var dialog = $("#dialog-message").removeClass('hide').dialog({
		resizable : false,
		modal : false,
		title : "密码修改",
		buttons : [{
			html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
			"class" : "btn btn-info btn-xs",
			click : function() {
				if ($('#password').val() == '') {
					alert("请输入密码！");
					return;
				}
				if ($('#password').val() != $('#repassword').val()) {
					alert("两次输入的密码不一致！");
					return;
				}
				if (confirm("确定要修改吗？")) {
					$.ajax({
						type : "post",
						url : portalPath + "/system/updatePassword.do",
						data : {
							password : $('#password').val(),
							repassword : $('#repassword').val()
						},
						beforeSend : function(XMLHttpRequest) {

						},
						success : function(rst, textStatus) {
							if (rst.state) {
								alert(rst.errorMessage);
								dialog.dialog("close");
							} else {
								alert(rst.errorMessage);
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {

						}
					});
				}
			}
		}, {
			html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
			"class" : "btn btn-xs",
			click : function() {
				$(this).dialog("close");
			}
		}]
	});
}

function userCfg() {
	addPanel('个性化配置', portalPath + '/dynamic/service/userCfg/index.jsp', true);
}
function submitform() {
	if ($('#password').val() == '') {
		alert("请输入密码！");
		return;
	}
	if ($('#password').val() != $('#repassword').val()) {
		alert("两次输入的密码不一致！");
		return;
	}
	if (confirm("确定要修改吗？")) {
		$.ajax({
			type : "post",
			url : portalPath + "/system/updatePassword.do",
			data : {
				password : $('#password').val(),
				repassword : $('#repassword').val()
			},
			beforeSend : function(XMLHttpRequest) {

			},
			success : function(rst, textStatus) {
				if (rst.state) {
					alert(rst.errorMessage);
					$('#stack1').modal('hide')
				} else {
					alert(rst.errorMessage);
				}
			},
			complete : function(XMLHttpRequest, textStatus) {

			},
			error : function() {

			}
		});
	}
}


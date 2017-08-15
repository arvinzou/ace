jQuery(function($) {
    initPhoto();
    $('#btn-search').on('click', function() {
        $.ajax({
                type : "post",
                url : contextPath + "/wxUser/selectWxUser.do",
                data:{role:"",nickName:$("#q").val()},
                success : function(rst, textStatus) {
                    var html=[];
                    $.each($(rst),function(i,o){
                            html.push('<div class="layout-user">');
                            html.push('<a href="javascript:insertWxUser(\''+o.unionId+'\')">');
                            html.push('<img  class="photo" src="'+o.avatarUrl+'">');
                            html.push('</a>');
                            html.push('<div style="text-align:center">');
                            html.push(o.nickName);
                            html.push('</div>');
                            html.push('</div>');
                    });
                    $("#wxUserList").html(html.join(""));
                }
         });
    });
});

function initPhoto(){
    $.ajax({
		type : "post",
		url : contextPath + "/wxUser/selectWxUser.do",
		data:{role:"admin"},
		success : function(rst, textStatus) {
			initPhotoDom(rst);
		}
	});
}
function initPhotoDom(rst){
        var html=new Array();
        html.push('<ul class="ace-thumbnails clearfix">');
        $.each($(rst),function(i,o){
				html.push('<li>');
                html.push('<a href="'+o.avatarUrl+'" title="'+o.nickName+'" data-rel="colorbox" class="cboxElement">');
                html.push('<img class="photo" src="'+o.avatarUrl+'">');
                html.push('</a>');
                html.push('<div class="tools tools-bottom">');
                html.push('<a href="javascript:delPhoto(\''+o.unionId+'\')">');
                html.push('<i class="ace-icon fa fa-times red"></i>');
                html.push('</a>');
                html.push('</div>');
                html.push('<div style="text-align:center">');
                html.push(o.nickName);
                html.push('</div>');
                html.push('</li>');
		});
                html.push('<li>');
                html.push('<a href="javascript:false"><img style="padding:20px" alt="60x60" id="btn-image-upload" class="photo" src="'+portalPath+'/content/common/image/add.png"></a>');
                html.push('</li>');
        html.push('</ul>');


    $("#wxUser").html(html.join(""));

    var $overflow = '';
    	var colorbox_params = {
    		rel: 'colorbox',
    		reposition:true,
    		scalePhotos:true,
    		scrolling:false,
    		previous:'<i class="ace-icon fa fa-arrow-left"></i>',
    		next:'<i class="ace-icon fa fa-arrow-right"></i>',
    		close:'&times;',
    		current:'{current} of {total}',
    		maxWidth:'100%',
    		maxHeight:'100%',
    		onOpen:function(){
    			$overflow = document.body.style.overflow;
    			document.body.style.overflow = 'hidden';
    		},
    		onClosed:function(){
    			document.body.style.overflow = $overflow;
    		},
    		onComplete:function(){
    			$.colorbox.resize();
    		}
    	};

    	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
    	$('#btn-image-upload').on('click',function() {
            var dialog = $("#dialog-message").removeClass('hide').dialog({
                modal : true,
                width : 750,
                title : "<div class='widget-header widget-header-small'><div class='widget-header-pd'>绑定管理员</div></div>",
                title_html : true,
                buttons : [{
                    html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                    "class" : "btn btn-info btn-xs",
                    click : function() {
                        $(this).dialog("close");
                    }
                }]
            });
            $(dialog).parent().css("top","1px");
            $(dialog).css("max-height",window.innerHeight-layoutTopHeight+50);

        });
}
function delPhoto(id){
$.ajax({
		type : "post",
		url : contextPath + "/wxUser/deleteRoleById.do",
		data:{id:id},
		success : function(rst, textStatus) {
			initPhoto();
		}
	});
}
function insertWxUser(id){
$.ajax({
		type : "post",
		url : contextPath + "/wxUser/updateRoleById.do",
		data:{id:id,role:'admin'},
		success : function(rst, textStatus) {
			initPhoto();
		}
	});
}
jQuery(function($) {
    loadText(id);
    updateReading(id);
});
function loadText(id) {
	$.ajax({
		type : "post",
		url : contextPath+"/www/anslysis/query.do",
		data : {
			id : id,
			start:0,
			limit:0,
			reportId:'loadWriting'
		},
		beforeSend : function(XMLHttpRequest) {
             $.showLoading();
		},
		success : function(rst, textStatus) {
            console.log(rst);
            $.hideLoading();
             var html=[];
            var c=0;
            $(rst.value).each(function(n,o){
                $.each(o, function(key, value) {

                    if(key=='photo'){
                         var src = fastdfs_server + value;
                                var img = new Image();
                                $(img).attr("src", "");
                                $(img).css("width", "140");
                                $(img).css("height", "170");
                                //图片加载加载后执行
                                $(img).load(function() {
                                    //图片默认隐藏
                                    $(this).hide();
                                    //移除小动画
                                    $(".loading").removeClass("loading").append(this);
                                    //使用fadeIn特效
                                    $(this).fadeIn("slow");
                                }).error(function() {
                                    //加载失败时的处理
                                })
                                //最后设置src
                                .attr("src", src);
                    }else{
                        $("#"+key).html(value);
                    }

                })
                c++;
            });
            if(c==0){
                 $.alert("没有找到数据！", "很抱歉！");
            }
            doDraw();
		},
		error : function() {
		    $.hideLoading();
			alert("加载错误！");
		}
	});
}

function updateReading(id) {
	$.ajax({
		type : "post",
		url : contextPath+"/www/anslysis/updateReading.do",
		data : {
			id : id,
			reportId:'loadWriting'
		}
	});
}
var imglist;
//安卓4.0+等高版本不支持window.screen.width，安卓2.3.3系统支持
var _width;

window.onresize = function(){
    //捕捉屏幕窗口变化，始终保证图片根据屏幕宽度合理显示
    doDraw();
}

function doDraw(){
    _width = window.innerWidth;
    imglist =document.getElementsByTagName("img");
    for( var i = 0, len = imglist.length; i < len; i++){
        DrawImage(imglist[i],_width-50);
    }
}

function DrawImage(ImgD,_width){
    var image=new Image();
    image.src=ImgD.src;
    image.onload = function(){
        //限制，只对宽高都大于30的图片做显示处理
        if(image.width>200 || image.height>200){
            if(image.width>_width){
                ImgD.width=_width;
                ImgD.height=(image.height*_width)/image.width;
            }else{
                ImgD.width=image.width;
                ImgD.height=image.height;
            }

        }
    }

}
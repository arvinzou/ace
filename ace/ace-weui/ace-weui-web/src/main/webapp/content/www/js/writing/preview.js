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


jQuery(function($) {
    loadData();
});
function loadData() {
	$.ajax({
		type : "post",
		url : contextPath+"/www/live/getLive.do",
		data : {
		    id:rid
		},
		beforeSend : function(XMLHttpRequest) {
             $.showLoading();
		},
		success : function(rst, textStatus) {
            console.log(rst);
            $.hideLoading();
            var o=rst;
            $("#content").html(o.content);
            var params={bgcolor:'#FFF',allowFullScreen:false,allowScriptAccess:'always'};
            var video=[o.rtmpUrl];
            CKobject.embed(contextPath+'/content/commom/ckplayer/ckplayer.swf','a1','ckplayer_a1','100%','100%',false,{},video,params);
            $(document.body).pullToRefreshDone();
		},
		error : function() {
		    $.hideLoading();
			alert("加载错误！");
		}
	});
}
$(function() {
   FastClick.attach(document.body);
});
var start=0;
  $(document.body).pullToRefresh().on("pull-to-refresh", function() {
    setTimeout(function() {
       loadData();
    }, 100);
});
$(function(){
    var li_a= $(".tab_menu ul li a");
    li_a.click(function(){
        $(this).removeClass("unselected");
        $(this).addClass("selected");
        $(this).parent().siblings().children().removeClass("selected");
        $(this).parent().siblings().children().addClass("unselected");
        var index =  li_a.index(this);
        $(".tab_box > div").eq(index).show().siblings().hide();
    });
})



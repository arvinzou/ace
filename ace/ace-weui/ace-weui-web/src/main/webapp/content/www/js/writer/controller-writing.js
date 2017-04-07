jQuery(function($) {
    loadText(null,author,0,99999);
});
function t_query(){
   start=0;
   $(".weui-panel__bd").html("");
   var name=$("#searchInput").val();
   loadText(name,author,0,99999);
   return false;
}
function loadText(name,author,start,limit) {
	$.ajax({
		type : "post",
		url : contextPath+"/www/anslysis/query.do",
		data : {
			author : author,
			name : name,
			start:start,
			limit:limit,
			reportId:'writing'
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
                html.push(' <a href="preview-text.jsp?id='+o.id+'" class="weui-media-box weui-media-box_appmsg">');
                //html.push(' <div class="weui-media-box__hd">');
               // html.push(' <img class="weui-media-box__thumb" src="'+fastdfs_server+o.photo+'" alt=""> ');
               // html.push(' </div>');
                html.push(' <div class="weui-media-box__bd">');
                html.push(' <h4 class="weui-media-box__title">【'+o.category+'】'+o.name+'</h4>');
                html.push(' <p class="weui-media-box__desc">'+o.dateOfPublication+'  '+o.author+' 阅读：'+o.reading+'</p>');
                html.push(' </div>');
                html.push(' </a>');
                c++;
            });
            $(".weui-panel__bd").append(html.join(" "));
            if(c==0){
                 $.alert("没有找到数据！", "很抱歉！");
            }
            $("#time").text(new Date);
            $(document.body).pullToRefreshDone();
		},
		error : function() {
		    $.hideLoading();
			alert("加载错误！");
		}
	});
}




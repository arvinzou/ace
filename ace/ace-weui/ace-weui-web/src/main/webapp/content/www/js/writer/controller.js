jQuery(function($) {
    loadText(null,0,10);
});
function loadText(name,start,limit) {
	$.ajax({
		type : "post",
		url : contextPath+"/www/anslysis/query.do",
		data : {
			category : category,
			name : name,
			start:start,
			limit:limit,
			reportId:'writer'
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
                html.push(' <a href="preview.jsp?id='+o.id+'" class="weui-media-box weui-media-box_appmsg">');
                html.push(' <div class="weui-media-box__hd">');
                html.push(' <img class="weui-media-box__thumb2" src="'+fastdfs_server+o.photo+'" alt=""> ');
                html.push(' </div>');
                html.push(' <div class="weui-media-box__bd">');
                html.push(' <h4 class="weui-media-box__title">'+o.name+'</h4>');
                html.push(' <p class="weui-media-box__desc">'+o.intro+'</p>');
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


function t_query(){
    start=0;
     $(".weui-panel__bd").html("");
    loadText($("#searchInput").val(),0,10);
    return false;
}

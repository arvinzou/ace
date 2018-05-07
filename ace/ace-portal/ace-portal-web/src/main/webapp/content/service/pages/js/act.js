
function App(){
    console.log("=============================App Start==============================");
    loader({path:contextPath,url:'/content/service/pages/css/style.css',type:'css'});
     getList();
     var moviePath="js/ZeroClipboard.swf";
}

function getList(){
    $.ajax({
            url : contextPath + '/tplPage/getList.do',
            type : 'POST',
            timeout : 30000,
            dataType : 'json',
            beforeSend:function(){
                loading=startLoading();
                if(loading) {
                   loading.settext("正在加载，请稍后......");
                }
            },
            success : function(data) {
                console.log(data);
                var tpl = document.getElementById('tpl-box').innerHTML;
                var html = juicer(tpl,{data:data});
                $("section").append(html);
                if(loading){
                    loading.remove();
                }
             }
    });

}

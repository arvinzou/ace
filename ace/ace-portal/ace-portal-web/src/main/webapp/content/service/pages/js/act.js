
function App(){
    console.log("=============================App Start==============================");
    loader({path:contextPath,url:'/content/service/pages/css/style.css',type:'css'});
     getList();

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
                $(function () {
                  $('[data-toggle="tooltip"]').tooltip()
                })
                $(".sys").mouseover(function(){
                  $(this).find('img:last-child').attr("src","http://www.xmypage.com/backend/qrcode.php?data="+$(this).data('itid'));
                  $(this).find('img:last-child').show();
                });
                $(".sys").mouseout(function(){
                   $(".sys img:last-child").hide();
                });

             }
    });

}

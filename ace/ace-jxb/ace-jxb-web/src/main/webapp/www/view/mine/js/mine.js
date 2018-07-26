function App() {
    console.log("=============================App Start==============================");
    initData();
};

function initData(){
    $.ajax({
        url: "/jxb/www/reg/findInfo",
        type:"post",
        async:false,
        data:{},
        success:function(result){
            if(result.status == 0) {
                var myInfo = document.getElementById('myInfo').innerHTML;
                var html = juicer(myInfo, {
                    data: result.data
                });
                $("#mine").append(html);
            }else {
                if(result.data == 'unregister'){   //跳转到注册页面
                    window.location.href = '/jxb/www/view/regist/regist.html';
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}
function App() {
    console.log("=============================App Start==============================");
    loader({
        path: contextPath,
        url: '/www/chat/css/style.css',
        type: 'css'
    });

    $('#button').click(function () {
        var form_val = $('.form-control').val();
        $('.chat-thread').append('<li class="right"><span class="headright"><img src="img/test.png"/></span>' + form_val + '</li>');
        $('.form-control').val('');

        $.post("./api.php", {info: form_val}, function (data) {
            $('.chat-thread').append('<li class="left">' + data + '</li>');
            $('.chat-thread').scrollTop($('.chat-thread')[0].scrollHeight);
        });

        $('.chat-thread').scrollTop($('.chat-thread')[0].scrollHeight);
    });
}

function  inputFocus () {
    var winobj = $(window),
        scope = this,
        agent = navigator.userAgent.toLowerCase();
    setTimeout(function () {
        if (agent.indexOf('safari') != -1 && agent.indexOf('mqqbrowser') == -1
            && agent.indexOf('coast') == -1 && agent.indexOf('android') == -1
            && agent.indexOf('linux') == -1 && agent.indexOf('firefox') == -1) {//safra浏览器
            window.scrollTo(0, 1000000);//先滚动到最底部，再用scrollY得到当前的值，必须延迟 否则拿到的就是1000000
            setTimeout(function(){
                window.scrollTo(0, window.scrollY - 45);//45像素 所有浏览器都是这么高
            }, 50)
        } else {//其他浏览器
            window.scrollTo(0, 1000000);
            // window.scrollTo(0, ++this.scrollNum);
        }
    }, 200);
}
/*var height = $(window).height() * 0.8;
$('.chat-thread').css('height', height);
});*/

$(function () {
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

});
/*var height = $(window).height() * 0.8;
 $('.chat-thread').css('height', height);
 });*/

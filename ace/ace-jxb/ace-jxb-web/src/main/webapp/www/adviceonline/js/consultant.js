$(function () {
    $('.retrie dt a').click(function () {
        var $t = $(this);
        if ($t.hasClass('up')) {
            $(".retrie dt a").removeClass('up');
            $('.downlist').hide();
            /*$('.mask').hide();*/
            /*$("body").css("background","#FFFFFF"); */
            $(".lode").hide();
        } else {
            $(".retrie dt a").removeClass('up');
            $('.downlist').hide();
            $t.addClass('up');
            $('.downlist').eq($(".retrie dt a").index($(this)[0])).show();
            /*$('.mask').show();*/
            /*$("body").css("background","#666666"); */
            $(".lode").show();
        }
    });
    $(".area ul li a:contains('" + $('#area').text() + "')").addClass('selected');
    $(".wage ul li a:contains('" + $('#wage').text() + "')").addClass('selected');
});
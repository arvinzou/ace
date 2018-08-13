$(function () {
    /*顶部点击菜单*/
    $('.menu').on('click', '.swiper-slide', changeTestType);
    $('.sort').on('click', '.sortoption', showSort);
    $('.cover').on('click', hiddenSort);
    $('.sort').on('click', '.option', changeSort)
})

function changeSort() {
    var $that = $(this);
    $that.siblings().removeClass('option_action');
    $that.addClass('option_action');
    hiddenSort();
    $('.sortoption .name').text($that.text());
}


function hiddenSort() {
    $('.main_menu').removeClass('main_menu_action');
    $('.cover').hide();
}

function showSort() {
    $('.main_menu').addClass('main_menu_action');
    $('.cover').show();
//	$('body').css('overflow','hidden');
}

function changeTestType() {
    var $that = $(this);
    $that.siblings().removeClass('action');
    $that.addClass('action');
    var index = $that.index();
    console.log(index);
}

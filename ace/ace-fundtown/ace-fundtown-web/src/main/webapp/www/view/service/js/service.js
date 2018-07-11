$(function () {
    $('.list').on('click', 'li', enter);
})

function enter() {
    var $that = $(this);
    var index = $that.index();
    switch (index) {
        case 0:
            window.location.href = 'form.html';
            break;
        case 1:
            window.location.href = 'video.html';
            break;
    }
}
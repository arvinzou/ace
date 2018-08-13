var mySwiper, category;
$(function () {
    initCategory();
    initSwriper();
    initMenu();
    /*顶部点击菜单*/
    $('.menu').on('click', '.swiper-slide', changeTestType);
    $('.sort').on('click', '.sortoption', showSort);
    $('.cover').on('click', hiddenSort);
    $('.sort').on('click', '.option', changeSort);
});

function initCategory() {
    category = localStorage.getItem('category') ? localStorage.getItem('category') : parseQueryString("id");
}


/*初始化测试列表*/
function initList() {
    var url = '/portal/www/test/getEvaluatTplList.do';
    var data = {
        category: category,
        page: 1,
        limit: 20
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewList(result.data);
        }
    });
}

/*初始化列表*/

function viewList(data) {
    var info = document.getElementById('temp_testLists').innerHTML;
    var infohtml = juicer(info, {
        data: data,
    });
    $("#testLists").html(infohtml);
}


/*解析地址参数*/
function parseQueryString(prop) {
    var obj = {};
    var url = window.location.href;
    var start = url.indexOf("?") + 1;
    var str = url.substr(start);
    var arr = str.split("&");
    for (var i = 0; i < arr.length; i++) {
        var arr2 = arr[i].split("=");
        obj[arr2[0]] = arr2[1];
    }
    return obj[prop];
}


/*获取菜单数据*/
function initMenu() {
    var url = '/portal/www/test/selectTestTypeList.do';
    $.getJSON(url, function (result) {
        viewMenu(result.rows);
    })
    initList(parseQueryString('id'));
}

/*渲染菜单*/
function viewMenu(data) {
    for (var i = 0; i < data.length; i++) {
        mySwiper.addSlide(i, '<span data-id=' + data[i].id + ' class="swiper-slide ' + (data[i].id == category ? "action" : "") + '">' + data[i].name + '</span>');
    }
    silperCenter();
}

function silperCenter() {
    var index = $('.swiper-container_menu .action').index();
    mySwiper.slideTo(index, 1000, false);
}


/*初始化Swriper*/
function initSwriper() {
    mySwiper = new Swiper('.swiper-container', {
        spaceBetween: 20,
        slidesPerView: 'auto',
        centeredSlides: true
        // scrollbar: {
        //     el: '.swiper-scrollbar',
        //     hide: false,
        // },
        // pagination: {
        //     el: '.swiper-pagination',
        // },
    })
}

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
    category = $that.data("id");
    localStorage.setItem('category', category)
    $that.siblings().removeClass('action');
    $that.addClass('action');
    silperCenter();
    initList();
}

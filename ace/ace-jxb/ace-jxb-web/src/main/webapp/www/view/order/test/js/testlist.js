var mySwiper, category, syid = 'jxb';
$(function () {
    initCategory();
    initSwriper();
    initMenu();
    /*顶部点击菜单*/
    $('.menu').on('click', '.swiper-slide', changeTestType);
    $('.sort').on('click', '.sortoption', showSort);
    $('.cover').on('click', hiddenSort);
    $('.sort').on('click', '.option', changeSort);
    $('#testLists').on('click', 'li', activeTest);
});


/*进入测试环节*/
function activeTest() {
    var $that = $(this);
    var id = $that.data("id");
    if (id) {
        window.location.href = '../testing1.html?id=' + id;
    }
}


/*确定类型*/
function initCategory() {
    category = localStorage.getItem('category') ? localStorage.getItem('category') : parseQueryString("id");
}


/*初始化测试列表*/
function initList(sord, orderBy) {
    var url = '/portal/www/test/getEvaluatTplList.do';
    var data = {
        orderBy: orderBy,
        sord: sord,
        category: category,
        page: 1,
        limit: 20,
        syid: syid
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
    var data = {
        syid: syid
    }
    $.getJSON(url, data, function (result) {
        viewMenu(result.rows);
    })
    initList();
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
    })
}

var types, sort = false;


function changeSort() {
    var $that = $(this);
    var type = $that.data("type");
    if (type == types) {
        sort = !sort;
    } else {
        types = type;
        sort = false;
    }
    $that.siblings().removeClass('option_action');
    $that.addClass('option_action');
    hiddenSort();
    $('.sortoption .name').text($that.text());
    initList(sort ? 'desc' : 'asc', type);
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
    localStorage.setItem('category', category);
    $that.siblings().removeClass('action');
    $that.addClass('action');
    silperCenter();
    initList();
}

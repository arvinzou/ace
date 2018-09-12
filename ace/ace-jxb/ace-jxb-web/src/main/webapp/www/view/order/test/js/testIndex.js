var bannerSwiper, menuSwiper, syid = 'jxb';

var imgMap = {
    人格测试: 'img/rgcs.png',
    智力测试: 'img/zlcs.png',
    行为测试: 'img/xwcs.png',
    心理健康测试: 'img/xlcs.png',
    情绪测试: 'img/qxcs.png',
    人际关系测试: 'img/rjgx.png',
    兴趣爱好测试: 'img/xqah.png',
    网络成瘾测试: 'img/wlcy.png'
}
$(function () {
    initSwriper();
    // initMenu();
    initList();
    $('.recommen').on('click', '.allList', sameTestTypeList);
    $('.panel .swiper-container_panel').on('click', '.swiper-slide', activeTest);
    $('#testLists').on('click', 'li', activeTest);
    $('#testListss').on('click', 'li', activeTestT);
    $('.search').on('click', '.notice', cancelSearch);
    $('#search_input').focus(activeSearch);
    $("input").keyup(searching);
});


function activeTestT() {
    var $that = $(this);
    cancelSearch();
    enterTest($that);
}


function searching() {
    var val = $('#search_input').val();
    if (!val) {
        return;
    }
    var url = '/portal/www/test/getEvaluatTplList.do';
    var data = {
        page: 1,
        limit: 20,
        syid: syid,
        name: val
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            var info = document.getElementById('temp_testLists').innerHTML;
            var infohtml = juicer(info, {
                data: result.data.EvaluaTplList,
            });
            $("#testListss").html(infohtml);
        }
    });
}

function cancelSearch() {
    $('#search_input').val('输入标题进行搜索');
    $('.search .notice .iconfont').html('&#xe702;');
    $('.search_list').hide();
    $('#testListss').empty();
}

function activeSearch() {
    $('.search .notice .iconfont').html('&#xe67c;');
    $('.search_list').show();
}


function activeTest() {
    var $that = $(this);
    enterTest($that);
}

function enterTest($that) {
    var id = $that.data("id");
    if (id) {
        window.location.href = '../testing1.html?id=' + id;
    }
}





function sameTestTypeList() {
        window.location.href = 'testlist.html';
}


/*初始化测试列表*/
function initList() {
    var url = '/portal/www/test/getEvaluatTplList.do';
    var data = {
        page: 1,
        limit: 20,
        syid: syid
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewBanner(result.data.EvaluaTplList.slice(0, 2));
            viewList(result.data.EvaluaTplList);
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

/*初始化banner*/
function viewBanner(data) {
    for (var i = 0; i < data.length; i++) {
        var img = bannerTemplate;
        img = img.replace('[imgUrl]', data[i].cover).replace('[id]', data[i].id);
        bannerSwiper[0].addSlide(i, img);
    }
}

/*初始化Swriper*/
function initSwriper() {
    bannerSwiper = new Swiper('.swiper-container_panel', {
        loop: true,
        pagination: {
            el: '.swiper-container_panel .swiper-pagination',
        },
    })
    menuSwiper = new Swiper('.swiper-container_menu', {
        loop: true,
        pagination: {
            el: '.swiper-container_menu .swiper-pagination',
        },
    })
}

// /*获取菜单数据*/
// function initMenu() {
//     var url = '/portal/www/test/selectTestTypeList.do';
//     var data = {
//         syid: syid
//     }
//     $.getJSON(url, data, function (result) {
//         viewMenu(result.rows);
//     })
// }

// /*渲染菜单*/
// function viewMenu(data) {
//     var index = data.length;
//     var j = 0;
//     var z = 0;
//     var slide = menuTemplateStart;
//     for (var i = 0; i < index; i++) {
//         var li = menuTemplateli;
//         li = li.replace('[menuName]', data[i].name)
//             .replace('[menuId]', data[i].id)
//             .replace('[end]', j == 3 ? 'end' : '')
//             .replace('[urlImg]', imgMap[data[i].name] ? imgMap[data[i].name] : 'img/null.png');
//         slide = slide + li;
//         j++;
//         if (j == 4) {
//             slide = slide + menuTemplateEnd;
//             menuSwiper[0].addSlide(z, slide);
//             slide = menuTemplateStart;
//             j = 0;
//             z++;
//         }
//     }
//     if (j != 0) {
//         slide = slide + menuTemplateEnd;
//         menuSwiper[0].addSlide(z, slide);
//     }
// }

var bannerTemplate = '<div class="swiper-slide" data-id="[id]"><img src="[imgUrl]"></div>';

// var menuTemplateStart = '<div class="swiper-slide">' +
//     '                        <ul class="page">';
//
// var menuTemplateEnd = '</ul>' +
//     '                    </div>';
//
// var menuTemplateli = '<li class="[end]" data-id="[menuId]">' +
//     '                                <div>' +
//     '                                    <img src="[urlImg]"/>' +
//     '                                </div>' +
//     '                                <p>[menuName]</p>' +
//     '                            </li>';






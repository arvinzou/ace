var map = null;
var markers = [];
var params = {
    limit: 99,
    subareaCode: '',
    stationCode: '',
};

var rst = null;

function findTopBuildingList() {
    var url = contextPath + '/topBuilding/findTopBuildingList';
    var data = params;
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            var marker;
            while (marker = markers.pop()) {
                marker.setMap(null);
            }
            var list = rst.rows;
            if (list.length) {
                for (var i = 0; i < list.length; i++) {
                    var o = list[i];
                    var imgUrl;
                    var font;
                    if (!(o.latitude && o.longitude)) {
                        continue
                    }
                    imgUrl = "img/icon0.png"
                    var point = new BMap.Point(o.longitude, o.latitude);
                    addMarker(point);
                }
            }
            //$("#modal-preview").modal("show");
        } else {
            alert(rst.errorMessage);
        }
    })
}


function bd_encrypt(gcjLat, gcjLon) {
    var x = gcjLon, y = gcjLat;
    var z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * this.x_pi);
    var theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * this.x_pi);
    bdLon = z * Math.cos(theta) + 0.0065;
    bdLat = z * Math.sin(theta) + 0.006;
    return {'lat': bdLat, 'lon': bdLon};
};




// jQuery(function ($) {
//     // $(".info").hide();
//     // $(".RightDiv").css("height", (window.innerHeight - 45) + "px");
//     // $("#FullScreen").click(function () {
//     //     var ml = $("#TextViewPanel").css("margin-left");
//     //     if (ml == '0px') {
//     //         $("#TextViewPanel").css("margin-left", "-380px");
//     //         $("#FullScreen").css('background-position', '-44px 0px');
//     //         $("#FullScreen").css('left', '-1px');
//     //     } else {
//     //         $("#TextViewPanel").css("margin-left", "0px");
//     //         $("#FullScreen").css('background-position', '-22px 0px');
//     //         $("#FullScreen").css('left', '299px');
//     //     }
//     // })
// });

$(function () {
    $('#Map').click(cancelActive)
    $('.detailBar').on('click', '.b', changeview);
    // $('.searchBar').on('click', '.rst', showSelect);
    getAreas();
    initJuicerMethod();
})


function cancelActive(event) {
    $('.searchBar .select').removeClass('active');
    $('.detailBar').removeClass('active');
}

function showSelect() {
    var s = $('.searchBar .select');
    if (s.hasClass('active')) {
        s.removeClass('active');
    } else {
        s.addClass('active');
    }
}

function changeview() {
    var that = $(this);
    if (that.hasClass('active')) {
        return;
    }
    var s = $('.swriper .swiper-wrapper');
    if (s.hasClass('active')) {
        s.removeClass('active');
    } else {
        s.addClass('active');
    }
    that.siblings().removeClass('active');
    that.addClass().addClass('active');
}


function initMap(la, lo) {
    map = new BMap.Map("Map" /*{minZoom: 12, maxZoom: 17}*/);
    var point = new BMap.Point(lo, la);  // 创建点坐标
    map.centerAndZoom(point, 13);
//    启动滚轮缩放
    map.enableScrollWheelZoom();
}


// 编写自定义函数,创建标注
function addMarker(point) {
    var marker = new BMap.Marker(point);
    map.addOverlay(marker);
}


function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}


function initPreview(id) {
    var url = contextPath + "/topBuilding/selectTopBuildingByPrimaryKey";
    var data = {
        id: id
    };
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            $('.detailBar').addClass('active');
        }
    })
    // $.ajax({
    //
    //     success: function (result) {
    //         stopLoad();
    //         if (result.status == 0) {
    //             var data = {};
    //             data['o'] = result.value;
    //             render('#fm-preview', data, 'tpl-preview');
    //             $(".accident_info").show();
    //             window.clearTimeout(timeOut);
    //             timeOut = window.setTimeout(function () {
    //                 $(".accident_info").hide();
    //             }, 3000);
    //
    //         } else {
    //             alert(result.errorMessage);
    //         }
    //     },
    //     error: function () {
    //         stopLoad();
    //         alert("对不起出错了！");
    //     }
    // });
}

function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}


// function getAreas() {
//     var url = contextPath + '/topSubarea/findTopSubareaList';
//     var data = {
//         start: 0,
//         limit: 100,
//     }
//     $.getJSON(url, data, function (rst) {
//         if (rst.status == 0) {
//             data = rst.rows[0];
//             if (data && data.longitude && data.latitude) {
//                 initMap(data.longitude, data.latitude);
//                 // initMap(114.270730,30.601090);
//             } else {
//                 initMap(114.270730, 30.601090);
//             }
//             ;
//             params.subareaCode = data.code;
//             findTopBuildingList();
//             getStations(data.code);
//             $('.rst .area').text(data.name);
//             render('#areaList', rst.rows, 'tpl-areaList');
//         }
//     })
// }

function areaChange(that) {
    var $t = $(that);
    var name = $t.data('name');
    var code = $t.data('code');
    var la = $t.data('la');
    var lo = $t.data('lo');
    params.subareaCode = code;
    params.stationCode = '';
    findTopBuildingList();
    getStations(code);
    if (la && lo) {
        var point = new BMap.Point(lo, la);
        map.panTo(point, true);
    }
    $('.rst .area').text(name);
    $('.rst .station .text').text('全部');
}


function stationChange(that) {
    var $t = $(that);
    var name = $t.data('name');
    var code = $t.data('code');
    var la = $t.data('la');
    var lo = $t.data('lo');
    params.stationCode = code;
    findTopBuildingList();
    if (la && lo) {
        var point = new BMap.Point(lo, la);
        map.panTo(point, true);
    }
    $('.rst .station .text').text(name);
}

function getStations(code) {
    var url = contextPath + '/topStation/findTopStationList';
    var data = {
        subareaCode: code,
        start: 0,
        limit: 200,
    }
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            render('#stationList', rst.rows, 'tpl-stationList');
        }
    })
}


var timeOut;


// //juicer自定义函数
// function initJuicerMethod() {
//     juicer.register('rsd', rsd);
// }
//
// function rsd(value, kernelKey, staticDictObjects) {
//     try {
//         if (!staticDictObjects) {
//             staticDictObjects = parent.staticDictObject;
//         }
//         var name = value;
//
//         if ((value + "") && ("" + value).indexOf(',') < 0) {
//             if (staticDictObjects && kernelKey && staticDictObjects[kernelKey]) {
//                 for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
//                     if (staticDictObjects[kernelKey][i].CODE == value) {
//                         name = staticDictObjects[kernelKey][i].NAME;
//                         break;
//                     }
//                 }
//             }
//         } else {
//             if (value) {
//                 var nameArray = [];
//                 var v = (value + "").split(',');
//                 for (var j = 0; j < v.length; j++) {
//                     for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
//                         if (staticDictObjects[kernelKey][i].CODE == v[j]) {
//                             nameArray.push(staticDictObjects[kernelKey][i].NAME);
//                             break;
//                         }
//                     }
//                 }
//                 name = nameArray.join(',');
//             }
//         }
//     } catch (err) {
//         console.log("渲染错误", value + ":" + kernelKey + ":" + err);
//     }
//     return name;
// }
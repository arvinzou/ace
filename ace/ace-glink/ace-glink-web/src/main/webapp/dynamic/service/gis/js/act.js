var map = null;
var markers = [];
var params = {
    limit: 9999,
};
x_pi=3.14159265358979324 * 3000.0 / 180.0;

var rst = null;

/**坐标转换*/
function bd_encrypt(gcjLat, gcjLon) {
    // console.log(gcjLat);
    var x = gcjLon, y = gcjLat;
    var z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * this.x_pi);
    // console.log(z);
    var theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * this.x_pi);
    bdLon = z * Math.cos(theta) + 0.0065;
    bdLat = z * Math.sin(theta) + 0.006;
    return {'lat': bdLat, 'lon': bdLon};
};

$(function () {
    $('#Map').click(cancelActive)
    $('.detailBar').on('click', '.b', changeview);
    initMap();
    findTopBuildingList();
})

/**初始化地图*/
function initMap() {
    map = new BMap.Map("Map" /*{minZoom: 12, maxZoom: 17}*/);
    var point = new BMap.Point(114.270730,30.601090);  // 创建点坐标
    map.centerAndZoom(point, 13);
//    启动滚轮缩放
    map.enableScrollWheelZoom();
}

/**查询建筑列表*/
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
                    if (!(o.latitude && o.longitude)) {
                        continue
                    }
                    imgUrl = "img/icon0.png";
                    var lalo=bd_encrypt(o.latitude,o.longitude)
                    var point = new BMap.Point(lalo.lon,lalo.lat);
                    addMarker(point,o);
                }
            }
            //$("#modal-preview").modal("show");
        } else {
            alert(rst.errorMessage);
        }
    })
}


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


// 编写自定义函数,创建标注
function addMarker(point,o){
    var myIcon = new BMap.Icon("./img/icon0.png", new BMap.Size(30,30),{imageSize:new BMap.Size(30,30)});
    var marker = new BMap.Marker(point,{icon:myIcon});
    marker.setTitle(o.name);
    marker.onclick=(function(o) {
        var clickLi = function() {
            console.log(o);
        }
        return clickLi
    })(o);
    map.addOverlay(marker);
}
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data
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

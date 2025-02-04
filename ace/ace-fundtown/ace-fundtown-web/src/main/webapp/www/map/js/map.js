var myChart, swiper;
$(function () {
    myChart = echarts.init(document.getElementById('main'));
    myChart.setOption(option);
    myChart.on('click', function (parmas) {
        console.log(parmas);
        viewinfo(parmas.data);
    });
    $('body').on('click', hideinfo);
})


function hideinfo() {
    $('#main').removeClass('mapinfo');
}

function viewinfo(data) {
    var name = data.name;
    var msg = massage[name];
    data.address = msg.address;
    $('.btn_navigation').data(data);
    $('#main').addClass('mapinfo');
    $('.company_info .name').text(name);
    $('.company_info .address').text(msg.address);
    swiper.removeAllSlides();
    var img = msg.images;
    for (var i = 0; i < img.length; i++) {
        swiper.addSlide(i, '<div class="swiper-slide"><img src="' + img[i] + '"/></div>');
    }
    swiper.slideTo(0, 100, false);
}

var option = {
    bmap: {
        center: [111.737953, 29.057274],
        zoom: 13,
        roam: true,
    },
    series: [
        {
            name: '游乐',
            type: 'scatter',
            coordinateSystem: 'bmap',
            data: youle,
            symbol: 'image://img/park.png',
            symbolSize: 40,
        },
        {
            name: '景点',
            type: 'scatter',
            coordinateSystem: 'bmap',
            data: jingdian,
            symbol: 'image://img/attractions.png',
            symbolSize: 40,
        },
        {
            name: '食宿',
            type: 'scatter',
            coordinateSystem: 'bmap',
            data: shisu,
            symbol: 'image://img/hotel.png',
            symbolSize: 40,
        },
        {
            name: '食宿',
            type: 'scatter',
            coordinateSystem: 'bmap',
            data: shisu,
            symbol: 'image://img/hotel.png',
            symbolSize: 40,
        },
        {
            name: '教育',
            type: 'scatter',
            coordinateSystem: 'bmap',
            data: jiaoyu,
            symbol: 'image://img/xuexiao.png',
            symbolSize: 40,
        },
        {
            name: '医院',
            type: 'scatter',
            coordinateSystem: 'bmap',
            data: yiyuan,
            symbol: 'image://img/yiyuan.png',
            symbolSize: 40,
        },
        {
            name: '本公司',
            type: 'scatter',
            coordinateSystem: 'bmap',
            data: self,
            symbol: 'image://img/logo.png',
            symbolSize: 40,
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false
                },
                emphasis: {
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#03f'
                }
            }
        }
    ]
}

$('.btn_navigation').click(function () {
    var data = $(this).data();
    // var url="https://api.map.baidu.com/geoconv/v1/";
    var datas = {
        coords: '' + data.value[0] + ',' + data.value[1],
        ak: 'YZjRB8FYnf7SdG7BfOGQQIGseKlMmTgF',
        from: 5,
        to: 3
    }
    $.ajax({
        url: "https://api.map.baidu.com/geoconv/v1/",
        data: datas,
        success: function (result) {
            if (result.status == 0) {
                var jinweidu = result.result[0];
                console.log(jinweidu.y);
                console.log(jinweidu.x);
                wx.openLocation({
                    latitude: jinweidu.y, // 纬度，浮点数，范围为90 ~ -90
                    longitude: jinweidu.x, // 经度，浮点数，范围为180 ~ -180。
                    name: data.name, // 位置名
                    address: data.address, // 地址详情说明
                    scale: 13, // 地图缩放级别,整形值,范围从1~28。默认为最大
                });
            }
        },
        dataType: "jsonp",
        jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
        jsonpCallback: "flightHandler",
    });
})
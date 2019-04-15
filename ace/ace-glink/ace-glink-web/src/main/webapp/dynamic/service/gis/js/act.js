var map = null;
var markers = [];
var params = {
    limit: 99,
    subareaCode:'',
    stationCode:'',
};
function rsd(value, kernelKey, staticDictObjects) {
    try {
        if (!staticDictObjects) {
            staticDictObjects = parent.staticDictObject;
        }
        var name = value;

        if ((value + "") && ("" + value).indexOf(',') < 0) {
            if (staticDictObjects && kernelKey && staticDictObjects[kernelKey]) {
                for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
                    if (staticDictObjects[kernelKey][i].CODE == value) {
                        name = staticDictObjects[kernelKey][i].NAME;
                        break;
                    }
                }
            }
        } else {
            if (value) {
                var nameArray = [];
                var v = (value + "").split(',');
                for (var j = 0; j < v.length; j++) {
                    for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
                        if (staticDictObjects[kernelKey][i].CODE == v[j]) {
                            nameArray.push(staticDictObjects[kernelKey][i].NAME);
                            break;
                        }
                    }
                }
                name = nameArray.join(',');
            }
        }
    } catch (err) {
        console.log("渲染错误", value + ":" + kernelKey + ":" + err);
    }
    return name;
}

var rst = null;

function findTopBuildingList() {
    var url = contextPath + '/topBuilding/findTopBuildingList';
    var data = params;
    $.getJSON(url, data, function (rst) {
        if (rst.status==0){
                var marker;
                while (marker = markers.pop()) {
                    marker.setMap(null);
                }
                var list=rst.rows;
                if (list.length) {
                    for (var i=0;i<list.length;i++) {
                        var o = list[i];
                        var imgUrl;
                        var font;
                        if(!(o.latitude&&o.longitude)){
                            continue
                        }
                        imgUrl = "img/icon0.png"
                        font = "<font style='font-weight: bold;color:#fff;font-size:10px'>" + 0 + "</font>"
                        var marker = new qq.maps.Marker({
                            //设置Marker的位置坐标
                            position: new qq.maps.LatLng(o.latitude, o.longitude),
                            //设置Marker被添加到Map上时的动画效果为落下
                            animation: qq.maps.MarkerAnimation.DOWN,
                            //设置Marker被添加到Map上时的动画效果为反复弹跳
                            //animation:qq.maps.MarkerAnimation.BOUNCE
                            //设置Marker被添加到Map上时的动画效果为从天而降
                            animation: qq.maps.MarkerAnimation.DROP,
                            //设置Marker被添加到Map上时的动画效果为升起
                            //animation:qq.maps.MarkerAnimation.UP
                            //设置显示Marker的地图
                            map: map,
                            //设置Marker可拖动
                            draggable: true,
                            //Marker的覆盖内容
                            decoration: new qq.maps.MarkerDecoration(font, new qq.maps.Point(0, -4)),
                            // decoration: new qq.maps.MarkerDecoration('<span class="demoSpan1">1</span>'),
                            //自定义Marker图标为大头针样式
                            icon: new qq.maps.MarkerImage(imgUrl),
                            //自定义Marker图标的阴影
                            // shadow: new qq.maps.MarkerImage("https://open.map.qq.com/doc/img/nilb.png"),
                            //设置Marker标题，鼠标划过Marker时显示
                            title: o.name,
                            //设置Marker的可见性，为true时可见,false时不可见
                            visible: true,
                            o: o

                        });
                        qq.maps.event.addListener(marker, 'click', function (event) {
                            initPreview(event.target.o.id);
                        });
                        markers.push(marker);
                    }
                }
                //$("#modal-preview").modal("show");
        } else {
            alert(rst.errorMessage);
        }
    })
}


jQuery(function ($) {
    $(".info").hide();
    $(".RightDiv").css("height", (window.innerHeight - 45) + "px");
    $("#FullScreen").click(function () {
        var ml = $("#TextViewPanel").css("margin-left");
        if (ml == '0px') {
            $("#TextViewPanel").css("margin-left", "-380px");
            $("#FullScreen").css('background-position', '-44px 0px');
            $("#FullScreen").css('left', '-1px');
        } else {
            $("#TextViewPanel").css("margin-left", "0px");
            $("#FullScreen").css('background-position', '-22px 0px');
            $("#FullScreen").css('left', '299px');
        }
    })
    $('.detailBar').on('click', '.b', changeview);
    $('.searchBar').on('click', '.rst', showSelect);
    getAreas();
    initJuicerMethod();
});

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


function initMap(la,lo) {
    map = new qq.maps.Map(document.getElementById("Map"), {
        center: new qq.maps.LatLng(la, lo),
        zoom: 14,
        //mapTypeId: "coordinate",
        resizeKeepCenter: true,
        mapTypeControl: true,
        panControl: false,
        zoomControl: false,
        scaleControl: false,
        minZoom: 4,
        maxZoom: 18,
        //设置平移控件的位置
        panControlOptions: {
            //设置平移控件的位置为相对右方中间位置对齐.
            position: qq.maps.ControlPosition.RIGHT_CENTER
        },
        zoomControlOptions: {
            //设置缩放控件的位置为相对左方中间位置对齐.
            position: qq.maps.ControlPosition.RIGHT_CENTER,
            //设置缩放控件样式为仅包含放大缩小两个按钮
            style: qq.maps.ZoomControlStyle.SMALL
        }

    });
}

function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}


function initPreview(id) {
    var url=contextPath + "/topBuilding/selectTopBuildingByPrimaryKey";
    var data={
        id: id
    };
    $.getJSON(url,data,function (rst) {
        if(rst.status==0){
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


function getAreas(){
   var url=contextPath+'/topSubarea/findTopSubareaList';
   var data={
       start:0,
       limit:100,
   }
   $.getJSON(url,data,function (rst) {
      if(rst.status==0){
          data=rst.rows[0];
          if(data&&data.longitude&&data.latitude){
              initMap(data.latitude,data.longitude);
          }else{
              initMap(30.601090,114.270730);
          };
          params.subareaCode=data.code;
          findTopBuildingList();
          getStations(data.code);
          $('.rst .area').text(data.name);
          render('#areaList',rst.rows,'tpl-areaList');
      }
   })
}

function areaChange(that) {
   var $t=$(that);
   var name=$t.data('name');
   var code=$t.data('code');
   var la=$t.data('la');
   var lo=$t.data('lo');
    params.subareaCode=code;
    params.stationCode='';
    findTopBuildingList();
    getStations(code);
    if(la&&lo){
        map.panTo(new qq.maps.LatLng(la, lo));
    }
    $('.rst .area').text(name);
    $('.rst .station .text').text('全部');
}


function stationChange(that) {
    var $t=$(that);
    var name=$t.data('name');
    var code=$t.data('code');
    var la=$t.data('la');
    var lo=$t.data('lo');
    params.stationCode=code;
    findTopBuildingList();
    if(la&&lo){
        map.panTo(new qq.maps.LatLng(la, lo));
    }
    $('.rst .station .text').text(name);
}

function getStations(code) {
    var url=contextPath+'/topStation/findTopStationList';
    var data={
        subareaCode:code,
        start:0,
        limit:200,
    }
    $.getJSON(url,data,function (rst) {
        if(rst.status==0){
            render('#stationList',rst.rows,'tpl-stationList');
        }
    })
}


var timeOut;


//juicer自定义函数
function initJuicerMethod() {
    juicer.register('rsd', rsd);
}
var map = null;
var markers = [];
var params = {
    limit: 99
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
var rst=null;
function findTraAccList() {
    $.ajax({
        url: contextPath + '/traAcc/getTraAccListTx',
        type: "post",
        async: false,
        data: params,
        success: function (req) {
            if (params.areaCode) {
                getLatLongByAreaCode({
                    areaCode: params.areaCode
                });
            }
            rst=req;
            if (rst) {

                Concurrent.Thread.create(function(){
                    //clearMarkers(markers);
                    var marker;
                    while (marker = markers.pop()) {
                        marker.setMap(null);
                    }
                    if (rst) {
                        for (var i in rst) {
                            var o = rst[i];
                            var imgUrl;
                            var font;
                            if (o.deadthToll && o.injuries) {
                                imgUrl = "img/icon01.png"
                                font = "<font class='halfStyle' data-content='' style='font-weight: bold;color:#fff;font-size:10px'>" + o.deadthToll + "/" + o.injuries + "</font>"
                            } else if (o.deadthToll && !o.injuries) {
                                imgUrl = "img/icon1.png"
                                font = "<font style='font-weight: bold;color:#fff;font-size:10px'>" + o.deadthToll + "</font>"
                            } else if (!o.deadthToll && o.injuries) {
                                imgUrl = "img/icon0.png"
                                font = "<font style='font-weight: bold;color:#fff;font-size:10px'>" + o.injuries + "</font>"
                            }else {
                                imgUrl = "img/icon0.png"
                                font = "<font style='font-weight: bold;color:#fff;font-size:10px'>" + 0 + "</font>"
                            }
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
                                title: o.roadManName,
                                //设置Marker的可见性，为true时可见,false时不可见
                                visible: true,
                                o: o

                            });
                            qq.maps.event.addListener(marker, 'click', function (event) {
                                console.log(event);
                                initPreview(event.target.o.id);
                            });
                            markers.push(marker);
                        }
                    }
                    //$("#modal-preview").modal("show");
                });

            } else {
                alert(rst.errorMessage);
            }
        },
        error: function () {

            alert("对不起出错了！");
        }
    });
}

function clearMarkers(markers) {
    var marker;
    while (marker = markers.pop()) {
        marker.setMap(null);
    }
}

function getLatLongByAreaCode(data) {
    $.ajax({
        url: contextPath + '/traAcc/getLatLongByAreaCode',
        type: "post",
        async: false,
        data: data,
        success: function (result) {
            if (result.status == 0) {
                map.panTo(new qq.maps.LatLng(result.value.latitude, result.value.longitude));
                $("input[name=areaCode]").val(result.value.areaName);
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            alert("对不起出错了！");
        }
    });
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
    initJuicerMethod();

    initMap();
    initForm();
    initCombotree();
    initEvents();
    getLatLongByAreaCode({
        areaCode: userProp.areaCode
    });
    findTraAccList({});
    $('#leftDiv').mouseover(setClass);
    $('#Map').mouseover(moveClass);
    $('#Header').mouseover(moveClass);
    $('.accident_info').mouseover(clearTime);
    $('.accident_info').mouseout(setTimeOut);
    $('.active_seach').click(searchRoadName);
    $('#check-group-category').on('click', 'button', setParams);
});

function initCombotree() {
    $('#tt').combotree({
        url:portalPath+"/system/selectProvinceTreeList.do" ,
        valueField: 'id',
        textField: 'text',
        method: 'get',
        // onChange :function(){
        //     $val =  $("#tt").combotree('getValue');
        //     $("#_EnvUnitCode").val($val);
        // },
        onLoadSuccess:function(node,data){
            $('.combo .combo-value').val(data[0].id);
        }

    });
}

function searchRoadName() {
    params.keyword = $("input[name=keyword]").val();
    findTraAccList();
}


function clearTime() {
    window.clearTimeout(timeOut);
}

function setTimeOut() {
    timeOut = window.setTimeout(function () {
        $(".accident_info").hide();
    }, 3000);
}

function setClass() {
    $('#leftDiv').addClass('leftIcon');
    $('#leftDiv').removeClass('centerIcon');
}

function moveClass() {
    $('#leftDiv').removeClass("leftIcon");
    $('#leftDiv').addClass("centerIcon");
}


function initMap() {
    map = new qq.maps.Map(document.getElementById("Map"), {
        //center: new qq.maps.LatLng(result.value.latitude, result.value.longitude),
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

function initForm() {
    $('#fm').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            if(params.startDate){
                params.startDate = params.startDate + ' 00:00:00';
            }if(params.endDate){
                params.endDate = params.endDate + ' 23:59:59';
            }
            findTraAccList(params);
            return false;
        }
    });
}

function setParams() {
    var that = $(this);
    params.category = that.data('id');
    findTraAccList();
    that.siblings().removeClass('active_but');
    that.addClass('active_but');
}

function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

function initEvents() {


    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });
    //道路级别
    var data = {};
    data.key = 'category';
    data.list = staticDictObject['170'];
    render($("#check-group-category"), data, "tpl-check-group");

    $("input[name=startDate]").datetimepicker({
        minView: "month",
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: true, //显示‘今日’按钮
        clearBtn: true, //清除按钮
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0
    }).on('changeDate', function (ev) {
        // moveCSS();
    });

    $('input[name=startDate]').focus(function () {
        $(this).blur(); //不可输入状态
    })


    $("input[name=endDate]").datetimepicker({
        minView: "month",
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: true, //显示‘今日’按钮
        clearBtn: true, //清除按钮
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0
    }).on('changeDate', function (ev) {
        // moveCSS();
    });
    $('input[name=endDate]').focus(function () {
        $(this).blur(); //不可输入状态
    });
    $('#tt').combotree({
        onSelect: function (node) {
            getLatLongByAreaCode({
                areaCode: node.id
            });
        }
    });
}

function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/traAcc/selectTraAccByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-preview', data, 'tpl-preview');
                $(".accident_info").show();
                window.clearTimeout(timeOut);
                timeOut = window.setTimeout(function () {
                    $(".accident_info").hide();
                }, 3000);

            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}


var timeOut;


//juicer自定义函数
function initJuicerMethod() {
    juicer.register('rsd', rsd);
}

function infoClose() {
    $(".info").hide();
}

function areaCode(o) {
    console.log(o);
}
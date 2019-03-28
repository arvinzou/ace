var regType = null;
var lat = null;
var longt = null;
var btnName = "上午签到"
$(function(){
    findList();
    initUserData();
    getConfig();
});
function initUserData(){
    $.ajax({
        url: contextPath+ "/www/sign/getAcctInfo",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                regType = result.data.regType;
                var date = new Date();
                var year = date.getFullYear();
                var month = date.getMonth()+1;
                var day = date.getDate();
                if(month < 10){
                    month = "0"+month;
                }
                result.data.currentDate = year+"-"+month+"-"+day;
                renderPage('userInfo', result.data, 'user-tpl');
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function getConfig(){
    $.ajax({
        url: "/portal/www/wx/jsapi/getConfig",
        type:"post",
        async:false,
        data:{"sysId": "partyschool", "url": window.location.href},
        success:function(result){
            if(result.status == 0) {
                locate(result.data);
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function locate(data){
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: data.appId, // 必填，
        timestamp: data.timestamp, // 必填，
        nonceStr: data.nonceStr, // 必填，
        signature: data.signature,// 必填，签名，见附录1
        jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    wx.ready(function () {
        wx.getLocation({
            type: 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
            success: function (res) {
                lat = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                longt = res.longitude; // 经度，浮点数，范围为180 ~ -180。

                var center = new qq.maps.LatLng(29.047770,111.598520);  //默认以常德市委党校为中心
                var locate = new qq.maps.LatLng(lat, longt);
                var map = new qq.maps.Map(document.getElementById("mapBox"), {
                    // 地图的中心地理坐标。
                    center: center,
                    zoom: 14
                });
                var marker = new qq.maps.Marker({
                    position: center,
                    map: map,
                    content: '常德市委党校'
                });
                var cirle = new qq.maps.Circle({
                    map: map,
                    center: center,
                    radius: 500,
                    strokeWeight:1
                });
                var marker = new qq.maps.Marker({
                    position: locate,
                    map: map,
                    content: '我'
                });
            },
            fail:function(e){
                alert("定位失败，请确认是否开启网络或定位权限。");
            }
        });
    });
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}



/**
 * 点击签到
 */
function record(){
    if(longitude){
        var con=confirm("是否确定提交?");
        if(con==true){
            $.ajax({
                url: contextPath+ "/www/att/record",
                type:"post",
                async:false,
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data:{
                    json: JSON.stringify({
                        longitude: longt,
                        latitude: lat
                    })
                },
                success:function(result){
                    if(result.status  == 1 && result.info == "ERROR_POINT"){
                        alert("对不起，您当前不在考勤区域内，不能签到！");
                    }
                    if(result.status == 0){
                        alert("签到成功！");
                        findList();
                    }
                },
                error:function(){
                    alert("系统服务内部异常！");
                }
            });
        }
    }else{
        alert("您当前的定位失败，请确认您的设备网络和定位服务！");
    }
}

/**
 * 查询签到列表
 */
function findList(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    if(month < 10){
        month = "0"+month;
    }
    var time = year+"-"+month+"-"+day;
    $.ajax({
        url: contextPath+ "/www/att/findList",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            dateTimeStr: time
        },
        success:function(result){
            if(result.status == 0){

                var num = result.data.am.length + result.data.pm.length + result.data.night.length;
                renderPage('recordList', result.data, 'record-tpl');
                $("#count").text("今日已签到"+num+"/5");
                var date = new Date();
                var hour = date.getHours();
                var minute = date.getMinutes()/60;
                hour = hour + minute;
                //上午签到
                if(result.data.am.length <1){
                    if(hour >= 8 && hour <=10){
                        //8:00~10:00允许签到
                        $("#amBtn").html('<div class="cell qiandao" onclick="record();"><p class="qtitle">上午签到</p></div>');
                    }else if(hour >= 10 && hour <= 12.5){
                        //10:00~12:30允许签退
                        $("#amBtn").html('<div class="cell qiandao" onclick="record();"><p class="qtitle">上午签退</p></div>');
                    }
                }else if(result.data.am.length >0 && result.data.am.length<2){
                    if(hour >= 10 && hour <= 12.5){
                        $("#amBtn").html('<div class="cell qiandao" onclick="record();"><p class="qtitle">上午签退</p></div>');
                    }
                }
                //下午签到
                if(result.data.pm.length <1){
                    //2:00~3:30允许签到
                    if(hour >= 14 && hour <= 15.5){
                        $("#pmBtn").html('<div class="cell qiandao" onclick="record();"><p class="qtitle">下午签到</p></div>');
                    }else if(hour >15.5 && hour <= 18){
                        //3:30~6:00允许签退
                        $("#pmBtn").html('<div class="cell qiandao" onclick="record();"><p class="qtitle">下午签退</p></div>');
                    }

                }else if(result.data.pm.length >0 && result.data.pm.length<2){
                    if(hour >=15.5 && hour <= 18){
                        $("#pmBtn").html('<div class="cell qiandao" onclick="record();"><p class="qtitle">下午签退</p></div>');
                    }
                }

                //晚上签到
                if(result.data.night.length <1){
                    //10:00~11:00允许签到
                    if(hour >= 22 && hour <= 23){
                        $("#nightBtn").html('<div class="cell qiandao" onclick="record();"><p class="qtitle">晚上签到</p></div>');
                    }
                }

            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

/**
 * 点击头像进入到个人中心页面
 */
function exitUserInfo(){
    window.location.href = contextPath + '/www/registered/person/index.jsp';
}
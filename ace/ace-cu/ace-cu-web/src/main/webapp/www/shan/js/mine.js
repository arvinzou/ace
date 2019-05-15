$(function () {
    getUserInfo();
    init();
    getOrderList();
})

function getUserInfo() {
    var url="/cu/www/user/findByOpenId";
    $.getJSON(url,function (result) {
        if(result.status == 0) {
            $('.head .headImg img').prop('href',result.data.headimgurl);
            $('.head .name').text(result.data.nickname);
        }else {
            alert(result.info);
        }
    })
}

/**查看捐赠记录*/
function getOrderList() {
    var url = "/cu/www/project/donateDetails";
    var data = {
        userId: "11c4a2bf65644c2ea72010583a11ada3",
        projectId: '14771994c7f54fe49c739c8552451f3d'
    };
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            var datas = JSON.parse(rst.data);
            renderPage('sss', datas, 'tpl-sss');
        } else {
            alert(rst.errorMessage);
        }
    })
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function init() {
    var data=queryURL();
    $('#point').text(data.point);
    $('#xunzhang').text(whichOne(data.point));
    showWhich(data.type);
    renderPage('aaa',data, 'tpl-aaa');
}
/**查询勋章个数*/
function whichOne(num) {
    if(num>1000){
        return 6;
    }else if(num>500){
        return 5;
    }
    else if(num>100){
        return 4;
    }
    else if(num>50){
        return 3;
    }
    else if(num>10){
        return 2;
    }
    else if(num>1){
        return 1;
    }else{
        return 0;
    }
}

/**选择哪一个查看*/

function showWhich(type){
    $('.tabF').removeClass("active");
    $('.tab'+type).addClass("active");
}

 /**url参数解析为对象*/
 function queryURL(){
     var url=window.location.href;
     var arr1 = url.split("?");
         var params = arr1[1].split("&");
         var obj = {};//声明对象
         for(var i=0;i<params.length;i++){
                 var param = params[i].split("=");
                 obj[param[0]] = param[1];//为对象赋值
             }
         return obj;
 }
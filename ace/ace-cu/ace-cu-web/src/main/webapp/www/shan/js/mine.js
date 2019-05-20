$(function () {
    getUserInfo();
    init();
})

function getUserInfo() {
    // var url="/cu/www/user/findByOpenId";
    // $.getJSON(url,function (result) {
    //     if(result.status == 0) {
    //         $('.head .headImg img').prop('src',result.data.headimgurl?result.data.headimgurl:"img/people.png");
    //         $('.head .name').text(result.data.nickname);
    //     }else {
    //         alert(result.info);
    //     }
    // })
}

/**查看捐赠记录*/
function getOrderList(projectId) {
    var url = "/cu/www/project/donateDetails";
    var data = {
        projectId: projectId
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
    var paramObj=queryURL();
    $('#point').text(paramObj.point);
    $('#xunzhang').text(whichOne(parseInt(paramObj.point)));
    showWhich(paramObj.type);
    renderPage('aaa',paramObj, 'tpl-aaa');
    getOrderList(paramObj.projectId);
}
/**查询勋章个数*/
function whichOne(num) {
    if(num>1000){
        return 6;
    }else if(num>499){
        return 5;
    }
    else if(num>99){
        return 4;
    }
    else if(num>49){
        return 3;
    }
    else if(num>9){
        return 2;
    }
    else if(num>0){
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
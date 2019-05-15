//
// function(){
//
//     var locaUrl = window.location.href;
//     var url = window.location.href.substring(locaUrl.indexOf("?")+1);
//     var primaryId = null;
//     var paramArr = url.split("&");
//     for(var i=0;i < paramArr.length;i++){
//         num=paramArr[i].indexOf("=");
//         if(num>0){
//             name=paramArr[i].substring(0,num);
//             value=paramArr[i].substr(num+1);
//             if(name == "projectId"){
//                 primaryId = value;
//             }
//         }
//     }
//
//     if(primaryId != null){
//         $.ajax({
//             url: "/cu/www/report/donateRank",
//             type:"post",
//             async:false,
//             data:{start:0, limit: 9999, projectId: primaryId, needOpenId: "1"},
//             success:function(result){
//                 if(result.status == 0) {
//                     renderPage('mine', result.data.own, 'mine-tpl');
//                     renderPage('donateList',  result.data.list, 'donate-tpl');
//                 }else {
//                     alert(result.info);
//                 }
//             },
//             error:function(){
//                 alert("系统服务内部异常！");
//             }
//         });
//     }
// };

var rankObjcet;

$(function(){
    var data=queryURL();
    getRankingList(data.projectId);
    $('.rankType .typeList').on('click','li',changeRankType)
})

/**查看不同的排名Wa ga ei li*/

function changeRankType() {
    var  that =$(this);
    var type=that.data('type');
    that.siblings().removeClass('active');
    that.addClass("active");
    showRank(type);
}


function getRankingList(projectId){
    var url="/cu/www/project/pointsRank";
    var data={
         projectId: '14771994c7f54fe49c739c8552451f3d'
    }
    $.getJSON(url,data,function(rst){
        if(rst.status == 0) {
            rankObjcet=JSON.parse(rst.data);
            showRank('totalRank');
        } else {
            alert(result.info);
        }
    })
}

function showRank(type){
    renderPage('donateList',rankObjcet[type].rank, 'donate-tpl');
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







function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
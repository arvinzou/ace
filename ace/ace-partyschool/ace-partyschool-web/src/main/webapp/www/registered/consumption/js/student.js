$(function(){
    initList();
    initCount();
});

function initList(){
    var nowDate = new Date();
    var year = nowDate.getFullYear();
    var month = nowDate.getMonth() + 1;
    if(month < 10){
        month = "0"+month;
    }
    var dateTimeStr = year+"-"+month;
    $.ajax({
        url:  "/api/www/api/findStudentFinList",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            lCardNo: 'ZX111011',
            dateTimeStr: dateTimeStr,
            startNum: 0,
            endNum: 999
        },
        success:function(result){
            if(result.status == 0) {
                renderPage('list', result.data, 'list-tpl');
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

function initCount(){
    $.ajax({
        url: "/api/www/api/findStudentFinCount",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            lCardNo: 'ZX111011'
        },
        success:function(result){
            if(result.status == 0) {
                renderPage('count', result.data, 'count-tpl');
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

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
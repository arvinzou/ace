var lCardNo = null;
var server = "https://www.cdswdx.top";
$(function(){

    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;

    new Mdate("dateShowBtn", {
        acceptId: "dateSelectorOne",
        beginYear: year,
        beginMonth: "1",
        endYear: year,
        endMonth: month,
        format: "-"
    });
    if(month < 10){
        month = "0"+month;
    }
    $("#dateSelectorOne").val(year+"-"+month);
    $.ajax({
        url:  contextPath+ "/www/sign/getAcctInfo",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                lCardNo = result.data.lCardNo;
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

    if(lCardNo){
        initList(lCardNo);
        initCount(lCardNo, year);
    }else{
        $("#count").html('<div class="noneCard">未绑定卡信息</div>');
    }
});

function initList(lCardNo, dateTimeStr){
    if(!dateTimeStr){
        dateTimeStr = $("#dateSelectorOne").val();
    }
    $.ajax({
        url: server+"/api/www/api/findStudentFinList",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            lCardNo: lCardNo,
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

function initCount(lCardNo, year){
    $.ajax({
        url: server+"/api/www/api/findStudentFinCount",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            lCardNo: lCardNo,
            year: year
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
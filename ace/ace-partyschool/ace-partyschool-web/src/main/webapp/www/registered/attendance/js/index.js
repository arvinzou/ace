var server = "https://www.cdswdx.top";
$(function(){
	$('#calendar').calendar({
        data: [
			
		],
        onSelected: function (view, date, data) {
            console.log(formatDate(date));
            initData(formatDate(date));
        }
    });

	var nowDate =formatDate(new Date());
    initData(nowDate);
});

function initData(date){
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
                if(lCardNo){
                    findAttDataByDay(date, lCardNo);
                }else{
                    alert("您还没有绑定卡的信息！");
                }

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

function  findAttDataByDay(date, cardId) {
    $.ajax({
        url: server+"/api/www/api/findAttDataByDay",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            lCardNo: cardId,
            dateTimeStr: date
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

function formatDate(date){
    var year = date.getFullYear();
    var month = date.getMonth() +1;
    if(month <10){
        month = "0"+month;
    }
    var day = date.getDate();
    if(day < 10){
        day = "0"+day;
    }
    return year+"-"+month+"-"+day;
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
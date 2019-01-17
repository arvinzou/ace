var server = "http://127.0.0.1";
$(function(){
	$('#calendar').calendar({
        data: [
			
		],
        onSelected: function (view, date, data) {
            console.log(formatDate(date));
            findAttDataByDay(formatDate(date));
        }
    });

	var nowDate =formatDate(new Date());
    findAttDataByDay(nowDate);
});

function  findAttDataByDay(date) {
    $.ajax({
        url: server+"/api/www/api/findAttDataByDay",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            lCardNo: 'Z0000187',
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
    return year+"-"+month+"-"+day;
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
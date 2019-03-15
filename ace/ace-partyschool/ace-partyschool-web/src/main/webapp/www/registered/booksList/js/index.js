var server = "https://www.cdswdx.top";
$(function(){
    $.ajax({
        url: contextPath+ "/www/sign/getAcctInfo",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
               var cardNo = result.data.lCardNo;
               if(cardNo){
                   findList(cardNo);
               }else{
                   alert("您还没有绑定卡，查询不到借阅信息！");
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

});

function findList(cardNo){
    $.ajax({
        url:  server+"/api/www/api/findBorrowList",
        type:"post",
        async:false,
        data:{
            lCardNo: cardNo
        },
        success:function(result){
            if(result.status == 0) {
                var list = result.data;
                var unReturnList = [];
                var resurnedList = [];
                for(var i=0; i<list.length; i++){
                    if(list[i].Signs == "未还"){
                        unReturnList.push(list[i]);
                    }else{
                        resurnedList.push(list[i]);
                    }
                }
                renderPage('unreturn', unReturnList, 'ulist-tpl');
                renderPage('return', resurnedList, 'list-tpl');
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
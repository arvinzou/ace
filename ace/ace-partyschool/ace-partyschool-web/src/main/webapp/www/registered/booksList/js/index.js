
$(function(){
    findList();
});

function findList(){
    $.ajax({
        url:  "/api/www/api/findBorrowList",
        type:"post",
        async:false,
        data:{
            lCardNo: 'Z0000012'
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
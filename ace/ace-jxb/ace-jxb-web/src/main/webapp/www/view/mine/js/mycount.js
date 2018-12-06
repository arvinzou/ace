window.onload = function() {
    console.log(window.location.href);
    var url =   window.location.href.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
    $('#income').on('click','.income_list',incomeListHistroy);
};

/**
 * 取款记录*/
function incomeListHistroy() {
    console.log(this);
    console.log($(this));
    console.log($(this).html());
    var id=$(this).data('id');
    window.location.href = contextPath + '/www/view/mine/incomeRecord.jsp?id='+id;
}


function initData(primaryId){
    $.ajax({
        url: contextPath+"/www/counselor/accountInfo",
        type:"post",
        async:false,
        data:{counselorId: primaryId},
        success:function(result){
            if(result.status == 0) {
                viewHtml('income', result.data, 'incomeTemp');
            }else {
                alert(result.errorMessage);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function viewHtml(IDom, data, tempId) {
    var navitem = document.getElementById(tempId).innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}
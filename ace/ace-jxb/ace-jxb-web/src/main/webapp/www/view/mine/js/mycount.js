function App() {
    console.log("=============================App Start==============================");

    console.log(window.location.href);
    var url =   window.location.href.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
};

function initData(primaryId){
    $.ajax({
        url: contextPath+"/www/counselor/accountInfo",
        type:"post",
        async:false,
        data:{counselorId: primaryId},
        success:function(result){
            if(result.status == 0) {
                viewHtml('income', result.data, 'incomeTemp');
                var total = 0;
                var rewardIncome = result.data.rewardIncome;
                var consultIncome = result.data.consultIncome;
                var monthIncome = result.data.monthIncome;
                var underlingIncome = result.data.underlingIncome;
                var courseIncome = result.data.courseIncome;
                if(rewardIncome != undefined){
                    total += parseFloat(rewardIncome);
                }
                if(consultIncome != undefined){
                    total += parseFloat(consultIncome);
                }
                if(monthIncome != undefined){
                    total += parseFloat(monthIncome);
                }
                if(underlingIncome != undefined){
                    total += parseFloat(underlingIncome);
                }
                if(courseIncome != undefined){
                    total += parseFloat(courseIncome);
                }
                $("#totalIncome").text(total);
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
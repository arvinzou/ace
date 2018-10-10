window.onload = function(){
    /**
     * 查询用户信息
     */
    $.ajax({
        url: "/cu/www/user/findByOpenId",
        type:"post",
        async:false,
        data:{},
        success:function(result){
            if(result.status == 0) {
                renderPage('userInfo', result.data, 'userInfo-tpl');
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
    /**
     * 爱心记录/爱心积分
     */
    $.ajax({
        url: "/cu/www/user/findDonateList",
        type:"post",
        async:false,
        data:{start:0, limit:9999},
        success:function(result){
            if(result.status == 0) {
                renderPage('nav_record', result.data, 'record-tpl');
                renderPage('scoreBox', result.data, 'score-tpl');

                var len = result.data.rows.length;
                var arr = result.data.rows;
                var total=0;
                var points = 0;
                for(var i=0; i<len; i++){
                    if(arr[i].donateAmount !='' && arr[i].donateAmount!=undefined){
                        total += parseFloat(arr[i].donateAmount);
                    }
                    if(arr[i].points !='' && arr[i].points!=undefined){
                        points += parseInt(arr[i].points);
                    }
                }
                $("#totalAmount").text(total);
                $("#amount").text(total);
                $("#totalPoint").text(points);
                $("#times").text(result.data.total);

            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });

    /**
     * 我的求助
     */
    $.ajax({
        url: "/cu/www/user/findMyProject",
        type:"post",
        async:false,
        data:{start:0, limit:9999},
        success:function(result){
            if(result.status == 0) {
                renderPage('projectList', result.data.rows, 'project-tpl');
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
};
function aboutus(){
    window.location.href = '/cu/www/about/about.html';
}

function apply(){
    window.location.href = '/cu/www/view/apply/apply.html';
}

function showProgress(id){
    window.location.href = '/cu/www/view/me/apply_progress.html?projectId='+id;
}
function hoverli(divId){
	$("#"+divId).removeClass('undis').addClass('dis');
	$("#"+divId).siblings().removeClass('dis').addClass('undis');
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

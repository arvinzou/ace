var projectId = null;
var status = null;
var openId = null;
window.onload = function(){
    $.ajax({
        url: "/cu/www/project/findList",
        type:"post",
        async:false,
        data:{start: 0, limit: 9999, type: "1"},
        success:function(result){
            if(result.status == 0) {
                projectId = result.data.rows[0].id;
                status = result.data.rows[0].status;
                renderPage('statistics', result.data.rows[0], 'project-tpl');
                renderPage('desscription', result.data.rows[0], 'des-tpl');
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });

    /**
     * 使用记录列表
     */
    if(projectId != null){
        $.ajax({
            url: "/cu/www/project/findUsedRecordList",
            type:"post",
            async:false,
            data:{projectId: projectId, start: 0, limit: 99999},
            success:function(result){
                if(result.status == 0) {
                    renderPage('useRecord', result.data, 'record-tpl');
                }else {
                    alert(result.errorMessage);
                }
            },
            error:function(){
                alert("系统服务内部异常！");
            }
        });

        /**
         * 捐赠列表
         */
        $.ajax({
            url: "/cu/www/project/findDonateListToday",
            type:"post",
            async:false,
            data:{projectId: projectId, start: 0, limit: 100},
            success:function(result){
                if(result.status == 0) {
                    renderPage('donatiteList', result.data.rows, 'doante-tpl');
                    if(result.data.rows.length < 1){
                        $(".donate_list").hide();
                    }else{
                        $(".donate_list").show();
                    }
                }else {
                    alert(result.info);
                }
            },
            error:function(){
                alert("系统服务内部异常！");
            }
        });

        /**
         * 一日捐榜单
         */
        $.ajax({
            url: "/cu/www/report/donateRank",
            type:"post",
            async:false,
            data:{start:0, limit: 999999, projectId: projectId, needOpenId: "1"},
            success:function(result){
                if(result.status == 0) {
                    renderPage('rankList', result.data.list, 'rank-tpl');
                    console.log("===================================aa"+result.data.list.length);
                    renderPage('totalAmount', result.data.list.length, 'total-tpl');
                    //renderPage('share_box', result.data.own, 'share-tpl');
                    openId = result.data.own.openid;
                }else {
                    alert(result.info);
                }
            },
            error:function(){
                alert("系统服务内部异常！");
            }
        });
    }
};

function donate(){
    if(projectId != null){
        window.location.href = '/cu/www/view/order/order.jsp?projectId='+projectId;
    }
}
/**
 * 跳转慈善榜单页面
 */
function donateRank(){
    if(projectId != null){
        window.location.href = '/cu/www/view/donatelist/donatelist.jsp?projectId='+projectId;
    }
}

/**
 * 使用记录查看更多
 */
function showMore(){
    if(projectId != null) {
        window.location.href = '/cu/www/view/daydonation/recordlist.jsp?projectId=' + projectId;
    }
}

/**
 * 传递爱心
 */
function transmit(){
    /*layer.open({
        type:1,
        content: $("#share_box").html(),
        shadeClose:true
    });*/
    if(openId == '' || openId == null || openId == undefined){
        alert("未查询到您的捐款信息！");
    }else{
        window.location.href = 'https://cswx.changde.gov.cn/cu/www/share/share.jsp?projectId='+projectId+'&openId='+openId;
    }
}
function troggle(obj, clazz){
    if($(obj).attr("name") == "down"){   //down展开，up收起
        $(obj).parent().siblings(clazz).removeClass("troggle");
        $(obj).html("<span class=\"opt\">收起</span><img src=\"img/up.png\" style=\"width: 0.3rem;height: 0.2rem;\">");
        $(obj).attr("name", "up");
    }else{
        $(obj).parent().siblings(clazz).addClass("troggle");
        $(obj).html("<span class=\"opt\">展开</span><img  src=\"img/down.png\" style=\"width: 0.3rem;height: 0.2rem;\">");
        $(obj).attr("name", "down");
    }
}
function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
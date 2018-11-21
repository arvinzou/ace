var page = 0;
var pageSize = 9999;

window.onload = function(){
    layer.open({
        type:1,
        content: $("#warning").html(),
        btn: '我知道了',
        shadeClose:false
    });

	$.ajax({
        url: "/cu/www/project/findList",
        type:"post",
        async:false,
        data:{start: page, limit: pageSize, type: "2"},
        success:function(result){
            if(result.status == 0) {
                renderPage('project', result.data.rows, 'project-tpl');
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
};

/**
 * 查看救急难项目详情
 */
function showProjectInfo(id){
    window.location.href = '/cu/www/jjndonation/donation.jsp?projectId='+id+'&type=2';
}

/**
 * 发起筹款
 */
function raiseMoney(){
    var time = new Date().getTime();
    window.location.href = '/cu/www/view/apply/read.jsp?v='+time;
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
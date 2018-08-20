function App() {
    initData();
};

function initData(){
    $.ajax({
        url: contextPath+"/www/reg/findInfo",
        type:"post",
        async:false,
        data:{},
        success:function(result){
            viewHtml('userInfo', result.data, 'userInfoTemp');
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

function showConsultOrder(){
    window.location.href = contextPath + '/www/view/history/index.jsp';
}

function showCourseOrder(){
    window.location.href = contextPath + '/www/view/purchaseRecord/index.jsp'
}
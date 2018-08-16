function loadlocal() {
    var urls = [];
    urls.push({path: contextPath, url: '/www/view/common/js/nav.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}
function App() {
    loadlocal();
    courseList();
}

function courseList(level){
    $.ajax({
        url: contextPath+ "/www/course/findList",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            start: 0,
            limit: 999,
            objects:level,
            auditRst:'1'
        },
        success:function(result){
            if(result.status == 0) {
                var courseTemp = document.getElementById('courseTemp').innerHTML;
                var html = juicer(courseTemp, {
                    data: result.data.rows
                });
                $("#courseList").html(html);
            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function courseDetail(id){
    window.location.href = contextPath + '/www/view/courseSummary/index.jsp?id='+id;
}
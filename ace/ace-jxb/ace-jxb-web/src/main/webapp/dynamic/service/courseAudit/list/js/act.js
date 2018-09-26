var loading = {};
var primaryId = null;
var videoUrl = null;
var params = {limit: 10};
var partId;
function App() {
    console.log("=============================App Start==============================");

}

window.onload = function(){
    primaryId =urlParams.did;
    initPartList();
}

function initPage(partId) {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: 10,
        visiblePages: 20,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
			params['start']=(num-1)*params.limit;
            params['initType']=type;
			params['partId']=partId;
            getPageList();
        }
    });
}
function initPartList(){
    var data = findPartList();
    try{
        renderPage('chapters', data.rows, 'chapterTemp');
        initPage(data.rows[0].id);
         partId=data.rows[0].id;
    }catch(e){}
}
function findPartList(){
    var resultData = null;
    $.ajax({
        url: contextPath + "/coursePart/findCoursePartList",
        type:"post",
        async:false,
        data:{
            page: 1,
            limit: 999,
            courseId: primaryId,
            orderBy: "createDate",
            sord: "asc"
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                resultData = result;
                initPage(result.rows[0].id);
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
    return resultData;
}
function getPageList(){
    var url = contextPath + "/courseSource/findCourseSourceList";
	startLoad();
    $.getJSON(url, params, function (result) {
        stopLoad();
        if (result.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total
                });
            }
            renderPage("courseList", result.rows, "courseTemp");
        }
    })
}

function renderPage(IDom, data, tempId) {
    var navitem = document.getElementById(tempId).innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function changeChapter(id){
    initPage(id);
}

function add(){
    var partId = $("#chapters .active").attr("datattr");
    window.location.href = contextPath+ '/dynamic/service/course/list/add/index.jsp?courseId='+primaryId+'&partId='+partId;
}

function editCourseSource(id){
	window.location.href = contextPath+ '/dynamic/service/courseAudit/list/edit/index.jsp?courseId='+urlParams.did+'&partId='+partId+'&id='+urlParams.id+'&did='+id;
}
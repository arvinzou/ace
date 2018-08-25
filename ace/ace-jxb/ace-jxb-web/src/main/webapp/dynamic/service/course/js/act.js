var loading = {};
function loadlocal() {
    var urls = [];
    urls.push({path: contextPath, url: '/content/common/js/jqPaginator.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadlocal();
}


window.onload = function (){
    initPage();    //初始化显示单品课程
}
var payType = "";
var videoUrl = "";
var params = {limit: 5, type: '1'};
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start']=(num-1)*params.limit;
            params['initType']=type;
            getPageList();
        }
    });
}
function t_query(){
    getPageList();
    return false;
}
function getPageList() {
    var url = contextPath+ "/course/findMyCourseList";
     params['name']=$("input[name=keyword]").val();
    startLoad();
    $.getJSON(url, params, function (result) {
        stopLoad();
        if (result.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total
                });
            }
            renderPage("courseList", result.rows, "list");
        }
    })
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function changeCourseType(type){
    if(type == '1'){
        $(".commonCourse").show();
        $(".specialCourse").hide();
    }else if(type == '2'){
        $(".commonCourse").hide();
        $(".specialCourse").show();
    }
    params['type']=type;
    initPage();
}
function edit(id){
     window.location.href = contextPath + '/dynamic/service/course/edit/index.jsp?id='+id+'&type='+params.type;
}


/**
 * 课程审核
 * @param id
 */
var courseId = null;
function openAudit(id, index){
    courseId = id;
    $("#auditOpt"+index).attr("data-toggle","modal");
    $("#auditOpt"+index).attr("data-target","#audit");
}

function add(type){
    window.location.href = contextPath+ '/dynamic/service/course/add/index.jsp?type='+type;
}
function audit(){
    startLoad();
    $.ajax({
        url: contextPath + "/course/audit",
        type:"post",
        async:false,
        data:{
            data:JSON.stringify({
                courseId: courseId,
                statement: $("textarea[name = 'message']").val(),
                rst: $("input[name='auditState']:checked").val()
            })
        },
        success:function(result){

            stopLoad();
            $("#audit").modal('hide');
            alert(result.errorMessage);
            if(result.status == 0) {
                getPageList();
            }
        },
        error:function(){
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function online(id){
    if(confirm("确定要上架吗？")){
        startLoad();
        $.ajax({
            url: contextPath + "/course/updateLineState",
            type:"post",
            async:false,
            data:{
               courseId :id,
               state:'1'
            },
            success:function(rst){
                stopLoad();
                if(rst.status == 0) {
                  getPageList();
                }else {
                    alert(rst.errorMessage);
                }
            },
            error:function(){
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}
function outline(id){
    if(confirm("确定要下架吗？")){
        startLoad();
        $.ajax({
            url: contextPath + "/course/updateLineState",
            type:"post",
            async:false,
            data:{
               courseId :id,
               state:'0'
            },
            success:function(rst){
                stopLoad();
                if(rst.status == 0) {
                  getPageList();
                }else {
                    alert(rst.errorMessage);
                }
            },
            error:function(){
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}
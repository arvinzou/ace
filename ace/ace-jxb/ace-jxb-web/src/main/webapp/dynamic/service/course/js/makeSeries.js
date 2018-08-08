var loading = {};
var primaryId = null;
function loadlocal() {
    var urls = [];
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/module.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/hotkeys.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/uploader.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/simditor.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/plupload/plupload.full.min.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/jcrop/jquery.Jcrop.min.js', type: 'js'});
    urls.push({path: contextPath, url: '/content/common/js/jqPaginator.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadlocal();
}

window.onload = function(){
    console.log(window.location.href);
    var url =   window.location.href.substring(1);
    primaryId = url.substring(url.indexOf('=')+1);

    findPartList();
}

function initpage(partId) {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 10,
        visiblePages: 20,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            findPartCourseList(num, type, partId);
        }
    });
}

function addSeries(){
    var partName = $("input[name='partName']").val();
    if(partName == '' || partName == undefined){
        alert("章节名称不能为空！");
    }
    $.ajax({
        url: contextPath + "/coursePart/insertCoursePart",
        type:"post",
        async:false,
        data:{jsons:JSON.stringify({
                courseId: primaryId,
                name:partName
            })
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                alert("创建成功！");
                findPartList();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function findPartList(){
    $.ajax({
        url: contextPath + "/coursePart/findCoursePartList",
        type:"post",
        async:false,
        data:{
            page: 1,
            limit: 999,
            courseId: primaryId
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                viewHtml('chapters', result.rows, 'chapterTemp');
                initpage(result.rows[0].id);
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function findPartCourseList(num, type, partId){
    var url = contextPath + "/courseSource/findCourseSourceList";
    var data = {
        page: num,
        limit: 10,
        partId: partId
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            viewHtml("courseList", result.rows, "courseTemp");
        }
    })
}

function viewHtml(IDom, data, tempId) {
    var navitem = document.getElementById(tempId).innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function changeChapter(id){
    initpage(id);
}

function createCourseSource(){
    var partId = $("#chapters  .active").attr("datattr");
    alert(partId);
    window.location.href = contextPath+ '/dynamic/service/course/make.jsp?courseId='+primaryId+'&partId='+partId;
}
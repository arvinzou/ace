window.onload = function (){
    initpage('1');    //初始化显示单品课程
}
var payType = "";

function initpage(courseType) {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 10,
        visiblePages: 20,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            getCourseList(num, type, courseType);
        }
    });
}

function getCourseList(num, type, courseType) {
    var url = contextPath+ "/course/findCourseList";
    var data = {
        page: num,
        limit: 10,
        type: courseType                  //1是单节课程，2是系列课程
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            viewHtml("courseList", result.rows, "list");
            viewHtml("makeCourse", result.rows[0], "makeTemp");
            viewHtml("createCourse", result.rows[0], "createTemp");
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

function makecourse(){
    var id = $('input[name="course"]:checked').val();
    if(id == '' || id == undefined){
        alert("请选择要制作的课程！");
        return;
    }
    window.location.href = contextPath + '/dynamic/service/course/make.jsp?id='+id;
}

function makeSeriesCourse(){
    var id = $('input[name="course"]:checked').val();
    if(id == '' || id == undefined){
        alert("请选择要制作的课程！");
        return;
    }
    window.location.href = contextPath + '/dynamic/service/course/makeSeries.jsp?id='+id;
}
function deleteCourse(id){
    $.ajax({
        url: contextPath + "/course/deleteCourseByCourseId",
        type:"post",
        async:false,
        data:{
            jsons:JSON.stringify({
               id: id
            })
        },
        success:function(result){
            if(result.status == 0) {
                alert("删除成功！");
                window.location.reload();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function changeCourseType(type){
    initpage(type);
}

function clickEdit(id){
    findCourseInfoById(id);
}

function findCourseInfoById(id){
    /**
     * 查询课程基本信息
     */
    $.ajax({
        url: contextPath + "/course/selectCourseByPrimaryKey",
        type:"post",
        async:false,
        data:{
            id: id
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('courseBasic', result.value, 'editCourseTemp');
                initEditor();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function initEditor(){
    var editor = new Simditor({
        textarea: $('#courseIntro'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: portalPath + '/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
}

function confirmEdit(id, type){
    var courseName = $("input[name = 'courseName']").val();
    var courseCover = $("#courseCover").attr("src");
    var introduction = $("textarea[name = 'introduction']").val();
    var price = $("input[name ='price']").val();
    payType = $(".payType .cactive").text()=="免费"?"0":"1";
    $.ajax({
        url: contextPath + "/course/updateCourse",
        type:"post",
        async:false,
        data:{
            jsons:JSON.stringify({
                id: id,
                type: type,
                category: '1',
                mediType: '1',
                name: courseName,
                cover: courseCover,
                duration: 0,
                costType: payType,
                cost: price,
                introduce: introduction
            })
        },
        success:function(result){
            if(result.status == 0) {
                alert("修改成功！");
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}
function payTypeCheck(dom) {
    if (dom == 'noPay') {
        payType = '0';
        $('#' + dom).removeClass('uncactive').addClass('cactive');
        $('#pay').removeClass('cactive').addClass('uncactive');
    } else {
        payType = '1';
        $('#' + dom).removeClass('uncactive').addClass('cactive');
        $('#noPay').removeClass('cactive').addClass('uncactive');
    }
}

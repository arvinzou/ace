window.onload = function () {
     jQuery(function ($) {
        initPage();
     });
}

function initPage(){
    initClassList();
}



function initClassList() {
    startLoad();
    $.ajax({
        url: contextPath + "/mailList/getClassList",
        type: "post",
        async: false,
        data: {
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data = result.value;
                render('#select1', data, 'tpl-select-list');
                initClasses($("#classesId").val());
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function initClasses(classId){
 startLoad();
    $.ajax({
        url: contextPath + "/mailList/getList",
        type: "post",
        async: false,
        data: {
            classesId:classId
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data = result.value;
//                render('#classStudent', data, 'tpl-student');
               initGroup(classId);

            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function initGroup(classId){
    startLoad();
    $.ajax({
        url: contextPath + "/files/findFilesList",
        type: "post",
        async: false,
        data: {
            classesId:classId
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data = result.value;
              render('#groupStudent', data, 'tpl-group');
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

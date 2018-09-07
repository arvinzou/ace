var mySwiper;
var studioStatus = null;

window.onload = function (){

    initpage();
}


/*初始化轮播图*/
function initSwriper() {
    mySwiper = new Swiper('.swiper-container', {
        loop: true,
        // 如果需要分页器
        pagination: {
            el: '.swiper-pagination',
        },

        // 如果需要前进后退按钮
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },

        // 如果需要滚动条
        scrollbar: {
            el: '.swiper-scrollbar',
        },
    })
}


function initpage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 10,
        visiblePages: 20,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            getStudioList(num, type);
        }
    });
}

function getStudioList(num, type) {
    var url = contextPath+ "/studio/findStudioList";
    var data = {
        page: num,
        limit: 10,
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            viewHtml("audioList", result.rows);
        }
    })
}

function viewHtml(IDom, data) {
    var navitem = document.getElementById('list').innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function audit(){
    var auditId = $("#auditId").val();
    var auditRs = $("input[name='radio']:checked").val();
    if(auditRs == '' || auditRs == undefined){
        alert("请选择审核状态！");
        return;
    }
    if(auditRs == studioStatus){
        alert("该工作室已经审核，不需要重复审核！");
        return;
    }
    $.ajax({
        url: contextPath +"/studio/audit",
        type:"post",
        async:false,
        data:{studioId: auditId, auditRs: auditRs},
        success:function(result){
            if(result.status == 0) {
                alert("审核成功！");
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

// function detail(){
//     var auditId = $("#auditId").val();
//     $.ajax({
//         url: contextPath +"/studio/selectStudioByPrimaryKey",
//         type:"post",
//         async:false,
//         data:{id: auditId},
//         success:function(result){
//             if(result.status == 0) {
//                 console.log(result);
//                 var data = result.value;
//                 var temp = document.getElementById('stdioInfo').innerHTML;
//                 var html = juicer(temp, {
//                     info: data
//                 });
//                 $("#info").html(html);
//             }else {
//                 alert(result.errorMessage);
//             }
//         },
//         error:function(){
//             alert("系统服务内部异常！");
//         }
//     });
// }
function edit(id){
    if (mySwiper) {
        mySwiper.destroy;
    }
    $('#studioInfo').modal('show');
    if (id) {
        var url = "/jxb/studio/selectStudioByPrimaryKey";
        var data = {
            id: id
        }
        $.getJSON(url, data, function (result) {
            if (result.status == 0) {
                var navitem = document.getElementById('temp_modalstudioInfo').innerHTML;
                var html = juicer(navitem, {
                    data: result.value
                });
                $("#modalstudioInfo").html(html);
                initSwriper();
            }
        });
    }
}
function setval(id, status){
    $("#auditId").val(id);
    studioStatus = status;
}
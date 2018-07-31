window.onload = function () {
    initpage();
    //getCounselorList();
    $('#counselorList').on('click', '.mt-card-social .info', detailInformation);
    $('#counselorList').on('click', '.mt-card-social .pass', approved);
    $('#counselorList').on('click', '.mt-card-social .refusal', auditFailed);
    $('.search_btn').click(searchList);
}

function searchList() {
    
}

function approved() {
    var $that = $(this);
    var id = $that.parents('.mt-card-social').data('id');
    swal({
        title: '确定通过审核？',
        text: '你将无法恢复它！',
        type: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '确定！',
        cancelButtonText: '取消！',
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: false
    }).then(function (data) {
        if (data.value) {
            if (startProcessing(id, 1) == 0) {
                swal({
                    text: "审核通过！",
                    type: "success",
                    timer: 1000,
                    showConfirmButton: false
                });
                return
            }
            swal({
                text: "审核过程失败,稍后重试！",
                type: "error",
                timer: 1000,
                showConfirmButton: false
            });
            return

        }
    })
}

function auditFailed() {
    var $that = $(this);
    var id = $that.parents('.mt-card-social').data('id');
    swal({
        title: '确定拒绝Ta的申请？',
        text: '你将无法恢复它！',
        type: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '确定！',
        cancelButtonText: '取消！',
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: false
    }).then(function (data) {
        if (data.value) {
            if (startProcessing(id, 2) == 0) {
                swal({
                    text: "操作成功！",
                    type: "success",
                    timer: 1000,
                    showConfirmButton: false
                });
                return
            }
            swal({
                text: "操作失败,稍后重试！",
                type: "error",
                timer: 1000,
                showConfirmButton: false
            });
            return

        }
    })
}


function startProcessing(id, rst) {
    var url = "/jxb/counselor/audit"
    var data = {
        counselorId: id,
        rst: rst,
    }
    $.getJSON(url, data, function (result) {
        return result.status
    })
}


function detailInformation() {
    var $that = $(this);
    var id = $that.parents('.mt-card-social').data('id');
    var url = "/jxb/counselor/selectCounselorByPrimaryKey"
    var data = {
        id: id
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewHtml("info", result.value);
            $('#myModal').modal('show');
        }
    })
}


function initpage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 20,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            getCounselorList(num, type);
        }
    });
}

function getCounselorList(num, type) {
    var url = "/jxb/counselor/findCounselorList";
    var data = {
        name: '',
        page: num,
        limit: 20,
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            viewHtml("counselorList", result.rows);
        }
    })
}


function viewHtml(IDom, data) {
    var navitem = document.getElementById('temp_' + IDom).innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}




jQuery(function ($) {
    $('#btn-search').on('click', function () {
        $('#fm-search').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date()
                });

                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });
});

function preview(id, title) {
    window.open(contextPath + '/dynamic/service/company/view.jsp?companyId=' + id);
}
function recoverData(id, status) {
    if (status != "0") {
        alert("会员状态非法，无需恢复！")
        return;
    }
    $.ajax({
        type: "post",
        url: contextPath + "/fopCompany/recoverData",
        data: {id: id, type: '0'},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {

            if (rst.state) {
                alert('恢复成功');
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
            } else {
                alert(rst.errorMessage);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

var show = false;
function del(rowid) {
    console.log(rowid);
    jQuery(cfg.grid_selector).jqGrid('delGridRow',
        rowid,
        {
            beforeShowForm: function (e) {
                var form = $(e[0]);
                if (!show) {
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
                }

                show = true;

            }
        });
}

function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
}
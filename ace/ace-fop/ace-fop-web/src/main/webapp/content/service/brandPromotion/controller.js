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
                // console.log(params);
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });

    $('#btn-view-add').on(
        'click',
        function () {
            jQuery(cfg.grid_selector).jqGrid(
                'editGridRow',
                'new',
                {
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: false,
                    beforeSubmit: function (postdata) {
                        postdata.content = editor.getValue();
                        return [true, "", ""];

                    },
                    beforeShowForm: function (e) {
                        initSimditor($("textarea[name=content]"), null);
                        appendUploadBtn("fileUrl");
                    }
                })
        });
});

function preview(id, title) {

    window.open(contextPath + "/www/html/band/band_info.html?id=" + id);
}

function loadView(id) {
    $.ajax({
        type: "post",
        url: cfg.view_load_data_url,
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            $.each(rst.value, function (key, value) {
                if (key == 'category') {
                    value = rsd(value, '83');
                }
                if (key == 'status') {
                    value == "1" ? "正常" : "关闭";
                }
                if (key.indexOf('Date') != -1 || key.indexOf('time') != -1 || key.indexOf('Time') != -1 || key.indexOf('birthday') != -1) {
                    value = Common.DateFormatter(value);
                }
                $("#dialog-message-view").find('#' + key).html(value);
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

function initSimditor(textarea, text) {
    editor = new Simditor({
        textarea: textarea,//jQuery对象，HTML元素或选择器字符串可以传递给这个选项
        params: {},// 在textarea中插入隐藏的输入来存储参数（键值对）。
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: portalPath + '/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
    if (text) {
        editor.setValue(text);
    }
}

function loadText(id) {
    $.ajax({
        type: "post",
        url: cfg.view_load_data_url,
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            initSimditor($("textarea[name=content]"), rst.value.content);
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

function edit(rowid) {
    console.log(rowid);
    jQuery(cfg.grid_selector).jqGrid(
        'editGridRow',
        rowid,
        {
            closeAfterAdd: true,
            recreateForm: true,
            viewPagerButtons: true,
            beforeShowForm: function (e) {
                loadText(rowid);
                appendUploadBtn("fileUrl");
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
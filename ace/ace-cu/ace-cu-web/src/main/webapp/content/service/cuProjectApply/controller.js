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

    $('#btn-view-add').on('click', function () {
        jQuery(cfg.grid_selector).jqGrid(
            'editGridRow',
            'new',
            {
                closeAfterAdd: true,
                recreateForm: true,
                viewPagerButtons: false,
                beforeShowForm: function (e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find(
                        '.ui-jqdialog-titlebar').wrapInner(
                        '<div class="widget-header" />')
                    style_edit_form(form);
                }
            })
    });

});

function preview(id, title) {
    $('#modal-preview').modal('show');
    loadView(id);
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
                //bind value
                $("#modal-preview").find('#' + key).html(value);
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
        data: {id: id},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            console.log(JSON.stringify(rst));
            initSimditor($("textarea[name=description]"), rst.value.content);
        },
        error: function () {
            alert("加载错误！");
        }
    });
}
function initIdCardInfo(applyId, divId) {
    $.ajax({
        type: "get",
        url: contextPath + "/cuProjectApply/findResList",
        data: {applyId: applyId, resTypes: "0"},
        success: function (rst, textStatus) {
            renderImage(rst.data, divId);
        }
    });
}
function initFP(applyId, divId) {
    $.ajax({
        type: "get",
        url: contextPath + "/cuProjectApply/findResList",
        data: {applyId: applyId, resTypes: "1"},
        success: function (rst, textStatus) {
            renderImage(rst.data, divId);
        }
    });
}
function initZD(applyId, divId) {
    $.ajax({
        type: "get",
        url: contextPath + "/cuProjectApply/findResList",
        data: {applyId: applyId, resTypes: "2"},
        success: function (rst, textStatus) {
            renderImage(rst.data, divId);
        }
    });
}
function initOthers(applyId, divId) {
    $.ajax({
        type: "get",
        url: contextPath + "/cuProjectApply/findResList",
        data: {applyId: applyId, resTypes: "3"},
        success: function (rst, textStatus) {
            renderImage(rst.data, divId);
        }
    });
}

function renderImage(rst, divId) {
    var html = new Array();
    html.push('<ul class="ace-thumbnails clearfix">');
    $.each($(rst), function (i, o) {
        html.push('<li>');
        html.push('<a href="' + o.resUrl + '" title="' + o.resName + '" target="view_window" data-rel="colorbox" class="cboxElement">');
        html.push('<img height="200" width="200" class="photo" src="' + o.resUrl + '">');
        html.push('</a>');
        // html.push('<div style="text-align:center">');
        // html.push(o.nickname);
        // html.push('</div>');
        html.push('</li>');
    });
    html.push('</ul>');
    $("#" + divId).html(html.join(""));
}


//编辑
function edit(rowid) {

    jQuery(cfg.grid_selector).jqGrid('editGridRow', rowid, {
        closeAfterAdd: true,
        recreateForm: true,
        viewPagerButtons: true,
        beforeSubmit: function (postdata) {
            //提交按钮，不需要修改
            return [false, "", ""];
        },
        beforeShowForm: function (e) {
            var form = $(e[0]);
            form.closest('.ui-jqdialog').find(
                '.ui-jqdialog-titlebar').wrapInner(
                '<div class="widget-header" />');
            //富文本编辑器
            loadText(rowid);
        }
    });
    //图片加载
    $("#tr_title_ID h5").after("<div id='custom-idCardInfo'></div>");
    $("#tr_title_FP h5").after("<div id='custom-fp'></div>");
    $("#tr_title_ZD h5").after("<div id='custom-zd'></div>");
    $("#tr_title_OT h5").after("<div id='custom-others'></div>");
    initIdCardInfo(rowid, "custom-idCardInfo");
    initFP(rowid, "custom-fp");
    initZD(rowid, "custom-zd");
    initOthers(rowid, "custom-others");
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

function audit(rowId, statusVal) {
    console.log("******************" + rowId + "||" + statusVal);
    if (statusVal != "1") {
        alert("请勿重复受理！")
        return;
    }

    //审核弹框
    $('#modal-audit').modal('show');
    //审核保存按钮事件监听
    $('#btn-view-audit').on('click', function (e) {
        //for testing
        var audit_result = $('input[name="audit_result"]:checked').val();
        var audit_opinion = $('#audit_opinion').val();
        if (audit_result == undefined) {
            alert("请选择审核结果!");
            return;
        }
        $.ajax({
            type: "post",
            url: contextPath + "/cuProjectApply/audit",
            data: {id: rowId, auditResult: audit_result, auditOpinion: audit_opinion},
            beforeSend: function (XMLHttpRequest) {
                startLoad();
            },
            success: function (rst, textStatus) {
                stopLoad();
                if (rst) {
                    console.log(JSON.stringify(rst));
                    $('#modal-audit').modal('hide');
                    alert(rst.errorMessage);
                    //重载数据
                    jQuery(cfg.grid_selector).jqGrid('setGridParam', {page: 1}).trigger("reloadGrid");
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
                stopLoad();
            },
            error: function () {
                alert("错误提醒", "系统发生异常,请联系系统管理员！");
                stopLoad();
            }
        });
    });

}

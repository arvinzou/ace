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
                        postdata.description = editor.getValue();
                        return [true, "", ""];

                    },
                    beforeShowForm: function (e) {
                        initSimditor($("textarea[name=description]"), null);
                    }
                })
        });
         //初始化事件
         initEvents();
});
function initEvents() {
    //审核模态框
    $('#modal-audit').on('shown.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        console.log(id);
        var modal = $(this);
        modal.find('.modal-body input[name=id]').val(id);
    });
    //审核框 确定按钮
    $('#modal-audit .btn-primary').on('click', function () {
        $('#modal-audit form').submit();
    });
    //审核框 提交事件
    $('#modal-audit form').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var rstVal = $('input[name="rst"]:checked').val();
            if (rstVal == undefined) {
                alert("请选择审核结果!");
                return;
            }
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date()
            });
            console.log(params);
            audit(params);
            return false;
        }
    });
     $(".btn-group .btn").bind('click', function (event) {
            $(event.target).siblings().removeClass("active");
            console.log(event);
            $(event.target).addClass("active");
        });
}
function audit(params) {
    console.log(params);
    startLoad();
    $.ajax({
        type: "post",
        url: contextPath + "/fopLoanProduct/audit",
        data: {
            id: params.id,
            auditResult: params.rst,
            auditOpinion: params.message
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            stopLoad();
            $("#modal-audit").modal('hide');
            alert(rst.errorMessage);
            if (rst.status == 0) {
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function preview(id, title) {
    window.open(contextPath + "/www/html/finance/product_info.html?id=" + id);
}

function edit(rowid){
    console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						rowid,
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
                            beforeSubmit: function (postdata) {
                                postdata.description = editor.getValue();
                                return [true, "", ""];
                    },
							beforeShowForm : function(e) {
							loadText(rowid);
							}
						});
}
var show=false;
function del(rowid){
    console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid('delGridRow',
    rowid,
    {
        beforeShowForm : function(e) {
            var form = $(e[0]);
            if(!show){
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
            }

            show=true;

        }
    });
}

function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam',{postData : params}).trigger("reloadGrid");
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
            initSimditor($("textarea[name=description]"), rst.value.description);
        },
        error: function () {
            alert("加载错误！");
        }
    });
}
var params={};
function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam',{postData : params}).trigger("reloadGrid");
}
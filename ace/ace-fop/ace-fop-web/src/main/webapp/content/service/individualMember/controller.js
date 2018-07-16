jQuery(function ($) {
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function (title) {
            var $title = this.options.title || '&nbsp;'
            if (("title_html" in this.options)
                && this.options.title_html == true)
                title.html($title);
            else
                title.text($title);
        }
    }));
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
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find(
                            '.ui-jqdialog-titlebar').wrapInner(
                            '<div class="widget-header" />')
                        style_edit_form(form);
                        //添加额外按钮显示
                        appendButtons();
                    }
                })
        });
    $('#btn-view-edit').on(
        'click',
        function () {
            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
                'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap,
                    $.jgrid.nav.alerttext)
            }
            jQuery(cfg.grid_selector).jqGrid(
                'editGridRow',
                gr,
                {
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: true,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find(
                            '.ui-jqdialog-titlebar').wrapInner(
                            '<div class="widget-header" />')
                        style_edit_form(form);
                        //添加额外按钮显示
                        appendButtons();
                        //法人号码一经注册，则不能修改
                        $("#lpMobile").attr("readOnly", "true");
                    }
                })
        });
    $('#btn-view-del').on(
        'click',
        function () {

            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
                'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap,
                    $.jgrid.nav.alerttext);
                return;
            }
            jQuery(cfg.grid_selector).jqGrid(
                'delGridRow',
                gr,
                {
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
    var dialog = $("#dialog-message-view").removeClass('hide').dialog({
        modal: false,
        width: 800,
        title: "<div class='widget-header widget-header-small'><div class='widget-header-pd'>" + title + "</div></div>",
        title_html: true,
        buttons: [

            {
                html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                "class": "btn btn-info btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
    $(dialog).parent().css("top", "1px");
    $(dialog).css("max-height", window.innerHeight - layoutTopHeight + 50);
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
                //企业性质
                if (key == "companyProperty") {
                    value = rsd(value, "134");
                }
                //status
                if (key == "status") {
                    if ("1" == value) {
                        value = "非会员";
                    }
                    if ("2" == value) {
                        value = "会员";
                    }
                }
                //文化程度
                if (key == "lpEducation") {
                    value = rsd(value, "10");
                }
                //民族
                if (key == "lpNationality") {
                    value = rsd(value, "09");
                }
                //性别
                if (key == "lpSex") {
                    value = rsd(value, "01");
                }
                //所在地区
                if (key == "areaCode") {
                    value = "areaCode";//rsd(value, "134");
                }
                //企业性质
                if (key == "companyProperty") {
                    value = rsd(value, "134");
                }
                //日期格式化
                if (key.indexOf('Dt') != -1 ||
                    key.indexOf('Date') != -1 ||
                    key.indexOf('time') != -1 ||
                    key.indexOf('Time') != -1 ||
                    key.indexOf('birthday') != -1) {
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


function appendButtons() {
    //企业通讯地址
    appendMapBtn("address");
    //图片logo上传
    appendUploadBtn("companyLogo");
}

/**
 * 1、添加地图位置的指针*/
function appendMapBtn(id) {
    var html = new Array();
    html.push("<a id='btn-map-add-"
        + id
        + "' class='ace-icon fa fa-location-arrow bigger-110' href='javascript:false'>选取</a>");
    $("#" + id).after(html.join(''));
    $('#btn-map-add-' + id).on('click', function () {
        window.open(portalPath + "/dynamic/common/map.jsp");
    });
}

/**
 * 2、自动填写地址
 * @param latitude
 */
function latitude(latitude) {
    $("#latitude").val(latitude);
}

function longitude(longitude) {
    $("#longitude").val(longitude);
}

function addr(addr) {
    $("#address").val(addr);
}
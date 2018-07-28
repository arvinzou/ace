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
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find(
                            '.ui-jqdialog-titlebar').wrapInner(
                            '<div class="widget-header" />')
                        style_edit_form(form);
                    }
                })
        });

    //btn-view-edit
    $('#btn-view-audit').on(
        'click',
        function () {
            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
                'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap,
                    $.jgrid.nav.alerttext)
            }
            var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
            //1-未审核，2-已审核
            if (rowData.status == "2") {
                alert("该流程已被处理过了!");
                return;
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

function preview(id, title, flowType, fromId) {
    var dialogId = "#fc-dialog-message-view";
    var postUrl = cfg.view_load_data_url;
    var tplId = "";
    if (flowType == '0') {
        id = fromId;
        dialogId = "#c-dialog-message-view";
        postUrl = contextPath + '/fopCompany/selectFopCompanyByPrimaryKey';
        tplId = 'tpl-company';
    } else if (flowType == '1') {
        id = fromId;
        dialogId = "#a-dialog-message-view";
        postUrl = contextPath + '/fopAssociation/selectFopAssociationByPrimaryKey';
        tplId = 'tpl-association';
    }

    //show dialog
    var dialog = $(dialogId).removeClass('hide').dialog({
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
    //render data
    loadView(id, postUrl, dialogId, tplId);
}
function loadView(id, postUrl, dialogId, tplId) {
    $.ajax({
        type: "post",
        url: postUrl,
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            // console.log("============================rst.value:" + rst.value)
            var tpl = document.getElementById(tplId).innerHTML;//'tpl-company'
            var renderHtml = juicer(tpl, rst.value);
            // console.log("========================renderHtml:" + renderHtml);
            $(dialogId).html(renderHtml);
            //格式化值
            $.each(rst.value, function (key, value) {
                //企业类型 "0": "企业会员", "4": "个人会员"},//, "1": "团体企业", "2": "律师事务所", "3": "银行机构"
                if (key == "companyType") {
                    var rst = "";
                    switch (value) {
                        case '0' :
                            rst = "企业会员";
                            break;
                        case '1' :
                            rst = "团体企业";
                            break;
                        case '2' :
                            rst = "律师事务所";
                            break;
                        case '3' :
                            rst = "银行机构";
                            break;
                        case '4' :
                            rst = "个人会员";
                            break;
                        default :
                            rst = "N/A";
                    }
                    value = rst;
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
                // $(dialogId).find('#' + key).html(value);
                $(dialogId).find('span[name=' + key + ']').html(value);
                $(dialogId).find('div[name=' + key + ']').html(value);

            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}
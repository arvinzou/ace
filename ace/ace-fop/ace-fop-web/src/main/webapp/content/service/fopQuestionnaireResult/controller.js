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

    $('#btn-view-del').on('click', function () {
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

function del(rowid) {
    console.log(rowid);
    jQuery(cfg.grid_selector).jqGrid('delGridRow', rowid, {
        beforeShowForm: function (e) {
            var form = $(e[0]);
            form.closest('.ui-jqdialog')
                .find('.ui-jqdialog-titlebar')
                .wrapInner('<div class="widget-header" />');
            //style_edit_form(form);
        }
    });
}
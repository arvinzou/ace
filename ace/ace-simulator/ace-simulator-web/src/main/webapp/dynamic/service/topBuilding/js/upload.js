jQuery(function ($) {
    init_uploader({});
});

function init_uploader(p) {
    $("#uploader").pluploadQueue({
        runtimes: 'html5,flash,silverlight,html4',
        chunk_size: '1mb',
        unique_names: true,
        multipart_params: p,
        filters: {
            max_file_size: '10mb',
            mime_types: [
                {title: "Excel files", extensions: "xls,xlsx"}
            ]
        },
        resize: {width: 320, height: 240, quality: 90},
        url: contextPath + '/topBuilding/importXls'
    });
    var uploader = $('#uploader').pluploadQueue();
    uploader.bind("UploadComplete", function () {

    });
    uploader.bind("FileUploaded", function (uploader, file, responseObject) {
        console.log(responseObject.response);
        var rst = JSON.parse(responseObject.response);
        if (!rst.state) {
            alert(rst.errorMessage);
        } else {
            alert(rst.errorMessage);
            $('#modal-import').modal('hide');
            jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
            initPreview(p.id);
        }
    });
}

function reset_uploader(p) {
    var uploader = $('#uploader').pluploadQueue();
    uploader.splice();
    uploader.refresh();
    init_uploader(p);
}
jQuery(function ($) {
    init_uploader({});
});

function init_uploader(p) {
    $("#uploader").pluploadQueue(p);
    var uploader = $('#uploader').pluploadQueue();
    uploader.bind("UploadComplete", function () {

    });
    uploader.bind("FileUploaded", function (uploader, file, responseObject) {
        console.log(responseObject.response);
        var rst = JSON.parse(responseObject.response);
        if (!rst.state) {
            alert(rst.errorMessage);
        } else {
            // alert(rst.errorMessage);
            $('#modal-import').modal('hide');
            jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
        }
    });
}

function reset_uploader(p) {
    var uploader = $('#uploader').pluploadQueue();
    uploader.splice();
    uploader.refresh();
    init_uploader(p);
}
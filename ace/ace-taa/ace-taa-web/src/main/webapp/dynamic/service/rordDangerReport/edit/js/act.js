var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑路况上报</span></li>");
        initForm();
        initEvents();
        appendUploadImageBtn();

    });
}

function appendUploadImageBtn() {
    $("#btn-upload-add").on('click',
        function (e) {
            e.preventDefault();
            var config = {
                extensions: "jpg,gif,png,bmp",
                url: portalPath + '/files/uploadImage.do',
                //    target: id,
                multipart_params: {}
            };

            reset_uploader(config);
            $('#modal-upload').modal('show');

        });

}

function del(obj) {
    $(obj).closest('li').remove();
}

function reset_uploader(config) {
    var uploader = $('#uploader').pluploadQueue();
    uploader.splice();
    uploader.refresh();
    init_uploader(config);
}

function initEditor() {
    editor = new Simditor({
        textarea: $('textarea[name=introduce]'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol',
            'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'
        ],
        upload: {
            url: portalPath + '/files/uploadImage.do',
            params: null,
            fileKey: 'file',
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
}

/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}



function initPage() {
    //   initEditor();
//   initUpload();
    /*  $("input[name=reportDate]").datetimepicker({
          format: 'yyyy-mm-dd hh:ii:ss',
          language: 'zh-CN',
          weekStart: 1,
          todayBtn: 1, //显示‘今日’按钮
          autoclose: 1,
          todayHighlight: 1,
          startView: 2,
          minView: 'hour', //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
          clearBtn: true, //清除按钮
          forceParse: 0
      });*/
    $('input[name=reportDate]').focus(function () {
        $(this).blur(); //不可输入状态
    });


    $('input[name=roadManId]').combogrid({
        panelWidth: 500,
        idField: 'id',
        textField: 'name',
        url: contextPath + '/roadMan/getListByCondition',
        mode: 'remote',
        fitColumns: true,
        method: 'get',
        columns: [
            [{
                field: 'name',
                title: '姓名',
                width: 100
            }, {
                field: 'orgName',
                title: '单位',
                width: 100
            }, {
                field: 'areaName',
                title: '县区',
                width: 100
            }]

        ],

        onSelect: function (rowIndex, rowData) {
            console.log(rowData);
            $("input[name=areaCode]").val(rowData.areaCode);
        }
    });

    $('input[name=roadSectionId]').combogrid({
        panelWidth: 500,
        idField: 'id',
        textField: 'name',
        url: contextPath + '/roadSection/getListByCondition',
        mode: 'remote',
        fitColumns: true,
        method: 'get',
        columns: [
            [{
                field: 'name',
                title: '路段名称',
                width: 100
            }, {
                field: 'roadManName',
                title: '路长',
                width: 100
            }, {
                field: 'startName',
                title: '路段起始',
                width: 100
            }, {
                field: 'endName',
                title: '路段截止',
                width: 100
            }]
        ],
        onSelect: function (rowIndex, rowData) {
            console.log(rowData);
            $("#roadManId").combogrid("grid").datagrid("loadData", [{
                id: rowData.roadManId,
                name: rowData.roadManName,
                orgName: rowData.orgName,
                areaName: rowData.areaName
            }]);
            $('#roadManId').combogrid('setValue', rowData.roadManId);
            $("input[name=areaCode]").val(rowData.areaCode);
        }
    });
}

function initEvents() {
    /*表单验证*/
    /*监听表单提交*/
    $('#fm-edit').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date(),
//coverUrl: $('#coverUrl').attr("src")
            });
            console.log(params);
            save(params);
            return false;
        }
    });
}

/*保存表单**/
function save(params) {
    $.extend(params, {});
    var ChangedImagesUrl = [];
    startLoad();
    var liImg = $("#images li img");
    if (liImg.length != null) {
        for (var i = 0; i < liImg.length; i++) {
            ChangedImagesUrl.push($(liImg[i]).attr("src"));
        }
    }
    params.ChangedImagesUrl = ChangedImagesUrl;
    startLoad();
    $.ajax({
        url: contextPath + "/rordDangerReport/updateRordDangerReport",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href = '../index.jsp?id=' + urlParams.id;
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function initForm() {
    startLoad();
    $.ajax({
        url: contextPath + "/rordDangerReport/selectRordDangerReportByPrimaryKey",
        type: "post",
        async: false,
        data: {id: urlParams.did},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                data['dict'] = staticDictObject;

                render('#fm-edit', data, 'tpl-fm');
                initPage();
//富文本填值
//editor.setValue(data['o'].summary);
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });


}

function latitude(latitude) {
    $("input[name=latitude]").val(latitude);
}

function longitude(longitude) {
    $("input[name=longitude]").val(longitude);
}

function addr(addr) {
    $("input[name=address]").val(addr);
}
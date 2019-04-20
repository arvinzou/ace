var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>创建分区管理</span></li>");
        initPage();
        initEvents();
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
    initForm();
}

function initEvents() {
    /*表单验证*/
    $("#fm-add").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            district: {required: true, maxlength: 50}, name: {required: true, maxlength: 50}
        },
        messages: {
            district: {
                required: "请输入行政区划",
                maxlength: "行政区划字符长度不能超过50"
            }, name: {
                required: "请输入分区名称",
                maxlength: "分区名称字符长度不能超过50"
            }
        }
    });
    /*监听表单提交*/
    $('#fm-add').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date(),
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
    startLoad();
    $.ajax({
        url: contextPath + "/topSubarea/insertTopSubarea",
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
    var data = staticDictObject;
    render('#fm-add-panel', data, 'tpl-fm-add');

    $(".form-body input[name='district']").combotree({
        url: portalPath + "/system/selectProvinceTreeList.do?id=00",
        method: 'get',
        label: '',
        labelPosition: 'top',
        valueField: "id",
        textField: "text",
        lines: true,
        queryParams: {},
        onBeforeSelect: function (node) {
            // 控制职能选择子节点
            // if (!$(this).tree('isLeaf', node.target)) {
            //     $(this).combo("showPanel");
            //     return false;
            // }
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

var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑分区管理</span></li>");
        initForm();
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

function initPage(dataObj) {

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
        },
        onLoadSuccess: function (node, data) {
            defaultValue('district', dataObj['o'].district, dataObj['o'].districtName);
        }
    });
}

function defaultValue(cbtid, defVal, defText) {
    var combotree = $("#" + cbtid);
    var tree = combotree.combotree('tree');
    var defNode = tree.tree("find", defVal);
    if (!defNode) {
        tree.tree('append', {
            data: [{
                id: defVal,
                text: defText
            }]
        });
        defNode = tree.tree("find", defVal);
        combotree.combotree('setValue', defVal);
        tree.tree('select', defNode.target);
        defNode.target.style.display = 'none';
    } else {
        combotree.combotree('setValue', defVal);
    }
}

function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            district: {required: true, maxlength: 50}, name: {required: true, maxlength: 50},
            code: {required: true, maxlength: 20}
        },
        messages: {
            district: {
                required: "请输入行政区划",
                maxlength: "行政区划字符长度不能超过50"
            }, name: {
                required: "请输入分区名称",
                maxlength: "分区名称字符长度不能超过50"
            }, code: {
                required: "请输入分区编码",
                maxlength: "分区名称字符长度不能超过20"
            }
        }
    });
    /*监听表单提交*/
    $('#fm-edit').ajaxForm({
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
        url: contextPath + "/topSubarea/updateTopSubarea",
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
        url: contextPath + "/topSubarea/selectTopSubareaByPrimaryKey",
        type: "post",
        async: false,
        data: {id: urlParams.did},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-edit', data, 'tpl-fm');
                initPage(data);

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

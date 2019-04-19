var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li> <span>编辑策略管理</span></li > ");
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

function initPage() {
    $(".form-body input[name='district']").combotree({
        url: portalPath + "/system/selectProvinceTreeList.do?id=00",
        method: 'get',
        label: '',
        labelPosition: 'top',
        valueField: "id",
        textField: "text",
        lines: true,
        panelWidth: 400,
        queryParams: {},
        onBeforeSelect: function (node) {
            // 控制职能选择子节点
            // if (!$(this).tree('isLeaf', node.target)) {
            //     $(this).combo("showPanel");
            //     return false;
            // }
        }
    });
    $(".form-body input[name='stationCode']").combogrid({
        url: contextPath + "/topStation/findTopStationList",
        method:'get',
        loadMsg:"正在获取...",
        panelWidth: 400,
        mode:'remote',
        idField:'id',
        textField:'name',
        columns:[[
            {field:'name',title:'站点名称',width:200}
        ]]
    });
}

function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            code: {required: true, maxlength: 50}, name: {required: true, maxlength: 50}
        },
        messages: {
            code: {
                required: "请输入策略编号",
                maxlength: "策略编号字符长度不能超过50"
            }, name: {
                required: "请输入策略名称",
                maxlength: "策略名称字符长度不能超过50"
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
    startLoad();
    $.ajax({
        url: contextPath + "/ltStrategy/updateLtStrategy",
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
        url: contextPath + "/ltStrategy/selectLtStrategyByPrimaryKey",
        type: "post",
        async: false,
        data: {id: urlParams.did},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
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

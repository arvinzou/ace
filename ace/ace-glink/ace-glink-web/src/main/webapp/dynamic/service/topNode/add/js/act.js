var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li> <span > 创建节点管理 </span></li>");
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
            code: {required: true, maxlength: 50},
            name: {required: true, maxlength: 50},
            ipv4: {required: true, maxlength: 20},
            port: {required: true, maxlength: 10},
            resolutionWidth: {required: true, maxlength: 20},
            resolutionHeight: {required: true, maxlength: 20}
        },
        messages: {
            code: {
                required: "请输入节点编号",
                maxlength: "节点编号字符长度不能超过50"
            }, name: {
                required: "请输入节点名称",
                maxlength: "节点名称字符长度不能超过50"
            }, ipv4: {
                required: "请输入IPV4地址",
                maxlength: "IPV4地址字符长度不能超过20"
            }, port: {
                required: "请输入端口号",
                maxlength: "端口号字符长度不能超过10"
            }, resolutionWidth: {
                required: "请输入分辨率宽",
                maxlength: "分辨率宽字符长度不能超过10"
            }, resolutionHeight: {
                required: "请输入分辨率高",
                maxlength: "分辨率高字符长度不能超过10"
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
//coverUrl: $('#coverUrl').attr("src"),
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
        url: contextPath + "/topNode/insertTopNode",
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
    $(".form-body input[name='buildingCode']").combogrid({
        url: contextPath + "/topBuilding/findTopBuildingList",
        method:'get',
        loadMsg:"正在获取...",
        panelWidth: 400,
        mode:'remote',
        idField:'code',
        textField:'name',
        columns:[[
            {field:'name',title:'建筑名称',width:200}
        ]]
    });
    $(".form-body input[name='stationCode']").combogrid({
        url: contextPath + "/topStation/findTopStationList",
        method:'get',
        loadMsg:"正在获取...",
        panelWidth: 400,
        mode:'remote',
        idField:'code',
        textField:'name',
        columns:[[
            {field:'name',title:'站点名称',width:200}
        ]]
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
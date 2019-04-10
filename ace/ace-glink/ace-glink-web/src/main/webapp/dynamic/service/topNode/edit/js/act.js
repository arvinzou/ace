var loading = {};
var editor;
var qq='',sn='';
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li> <span>编辑节点管理</span></li>");
        initForm();
        initEvents();
    });
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
    $(".form-body input[name='buildingCode']").combogrid({
        url: contextPath + "/topBuilding/findTopBuildingList?name="+qq,
        method: 'get',
        loadMsg: "正在获取...",
        panelWidth: 400,
        mode: 'remote',
        // fitColumns: true,
        idField: 'code',
        textField: 'name',
        pageSize: 100,
        columns: [[
            {field: 'name', title: '建筑名称', width: 200}
        ]]
    });
    $(".form-body input[name='stationCode']").combogrid({
        url: contextPath + "/topStation/findTopStationList?name="+sn,
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

function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            code: {required: true, maxlength: 50},
            name: {required: true, maxlength: 50},
            address: {required: true, maxlength: 200},
            ipv4: {required: true, maxlength: 20},
            port: {required: true, maxlength: 10}
        },
        messages: {
            code: {
                required: "请输入节点编号",
                maxlength: "节点编号字符长度不能超过50"
            }, name: {
                required: "请输入节点名称",
                maxlength: "节点名称字符长度不能超过50"
            }, address: {
                required: "请输入详细地址",
                maxlength: "详细地址字符长度不能超过200"
            }, ipv4: {
                required: "请输入IPV4地址",
                maxlength: "IPV4地址字符长度不能超过20"
            }, port: {
                required: "请输入端口号",
                maxlength: "端口号字符长度不能超过10"
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
        url: contextPath + "/topNode/updateTopNode",
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
        url: contextPath + "/topNode/selectTopNodeByPrimaryKey",
        type: "post",
        async: false,
        data: {id: urlParams.did},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                qq=result.value.topBuilding.name;
                sn=result.value.topStation.name;
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
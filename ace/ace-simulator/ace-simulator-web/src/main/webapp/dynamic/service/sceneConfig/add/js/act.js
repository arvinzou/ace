var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li> < span > 创建场景设置 < /span></li >");
        initPage();
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
    initForm();
}

function initEvents() {
    /*表单验证*/
    $("#fm-add").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            district: {required: true, maxlength: 50},
            linkType: {required: true, maxlength: 10},
            linkCode: {required: true, maxlength: 50},
            code: {required: true, maxlength: 100},
            name: {required: true, maxlength: 50}
        },
        messages: {
            district: {
                required: "请输入行政区划",
                maxlength: "行政区划字符长度不能超过50"
            }, linkType: {
                required: "请输入分类",
                maxlength: "分类字符长度不能超过10"
            }, linkCode: {
                required: "请输入节点/站点编码",
                maxlength: "节点/站点编码字符长度不能超过50"
            }, code: {
                required: "请输入策略编码",
                maxlength: "策略编码字符长度不能超过100"
            }, name: {
                required: "请输入策略名称",
                maxlength: "策略名称字符长度不能超过50"
            }
        }
    });
    /*监听表单提交*/
    $('#fm-add').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                if (params[obj.name]) {
                    params[obj.name] = params[obj.name] + ',' + obj.value;
                } else {
                    params[obj.name] = obj.value;
                }
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
        url: contextPath + "/sceneConfig/insertSceneConfig",
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

var lnktype;
var combo;

function initForm() {
    var data = staticDictObject;
    console.log(data);
    render('#fm-add-panel', data['184'], 'tpl-fm-add');

    lnktype="站点";

    $(".form-body input[name='district']").combotree({
        url: portalPath + "/system/selectProvinceTreeList.do?id=00",
        method: 'get',
        label: '',
        panelWidth: 400,
        labelPosition: 'top',
        valueField: "id",
        textField: "text",
        lines: true,
    });

    combo= $(".form-body input[name='linkCode']").combogrid({
        url: contextPath + "/topNode/findNodeAndStationList?remark="+lnktype,
        method:'get',
        loadMsg:"正在获取...",
        panelWidth: 400,
        mode:'remote',
        idField:'code',
        textField:'name',
        columns:[[
            {field:'code',title:'编码',width:100},
            {field:'name',title:'名称',width:200},
            {field:'remark',title:'类型',width:50},
        ]]
    });

    $("input[name=startDate]").datetimepicker({
        minView: "hour",
        format: 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: true, //显示‘今日’按钮
        clearBtn: true, //清除按钮
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0
    }).on('hide', function (event) {
        event.preventDefault();
        event.stopPropagation();
        var startTime = event.date;
        console.log(startTime);
        $("input[name=endDate]").datetimepicker('setStartDate', startTime);
    }).on('show', function (event) {
        event.preventDefault();
        event.stopPropagation();
        $("input[name=startDate]").datetimepicker('setStartDate', new Date());
    });

    $('input[name=startDate]').focus(function () {
        $(this).blur(); //不可输入状态
    })


    $("input[name=endDate]").datetimepicker({
        minView: "hour",
        format: 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: true, //显示‘今日’按钮
        clearBtn: true, //清除按钮
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0
    }).on('hide', function (event) {
        event.preventDefault();
        event.stopPropagation();
        var endTime = event.date;
        $("input[name=startDate]").datetimepicker('setEndDate', endTime);
    });
    $('input[name=endDate]').focus(function () {
        $(this).blur(); //不可输入状态
    });
}

function changeUrl(type) {
    switch (type) {
        case 1:
            lnktype="站点";
            break;
        case 2:
            lnktype="节点";
            break;
        case 3:
            lnktype="建筑";
            break;
    }
    $(".form-body input[name='linkCode']").val('');
    combo.combogrid('grid').datagrid('options').url=contextPath + "/topNode/findNodeAndStationList?remark="+lnktype;
    combo.combogrid('grid').datagrid('reload');
}

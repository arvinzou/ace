var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>事故快报</span></li>");
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
        errorPlacement: function (error, element) {
            $(element).closest("form").find(".error-" + element.attr("name")).append(error);
        },
        rules: {
            address: {
                required: true,
                maxlength: 50
            },
            weather: {
                required: true
            },
            accidentTime: {
                required: true
            },
            vehicleType: {
                required: true
            },
            deadthToll: {
                digits: true,
                range: [0, 999]
            },
            injuries: {
                digits: true,
                range: [0, 999]
            }
        },
        messages: {
            address: {
                required: "请选择事故发生地点",
                maxlength: "事故发生地点字符长度不能超过50"
            },
            weather: "请选择天气",
            accidentTime: "请选择事故事件",
            vehicleType: "请选择车型",
            deadthToll: "请输入0~999区间的整数",
            injuries: "请输入0~999区间的整数"
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
        url: contextPath + "/traAcc/insertTraAcc",
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
/**
 * 验证事故时间有效性
 * @param val
 */
function validateAccTime(val) {
    var nowTime = Date.parse(new Date());
    var inputTime = Date.parse(val)
    if (inputTime > nowTime) {
        alert("事故时间不得晚于当前系统时间!");
    }
}

function initForm() {
    var data = staticDictObject;
    render('#fm-add-panel', data, 'tpl-fm-add');

    $("input[name=accidentTime]").datetimepicker({
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
    });
    $('input[name=accidentTime]').focus(function () {
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

function latitude(latitude) {
    $("input[name=latitude]").val(latitude);
}

function longitude(longitude) {
    $("input[name=longitude]").val(longitude);
}

function addr(addr) {
    $("input[name=address]").val(addr);
}

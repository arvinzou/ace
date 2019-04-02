var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>事故续报</span></li>");
        initJuicerMethod();
        initForm();
        initEvents();
    });
}

function initJuicerMethod() {
    juicer.register('isChecked', isChecked);
}

/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
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

function initPage() {
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

function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
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
            vehicleType: {
                required: true
            },
        },
        messages: {
            address: {
                required: "请选择事故发生地点",
                maxlength: "事故发生地点字符长度不能超过50"
            },
            weather: {
                required: "请选择天气"
            },
            vehicleType: {
                required: "请选择车型"
            },
        }
    });
    /*监听表单提交*/
    $('#fm-edit').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            //事故车型
            var mtypeList = [];
            $("input[name='vehicleType']:checked").each(function (index, item) {
                var item = {vehicleType: $(this).val()};
                mtypeList.push(item);
            });
            //事故原因
            var causeList = [];
            $("input[name='cause']:checked").each(function (index, item) {
                var item = {cause: $(this).val()};
                causeList.push(item);
            });

            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date(),
                mtypeList: mtypeList,	//事故车型
                causeList: causeList	//事故原因
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
        url: contextPath + "/traAcc/updateTraAcc",
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
        url: contextPath + "/traAcc/selectTraAccByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: urlParams.did
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                data['dict'] = staticDictObject;
                data['vehicleType'] = result.value.mtypeList;
                data['cause'] = result.value.causeList;
                render('#fm-edit', data, 'tpl-fm');
                initPage();
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

function isChecked(val, arr) {
    var rst = '';
    $.each(arr, function (index, obj) {
        if (val == obj.vehicleType) {
            rst = 'checked';
            return false;//return false：相当于break； return true:相当于continue;
        } else if (val == obj.cause) {
            rst = 'checked';
            return false;
        }
    });

    return rst;
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

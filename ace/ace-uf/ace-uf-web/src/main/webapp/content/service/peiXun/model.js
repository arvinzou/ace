var _colNames = ['主键', '培训主题', '培训地点', '维度', '经度','培训单位', '培训对象', '培训进度', '培训日期', '培训内容', '操作'];
var _colModel = function () {
    return [

        {
            name : 'id',//表格列的名称
            editable : false,//定义字段是否可编辑
            hidden : true,//在初始化表格时是否要隐藏此列
            width : 100,//默认列的宽度，只能是象素值
            editoptions : {
                size : "20",
                maxlength : "50"
            },
            formoptions : {//对于form进行编辑时的属性设置
                elmprefix : "",
                elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
            },
            editrules : {
                required : true
            }
        },

        {
            name : 'name',
            editable : true,
            width : 200,
            editoptions : {
                style : 'width:200px;',
                maxlength : "20"
            },
            formoptions : {
                elmprefix : "",
                elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
            },
            editrules : {
                required : true
            }
        },


        {
            name : 'address',
            editable : true,
            hidden : true,
            width : 100,
            editoptions : {
                style : 'width:200px;',
                maxlength : "50",
                colspan : false
            },
            formoptions : {
                elmprefix : "",
                elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
            },
            editrules : {
                required : true
            }
        },

        {
            name : 'latitude',
            hidden : true,
            editable : true,
            width : 100,
            editoptions : {
                style : 'width:200px;',
                size : "20",
                maxlength : "50"
            },
        },
        {
            name : 'longitude',
            hidden : true,
            editable : true,
            width : 100,
            editoptions : {
                style : 'width:200px;',
                size : "20",
                maxlength : "50"
            },
        },

        {
            name : 'trainees',//表格列的名称
            editable : true,//定义字段是否可编辑
            width : 100,//默认列的宽度，只能是象素值
            editoptions : {
                size : "20",
                maxlength : "50"
            },
            formoptions : {//对于form进行编辑时的属性设置
                style : 'width:200px;',
                elmprefix : "",
                elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
            },
            editrules : {
                required : true
            }
        },


        {
            name : 'dept',//表格列的名称
            editable : true,//定义字段是否可编辑
            width : 100,//默认列的宽度，只能是象素值
            editoptions : {
                style : 'width:200px;',
                size : "20",
                maxlength : "50"
            },
            formoptions : {//对于form进行编辑时的属性设置
                elmprefix : "",
                elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>",
            },
            editrules : {
                required : true,
            }
        },

        {
            name: 'category',
            width: 100,
            edittype: "select",
            renderer: function (value) {
                return rsd(value, "108");
            },
            editoptions: {
                value: odparse("108")
            },
        },
        {
            name: 'time',
            editable: true,
            width: 100,
            edittype: "datetimebox",
            editoptions: {
                style : 'width:200px;',
                maxlength: "20"
            },
            dataoptions: {
                showSeconds:false,
                formatter: function (date) {
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    var h= date.getHours();
                    var mm=date.getMinutes();
                    var s=date.getSeconds();
                    return y + '-'
                        + (m < 10 ? ('0' + m) : m) + '-'
                        + (d < 10 ? ('0' + d) : d)+' '
                        +(h < 10 ? ('0' + h) : h)+':'
                        +(mm < 10 ? ('0' + mm) : mm)+':00';
                },
               parser: function (s) {
                    console.log('s的值是：'+s);
                    if (isNaN(s)){
                        var d = new Date(Date.parse(s.replace(/-/g, "/")));
                        console.log('d的值是：'+d);
                        return d;
                    } else {
                        return new Date();
                    }
                }
            },
            renderer: function (value) {
                return value == null ? "" : value.substring(0, 16);
            },
            formoptions: {
                elmprefix: "",
                elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
            },
            editrules: {
                required: true
            }
        },

        {
            name : 'content',
            editable : true,
            hidden : true,
            width : 100,
            edittype : "textarea",
            editoptions : {
                style : 'width:600px;height:200px',
                colspan : true,
                maxlength : "200"
            }
        },

        {
            name: 'opt',
            width: 100,
            hidden: false,
            editable: false,
            sortable: false,
            renderer: function (value, cur) {
                return renderBtn(cur);
            }
        }
    ];
}

function aceSwitch(cellvalue, options, cell) {
    console.log('aceSwitch');
    setTimeout(function () {
        $(cell).find('input[type=checkbox]').addClass(
            'ace ace-switch ace-switch-5').after(
            '<span class="lbl"></span>');
    }, 0);
}

// enable datepicker
function pickDate(cellvalue, options, cell) {
    setTimeout(function () {
        $(cell).find('input[type=text]').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        });
    }, 0);
}

function renderBtn(cur) {
    var id = $.jgrid.getAccessor(cur, 'id');
    var title = $.jgrid.getAccessor(cur, 'name');
    var html = [];
    html.push('<a target="_blank" href="');
    html.push('javascript:preview(\'' + id + '\',\'' + title + '\')');
    html.push('"');
    html.push('><span class="badge badge-info">查看</span></a>');
    return html.join(' ');
}
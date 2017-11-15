var _colNames = ['主键', '名称', '作者', '作者单位', '媒体', '宣传网址', '发布日期', '封面', '操作'];
var _colModel = function () {
    return [
        {
            name: 'id',
            editable: false,
            hidden: true,
            width: 100,
            editoptions: {
                size: "20",
                maxlength: "50"
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
            name: 'name',
            editable: true,
            width: 300,
            editoptions: {
                style: 'width:200px;',
                maxlength: "50",
                colspan: false
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
            name: 'author',
            editable: true,
            width: 200,
            editoptions: {
                style: 'width:200px;',
                maxlength: "50",
                colspan: false
            },
        },

        {
            name: 'dept',
            editable: true,
            width: 200,
            editoptions: {
                style: 'width:200px;',
                maxlength: "50",
                colspan: false
            },
        },

        {
            name: 'media',
            editable: true,
            width: 200,
            editoptions: {
                style: 'width:200px;',
                maxlength: "50",
                colspan: false
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
            name: 'url',
            editable: true,
            width: 300,
            editoptions: {
                style: 'width:200px;',
                maxlength: "50",
                colspan: false
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
            name: 'published',
            editable: true,
            width: 200,
            edittype: "datebox",
            dataoptions: {
                formatter: function (date) {
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
                        + (d < 10 ? ('0' + d) : d);
                },
                parser: function (s) {
                    if (!s)
                        return new Date();
                    var ss = (s.split('-'));
                    var y = parseInt(ss[0], 10);
                    var m = parseInt(ss[1], 10);
                    var d = parseInt(ss[2], 10);
                    if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
                        return new Date(y, m - 1, d);
                    } else {
                        return new Date();
                    }
                }
            },
            renderer: function (value) {
                return value == null ? "" : value.substring(0, 10);
            },
            editoptions: {
                size: "20",
                maxlength: "50"
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
            name: 'photo',
            editable: true,
            hidden: true,
            width: 100,
            editoptions: {
                style: 'width:400px;',
                maxlength: "200",
                colspan: false
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
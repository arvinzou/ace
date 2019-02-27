var _colNames = ['主键', '类型', '班级', '标题', '文件地址', '内容', '发布时间 ', '发布人', '发布/已阅', '操作'];
var _colModel = function () {
    return [{
        name: 'id',
        editable: false,
        width: 100,
        hidden: true,
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
    }, {
        name: 'category',
        editable: true,
        hidden: true,
        width: 50,
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
    }, {
        name: 'classesId',
        editable: true,
        hidden: true,
        edittype: "combogrid",
        width: 50,
        editoptions: {
            style: "width:847px",
            size: "20",
            maxlength: "50"
        },
        dataoptions: {
            panelWidth: 300,
            idField: 'id',
            textField: 'name',
            url: contextPath + '/classes/findClassesList',
            mode: 'remote',
            fitColumns: true,
            method: 'get',
            pageSize: 100,
            columns: [
                [{
                    field: 'name',
                    title: "--请搜索班级--",
                    width: 50
                }]
            ]
        },
        formoptions: {
            elmprefix: "",
            elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
        },
        editrules: {
            required: true
        }
    }, {
        name: 'title',
        editable: true,
        width: 250,
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
    }, {
        name: 'fileUrl',
        editable: true,
        width: 100,
        hidden: true,
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
    }, {
        name: 'content',
        editable: true,
        hidden: true,
        width: 100,
        edittype: "textarea",
        editoptions: {
            colspan: true,
            style: 'width:750px;line-height: 25px;height: 100px;'
        },
        formoptions: {
            elmprefix: "",
            elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
        },
        editrules: {
            required: true
        }
    }, {
        name: 'pushDate',
        editable: false,
        width: 100

    }, {
        name: 'publisher',
        editable: true,
        width: 60
    }, {
        name: 'pushed',
        editable: false,
        width: 50,
        renderer: function (value, cur) {
            return value + "/<span style='color:blue'>" + $.jgrid.getAccessor(cur, 'viewed') + "</span>";
        }
    }, {
        name: 'opt',
        width: 80,
        hidden: false,
        editable: false,
        sortable: false,
        renderer: function (value, cur) {
            return renderBtn(cur);
        }
    }];
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
    var rowid = $.jgrid.getAccessor(cur, 'id');
    var title = $.jgrid.getAccessor(cur, 'title');

    var opt = [];
    opt.push('<a href="javascript:edit(\'' + rowid + '\')">编辑</a> ');
    opt.push('<a href="javascript:push(\'' + rowid + '\')">发布</a> ');
    opt.push('<a href="javascript:del(\'' + rowid + '\')">删除</a>  ');
    opt.push('<a href="#" data-toggle="modal" data-id="' + rowid + '" data-title="' + title + '" ' + 'data-target="#modal-preview">查看</a>');

    return opt.join(' ');
}

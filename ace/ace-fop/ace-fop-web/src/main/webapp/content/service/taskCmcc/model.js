var _colNames = ['编号', '发送状态', '任务名称', '短信内容', '创建时间', '发送人', '操作'];
var _colModel = function () {
    return [{
        name: 'taskId',
        index: 'taskId',
        width: 100,
        sortable: false,
        editable: true,
        editoptions: {
            readonly: true
        }
    }, {
        name: 'status',
        index: 'status',
        width: 100,
        sortable: false,
        editable: false,
        renderer: function (value) {
            var rst = "";
            switch (value) {
                case '1':
                    rst = "YES";
                    break;
                case '0':
                    rst = "NO";
                    break;
                default:
                    rst = "N/A";
            }
            return rst;
        }
    }, {
        name: 'taskName',
        index: 'taskName',
        width: 150,
        editable: true,
        editoptions: {
            size: "20",
            maxlength: "30"
        }
    }, {
        name: 'msg',
        index: 'msg',
        width: 350,
        editable: true,
        editoptions: {
            size: "20",
            maxlength: "30"
        }
    }, {
        name: 'createTime',
        width: 150,
        sortable: true,
        editable: false
    }, {
        name: 'name',
        index: 'name',
        width: 80,
        editable: true,
        editoptions: {
            size: "20",
            maxlength: "30"
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
    var rowid = $.jgrid.getAccessor(cur, cfg.dataId);
    var title = $.jgrid.getAccessor(cur, 'title');
    var opt = [];

    opt.push('<a class="operation" href="#" data-toggle="modal" data-target="#modal-detail" data-id="' + rowid + '">详情</a> ');
    if (authorConfig.hasOwnProperty(cfg.grid_delete_data_url)) {
        opt.push('<a href="javascript:del(\'' + rowid + '\')">删除</a>  ');
    }
    return opt.join('');
}
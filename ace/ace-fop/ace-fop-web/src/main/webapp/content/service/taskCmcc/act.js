jQuery(function ($) {
    $("#btn-add").on('click', function (e) {
        e.preventDefault();
        selectMobile();
    });
    $('#combogrid-tmp').combogrid({
        onSelect: function (index, row) {
            selectMobile();
        }
    });
    $("#btn-view-save").on('click', function (e) {
        e.preventDefault();
        console.log("btn-view-save click");
        save();
    });
    $("#btn-view-add").on('click', function (e) {
        e.preventDefault();
        $('#modal-tree').modal('show');
    });
    $("#btn-view-remove").on('click', function (e) {
        e.preventDefault();
        $('#task-content').html('');
    });
    $("#btn-view-remove-last").on('click', function (e) {
        $('#task-content').find('div:last').remove();
    });
    $("#btn-remove").on('click', function (e) {
        e.preventDefault();
        $('#task-content-view').html('');
    });
    $("#btn-remove-last").on('click', function (e) {
        $('#task-content-view').find('div:last').remove();
    });
    $("#btn-view-submit").on('click', function (e) {
        e.preventDefault();
        send();
    });
});

jQuery(function ($) {
    $('#tree-dept').tree({
        checkbox: true,
        url: '/fop/fopCallRecord/selectMemberCheckTreeList',
        onBeforeExpand: function (node, param) {

        },
        onClick: function (node) {

        }
    });

    $('#combogrid-tmp').combogrid({
        panelWidth: 530,
        idField: 'id',
        textField: 'fullName',
        url: '/fop/fopCallRecord/selectSendList',
        mode: 'remote',
        fitColumns: false,
        method: 'get', columns: [[
            {field: 'mobile', title: '手机号', width: 100, align: 'right'},
            {field: 'fullName', title: '企业/团体/个人名称', width: 390, align: 'right'}
        ]],
        keyHandler: {
            up: function () {
            },

            down: function () {
            },

            enter: function () {
            },
            query: function (q) {
                $('#combogrid-tmp').combogrid("grid").datagrid("reload", {'q': q});
                $('#combogrid-tmp').combogrid("setValue", q);
            }
        }
    });
});


function save() {
    var html = new Array();
    var nodes = $('#tree-dept').tree('getChecked');
    $.each($(nodes), function (i, o) {
        if (o.href != '') {
            html.push('<div class="layout-user" >');
            html.push('<user tel="' + o.href + '" class="badge">');
            html.push(o.text);
            html.push('</user>');
            html.push('</div>');

        }
    });
    nodes = $('#tree-free').tree('getChecked');
    $.each($(nodes), function (i, o) {
        if (o.href != '') {
            html.push('<div class="layout-user" >');
            html.push('<user tel="' + o.href + '" class="badge">');
            html.push(o.text);
            html.push('</user>');
            html.push('</div>');
        }
    });
    $('#task-content').html(html.join('') + $('#task-content-view').html());
    $('#task-content-view').html('');
    $('#modal-tree').modal('hide');
}

function selectMobile() {
    var html = new Array();
    var g = $('#combogrid-tmp').combogrid('grid'); // get datagrid object
    var r = g.datagrid('getSelected'); // get the selected row
    var isExit = false;
    if (r && r.mobile) {
        $.each($('user'), function (i, obj) {
            if ($(obj).attr("tel") == r.mobile) {
                alert("重复的手机号。");
                isExit = true;
                return;
            }
        });
        html.push('<div class="layout-user" >');
        html.push('<user tel="' + r.mobile + '" class="badge">');
        html.push(r.fullName);
        html.push('</user>');
        html.push('</div>');
        if (!isExit) {
            $('#task-content-view').html($('#task-content-view').html() + html.join(''));
        }

    } else {
        alert("请选择人员且手机号不能为空。");
    }
}

function send() {
    var tel = new Array();
    $.each($('user'), function (i, obj) {
        tel.push($(obj).attr("tel") + "," + $(obj).html());
    });
    var telext = $('#telext').val();
    if (telext != '') {
        var reg = /^1\d{10}(;1\d{10})*$/;
        var r = telext.match(reg);
        if (r == null) {
            alert('对不起，多个手机号用;隔开,请注意英文分号!');
            return;
        }

        var o = telext.split(';');
        for (var i = 0; i < o.length; i++) {
            tel.push(o[i] + ",临时");
        }
    }
    var taskCmcc = {};
    taskCmcc['msg'] = $('#msg').val() + "【常德市工商联】";
    taskCmcc['taskName'] = $('#taskName').val();
    taskCmcc['tel'] = tel.join(';');
    var json = JSON.stringify(taskCmcc);
    $.ajax({
        type: "post",
        url: contextPath + "/taskCmcc/insertTaskCmcc.do",
        data: {
            jsons: json
        },
        beforeSend: function (XMLHttpRequest) {
            startLoad();
        },
        success: function (rst, textStatus) {
            if (rst) {
                alert(rst.errorMessage);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            stopLoad();
            window.location.href = contextPath + '/dynamic/service/taskCmcc/index.jsp?id=' + urlid;
        },
        error: function () {
            stopLoad();
        }
    });
}

function msgOnChange(field) {
    var msg = $('#msg').val();
    $('#msg-des').html('普通短信长度为70个字,还可输入<span class="badge badge-warning">' + (70 - msg.length) + "</span>字符");
}

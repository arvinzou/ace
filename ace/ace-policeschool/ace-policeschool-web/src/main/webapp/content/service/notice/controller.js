var params = {category: '2'};
var id;
jQuery(function ($) {
    //查询
    $('#btn-search').on('click', function () {
        $('#fm-search').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date()
                });
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });


    //初始化事件
    initEvents();
    initClassList();
});

/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });

    $(obj).html(html);
}

function initEvents() {
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    })
    var data = {};
    data.key = 'category';
    data.list = staticDictObject['185'];
    render($("#check-group-category"), data, "tpl-check-group");
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });
    $("#btn-view-add").bind('click', function (event) {
        location.href = "add/index.jsp?id=" + urlParams.id;
    });
    $("#btn-view-save").bind('click', function (event) {
        save();
    });
}

function initPreview(id) {
    startLoad();
    $.ajax({
        url: cfg.view_load_data_url,
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-preview', data, 'tpl-preview');
                //loadAttach(id);
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


function edit(rowid) {
    location.href = "edit/index.jsp?id=" + urlParams.id + "&did=" + rowid;
}

var show = false;

function del(rowid) {
    console.log(rowid);
    jQuery(cfg.grid_selector).jqGrid('delGridRow', rowid, {
        beforeShowForm: function (e) {
            var form = $(e[0]);
            if (!show) {
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
            }
            show = true;
        }
    });
}

function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam', {
        postData: params
    }).trigger("reloadGrid");
}

function initClassList() {
    startLoad();
    $.ajax({
        url: contextPath + "/mailList/getClassList",
        type: "post",
        async: false,
        data: {},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data = result.value;
                render('#select1', data, 'tpl-select-list');
                params.category = '2';
                initGrid();
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

function loadAttach(noticeId) {
    $.ajax({
        type: "get",
        url: portalPath + "/attach/findAttachList.do",
        data: {
            noticeId: noticeId
        },
        success: function (rst, textStatus) {
            if (rst && rst.state) {
                var html = [];
                $.each(rst.value, function (n, file) {
                    html.push('<div style="padding:10px" id="' + file.fileUrl + '"><a href="' + fastdfs_server + file.fileUrl +
                        '" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize / 1024) + 'kb)</div>');
                });
                $('#filelist-history').html(html.join(''));
            } else {
                alert(rst.errorMessage);
            }
        }
    });
}

function push(rowid) {
    $("#modal-push").modal("show");
    id = rowid;
    initTree();
}

function initTree() {
    $('#tt').tree({
        url: contextPath + '/mailList/getTree',
        method: 'get',
        animate: true,
        lines: false,
        checkbox: true,
        onLoadSuccess: function () {
            //$("#tt").tree("expandAll");
        }
    })
}
function save() {
    var nodes = $('#tt').tree('getChecked', ['checked', 'indeterminate']);
    var s = '';
    for (var i = 0; i < nodes.length; i++) {
        if (s != '') s += ',';
        s += nodes[i].id;
    }
    console.log(s);

    $.ajax({
        type: "post",
        url: contextPath + "/noticeStatus/insertNoticeStatus",
        data: {id: id, userIds: s},
        beforeSend: function (XMLHttpRequest) {
            startLoad();
        },
        success: function (rst, textStatus) {
            if (rst) {
                $('#modal-push').modal('hide');
                alert(rst.errorMessage);
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    postData: params
                }).trigger("reloadGrid");
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            stopLoad();
        },
        error: function () {
            stopLoad();
        }
    });
}

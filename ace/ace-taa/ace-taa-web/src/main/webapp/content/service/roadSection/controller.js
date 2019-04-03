jQuery(function ($) {
    //查询
    $('#btn-search').on('click', function () {
        $('#fm-search').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var params = {};
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
    //添加
    $('#btn-view-add').on('click', function () {
        jQuery(cfg.grid_selector).jqGrid('editGridRow', 'new', {
            closeAfterAdd: true,
            recreateForm: true,
            viewPagerButtons: false,
            beforeSubmit: function (postdata) {
                var checked = submitCheck(postdata);
                if (!checked) {
                    return [false, "", ""];
                }
                //
                // //验证
                // if (postdata.startName == postdata.endName) {
                //     alert('"路段开始"与"路段截止"值不能相同');
                //     return;
                // }
                // if (postdata.startNo == postdata.endNo) {
                //     alert('"标号开始"与"标号截止"值不能相同');
                //     return;
                // }

                return [true, "", ""];
            },
            beforeShowForm: function (e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog')
                    .find('.ui-jqdialog-titlebar')
                    .wrapInner('<div class = "widget-header" / >');
            }
        })
    });

    //初始化事件
    initEvents();
});

//字符串判空
function strIsEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}

function submitCheck(data) {
    //验证
    if (data.startName == data.endName) {
        alert('"路段开始"与"路段截止"值不能相同');
        return false;
    }
    if ((!strIsEmpty(data.startNo) && !strIsEmpty(data.endNo)) && data.startNo == data.endNo) {
        alert('"标号开始"与"标号截止"值不能相同');
        return false;
    }
    return true;
}

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

    });

}



function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + '/road/selectRoadByPrimaryKey',
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
                initTable($('#list1'));
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
    console.log(rowid);
    jQuery(cfg.grid_selector).jqGrid('editGridRow', rowid, {
        closeAfterAdd: true,
        recreateForm: true,
        viewPagerButtons: true,
        beforeSubmit: function (postdata) {
            var checked = submitCheck(postdata);
            if (!checked) {
                return [false, "", ""];
            }
            // //验证
            // if (postdata.startName == postdata.endName) {
            //     alert('"路段开始"与"路段截止"值不能相同');
            //     return;
            // }
            // if (postdata.startNo == postdata.endNo) {
            //     alert('"标号开始"与"标号截止"值不能相同');
            //     return;
            // }

            return [true, "", ""];
        },
        beforeShowForm: function (e) {
            var form = $(e[0]);
            form.closest('.ui-jqdialog')
                .find('.ui-jqdialog-titlebar')
                .wrapInner(' <div class = "widget-header" / > ');

        }
    });
}

var show = false;

function del(rowid) {
    console.log(rowid);
    jQuery(cfg.grid_selector).jqGrid('delGridRow', rowid, {
        beforeShowForm: function (e) {
            var form = $(e[0]);
            if (!show) {
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner(' <div class = "widget-header" / > ');
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


function initTable(table) {
    //var table = $('#list1');

    // begin first table
    table.dataTable({

        // Internationalisation. For more info refer to http://datatables.net/manual/i18n
        "language": {
            "aria": {
                "sortAscending": ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            },
            "emptyTable": "没有找到数据",
            "info": "显示 _START_ 到 _END_ 共 _TOTAL_ 记录",
            "infoEmpty": "没有查找到数据",
            "infoFiltered": "(filtered1 from _MAX_ total records)",
            "lengthMenu": "显示 _MENU_",
            "search": "搜索:",
            "zeroRecords": "没有找到数据",
            "paginate": {
                "previous": "Prev",
                "next": "Next",
                "last": "Last",
                "first": "First"
            }
        },

        // Or you can use remote translation file
        //"language": {
        //   url: '//cdn.datatables.net/plug-ins/3cfcc339e89/i18n/Portuguese.json'
        //},

        // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
        // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js).
        // So when dropdowns used the scrollable div should be removed.
        //"dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",

        "bStateSave": true, // save datatable state(pagination, sort, etc) in cookie.

        "lengthMenu": [
            [5, 15, 20, -1],
            [5, 15, 20, "全部"] // change per page values here
        ],
        // set the initial value
        "pageLength": 5,
        "pagingType": "bootstrap_full_number",
        "columnDefs": [
            {  // set default column settings
                'orderable': false,
                'targets': [0]
            },
            {
                // "searchable": false,
                // "targets": [0]
            },
            {
                "className": "dt-right",
                //"targets": [2]
            }
        ],
        "order": [
            [1, "asc"]
        ] // set first column as a default sort by asc
    });


    table.find('.group-checkable').change(function () {
        var set = jQuery(this).attr("data-set");
        var checked = jQuery(this).is(":checked");
        jQuery(set).each(function () {
            if (checked) {
                $(this).prop("checked", true);
                $(this).parents('tr').addClass("active");
            } else {
                $(this).prop("checked", false);
                $(this).parents('tr').removeClass("active");
            }
        });
    });

    table.on('change', 'tbody tr .checkboxes', function () {
        $(this).parents('tr').toggleClass("active");
    });


}

function delTable(id) {
    var ids = [];
    $.each($('input:checkbox'), function () {
        if (this.checked) {
            console.log($(this).val());
            ids.push($(this).val());
        }
    });
    delByIds(ids.join(","), id);
}

function delByIds(ids, id) {
    startLoad();
    $.ajax({
        url: contextPath + "/roadSection/deleteRoadSectionByRoadSectionIds",
        type: "post",
        async: false,
        data: {ids: ids},
        success: function (rst) {
            stopLoad();
            alert(rst.errorMessage);
            if (rst.status == 0) {
                initPreview(id);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function previewMap(id, num) {
    if (num < 1) {
        alert("当前路段未采集，无法查看")
    }
    window.open(contextPath + "/dynamic/service/roadSection/map.jsp?id=" + id);
}


function clearForm(obj) {
    var form = $(obj).parents("form");
    $(form)[0].reset();

}

function reset(rowid) {
    if (confirm("确认清空已采集GPS数据么？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/roadSection/reset",
            type: "post",
            async: false,
            data: {sectionId: rowid},
            success: function (rst) {
                stopLoad();
                alert(rst.errorMessage);
                if (rst.status == 0) {
                    initPreview(rowid);
                }
            },
            error: function () {
                stopLoad();
                alert("对不起出错了！");
            }
        });
    }
}

//添加分路长
function addBranchRoadMan(roadSectionId) {
    $("#modal-add").on("hide.bs.modal", function () {
        $("#fm-add").get(0).reset();//清除上一次添加的数据
    });
    $("#modal-add").modal("show");
    $('#roadSectionId').val($("input[name='roadsId']").val());
    $('#addRoadMan').attr("disabled", false);
    $('#reset').attr("disabled", false);

}
//分路长列表
function branchRoadManList(rowid) {
    $("input[name='roadsId']").val(rowid);
    $("#moda2-preview").modal("show");
    initPreviewBranch(rowid);

}
function initPreviewBranch(rowid) {
    startLoad();
    $.ajax({
        url: contextPath + '/branchRoadMan/getList',
        type: "post",
        async: false,
        data: {
            roadSectionId: rowid
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm2-preview', data, 'tp2-preview');
                initTable($('#list2'));
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

function initAddModal() {

    var areaCode = $('#areaCode').combotree('getValue');
    var roadSectionId = $('#roadSectionId').val();
    var name = $('#name').val();
    var mobile = $('#mobile').val();
    if (strIsEmpty(areaCode)) {
        alert('请选择行政区域！');
    } else if (strIsEmpty(name)) {
        alert('请填写姓名！');


    } else if (strIsEmpty(mobile)) {
        alert('请填写手机号！');

    } else {
        $.ajax({
            url: contextPath + "/branchRoadMan/insertBranchRoadMan",
            type: "post",
            async: false,
            data: $('#fm-add').serialize(),
            success: function (rst) {
                stopLoad();
                alert(rst.errorMessage);
                $("#modal-add").modal("hide");
                branchRoadManList(roadSectionId);

            },
            error: function () {
                stopLoad();
                alert("对不起出错了！");
            }
        });

    }
}
//删除分路长
function delTab(id, roadSectionId) {
    if (confirm("确定要刪除吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/branchRoadMan/deleteBranchRoadManByBranchRoadManId",
            type: "post",
            async: false,
            data: {
                id: id,
            },
            success: function (rst) {
                stopLoad();
                if (rst.status == 0) {
                    branchRoadManList(roadSectionId);

                } else {
                    alert(rst.errorMessage);
                }
            },
            error: function () {
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}


//查看分路长
function findTab(rowid) {
    $("#moda3-preview").modal("show");
    startLoad();
    $.ajax({
        url: contextPath + '/branchRoadMan/selectBranchRoadManByPrimaryKey',
        type: "post",
        async: false,
        data: {
            id: rowid
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm3-preview', data, 'tp3-preview');

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

function editTab(rowid) {
    $("#modal-update").modal("show");
    $('#updateRoadMan').attr("disabled", false);
    $('#reset1').attr("disabled", false);
    startLoad();
    $.ajax({
        url: contextPath + '/branchRoadMan/selectBranchRoadManByPrimaryKey',
        type: "post",
        async: false,
        data: {
            id: rowid
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                $('#id1').val(result.value.id);
                $('#roadSectionId1').val(result.value.roadSectionId);
                $('#name1').val(result.value.name);
                $('#mobile1').val(result.value.mobile);
                $('#areaCode1').combotree('setValue', result.value.areaCode);
                $('#orgName1').val(result.value.orgName);
                $('#postName1').val(result.value.postName);
                $('#status1').val(result.value.status);
                $('#createDate1').val(result.value.createDate);

                //  render('#fm4-preview', data, 'tp4-preview');

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


//提交修改
function initUpdateModal() {
    var id = $('#id1').val();
    var roadSectionId = $('#roadSectionId1').val();
    var name = $('#name1').val();
    var mobile = $('#mobile1').val();
    var areaCode = $('#areaCode1').combotree('getValue');
    var orgName = $('#orgName1').val();
    var postName = $('#postName1').val();
    var status = $('#status1').val();
    var createDate = $('#createDate1').val();
    if (strIsEmpty(name)) {
        alert('请填写姓名！');

    } else if (strIsEmpty(mobile)) {
        alert('请填写手机号！');

    } else {
        $.ajax({
            url: contextPath + "/branchRoadMan/updateBranchRoadMan",
            type: "post",
            async: false,
            dataType: "json",
            data: {
                id: id,
                roadSectionId: roadSectionId,
                name: name,
                mobile: mobile,
                areaCode: areaCode,
                orgName: orgName,
                postName: postName,
                status: status,
                createDate1: createDate,
            },
            success: function (rst) {
                stopLoad();
                alert(rst.errorMessage);
                $("#modal-update").modal("hide");
                branchRoadManList(roadSectionId);

            },
            error: function () {
                stopLoad();
                alert("对不起出错了！");
            }
        });
    }

}
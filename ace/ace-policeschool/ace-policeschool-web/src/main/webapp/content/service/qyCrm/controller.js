var params = {};
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
//添加
    $('#btn-view-add').on('click', function () {
        jQuery(cfg.grid_selector).jqGrid('editGridRow', 'new', {
            closeAfterAdd: true,
            recreateForm: true,
            viewPagerButtons: false,
            beforeShowForm: function (e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog')
                    .find('.ui-jqdialog-titlebar')
                    .wrapInner('<div class="widget-header" / > ');

            }
        })
    });

//初始化事件
    initEvents();
    initJuicerMethod();
});

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('rsd', rsd);
    juicer.register('parseStatus', parseStatus);
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
        var data = {};
        data.key = 'category';
        data.list = staticDictObject['170'];
        render($("#check-group-category"), data, "tpl-check-group");

    });
    //数据同步 -- 模态框
    $('#modal-sync').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var userId = relatedTarget.data('id');
        initSyncForm(userId);
    });

    //数据同步 -- 确定按钮
    $('#modal-sync .btn-primary').on('click', function () {
        $('#modal-sync form').submit();
    });
    //数据同步 -- 提交事件
    $('#modal-sync form').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var name = $("#fm-sync .name").text();
            if (strIsEmpty(name)) {
                alert("学员姓名信息有误！");
                return false;
            }
            var idCard = $("#fm-sync .idCard").text();
            if (strIsEmpty(idCard)) {
                alert("学员身份证信息有误！");
                return false;
            }
            var mobile = $("#fm-sync .mobile").text();
            if (strIsEmpty(mobile)) {
                alert("学员手机号不合法！");
                return false;
            }
            //设备数组
            var idArray = new Array();
            $('input[name="device"]:checked').each(function () {
                idArray.push($(this).val());//向数组中添加元素  
            });
            if (idArray.length == 0) {
                alert("请选择同步设备！");
                return false;
            }
            var idStr = idArray.join(',');//将数组元素连接起来以构建一个字符串  
            console.log("id_str: " + idStr);
            //组装参数，调用接口
            var p = {};
            p['idStr'] = idStr;
            p['userId'] = $("#fm-sync .userId").text().trim();
            var modal = $(this);
            syncData(p, modal);

        }
    });
}

function syncData(p, modal) {
    startLoad();
    $.ajax({
        url: contextPath + '/qyCrm/syncData',
        type: "post",
        async: false,
        data: p,
        success: function (result) {
            stopLoad();
            alert(result.errorMessage)
            if (result.status == 1) {
                return false;
            }
            $('#modal-sync').modal('hide');
            setParams('', '');
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function initSyncForm(userId) {
    startLoad();
    $.ajax({
        url: contextPath + '/qyCrm/getDevice',
        type: "post",
        async: false,
        data: {
            userId: userId
        },
        success: function (result) {
            stopLoad();
            var deviceList = result.deviceList;
            var vo = result.vo;
            var data = {};
            data['o'] = result.vo;
            if (null != deviceList && deviceList.status == 1) {
                data['deviceList'] = deviceList.data;
            } else {
                alert("获取设备列表失败!");
            }
            render('#fm-sync', data, 'tpl-sync');
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
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
                console.log(JSON.stringify(data));
                render('#fm-preview', data, 'tpl-preview');
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

function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
}

/**
 * undo-未上传
 * pass-上传成功
 * fail-上传失败
 */
function parseStatus(val) {
    var rst = "";
    switch (val) {
        case 'SYNC_OK' :
            rst = "同步成功";
            break;
        case 'SYNC_FAIL' :
            rst = "同步失败";
            break;
        default :
            rst = "未同步";
    }
    return rst;
}


function strIsEmpty(str) {
    if (typeof str == "undefined" || str == null || str == "") {
        return true;
    } else {
        return false;
    }
}

function validateMobile(mobile) {
    var reg = /^1[3|4|5|7|8][0-9]\d{8,11}$/;
    console.log("strIsEmpty:" + strIsEmpty(mobile));
    console.log("length:" + (mobile.length != 11));
    console.log("reg:" + !reg.test(mobile));
    if (strIsEmpty(mobile) || mobile.length != 11 || !reg.test(mobile)) {
        return false;
    } else {
        return true;
    }
}
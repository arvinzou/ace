var loading = {};
var params = {limit: 10};
window.onload = function () {
    initPage();
    initEvents();
}


/*分区管理初始化分页*/
function initPage() {
    //分页组件
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
}


function setParams(key, value) {
    params[key] = value;
    getPageList();
}

/*分区管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/topSubarea/findTopSubareaList";
    params['name'] = $("input[name=keyword]").val();
    startLoad();
    var code;
    $.getJSON(url, params, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            /*  if (params.initType == "init") {
                  $('#pagination1').jqPaginator('option', {
                      totalCounts: rst.total == 0 ? 1 : rst.total,
                      currentPage: 1
                  });
              }*/
            render($("#page-list"), rst.rows, "tpl-list");
            code = rst.rows.code;
        }
    });
    var aurl = contextPath + "/topStation/findTopStationList";
    params['subareaCode'] = code;
    startLoad();
    $.getJSON(aurl, params, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            /*  if (params.initType == "init") {
                  $('#pagination1').jqPaginator('option', {
                      totalCounts: rst.total == 0 ? 1 : rst.total,
                      currentPage: 1
                  });
              }*/
            render('#station-list', rst.rows, 'tp2-list');
        }
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


//查找站点
function stationList(code) {
    startLoad();
    $.ajax({
        url: contextPath + "/topStation/findTopStationList",
        type: "post",
        async: false,
        data: {
            subareaCode: code
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                if (result.rows.length == 0) {
                    $("#allCheck").hide();
                    $("#animaLnk-list ul").empty();
                }
                console.log(result.rows);
                render('#station-list', result.rows, 'tp2-list');
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



//查找节目
function animaList(obj, code) {
    //startLoad();
    var onlinelen;
    var len1;
    var a = $('#animaLnk-list ul li').is("." + code);
    if (a == true) {

        $('#animaLnk-list ul li').remove("." + code);

    } else {
        $.ajax({
            url: contextPath + "/animaLnk/findAnimaLnkList",
            type: "post",
            async: false,
            data: {
                stationCode: code
            },
            success: function (result) {
                stopLoad();
                if (result.status == 0) {
                    var data = result.rows;
                    //   data.unshift(n);
                    console.log(result.rows.length);
                    if (result.rows.length == 0) {
                        //$('#animaLnk-list').append("该站点没有节目");
                    }
                    $("#allCheck").show();
                    //    render('#animaLnk-list', data, 'tp3-list');
                    for (var i = 0; i < result.rows.length; i++) {
                        //   $('#animaLnk-list ul').append("<li><video src='"+result.rows[i].prePlayUrl+"' style='width: 300px;height: 300px' controls=\"controls\"></video></li>");
                        onlinelen = $("input[name='online']").length;
                        len1 = onlinelen + 1;
                        $('#animaLnk-list ul').append("  <li class=" + result.rows[i].stationCode + "><div class='div1'>\n" +
                            "            <input name=\"checkanima\" type=\"checkbox\" value=" + len1 + " onclick=\"checkanima();\"/>\n" +
                            "            <span class='bname'>" + result.rows[i].topBuildingName + "</span>\n" +
                            "           </div>\n" +
                            "            <video src=" + result.rows[i].prePlayUrl + " controls=\"controls\" >\n" +
                            "            </video>\n" +
                            "         <div class='box'>\n" +
                            "\n" +
                            "            <input type=\"checkbox\" id=\"s" + len1 + "\"  class=\"a\" name=\"online\" onclick=\"checkonline(this," + len1 + ");\"/>\n" +
                            "            <label class=\"slider-v2\" for=\"s" + len1 + "\"></label>\n" +
                            "\n" +
                            "            <input type=\"checkbox\" id=\"b" + len1 + "\"  class=\"a\" name=\"pause\"/>\n" +
                            "            <label class=\"slider-v3\" for=\"b" + len1 + "\"></label>\n" +
                            "\n" +
                            "          <a href=\"#\" data-toggle=\"modal\" data-id=" + result.rows[i].id + " data-title=" + result.rows[i].name + "\n" +
                            "               data-target=\"#modal-option\" class=\"edit btn   green\">替换</a><span class='closespan' id=\"close" + len1 + "\"></span></div></li>");
                    }
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
    var attr = $(obj).parent().attr('style');

    if (attr == '' || attr == undefined) {
        $(obj).parent().css("background", "#1890FF");
        $(obj).css("color", "#ffffff");

    } else {
        $(obj).parent().removeAttr("style");
        $(obj).css("color", "#000002");
    }

}

function initEvents() {
    //初始化按钮组件
    initBtnEvents();
    //模态框事件
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    })

    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });
    $('#modal-option').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        topBuildingCode = id;
        var modal = $(this);
        initForm(id);
    });
    //多选
    $("input[name='checkList']").click(function () {
//判断当前点击的复选框处于什么状态$(this).is(":checked") 返回的是布尔类型
        if ($(this).is(":checked")) {
            $("input[name='checkanima']").prop("checked", true);

        } else {
            $("input[name='checkanima']").prop("checked", false);

        }
        var a = $("input[name='checkanima']:checked").length;
        $("#checkCount").text(a);
    });
    //批量操作开关
    $("input[name='checkonline']").click(function () {
        //先判断多选是否选中
        if ($(this).is(":checked")) {
            $('input[name="checkanima"]:checked').each(function () {
                $("#s" + $(this).val()).prop("checked", true);
            });
        } else {
            $('input[name="checkanima"]:checked').each(function () {
                $("#s" + $(this).val()).prop("checked", false);

            });
        }
    });
// 批量操作暂停恢复
    $("input[name='checkpause']").click(function () {
        //先判断多选是否选中
        if ($(this).is(":checked")) {
            $('input[name="checkanima"]:checked').each(function () {
                //选中的批量操作
                $("#b" + $(this).val()).prop("checked", true);
            });
        } else {
            $('input[name="checkanima"]:checked').each(function () {
                $("#b" + $(this).val()).prop("checked", false);
            });
        }
    });


}


function initBtnEvents() {
    $('#fm-search').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            params['initType'] = 'init';
            params['start'] = 0;
            getPageList();
            return false;
        }

    });
}

function checkonline(obj, len1) {
    //关闭vedio
    if ($(obj).is(":checked")) {
        $(obj).parent().parent().find("video").removeClass("overlay");
        $(obj).parent().parent().find("video").attr("controls", "controls");

        $("#close" + len1).text("当前已开启");
    } else {
        $(obj).parent().parent().find("video").addClass("overlay");
        $(obj).parent().parent().find("video").removeAttr("controls");
        $("#close" + len1).text("当前已关闭");
    }
}

//选中的值
function checkanima() {
    var a = $("input[name='checkanima']:checked").length;
    var chk_value = [];
    if (a == 0) {
        $("#checkCount").text('0');
    } else {
        $('input[name="checkanima"]:checked').each(function () {
            chk_value.push($(this).val());
            var b = chk_value.length;
            $("#checkCount").text(b);
        });
    }


}


function initForm(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/animaRes/findAnimaResList",
        type: "post",
        async: false,
        data: {
            start: 0,
            limit: 40
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data.lnkid = id;
                data.list = result.rows;

                render('#animaList', data, 'animaList-tpl');
                console.log(data);
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

function updatePrePlayUrl(lnkid, id, prePlayUrl) {
    startLoad();
    $.ajax({
        url: contextPath + "/scene/updatePrePlayUrl",
        type: "post",
        async: false,
        data: {
            id: lnkid,
            prePlayUrl: prePlayUrl

        },
        success: function (rst) {
            stopLoad();
            $("#modal-option").modal('hide');
            alert(rst.errorMessage);
            if (rst.status == 0) {
                getPageList();
                $("#animaLnk-list ul").empty();
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}


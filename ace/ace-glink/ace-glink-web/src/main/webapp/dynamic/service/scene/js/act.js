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


function clinkList(obj, code) {

}

//查找节目
function animaList(obj, code) {
    //startLoad();
    $('div').is('.redColor')
    var a = $('#animaLnk-list ul li').is("." + code);
    if (a == true) {

        $('#animaLnk-list ul li').remove("." + code);

    } else {
        $.ajax({
            url: contextPath + "/animaLnk/findAnimaLnkList",
            type: "post",
            async: false,
            data: {
                lnkCode: code
            },
            success: function (result) {
                stopLoad();
                if (result.status == 0) {
                    var data = {};
                    console.log(result.rows.length);
                    if (result.rows.length == 0) {
                        //$('#animaLnk-list').append("该站点没有节目");
                    }
                    $("#allCheck").show();
                    //  render('#animaLnk-list', result.rows, 'tp3-list');
                    for (var i = 0; i < result.rows.length; i++) {
                        //   $('#animaLnk-list ul').append("<li><video src='"+result.rows[i].prePlayUrl+"' style='width: 300px;height: 300px' controls=\"controls\"></video></li>");

                        $('#animaLnk-list ul').append("  <li class=" + result.rows[i].lnkCode + "><div>\n" +
                            "            <input name=\"checkanima\" type=\"checkbox\" value=" + result.rows[i].id + " onclick=\"checkanima();\"/>\n" +
                            "            <span>" + result.rows[i].topBuildingName + "</span>\n" +
                            "           </div>\n" +
                            "            <video src=" + result.rows[i].prePlayUrl + " controls=\"controls\">\n" +
                            "            </video>\n" +
                            "        </li>");
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

    } else {
        $(obj).parent().removeAttr("style");
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
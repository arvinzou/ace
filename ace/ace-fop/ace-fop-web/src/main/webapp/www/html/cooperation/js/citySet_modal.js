var areaCode_modal = null;
function SelmCity(obj, e) {
    var ths = obj;
    var dal = '<div class="_mcitys"><span title="关闭" id="cColse" >×</span><div id="_mcitysheng" class="_mcitys0">请选择省份</div><div id="_mcitys0" class="_mcitys1"></div><div style="display:none" id="_mcitys1" class="_mcitys1"></div><div style="display:none" id="_mcitys2" class="_mcitys1"></div></div>';
    Iput.show({
        id: ths,
        event: e,
        content: dal,
        width: "470"
    });
    $("#cColse").click(function() {
        Iput.colse()
    });
    var tb_province = [];
    var b = province[0].children;
    for (var i = 0,
    len = b.length; i < len; i++) {
        tb_province.push('<a data-id="' + b[i]['id'] + '" data-name="' + b[i]['text'] + '" title="' + b[i]['text'] + '">' + b[i]['text'] + '</a>')
    }
    $("#_mcitys0").append(tb_province.join(""));
    $("#_mcitys0 a").click(function() {
        var g = getmCity($(this));
        $("#_mcitys1 a").remove();
        $("#_mcitys1").append(g);
        $("._mcitys1").hide();
        $("._mcitys1:eq(1)").show();
        $("#_mcitys0 a,#_mcitys1 a,#_mcitys2 a").removeClass("AreaS");
        $(this).addClass("AreaS");
        var lev = $(this).data("name");
        ths.value = $(this).data("name");
        areaCode_modal = $(this).data("id");
        if (document.getElementById("hcity") == null) {
            var mhcitys = $('<input>', {
                type: 'hidden',
                name: "hcity",
                "data-id": $(this).data("id"),
                id: "hcity",
                val: lev
            });
            $(ths).after(mhcitys)
        } else {
            $("#hcity").val(lev);
            $("#hcity").attr("data-id", $(this).data("id"))
        }
        $("#_mcitys1 a").click(function() {
            $("#_mcitys1 a,#_mcitys2 a").removeClass("AreaS");
            $(this).addClass("AreaS");
            var lev = $(this).data("name");
            areaCode_modal = $(this).data("id");
            if (document.getElementById("hproper") == null) {
                var mhcitys = $('<input>', {
                    type: 'hidden',
                    name: "hproper",
                    "data-id": $(this).data("id"),
                    id: "hproper",
                    val: lev
                });
                $(ths).after(mhcitys)
            } else {
                $("#hproper").attr("data-id", $(this).data("id"));
                $("#hproper").val(lev)
            }
            var bc = $("#hcity").val();
            ths.value = bc + "/" + $(this).data("name");
            var ar = getmArea($(this));
            $("#_mcitys2 a").remove();
            if (ar == '') Iput.colse();
            $("#_mcitys2").append(ar);
            $("._mcitys1").hide();
            $("._mcitys1:eq(2)").show();
            $("#_mcitys2 a").click(function() {
                $("#_mcitys2 a").removeClass("AreaS");
                $(this).addClass("AreaS");
                var lev = $(this).data("name");
                areaCode_modal = $(this).data("id");
                if (document.getElementById("harea") == null) {
                    var mhcitys = $('<input>', {
                        type: 'hidden',
                        name: "harea",
                        "data-id": $(this).data("id"),
                        id: "harea",
                        val: lev
                    });
                    $(ths).after(mhcitys)
                } else {
                    $("#harea").val(lev);
                    $("#harea").attr("data-id", $(this).data("id"))
                }
                var bc = $("#hcity").val();
                var bp = $("#hproper").val();
                ths.value = bc + "/" + bp + "/" + $(this).data("name");
                Iput.colse()
            })
        })
    })
}
function getmCity(obj) {
    var c = obj.data('id');
    var e = province[0].children;
    var f = [];
    var g = '';
    for (var i = 0; i < e.length; i++) {
        if (e[i]['id'] == parseInt(c)) {
            f = e[i]['children'];
            break
        }
    }
    for (var j = 0; j < f.length; j++) {
        g += '<a data-id="' + f[j]['id'] + '" data-name="' + f[j]['text'] + '" title="' + f[j]['text'] + '">' + f[j]['text'] + '</a>'
    }
    $("#_mcitysheng").html('请选择城市');
    return g
}
function getmArea(obj) {
    var c = obj.data('id');
    var e = province[0].children;
    var f = [];
    var g = '';
    for (var i = 0; i < e.length; i++) {
        for (var j = 0; j < e[i]['children'].length; j++) {
            if (e[i]['children'][j]['id'] == parseInt(c) && e[i]['children'][j]['children']) {
                f = e[i]['children'][j]['children'];
                break
            }
        }
    }
    if (f.length) {
        for (var k = 0; k < f.length; k++) {
            g += '<a data-id="' + f[k]['id'] + '" data-name="' + f[k]['text'] + '" title="' + f[k]['text'] + '">' + f[k]['text'] + '</a>'
        }
    }
    $("#_mcitysheng").html('请选择区县');
    return g
}
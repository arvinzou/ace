var pageSize=10;
var noticeType = "";
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
var areaCode = null;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);
var isCompany = 1;
var companyProperty = null;
//企业类型
var data = [{
    "id": "1",
    "text": "私营企业"
    },
    {
        "id": "2",
        "text": "港澳台商投资企业"
    },
    {
        "id": 3,
        "text": "外商投资企业"
    },
    {
        "id": 4,
        "text": "股份合作企业"
    },
    {
        "id": 5,
        "text": "其他联营企业"
    },
    {
        "id": 6,
        "text": "有限责任公司"
    },
    {
        "id": 7,
        "text": "股份有限公司"
    },
    {
        "id": 8,
        "text": "其他企业"
    }
];

app.controller(ngControllerName,function($scope){
    try{
        $scope.userProp = userProp;
    }catch(e){}

    $("#city").click(function (e) {
        SelCity(this, e);
    });
    // 初始化查询所有
    $('#demo2').comboboxfilter({
        url: '',
        scope: 'FilterQuery1',
        data: data,
        onChange: function(newValue) {
            companyProperty = newValue;
        }
    });
    $.ajax({
        url: "/fop/www/findCompanyList",
        type:"post",
        async:false,
        data:{page:currentPage, limit: pageSize, isCompany: isCompany},
        success:function(result){
            if(result.status == 0) {
                $scope.items = result.data.list;
                $scope.totalCount = result.data.total;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
                var totalSize = result.data.total;
                var totalPage;
                if(totalSize % pageSize == 0){
                    totalPage = totalSize / pageSize;
                }else{
                    totalPage = totalSize / pageSize +1;
                }
                laypage({
                    cont: $("#paganation"),   //容器名
                    pages: totalPage,           //总页数
                    curr: currentPage,         //当前页
                   // skip: true,
                    skin: '#1A56A8',
                    groups: 5,                  //连续显示分页数
                    jump: function(obj, first){ //触发分页后的回调
                        if(!first){
                            currentPage = obj.curr;
                            $scope.searchList(currentPage, pageSize);
                        }
                    }

                });
            }else {
                layer.alert(result.errorMessage, {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        },
        error:function(){
            layer.alert("系统内部服务异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });

    $scope.searchList = function(currentPage, pageSize){
        var key_word = $("#key_word").val();
        var areaCode = $("#areaCode option:checked").val();
        var memberType = $("#memberType option:checked").val();
        if(memberType == "1"){
            isCompany = "1";
        }else if(memberType =="2"){
            isCompany = "2";
        }else if(memberType =="4"){
            isCompany = "4";
        }
        $.ajax({
            url: "/fop/www/findCompanyList",
            type:"post",
            async:false,
            data:{page:currentPage, limit: pageSize, fullName: key_word , areaCode: areaCode, companyProperty: companyProperty, isCompany: isCompany},
            success:$scope.responseHandle,
            error:function(){
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }

    $scope.responseHandle = function(result){
        if(result.status == 0) {
            $scope.items = result.data.list;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        }else {
            layer.alert(result.errorMessage, {
                icon: 5,
                skin: 'myskin'
            });
        }
    }
    /**
     * 根据企业性质，地区，关键字去查询企业公示
     */
    $scope.search = function(){
        var key_word = $("#key_word").val();
        var memberType = $("#memberType option:checked").val();
        if(memberType == "1"){
            isCompany = "1";
            $(".search_filter2").show();
        }else if(memberType =="2"){
            isCompany = "2";
            $(".search_filter2").hide();
        }else if(memberType == "4"){
            isCompany = "4"
            $(".search_filter2").hide();
        }
        console.log("地区代码：",areaCode);
        $.ajax({
            url: "/fop/www/findCompanyList",
            type:"post",
            async:false,
            data:{page:currentPage, limit: pageSize, fullName: key_word , areaCode: areaCode, companyProperty: companyProperty, isCompany: isCompany},
            success:function(result){
                if(result.status == 0) {
                    $scope.items = result.data.list;
                    $scope.totalCount = result.data.total;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                    var totalSize = result.data.total;
                    var totalPage;
                    if(totalSize % pageSize == 0){
                        totalPage = totalSize / pageSize;
                    }else{
                        totalPage = totalSize / pageSize +1;
                    }
                    laypage({
                        cont: $("#paganation"),   //容器名
                        pages: totalPage,           //总页数
                        curr: currentPage,         //当前页
                       // skip: true,
                        skin: '#1A56A8',
                        groups: 5,                  //连续显示分页数
                        jump: function(obj, first){ //触发分页后的回调
                            if(!first){
                                currentPage = obj.curr;
                                $scope.searchList(currentPage, pageSize);
                            }
                        }

                    });
                }else {
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin'
                    });
                }
            },
            error:function(){
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }

    function SelCity(obj, e) {
        var ths = obj;
        var dal = '<div class="_citys"><span title="关闭" id="cColse" >×</span><div id="_citysheng" class="_citys0">请选择省份</div><div id="_citys0" class="_citys1"></div><div style="display:none" id="_citys1" class="_citys1"></div><div style="display:none" id="_citys2" class="_citys1"></div></div>';
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
        $("#_citys0").append(tb_province.join(""));
        $("#_citys0 a").click(function() {
            var g = getCity($(this));
            $("#_citys1 a").remove();
            $("#_citys1").append(g);
            $("._citys1").hide();
            $("._citys1:eq(1)").show();
            $("#_citys0 a,#_citys1 a,#_citys2 a").removeClass("AreaS");
            $(this).addClass("AreaS");
            var lev = $(this).data("name");
            ths.value = $(this).data("name");
            areaCode = $(this).data("id");
            console.log(areaCode);
            $scope.search();
            if (document.getElementById("hcity") == null) {
                var hcitys = $('<input>', {
                    type: 'hidden',
                    name: "hcity",
                    "data-id": $(this).data("id"),
                    id: "hcity",
                    val: lev
                });
                $(ths).after(hcitys)
            } else {
                $("#hcity").val(lev);
                $("#hcity").attr("data-id", $(this).data("id"))
            }
            $("#_citys1 a").click(function() {
                $("#_citys1 a,#_citys2 a").removeClass("AreaS");
                $(this).addClass("AreaS");
                var lev = $(this).data("name");
                areaCode = $(this).data("id");
                $scope.search();
                if (document.getElementById("hproper") == null) {
                    var hcitys = $('<input>', {
                        type: 'hidden',
                        name: "hproper",
                        "data-id": $(this).data("id"),
                        id: "hproper",
                        val: lev
                    });
                    $(ths).after(hcitys)
                } else {
                    $("#hproper").attr("data-id", $(this).data("id"));
                    $("#hproper").val(lev)
                }
                var bc = $("#hcity").val();
                ths.value = bc + "/" + $(this).data("name");
                var ar = getArea($(this));
                $("#_citys2 a").remove();
                if (ar == '') Iput.colse();
                $("#_citys2").append(ar);
                $("._citys1").hide();
                $("._citys1:eq(2)").show();
                $("#_citys2 a").click(function() {
                    $("#_citys2 a").removeClass("AreaS");
                    $(this).addClass("AreaS");
                    var lev = $(this).data("name");
                    areaCode = $(this).data("id");
                    $scope.search();
                    if (document.getElementById("harea") == null) {
                        var hcitys = $('<input>', {
                            type: 'hidden',
                            name: "harea",
                            "data-id": $(this).data("id"),
                            id: "harea",
                            val: lev
                        });
                        $(ths).after(hcitys)
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
    function getCity(obj) {
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
        $("#_citysheng").html('请选择城市');
        return g
    }
    function getArea(obj) {
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
        $("#_citysheng").html('请选择区县');
        return g
    }
});
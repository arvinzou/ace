var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
var areaCode = null;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);
var cotype = null;
var costep = null;

//合作方式
var data_cotype = [{
        "id": "1",
        "text": "投资合作"
    },
    {
        "id":"2",
        "text":"合作开发"
    },
    {
        "id":"3",
        "text":"出资+资源合作"
    },
    {
        "id":"4",
        "text":"其他"
    }
];
app.controller(ngControllerName,function($scope){
    try{
        $scope.userProp = userProp;
    }catch(e){}
    //初始化文本框
    var editor = new Simditor({
        textarea: $('#content'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: '/portal/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });

    $("#city").click(function (e) {
        SelCity(this, e);
    });
    $("#city_modal").click(function (e) {
        SelmCity(this, e);
    });
    $("s").click(function (e) {
        SelCity(document.getElementById("city"), e);
    });
    $('#coperationType').comboboxfilter({
        url: '',
        scope: 'FilterQuery1',
        data: data_cotype,
        onChange: function(newValue) {
            cotype = newValue;
            console.log(cotype);
        }
    });

    $.ajax({
        url: "/fop/www/findProjectList",
        type:"post",
        async:false,
        data:{limit:pageSize, page: currentPage, status: "2"},
        success:function(result){
            if(result.status == 0) {
                $scope.items = result.data.list;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
                var totalSize = result.data.total;
                $scope.totalSize = totalSize;
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
                    //skip: true,
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
        $.ajax({
            url: "/fop/www/findProjectList",
            type:"post",
            async:false,
            data:{page:currentPage, limit: pageSize, status: "2"},
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
     * 查看项目详情
     * @param index
     */
    $scope.showProjectInfo = function(index){
        var primaryId = $scope.items[index].id;
        console.log(primaryId);
        window.open('cooperation_info.html?id='+primaryId);
    }

    /**
     * 发布之前判断是否已经登录
     */
    $scope.before_release = function ($event) {
        var userProp = parent.parent.userProp;
        if (userProp == null || userProp == ''){
            layer.alert("请先登录后再发布！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }else if(userStatus != '2'){
            //非会员也不能发布
                layer.alert("对不起，您还不是会员，请先完善信息！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
        }else if(userTypes == '3'){ //3代表银行，不能发表
                layer.alert("对不起，您属于银行企业类型，不能发表！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
        }else{
            $event.target.dataset.target='#myModal1';
        }
    }

    /**
     * 发布项目
     */
    $scope.relaseProject = function(){
            var flag = true;
            var projectName = $("input[name='projectName']").val();
            var cooType = $("#cooType option:checked").val();
            var projectType = $("input[name='projectType']").val();
            var content = $("textarea[name='content']").val();
            if(projectName == '' || projectName == undefined){
                flag = false;
                layer.alert("项目名称不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(cooType == '' || cooType == undefined){
                flag = false;
                layer.alert("合作方式不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(areaCode_modal == '' || areaCode_modal == null){
                flag = false;
                layer.alert("所属区域不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(content == '' || content == null){
                flag = false;
                layer.alert("内容不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(projectType == '' || projectType == null){
                flag = false;
                layer.alert("项目类型不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(flag){
                $.ajax({
                    url: "/fop/www/insertProject",
                    type:"post",
                    async:false,
                    data:{projectName:projectName, coopType: cooType, areaCode: areaCode_modal, projectType: projectType, coopDesc:content},
                    success:function(result){
                        if(result.status == 0) {
                            $scope.searchByParam();
                            layer.alert("发布成功！", {
                                icon: 1,
                                skin: 'myskin'
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

    }

    /**
     * 根据条件进行检索
     */
    $scope.searchByParam = function(){
        var key_word = $("#financeTitle").val();
        console.log("areaCode",areaCode);
        $.ajax({
            url: "/fop/www/findProjectList",
            type: "post",
            async: false,
            data: {limit: pageSize, page: currentPage, status: "2", projectName: key_word, coopType: cotype, areaCode: areaCode},
            success: function (result) {
                if (result.status == 0) {
                    $scope.items = result.data.list;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                    var totalSize = result.data.total;
                    $scope.totalSize = totalSize;
                    var totalPage;
                    if (totalSize % pageSize == 0) {
                        totalPage = totalSize / pageSize;
                    } else {
                        totalPage = totalSize / pageSize + 1;
                    }
                    laypage({
                        cont: $("#paganation"),   //容器名
                        pages: totalPage,           //总页数
                        curr: currentPage,         //当前页
                        //skip: true,
                        skin: '#1A56A8',
                        groups: 5,                  //连续显示分页数
                        jump: function (obj, first) { //触发分页后的回调
                            if (!first) {
                                currentPage = obj.curr;
                                $scope.searchList(currentPage, pageSize);
                            }
                        }

                    });
                } else {
                    alert(result.errorMessage);
                }
            },
            error: function () {
                alert("内部服务异常");
            }
        });
    }

    /**
     * 列表排序
     */
    $scope.sortList = function(name){
        var flag = name;
        var orderParam = "";
        if(flag == 'asc'){
            orderParam = 'asc';
            $("#"+flag).hide();
            $("#desc").show();
        }else{
            orderParam = 'desc';
            $("#"+flag).hide();
            $("#asc").show();
        }
        $.ajax({
            url: "/fop/www/findProjectList",
            type:"post",
            async:false,
            data:{"limit":pageSize,  page: 1, sord : orderParam, status: "2"},
            success:function(result){
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
            $scope.searchByParam();
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
                $scope.searchByParam();
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
                    $scope.searchByParam();
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
app.filter('formatDate', function() { //可以注入依赖
    return function(text) {
        if(text.length>10){
            return text.substring(0,10);
        }else{
            return text;
        }
    }
});

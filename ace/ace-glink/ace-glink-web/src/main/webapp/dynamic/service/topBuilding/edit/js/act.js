var loading = {};
var editor;
window.onload = function (){
jQuery(function ($) {
$(".breadcrumb").append("<li><span>编辑建筑物管理</span></li>");
    initForm();
    initEvents();
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

function initPage() {

}
function initEvents(){
/*表单验证*/
$("#fm-edit").validate({
    onfocusout: function(element) {
        $(element).valid();
        },
    rules: {
            code: {required: true,maxlength:50},
            name: {required: true,maxlength:50},
            address: {required: true,maxlength:200},
            subareaCode: {required: true,maxlength:50}
            },
    messages: {
        code: {
            required: "请输入建筑编号",
            maxlength:"建筑编号字符长度不能超过50"
        },
        name: {
            required: "请输入建筑名称",
            maxlength:"建筑名称字符长度不能超过50"
        },
        address: {
            required: "请输入所在地",
            maxlength:"所在地字符长度不能超过200"
        },
        subareaCode: {
            required: "请输入分区编码",
            maxlength:"分区编码字符长度不能超过50"
        }
    }
});
/*监听表单提交*/
$('#fm-edit').ajaxForm({
    beforeSubmit: function (formData, jqForm, options) {
        var params = {};
        $.each(formData, function (n, obj) {
        params[obj.name] = obj.value;
});
$.extend(params, {
    time: new Date(),
//coverUrl: $('#coverUrl').attr("src")
});
console.log(params);
save(params);
return false;
}
});
}
/*保存表单**/
function save(params) {
    $.extend(params, {});
    startLoad();
    $.ajax({
        url: contextPath + "/topBuilding/updateTopBuilding",
        type: "post",
        async: false,
        data: {
        jsons: JSON.stringify(params)},
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href ='../index.jsp?id='+urlParams.id;
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
        });
}

function initForm(){
    startLoad();
    $.ajax({
        url: contextPath + "/topBuilding/selectTopBuildingByPrimaryKey",
        type:"post",
        async:false,
        data:{ id: urlParams.did },
        success:function(result){
            stopLoad();
            if(result.status == 0) {
                var data={};
                data['o']=result.value;
                render('#fm-edit',data,'tpl-fm');
                initDict(result.value.type, result.value.stationCode);
                initPage();
                //富文本填值
                //editor.setValue(data['o'].summary);
            }else {
                alert(result.errorMessage);
            }
            },
        error:function(){
        stopLoad();
        alert("对不起出错了！");
        }
    });
}

function initDict(type, stationCode){
    var data=staticDictObject['177'];
    var dataList = [];
    for(var i=0; i < data.length; i++){
        if(data[i].CODE != ""){
            if(type && type == data[i].CODE){
                data[i].selected = "selected";
            }
            dataList.push(data[i]);
        }
    }
    render('#type',dataList,'type-tpl');
    initStationList(stationCode);
}

function initStationList(stationCode){
    startLoad();
    $.ajax({
        url: contextPath + "/topStation/findTopStationList",
        type: "post",
        async: false,
        data: {
            start: 0,
            limit: 999
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var dataList = result.rows;
                var areaList = [];
                    for(var i=0; i<dataList.length; i++){
                        var o = {};
                        if(stationCode == dataList[i].code){
                            o.selected = "selected";
                        }
                        o.code = dataList[i].code;
                        o.name = dataList[i].name;
                        areaList.push(o);
                    }
                render('#areaList',areaList, 'area-tpl');
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

function latitude(latitude) {
    $("input[name=latitude]").val(latitude);
}

function longitude(longitude) {
    $("input[name=longitude]").val(longitude);
}

function addr(addr) {
    $("input[name=address]").val(addr);
}
var option={
    animation: false,
    bmap: {
        center: [111.691347,29.040225],
        zoom: 14,
        roam: true
    },
    visualMap: {
        show: false,
        top: 'top',
        min: 0,
        max: 5,
        seriesIndex: 0,
        calculable: true,
        inRange: {
            color: ['blue', 'blue', 'green', 'yellow', 'red']
        }
    },
    series: [{
        type: 'heatmap',
        coordinateSystem: 'bmap',
        pointSize: 10,
        blurSize: 12
    }]
};

var params = {
    limit:9999
};

var myChart;

$(function () {
    initEchart();
    getData();
    initRoadType();
    initEvents();
    initForm();
    initCombotree();
    $('#leftDiv').mouseover(setClass);
    $('#Map').mouseover(moveClass);
    $('#Header').mouseover(moveClass);
    $('.active_seach').click(searchRoadName);
    $('#check-group-category').on('click','button',setParams);
});


function initCombotree() {
    $('#tt').combotree({
        url:portalPath+"/system/selectProvinceTreeList.do" ,
        valueField: 'id',
        textField: 'text',
        method: 'get',
        // onChange :function(){
        //     $val =  $("#tt").combotree('getValue');
        //     $("#_EnvUnitCode").val($val);
        // },
        onLoadSuccess:function(node,data){
            $('.combo .combo-value').val(data[0].id);
        }

    });
}

function searchRoadName() {
    params.keyword = $("input[name=keyword]").val();
    getData();
}

function setParams() {
    var that =$(this);
    params.category = that.data('id');
    getData();
    that.siblings().removeClass('active_but');
    that.addClass('active_but');
}


function setClass() {
    $('#leftDiv').addClass('leftIcon');
    $('#leftDiv').removeClass('centerIcon');
}
function moveClass() {
    $('#leftDiv').removeClass("leftIcon");
    $('#leftDiv').addClass("centerIcon");
}


function initRoadType() {
    var data = {};
    data.key = 'category';
    data.list = staticDictObject['170'];
    render($("#check-group-category"), data, "tpl-check-group");
}



function initEvents() {
    $("input[name=startDate]").datetimepicker({
        minView: "month",
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1, //显示‘今日’按钮
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        clearBtn: true, //清除按钮
        forceParse: 0
    }).on('hide', function (event) {
        event.preventDefault();
        event.stopPropagation();
        var startTime = event.date;
        $("input[name=endDate]").datetimepicker('setStartDate', startTime);
        $("input[name=endDate]").val("");
    });

    $('input[name=startDate]').focus(function() {
        $(this).blur(); //不可输入状态
    })


    $("input[name=endDate]").datetimepicker({
        minView: "month",
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1, //显示‘今日’按钮
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        clearBtn: true, //清除按钮
        forceParse: 0
    }).on('hide', function (event) {
        event.preventDefault();
        event.stopPropagation();
        var endTime = event.date;
        $("input[name=startDate]").datetimepicker('setEndDate', endTime);
    });
    $('input[name=endDate]').focus(function() {
        $(this).blur(); //不可输入状态
    });
    $('#tt').combotree({
        onSelect: function (node) {
            getLatLongByAreaCode({
                areaCode: node.id
            });
        }
    });
}

function getLatLongByAreaCode(data) {
    $.ajax({
        url: contextPath + '/traAcc/getLatLongByAreaCode',
        type: "post",
        async: false,
        data: data,
        success: function (result) {
            if (result.status == 0) {
                option.bmap.center=[result.value.longitude,result.value.latitude];
                myChart.setOption(option, true);
                $("input[name=areaCode]").val(result.value.areaName);
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            alert("对不起出错了！");
        }
    });
}



function initForm() {
    $('#fm').ajaxForm({
        beforeSubmit: function(formData, jqForm, options) {
            $.each(formData, function(n, obj) {
                params[obj.name] = obj.value;
            });
            params.startDate=params.startDate+' 00:00:00';
            params.endDate=params.endDate+' 00:00:00';
            getData();
            return false;
        }
    });
}

function initEchart() {
    myChart = echarts.init(document.getElementById("Map"));
}


function getData() {
	var url=contextPath+'/traAcc/getTraAccListBd';
	// var url='hangzhou-tracks.json';
    $.getJSON(url, params,function (rst) {
        var points = [].concat.apply([], rst.map(function (track) {
            return track.map(function (seg) {
                return seg.coord.concat([1]);
            });
        }));
        option.series[0].data=points;
        myChart.setOption(option, true);
    });
}


function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}
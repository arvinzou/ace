$(function(){
    var myChart = echarts.init(document.getElementById('chartBox'));
    option = {
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['故障数目']
        },
        toolbox: {
            show : false,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['0301','0302','0303','0304','0305','0306','0307','0308','0309','0310','0311','0312','0313','0314','0315']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'故障数目',
                type:'line',
                stack: '总量',
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data:[10, 9, 11, 15, 3, 1, 5, 6, 10, 3, 0, 12, 3, 8, 9]
            }
        ]
    };
    myChart.setOption(option);

});

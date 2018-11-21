var option2 = {
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: []
    },
    calculable: true,
    xAxis: [{
        type: 'category',
        data: [],
        axisTick: {
            alignWithLabel: true
        }
    }],
    yAxis: [{
        type: 'value',
        show: true,
        axisLabel: {
            formatter: '{value} 人'
        }
    }],
    series: [{
        name: '统战人士',
        type: 'bar',
        data: []
    }]
}
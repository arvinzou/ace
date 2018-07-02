var option2 = {
    color: ['#3398DB'],
    grid: {
        containLabel: true
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    xAxis: [{
        type: 'category',
        data: []
    }],
    yAxis: [{
        type: 'value'
    }],
    series: [{
        name: '统战人士',
        type: 'bar',
        data: []
    }]
};
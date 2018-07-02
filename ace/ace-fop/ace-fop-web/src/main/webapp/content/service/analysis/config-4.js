var option4 = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c}%"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: []
    },
    calculable: false,
    series: [{
        height: '70%',
        name: '漏斗图',
        type: 'funnel',
        data: []
    }]
};

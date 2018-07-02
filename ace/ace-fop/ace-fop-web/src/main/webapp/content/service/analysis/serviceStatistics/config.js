var option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c}篇"
    },
    legend: {
        orient: 'vertical',
        x: 'top',
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
var option1 = {
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        left: 'left',
        data: []
    },
    series : [
        {
            name: '会议',
            type: 'pie',
            data:[
                {value:335, name:'直接访问'}
            ]
        }
    ]
};

var loading = {};
var loading = {};
function loadlocal() {
    var urls = [];
    urls.push({path: contextPath, url: '/dynamic/service/statics/js/echarts.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadlocal();
}


window.onload = function (){

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title: {
            text: '订单'
        },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: ''
                }
            }
        },
        legend: {
            data:['付费订单','付费金额']
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['一月份','二月份','三月份','四月份','五月份','六月份','七月份','八月份','九月份','十月份','十一月份','十二月份']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'付费订单',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[120, 132, 101, 134, 90, 230, 210, 112, 220, 140, 90, 89]
            },
            {
                name:'付费金额',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[220, 182, 191, 234, 290, 330, 310, 229, 178, 186, 165, 210]
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

var myChart;
var domMain = document.getElementById('main');
var domMessage = document.getElementById('wrong-message');
var needRefresh = false;
var enVersion = location.hash.indexOf('-en') != -1;
var hash = location.hash.replace('-en','');
hash = hash.replace('#','') || (needMap() ? 'shine' : 'shine');
hash += enVersion ? '-en' : '';
var curTheme;
function requireCallback (ec, defaultTheme) {
    curTheme = themeSelector ? defaultTheme : {};
    echarts = ec;
    refresh();
    //window.onresize = myChart.resize;
}
var themeSelector = $('#theme-select');
if (themeSelector) {
    themeSelector.html(
        '<option selected="true" name="shine">shine</option>'
        + '<option name="infographic">infographic</option>'
        + '<option name="shine">shine</option>'
        + '<option name="dark">dark</option>'
        + '<option name="blue">blue</option>'
        + '<option name="green">green</option>'
        + '<option name="red">red</option>'
        + '<option name="gray">gray</option>'
        + '<option name="helianthus">helianthus</option>'
        + '<option name="roma">roma</option>'
        + '<option name="mint">mint</option>'
        + '<option name="macarons">macarons</option>'
        + '<option name="macarons2">macarons2</option>'
        + '<option name="sakura">sakura</option>'
        + '<option name="default">default</option>'
    );
    $(themeSelector).on('change', function(){
        selectChange($(this).val());
    });
    function selectChange(value){
        var theme = value;
        myChart.showLoading();
        myChartGauge.showLoading();
        myChartBar.showLoading();
        
        myChartGaugeSy.showLoading();
        myChartPieold.showLoading();
        $(themeSelector).val(theme);
        if (theme != 'default') {
            window.location.hash = value + (enVersion ? '-en' : '');
            require(['theme/' + theme], function(tarTheme){
                curTheme = tarTheme;
                setTimeout(refreshTheme, 500);
            })
        }
        else {
            window.location.hash = enVersion ? '-en' : '';
            curTheme = {};
            setTimeout(refreshTheme, 500);
        }
    }
    function refreshTheme(){
        myChart.hideLoading();
        myChartGauge.hideLoading();
        myChartBar.hideLoading();
        myChartGaugeSy.hideLoading();
        myChartPie.hideLoading();
        
        myChart.setTheme(curTheme);
        myChartGauge.setTheme(curTheme);
        myChartBar.setTheme(curTheme);
        myChartGaugeSy.setTheme(curTheme);
        myChartPie.setTheme(curTheme);
    }
    if ($(themeSelector).val(hash.replace('-en', '')).val() != hash.replace('-en', '')) {
        $(themeSelector).val('macarons');
        hash = 'macarons' + enVersion ? '-en' : '';
        window.location.hash = hash;
    }
}





function refresh(isBtnRefresh){
    initDataMap(true);
    initDataGague(true);  
    initDataBar(true);
    initDataGagueSy(true); 
    initDataPie(true);
}

function needMap() {
    var href = location.href;
    return href.indexOf('map') != -1
           || href.indexOf('mix3') != -1
           || href.indexOf('mix5') != -1
           || href.indexOf('dataRange') != -1;

}

var echarts;
jQuery(function($) {
	require.config({
        paths: {
            echarts: './www/js'
        }
    });
    launchExample();
});
var isExampleLaunched;
function launchExample() {
    if (isExampleLaunched) {
        return;
    }

    // 按需加载
    isExampleLaunched = 1;
    require(
        [
            'echarts',
            'theme/' + hash.replace('-en', ''),
            'echarts/chart/line',
            'echarts/chart/bar',
            'echarts/chart/scatter',
            'echarts/chart/k',
            'echarts/chart/pie',
            'echarts/chart/radar',
            'echarts/chart/force',
            'echarts/chart/chord',
            'echarts/chart/gauge',
            'echarts/chart/funnel',
            'echarts/chart/eventRiver',
            'echarts/chart/venn',
            'echarts/chart/treemap',
            'echarts/chart/tree',
            'echarts/chart/wordCloud',
            needMap() ? 'echarts/chart/map' : 'echarts'
        ],
        requireCallback
    );
}


var myChart1;
var myChart2;
var curTheme = {};
var theme = "default";
var needRefresh = false;
function requireCallback(ec, defaultTheme) {
	echarts = ec;
	refresh();
}
function refresh(isBtnRefresh) {
	initData(true);
}

function needMap() {
	return false
}
var echarts;
var isExampleLaunched;
function launchExample() {
	require.config({
		paths : {
			echarts : portalPath + '/content/common/js/echarts-2.27'
		}
	});
	if (isExampleLaunched) {
		return;
	}
	// 按需加载
	isExampleLaunched = 1;
	require(['echarts/theme/' + theme], function(tarTheme) {
		curTheme = tarTheme;
	})
	require(['echarts', 'echarts/chart/line', 'echarts/chart/bar',
			'echarts/chart/scatter', 'echarts/chart/k', 'echarts/chart/pie',
			'echarts/chart/radar', 'echarts/chart/force',
			'echarts/chart/chord', 'echarts/chart/gauge',
			'echarts/chart/funnel', 'echarts/chart/eventRiver',
			'echarts/chart/venn', 'echarts/chart/treemap',
			'echarts/chart/tree', 'echarts/chart/wordCloud',
			needMap() ? 'echarts/chart/map' : 'echarts'], requireCallback);
}

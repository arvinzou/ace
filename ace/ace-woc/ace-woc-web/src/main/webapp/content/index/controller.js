function initData() {
    chart1();//今日折线图
    chart2();//当月折线图
    chart3();//其他数据项
}
function initMyChar1() {
    if (myChart1 && myChart1.dispose) {
        myChart1.dispose();
    }
    myChart1 = echarts.init(document.getElementById('ct1'), curTheme);
    window.onresize = myChart1.resize;
    myChart1.setOption(option1, true);
    myChart1.hideLoading();
}

function initMyChar2() {
    if (myChart2 && myChart2.dispose) {
        myChart2.dispose();
    }
    myChart2 = echarts.init(document.getElementById('ct2'), curTheme);
    window.onresize = myChart2.resize;
    myChart2.setOption(option2, true);
    myChart2.hideLoading();
}

function chart2() {
    // $.ajax({
    //     type: "post",
    //     url: contextPath + '/homeChart/selectWorkData.do',
    //     success: function (rst) {
    //         var html = new Array();
    //         var k = 0;
    //         for (var i in rst.value) {
    //             var o = rst.value[i];
    //             k++;
    //
    //             html.push('<tr>');
    //             html.push('<td width="60px">');
    //             html.push('<span class="badge badge-info">' + (k) + '</span> ');
    //
    //             html.push('</td>');
    //             html.push('<td align="left">')
    //             html.push(o.name);
    //             html.push('</td>');
    //             html.push('<td>');
    //             html.push('<span class="badge badge-danger">');
    //             html.push(o.value);
    //             html.push('</span>  ');
    //             html.push('</td>');
    //             html.push('</tr>');
    //         }
    //         $('#work-list-grid').html(html.join(''));
    //
    //     }
    // });

    initMyChar2();
}

function chart1() {
    // $.ajax({
    // 	type : "post",
    // 	url : contextPath + '/homeChart/selectChartData.do',
    // 	success : function(rst) {
    // 		$.each(rst.value, function(key, o) {
    // 			o.name=o.name+"("+o.value+")";
    // 			option1.series[0].data.push(o);
    // 		});
    //
    // 		initMyChar1();
    // 	}
    // });

    // var o = "";
    // option1.series[0].data.push(o);

    initMyChar1();
}

function chart3() {
    $.ajax({
        type: "post",
        url: contextPath + '/anslysis/queryCounts',
        data: {reportId: "portal"},
        success: function (rst) {
            // $.each(rst, function (i, o) {
            //     $('#' + o.id).html(o.value);
            //     console.log(o);
            // });

            $('#traffic-count').html(rst.trafficCounts);
            $('#illegal-traffic-count').html(rst.trafficIllegalCounts);
            $('#pt-traffic-count').html(rst.ptTrafficCounts);
            $('#pt-cases-count').html(rst.ptCasesCounts);
        }
    });
}

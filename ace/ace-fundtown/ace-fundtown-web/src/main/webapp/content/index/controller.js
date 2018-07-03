function initData() {
    //chart1();
    //chart2();
    chart3();
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

function chart2() {
    $.ajax({
        type: "post",
        url: contextPath + '/homeChart/selectWorkData.do',
        success: function (rst) {
            var html = new Array();
            var k = 0;
            for (var i in rst.value) {
                var o = rst.value[i];
                k++;

                html.push('<tr>');
                html.push('<td width="60px">');
                html.push('<span class="badge badge-info">' + (k) + '</span> ');

                html.push('</td>');
                html.push('<td align="left">')
                html.push(o.name);
                html.push('</td>');
                html.push('<td>');
                html.push('<span class="badge badge-danger">');
                html.push(o.value);
                html.push('</span>  ');
                html.push('</td>');
                html.push('</tr>');
            }
            $('#work-list-grid').html(html.join(''));

        }
    });
}

function chart1() {
    $.ajax({
        type: "post",
        url: contextPath + '/homeChart/selectChartData.do',
        success: function (rst) {
            $.each(rst.value, function (key, o) {

                o.name = o.name + "(" + o.value + ")";

                option1.series[0].data.push(o);
            });

            initMyChar1();
        }
    });

}

function chart3() {
    // $.ajax({
    // 	type : "post",
    // 	url : contextPath + '/anslysis/query.do',
    // 	data:{reportId:"portal"},
    // 	success : function(rst) {
    // 		$.each(rst.value, function(i,o) {
    // 			$('#' + o.id).html(o.value);
    // 		});
    // 	}
    // });
    portalCount();
}

function portalCount() {
    var url = contextPath + '/anslysis/portalCount';
    data = {};
    $.getJSON(url, data, function (result) {
        for (var key in result) {
            $('#' + key).text(result[key]);
        }
    });
}

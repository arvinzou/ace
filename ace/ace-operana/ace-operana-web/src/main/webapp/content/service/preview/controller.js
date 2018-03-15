jQuery(function($) {
	launchExample();
	loadFiles();
	loadView();
});
function initData() {
	chart1("1");
	chart2();
	chart3();
	chart4();
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
	$.ajax({
		type : "post",
		url : contextPath + '/chart/chart2.do',
		data : {
			meetingId : meetingId,
			topicId : topicId,
			normId : normId
		},
		success : function(rst) {
			option2.xAxis[0].data = rst.dataX;
			option2.series[0].data = rst.dataY1;
			option2.series[1].data = rst.dataY2;

			initMyChar2();
		}
	});
}

function chart1(viewType) {
	$.ajax({
				type : "post",
				url : contextPath + '/chart/chart1.do',
				data : {
					meetingId : meetingId,
					topicId : topicId,
					normId : normId,
					viewType : viewType
				},
				success : function(rst) {
					var same = false;
					option1.xAxis[0].data = rst.dataX;
					option1.series[0].data = rst.dataY;
					//
					option1.title.text = rst.name;
					//option1.legend.data[0] = rst.name;
					option1.series[0].name = rst.name;
					if (same) {
						//option1.series[0].markLine.data[0][1].xAxis = rst.xAxis;
						//option1.series[0].markLine.data[0][0].yAxis = rst.yAxis;

						// option1.series[0].markLine.data[0][0].value = rst.yAxis;
						//option1.series[0].markLine.data[0][1].yAxis = rst.yAxis;
					} else {
						//option1.series[1].data = rst.index;
						var line = [];
						var m = rst.dataX;
						var n = rst.index;
						for (var i = 0; i < rst.dataX.length; i++) {
							var linex = [];

							if (i == 0) {
								linex = [{
									value : n[i],
									xAxis : -1,
									yAxis : n[i]
								}, {
									value : n[i],
									xAxis : m[i],
									yAxis : n[i]
								}];
							} else {
								linex = [{
									value : n[i - 1],
									xAxis : m[i - 1],
									yAxis : n[i - 1]
								}, {
									value : n[i],
									xAxis : m[i],
									yAxis : n[i]
								}];
							}
							line.push(linex);
						}

						//console.log(JSON.stringify(line));
						option1.series[0].markLine.data = [];
						option1.series[0].markLine.data = line;
					}

					if (rst.dataX.length <= 8) {
						//option1.series[0].barWidth = 40;
					}
					var html = [];
					html
							.push("<tr style='background:#3D7878;text-align:center;font-weight:800;'>");
					html.push("<td colspan='" + (rst.dataX.length + 1)
							+ "' style='color:#FFFFFF'>");
					html.push(rst.name);
					html.push("</td>");
					html.push("<tr>");
					html
							.push("<td style='width:50px;color:red;font-weight:800'>");
					html.push('时间');
					html.push("</td>");
					$(rst.dataX).each(function(i, o) {
						html.push("<td style='color:red;font-weight:800'>");
						html.push(o);
						html.push("</td>");
					});
					html.push("</tr>");
					html.push("<tr>");
					html.push("<td style='width:100px;font-weight:800'>");
					html.push(rst.name);
					html.push("</td>");
					$(rst.dataY).each(function(i, o) {
						html.push("<td style='font-weight:800'>");
						html.push(o);
						html.push("</td>");
					});
					html.push("</tr>");
					/*html.push("<tr>");
					html.push("<td>");
					html.push('目标值');
					html.push("</td>");
					$(rst.index).each(function(i, o) {
						html.push("<td>");
						html.push(o);
						html.push("</td>");
					});
					html.push("</tr>");*/
					//$("#grid1").html(html.join(""));
					initMyChar1();
				}
			});

}

function chart3() {
	$
			.ajax({
				type : "post",
				url : contextPath + '/chart/chart3.do',
				data : {
					meetingId : meetingId,
					topicId : topicId,
					normId : normId
				},
				success : function(rst) {
					var html = [];
					/*html
							.push("<tr style='background:#3D7878;text-align:center;font-weight:800;'>");
					html.push("<td colspan='" + (rst.item.length)
							+ "' style='color:#FFFFFF'>");
					html.push("不良现象明细");
					html.push("</td></tr>");
					 */
					html.push("<thead>");
					html
							.push("<tr style='background:#336666;text-align:center;font-weight:800;'>");
					$(rst.itemName)
							.each(
									function(i, o) {
										if (i == 0) {
											html
													.push("<td style='color:#FFFFFF;font-weight:800' class='wt600'>");
										} else {
											html
													.push("<td style='color:#FFFFFF;font-weight:800' class='wt60'>");
										}

										html.push(o);
										html.push("</td>");
									});
					html.push("</tr>");
					html.push("</thead>");
					html.push("<tbody>");
					$(rst.data)
							.each(
									function(i, e) {
										html.push("<tr>");
										$(rst.item)
												.each(
														function(i, o) {
															var background = "";
															if (o == rst.cwk) {
																background = "background:#006000;color:#FFFFFF;";
															}
															if (o == 'name') {
																background = "background:#FFFF6F";
															}
															if (i == 0) {
																html
																		.push("<td style='font-weight:800;"
																				+ background
																				+ "' class='wt600'>");
															} else {
																html
																		.push("<td style='font-weight:800;"
																				+ background
																				+ "' class='wt60'>");
															}

															html.push(e[o]);
															html.push("</td>");
														});
										html.push("</tr>");
									});
					html.push("</tbody>");
					$("#grid2").html(html.join(""));
				}
			});
}
function chart4() {
	$
			.ajax({
				type : "post",
				url : contextPath + '/chart/chart4.do',
				data : {
					meetingId : meetingId,
					topicId : topicId,
					normId : normId
				},
				success : function(rst) {
					var html = [];
					/*html
							.push("<tr style='background:#3D7878;text-align:center;font-weight:800;'>");
					html.push("<td colspan='" + (rst.item.length)
							+ "' style='color:#FFFFFF'>");
					html.push("TOP问题分析");
					html.push("</td></tr>");

					 */
					html.push("<thead>");
					html
							.push("<tr style='background:#336666;text-align:center;font-weight:800;'>");
					$(rst.itemName)
							.each(
									function(i, o) {
										html
												.push("<td style='color:#FFFFFF;font-weight:800' class='wt80'>");
										html.push(o);
										html.push("</td>");
									});
					html.push("</tr>");
					html.push("</thead>");
					html.push("<tbody>");
					$(rst.data)
							.each(
									function(i, e) {
										html.push("<tr>");
										$(rst.item)
												.each(
														function(i, o) {
															var background = "";

															if (o == rst.cwk) {
																background = "background:#006000;color:#FFFFFF;";
															}
															if (o == 'probDiscri') {
																var text = e[o];
																if (text) {
																	e[o] = text
																			.replace(
																					/\b(\S+)\b/g,
																					function(
																							$0) {
																						if (typeof $0 !== 'undefined') {
																							return $0
																									+ '';
																						}
																					})
																			.replace(
																					/\s+/g,
																					'<br/>');
																}

																background = "background:#FFFF6F";
															}
															if (o == 'actions') {
																var text = e[o];
																if (text) {
																	e[o] = text.replace(/\b(\S+)\b/g,function($0) {
																						if (typeof $0 !== 'undefined') {
																							return $0+ '';
																						}
																					}).replace(/\s+/g,'<br/>');
																}
															}
															if (o == 'status') {
																if (e[o] == 'Open') {
																	background = "background:red";
																}
																if (e[o] == 'Closed') {
																	background = "background:green";
																}
																if (e[o] == 'Ongoing') {
																	background = "background:#FFFF37";
																}
																if (e[o] == 'Track') {
																	background = "background:#FFA042";
																}

															}
															html
																	.push("<td style='font-weight:800;"
																			+ background
																			+ "' class='wt80'>");
															html.push(e[o]);
															html.push("</td>");
														});
										html.push("</tr>");
									});
					html.push("</tbody>");
					$("#grid3").html(html.join(""));
				}
			});
}
window.onresize = function() {
	autosize()
}
function autosize() {
	var h = window.innerHeight;
	var w = window.innerWidth;
	var charh = 300;
	var charw = parseInt(w / 2) - 20;
	charh = parseInt(charw * 0.5);
	$('#ct1').css("height", charh);
	$('#ct1').css("width", charw);
	$('#ct2').css("height", charh);
	$('#ct2').css("width", charw);

	if (myChart1) {
		myChart1.resize();
	}

}
function reload() {
	chart4();
}
function add() {
	var url = contextPath + '/dynamic/service/tpa/index.jsp?meetingId='
			+ meetingId + '&topicId=' + topicId + '&normId=' + normId;
	window.open(url);
}
function reload2() {
	chart3();
}
function add2() {
	var url = contextPath + '/dynamic/service/normDetail/index.jsp?meetingId='
			+ meetingId + '&topicId=' + topicId + '&normId=' + normId;
	window.open(url);
}
function setViewType(viewType) {
	chart1(viewType);
}

function viewFiles(meetingId,topicId,normId) {
	var tableId = "detail5";
	var dttable;
	if ($('#' + tableId).hasClass('dataTable')) {
		dttable = $('#' + tableId).dataTable();
		dttable.fnClearTable(); // 清空一下table
		dttable.fnDestroy(); // 还原初始化了的datatable
	}
	var table = $('#' + tableId).DataTable({
				ajax : {
					url : contextPath+ '/normFiles/selectFilesByMeetingTopicNormId.do?meetingId='+ meetingId+ '&topicId=' + topicId + '&normId=' + normId,
					dataSrc : 'data'
				},
				columns : [{
					data : 'rownum'
				}, {
					data : 'name'
				}, {
                 					data : 'createDate'
                 				}],
				bAutoWidth : false,
				"fnInitComplete" : function() {
					this.fnAdjustColumnSizing(true);
					$('[data-rel=tooltip]').tooltip();
				},
				"createdRow" : function(row, data, dataIndex) {
					$(row).children('td').eq(0).attr('style','text-align: center;font-weight:800;width:100px');
					$(row).children('td').eq(1).html('<a href="'+ fastdfs_server+data.url + '" target="_blank"><span class="badge badge-info">'+ data.name + '</span></a>');
					$(row).children('td').eq(1).attr('style','width:500px');
					$(row).children('td').eq(2).html('<a href="javascript:deleteNormFilesByNormFilesId(\''  + data.name + '\',\'' + data.id+ '\',\'' + data.meetingId+ '\',\'' + data.topicId+ '\',\'' + data.normId+ '\')"><span class="badge badge-danger">删除</span></a>');
					$(row).children('td').eq(2).attr('style','width:200px');

				},
				"aLengthMenu" : [5, 10, 15, 20],
				"oLanguage" : {
					"sLengthMenu" : "每页显示 _MENU_ 条记录",
					"sZeroRecords" : "对不起，查询不到任何相关数据",
					"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
					"sInfoEmpty" : "第 0 到 0 条记录，共 0 条",
					"sInfoFiltered" : "数据表中共为 _MAX_ 条记录)",
					"sProcessing" : "正在加载中...",
					"sSearch" : "搜索 ",
					"sUrl" : "",
					"oPaginate" : {
						"sFirst" : "",
						"sPrevious" : "",
						"sNext" : "",
						"sLast" : ""
					},
					"oAria" : {
						"sSortAscending" : ": 升序排列",
						"sSortDescending" : ": 降序排列"
					}
				}
			});

}

function deleteNormFilesByNormFilesId(name,id,meetingId,topicId,normId){
    if(confirm("确定要删除"+name+"吗？")){
        $.ajax({
                type : "post",
                url : contextPath + "/normFiles/deleteNormFilesByNormFilesId.do",
                data : {
                    id : id
                },
                beforeSend : function(XMLHttpRequest) {

                },
                success : function(rst, textStatus) {
                    launchExample();
                    viewFiles(meetingId,topicId,normId);

                },
                error : function() {
                    alert("加载错误！");
                }
            });
    }
}

function loadFiles(){
     $.ajax({
        type : "post",
        url : contextPath + "/normFiles/selectFilesByMeetingTopicNormId.do",
        data : {
            meetingId:meetingId,
            topicId:topicId,
            normId : normId
        },
        beforeSend : function(XMLHttpRequest) {

        },
        success : function(rst, textStatus) {
            console.log(rst);
            if(rst.data.length>0){
                viewFiles(meetingId,topicId,normId);
                $("#filesView").css("display","display");
                $("#chartView").css("display","none");
            }else{
                 $("#filesView").css("display","none");
                 $("#chartView").css("display","display");
            }

        },
        error : function() {
            alert("加载错误！");
        }
    });
}
function loadView() {
	$.ajax({
		type : "post",
		url : contextPath + '/norm/selectNormByPrimaryKey.do',
		data : {
			id : normId
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
            var o=rst.value;
            if(o.url){
                location.href=o.url;
            }
		},
		error : function() {
			alert("加载错误！");
		}
	});
}
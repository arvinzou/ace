var meetingId, topicId, normId, _title = 'CAC';
jQuery(function($) {
	//preview('1493463925789');
	viewMeeting();
});

function delMeetingById(name, id) {
	if (confirm("确定要关闭" + name + "吗？")) {
		$.ajax({
			type : "post",
			url : contextPath + "/meeting/deleteMeetingByMeetingId.do",
			data : {
				jsons : JSON.stringify({
					id : id
				})
			},
			beforeSend : function(XMLHttpRequest) {

			},
			success : function(rst, textStatus) {
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1
				}).trigger("reloadGrid");

			},
			error : function() {

				alert("加载错误！");
			}
		});
	}
}
function viewMeeting(name) {
	$.ajax({
		type : "post",
		url : contextPath + '/meeting/findMeetingList.do',
		data : {
			name : name,
			division:userProp.corpId,
			status:'2'
		},
		success : function(rst) {
			var html = [];


			$(rst.rows).each(function(i, o) {

			    var bg="#FFFFFF";
			    if(i==0){

			        bg="#eeeeee";
			        act1(o.id);
			    }
				html.push("<tr><td style='height:60px;background-color:"+bg+"' id='"+o.id+"'>");
                html.push("<div class='row'>");

				html.push("<div class='col-md-7'>");
				html.push("<div class='meeting1'>");
				html.push(o.meetingDate);
				html.push("</div>");
				html.push("<div class='meeting2'>");
                html.push(o.divisionName);
                html.push(' ');
                html.push(rsd(o.site, "106"));
                html.push("</div>");
                html.push("<div class='meeting2'>");
                html.push('<a class="blue" href="javascript:previewMeeting()"');
                html.push(' data-rel="tooltip" data-placement="right" title="'+o.discribtion+'">');
                html.push(o.name);
                html.push("</a>");
                html.push("</div>");
                html.push("<div class='meeting3'>");
                html.push(o.ownerName);
                html.push("</div>");
                html.push("</div>");
                html.push("<div class='col-md-5'><div class='meeting4'>");

                html.push('<a class="blue" href="javascript:act1(\''+o.id+'\')" data-rel="tooltip" data-placement="top" title="Lanuch"><i class="ace-icon fa fa-play bigger-150"></i></a>');
               html.push('<br> Lanuch');
               html.push('<br>');
               html.push('<a class="red" href="javascript:act2(\''+o.id+'\')" data-rel="tooltip" data-placement="top" title="Close"><i class="ace-icon fa fa-stop bigger-150"></i></a>');
               html.push('<br> Close  ');
                html.push("</div></div>");
                html.push("</div>");




				html.push("</td></tr>");
			});

			$("#meeting-grid").html(html.join(""));
			$('[data-rel=tooltip]').tooltip();
			$('#nav-search-input').bind('keypress', function(event) {
            				if (event.keyCode == "13") {
            					viewMeeting($('#nav-search-input').val());
            				}
            			});
            			$('#nav-search-input').val(name)
		}
	});
}

function act3(){
	viewMeeting($('#nav-search-input').val());
}

function updatePresent(data) {

	$.ajax({
		type : "post",
		url : contextPath + "/meeting/updatePresent.do",
		data : {text:JSON.stringify(data)
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
		    viewUser(data.meetingId);

			alert(rst.errorMessage);


		},
		error : function() {

			alert("加载错误！");
		}
	});
}
function presentSetting(){
    $("#fm4").submit();
}

jQuery(function($) {
	launchExample();
});
function initData() {
	chart1();
	chart2();
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
function chart1() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data : {
		    reportId:'portal'
		},
		success : function(rst) {
            option1.series[0].data=rst.value;
            $(rst.value).each(function(i,o){
             option1.legend.data.push(o.name);
            });
			initMyChar1();
		}
	});
}
function chart2() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data : {
		    reportId:'task',
		    userId:userProp.userId
		},
		success : function(rst) {

            $(rst.value).each(function(i,o){
                option2.xAxis[0].data.push(o.name);
            });
            $(rst.value).each(function(i,o){
                            option2.series[1].data.push(o.value1);
                   });
                   $(rst.value).each(function(i,o){
                                               option2.series[0].data.push(o.value2);
                                      });
			initMyChar2();
		}
	});
}

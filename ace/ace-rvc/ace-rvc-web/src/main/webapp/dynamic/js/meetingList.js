// JavaScript Document
var host = 'http://1931507me2.iok.la:28228/rvc';
var userids = 'E341A7084C56499AB0390BCAA3AA5BCD';
$(function () {
	downloadMeetingList();
	//	$('#meetingList').on('click', '.opt', checkone);
	$('#meetingList').on('dblclick', 'tr', openVideo);
	$('#selectMeeting').click(selectMeetingweb);
	$('.searchMeeting').click(searchMeetingDo);
	$('#deleteMeeting').click(deleteMeetingDo);
	$('#meetingList').on('click', '.startMeeting', startMeetingDo);
});
/*开始会议*/
function startMeetingDo(){
	var $this=$(this);
	var $tr = $this.parent().parent();
	var conferenceId = $tr.data('conferenceId');
	var url=host+'/conference/start.do';
	var data={'conferenceId':conferenceId,'userId':userids};
	$.post(url,data,function(result){
		if(result.result_code==1){
			$this.parent().empty().append($("<div class='status' style='color: limegreen;'>正在召开</div>"));
		}
	});
}

/*删除会议*/
function deleteMeetingDo() {
	var tr = $('#meetingList').children();
	for (var i = 0; i < tr.length; i++) {
		if (tr.eq(i).children().eq(0).children().eq(0).is(':checked')) {
			if (confirm("确定删除：" + tr.eq(i).children().eq(1).text())) //在页面上弹出对话框
			{
				var url = host + "/conference/delete.do";
				var data = {
					'userId': userids,
					'conferenceId': tr.eq(i).data('conferenceId')
				};
				$.post(url, data, function (result) {
					if (result.result_code == 1) {
						downloadMeetingList();
					} else {
						alert("删除失败，请重试。");
					}
				});
			}
		}
	}
}

/*查询会议*/
function searchMeetingDo() {
	var value = $('.searchContent').val();
	console.log(value);
	var condition = {
		"limit": 10,
		"searchRegion": "all",
		"start": 0,
		"title": value.trim()
	};
	var url = host + "/conference/search.do";
	var data = {
		'userId': userids,
		condition: JSON.stringify(condition)
	};
	$.post(url, data, function (result) {
		$('#meetingList').empty();
		console.log(result);
		viewMeetingList(result.data.rows);
	});
}

/*打开创建会议的页面*/
function selectMeetingweb() {
	window.location.href = "selectMeeting.html";
}

/*下载会议列表*/
function downloadMeetingList() {
	var condition = {
		"limit": 10,
		"searchRegion": "all",
		"start": 0,
		"title": ""
	};
	var url = host + "/conference/search.do";
	var data = {
		'userId': userids,
		condition: JSON.stringify(condition)
	};
	$.post(url, data, function (result) {
		$('#meetingList').empty();
		console.log(result);
		viewMeetingList(result.data.rows);
	});
	//	
	//		url: "http://1931507me2.iok.la:28228/rvc/conference/search.do?",
	//		data:{"userId":"A8AB6724AA5441EA8AEC6E5F12F2A548","condition":{"limit":10,"searchRegion":"related","start":0,"title":"Arvin"}},

}
/*点击进入会议*/
function openVideo() {
	var $tr = $(this);
	var conferenceId = $tr.data('conferenceId');
	console.log(conferenceId);
	if (!conferenceId) {
		alert("加载错误，刷新重试");
		return;
	}
	window.location.href = "meeting.html?conferenceId="+conferenceId;
}

/*渲染会议列表*/
function viewMeetingList(list) {
	console.log(list);
	for (var i = 0; i < list.length; i++) {
		var title = list[i].title;
		var beginDate = list[i].beginDate;
		var endDate = list[i].endDate == null ? "/" : list[i].endDate;
		var duration = list[i].duration == null ? "/" : list[i].duration;
		var conferenceId = list[i].id;
		var createUserId=list[i].createUserId;
		var liveURL = list[i].liveURL;
		var status = list[i].status == 0 ? (createUserId==userids?'<button type="button" class="btn startMeeting btn-default">开始会议</button>':"<div class='status' style='color: dodgerblue;'>未召开</div>"): (status = list[i].status == 1 ? "<div class='status' style='color: limegreen;'>正在召开</div>" : "<div class='status' style='color: gray;'>已召开</div>");
		var tr = template.replace('[title]', title)
			.replace('[beginDate]', beginDate)
			.replace('[endDate]', endDate)
			.replace('[duration]', duration)
			.replace('[status]', status);
		tr = $(tr);
		$(tr).data('conferenceId', conferenceId);
		$(tr).data('liveURL', liveURL);
		$('#meetingList').append(tr);
	}
}

/*会议列表的样式*/
var template = '<tr>' +
	'<td><input type="checkbox"></td>' +
	'<td>[title]</td>' +
	'<td>[beginDate]</td>' +
	'<td>[endDate]</td>' +
	'<td>[duration]</td>' +
	'<td>[status]</td>' +
	'</tr>';

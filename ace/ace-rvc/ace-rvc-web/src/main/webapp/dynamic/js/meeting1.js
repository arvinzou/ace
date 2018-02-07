var host = 'http://1931507me2.iok.la:28228/rvc';
//var host = 'http://192.168.1.104:6001/rvc';
var userids = 'E341A7084C56499AB0390BCAA3AA5BCD';
var conferenceId = '';
var userIdsData;
var RECTime;
var MeetingPersonTotal;
var MeetingPersonSignSum = 0;
$(function () {
	initMeeting();
	initTimer();
	$('#uploadMeetingFileBtn').click(uploadMeetingFileDo);
	$('#uploadMeetingDataBtn').click(uploadMeetingDataDo);
	$('.upFilePageHide').click(hideUpFilePageDo);
	$('.menuList').on('click', '.meetingFileMenu', showMeetingFile);
	$('.menuList').on('click', '.meetingDataMenu', showMeetingData);
	$('.menuList').on('click', '.videoMeetingMenu', showMeetingVideo);
	$('#meetingPersonList').on('click', 'li', signAction);
	$('.searchMeetingFile').click(searchFileDo);
	$('.searchMeetingData').click(searchDataDo);
	$('#allSignBtn').click(SignAllDo);
	$('.RECBtn').click(RECActionDo);
	$('.stopMeeting').click(stopMeetingDo);
	$('.printScreen').click(drawImgDo);
	$('#emojiList').on('click','li',addEmojiDo);
	$('#emojiBtn').click(showEmojiDo)
	initEmoji();
});



/*显示emoji框框*/
function showEmojiDo(){
	$('.emojisWindow').show();
}

/*点击Emoji图爿*/
function addEmojiDo(){
	var value=$(this).attr("value");
	var val=$('#chatInputText').val();
	var textval=val+value;
	var num=textval.length;
	$('#chatInputText').val(val+value);
	 $('#chatInputText').setCurPos(num,num);
}
/*初始化emoji按钮*/
function initEmoji(){
	$('#emojiList').empty();
	for(var i=1;i<88;i++){
		var li=emojiBox.replace('[i]',i).replace('[i]',i);
		$('#emojiList').append($(li));
	}
}

var emojiBox='<li value="[#[i]#]"><img src="images/emoji/[i].png"/></li>'

/*上传文件按钮*/
function uploadMeetingFileDo() {
	$('.upFilePage').show();
	initFileUpload();
}

function uploadMeetingDataDo() {
	$('.upFilePage').show();
	initFileUpload();
}

function hideUpFilePageDo() {
	$('.upFilePage').hide();
}

/*截图*/
function drawImgDo() {
	var video = document.getElementsByTagName("video")[0];
	if(!video){
		video = document.getElementsByTagName("object")[0];
	}
	console.log(video);
	//var video = document.getElementById("myTabContent");
	var canvas2 = document.getElementById('canvas2');
	var context2 = canvas2.getContext('2d');
	var base = '';
	context2.drawImage(video, 0, 0, 1041, 644);
	var url = host + '/file/uploadBase64Str.d';
	var data = {
		'base64Str': base,
		'fileName': conferenceId + (new Date().getTime()),
		'suffix': 'png',
	};
	$.post(url, data, function (result) {
		if (result.result_code == 1) {
			console.log(result);

		}
	});
}

//文件下载初始化
function initFileUpload() {
	$("#input-711").fileinput({
		language: 'zh',
		uploadUrl: host + '/file/upload.do', // server upload action  
		uploadAsync: false,
		maxFileCount: 8,
		showBrowse: true,
		browseOnZoneClick: true,
		uploadExtraData: {
			'conferenceId': conferenceId,
			'userId': userids,
		}
	});
}
/*结束会议*/
function stopMeetingDo() {
	$(this).css('color', '#de2800');
	if (confirm("确定结束会议？")) {
		var url = host + '/conference/end.do';
		var data = {
			'userId': userids,
			'conferenceId': conferenceId,
		};
		$.post(url, data, function (result) {
			if (result.result_code == 1) {} else {
				alert("结束会议失败，请刷新后重试！");
			}
		});
	}
	$(this).css('color', '');
}

/*屏幕录制开始*/
function RECActionDo() {
	var $that = $(this);
	if ($.cookie('nowTime')) {
		/*如果有时间*/
		if (RECMethod('stop')) {
			clearInterval(RECTime);
			$that.css('color', '');
			RECTime = null;
			$.cookie('nowTime', null);
		}
	} else {
		/*如果没有时间*/
		if (RECMethod('start')) {
			var nowTime = new Date().getTime();
			$.cookie('nowTime', nowTime);
			$that.css('color', '#de2800');
			timer(nowTime);
		}
	}
}
/*录制操作数据上传方法*/
function RECMethod(action) {
	var url = host + '/conference/record.do';
	var data = {
		'userId': userids,
		'conferenceId': conferenceId,
		'action': action,
	};
	$.post(url, data, function (result) {
		if (result.result_code == 1) {
			return true;
		}
		return false;
	});
}
/*初始化计时器*/
function initTimer() {
	if ($.cookie('nowTime')) {
		timer($.cookie('nowTime'));
	}
}

/*计时器*/
function timer(startTime) {
	document.getElementById("RECtime").innerText = "00:00:00";
	var sec = 0;
	RECTime = setInterval(function () {
		sec = (new date().getTime() - startTime) / 1000;
		var date = new Date(0, 0);
		date.setSeconds(sec);
		var h = date.getHours(),
			m = date.getMinutes(),
			s = date.getSeconds();
		document.getElementById("RECtime").innerText = two_char(h) + ":" + two_char(m) + ":" + two_char(s);
	}, 500);
}
/*时间位数检测*/
function two_char(n) {
	return n >= 10 ? n : "0" + n;
}


/*一键签到*/
function SignAllDo() {
	if ($("#allSignBtn").is(':checked')) {
		if (confirm("确定参会人员已经全部到齐了？确定则后不可修改！")) {
			var url = host + '/conference/signIn.do';
			var data = {
				'signInType': 'all',
				'userId': userids,
				'conferenceId': conferenceId,
				'ids': userIdsData,
			};
			console.log("signAll");
			$.post(url, data, function (result) {
				if (result.result_code == 1) {
					$('#meetingPersonList li').css('color', '#de2800');
					$('#allSignBtn').prop("disabled", true);
					MeetingPersonSignSum = MeetingPersonTotal;
					viewPersonNumber();

				} else {
					$("#allSignBtn").prop("checked", false);
					alert('签到失败，请刷新后重试');
				}
			});
		}
	}
}


//function switchStream(liveURL,title){
//		console.log('toplay');
//		SewisePlayer.toPlay('http://192.168.20.243/hlsfile/2018/01/05/20180105092346_975/1280_720/playlist.m3u8', title, "", true);
//}


/*查询会议文件*/
function searchFileDo() {
	console.log(123);
	var value = $('.searchFileName').val();
	console.log(value);
	if (value) {
		return;
	}
	var url = '';
	var data = '';
	$.getJSON(url, data, function (result) {

	});
}
/*查询会议文件*/
function searchDataDo() {
	console.log(12345);
	var value = $('.searchDataName').val();
	console.log(value);
	if (value) {
		return;
	}
	var url = '';
	var data = '';
	$.getJSON(url, data, function (result) {

	});
}

/*点击签到*/
function signAction() {
	var $li = $(this);
	var thisColor = $li.css('color');
	console.log("thiscolor");
	if (thisColor == 'rgb(222, 40, 0)') {
		console.log("已签到");
		return;
	}
	var url = host + '/conference/signIn.do';
	var data = {
		'signInType': 'self',
		'userId': userids,
		'conferenceId': conferenceId,
		'ids': $li.data('userId'),
	};
	$.post(url, data, function (result) {
		if (result.result_code == 1) {
			$li.css('color', '#de2800');
			MeetingPersonSignSum += 1;
			viewPersonNumber();
		}
	});
}
/*初始化网页*/
function initMeeting() {
	"use strict";
	$('.contentMeeting').hide();
	$('#onPlayer').hide();
	$('#MeetingVideo').show();
	$('#chatTextWindow').scrollTop($('#chatTextWindow')[0].scrollHeight);
	var thisURL = document.URL;
	var getval = thisURL.split('?')[1];
	conferenceId = getval.split("=")[1];
	loadVideo();
	loadMeetingPerson();
}
/*下载参加会议的人员名单*/
function loadMeetingPerson() {
	"use strict";
	var url = host + '/conference/getMemberList.do';
	var data = {
		'conferenceId': conferenceId,
		'userId': userids
	};
	$.post(url, data, function (result) {
		if (result.result_code == 1) {
			viewMeetingPersonList(result.data);
		}
	});
}
/*渲染与会人员*/
function viewMeetingPersonList(data) {
	"use strict";
	userIdsData = '';
	MeetingPersonTotal = data.length;
	$('#meetingPersonList').empty();
	for (var i = 0; i < data.length; i++) {
		var li = meetingPersonBox.replace('[name]', data[i].userName);
		userIdsData += data[i].userId;
		if (i < data.length - 1) {
			userIdsData += ',';
		}
		var $li = $(li).data('userId', data[i].userId);
		if (data[i].status == 1) {
			$li.css("color", "#de2800");
			MeetingPersonSignSum += 1;
		}
		$('#meetingPersonList').append($li);
	}
	viewPersonNumber();
}
var meetingPersonBox = '<li><i class="iconfont"> </i>[name]</li>';

/*渲染会议人数和签到人数*/
function viewPersonNumber() {
	$('.signNumber .number1').text(MeetingPersonSignSum);
	$('.signNumber .number2').text(MeetingPersonTotal);
}
/*加载视频*/
function loadVideo() {
	var url = host + '/conference/get.do';
	var data = {
		'conferenceId': conferenceId,
		'userId': userids,
	};
	$.post(url, data, function (result) {
		console.log(result);
		if (result.result_code == 1) {
			if (result.data.status == 0) {
				$('#onPlayer').show();
			}
			showTitle(result.data);
			$.cookie('liveURL', result.data.liveURL);
			console.log(result.data.liveURL);
		}
	});
}

/*显示title*/
function showTitle(data) {
	var statu = data.status == 0 ? '<span style="color: dodgerblue">[未召开]</span>' : data.status == 1 ? '<span style="color: green">[正在召开]</span>' : '<span style="color: gray">[已召开]</span>';
	var title = '<p>' + statu + '   ' + data.title + '</p>';
	console.log(title);
	$('.meetingTitle').empty();
	$('.meetingTitle').append($(title));
}

function showMeetingFile() {
	console.log('123');
	initMenu($(this));
	$('#meetingFile').show();
}

/*查看会议数据*/
function showMeetingData() {
	console.log(2);
	var url = host + '/conference/res/get.do';
	var data = {
		'resType': '1,2,3,4,5',
		'userId': userids,
		'conferenceId': conferenceId,
	};
	$.post(url, data, function (result) {
		console.log(result);
		if (result.result_code == 1) {
			viewMeetingData(result.data);
			initMenu($(this));
			$('#meetingData').show();

		}
	});
}
/*渲染会议数据*/
function viewMeetingData(data) {
	$('#MeetingDataList').empty();
	for (var i = 0; i < data.length; i++) {
		var tr = MeetingDataBox.replace('[#]', i + 1)
			.replace('[fileName]', data[i].fileName)
			.replace('[date]', data[i].date)
			.replace('[author]', data[i].author)
			.replace('[fileURL]', data[i].fileURL);
		$('#MeetingDataList').append($(tr));
	}


}

/**/
var MeetingDataBox = '<tr>' +
	'<td>[#]</td>' +
	'<td>[fileName]</td>' +
	'<td>[date]</td>' +
	'<td>[author]</td>' +
	'<td><button href="[fileURL]" type="button" class="btn btn-default" download="[fileName]">下载</button></td>' +
	'</tr>';

function showMeetingVideo() {
	console.log(2);
	initMenu($(this));
	$('#MeetingVideo').show();
}


function initMenu(that) {
	that.siblings().removeClass('menuActive');
	that.addClass('menuActive');
	$('.contentMeeting').hide();
}

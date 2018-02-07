var host = 'http://1931507me2.iok.la:28228/rvc';
var userids = 'E341A7084C56499AB0390BCAA3AA5BCD';
var inviteList;
$("#startDate").datetimepicker({
	format: "yyyy-mm-dd hh:ii",
	language: 'zh-CN',
	autoclose: true,
	todayBtn: true,
	minuteStep: 10
}).on('change', function (ev) {
	var startDate = $('#startDate').val();
	$("#returnDate").datetimepicker('setStartDate', startDate);
	$("#startDate").datetimepicker('hide');
});
$("#returnDate").datetimepicker({
	format: "yyyy-mm-dd hh:ii",
	language: 'zh-CN',
	autoclose: true,
	todayBtn: true,
	minuteStep: 10
}).on('change', function (ev) {
	var returnDate = $("#returnDate").val();
	$("#startDate").datetimepicker('setEndDate', returnDate);
	$("#returnDate").datetimepicker('hide');
});

$(function () {
	initHtml();
	$('.next_1').click(NextAddPerson);
	$('.back_1').click(backSelectMessage);
	$('.next_2').click(NextSuccessWeb);
	$('.selectMessage').on('focus', '.form-control', hidePromptMessage);
	$('.address').focus(searchAddressDo);
	$('.addressList').on('click', 'li', addAddressDo);
	$('.personList').on('click', '.meeter', addPersonDo);
	$('.createMeetingOK').click(createMeetingdo);
});
/*创建会议完成*/
function createMeetingdo(){
	    console.log('postData1111111111111111111111111');
		
		var title = $('.title').val();
		var emceeName = $('.emceeName').val();
		var description = $('.description').val();
		var beginDate = $('.beginDate').val();
		var endDate = $('.endDate').val();
		var address = $('.address').val();
		
		var url = host+"/conference/create.do";
	 	var data = {
			'userId': userids,
	 		'conference':JSON.stringify({
				'title':title,
				'emceeName':emceeName,
				'description':description,
				'beginDate':beginDate,
				'endDate':endDate,
				'address':address,
				'inviteList':inviteList
			})
	 	};
		
	 	$.post(url, data, function (result) {
			console.log(result);
	 		if (result.result_code == 1) {
	 			
	 		}
	 	});
}

/*点击添加参会人员*/
function addPersonDo() {
	var color = $(this).css('color');
	if (color == 'rgb(222, 40, 0)') {
		$(this).css('color', '#000');
		return;
	}
	$(this).css('color', '#de2800');
}

/*点击预选地址*/
function addAddressDo() {
	var address = $(this).text();
	$('.address').val(address);
	$('.showAddress').hide();
}
/*网页初始化*/
function initHtml() {
		$('.schedule').hide();
	 	$('.schedule-1').show();
	 	$('.personMessage').hide();
	 	$('.successMessage').hide();
		$('.showAddress').hide();
	    loadPersonList();
}
/*下载与会人员列表*/
function loadPersonList() {
	var url = host + "/user/all.do";
	var data = {
		userId: userids
	};
	$.getJSON(url, data, function (result) {
		console.log(result);
		if (result.result_code == 1) {
			viewPersonList(result.data);
		} else {
			alert('请刷新网页！');
		}
	});
}
/*渲染与会人员列表*/
function viewPersonList(data) {
	$('.meeters').empty();
	console.log(data);
	for (var i = 0; i < data.length; i++) {
		var li = boxPerson.replace('[name]', data[i].userName);
		var $li = $(li).data('userId', data[i].userId);
		$('.meeters').append($li);
	}

}

var boxPerson = '<li class="meeter">[name]</li>';


/**/

/*当输入地址时自动查找地址信息*/
function searchAddressDo() {
	var url = host + "/conference/getAddrList.do";
	var data = {
		'userId': userids,
	};
	$.getJSON(url, data, function (result) {
		console.log(result);
		if (result.result_code == 1) {
			$('.showAddress').show();
			$('.addressList').empty();
			viewAddress(result.data);
		}
	});
}
/*渲染地址列表*/
function viewAddress(data) {

	for (var i = 0; i < data.length; i++) {
		console.log(data.addrName);
		var tr = template.replace('[address]', data[i].addrName);
		tr = $(tr);
		$('.addressList').append(tr);
	}
}
var template = '<li>[address]</li>';

/*点击输入是取消提示*/
function hidePromptMessage() {
	console.log(12312412);
	$('.promptMessage').text('');
}

/*基本信息填写完成，下一步*/
function NextAddPerson() {
	var title = checkProperty('title', '会议名称', false, 50);
	var emceeName = checkProperty('emceeName', '会议主持人', false, 200);
	var description = checkProperty('description', '会议议程', true, 500);
	var beginDate = checkProperty('beginDate', '会议开始时间', false, 100);
	var endDate = checkProperty('endDate', '会议结束时间', true, 100);
	var address = checkProperty('address', '会议地址', false, 200);
	/* 会议参数   Demo:{"address":"湖南省常德市武陵区","addressId":"AE8E8E8EC8C8","beginDate":"2017-12-20 17:19:01","endDate":"2017-12-20 17:19:01","title":"接口创建测试会议"}*/
	//	 {"addressId":"addres-id","addressName":"常德市市政协会议室","beginDate":"2018-01-03 17:11:18","emceeId":"eUserID","emceeName":"主持人-Mir朱","endDate":"2018-01-03 17:11:18","inviteList":[{"userId":""},{"join":false,"level":"1","userId":"id1","userName":"普通人1"},{"join":false,"level":"1","userId":"id2","userName":"普通人2"}],"subtitle":"会议议程：1、议程1。2、议程23、议程3","title":"常德市党建会议"}

	if (title === false || emceeName === false || description === false || beginDate === false || endDate === false || address === false) {
		return;
	}
	$('.schedule').hide();
	$('.schedule-2').show();
	$('.selectMessage').hide();
	$('.personMessage').show();
	$('.successMessage').hide();
}
/*添加与会人员完成到第三步*/
function NextSuccessWeb() {
	var flag=true;
	var jsonStr = '[';
	var $li = $('.meeters').children();
	for (var i = 0; i < $li.length; i++) {
		if ($li.eq(i).css('color') == 'rgb(222, 40, 0)') {
			if (flag) {
				flag = false;
			} else {
				jsonStr += ',';
			}
			var userName = $li.eq(i).html();
			var userId = $li.eq(i).data('userId');
			jsonStr += '{"userId":"' + userId + '","userName":"' + userName + '"}';
		}
		
	}
	jsonStr +=']';
	if (flag) {
		alert("没有选择参会人员");
		return;
	}
	inviteList=JSON.parse(jsonStr);
	$('.schedule').hide();
	$('.schedule-3').show();
	$('.selectMessage').hide();
	$('.personMessage').hide();
	$('.successMessage').show();
}

function backSelectMessage() {
	$('.schedule').hide();
	$('.schedule-1').show();
	$('.selectMessage').show();
	$('.personMessage').hide();
	$('.successMessage').hide();
}

/*检查会议信息填入是否准确*/

function checkProperty(name, propertyName, LetNull, maxSize) {
	var value = $('.' + name).val();
	/*没有输入*/
	if (!value) {
		/*允许为空*/
		if (LetNull) {
			return null;
		}
		$('.promptMessage').text(propertyName + '：不能为空');
		return false;
	}

	if (value.length <= maxSize) {
		return value;
	}
	$('.promptMessage').text(propertyName + '：最多输入' + maxSize + '个字。');
	return false;
}

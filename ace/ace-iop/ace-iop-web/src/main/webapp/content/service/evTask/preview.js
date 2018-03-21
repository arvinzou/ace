var gd={};
var category="1462546202593";
jQuery(function($) {
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
	loadView(evTaskId);


});
function loadView(id) {
	$.ajax({
		type : "get",
		url : contextPath + "/evTask/selectEvTaskByPrimaryKey.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			var html1 = new Array();
			var html2 = new Array();
			gd = rst.response;
			category=rst.response.category;
			if(category=="1462546395184"){
                    $('#preview-cns1').html("第三步 打印二维码");
                    $('#preview-cns2').html("<a href=\"javascript:printqr()\">打印</a>");
                }
			//alert(rst.response.category);
			if (rst && rst.state) {
			
					var a=(rst.response.allUsers-rst.response.voteUsers);
					var b=parseInt(((rst.response.allUsers-rst.response.voteUsers)/rst.response.allUsers)*100);
					$('#jk').html("本次测评共分发账号："+rst.response.allUsers+"个，实际参与"+a+"人，参评率 "+b+"%。<a href='javascript:viewTaskUser()'>监控投票</a>");
				
				$.each(rst.response, function(key, value) {
					if(key=='status'){
						rst.response.status=rsd(value, "STATIC_DATA_54");
					}
					if(key=='evTempleteId'){
						rst.response.evTempleteId=rsd(value, "STATIC_DATA_53");
					}
					if(key=='category'){
						rst.response.category=rsd(value, "STATIC_DATA_20");
					}
				});
				$.each(rst.response, function(key, value) {
					$('#' + key).html(value);
				});
				
				
				
				$.each(rst.list, function(i, o) {

				});
			} else {
				alert(rst.errorMessage);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function(XMLHttpRequest, textStatus) {
			alert(textStatus);
		}
	})
}
function reloadGrid(){
	loadView(evTaskId);
}

function setEvContent(){
	console.log(gd);
	parent.addPanel(gd.evTaskName+".评测说明配置", contextPath+ '/dynamic/service/evTask/add.jsp?id=' + urlid+ '&evTaskId=' + gd.evTaskId + "&time="+ new Date(), true);
}

function startEv(){
	console.log(gd);
	parent.addPanel(gd.evTaskName+".开启投票", contextPath+ '/dynamic/service/evTask/add-status.jsp?id=' + urlid+ '&evTaskId=' + gd.evTaskId + "&time="+ new Date(), true);
}
function report(){
	console.log(gd);
	//alert(category);
	var page="";
	if(category=="1462546202593"){
		page="report-leader.jsp";
	}
	if(category=="1462546395184"){
		page="report-teacher.jsp";
	}
	if(category=="1462546395182"){
		page="report-leader-member.jsp";
	}
	parent.addPanel(gd.evTaskName+".统计",contextPath+ '/dynamic/service/evTask/'+page+'?id=' + urlid+ '&evTaskId=' + gd.evTaskId + "&time="+ new Date(),true);
}
function preview(){
	console.log(gd);
	//alert(category);
	var page="vote-view-leader-member.jsp";
	if(category=="1462546202593"){
		page="vote-view-leader.jsp";
	}
	if(category=="1462546395184"){
		page='/dynamic/www/vote/index.jsp?deptId=010601&classId=1&gradeId=10&evTaskId='+ gd.evTaskId;
		window.open(contextPath+ page);
		return;
	}
	window.open(contextPath+ '/dynamic/service/evTask/'+page+'?id=' + urlid+ '&evTaskId=' + gd.evTaskId + "&time="+ new Date());
}
function setTaskUser(){
	console.log(gd);
	parent.addPanel(gd.evTaskName+".分发账号", contextPath+ '/dynamic/service/evTask/add-task-user.jsp?id=' + urlid+ '&evTaskId=' + gd.evTaskId + "&time="+ new Date(), true);
}
function viewTaskUser(){
	console.log(gd);
	parent.addPanel(gd.evTaskName+".账号列表", contextPath+ '/dynamic/service/evTask/view-task-user.jsp?id=' + urlid+ '&evTaskId=' + gd.evTaskId + "&time="+ new Date(), true);
}
function printqr(){
    window.open(contextPath+ '/dynamic/service/evTask/printqr.jsp?id=' + urlid+ '&evTaskId=' + gd.evTaskId + "&time="+ new Date());
}
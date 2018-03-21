var gd={};
jQuery(function($) {
	findMyEvTaskList($('#task-content1'),1);
	//findMyEvTaskList($('#task-content2'),2);
	window.setInterval(interval, 10000);
	
});
function interval(){
	findMyEvTaskList($('#task-content1'),1);
	findMyEvTaskList($('#task-content2'),2);
}

function findMyEvTaskList(obj,status) {
	$.ajax({
		type : "post",
		url : "/ev/evTask/findMyEvTaskList.do",
		data : {
			status:status
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			
			var html = new Array();
			$.each($(rst.list), function(i, o) {
				var url='';
				var page="vote-view-leader-member.jsp";
				if(o.category=="1462546202593"){
					page="vote-view-leader.jsp";
				}
				if(o.category=="1462546395184"){
					page="vote-view-teacher.jsp";
				}
				url='/ev/dynamic/service/evTask/'+page+'?id=' + urlid+ '&evTaskId=' + o.evTaskId + "&time="+ new Date();
				html.push('<tr>');
				html.push('<td  align="center">'+(i+1)+'</td>');
				html.push('<td><a href="'+url+'" target="_blank">'+o.evTaskName+'</a></td>');
				html.push('<td>'+o.evObjName+'</td>');
				html.push('<td>'+swap(o.status)+'</td>');
				html.push('<td>'+o.evDeadline+'</td>');
				html.push('<td><a href="'+url+'" target="_blank">开始评测</a></td>');
				html.push('</tr>');
				
			});
			$(obj).html(html.join(''));
			//console.log(html.join(''));
		}
	});
}
function swap(status){
	if(status=='1'){
		return "准备中";
	}
	if(status=='2'){
		return "评测中";
	}
	if(status=='3'){
		return "评测结束";
	}
	return "评测结束";
}
$(function () {
	initWeb();
	$('.videobar').mouseover(showStatusDo);
	$('.videobar').mouseout(hideStatusDo);
	$('.startStatus').click(startVideoDo);
	$('.stopStatus').click(stopVideoDo);
	$('.delect-video').click(delectVideoDo);
});

$("#startDate").datetimepicker({
	format: "yyyy-mm-dd",
	language: 'zh-CN',
	autoclose: true,
	todayBtn: true,
	endDate: new Date(),
	maxView: '4',
	minView: '2',
}).on('change', function (ev) {
	var startDate = $('#startDate').val();
	$("#endDate").datetimepicker('setStartDate', startDate);
	$("#startDate").datetimepicker('hide');
});
$("#endDate").datetimepicker({
	format: "yyyy-mm-dd",
	language: 'zh-CN',
	autoclose: true,
	todayBtn: true,
	endDate: new Date(),
	maxView: '4',
	minView: '2',
}).on('change', function (ev) {
	var endDate = $("#endDate").val();
	$("#startDate").datetimepicker('setEndDate', endDate);
	$("#endDate").datetimepicker('hide');
});

function initWeb() {
	$('.startStatus').hide();
	$('.stopStatus').hide();
}

/*显示状态*/
function showStatusDo() {
	var media = $(this).find('video')[0];	
	if (media.paused) {
		var $startStatus=$('.startStatus');
		$(this).find($startStatus).show();
	} else {	
		var $stopStatus=$('.stopStatus');
		$(this).find($stopStatus).show();
	}
}
function hideStatusDo(){
	$('.startStatus').hide();
	$('.stopStatus').hide();
}

function startVideoDo(){
	$('video').each(function(){
		$(this)[0].pause();
	});
	var media=$(this).parent().parent().find('video')[0];
	media.play();
}

function stopVideoDo(){
	var media=$(this).parent().parent().find('video')[0];
	media.pause();
}

function delectVideoDo(){
	$(":checked").each(function(index){
		var $this=$(this);
		if($this.is(':checked')){
			var id=$this.parent().parent().parent().data('id');
			actionDelectVideo(id);
		};
	});
}

function actionDelectVideo(id){
	
}
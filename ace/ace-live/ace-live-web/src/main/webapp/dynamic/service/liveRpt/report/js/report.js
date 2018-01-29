var host='http://localhost/live';
var start;
var limit=25;
var orderByStr=null;
var id;
var mediaType=2;

$(function () {
	initweb();
	$(":radio").click(chooseTypeDo);
    $('.cancel').click(hideSelectReportDo);
    $('#liveListTable').on('click','a',actionSelectReportDo);
    $('.search').click(searchByNameDo);
    $('.release').click(releaseDo);
});
/*确认发布报道*/
function releaseDo() {
	
}

/*按名字搜索直播*/
function searchByNameDo(){
    var inputName=$('.searchByName').val();
    console.log(inputName);
    loadLiveList(inputName);
}

/*点击添加直播报道*/
function actionSelectReportDo() {
	id=null;
    id= $(this).parent().parent().data('Liveid');
    if(!id){
    	return;
	};
    $('.select_report').show();
}

/*点击隐藏发布直播页*/
function  hideSelectReportDo() {
	$('.select_report').hide();
}
/*选择报道类型*/
function chooseTypeDo() {
    mediaType=$(this).val();
	switch (parseInt(mediaType)) {
		case 1: chooseVideoDo(); break;
		case 2: chooseImageDo(); break;
		case 3:	chooseAudioDo();
	}
}
function chooseVideoDo(){
	$('#file-3').fileinput('clear');
	$('#file-3').fileinput('refresh',{maxFileCount: 1,allowedPreviewTypes: ['video'],allowedFileTypes: ['video']});
}
function chooseImageDo(){
	$('#file-3').fileinput('clear');
	$('#file-3').fileinput('refresh',{maxFileCount: 4,allowedPreviewTypes: ['image'],allowedFileTypes: ['image']});
}
function chooseAudioDo(){
	$('#file-3').fileinput('clear');
	$('#file-3').fileinput('refresh',{maxFileCount: 1,allowedPreviewTypes: ['audio'],allowedFileTypes: ['audio']});
}
/*网页初始化*/
function initweb() {
	initFileUpload();
	chooseImageDo();
    loadLiveList();
}
/*下载直播数据*/
function loadLiveList(name) {
    var url=host+'/live//findLiveList.do';
    var data={
        'name':name,
        'start':start,
        'limit':limit,
        'orderBy':orderByStr,
        'deptId':'00010001'
    }
    $.getJSON(url, data,function (result) {
        if(result.status==0){
            viewLiveList(result.rows);
        }
    })
}

/*渲染直播列表*/
function viewLiveList(data) {
    $('#liveListTable').empty();
    for(var i=0;i<data.length;i++){
        var trLive=liveTemplate;
        trLive=trLive.replace('[name]',data[i].name);
        trLive=trLive.replace('[createUserName]',data[i].createUserName);
        trLive=trLive.replace('[startTime]',data[i].startTime.substring(0,16));
        var $tr=$(trLive).data("Liveid",data[i].id);
        $('#liveListTable').append($tr);
    }
}

/*直播列表模板*/
var liveTemplate='<tr>'+
    '                <td>[name]</td>'+
    '                <td>[createUserName]</td>'+
    '                <td>[startTime]</td>'+
    '                <td><a>发布报道</a></td>'+
    '</tr>';

/*文件上传组件初始化*/
function initFileUpload() {
	$("#file-3").fileinput({
		theme: 'fa',
		language: 'zh',
		uploadUrl: 'www',
		uploadAsync: false,
		showUpload: false,
		maxFileCount: 4,
		showCaption: false,
		browseClass: "btn btn-primary btn-lg",
		fileType: "any",
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
		overwriteInitial: false,
		initialPreviewAsData: true,
	});
}

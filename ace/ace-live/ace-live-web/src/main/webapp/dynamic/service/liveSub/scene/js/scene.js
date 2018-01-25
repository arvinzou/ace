/*变量*/
var host='http://localhost/live';
var imghost="http://zx.huacainfo.com/";
var start;
var limit=25;
var orderByStr=null;
var id;
var status;
$(function(){
    initWeb();
    $('.actionList').click(actionListDo);
    $('.search').click(searchByNameDo);
    $('.sortLive').click(sortLiveDo);
    $('.sceneList').on('click','.picbar',modifyLiveDo);
    $('.cancel').click(hideModifyWebDo);
});


/*隐藏修改页面*/
function hideModifyWebDo() {
    $('.modify').hide();
    $(".modify .form-control").val('');
    $('.imgbar').empty();
}
/*修改直播信息*/
function modifyLiveDo() {
    console.log('修改直播');
    id= $(this).parent().data('Liveid');
    console.log(id);
    var url=host+'/live/selectLiveByPrimaryKey.do';
    var data={
        'id':id
    }
    $.getJSON(url, data,function (result) {
        console.log(result);
        if(result.status==0){
           showModifyWeb(result.value);
        }
    })
}
/*进入修改页*/
function showModifyWeb(data) {
    $('.modify').show();
    for (var item in data) {
        if(item=='imageSrc'){
            console.log(data[item]);
            viewCover(data[item]);
        }else if(item=='category'){
            $(":radio[name='category'][value='" + data[item] + "']").prop("checked", "checked");
        }else if(item=='status'){
            status=data[item];
        }else{
            $('.'+item).val(data[item]);
        }
    }
}

/*按开始时间排序*/
function sortLiveDo() {
    console.log('orderByStr');
    if(!orderByStr){
        orderByStr='startTime';
    }else{
        orderByStr=null;
    }
    searchByNameDo();
}

/*按名字搜索直播*/
function searchByNameDo(){
    var inputName=$('.searchByName').val();
    console.log(inputName);
    loadLiveList(inputName);
}

/*点击直播*/
function actionListDo(){
    console.log('actionListDo');
    var url=host+'/live/findLiveList.do';
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

/*初始化页面*/
function initWeb(){
    start=0;
    $('.modify').hide();
    /*下载直播数据*/
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
    $('.sceneList').empty();
    for(var i=0;i<data.length;i++){
        var liLive=liveTemplate;
        liLive=liLive.replace('[imageSrc]',imghost+data[i].imageSrc);
        liLive=liLive.replace('[name]',data[i].name);
        liLive=liLive.replace('[createUserName]',data[i].createUserName);
        liLive=liLive.replace('[startTime]',data[i].startTime.substring(0,16));
        var status=data[i].status==2?'恢复直播':(data[i].status==1?'开始直播':'');
        liLive=liLive.replace('[status]',status);
        var $li=$(liLive).data("Liveid",data[i].id);
        $('.sceneList').append($li);
    }
}


var liveTemplate=' <li>'+
    '             <div class="picbar">'+
    '                <div class="pic">'+
    '                    <img src="[imageSrc]">'+
    '                </div>'+
    '                <span class="actionTop">'+
    '                	<i class=""></i>置顶'+
    '                </span>'+
    '                <span class="reportNum">15条报道'+
    '                </span>'+
    '                <div class="livetitle omission">[name]</div>'+
    '            </div>'+
    '            <div class="msgbar fn-clear"> '+
    '            	<span class="omission msgbar-common creater">'+
    '            		<i class=""></i>[createUserName]'+
    '            	</span>'+
    '                <span class="msgbar-common msgbar-time">'+
    '            		<i class="pz-icon icon-clock"></i>[startTime]'+
    '            	</span>'+
    '                <a class="j-stop">[status]</a>'+
    '            </div>'+
    '        </li>';
var timer;
$(window).resize(function () {   //当浏览器大小变化时
	clearTimeout(timer);
	timer=setTimeout(function(){
		window.location.reload();
	},500)
});
var params={
	start:0,
	limit:100
}

$(function () {
	getStations();
	$('#check').on('click','li',checkStation)
	$('#checked').on('click','li',removeStation)
});
/*删除选择站点*/

function removeStation() {
    var that=$(this);
    var code=that.data('code');
    var name=that.data('name');
    $('#videos .video'+code).remove();
    renderadd('#check',[{code:code, name:name}],'tpl-check');
    that.remove();
}

/*点击选择站点*/
function checkStation() {
	var that=$(this);
	var code=that.data('code');
	var name=that.data('name');
	if(!code){
		return
	}
	startLoad();
    var url=contextPath + "/animaLnk/findAnimaLnkList";
	var data={
        stationCode:code
	}
	$.getJSON(url,data,function (rst) {
		stopLoad();
        if(rst.status==0){
            that.remove();
            renderadd('#checked',[{code:code, name:name}],'tpl-checked');
            var data=rst.rows;
            renderadd('#videos',data,'tpl-videos')
		}
    })
}
/*获取所有站点*/
function getStations() {
	var url=contextPath + "/topStation/findTopStationList";
	$.get(url,params,function (rst) {
		if(rst.status==0){
			var data=rst.rows;
			render('#check',data,'tpl-check')
		}
    });
}

function renderadd(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).append(html);
}


/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}
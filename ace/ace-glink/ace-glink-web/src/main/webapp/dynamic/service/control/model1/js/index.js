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
    initStrategy();
	$('#check').on('click','li',checkStation);
	$('#checked').on('click','li',removeStation);
    $(' .modal .modal-content').on('click','.modal-close', closeModal);
    $(' .modal .modal-content').on('click','.modal-close', closeModal);
    $('.btns').on('click','.strategySend',function () {
        $('.content').hide();
        $('.strategySend').show();
    })
    $('.btns').on('click','.sceneControl',function () {
        $('.content').hide();
        $('.sceneControl').show();
    })
    // $('.btns').click('click','.strategySend',function () {
    //     $('.content').hide();
    //     $('.strategySend').show();
    // })
});


function closeModal() {
    $(this).parents().find('.modal').hide();
}

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

/*************************************************************************************************************/
var strategyParams = {
    start: 0,
    limit: 21
}

/*任务初始化管理*/
function initStrategy() {
    $('#strategyList').on('click', 'button', selectPreset);
    $('.strategy-modal #presets').on('click', 'li', setStrategy);
    initPageStrategy();
}

function setStrategy() {
    var that = $(this);
    strategyPostData.presetNo = that.data('presetNo');
    var url = '';
    $.post(url, strategyPostData, function () {

    })
}

/*初始化分页器*/
function initPageStrategy() {
    $.jqPaginator('#pagination3', {
        totalCounts: 1,
        pageSize: strategyParams.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            strategyParams['start'] = (num - 1) * strategyParams.limit;
            strategyParams['initType'] = type;
            getStrategyList();
        }
    });

/*    $.jqPaginator('#pagination4', {
        totalCounts: 1,
        pageSize: strategyParams.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            presetMap['start'] = (num - 1) * strategyParams.limit;
            presetMap['initType'] = type;
            if (type != 'init') {
                getStrategyList();
            }
        }
    });*/
}


/*ajax获取数据列表*/
function getStrategyList(key, value) {
    if (key) {
        strategyParams[key] = value;
    }
    var url = contextPath + "/ltStrategy/findLtStrategyList";
    $.getJSON(url, strategyParams, function (rst) {
        if (rst.status == 0) {
            if (strategyParams.initType == "init") {
                $('#pagination3').jqPaginator('option', {
                    totalCounts: rst.total == 0 ? 1 : rst.total,
                    currentPage: 1
                });
            }
            render($("#strategyList"), rst.rows, "tpl-strategyList");
        }
    });
}

var presetMap = {
    start: 0,
    limit: 10
};
var strategyPostData = {};

function selectPreset() {
    $('.scenario-modal').show();
    var url = contextPath + "/ltStrategy/strategysDetail";
    $.getJSON(url, presetMap, function (rst) {
        if (rst.status == 0) {
            render($("#presets"), rst.value.data, "tpl-presets");
        }
    });
}

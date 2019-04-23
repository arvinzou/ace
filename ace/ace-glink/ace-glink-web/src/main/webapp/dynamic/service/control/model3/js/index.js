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
    initPage();
});


/*分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 100,
        pageSize: 5,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {

        }
    });
}



/*添加渲染*/
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
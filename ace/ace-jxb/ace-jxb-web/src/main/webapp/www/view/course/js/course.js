/**
 * 课程点播
 * @type {*|jQuery}
 */

/**
 * 导航栏
 */
var th_width = $(".news-module li").eq(0).width();
var th_left = $(".news-module li").eq(0).offset().left;
var slider_width = $(".news-slider").width();
var slider_left = th_left + (th_width/2) - slider_width/2;
$(".news-slider").css("left",slider_left);
$(".news-module li").on("click",function(){
    var n = $(this).index();
    var th_width = $(this).width();
    var th_left = $(this).offset().left;
    var slider_width = $(".news-slider").width();
    var slider_left = th_left + (th_width/2) - slider_width/2;
    $(".news-slider").css("left",slider_left);
    $(this).addClass("active").siblings().removeClass("active");
});

$(function(){
    //初始化页面
    initWeb();

    //点击课程进入课程详细页面
    $('.course').on('click', '#course', loadCourseContent);
});

function initWeb() {
    $.get('/portal/system/getUserProp.do', function () {
        loadCourseList();
    });
}

function loadCourseList(category) {
    var url = '/jxb/www/jxb/findCourseList.do';
    var data = {
        'start': start,
        'limit': limit,
       // 'orderBy': orderByStr,
        'category' : category,
        'page' : 1,
       // 'sord': 'asc'
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewCourseList(result.rows);
        }
    })
}

/*渲染课程列表*/

function viewCourseList(data) {
    $('.audioContent').empty();
    for (var i = 0; i < data.length; i++) {
        var coursePanel = courseTemplate;
        coursePanel = coursePanel.replace('[audioCover]', imgHost + data[i].cover);
        coursePanel = coursePanel.replace('[duration]', data[i].duration);
        var audioType = data[i].mediType == '01'? '视频': '音频';
        coursePanel = coursePanel.replace('[audioType]', audioType);
        coursePanel = coursePanel.replace('[name]', data[i].name);
        var pay = data[i].costType == '01' ? '免费' : '¥' + data[i].cost;
        var costType = data[i].costType == '01' ? 'free' : 'pay';
        coursePanel = coursePanel.replace('[cost]', pay);
        coursePanel = coursePanel.replace('[costType]', costType);
        coursePanel = coursePanel.replace('[demandNum]', data[i].demandNum + '人学过');

        var $course = $(coursePanel).data('courseId', data[i].id);
        $('.audioContent').append($course);
    }
}

/**
 *   通过导航切换标题类型
 */
function changeCourseTab(category){
    console.log(category);
    if(category != null && category != undefined){
        loadCourseList(category);
    }
}

/*$(".audioContainer img").click(function(){
   location.href='/jxb/www/view/course/coursePlay.jsp';
});*/

function loadCourseContent(){
    location.href = '/jxb/www/view/course/coursePlay.jsp';
}
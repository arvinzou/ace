window.onload = function(){
    hotTeachers();
    freecourse();
    qualityCourse();
    initBanner();
    initSwriper();
}

/*初始化Swriper*/
function initSwriper() {
    var bannerSwiper = new Swiper('.swiper-container_panel', {
        pagination: {
            el: '.swiper-pagination',
        }
    });
    var freeCourseSwiper = new Swiper('.swiper-container_course', {
      slidesPerView: 'auto',
      spaceBetween: 20,
      freeMode: true
    });
   var teacherSwiper = new Swiper('.swiper-container_teacher', {
      slidesPerView: 'auto',
      spaceBetween: 20,
      freeMode: true
    });
}
function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
/**
 * 人气老师
 */
function hotTeachers(){
    $.ajax({
        url: contextPath+ "/www/index/getCounselorRanking",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                renderPage('hotTeachers', result.data.rows, 'teacher-tpl')
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

/**
 * 课程
 */
function freecourse(){
    $.ajax({
        url: contextPath+ "/www/index/findListFree",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                //限时免费
                renderPage('freeCourse', result.data.rows, 'free-tpl');
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}
function qualityCourse(){
    $.ajax({
        url: contextPath+ "/www/index/findListRand",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                //精品课程
                renderPage('qualityCourse', result.data.rows, 'quality-tpl');
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

/**
 * 顾问在线点击
 */
function consultClick(){
    window.location.href = contextPath + '/www/view/consultant/index.jsp';
}

/**
 * 心理课程点击
 */
function courseClick(){
    window.location.href = contextPath + '/www/view/course/index.jsp';
}

/**
 * 在线直播点击（该功能在建设中）
 */
function liveClick(){
    alert("该功能在建设中，敬请期待!");
}

/**
 * 心理测试点击
 */
function testClick(){
    window.location.href = contextPath + '/www/view/order/test/testindex.html';
}

/**
 * 进入咨询师主页咨询
 */
function loadCounselor(id){
    window.location.href = contextPath + '/www/view/counselor/index.jsp?id='+id;
}

/**
 * 查看课程详情
 * @param id
 */
function courseDetail(id){
    window.location.href = contextPath + '/www/view/courseSummary/index.jsp?id='+id;
}

/**
 * 心阳光联盟首页
 */
function index(){
    window.location.href = contextPath + '/www/view/index/index.jsp';
}

/**
 * 个人中心
 */
function mine(){
    window.location.href = contextPath + '/www/view/mine/mine.jsp';
}

function initBanner(){
    $.ajax({
        url: contextPath+ "/www/index/banner",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                renderPage('banner', result.data, 'banner-tpl');
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}
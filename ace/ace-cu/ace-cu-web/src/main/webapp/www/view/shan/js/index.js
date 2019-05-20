var barragerList = [
    {
        message: " IK hou van jou",
        headimg: "http://www.w3school.com.cn/i/eg_tulip.jpg"
    }
]

var item = {
    img: '', //图片
    info: '', //文字
    href: 'javascript:;', //链接
    close: true, //显示关闭按钮
    speed: parseInt(Math.random() * 6 + 5, 10), //延迟,单位秒,默认6
    color: '#ffffff', //颜色,默认白色
    old_ie_color: '#ffffff', //ie低版兼容色,不能与网页背景相同,默认黑色
}

var projectId;
var status;
var totalPoint=0;
$(function () {
    getProject();
    $('.menu .menuBtn').on('click','.href2',goRank);
    $('#sss').on('click','.help',alertHelp)
})

/**显示提示信息*/
function alertHelp(){
    var that=$(this);
    var message=that.data('message');
    alert(message);
}

/**查看排名*/
function goRank() {
    window.location.href ="jifen.jsp?projectId="+projectId;
}

/**点击导航按钮*/
function hrefgo() {
    var that=$(this);
    var type=that.data('type');
    window.location.href ="mine.jsp?type="+type+"&point="+totalPoint+'&projectId='+projectId;
}

/**捐款*/
function donate(){
    if(projectId != null){
        window.location.href = '/cu/www/view/order/order.jsp?donateType=2&projectId='+projectId;
    }
}

/**获取项目id*/
function getProject() {
    var url = "/cu/www/project/findList";
    var data = {start: 0, limit: 9999, type: "5"};
    $.post(url, data, function (rst) {
        if (rst.status == 0) {
            projectId = rst.data.rows[0].id;
            status = rst.data.rows[0].status;
            getInit();
        } else {
            alert(rst.errorMessage);
        }
    })
}

/**获取初始化数据*/
function getInit() {
    var url = "/cu/www/project/init";
    var data = {
        projectId:projectId
    };
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            var datas = JSON.parse(rst.data);
            totalPoint=datas.totalHeartPoint+datas.totalActionPoint;
            console.log('totalPoint',totalPoint);
            $('#point').text(totalPoint);
            barragerList=datas.bulletScreen;
            openBarrager();
            renderPage('sss', datas, 'tpl-sss');
            $('.menu .menuBtn').on('click','.href1',hrefgo);
        } else {
            alert(rst.errorMessage);
        }
    })
}

function openBarrager() {
    var i = 0;
    setInterval(function () {
        if (barragerList.length == i) {
            i = 0;
        }
        var s = barragerList[i];
        if(s.remark){
            if(s.remark.length>16){
                item.info = s.remark.substring(0,16)+'…';
            }
            item.info = s.remark;
            item.img = s.avatarUrl?s.avatarUrl:"img/people.png";
            $('#message').barrager(item);
        }
        i++;
    }, 2000)
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
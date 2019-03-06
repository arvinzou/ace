$(function () {
    juicer.register('dateTimeToDate', dateTimeToDate);
    getUserInfo();
    initData();
    $('#testNumber').on('click','.notDoneBtn',getNotDoneTests);
    $('#testNumber').on('click','.doneBtn',getDoneTests);
    $('#testList').on('click','.temp2',doTest);
    $('#testList').on('click','.temp1',viewTestd);
})


/**
 *查看测试过的评测*/

function viewTestd() {
    var eid=$(this).data('eid');
    var cid=$(this).data('cid');
    if(eid&&cid){
        window.location.href = 'detail.jsp?eid='+eid+'&cid='+cid;
        return;
    }
    alert("刷新页面重试");
}

/**开始测试*/
function doTest() {
    var eid=$(this).data('eid');
    var cid=$(this).data('cid');
    if(eid&&cid){
        window.location.href = 'test.jsp?eid='+eid+'&cid='+cid;
        return;
    }
    alert("刷新页面重试");
}

/**没有做的测试列表*/
function getNotDoneTests() {

    $(this).siblings().removeClass('action');
    $(this).addClass('action');
    getNotDoneTestList();
}


/**做了的测试列表*/
function getDoneTests() {
    $(this).siblings().removeClass('action');
    $(this).addClass('action');
    getDoneTestList();
}

/**时间截取*/
function dateTimeToDate(Str) {
    return Str.substring(0,11);
}

/**获取用户信息*/
function getUserInfo(){
    var url=contextPath+ "/www/sign/getAcctInfo";
    $.getJSON(url,function (rst) {
        if(rst.status==0){
            renderPage('userInfo',rst.data,'tpl_userInfo')
        }
        else {
            alert("获取用户信息失败！");
        }
    })
}


/**初始化数据*/
function initData() {
    getNotDoneTestList();
}

/**获取没有做的测试列表*/
function getNotDoneTestList() {
    var url=contextPath+ "/www/classSchedule/notDoneTestList";
    var data={
        start:0,
        limit:200
    }
    $.getJSON(url,data,function (rst) {
        if(rst.status==0){
            renderPage('testNumber',rst.data,'tpl_testNumber')
            renderPage('testList',rst.data.list,'tpl_notDoneTest')
        }
        else {
            alert("获取用户信息失败！");
        }
    })
}

/**获取做的测试列表*/
function getDoneTestList() {
    var url=contextPath+ "/www/classSchedule/DoneTestList";
    var data={
        start:0,
        limit:200
    }
    $.getJSON(url,data,function (rst) {
        if(rst.status==0){
            renderPage('testList',rst.data.list,'tpl_doneTest')
        }
        else {
            alert("获取用户信息失败！");
        }
    })
}

/**渲染方法*/
function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
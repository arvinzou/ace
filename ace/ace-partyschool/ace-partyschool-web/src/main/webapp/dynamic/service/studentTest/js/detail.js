$(function () {
    // 为每一个textarea绑定事件使其高度自适应
    juicer.register('parseIntF', parseIntF);
    juicer.register('formatIndex', formatIndex);
    getTestInfo();
    $.each($("textarea"), function (i, n) {
        autoTextarea($(n)[0]);
    });

    $('.back').click(back);
})
var eid;
var cid;

function back() {
    window.history.back();
}



/**计算并取整*/
function parseIntF(num) {
    return parseInt(num*0.9);
}

/**计算序列*/
function formatIndex(num) {
    var index=''+(parseInt(num)+2);
    return index>9?index:'0'+index;
}


var question='';
/**获取测试信息*/
function getTestInfo() {
    eid=GetQueryString('eid');
    cid=GetQueryString('cid');
    var url=contextPath+ "/www/classSchedule/selectClassScheduleByPrimaryKey";
    var data={
        id:cid
    }
    $.getJSON(url,data,function (rst) {
        if(rst.status==0){
            renderPage('title',rst.value.course,'tpl_title');
            question=rst.value.question;
        }
        else {
            console.log("获取用户信息失败！");
        }
    })
    url=contextPath+ "/www/evaluationRst/findEvaluationRstList";
    data={
        classScheduleId:cid
    }
    $.getJSON(url,data,function (rst) {
        if(rst.status==0){
            renderPage('test',rst.data,'tpl_test');
            if(question){
                $('#question').text(question);
            }
        }
        else {
            console.log("获取用户信息失败！");
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


/**解析url参数*/

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
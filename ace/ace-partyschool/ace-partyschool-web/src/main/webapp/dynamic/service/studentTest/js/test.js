$(function () {
    // 为每一个textarea绑定事件使其高度自适应
    juicer.register('parseIntF', parseIntF);
    juicer.register('formatIndex', formatIndex);
    getTestInfo();
    $.each($("textarea"), function (i, n) {
        autoTextarea($(n)[0]);
    });
    $('#textarea').keyup(checkFontLength);
    $('.testContent').on('click', '.subBtn', subScore);
    $('.testContent').on('click', '.addBtn', addScore);
    $('.submit').click(submitTest);
})
var eid;
var cid;

function submitTest() {
    var item=$('.items');
    var data=[];
    var cell={
        evaluatingId:eid,
        classScheduleId:cid
    }
    var length=item.length;
    for(var i=0;i<length;i++){
        var $that=$(item[i]);
        var cell={
            evaluatingId:eid,
            classScheduleId:cid
        }
        cell.name=$that.data('name');
        cell.introduce=$that.data('introduce');
        cell.score=$that.find('.number').val();
        data.push(cell);
    }
    postData(data);
}

/**提交数据*/
function postData(datas) {
    var url=contextPath+ "/www/evaluationRst/insertEvaluationRstList";
    var data={
        evaluationRst:JSON.stringify(datas),
        evaluationContent:JSON.stringify({
            classScheduleId:cid,
            content:$('#textarea').val(),
            judge:$('input[name="test_1"]:checked').val(),
        })
    }
    $.post(url,data,function (rst) {
        if(rst.status==0){
            alert("感谢您的测评");
            window.history.go(-1);
        }
        else {
            alert("获取用户信息失败");
        }
    })
}

/**计算并取整*/
function parseIntF(num) {
    return parseInt(num*0.9);
}

/**计算序列*/
function formatIndex(num) {
    var index=''+(parseInt(num)+1);
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
            alert("获取用户信息失败！");
        }
    })
    url=contextPath+ "/www/evaluationIndex/findEvaluationIndexList";
    data={
        evaluatingId:eid
    }
    $.getJSON(url,data,function (rst) {
        if(rst.status==0){
            renderPage('test',rst.rows,'tpl_test');
            if(question){
                $('#question').text(question);
            }
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


/**解析url参数*/

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}


/**
 * 添加分数
 */
function subScore() {
    var that = $(this);
    changeSore(that, -1);
}

/**
 * 添加分数
 */
function addScore() {
    var that = $(this);
    changeSore(that, 1);
}


function checkNumber(that,nums){
    var n=that.value;
    if(n>nums){
        that.value=nums;
    }if(n<0){
        that.value=0;
    }
}

/**
 * 更改分值
 */
function changeSore(that, i) {
    var total = that.parent().find('.number').data('total');
    var score = that.parent().find('.number').val();
    var now = parseInt(score) + i;
    if (now < 0 || now > total) {
        return;
    };
    that.parent().find('.number').val(now);
}


/**
 * 检查字数
 */

function checkFontLength() {
    var that = $(this);
    $('.input_text .font_number').text(that.val().length);
}

/**
 * 文本框根据输入内容自适应高度
 * {HTMLElement}   输入框元素
 * {Number}        设置光标与输入框保持的距离(默认0)
 * {Number}        设置最大高度(可选)
 */
var autoTextarea = function (elem, extra, maxHeight) {
    extra = extra || 0;
    var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
        isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
        addEvent = function (type, callback) {
            elem.addEventListener ?
                elem.addEventListener(type, callback, false) :
                elem.attachEvent('on' + type, callback);
        },
        getStyle = elem.currentStyle ?
            function (name) {
                var val = elem.currentStyle[name];
                if (name === 'height' && val.search(/px/i) !== 1) {
                    var rect = elem.getBoundingClientRect();
                    return rect.bottom - rect.top -
                        parseFloat(getStyle('paddingTop')) -
                        parseFloat(getStyle('paddingBottom')) + 'px';
                }
                ;
                return val;
            } : function (name) {
                return getComputedStyle(elem, null)[name];
            },
        minHeight = parseFloat(getStyle('height'));
    elem.style.resize = 'none'; //如果不希望使用者可以自由的伸展textarea的高宽可以设置其他值

    var change = function () {
        var scrollTop, height,
            padding = 0,
            style = elem.style;

        if (elem._length === elem.value.length) return;
        elem._length = elem.value.length;

        if (!isFirefox && !isOpera) {
            padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
        }
        ;
        scrollTop = document.body.scrollTop || document.documentElement.scrollTop;

        elem.style.height = minHeight + 'px';
        if (elem.scrollHeight > minHeight) {
            if (maxHeight && elem.scrollHeight > maxHeight) {
                height = maxHeight - padding;
                style.overflowY = 'auto';
            } else {
                height = elem.scrollHeight - padding;
                style.overflowY = 'hidden';
            }
            ;
            style.height = height + extra + 'px';
            scrollTop += parseInt(style.height) - elem.currHeight;
            document.body.scrollTop = scrollTop;
            document.documentElement.scrollTop = scrollTop;
            elem.currHeight = parseInt(style.height);
        }
        ;
    };

    addEvent('propertychange', change);
    addEvent('input', change);
    addEvent('focus', change);
    change();
}

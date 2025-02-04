$(function () {
    $('.push_data').on('click', submitForm);
    $('.content').on('blur', 'input', validateInput);
    $('.content').on('focus', 'input', clearStyle);
    $('.content').on('click', '.get_code', sendcode);
})

var countdown = 50;
function sendcode() {
    var result = validateform('mobilePhone');
    if (result.status == 1) {
        $('#mobilePhone').parent().css({'border-bottom': '1px solid red'});
        return
    }
    var url = '/fundtown/www/sms/sendCode';
    var data = {
        mobile: result.message,
    }
    $.post(url, data, function (result) {
        if (result.status != 0) {
            alert(result.info);
        }
    });
    settime();
}

function settime(){
    var btnName = "获取验证码";
    if (countdown == 0) {
        btnName = "获取验证码";
        countdown = 50;
        stop = true;
    } else {
        stop = false;
        btnName = "重新发送 " + countdown + "";
        countdown--;
    }

    stop = stop;
    countdown = countdown;
    $('.get_code').text(btnName);
    $('.get_code').attr("disabled", true);
    $('.get_code').css({'background-color':'#E1E5ED'},{'color':'#666666'});
    if (!stop) {
        setTimeout(function () {
            this.settime()
        }, 1000)
    }else{
        $('.get_code').attr("disabled", false);
        $('.get_code').css({'background-color':'#3283F9'},{'color':'#FFFFFF'});
    }
}


function clearStyle() {
    var $that = $(this);
    $that.parent().css({'border-bottom': '1px solid #E5E5E5'});
}

function submitForm() {
    var $input = $('input');
    var data = {};
    for (var i = 0; i < $input.length; i++) {
        var idName = $input.eq(i).attr('id');
        var result = validateform(idName);
        if (result.status == 1) {
            $input.eq(i).parent().css({'border-bottom': '1px solid red'});
            return
        }
        var key = $input.eq(i).attr('name');
        data[key] = result.message;
    }
    var url = '/fundtown/www/process/vipApply';
    var datas = {
        code: data.code,
        json: JSON.stringify(data)
    }
    $.post(url, datas, function (result) {
        if (result.status == 0) {
            window.location.href = 'schedule.html';
        } else {
            alert("入驻申请失败");
        }
    })
}

function validateInput() {
    var $that = $(this);
    if (!$that.val().trim()) {
        return;
    }
    $that.parent().css({'border-bottom': '1px solid #E5E5E5'});
    var idName = $that.context.id;
    if (!idName) {
        return;
    }
    var result = validateform(idName);
    if (result.status == 0) {
        $that.parent().css({'border-bottom': '1px solid green'});
    } else {
        $that.parent().css({'border-bottom': '1px solid red'});
    }
}



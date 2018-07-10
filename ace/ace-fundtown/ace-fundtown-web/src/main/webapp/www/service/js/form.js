$(function () {
    $('.push_data').on('click', submitForm);
    $('.content').on('blur', 'input', validateInput);
    $('.content').on('focus', 'input', clearStyle);
})


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
        json: JSON.stringify(data),
    }
    $.post(url, datas, function () {

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
    console.log(result);
    if (result.status == 0) {
        $that.parent().css({'border-bottom': '1px solid green'});
    } else {
        $that.parent().css({'border-bottom': '1px solid red'});
    }
}



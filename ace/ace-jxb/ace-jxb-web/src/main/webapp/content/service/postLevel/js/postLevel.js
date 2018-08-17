var focusValue = '';
var adding = false;

window.onload = function () {
    initWeb();
    $('.level_list').on('click', '.delectBtn', delectTr);
    $('.level_list').on('click', '.cancelBtn', cancelTr);
    $('.level_list').on('click', '.insertBtn', insertLevel);
    $('.level_list').on('focus', 'input', focusCheck);
    $('.level_list').on('blur', 'input', blurCheck);
};


function initWeb() {
    var url = '/jxb/postLevel/findPostLevelList';
    var data = {
        star: 0,
        page: 100
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('temp_level_list').innerHTML;
            var html = juicer(navitem, {
                data: result.rows,
            });
            $(".level_list").html(html);
        }
    });
}


/*结束提交*/
function blurCheck() {
    var $that = $(this);
    if ($that.parent().parent().is('.modifyTr')) {
        return;
    }
    var blurValue = $that.val();
    if (blurValue != focusValue) {
        var data = getObject($that.parent().parent());
        if (!data) {
            return false;
        }
        var url = '/jxb/postLevel/updatePostLevel';
        var datas = {
            jsons: JSON.stringify(data)
        }
        $.post(url, datas, function (result) {

        });
    }
}


/*开头检查*/
function focusCheck() {
    var $that = $(this);
    if ($that.is('.modifyTr')) {
        return;
    }
    focusValue = $that.val();
}


function getObject($tr) {
    var object = {
        // postIndex:'',
        postName: '',
        turnover: '',
        counselorNum: '',
        ratio: ''
    };
    for (key in object) {
        var val = $tr.find('.' + key).val();
        if (!val) {
            $tr.find('.' + key).focus();
            return false;
        }
        object[key] = val;
    }
    var id = $tr.data("id");
    object.id = id;
    return object;
}


function delectTr() {
    var $that = $(this);
    var id = $that.parent().parent().data("id");
    var url = '/jxb/postLevel/deletePostLevelByPostLevelId';
    var data = {
        jsons: JSON.stringify(
            {
                id: id
            }
        )

    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            $that.parent().parent().remove();
            return;
        }
        alert("删除失败。")
    })
}

function cancelTr() {
    $(this).parent().parent().remove();
}


function insertLevel() {
    var $that = $(this);
    var data = getObject($that.parent().parent());
    if (!data) {
        return false;
    }
    var url = '/jxb/postLevel/insertPostLevel';
    var datas = {
        jsons: JSON.stringify(data)
    }
    $.post(url, datas, function (result) {
        if (result.status == 0) {
            window.location.reload();
        }
    });
}


function createLevel() {
    if (adding) {
        alert("正在创建中。")
        return;
    }
    adding = true
    $('.level_list input').attr("disabled", "disabled");
    // var size=$('.level_list tr').length;
    $('.level_list').append("<tr class='modifyTr'>\n" +
        // "                                                                                <td><input type=\"text\" class=\"postIndex\" value=\""+size+"\"></td>\n" +
        "                                                                                <td><input type=\"text\" class=\"postName\"></td>\n" +
        "                                                                                <td><input type=\"text\" class=\"turnover\"></td>\n" +
        "                                                                                <td><input type=\"text\" class=\"counselorNum\"></td>\n" +
        "                                                                                <td><input type=\"text\" class=\"ratio\"></td>\n" +
        "                                                                                <td><span class=\"insertBtn primary-link\" width=\"15%\">确定</span></td>\n" +
        "                                                                                <td><span class=\"cancelBtn primary-link\" width=\"15%\">删除</span></td>\n" +
        "                                                                            </tr>")
}
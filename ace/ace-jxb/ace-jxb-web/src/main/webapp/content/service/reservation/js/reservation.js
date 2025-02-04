window.onload = function () {
    initWeb();
    $('.submit_btn').click(submitForm);
    getMyConsultInfo();
};


function initWeb() {
    var url = portalPath + '/dict/findListByCategoryId.do?categoryId=152';
    $.getJSON(url, function (result) {
        var navitem = document.getElementById('temp_field').innerHTML;
        var html = juicer(navitem, {
            data: result,
        });
        $("#field").html(html);
    })
    var url = portalPath + '/dict/findListByCategoryId.do?categoryId=149';
    $.getJSON(url, function (result) {
        var navitem = document.getElementById('temp_objects').innerHTML;
        var html = juicer(navitem, {
            data: result,
        });
        $("#objects").html(html);
    })
}



function getMyConsultInfo() {
    var url = "/jxb/consult/getMyConsultInfo";
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            if (result.data) {
                fillForm(result.data);
            }
        }
    })
}

function fillForm(data) {
    for (key in data) {
        if (key == "productList") {
            var list = data[key];
            for (var i = 0; i < list.length; i++) {
                $('[name=type' + list[i].type + ']').val(list[i].price);
            }
        }
        $('[name=form_' + key + ']').val(data[key]);
    }
    if (data.status == 1) {
        $("[name='status']").bootstrapSwitch('state', true);
    } else if (data.status == 0) {
        $("[name='status']").bootstrapSwitch('state', false);
    } else {
        $("input[name='status']").bootstrapSwitch('state', false);
        $("input[name='status']").bootstrapSwitch('disabled', true);
        $('#resultState').show();
    }
    var tag = $('#field .md-checkbox');
    var tags = data["field"];
    filloption(tag, tags)
    tag = $('#objects .md-checkbox');
    tags = data["objects"];
    filloption(tag, tags)

}

function filloption(tag, tags) {
    for (var i = 0; i < tag.length; i++) {
        var text = tag.eq(i).find('label').text().trim();
        if (tags.indexOf(text) != -1) {
            tag.eq(i).find('input').attr('checked', true)
        }
    }
    ;
}


//
// function getUserinfo() {
//     var url = "/jxb/counselor/getMyinfo"
//     $.getJSON(url, function (result) {
//         if (result.status == 0) {
//             viewUserinfo(result.value);
//
//         }
//     })
// }


// function viewUserinfo(data) {
//     for (key in data) {
//         $('.user-' + key).text(data[key]);
//     }
//     ;
//     $('.user-imagePhotoUrl').prop('src', data['imagePhotoUrl']);
// }

function submitForm() {
    var formObject = {
        telephoneCon: 'money_null',
        wecharCon: 'money1_null',
        facetofaceCon: 'money2_null'
        // info: 'notNull1',
    }


    for (key in formObject) {
        var idName = formObject[key];
        var result = validateform(idName);
        if (result.status == 0) {
            formObject[key] = result.message;
        } else {
            $('#' + idName).next().text(result.message);
            return
        }
    }
    ;

    formObject.city = $('#city').val().trim();

    if (!(formObject.telephoneCon || formObject.wecharCon || formObject.facetofaceCon)) {
        $('#money_null').next().text("咨询方式必须填写一项。");
        return;
    }


    // if((formObject.city!='')!=(formObject.facetofaceCon)){
    //     $('#city').next().text("面对面咨询必须填写地址。");
    //     return;
    // }


    var arr = new Array();
    $('#field :checkbox:checked').each(function (i) {
        arr[i] = $(this).next().text().trim();
    });
    if (arr.length == 0) {
        alert("还没有选择您的个人擅长");
        return
    } else if (arr.length > 4) {
        alert("个人擅长最多只能选择四项");
        return
    }
    formObject.field = arr.join(',');

    var arr = new Array();
    $('#objects :checkbox:checked').each(function (i) {
        arr[i] = $(this).next().text().trim();
    });
    var objects = arr.join(",");
    if (objects.length = 0) {
        alert("还没有选择您的咨询对象");
        return
    }
    if ($("[name='status']").is(':checked')) {
        formObject.status = 1;
    } else {
        formObject.status = 0;
    }
    formObject.objects = objects;
    var url = '/jxb/consult/modifyConsult';
    $.post(url, formObject, function (result) {
        if (result.status == 0) {
            alert("信息更新成功");
            return;
        }
        alert("信息更新失败,请稍后再试！");

    })
}
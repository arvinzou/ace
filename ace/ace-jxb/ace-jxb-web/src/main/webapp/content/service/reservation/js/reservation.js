window.onload = function () {
    getUserinfo();
    getMyConsultInfo();
    $('.submit_btn').click(submitForm);
};


function getMyConsultInfo() {
    var url = "/jxb/consult/getMyConsultInfo";
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            if (result.data) {
                $('.modify_btn').text("修改预约");
                viewReservationInfo(result.data);
                fillForm(result.data);
            } else {
                $('.modify_btn').text("创建预约");
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


function viewReservationInfo(data) {
    var navitem = document.getElementById('temp_reservtionInfo').innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#reservtionInfo").html(html);
}


function getUserinfo() {
    var url = "/jxb/counselor/getMyinfo"
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            viewUserinfo(result.value);

        }
    })
}


function viewUserinfo(data) {
    for (key in data) {
        $('.user-' + key).text(data[key]);
    }
    ;
    $('.user-imagePhotoUrl').prop('src', data['imagePhotoUrl']);
}

function submitForm() {
    var formObject = {
        telephoneCon: 'money_null',
        wecharCon: 'money1_null',
        facetofaceCon: 'money2_null',
        info: 'notNull1',
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
    var field = arr.join(",");
    if (field.length == 0) {
        alert("还没有选择您的个人擅长");
        return
    }
    formObject.field = field;

    var arr = new Array();
    $('#objects :checkbox:checked').each(function (i) {
        arr[i] = $(this).next().text().trim();
    });
    var objects = arr.join(",");
    if (objects.length = 0) {
        alert("还没有选择您的咨询对象");
        return
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
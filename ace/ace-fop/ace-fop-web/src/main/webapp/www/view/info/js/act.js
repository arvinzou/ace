var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        initPage();
        initEvents();
    });
}
/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

function initPage() {
   loadPersonData();
}

function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
		errorPlacement: function(error, element) {
             $(element).closest( "form" ).find( ".error-" + element.attr( "name" )).append( error );
        },
        rules: {
            realName: {
                required: true,
                maxlength: 20
            },
            email:{
                email:true
            }
        },
        messages: {
            realName: {
                required: "请输入姓名",
                maxlength: "姓名字符长度不能超过20"
            }
        }
    });
    /*监听表单提交*/
    $('#fm-edit').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date()

            });
            console.log(params);
            save(params);
            return false;
        }
    });
}
/*保存表单**/
function save(params) {
    $.ajax({
        url: contextPath + "/fopPerson/www/updateFopPerson",
        type: "post",
        async: false,
        beforeSend: function (XMLHttpRequest) {
            startLoad();
        },
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            //alert(result.errorMessage);
            $.alert(result.errorMessage, "系统提示");
        },
        error: function () {
			stopLoad();
            alert("对不起出错了！");
        }
    });
}

function loadPersonData() {
    $.ajax({
        type: "post",
        url: contextPath+"/fopPerson/www/selectFopPersonByCurCorpId",
        data: {
            id:'01824693943746a39c59f264d1f93b8c'
        },
        beforeSend: function (XMLHttpRequest) {
            startLoad();
        },
        success: function (rst, textStatus) {
            stopLoad();
            var data = {};
            data['o'] = rst.value;
            if( rst.value.birthDate){
              data['o'].birthDate = rst.value.birthDate.substr(0,10);
            }
            if( rst.value.recruitmentDate){
              data['o'].recruitmentDate = rst.value.recruitmentDate.substring(0,10);
            }


            data['dict']=staticDictObject;
            console.log(data);
            render('#fm-edit', data, 'tpl-fm');
            initEvents();
        },
        error: function () {
            stopLoad();
            alert("加载错误！");
        }
    });
}
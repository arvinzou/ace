function loadView(id) {
    $.ajax({
        type: "post",
        url: contextPath + '/fopAssociation/selectFopAssociationByPrimaryKey',
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            //动态渲染
            var tpl = document.getElementById('tpl-view-page').innerHTML;
            var renderHtml = juicer(tpl, rst.value);
            $('.main_box').html(renderHtml);
            initGrid();

            $.each(rst.value, function (key, value) {
                //status
                if (key == "status") {
                    if ("1" == value) {
                        value = "非会员";
                    }
                    if ("2" == value) {
                        value = "会员";
                    }
                }
                if (key == 'category') {
                    value = rsd(value, '83');
                }
                if (key == 'status') {
                    value == "1" ? "正常" : "关闭";
                }
                if (key.indexOf('Date') != -1 || key.indexOf('time') != -1 || key.indexOf('Time') != -1 || key.indexOf('birthday') != -1) {
                    value = Common.DateFormatter(value);
                }
                $(".form_info").find('span[name=' + key + ']').html(value);
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

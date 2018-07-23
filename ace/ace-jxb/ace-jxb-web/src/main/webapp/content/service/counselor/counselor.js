function App() {
    console.log("=============================App Start==============================");

    //init page
    loadCounselorList();

}

//加载老师列表
function loadCounselorList() {
    $.ajax({
        type: "post",
        url: contextPath + '/counselor/findCounselorList',
        data: {},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            console.log("++++++++++++++++++++++++++++");
            console.log(rst);
            console.log("++++++++++++++++++++++++++++");

            //html render
            var tpl = document.getElementById('tpl-counselor-box').innerHTML;
            var renderHtml = juicer(tpl, rst);
            $('.counselor-list').html(renderHtml);

        },
        error: function () {
            alert("加载错误！");
        }
    });
}

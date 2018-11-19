window.onload=function () {
    initData();
}

function initData() {
    var url=contextPath+"/www/counselor/findWithdrawList";
    var data={};
    $.getJSON(url,data,function (rst) {
        if(rst.status == 0){
            console.log(rst);
            var mylist = document.getElementById('tmp-list').innerHTML;
            var html = juicer(mylist, {
                data: rst.data.rows
            });
            $("#list").append(html);
        }
    })
}
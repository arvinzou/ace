window.onload=function () {
    new Mdate("dateSelectorOne");
    var date=new Date();
    $('.month').on('change','input',getList);
    $('#dateSelectorOne').val(date.getFullYear()+"年"+(date.getMonth()+1)+'月').attr('data-year',date.getFullYear()).attr("data-month",date.getMonth()+1);
    $('.month').on('change','input',getList);
    getOrderList();
}

function getList(){
    console.log(this.val());
}

function getOrderList(date) {
    var date='2018年8月';
    var year=date.substring(0,4);
    var momth=date.substring(date.indexOf('年')+1,date.indexOf('月'));
    console.log(year+momth);
    var url=contextPath+"/www/order/findList";
    var data={findType:'2',
    year:year,
    momth:momth};
    $.getJSON(url,data,function (rst) {
        console.log(rst);
    })
}
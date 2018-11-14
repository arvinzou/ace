window.onload=function () {
    new Mdate("dateSelectorOne");
    var date=new Date();
    $('#dateSelectorOne').val(date.getFullYear()+"年"+(date.getMonth()+1)+'月').attr('data-year',date.getFullYear()).attr("data-month",date.getMonth()+1);
    $('.month').on('change','input',getList);
}

function getList(){
    console.log(this.val());
}
var identityType = "";
function changeIdentity(type, obj){
    identityType = type;
    if(type == 'student'){
        $('#teacher').attr('src',contextPath+'/www/sign/img/teacher.png');
        $('#student').attr('src',contextPath+'/www/sign/img/student-checked.png');
        $("#teacher").siblings().removeClass('active').addClass('unactive');
        $("#student").siblings().removeClass('unactive').addClass('active');
        window.location.href = contextPath+'/www/sign/regs.jsp';
    }else{
        $('#student').attr('src',contextPath+'/www/sign/img/student.png');
        $('#teacher').attr('src',contextPath+'/www/sign/img/teacher-checked.png');
        $("#student").siblings().removeClass('active').addClass('unactive');
        $("#teacher").siblings().removeClass('unactive').addClass('active');
        window.location.href = contextPath + '/www/sign/regt.jsp';
    }
}


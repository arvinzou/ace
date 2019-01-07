var identityType = "";
function changeIdentity(type, obj){
    identityType = type;
    if(type == 'student'){
        $('#teacher').attr('src',contextPath+'/www/sign/img/teacher.png');
        $('#student').attr('src',contextPath+'/www/sign/img/student-checked.png');
        $("#student").siblings().addClass('active');
        window.location.href = contextPath+'/www/sign/regs.jsp';
    }else{
        $('#student').attr('src',contextPath+'/www/sign/img/student.png');
        $('#teacher').attr('src',contextPath+'/www/sign/img/teacher-checked.png');
        $("#teacher").siblings().addClass('active');
        window.location.href = contextPath + '/www/sign/regt.jsp';
    }
}


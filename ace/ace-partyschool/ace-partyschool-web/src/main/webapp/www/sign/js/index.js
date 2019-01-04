var identityType = "";
function changeIdentity(type, obj){
    identityType = type;
    if(type == 'student'){
        $('#teacher').attr('src',contextPath+'/www/sign/img/teacher.png');
        $('#student').attr('src',contextPath+'/www/sign/img/student-checked.png');
    }else{
        $('#student').attr('src',contextPath+'/www/sign/img/student.png');
        $('#teacher').attr('src',contextPath+'/www/sign/img/teacher-checked.png');
    }
    $("#unactive").hide();
    $("#active").show();
}

function nextStep(){
    if(identityType == 'student'){
        window.location.href = contextPath+'/www/sign/regs.jsp';
    }else {
        window.location.href = contextPath + '/www/sign/regt.jsp';
    }
}
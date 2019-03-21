var courseId = null;
var sourceId = null;
function App() {
    loader({
        path: contextPath,
        url: '/www/view/common/js/star-rating.js',
        type: 'js',
        callback: function () {
        }
    });
}
window.onload = function(){
    console.log(window.location.href);
    var url =   window.location.href.substring(window.location.href.indexOf("?")+1, window.location.href.length+1);
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "courseId"){
                courseId = value;
            }
            if(name == "sourceId"){
                sourceId = value;
            }
        }
    }
    console.log("courseId:"+courseId+"\n"+"sourceId:"+sourceId+"\n");
    initCourseInfo(courseId);


    var $inp = $('#rating-input');
    $inp.rating({
        min: 0,
        max: 5,
        step: 1,
        size: 'xs',
        theme: 'krajee-fa',
        showClear: false,
        starCaptions: {1: '极差', 2: '差', 3: '一般', 4: '良好', 5: '推荐'},
        captionElement: "#kv-caption",
        filledStar: '<i class="fas fa-star"></i>',
        emptyStar: '<i class="fas fa-star"></i>',
    });
}
function initCourseInfo(courseId){
    // startLoad();
    $.ajax({
        url: contextPath+ "/www/course/findCourseDetail",
        type:"post",
        async:false,
        data:{
            courseId: courseId
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('banner', result.data, 'bannerTemp');
                viewHtml('teacherName', result.data, 'teacherTemp');
                var type = result.data.type;
                if(type == '1'){
                    //单节课程
                    viewHtml('sourceTitle', result.data.courseSource, 'sourceNameTemp');
                    viewHtml('courseAudio', result.data.courseSource, 'audioTemp');
                }else if(type == '2'){
                    //系列课程
                    findSourceInfo(sourceId);
                }
                initPlay();
                initCommentsList();
                // stopLoad();
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                // stopLoad();
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
            // stopLoad();
        }
    });
}

function viewHtml(IDom, data, tempId) {
    var navitem = document.getElementById(tempId).innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function showCatalog(courseId, partId){
    if(partId == '0'){   //0代表是单节课程，没有目录
        alert("改课程为单节课程，没有目录信息！");
    }else{
        window.location.href = contextPath + '/www/view/catalogues/index.jsp?id='+courseId;
    }
}

function showScripts(sourceId){
    window.location.href = contextPath + '/www/view/courseDetail/index.jsp?id='+sourceId;
}

function findSourceInfo(sourceId){
    $.ajax({
        url: contextPath+ "/www/course/findCourseSource",
        type:"post",
        async:false,
        data:{
            srcId: sourceId
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('sourceTitle', result.data, 'sourceNameTemp');
                viewHtml('courseAudio', result.data, 'audioTemp');

            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function commitComments(){
    var level = $("#rating-input").val();
    var content = $("textarea[name = 'content']").val();
    if(content == '' || content == undefined){
        alert("留下点评价呗~");
        return;
    }
    $.ajax({
        url: contextPath+ "/www/course/cmt/add",
        type:"post",
        async:false,
        data:{
            courseId: courseId,
            content: content,
            grade: level
        },
        success:function(result){
            if(result.status == 0) {
                alert("感谢您的评价，下次我们会做得更好！");
                initCommentsList();
                $('#myModal').modal('hide');
            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function initCommentsList(){
    $.ajax({
        url: contextPath+ "/www/course/cmt/findCmtList",
        type:"post",
        async:false,
        data:{
            courseId: courseId,
            start: 0,
            limit: 9999
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('commentList', result.data.rows, 'commentListTemp');
            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}



/**
 * 增加点播量
 * @param courseId
 */
function addPlayNums(courseId){
    $.ajax({
        url: contextPath+ "/www/course/addDemandNum",
        type:"post",
        async:false,
        data:{
            courseId: courseId
        },
        success:function(result){
            if(result.status == 0) {

            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function initPlay() {
    var audioPlayer = document.querySelector('.music-play');
    var audioButton = audioPlayer.querySelector('.buttons');
    var audioControl = audioPlayer.querySelector('.controls');
    var playPauseBtn = audioButton.querySelector('.playPause');
    var waitBtn = audioButton.querySelector('.fa-circle-notch');
    var totalTime = audioControl.querySelector('.totalTime');
    var slider = audioControl.querySelector('.slider');
    var playTime = audioControl.querySelector('.playTime');
    var progress = audioControl.querySelector('.progress');
    var media = audioPlayer.querySelector('audio');
    playPauseBtn.addEventListener('click', togglePlay);
    slider.addEventListener('click', changeProgress);
    slider.addEventListener('touchmove', touchmove, false);
    media.addEventListener('canplay', canPlay);
    media.addEventListener('waiting', waiting);
    media.addEventListener('loadedmetadata', loadedmetadata);
    media.addEventListener('progress', progress);
    media.addEventListener('timeupdate', updateProgress);
    media.addEventListener('ended', ended);

    function touchmove(e) {
        var _x = e.touches[0].pageX;
        var progressW=this.clientWidth;
        var p=_x-this.getBoundingClientRect().left;
        if(p<0){
            p=0;
        }
        if(p>progressW){
            p=progressW;
        }
        activeProgress(p,progressW);
    }

    function activeProgress(x,y){
        var scale = x/y;
        media.currentTime = parseInt(media.duration * scale);
        var percent = scale * 100;
        progress.style.width = percent + '%';
    }

    function changeProgress(e) {
        activeProgress(e.offsetX,slider.offsetWidth)
    }


    function togglePlay() {
        if(media.paused) {
            playIcon();
            media.play();
        } else {
            pausedIcon();
            media.pause();
        }

    }
    function playIcon(){
        playPauseBtn.classList.remove("fa-play");
        playPauseBtn.classList.add("fa-pause");
    }
    function pausedIcon(){
        playPauseBtn.classList.add("fa-play");
        playPauseBtn.classList.remove("fa-pause");
    }

    function togglePause() {
        media.pause();
    }

    function canPlay() {
        playPauseBtn.style.display = "block";
        waitBtn.style.display = "none";
    }

    function loadedmetadata(e) {
        totalTime.innerText = formatTime(media.duration);
    }



    function waiting() {
        playPauseBtn.style.display = "none";
        waitBtn.style.display = "block";
    }

    function updateProgress(e) {
        var current = media.currentTime;
        var percent = current / media.duration * 100;
        progress.style.width = percent + '%';
        playTime.textContent = formatTime(current);
    }

    function formatTime(time) {
        var min = Math.floor(time / 60);
        var sec = Math.floor(time % 60);
        return   (min < 10 ? '0' + min : min) + ':' + (sec < 10 ? '0' + sec : sec);
    }
    function ended(){
        media.currentTime=0;
        progress.style.width = '0%';
        pausedIcon();
    }
}
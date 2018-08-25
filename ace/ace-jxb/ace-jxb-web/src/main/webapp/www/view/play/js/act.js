var courseId = null;
var sourceId = null;
function App() {
    loader({
        path: contextPath,
        url: '/www/view/common/js/star-rating.js',
        type: 'js',
        callback: function () {
            jQuery(document).ready(function() {
                var $inp = $('#rating-input');
                $inp.rating({
                    min: 0,
                    max: 5,
                    step: 1,
                    size: 'xs',
                    showClear: false
                });
            });
        }
    });

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
}

function initCourseInfo(courseId){
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
                initCommentsList();
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
                palyer();

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
                window.location.reload();
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


function palyer() {
    var audioPlayer = document.querySelector('.green-audio-player');
    var playPause = audioPlayer.querySelector('#playPause');
    var playpauseBtn = audioPlayer.querySelector('.play-pause-btn');
    var loading = audioPlayer.querySelector('.loading');
    var progress = audioPlayer.querySelector('.progress');
    var sliders = audioPlayer.querySelectorAll('.slider');
    var player = audioPlayer.querySelector('audio');
    var currentTime = audioPlayer.querySelector('.current-time');
    var totalTime = audioPlayer.querySelector('.total-time');
    var speaker = audioPlayer.querySelector('#speaker');

    var draggableClasses = ['pin'];
    var currentlyDragged = null;

    window.addEventListener('mousedown', function (event) {

        if (!isDraggable(event.target)) return false;

        currentlyDragged = event.target;
        var handleMethod = currentlyDragged.dataset.method;

        this.addEventListener('mousemove', window[handleMethod], false);

        window.addEventListener('mouseup', function () {
            currentlyDragged = false;
            window.removeEventListener('mousemove', window[handleMethod], false);
        }, false);
    });

    playpauseBtn.addEventListener('click', togglePlay);
    player.addEventListener('timeupdate', updateProgress);
    player.addEventListener('volumechange', updateVolume);
    player.addEventListener('loadedmetadata', function () {
        totalTime.textContent = formatTime(player.duration);
    });
    player.addEventListener('canplay', makePlay);
    player.addEventListener('ended', function () {
        playPause.attributes.d.value = "M18 12L0 24V0";
        player.currentTime = 0;
    });


    sliders.forEach(function (slider) {
        var pin = slider.querySelector('.pin');
        slider.addEventListener('click', window[pin.dataset.method]);
    });


    function isDraggable(el) {
        var canDrag = false;
        var classes = Array.from(el.classList);
        draggableClasses.forEach(function (draggable) {
            if (classes.indexOf(draggable) !== -1) canDrag = true;
        });
        return canDrag;
    }

    function inRange(event) {
        var rangeBox = getRangeBox(event);
        var rect = rangeBox.getBoundingClientRect();
        var direction = rangeBox.dataset.direction;
        if (direction == 'horizontal') {
            var min = rangeBox.offsetLeft;
            var max = min + rangeBox.offsetWidth;
            if (event.clientX < min || event.clientX > max) return false;
        } else {
            var min = rect.top;
            var max = min + rangeBox.offsetHeight;
            if (event.clientY < min || event.clientY > max) return false;
        }
        return true;
    }

    function updateProgress() {
        var current = player.currentTime;
        var percent = current / player.duration * 100;
        progress.style.width = percent + '%';

        currentTime.textContent = formatTime(current);
    }

    function updateVolume() {
        volumeProgress.style.height = player.volume * 100 + '%';
        if (player.volume >= 0.5) {
            speaker.attributes.d.value = 'M14.667 0v2.747c3.853 1.146 6.666 4.72 6.666 8.946 0 4.227-2.813 7.787-6.666 8.934v2.76C20 22.173 24 17.4 24 11.693 24 5.987 20 1.213 14.667 0zM18 11.693c0-2.36-1.333-4.386-3.333-5.373v10.707c2-.947 3.333-2.987 3.333-5.334zm-18-4v8h5.333L12 22.36V1.027L5.333 7.693H0z';
        } else if (player.volume < 0.5 && player.volume > 0.05) {
            speaker.attributes.d.value = 'M0 7.667v8h5.333L12 22.333V1L5.333 7.667M17.333 11.373C17.333 9.013 16 6.987 14 6v10.707c2-.947 3.333-2.987 3.333-5.334z';
        } else if (player.volume <= 0.05) {
            speaker.attributes.d.value = 'M0 7.667v8h5.333L12 22.333V1L5.333 7.667';
        }
    }

    function getRangeBox(event) {
        var rangeBox = event.target;
        var el = currentlyDragged;
        if (event.type == 'click' && isDraggable(event.target)) {
            rangeBox = event.target.parentElement.parentElement;
        }
        if (event.type == 'mousemove') {
            rangeBox = el.parentElement.parentElement;
        }
        return rangeBox;
    }

    function getCoefficient(event) {
        var slider = getRangeBox(event);
        var rect = slider.getBoundingClientRect();
        var K = 0;
        if (slider.dataset.direction == 'horizontal') {

            var offsetX = event.clientX - slider.offsetLeft;
            var width = slider.clientWidth;
            K = offsetX / width;
        } else if (slider.dataset.direction == 'vertical') {

            var height = slider.clientHeight;
            var offsetY = event.clientY - rect.top;
            K = 1 - offsetY / height;
        }
        return K;
    }

    function rewind(event) {
        if (inRange(event)) {
            player.currentTime = player.duration * getCoefficient(event);
        }
    }

    function changeVolume(event) {
        if (inRange(event)) {
            player.volume = getCoefficient(event);
        }
    }

    function formatTime(time) {
        var min = Math.floor(time / 60);
        var sec = Math.floor(time % 60);
        return min + ':' + (sec < 10 ? '0' + sec : sec);
    }

    function togglePlay() {
        if (player.paused) {
            $('#playPause').prop('src', 'img/stop.png');
            player.play();
        } else {
            $('#playPause').prop('src', 'img/play.png');
            player.pause();
        }
    }

    function makePlay() {
        playpauseBtn.style.display = 'block';
        loading.style.display = 'none';
    }
}

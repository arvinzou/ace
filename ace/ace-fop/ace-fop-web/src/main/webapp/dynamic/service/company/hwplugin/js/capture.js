/**
 * Created by ArvinZou on 2018/11/7.
 */
function getcurcam() {
    return document.getElementById("CamList").value;
}
//初始化
function initCtrl() {
    deloptionCam();

    var count = hwcam.GetCamCount();
    for (i = 0; i < count; i++) {
        var s = hwcam.GetCamName(i);
        addoptionCam(s);
    }
    document.getElementById("CamList").value = 0;

    initclr();
    initAudio();
    initcutmode();
    initclrspace();

    //直接开始预览
    startpreview();
}

function initAudio() {
    var count = hwcam.GetAudioCount();
    for (var i = 0; i < count; i++) {
        var s = hwcam.GetAudioName(i);
        addoptionaudio(s);
    }
}

function changeaudio() {
    var obj = document.getElementById("audioList").options;
    hwcam.SetAudio(obj.selectedIndex);
}

function addoptionaudio(s) {
    var obj = document.getElementById("audioList").options;
    var opt = new Option(s, obj.length);
    obj.options.add(opt);
}

function initcutmode() {
    addoptioncutmode("不裁切");
    addoptioncutmode("手工裁切");
    addoptioncutmode("自动裁切");

    document.getElementById("cutMode").value = 0;
}

function changecutmode() {
    var idx = getcurcam();
    var obj = document.getElementById("cutMode").options;

    if (obj.selectedIndex == 0) {
        hwcam.SetMouseMode(idx, 0);
        hwcam.SetAutoCrop(idx, false);
    }
    else if (obj.selectedIndex == 1) {
        hwcam.SetMouseMode(idx, 1);
        hwcam.SetAutoCrop(idx, false);
    }
    else if (obj.selectedIndex == 2) {
        hwcam.SetMouseMode(idx, 0);
        hwcam.SetAutoCrop(idx, true);
    }
}

function addoptioncutmode(s) {
    var obj = document.getElementById("cutMode").options;
    var opt = new Option(s, obj.length);
    obj.options.add(opt);
}

function addoptionCam(s) {
    var obj = document.getElementById("CamList").options;
    var opt = new Option(s, obj.length);
    obj.options.add(opt);
}

function deloptionCam() {
    var obj = document.getElementById("CamList").options;
    while (obj.length > 0) {
        obj.options.remove(obj.length - 1);
    }
}


//开始预览
function startpreview() {
    var idx = getcurcam();
    hwcam.StartPreview(idx);
    enumres();
    initScanSize();
}
//停止预览
function stoppreview() {
    var idx = getcurcam();
    hwcam.StopPreview(idx);
}

function getdatestr() {

    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var hour = date.getHours();
    var min = date.getMinutes();
    var sec = date.getSeconds();
    if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
    }
    if (min >= 0 && min <= 9) {
        min = "0" + min;
    }
    if (sec >= 0 && sec <= 9) {
        sec = "0" + sec;
    }
    var currentdate = date.getFullYear() + month + strDate + hour + min + sec;


    return currentdate.toString();
}

//文件默认存储路径
var defaultFilePath = "d:\\";

//拍照
function capture() {
    var timestamp = getdatestr();
    var idx = getcurcam();
    var fileName = "hw" + timestamp + ".jpg";
    var filePath = defaultFilePath + fileName;
    hwcam.CaptureImage(idx, filePath);
    hwthumb.AddImage(filePath);
}
//获取画面中的base64字符串
function capturebase64() {
    var idx = getcurcam();
    var imageStr = hwcam.CaptureBase64(idx);
    document.getElementById("txt").innerText = imageStr;
    //do post
    var params = {
        base64Str: imageStr,
        category: getQueryString('category'),
        companyId: getQueryString('did')
    };
    $.ajax({
        type: 'POST',
        url: contextPath + "/www/company/upload",
        data: params,
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            console.log(JSON.stringify(rst));
            if (rst.status == "0") {
                alert(rst.info);
            } else {
                alert(rst.info);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
            alert("网络异常！");
        }
    });
}

function changedev() {
    var idx = getcurcam();

    //hwcam.SetVisible(idx, false);
    if (idx == 0) {
        hwcam.StopPreview(1);
    }
    else if (idx == 1) {
        hwcam.StopPreview(0);
    }
    hwcam.StartPreview(idx);

    enumres();
}

function changeres() {
    var idx = getcurcam();
    var obj = document.getElementById("resList").options;
    var x = obj.selectedIndex;
    hwcam.SetCamResIndex(idx, x);
}

function enumres() {
    var idx = getcurcam();
    deloptionRes();
    var iResIndex = 0;
    if (iResIndex != -1) {
        var count = hwcam.GetResCount(idx);
        for (i = 0; i < count; i++) {
            var w = hwcam.GetResWidth(idx, i);
            var h = hwcam.GetResHeight(idx, i);
            var str = w.toString() + "x" + h.toString();
            addoptionRes(str);
        }
        document.getElementById("resList").value = iResIndex;
    }
}

function deloptionRes() {
    var obj = document.getElementById("resList").options;
    while (obj.length > 0) {
        obj.options.remove(obj.length - 1);
    }
}
function addoptionRes(s) {
    var obj = document.getElementById("resList").options;
    var opt = new Option(s, obj.length);
    obj.options.add(opt);
}

function hwmark() {
    alert(hwcam.GetHWFlag());
}

function showwatermark(obj) {
    hwcam.SetWatermark(obj.checked, 100, 200, "汉王e拍仪, 这是水印");
}

function rotleft() {
    var idx = getcurcam();
    hwcam.RotateLeft(idx);
}

function rotright() {
    var idx = getcurcam();
    hwcam.RotateRight(idx);
}

function setexposure(obj) {
    var idx = getcurcam();
    hwcam.SetExposure(idx, obj.checked, -3);
}

function setcapture(obj) {
    var idx = getcurcam();
    if (obj.checked) {
        hwcam.SetCaptureMode(idx, 1);
    }
    else {
        hwcam.SetCaptureMode(idx, 0);
    }
}

function videoprop() {
    var idx = getcurcam();
    hwcam.ShowVideoPropDlg(idx);
}
//图片上传
function httpupload() {
    console.log("httpupload++++++++++++++++++++++++++");

    var uploadURL = "http://localhost/fop/www/company/upload?category=" + '1';
    //获取缩列图中图片数目
    var count = hwthumb.GetImageCount();
    console.log("count=" + count);
    //是否选中
    var isChecked;
    //文件路径
    var filePath;
    var rs;
    for (var i = 0; i < count; i++) {
        //获取
        isChecked = hwthumb.IsChecked(i);
        console.log("isChecked=" + isChecked);
        if (isChecked) {
            filePath = hwthumb.GetImagePath(i);
            console.log("filePath=" + filePath);
            rs = hwcam.HttpUpload(filePath, uploadURL);
            console.log("upload-rs : " + rs);
            hwcam.DeleteImage(filePath);
        }
    }
}

function recogbarcode() {
    var idx = getcurcam();
    alert(hwcam.ReadBarcode(idx));
}

function initclr() {
    addoptionclr("彩色");
    addoptionclr("灰度");
    addoptionclr("黑白");
    addoptionclr("彩色去底色");
    document.getElementById("clrList").value = 0;
}

function addoptionclr(s) {
    var obj = document.getElementById("clrList").options;
    var opt = new Option(s, obj.length);
    obj.options.add(opt);
}

function changeclr() {
    var idx = getcurcam();
    var obj = document.getElementById("clrList").options;
    if (obj.selectedIndex == 3) {
        hwcam.SetDelBack(idx, true);
        hwcam.SetColorMode(idx, 0);
    }
    else {
        hwcam.SetDelBack(idx, false);
        hwcam.SetColorMode(idx, obj.selectedIndex);
    }


}


function initclrspace() {
    addoptionclrspace("YUY2");
    addoptionclrspace("MJPG");

    document.getElementById("clrSpace").value = 0;
}

function changeclrspace() {
    var idx = getcurcam();
    var obj = document.getElementById("clrSpace").options;

    hwcam.SetClrSpace(idx, obj.selectedIndex);
}

function addoptionclrspace(s) {
    var obj = document.getElementById("clrSpace").options;
    var opt = new Option(s, obj.length);
    obj.options.add(opt);
}


function createpdf() {
    alert("createpdf");
    var imagespath = "";
    var count = hwthumb.GetImageCount();
    for (var i = 0; i < count; i++) {
        if (hwthumb.IsChecked(i))      //图片是选中(勾选)
        {
            imagespath += hwthumb.GetImagePath(i);
            imagespath += "@";                      //多个路径需要以"@"间隔
        }
    }

    if (imagespath.length > 0) {
        hwcam.CreatePDF("d:\\hw123.pdf", imagespath);
    } else {
        alert("请选择图片!");
    }
}

function convert2base64() {
    alert("将文件转换为base64, 请查看此处代码确认文件存在");
    var str = hwcam.Convert2Base64("d:\\hw202908123123.jpg");
    console.log("str=" + str);
    document.getElementById("txt").innerText = str;
}

function startrecord() {
    var idx = getcurcam();
    var obj = document.getElementById("rec");
    if (obj.value == "开始录像") {
        alert("正式开始录像");
        var myDate = new Date();
        var filepath = "d:\\" + myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + "_" + myDate.getHours() +
            myDate.getMinutes() + myDate.getSeconds() + ".wmv";
        //请注意第三个参数表示是否录音, 0--不录音, 1--录音"
        hwcam.StartRecord(idx, filepath, 1);
        obj.value = "停止录像";
    }
    else if (obj.value == "停止录像") {
        hwcam.StopRecord(idx);
        obj.value = "开始录像";
    }
}


function getcamindex() {
    alert("主头索引为:" + hwcam.GetCamIndex("0c45", "6366"));
    alert("副头索引为:" + hwcam.GetCamIndex("1b17", "0209"));
}

function initScanSize() {
    var count = hwcam.GetScanSizeCount();
    for (var i = 0; i < count; i++) {
        var s = hwcam.GetScanSizeName(i);
        addoptionscansize(s);
    }
}

function changescansize() {
    var obj = document.getElementById("scanSize").options;
    hwcam.SetScanSize(obj.selectedIndex);
}

function addoptionscansize(s) {
    var obj = document.getElementById("scanSize").options;
    var opt = new Option(s, obj.length);
    obj.options.add(opt);
}

function deletescansize() {
    var obj = document.getElementById("scanSize").options;
    hwcam.DeleteScanSize(obj.selectedIndex);
    obj.options.remove(obj.selectedIndex);
}

function zoomin() {
    var idx = getcurcam();
    hwcam.ZoomIn(idx);
}

function zoomout() {
    var idx = getcurcam();
    hwcam.ZoomOut(idx);
}

function setupscansize(obj) {
    var idx = getcurcam();
    if (obj.checked) {
        hwcam.SetMouseMode(idx, 2);
    }
    else {
        hwcam.SetMouseMode(idx, 0);
    }
}


function timerstart() {
    var obj = document.getElementById("tmcap");
    if (obj.value == "开始定时拍照") {
        hwcam.StartTimerCap(3);               //开始定时拍照， 时间间隔为3秒
        obj.value = "停止定时拍照";
    }
    else if (obj.value == "停止定时拍照") {
        hwcam.StopTimerCap();               //开始定时拍照， 时间间隔为3秒
        obj.value = "开始定时拍照";
    }

}

function autocapstart() {
    var obj = document.getElementById("autocap");
    if (obj.value == "开始自动拍照") {
        hwcam.StartAutoCap();
        obj.value = "停止自动拍照";
    }
    else if (obj.value == "停止自动拍照") {
        hwcam.StopAutoCap();
        obj.value = "开始自动拍照";
    }
}

function startpip() {
    var obj = document.getElementById("pip");
    if (obj.value == "开启画中画") {
        hwcam.StartPIP(1, 0);
        obj.value = "关闭画中画";
    }
    else if (obj.value == "关闭画中画") {
        hwcam.StopPIP(1);
        obj.value = "开启画中画";
    }
}

function setjpgquality() {
    var idx = getcurcam();
    //设置jpg文件质量, 第一个参数为摄像头索引, 第二个参数为jpg质量, 取值范围1-100, 越大文件越大, 越小文件越小
    hwcam.SetJpgQuality(idx, 60);
}

function setdpi() {
    var idx = getcurcam();
    hwcam.SetDPI(idx, 200);           //第一个参数为摄像头索引, 第二个参数为dpi值
}

function ftpupload() {
    alert("请参考此处代码填写正确的参数");
    //hwcam.FTPUploadImage("文件路径", "ftp服务器路径", "用户名", "密码", nPort);       //nPort:端口
}

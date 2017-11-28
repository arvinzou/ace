<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>人脸检测</title>
</head>
<jsp:include page="/dynamic/common/common.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/swiper/css/swiper.min.css">
<style>
.swiper-container1 {
      width: 90%;
      padding-top: 50px;
      padding-bottom: 50px;
    }
    .swiper-container2 {
      width: 90%;
      padding-top: 50px;
      padding-bottom: 50px;
    }
    .swiper-slide {
      background-position: center;
      background-size: cover;
      width: 100px;
      height: 100px;
    }
    .center{
        text-align:center
    }
    .photo{
        max-height:250px;
        min-height:250px;
    }
.btn-sm {
    border-width: 4px;
    font-size: 13px;
    padding: 4px 9px;
    line-height: 1.39;
}

.search-box {
    zoom: 1;
    position: relative;
    margin: 20px 0 24px;
}
.file-uploader-one {
    position: absolute;
    top: -9999px;
    left: -9999px;
    opacity: 0;
    width: 137px;
    height: 42px;
    cursor: pointer;
}
.upload-img {
    position: relative;
    width: 135px;
    height: 40px;
    line-height: 36px;
    transition: all .3s ease 0s;
    cursor: pointer;
    display: inline-block;
    text-align: center;
    color: #4c4c4c;
    border: 1px solid #cacaca;
    background-color: #fff;
    font-size: 14px;
    float: left;
}

.icon-upload {
    margin-right: 10px;
}
.icons.icon-upload {
    width: 20px;
    height: 24px;
    background-image: url(https://www.faceplusplus.com.cn/images/icons/icon-upload.png);
}
.icons {
    display: inline-block;
    background-repeat: no-repeat;
    vertical-align: middle;
}
.search-box button {
    transition: all .3s ease 0s;
    cursor: pointer;
    text-align: center;
    display: inline-block;
    color: #fff;
    border: 1px solid #379edf;
    background-color: #379edf;
    font-size: 14px;
    width: 66px;
    height: 40px;
    float: right;
}
.search-box input.search-input {
    border: 1px solid #59b1f5;
    width: 60%;
    height: 40px;
    line-height: 40px;
    text-indent: 1em;
    float: right;
}
.result-box {
    width: 100%;
    border: 1px solid #cacaca;
    padding: 20px;
    margin: 5px;
    text-align: left;
}
.btn-ck {
    transition: all .3s ease 0s;
    cursor: pointer;
    text-align: center;
    display: inline-block;
    color: #fff;
    border: 1px solid #379edf;
    background-color: #379edf;
    font-size: 14px;
    width: 200px;
    height: 40px;
    margin: 10px;
}
.padding{
    margin: 10px;
}
.img-box {
    width: 200px;
	height: 200px;
	float: left;
	margin: 5px;
	text-align:center;
}
.box {
    border: 0px solid #cacaca;
	padding:5px;
	margin: 5px;
}
 .background {
        background-color: #cacaca;
    }
.resp-json{
    resize: none;
    background: #fbfbfb;
    border: none;
    overflow-y: auto;
    overflow-x: hidden;
    color:blue;
    font-size: 14px;
    line-height: 16px;
}

</style>
<body>
<div class="page-content">
    <div class="row center padding">

        <h3>人脸检测</h3>
        <div id="message"></div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-6">
            <h5 class="header-title">检测对象</h5>
            <div class="box center">
                <div class="row photo" id="box1"></div>
                <div class="row">
                    <div class="swiper-container1">
                        <div class="swiper-wrapper" id="swiper1">

                        </div>
                        <!-- Add Pagination -->
                        <div class="swiper-pagination"></div>
                    </div>
                    <div class="search-box search-box-one">
                        <input type="file" accept="image/png,image/jpeg" id="file-uploader-one"
                               class="file-uploader-one">
                        <div id="file-uploader-btn-one" class="upload-img"><i
                                class="icons icon-upload"></i><span>本地上传</span></div>
                        <button class="search-btn search-btn-one" id="btn-query" authority="false">检测</button>
                        <input placeholder="图片URL" class="search-input search-input-one">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6">
            <h5 class="header-title">检测结果</h5>
            <div class="row hide" style="padding:10px" id="rst-view">

                <div class="labelItem"><span class="labelItemHeader">性别</span><br><span id="gender"></span></div>
                <div class="labelItem"><span class="labelItemHeader">年龄</span><br><span id="age"></span></div>
                <div class="labelItem"><span class="labelItemHeader">微笑程度</span><br><span id="smile"></span></div>
                <div class="labelItem"><span class="labelItemHeader">是否佩戴眼镜</span><br><span id="glass"></span></div>
                <div class="labelItem"><span class="labelItemHeader">情绪</span><br><span id="emotion"></span></div>
                <div class="labelItem"><span class="labelItemHeader">人种</span><br><span id="ethnicity"></span></div>
                <div class="labelItem"><span class="labelItemHeader">颜值</span><br><span id="beauty"></span></div>
                <div class="labelItem"><span class="labelItemHeader">健康</span><br><span id="health"></span></div>
                <div class="labelItem"><span class="labelItemHeader">色斑</span><br><span id="stain"></span></div>
                <div class="labelItem"><span class="labelItemHeader">青春痘</span><br><span id="acne"></span></div>
                <div class="labelItem"><span class="labelItemHeader">黑眼圈</span><br><span id="dark_circle"></span></div>

            </div>
            <h5 class="header-title">JSON数据</h5>

            <textarea style="width:100%;height:280px" readonly class="resp-json">

                </textarea>
        </div>
    </div>


</div>


<jsp:include page="/dynamic/common/footer-1.jsp"/>

<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script
        src="${pageContext.request.contextPath}/content/service/detect/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/detect/upload.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/person/face.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/swiper/js/swiper.min.js"></script>
<jsp:include page="/dynamic/common/footer-2.jsp"/>

<div id="dialog-message" class="hide">
    <div id="uploader">
        <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
    </div>
</div>

</body>
</html>
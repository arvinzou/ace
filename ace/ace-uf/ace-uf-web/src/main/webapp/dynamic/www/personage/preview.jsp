<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>统战人士</title>
    <jsp:include page="../../common/common-www.jsp"/>
    <script>
var id='${param.id}';


    </script>
</head>
<body ontouchstart style="padding:5px">
<div ng-app="myApp" ng-controller="myCtrl">
    <div class="weui-flex">
        <div class="weui-flex__item" style="text-align: center;">
            <img class="photo" ng-if="o.photo" src="{{fastdfs_server+ o.photo}}"/><div style="text-align: center;padding:2px;font-weight:800;font-size:14px">{{o.name}}</div>
        </div>
        <div class="weui-flex__item">
            <div class="weui-form-preview__bd">

                <div class="weui-form-preview__item item-text">
                    <label class="weui-form-preview__label">党派</label>
                    <span class="weui-form-preview__value">{{o.tel}}</span>
                </div>

                <div class="weui-form-preview__item item-text">
                    <label class="weui-form-preview__label">辖区</label>
                    <span class="weui-form-preview__value">{{o.areaName}}</span>
                </div>
                <div class="weui-form-preview__item item-text">
                    <label class="weui-form-preview__label">单位</label>
                    <span class="weui-form-preview__value">{{o.deptName}}</span>
                </div>
                <div class="weui-form-preview__item item-text">
                    <label class="weui-form-preview__label">生日</label>
                    <span class="weui-form-preview__value">{{o.birthdayName}}</span>
                </div>
                <div class="weui-form-preview__item item-text">
                    <label class="weui-form-preview__label">电话</label>
                    <span class="weui-form-preview__value">{{o.tel}}</span>
                </div>
            </div>
        </div>
    </div>
    <h3 class="header-title">其他</h3>


    <div  class="weui-form-preview__bd">
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">类别</label>
            <span class="weui-form-preview__value">{{o.categoryName}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">籍贯</label>
            <span class="weui-form-preview__value">{{o.deptName}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">民族</label>
            <span class="weui-form-preview__value">{{o.tel}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">职级</label>
            <span class="weui-form-preview__value">{{o.tel}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">加入时间</label>
            <span class="weui-form-preview__value">{{o.tel}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">行政职务</label>
            <span class="weui-form-preview__value">{{o.tel}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">学历</label>
            <span class="weui-form-preview__value">{{o.deptName}}</span>
        </div>

        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">职称</label>
            <span class="weui-form-preview__value">{{o.tel}}</span>
        </div>

        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">现任职务时间</label>
            <span class="weui-form-preview__value">{{o.tel}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">现任职级时间</label>
            <span class="weui-form-preview__value">{{o.tel}}</span>
        </div>

    </div>
    <h3 class="header-title">简介</h3>
    <div style="padding:12px">
        {{o.intro}}
    </div>


    <div style="padding:12px">
        <img class="photo" ng-if="o.remark" src="{{fastdfs_server+ o.remark}}"/><div ng-if="o.remark" style="text-align: center;padding:2px;font-weight:800;font-size:14px">二维码</div>
    </div>

</div>
<jsp:include page="../../common/footer-1-www.jsp"/>
<script src="${pageContext.request.contextPath}/content/www/personage/preview.js"></script>
<style>
.photo{
    max-width: 150px;
    max-height: 150px;

}
.header-title {
    line-height: 20px;
    margin-bottom: 5px;
    margin-top: 5px;
    padding-bottom: 4px;
    border-bottom: 1px solid #CCC;
    font-weight:800;
    font-size:14px;
}
.item-text {
    line-height: 20px;
    margin-bottom: 5px;
    margin-top: 5px;
    padding-bottom: 4px;
    border-bottom: 1px solid #CCC;
    font-weight:800;
    font-size:14px;
}
.weui-form-preview__bd {
    padding: 10px 15px;
    font-size: .9em;
    text-align: right;
    line-height: 2;
}
.weui-form-preview__label {
    float: left;
    margin-right: 1em;
    min-width: 1em;
    text-align: justify;
    text-align-last: justify;
}
</style>
</body>
</html>
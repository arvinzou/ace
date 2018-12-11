<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>

    <title>精品课程</title>
    <jsp:include page="../../../dynamic/common/base.jsp"/>
    <link rel="stylesheet" type="text/css" href="css/swiper.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script type="text/javascript" src="../../common/js/loader.js"></script>
    <script type="text/javascript" src="js/swiper.min.js"></script>
    <script src="${portalPath}/content/common/js/dict_jxb.js?version=${version}"></script>
    <script type="text/javascript" src="js/act.js"></script>
</head>

<body>
<div class="mainContainer">
    <div class="row menu">
        <div class="col-xs-2 col-md-2" style="padding-right: 0;">
            <div class="selectbox">
                <div class="selemediv">
                    <div class="selemenu ">
                        <input style="display: none;" value="" id="ability"/>
                        <input style="display: none;" value="" id="csinput"/>
                        <input style="display: none;" value="" id="quinput"/>
                        <span class="">筛选</span>
                    </div>
                    <div class="citylist2">
                        <div class="xzk">
                            <div class="leibie">针对能力</div>
                            <ul class="shangquan" id="method">
                                <li class="active">不限</li>

                            </ul>
                        </div>
                        <div class="xzk">
                            <div class="leibie">课程类型</div>
                            <ul class="chengshi">
                                <li class="active">不限</li>
                                <li>单节课程</li>
                                <li>系列课程</li>
                            </ul>
                        </div>
                        <div class="xzk">
                            <div class="leibie">价格</div>
                            <ul class="qu">
                                <li class="active">不限</li>
                                <li>免费</li>
                                <li>付费</li>
                                <%--<li>会员免费</li>--%>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-10 col-md-10"
             style="background-color: #fff; padding-left: 0!important;padding-right: 0!important;">
            <div class="container">
                <div class="swiper-container">
                    <div class="swiper-wrapper" id="menuObject">


                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="course_List">
        <ul id="courseList">

        </ul>
    </div>
</div>
</body>

<script id="menuObject-tpl" type="text/template">

        <span class="active swiper-slide" onclick="courseList();">全部</span>
        {@each data as item, index}
        {@if item.CODE!=''}
        <span class="swiper-slide" onclick="courseList('\${item.CODE}');">\${item.NAME}</span>
        {@/if}
        {@/each}
</script>
<script id="courseTemp" type="text/template">
    {@each data as item}
    <li data-id="\${item.id}" onclick="courseDetail('\${item.id}');">
        <div class="left_div">
            <img src="\${item.cover}" alt="">
        </div>
        <div class="right_div">
            <p class="test_title">
                \${item.name}
            </p>
            <p class="test_remark">
                <span>\${getObjectName(item.objects)}</span>
                <span>·</span>
                <span>\${getPurportName(item.purport)}</span>
            </p>

            <span class="price">
                    {@if item.cost==0||item.costType == '0'}
                    <span class="free">免费</span>
                    {@else}
                    <span class="no_free">
                        <span class="now_price">￥\${item.cost}</span>
                        <span class="old_price">￥\${item.primeCost}</span>
                    </span>
                    {@/if}
            	</span>
        </div>
    </li>
    {@/each}
</script>

<script id="method-tpl" type="text/template">
    {@each data as item, index}
    {@if item.CODE != ''}
    <li data-value="\${item.CODE}">\${item.NAME}</li>
    {@/if}
    {@/each}
</script>
</html>
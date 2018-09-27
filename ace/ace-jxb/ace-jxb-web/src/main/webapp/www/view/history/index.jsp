<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>我的咨询</title>
    <link rel="stylesheet" type="text/css" href="../common/css/nav.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <jsp:include page="../../../dynamic/common/base.jsp"/>
    <script type="text/javascript" src="js/act.js"></script>
    <script type="text/javascript" src="../../common/js/loader.js"></script>
</head>
<body>
<div class="nav_box">
    <div class="row" style="height: 1.5rem;">
        <div class="navigation">
            <div class="news-title">
                <ul class="news-module project_nav_ul clear">
                    <li class="active" onclick="orderList();">全部</li>
                    <li onclick="orderList('1');">待支付</li>
                    <li onclick="orderList('2','3','6');">待完成</li>
                    <li onclick="orderList('4','7');">已完成</li>
                </ul>
                <div class="news-slider"></div>
            </div>
        </div>
    </div>
    <div class="container" id="orderList">

    </div>

    <script id="orderListTemp" type="text/template">
        {@each data.rows as item,index}
        <div class="row box">
            <div class="row">
                <div class="col-xs-3 col-sm-2 row_01" onclick="showDetail('\${item.id}');">
                    <img class="head_img" src="\${item.counselor.imagePhotoUrl}"/>
                </div>
                <div class="col-xs-6 col-sm-8 row_01" onclick="showDetail('\${item.id}');">
                    <div class="row consotor">
                        <div class="col-xs-6 col-xs-6 consotor_01">\${item.counselor.name}</div>
                        <div class="col-xs-6 col-xs-6 consotor_02"></div>
                    </div>
                    <div class="row introduce" onclick="showDetail('\${item.id}');">
                        <p>\${item.counselor.certification}</p>
                    </div>
                </div>
                <div class="col-xs-3 col-sm-2 row_01">
                    {@if item.payStatus == '2' || item.payStatus == '6' || item.payStatus == '7'}
                    <button class="chat" data-toggle="modal" data-target="#myModal" onclick="setVal('\${item.id}');">投诉TA</button>
                    {@/if}
                </div>
            </div>
            <div class="row dorder" onclick="showDetail('\${item.id}');">
                <div class="col-xs-5 col-sm-5" style="padding-right: 0;">
                    {@if item.consultProduct.type == '1'}
                    <span class="dorder_title">电话咨询：</span><span class="dorder_num">\${item.amount}</span>
                    {@else if item.consultProduct.type == '2'}
                    <span class="dorder_title">视频咨询：</span><span class="dorder_num">\${item.amount}</span>
                    {@else if item.consultProduct.type == '3'}
                    <span class="dorder_title">面对面咨询：</span><span class="dorder_num">\${item.amount}</span>
                    {@/if}
                </div>
                <div class="col-xs-5 col-sm-5" style="padding: 0;"><span class="dorder_title">支付金额：</span><span
                        class="dorder_num">\${item.payMoney}</span></div>
                <div class="col-xs-2 col-sm-2"></div>
            </div>
            <div class="row dorder" style="border: none;" onclick="showDetail('\${item.id}');">
                <div class="col-xs-8 col-sm-8" style="overflow: hidden;"><p class="order_num">
                    预约时间：\${item.consultOrder.reserveDate}</p></div>
                <div class="col-xs-4 col-sm-4" style="text-align: right;">
                    {@if item.payStatus == '1'}
                    <p class="unfinished">待支付</p>
                    {@else if item.payStatus == '2'}
                    <p class="unfinished">已付款</p>
                    {@else if item.payStatus == '3'}
                    <p class="unfinished">申请退款</p>
                    {@else if item.payStatus == '4'}
                    <p class="finished">已退款</p>
                    {@else if item.payStatus == '6'}
                    <p class="unfinished">结束/待评价</p>
                    {@else if item.payStatus == '7'}
                    <p class="finished">已完结</p>
                    {@else}
                    <p class="finished">自动关闭</p>
                    {@/if}
                </div>
            </div>
        </div>
        {@/each}
    </script>
</div>

<!--投诉模态框-->
<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="margin:4rem 0.5rem !important;">
        <div class="modal-content">
            <div class="modal-body">
                <h3 class="complaint-title">投诉TA</h3>
                <textarea style="width: 100%; height: 6rem;" name="comment" placeholder="请在此输入您在购买和咨询服务过程中遇到的问题，我们收到投诉后会及时与您联系~"
                                  maxlength="200"
                                  onfocus="this.placeholder=''"
                                  onblur="this.placeholder='请在此输入您在购买和咨询服务过程中遇到的问题，我们收到投诉后会及时与您联系~'"></textarea>
            </div>
            <div class="pjbtn" style="padding-left:2rem;">
                <button  class="commit" onclick="commit();">确定投诉</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>

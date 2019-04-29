<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>弱电</title>
</head>
<script src="js/index.min.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="css/common.css"/>
<link rel="stylesheet" href="css/index.css"/>
<%
    session.setAttribute("portalPath", "/portal");
%>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var version = '${cfg.version}';
    var fastdfs_server = '${cfg.fastdfs_server}';
    var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
    var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
    var default_page_list = [${cfg.default_page_list}];
</script>

<body>
<div class="head">
    <div class="logo">
        <img src="img/logo.png" alt=""/> 江汉区照明控制平台
    </div>
    <div class="btns">
        <div class="btn">
            <p class="cn">场景控制</p>
            <p class="en">Scene Control</p>
        </div>
        <div class="btn">
            <p class="cn">场景列表</p>
            <p class="en">Scene List</p>
        </div>
        <div class="btn strategySend">
            <p class="cn">策略下发</p>
            <p class="en">Strategy Send</p>
        </div>
    </div>
    <div class="userInfo">
        <div class="up">
            <div class="headImg">
                <img alt="" class="img-circle"
                     src="${portalPath}/content/common/assets/layouts/layout/img/avatar3_small.jpg"/>
            </div>
            <span class="username username-hide-on-mobile"> ${SESSION_USERPROP_KEY.name} </span>

            <div class="menu-wrap">
                <ul class="menu-ul">
                    <li><a href="${portalPath}/index.jsp">返回首页</a></li>
                    <li><a href="${portalPath}/dynamic/portal/security/loginOut.jsp">安全退出</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div style="display: none" class="sceneControl content">
    <div class="left">
        <div class="control">
            <div class="title">
                场景控制
            </div>
            <div class="btns">
                <div class="playback-status" >
                    <img src="img/start.png" />
                    <p></p>
                </div>
                <div>
                    <img src="img/stop.png" />
                    <p>恢复</p>
                </div>
                <div>
                    <img src="img/change.png" />
                    <p>切换</p>
                </div>
            </div>
            <div class="radio">
            </div>
            <div class="wrap switch-wrap">
                <input type="checkbox" id="s6"   />
                <label class="slider-v3 label-switch" for="s6"></label>
                <span></span>
            </div>
            <!--/wrap-->

        </div>
    </div>
    <div class="center">
        <div class="checkList">
            <ul id="checked">

            </ul>
        </div>
        <div class="videoList">
            <ul id="videos">
                <div class="default">
                    <img src="img/default.png" alt="">
                    <p>当前暂无场景，请先选择站点</p>
                </div>
            </ul>
        </div>
    </div>
    <div class="right">
        <div class="stations">
            <div class="title">江汉区站点(多选)</div>
            <div class="inputGroup">
                <input type="text" placeholder="输入站点名称"/>
                <button>

                </button>
            </div>
            <div class="sList">
                <ul id="check">

                </ul>
            </div>
        </div>
    </div>
</div>

<div class="content strategyPart content1"  style="display: block" >
    <div class="modals strategy">
        <div class="modals-head">
            <span class="title">定时设置</span>
            <div class="operation">
                <button>创建</button>
                <div class="inputGroup">
                    <input class="timerName" type="text" placeholder="输入任务名称" name="timerName"/>
                    <button class="submit">

                    </button>
                </div>
            </div>
        </div>
        <div class="modals-body">
            <div class="table">
                <table class="table-tg" style="border:1px solid rgb(12,129,135);">
                    <tr>
                        <td class="tg-mvxc" width="10%">策略编号</td>
                        <td class="tg-mvxc" width="30%">策列名称</td>
                        <td class="tg-mvxc" width="10%">分区</td>
                        <td class="tg-mvxc" width="20%">策略状态</td>
                        <td class="tg-mvxc" width="20%">操作</td>
                    </tr>
                    <tbody id="strategyList">

                    </tbody>
                </table>
            </div>
            <div class="paginationbar" style="float: right">
                <ul class="pagination" id="pagination3"></ul>
            </div>
        </div>
    </div>
</div>


<div class="modal" style="display: none">
    <div class="modal-content">
        <div class="bg">
            <div class="lefgBg">
                <div class="l1"></div>
                <div class="l2"></div>
                <div class="l3"></div>
                <div class="l2 l22"></div>
                <div class="l4"></div>

            </div>
            <div class="centerBg">
                <div class="c1"></div>
                <div class="c2"></div>
                <div class="c3"></div>
            </div>
            <div class="rightBg">
                <div class="r1"></div>
                <div class="r2"></div>
                <div class="r3"></div>
                <div class="r2 r22"></div>
                <div class="r4"></div>
            </div>
        </div>
        <div class="modal-body">
            <div class="inputGroup">
                <input type="text" placeholder="输入站点名称"/>
                <button>
                </button>
            </div>

            <div class="inputGroup buttonGroup">
                <button>
                    确定
                </button>
            </div>
            <div class="videoList">
                <ul>
                    <li class="videoGroup">
                        <div class="info">
                            <label class="label" for="c1">
                                <input id="c1" type="checkbox"/> 视频名称
                            </label>
                        </div>
                        <div class="video">
                            <video controls="true" autoplay="true">
                                <source src="http://www.w3school.com.cn/example/html5/mov_bbb.mp4"
                                        type="video/mp4">
                                </source>
                            </video>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>


<div class="modal scenario-modal" style="display: none">
    <div class="modal-content">
        <div class="bg">
            <div class="lefgBg">
                <div class="l1"></div>
                <div class="l2"></div>
                <div class="l3"></div>
                <div class="l2 l22"></div>
                <div class="l4"></div>

            </div>
            <div class="centerBg">
                <div class="c1"></div>
                <div class="c2"></div>
                <div class="c3"></div>
            </div>
            <div class="rightBg">
                <div class="r1"></div>
                <div class="r2"></div>
                <div class="r3"></div>
                <div class="r2 r22"></div>
                <div class="r4"></div>
            </div>
        </div>
        <div class="modal-body">

        </div>
        <div class="modal-close">
            <img src="img/close.png" alt="">
        </div>
    </div>
</div>


<%--<div class="modal addStrategy-modal" style="display: block">--%>
    <%--<div class="modal-content">--%>
        <%--<div class="bg">--%>
            <%--<div class="lefgBg">--%>
                <%--<div class="l1"></div>--%>
                <%--<div class="l2"></div>--%>
                <%--<div class="l3"></div>--%>
                <%--<div class="l2 l22"></div>--%>
                <%--<div class="l4"></div>--%>

            <%--</div>--%>
            <%--<div class="centerBg">--%>
                <%--<div class="c1"></div>--%>
                <%--<div class="c2"></div>--%>
                <%--<div class="c3"></div>--%>
            <%--</div>--%>
            <%--<div class="rightBg">--%>
                <%--<div class="r1"></div>--%>
                <%--<div class="r2"></div>--%>
                <%--<div class="r3"></div>--%>
                <%--<div class="r2 r22"></div>--%>
                <%--<div class="r4"></div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="modal-body">--%>

        <%--</div>--%>
        <%--<div class="modal-close">--%>
            <%--<img src="img/close.png" alt="">--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>



<div class="modal setTime-modal" style="display: block">
    <div class="modal-content">
        <div class="bg">
            <div class="lefgBg">
                <div class="l1"></div>
                <div class="l2"></div>
                <div class="l3"></div>
                <div class="l2 l22"></div>
                <div class="l4"></div>

            </div>
            <div class="centerBg">
                <div class="c1"></div>
                <div class="c2"></div>
                <div class="c3"></div>
            </div>
            <div class="rightBg">
                <div class="r1"></div>
                <div class="r2"></div>
                <div class="r3"></div>
                <div class="r2 r22"></div>
                <div class="r4"></div>
            </div>
        </div>
        <div class="modal-body">
            <div class="box">
                <div class="unit">
                    <div class="piece active" data-type="monthData">日程数据<br/>(周)</div>
                    <div class="piece" data-type="monthData">日程数据<br/>(月)</div>
                    <div class="piece" data-type="weekData">假日模式</div>
                    <div class="piece" data-type="dayData">时间模式</div>
                </div>
                <div class="info">
                    <div class="btn-list">
                        <button class="style1" form="setS">确定</button>
                    </div>
                    <div class="show" id="page-update">
                        <form id="setS" action="">
                            <div class="form" id="inputList">

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-close">
            <img src="img/close.png" alt="">
        </div>
    </div>
</div>





</body>

<script id="tpl-check" type="text/template">
    {@each data as item, index}
    <li data-name="\${item.name}" data-code="\${item.code}">\${item.name}</li>
    {@/each}
</script>

<script id="tpl-checked" type="text/template">
    {@each data as item, index}
    <li data-name="\${item.name}" data-code="\${item.code}">\${item.name}</li>
    {@/each}
</script>

<script id="tpl-videos" type="text/template">
    {@each data as item, index}
    <li class="videoGroup video\${item.stationCode}">
        <div class="info">
            <label class="label" for="c\${item.id}">
                <input id="c\${item.id}" type="checkbox"/> \${item.animaResVo.name}
            </label>
        </div>
        <div class="video">
            <video controls="true">
                <source src="\${item.prePlayUrl}"
                        type="video/mp4">
                </source>
            </video>
        </div>
    </li>
    {@/each}
</script>


<script id="tpl-strategyList" type="text/template">
    {@each data as item, index}
    <tr class="tr">
        <td class="tg-84q5"> \${item.code}</td>
        <td class="tg-84q5"> \${item.name}</td>
        <td class="tg-84q5">\${item.areaCode}</td>
        <td class="tg-84q5"> {@if item.state==0}
            <span style="color: #FF616D;">未执行</span>
            {@else if item.state==1}
            <span style="color: #0AFD99;">执行</span>
            {@else}
            {@/if}
        </td>
        <td class="tg-84q5">
            <a href="javascript:selectTimerDate('\${item.id}');" style="color: #53FDFF;">修改</a>
            <a href="javascript:selectTimerDate('\${item.id}');" style="color: #53FDFF;">定时</a>
            <a href="javascript:selectTimerDate('\${item.id}');" style="color: #53FDFF;">下发</a>
        </td>
    </tr>
    {@/each}
</script>

<script id="tpl-strategyList" type="text/template">
    {@each data as item, index}
    <li>
        <div class="top">
            \${item.code}-\${item.name}
        </div>
        <div class="bottom">
            <button data-areaNo="\${item.areaNo}" data-areaNodeID="\${item.areaNodeID}">
                选择
            </button>
        </div>
    </li>
    {@/each}
</script>

<script id="tpl-presets" type="text/template">
    {@each data as item, index}
    <li data-presetNo="\${item.presetNo}">\${item.strategyExplain}</li>
    {@/each}
</script>


<script id="tpl-weeks" type="text/template">
    <ul class="list weeks">
        <li>
            <span> 周一</span>
            <div class="wrap">
                <input type="checkbox" id="w1" name="weeks" value="1"/>
                <label class="slider-v3" for="w1"></label>
            </div>
        </li>
        <li>
            <span> 周二</span>
            <div class="wrap">
                <input type="checkbox" id="w2" name="weeks" value="2"/>
                <label class="slider-v3" for="w2"></label>
            </div>
        </li>
        <li>
            <span> 周三</span>
            <div class="wrap">
                <input type="checkbox" id="w3" name="weeks" value="3"/>
                <label class="slider-v3" for="w3"></label>
            </div>
        </li>
        <li>
            <span> 周四</span>
            <div class="wrap">
                <input type="checkbox" id="w4" name="weeks" value="4"/>
                <label class="slider-v3" for="w4"></label>
            </div>
        </li>
        <li>
            <span> 周五</span>
            <div class="wrap">
                <input type="checkbox" id="w5" name="weeks" value="5"/>
                <label class="slider-v3" for="w5"></label>
            </div>
        </li>
        <li>
            <span> 周六</span>
            <div class="wrap">
                <input type="checkbox" id="w6" name="weeks" value="6"/>
                <label class="slider-v3" for="w6"></label>
            </div>
        </li>
        <li>
            <span> 周日</span>
            <div class="wrap">
                <input type="checkbox" id="w7" name="weeks" value="7"/>
                <label class="slider-v3" for="w7"></label>
            </div>
        </li>
    </ul>
</script>

<script id="tpl-month" type="text/template">
    {@each data as item, index}
    <ul class="list weeks">
        <li>
            <span> 1月</span>
            <div class="wrap">
                <input type="checkbox" id="m1" name="weeks" value="1"/>
                <label class="slider-v3" for="m1"></label>
            </div>
        </li>
    </ul>
    {@/each}
</script>


<style type="text/css">

</style>
<script src="https://cdn.bootcss.com/jquery/3.4.0/jquery.min.js"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<script>
    $(function(){
        requestSenceStatusData();  //请求场景状态数据
    });
    /**
     * 请求场景状态数据
     */
    function requestSenceStatusData(){
        $.ajax({
            url: contextPath + "/pagePortal/findList",
            type: "post",
            async: false,
            success: function (res) {
               console.log(res);
                if(res.length > 0){
                    initSenceStatusData(res);   //初始化场景状态数据
                }else{
                    alert('暂无数据')
                }
            },
            error: function () {
                alert("对不起出错了！");
            }
        });
    }

    /**
     * 初始化场景状态数据
     */
    function  initSenceStatusData(arr){
        var  $switch = $('.switch-wrap');
        var  $playbackStatus = $('.playback-status');
        $.each(arr,function (i,item) {
            console.log(item);
            if(item.itemKey == "sceneControlState"){
                if(item.itemValue==1){ // 1开，2关
                    $switch.find('input').attr('checked',true);
                    $switch.find('label').attr('data-value',item.itemValue);
                    $switch.find('span').html('开启');
                }else{
                    $switch.find('input').attr('checked',false);
                    $switch.find('label').attr('data-value',item.itemValue);
                    $switch.find('span').html('关闭');
                }
            }
            if(item.itemKey == "playbackStatus"){  // 0播放， 1暂停
                if(item.itemValue == 0){
                    $playbackStatus.find('img').attr('src','img/stop.png');
                    $playbackStatus.find('p').html('播放');
                    $playbackStatus.attr('data-value',item.itemValue);
                }else{
                    $playbackStatus.find('img').attr('src','img/start.png');
                    $playbackStatus.find('p').html('暂停');
                    $playbackStatus.attr('data-value',item.itemValue);
                }

            }
        });
    }

    /*
    * 总开关点击切换事件
    * */
    $('.label-switch').on('click',function(e){
        var  $switch = $('.switch-wrap');
        var $input = $switch.find('input');
        var $play = $('.playback-status');
        var str1 = 'sceneControlState';  //场景控制状态 // 1,开， 2关
        var str2 = 'playbackStatus';  //播放状态 //0播放， 1暂停
        if(confirm('确认切换当前状态？')){
            if($input.is(':checked')){
                $switch.find('label').attr('data-value',2);
                $input.attr('checked',true);
                $switch.find('span').html('关闭');
                submitSenceStatusData(str1,2);  //场景控制状态

                //场景开关关闭时，关闭所有操作
                $play.attr('data-value',1);
                $play.find('img').attr('src','./img/start.png');
                $play.find('p').html('暂停');
                submitSenceStatusData(str2,1);  //播放状态为暂停
            }else{
                $switch.find('label').attr('data-value',1);
                $input.attr('checked',false);
                $switch.find('span').html('开启');
                submitSenceStatusData(str1,1);
            }
        }

    });
    /*
    * 提交状态
    * */
    function submitSenceStatusData(str,num){
        $.ajax({
            url: contextPath + "/pagePortal/updatePagePortalData",
            type: "post",
            async: false,
            data:{
                key:str,
                val:num
            },
            success: function (res) {
                if(res.status == 0){
                    // alert('场景控制状态保存成功');
                }
            },
            error: function () {
                alert("对不起出错了！");
            }
        });
    }

    /**
     * 播放状态点击事件
     */
    $('.playback-status img').on('click',function () {
        var switchValue = $('.switch-wrap label').attr('data-value');
        var $play = $('.playback-status');
        var str = 'playbackStatus';  //播放状态
        var playValue =  $play.attr('data-value');
        console.log(switchValue);
        console.log(playValue);
        if(switchValue == 1){ // 1,开， 2关
            if(playValue == 0){  //0播放， 1暂停
                $play.attr('data-value',1);
                $play.find('img').attr('src','img/start.png');
                $play.find('p').html('暂停');
                submitSenceStatusData(str,1);
            }else{
                $play.attr('data-value',0);
                $play.find('img').attr('src','img/stop.png');
                $play.find('p').html('播放');
                submitSenceStatusData(str,0);
            }
        }else{
            alert('请切换开关状态为开启，再进行操作！')
        }
    });


    //菜单显示与隐藏
    document.onclick = function (e) {
        $('.menu-wrap').hide();
    };
    $('.userInfo>.up').on("click", function (e) {
        if ($('.menu-wrap').css("display") == "none") {
            $('.menu-wrap').show();
        } else {
            $('.menu-wrap').hide();
        }
        e = e || event;
        stopFunc(e);
    });

    $('.menu-wrap').on("click", function (e) {
        e = e || event;
        stopFunc(e);
    });

    function stopFunc(e) {
        e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
    }
</script>
</html>
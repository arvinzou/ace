//create by cxy 2016-08
window.onload = function () {

    $(function () {
        getCourseList();
        initFullCalendar(jsonStr);
    })

    function getCourseList() {
        var url = contextPath + "/course/findListClassifiedName";
        var data = {};
        $.getJSON(url, data, function (rst) {
            console.log(rst);
            render('#courseList', rst.data, 'tpl-courseList');
            initEvent();
        })
    }

    function getdate() {
        var now = new Date();
        y = now.getFullYear();
        m = now.getMonth() + 1;
        d = now.getDate();
        m = m < 10 ? "0" + m : m;
        d = d < 10 ? "0" + d : d;
        console.log(y + "-" + m + "-" + d);
        return y + "-" + m + "-" + d;
    }

    function initEvent() {
        $(".external-events-box>li>h5").click(function () {
            $(this).parent().siblings().find("h5").removeClass("hbg");
            $(this).parent().siblings().find("ul").hide();
            $(this).next().toggle();
            $(this).toggleClass("hbg");
        });
        $('#external-events .fc-event').each(function () {
            $(this).data('event', {
                title: $.trim($(this).text()),
                t:$(this).data('teacher'),
                stick: false
            });
            $(this).draggable({
                zIndex: 999,
                revert: true,
                revertDuration: 0
            });
        });
    }

    /*页面渲染*/
    function render(obj, data, tplId) {
        var tpl = document.getElementById(tplId).innerHTML;
        var html = juicer(tpl, {
            data: data,
        });
        $(obj).html(html);
    }

    //初始化、
    var jsonStr = {
        name: "",
        class: "",
        crew_startDate: getdate(),
        crew_end_Date: '',
//        crew_classTime_Arr:["08:40~09:20","09:30~10:10","10:20~11:00","11:20~12:00","13:40~14:20","14:40~15:20","15:40~16:20","16:40~17:20"]
        crew_classTime_Arr: ["上午", "下午"]
    };
    
    
    function () {
        
    }
    

    //初始化日历方法
    function initFullCalendar(jsonStr) {
        //var crew_classTime_arr=new Array();
        //var crew_classTime_Arr=new Array("08:40~09:20","09:30~10:10","10:20~11:00","11:20~12:00","13:40~14:20","14:40~15:20","15:40~16:20","16:40~17:20");

        var crew_classTime_Arr;
        var crew_startDate = getdate();
        //获取课节
        $.each(jsonStr, function (key, value) {
            console.log(jsonStr);
            crew_classTime_Arr = jsonStr["crew_classTime_Arr"];
            crew_startDate = jsonStr["crew_startDate"];
        });

        //先清空已有控件内容
        $('#calendar').fullCalendar('destroy');
        //加载日历控件
        $('#calendar').fullCalendar({
//                defaultDate: '2016-07-12',
            defaultDate: crew_startDate,
            defaultView: "agendaWeek", //默认显示周
            //        defaultView: 'timelineDay',
            height: 600,
            editable: true,
            customButtons: {
                //自定义 按钮可以添加多个，自己命名  在header 部分调用即可 比如这里的myCustomButton
                myCustomButton: {
                    text: '自定义按钮',
                    click: function () {
                        alert('点击自定义按钮!');
                    }
                },
                prev: {
                    text: '上一周',
                    click: function () {
                        $('#calendar').fullCalendar('prev');
                        $('#calendar').fullCalendar('getView').title
                    }
                },
                next: {
                    text: '下一周',
                    click: function () {
                        $('#calendar').fullCalendar('next');
                    }
                }
            },
            header: {
                left: 'prev,next today, myCustomButton',
                center: 'title',
                right: 'agendaWeek'
            },
            eventStartEditable: true,
            eventDurationEditable: false,
            eventOverlap: false,
            allDaySlot: false,//是否显示全天
            axisFormat: 'H:mm时',//修改日期前面显示内容默认为早上6点00分，改完后显示6:00时('H:mm时')
            slotDuration: '01:00:00', //时间间隔
            slotLabelFormat: '第H课',
            crew_classTime_Arr: crew_classTime_Arr,
            scrollTime: '01:00:00', //滚动起始时间
            displayEventTime: false, //默认不显示事件上的时间
//          timeFormat: 'HH:mm',//事件上日期格式（默认事件不显示日期）
            minTime: '01:00:00', //最小开始时间
            maxTime: '03:00:00',//最大开始时间
            defaultTimedEventDuration: '01:00:00',//默认拖进事件 的时间间隔大小
            buttonText: {
                today: '今天',
                week: '周',
                day: '日'
            },


            //点击一天时触发
            dayClick: function (date, allDay, jsEvent, view) {
                //当前日期
                // alert(date);
//                        alert(date.format('YYYY-MM-DD'));
//                        alert(date.format('DD'));

//                        if (typeof($(this).attr("isHoliday")) == "undefined" || $(this).attr("isHoliday") == "false") {
//                            $(this).css({'background-color': '#eaf4fa'});
//                            $(this).attr("isHoliday", "true");
//                            showOperationDialog("","addModal");
//                        } else {
//                            $(this).css({'background-color': '#f8f8f8'});
//                            $(this).attr("isHoliday", "false");
//                            $(this).find("i").remove();
//                        }

//添加事件
//                        var eventData;
//                        if (title) {
//                            eventData = {
//                                title: title,
//                                start: start,
//                                end: end
//                            };
//                        }
//                        $('#calendar').fullCalendar('addEventSource',eventData);
            },
            // 当点击某一个事件时触发此操作
            eventClick: function (calEvent, jsEvent, view) {
//                        alert(calEvent.id);
                //  $(this).css('border-color', 'red');
                showOperationDialog("", "addModal");
//删除事件
//                        $('#calendar').fullCalendar('removeEvents',"test");
            },
            //当鼠标悬停在一个事件上触发此操作
            eventMouseover: function (calEvent, jsEvent, view) {
            //
            },
            //当鼠标从一个事件上移开触发此操作
            eventMouseout: function (event, jsEvent, view) {
            },

            droppable: true, //允许拖拽放置
            eventReceive: function (event) {
                //根据event.title内容，修改拖拽后的样式
                console.log(1111111);
                console.log(arguments);
                // event.className.push("bg-crew-baoan");
                // event.title = '<p class="fc-schedule-td-p">' + event.title + '</p>' +
                //     '<p class="fc-schedule-td-p">测试拖拽</p>' +
                //     '<p class="fc-schedule-td-p"><span class="font-blue">测试</span>，测试 </p>';
                //
                //     $('#calendar').fullCalendar('renderEvent', event, true);//重新加载此事件
            },
            eventRender: function (event, element) {
                // console.log(arguments);
                //titile值改为html ,直接修改后，缩放不起作用，fc-content中少了子div(.fc-bg   .fc-title fc-resizer )
                //element.html(event.title);
                element.find(".fc-title").html('《'+event.title+'》——'+event.t);//cxy修改后的，不确定会不会有其他问题


            },
            //浏览器大小改变是触发
            eventResize: function (event, dayDelta, minuteDelta, revertFunc) {

            },
            //拖拽之后触发（仅限控件内部事件）
            eventDrop: function (event, dayDelta, minuteDelta, allDay, revertFunc) {
                console.log('eventDrop');
                console.log(arguments);
            },
            //事件被拖动之后触发
            eventDragStop: function (event, jsEvent, ui, view) {
                console.log('eventDragStop');
               console.log(arguments);
            },
            //事件被拖动之前触发
            eventDragStart: function (event, jsEvent, ui, view) {
                //alert(event.id);
                // alert(234);
            },
            //事件数据
            events: [
            ],

            //动态取数据例子
//                    events: function (start, end, timezone, callback) {
//                        $.ajax({
//                            type: "GET",
//                            url: '/routine/myschedules',
//                            dataType: 'json',
//                            data: {start: "" + start, end: "" + end, filter: $("#hid").val()},
//                            success: function (doc) { var events = []; for (var i = 0; i < doc.meeting.length; i++) { var m = doc.meeting[i];
//                                events.push({
//                                    key: "meeting",
//                                    id: m.id,
//                                    typeId: m.meetingType.typeId,
//                                    title: m.title,
//                                    content: "时间：" + ameutils.convertTime(m.meetingDate, "yyyy-MM-dd hh:mm:ss") + "  类型：" + m.meetingType.typeName,
//                                    start: ameutils.convertTime(m.meetingDate, "yyyy-MM-dd"),
//                                    time: ameutils.convertTime(m.meetingDate, "yyyy-MM-dd hh:mm:ss"),
//                                    backgroundColor: '#E0FFFF',
//                                    textColor: '#000000',
//                                    borderColor: '#E0FFFF',
//                                });
//                            } for (var i = 0; i < doc.outgoing.length; i++) { var o = doc.outgoing[i];
//                                events.push({
//                                    key: "outgoing",
//                                    id: o.id,
//                                    typeId: o.outgoingType.type,
//                                    title: o.title,
//                                    content: "时间：" + ameutils.convertTime(o.startTime, "yyyy-MM-dd") + "  类型：" + o.outgoingType.typeName,
//                                    start: ameutils.convertTime(o.startTime, "yyyy-MM-dd"),
//                                    time: ameutils.convertTime(o.startTime, "yyyy-MM-dd"),
//                                    backgroundColor: '#E0FFFF',
//                                    textColor: '#000000',
//                                    borderColor: '#E0FFFF',
//                                    constraint: 'availableForMeeting'  }); if (o.outgoingType.type == 10)
//                                    outgoingnum2 = outgoingnum2 + 1; else  outgoingnum1 = outgoingnum1 + 1;
//                            } var neihtml = ""; for (var i = 0; i < doc.bulletins.length; i++) { var b = doc.bulletins[i];
//                                neihtml += '<dl>';
//                                neihtml += '<dt>';
//                                neihtml += '<h4>' + b.title + '</h4>';
//                                neihtml += '<span class="f12 font-grey-silver">2015-05-18 07:13</span> </dt>';
//                                neihtml += '<dd>';
//                                neihtml += '<p>' + b.content + '</p>';
//                                neihtml += '</dd>';
//                                neihtml += '</dl>';
//                            } var waihtml = ""; for (var i = 0; i < doc.announcements.length; i++) { var a = doc.announcements[i];
//                                waihtml += '<dl>';
//                                waihtml += '<dt>';
//                                waihtml += '<h4>' + a.title + '</h4>';
//                                waihtml += '<span class="f12 font-grey-silver">2015-05-18 07:13</span> </dt>';
//                                waihtml += '<dd>';
//                                waihtml += '<p>' + a.type + '</p>';
//                                waihtml += '</dd>';
//                                waihtml += '</dl>';
//                            }
//                                $("#meetingnum").html(doc.meetingnum);
//                                $("#outgoingnum1").html(doc.outgoingnum1);
//                                $("#outgoingnum2").html(doc.outgoingnum2);
//                                $("#nei").html(neihtml);
//                                $("#wai").html(waihtml);
//                                callback(events);
//                            }
//                        });
//                    },
            //选择，可选择区域
            selectable: true,
            selectHelper: true,
            select: function (start, end) {
                var title = prompt('Event Title:');
                var eventData;
                if (title) {
                    eventData = {
                        title: title,
                        start: start,
                        end: end
                    };
                    $('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true

                }
                $('#calendar').fullCalendar('unselect');

            }
//第二种添加
//                    select: function (start, end, jsEvent, view) {
//                        $("#calendar").fullCalendar('addEventSource', [{
//                            start: start,
//                            end: end,
//                            rendering: 'background',
//                            block: true,
//                        }, ]);
//                        $("#calendar").fullCalendar("unselect");
//                    },


//                    $('#calendar').fullCalendar('refetchEvents'); //重新获取所有事件数据
//                    selectable: true,
//                    select: function (start, end, jsEvent, view) {
//                        $("#calendar").fullCalendar('addEventSource', [{
//                            start: start,
//                            end: end,
//                            rendering: 'background',
//                            backgroundColor:'#3192cb',
//                            block: true,
//                        }, ]);
//                        $('#calendar').fullCalendar('unselect');
//                    },


//                    selectOverlap: function(event) {
//                       // return ! event.block;
//                    }
        });


//        //重新设置日历的某些属性
//        $('#calendar').fullCalendar('option', {
////                    lang: 'fr',
////                    isRTL: true,
//            slotLabelFormat:'第H课',
//        });

//判断时间是否冲突
        function isOverlapping(event) {
            //var array =  $('#calendar').fullCalendar('clientEvents');

            var calendar = $('#calendar').fullCalendar('getCalendar');
            for (i in calendar) {
                if (array[i].id != event.id) {
                    if (!(array[i].start >= event.end || array[i].end <= event.start)) {
                        return true;
                    }
                }
            }
            return false;
        }


//        $('.fc-right .fc-button-group').before('<input class="t-button w100" value="新增(自定义)"/>');
////                $(".fc-slats table tbody tr .fc-axis").each(function(){
////                    $(this).append("<p style='line-height: 15px;margin: 0;padding: 0;text-align: left'>8:00~9:00</p>")
////                });

    }


}



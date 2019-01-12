//create by cxy 2016-08
window.onload = function () {

    $(function () {
        getCourseList();
        initFullCalendar(jsonStr);
        initSelect();
        initcurrentClass();
        // getEventData();
    })

    var parameter={
        classesId:2
    }
    function formatDate(target) {
        var date=target.substring(0,target.indexOf(" – "));
        date=date.replace('年','-').replace('月','-');
        parameter.weekDate=date+' 00:00:00';
        getEventData();
    }
    
    function getEventData() {
        var url=contextPath + "/classSchedule/LearnedCourses";
        $.getJSON(url,parameter,function (rst) {
            console.log(rst);
        })
    }

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
                t: $(this).data('teacher'),
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
        crew_classTime_Arr: ["上午", "下午"]
    };


    //初始化日历方法
    function initFullCalendar(jsonStr) {
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
                        formatDate($('#calendar').fullCalendar('getView').title);
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
            },
            // 当点击某一个事件时触发此操作
            eventClick: function (calEvent, jsEvent, view) {
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
            },
            eventRender: function (event, element) {
                element.find(".fc-title").html('《' + event.title + '》——' + event.t);//cxy修改后的，不确定会不会有其他问题
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
            events: [],
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
        });

    }

    function initSelect() {
        $(".js-example-basic-single2").select2({
            ajax: {
                url: contextPath + '/classes/findClassesList',
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    return {
                        status:2,
                        name: params.term, // search term
                        page: params.page
                    };
                },
                processResults: function (data, params) {
                    params.page = params.page || 1;
                    var datas = $.map(data.rows, function (obj) {
                        obj.text = obj.text || obj.name; // replace name with the property used for the text
                        return obj;
                    });
                    datas = $.map(datas, function (obj) {
                        obj.id = obj.id; // replace name with the property used for the text
                        return obj;
                    });
                    return {
                        results: datas,
                        pagination: {
                            more: (params.page * 30) < data.total_count
                        },
                        paginate: {
                            more: true
                        }
                    };
                },
                cache: true
            },
            placeholder: '选择历史班级',
            escapeMarkup: function (markup) {
                return markup;
            },
        });
    }
    
    function initcurrentClass() {
        var url=contextPath + '/classes/findClassesList';
        var data={
            status:1
        }
        $.getJSON(url,data,function (rst) {
            if(rst.status==0){
                render('#classList', rst.rows, 'tpl-classList');
            }
        })
    }
    
}



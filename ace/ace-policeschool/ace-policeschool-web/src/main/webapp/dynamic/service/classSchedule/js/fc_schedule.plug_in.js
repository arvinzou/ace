//create by cxy 2016-08
window.onload = function () {

    var parameter = {
        weekDate: getdate(),
        classesId: 2,
        start:0,
        limit:100
    }

    $(function () {
        getCourseList();
        initFullCalendar();
        initcurrentClass();
        initSelect();
        $('#b_container').on('click', '.searchCourse', searchCourse);
        $('#classList').on('click', '.btn-default', setParams);
        $('#courseDetail').on('click', '.delete', deleteCourse);
        $('#courseDetail').on('change', '.switch_4', ifTest);
        $(".js-example-basic-single2").on('change', classSearch2);

        // getEventData();
    })

    function ifTest() {
        var id = $(this).data('id');
        var url = contextPath + "/classSchedule/updateIfTest"
        var data = {
            id: id
        }
        $.getJSON(url, data, function (rst) {
            if (rst.status == 0) {

            }
        })
    }

    function deleteCourse() {
        var id = $(this).data('id');
        var url = contextPath + "/classSchedule/deleteClassScheduleByClassScheduleId"
        var data = {
            id: id
        }
        $.getJSON(url, data, function (rst) {
            if (rst.status == 0) {
                formatDate($('#calendar').fullCalendar('getView').title);
                $('#courseDetail').empty();
            }
        })
    }

    function classSearch2() {
        parameter['classesId'] = $("select[name=classId]").val();
        $('#classList .btn-default').removeClass('active');
        getEventData();
    }


    function setParams() {
        var that = $(this);
        parameter.classesId = that.data('id');
        that.siblings().removeClass('active');
        that.addClass('active');
        getEventData();
    }

    // var parameter = {
    //     weekDate:getdate(),
    // }

    function searchCourse() {
        var val = $('#b_container .courseNameTeacher').val();
        var data = {
            name: val,
            tName: val
        }
        getCourseList(data)
    }

    /** */



    function formatDate(target) {
        var date = target.substring(0, target.indexOf(" – "));
        date = date.replace('年', '-').replace('月', '-');
        parameter.weekDate = date + ' 00:00:00';
        getEventData();
    }

    function getEventData() {
        var url = contextPath + "/classSchedule/LearnedCourses";
        $.getJSON(url, parameter, function (rst) {
            if (rst.status == 0) {
                formatEventData(rst.rows);
                $('#calendar').fullCalendar('removeEvent');
            }
        })
    }

    /*整理成event的数据格式*/
    function formatEventData(data) {
        $('#calendar').fullCalendar('removeEvents');
        var event = {
            color: '#efffe3',
            textColor: '#444',
            className: []
        };
        for (index in data) {
            var item = data[index];
            event.className.push("bg-crew-good");
            event.id = item.id;
            event.title = item.course.name;
            event.teacher = item.teacher.name;
            event.courseId = item.id;
            event.teacherId = item.teacherId;
            event.start = item.startTime;
            event.end = item.endTime;
            event.courseId=item.courseId;
            event.classesId=item.classesId;
            event.ifTest=item.ifTest;
            $('#calendar').fullCalendar('renderEvent', event, true);
        }
    }

    function getCourseList(data) {
        var url = contextPath + "/course/findListClassifiedName";
        $.getJSON(url, data, function (rst) {
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
                teacher: $(this).data('teacher'),
                courseId: $(this).data('courseid'),
                teacherId: $(this).data('teacherid'),
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

    // //初始化、
    // var jsonStr = {
    //     name: "",
    //     class: "",
    //     crew_startDate: getdate(),
    //     crew_end_Date: '',
    //     crew_classTime_Arr: ["上午", "下午"]
    // };


    //初始化日历方法
    function initFullCalendar() {

        var crew_startDate = getdate();
        //先清空已有控件内容
        $('#calendar').fullCalendar('destroy');
        //加载日历控件
        $('#calendar').fullCalendar({
            defaultDate: crew_startDate,
            defaultView: "agendaWeek", //默认显示周
            height: 700,
            editable: true,
            customButtons: {
                //自定义 按钮可以添加多个，自己命名  在header 部分调用即可 比如这里的myCustomButto
                prev: {
                    text: '〈',
                    click: function () {
                        $('#calendar').fullCalendar('prev');
                    }
                },
                next: {
                    text: '〉',
                    click: function () {
                        $('#calendar').fullCalendar('next');

                    }
                }
            },
            header: {
                left: 'today',
                center: 'prev,title,next',
                right: ''
            },
            eventOverlap: false,//不允许事件覆盖
            allDaySlot: false,//是否显示全天
            scrollTime: '06:00:00', //滚动起始时间
            displayEventTime: true, //默认不显示事件上的时间
            defaultTimedEventDuration: '01:00:00',//默认拖进事件 的时间间隔大小*/
            buttonText: {
                today: '今天',
                week: '周',
                day: '日'
            },

            viewRender: function (e) {
                formatDate(e.title);
            },
            //点击一天时触发
            dayClick: function (date, allDay, jsEvent, view) {
            },
            // 当点击某一个事件时触发此操作
            eventClick: function (calEvent, jsEvent, view) {
                var url = contextPath + "/classSchedule/selectClassScheduleByPrimaryKey"
                var data = {
                    id: calEvent.id
                }
                $.getJSON(url, data, function (rst) {
                    if (rst.status == 0) {
                        render('#courseDetail', rst.value, 'tpl-courseDetail');
                    }
                })
            },
            droppable: true, //允许拖拽放置
            eventReceive: function (data,d) {
                var dataTime=formatEventTime(data);
                var insertDate = {};
                Object.assign(insertDate, dataTime);
                insertDate.classesId = parameter.classesId;
                insertDate.teacherId = data.teacherId;
                insertDate.courseId = data.courseId;
                var url = contextPath + "/classSchedule/insertClassSchedule"
                var jdata = {
                    jsons: JSON.stringify(insertDate)
                }
                startLoad();
                $.post(url, jdata, function (rst) {
                    if (rst.status == 0) {
                        formatDate($('#calendar').fullCalendar('getView').title);
                    } else {
                        alert("排课失败，请刷新后重试！");
                    }
                    stopLoad();

                })
            },
            eventRender: function (event, element) {
                element.find(".fc-title").html('《' + event.title + '》——' + event.teacher);//cxy修改后的，不确定会不会有其他问题
            },

            /*调整时间范围触发*/
            eventResize: function (event, dayDelta, minuteDelta, revertFunc) {
                updataCourseSchedule(event);
            },
            //拖拽之后触发（仅限控件内部事件）
            eventDrop: function (event, dayDelta, minuteDelta, allDay, revertFunc) {
                updataCourseSchedule(event);
            },
            //事件数据
            events: [],
            //选择，可选择区域
            selectable: true,
            selectHelper: true,
        });

    }


    function updataCourseSchedule(data){
        var url = contextPath + "/classSchedule/updateClassSchedule"
        var dataTime=formatEventTime(data);
        var updateDate = {};
        updateDate.id=data.id;
        updateDate.classesId = data.classesId;
        updateDate.teacherId = data.teacherId;
        updateDate.courseId = data.courseId;
        updateDate.ifTest = data.ifTest;
        Object.assign(updateDate, dataTime);
        var jdata = {
            jsons: JSON.stringify(updateDate)
        }
        startLoad();
        $.post(url, jdata, function (rst) {
            if (rst.status == 0) {
                formatDate($('#calendar').fullCalendar('getView').title);
            } else {
                alert("排课失败，请刷新后重试！");
            }
            stopLoad();
        })
    }
    
    function formatEventTime(data) {
        var rst={
        };
        var startTime = JSON.stringify(data.start).replace('T',' ').replace(/"/ig,'');
        var endTime=data.end;
        rst.startTime=startTime;
        if(endTime){
            rst.endTime=JSON.stringify(endTime).replace('T',' ').replace(/"/ig,'');
        }else{
            var item=startTime.substring(11,13);
            var i=(parseInt(item)+1);
            i=i>9?(' '+i):(' 0'+i);
            rst.endTime=startTime.replace(' '+item,i);
        }
        return rst;
    }

    function initSelect() {
        $(".js-example-basic-single2").select2({
            ajax: {
                url: contextPath + '/classes/findClassesList',
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    return {
                        status: 2,
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
            placeholder: '选择历史班次',
            escapeMarkup: function (markup) {
                return markup;
            },
        });
    }

    function initcurrentClass() {
        var url = contextPath + '/classes/findClassesList';
        var data = {
            status: 1
        }
        $.getJSON(url, data, function (rst) {
            if (rst.status == 0) {
                parameter.classesId = rst.rows[0].id;
                parameter.weekDate = getdate();
                initFullCalendar();
                render('#classList', rst.rows, 'tpl-classList');
            }
        })
    }


    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

}
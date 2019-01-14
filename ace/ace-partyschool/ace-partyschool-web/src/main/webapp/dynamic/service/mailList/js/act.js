window.onload = function () {
     jQuery(function ($) {
        initPage();
     });
}

function initPage(){
    initClassList();
    $('#btn-view-add').on('click', function () {
            bootbox.prompt("请输入组名称", function(o){
               if(o){
                   insertMailList({name:o,pid:$("#classId").val()})
               }
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

function initClassList() {
    startLoad();
    $.ajax({
        url: contextPath + "/mailList/getClassList",
        type: "post",
        async: false,
        data: {
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data = result.value;
                render('#select1', data, 'tpl-select-list');
                initClasses($("#classId").val());
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function initClasses(classId){
 startLoad();
    $.ajax({
        url: contextPath + "/mailList/getList",
        type: "post",
        async: false,
        data: {
            classId:classId
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data = result.value;
                render('#classStudent', data, 'tpl-student');
                initGroup(classId);

            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function initGroup(classId){
    startLoad();
    $.ajax({
        url: contextPath + "/mailList/getMailListContent",
        type: "post",
        async: false,
        data: {
            classId:classId
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data = result.value;
                render('#groupStudent', data, 'tpl-group');
                initTree(classId);
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

/*
             * 虽然已经设定了img元素可被拖动，但是浏览器默认地，无法将数据/元素放置到其他元素中。
             * 如果有需要设置某些元素可接受被拖动元素，则要阻止它的默认行为，
             * 这要通过设置该接收元素的ondragover 事件，调用event.preventDefault() 方法
             */
            function allowDrop(ev) {
                ev.preventDefault(); //阻止默认行为

                //ev.target.id
                //此处ev.target是接收元素，通过事件被绑定在哪个元素即可区分
            }

            /*
             * 当该img元素被拖动时，会触发一个ondragstart 事件，该事件调用了一个方法drag(event)。
             */
            function drag(ev) {
                //ev.dataTransfer.setData() 方法设置被拖数据的数据类型（Text）和值（被拖元素id），
                //该方法将被拖动元素的id存储到事件的dataTransfer对象内，ev.dataTransfer.getData()可将该元素取出。
                //此处ev.target是被拖动元素
                ev.dataTransfer.setData("Text", ev.target.id);
            }

            /*
             * 当被拖元素移动到接收元素，
             * 松开鼠标时（即被拖元素放置在接收元素内时）会出发ondrop事件
             */
            function drop(ev) {
                ev.preventDefault(); //阻止默认行为
                var data = ev.dataTransfer.getData("Text"); //将被拖动元素id取出
                ev.target.appendChild(document.getElementById(data)); //将被拖动元素添加到接收元素尾部
                var studentId=$("#"+data).data("studentid");
                console.log("studentId>"+studentId);
                var groupId=$(ev.target).data("groupid");
                if(!groupId){
                                    groupId='0';
                                }
                console.log("groupId>"+groupId);


                setTimeout(function(){
                    saveGroup(groupId,studentId);
                });

            }


            function saveGroup(groupId,studentId){



                startLoad();
                    $.ajax({
                        url: contextPath + "/mailList/updateClassesByIds",
                        type: "post",
                        async: false,
                        data: {
                            classId:groupId,
                            ids:studentId
                        },
                        success: function (result) {
                            stopLoad();
                            if (result.status == 0) {
                              initGroup($("#classId").val());
                            } else {
                                alert(result.errorMessage);
                            }
                        },
                        error: function () {
                            stopLoad();
                            alert("对不起出错了！");
                        }
                    });
            }

            function addMove(o){
                $(o).css("cursor","move");
                $(o).css("font-weight","800");
            }
            function delMove(o){
                $(o).css("cursor","default");
                $(o).css("font-weight","400");
            }

            function initTree(classId){
                $('#tt').tree({
                    url:contextPath+'/mailList/getTreeListByClassId?classId='+classId,
                    method: 'get',
                    animate: true,
                    lines:false,
                    onLoadSuccess:function()  {
                        $("#tt").tree("expandAll");
                    }
                })


            }


            function insertMailList(data){
                startLoad();
                $.ajax({
                    url: contextPath + "/mailList/insertMailList",
                    type: "post",
                    async: false,
                    data: {
                        jsons:JSON.stringify(data)
                    },
                    success: function (result) {
                        stopLoad();
                         initClasses($("#classId").val());
                        alert(result.errorMessage);
                    },
                    error: function () {
                        stopLoad();
                        alert("对不起出错了！");
                    }
                });
            }